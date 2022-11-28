package top.mxzero.travel.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.mxzero.travel.dao.QQAuthInfoDao;
import top.mxzero.travel.exception.ServiceException;
import top.mxzero.travel.service.AuthService;
import top.mxzero.travel.service.UserService;
import top.mxzero.travel.vo.QQAuthInfo;
import top.mxzero.travel.vo.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/11/28
 */
@Service
@PropertySource("classpath:auth.properties")
public class QQAuthService implements AuthService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(QQAuthService.class);
    @Value("${auth.qq.appid}")
    private String appid;
    @Value("${auth.qq.appkey}")
    private String appkey;
    @Value("${auth.qq.fallback}")
    private String fallback;
    @Value("${auth.qq.authorizeUrl}")
    private String authorizeUrl;
    @Value("${auth.qq.accessTokenUrl}")
    private String accessTokenUrl;
    @Value("${auth.qq.openIdUrl}")
    private String openIdUrl;
    @Value("${auth.qq.userinfoUrl}")
    private String userinfoUrl;

    @Autowired
    private QQAuthInfoDao qqAuthInfoDao;

    @Autowired
    private UserService userService;

    @Override
    public boolean authorize(String code) {
        String accessToken = this.getAccessToken(code);
        if (!StringUtils.hasLength(accessToken)) {
            LOGGER.error("获取access失败,code:{}", code);
            return false;
        }

        String openId = getOpenId(accessToken, code);
        if (!StringUtils.hasLength(openId)) {
            LOGGER.error("获取openId失败,code:{},access_token:{}", code, accessToken);
            return false;
        }

        QQAuthInfo authInfo = qqAuthInfoDao.selectByOpenId(openId);

        // 未查询到认证信息，第一次使用QQ登录
        // 新增用户信息，新增qq auth信息
        if (authInfo == null) {
            Map<String, String> userinfoMap = getUserinfo(accessToken, openId);
            User user = new User();
            user.setPassword("0");
            user.setAdmin(false);
            user.setCreateTime(new Date());
            user.setUsername(userinfoMap.get("nickname"));
            user.setAvatar(userinfoMap.get("figureurl_2"));
            boolean result = userService.save(user);

            LOGGER.info("add user userId:{}", user.getId());

            if (result) {
                QQAuthInfo qqAuthInfo = new QQAuthInfo();
                qqAuthInfo.setOpenId(openId);
                qqAuthInfo.setUserId(user.getId());
                boolean addQQAuthResult = qqAuthInfoDao.insert(qqAuthInfo) > 0;
                if (!addQQAuthResult) {
                    throw new ServiceException("新增用户失败");
                }
                LOGGER.info("add auth info user_id:{},open_id:{}", user.getId(), openId);
                return true;
            }
            return false;
        }

        User user = userService.get(authInfo.getUserId());
        if (user == null) {
            LOGGER.error("user not found by openId-{}", openId);
            throw new ServiceException("登录失败");
        }

        LOGGER.info("用户登录:{}", user);
        return true;
    }

    private String getAccessToken(String code) {
        try (CloseableHttpClient request = HttpClients.createDefault()) {
            URIBuilder builder = new URIBuilder(accessTokenUrl);
            builder
                    .setParameter("grant_type", "authorization_code")
                    .setParameter("code", code)
                    .setParameter("client_id", appid)
                    .setParameter("client_secret", appkey)
                    .setParameter("redirect_uri", fallback);

            HttpGet httpGet = new HttpGet(builder.build());

            CloseableHttpResponse response = request.execute(httpGet);
            HttpEntity entity = response.getEntity();
            // access_token=***&expires_in=***&refresh_token=***
            String content = EntityUtils.toString(entity);
            LOGGER.info("get accessToken content:{}", content);

            Map<String, String> data = new HashMap<>();
            for (String dataMap : content.split("&")) {
                String key = dataMap.substring(0, dataMap.indexOf("="));
                String value = dataMap.substring(dataMap.indexOf("=") + 1);
                LOGGER.info("{}:{}", key, value);
                data.put(key, value);
            }
            LOGGER.info("access_token={}", data.get("access_token"));
            LOGGER.debug("refresh_token={}", data.get("refresh_token"));
            LOGGER.debug("expires_in={}", data.get("expires_in"));
            return data.get("access_token");
        } catch (Exception ignored) {
        }

        return null;
    }

    private String getOpenId(String accessToken, String code) {
        try (CloseableHttpClient request = HttpClients.createDefault()) {
            URIBuilder builder = new URIBuilder(openIdUrl);
            builder
                    .setParameter("access_token", accessToken)
                    .setParameter("fmt", "json");

            HttpGet httpGet = new HttpGet(builder.build());

            CloseableHttpResponse response = request.execute(httpGet);
            HttpEntity entity = response.getEntity();
            // {"client_id":"**","openid":"**"}
            String content = EntityUtils.toString(entity);
            LOGGER.info("get openId content:{}", content);

            ObjectMapper objectMapper = new ObjectMapper();
            Map data = objectMapper.readValue(content, Map.class);

            LOGGER.info("openId:{}", data.get("openid"));
            return data.get("openid").toString();
        } catch (Exception ignored) {
        }
        return null;
    }

    private Map<String, String> getUserinfo(String accessToken, String openId) {
        try (CloseableHttpClient request = HttpClients.createDefault()) {
            URIBuilder builder = new URIBuilder(userinfoUrl);
            builder
                    .setParameter("oauth_consumer_key", appid)
                    .setParameter("access_token", accessToken)
                    .setParameter("openid", openId);

            HttpGet httpGet = new HttpGet(builder.build());

            CloseableHttpResponse response = request.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);

            // {
            //   "ret":0,
            //   "msg":"",
            //   "nickname":"YOUR_NICK_NAME",
            //}
            LOGGER.info("get userinfo content:{}", content);

            Map data = OBJECT_MAPPER.readValue(content, Map.class);

            LOGGER.info(data.toString());

            // 获取用户信息错误
            if ("0".equals(data.get("ret"))) {
                LOGGER.warn("get userinfo code:{} error:{}", data.get("ret"), data.get("msg"));
                return new HashMap<>();
            }

            Map<String, String> map = new HashMap<>();
            map.put("nickname", data.get("nickname").toString());
            map.put("figureurl", data.get("figureurl").toString());
            map.put("figureurl_1", data.get("figureurl_1").toString());
            map.put("figureurl_2", data.get("figureurl_2").toString());
            map.put("figureurl_qq_1", data.get("figureurl_qq_1").toString());
            map.put("figureurl_qq_2", data.get("figureurl_qq_2").toString());
            map.put("gender", data.get("gender").toString());
            return map;
        } catch (Exception ignored) {
        }
        return new HashMap<>();
    }
}
