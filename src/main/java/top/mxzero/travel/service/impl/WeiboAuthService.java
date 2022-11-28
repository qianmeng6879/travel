package top.mxzero.travel.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.mxzero.travel.dao.WeiboAuthInfoDao;
import top.mxzero.travel.exception.ServiceException;
import top.mxzero.travel.service.AuthService;
import top.mxzero.travel.service.UserService;
import top.mxzero.travel.vo.User;
import top.mxzero.travel.vo.WeiboAuthInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/11/28
 */
@Service
@Qualifier("weiboAuthService")
public class WeiboAuthService implements AuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeiboAuthService.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Value("${auth.weibo.appid}")
    private String appid;
    @Value("${auth.weibo.appkey}")
    private String appkey;
    @Value("${auth.weibo.fallback}")
    private String fallback;
    @Value("${auth.weibo.authorizeUrl}")
    private String authorizeUrl;
    @Value("${auth.weibo.accessTokenUrl}")
    private String accessTokenUrl;
    @Value("${auth.weibo.uidUrl}")
    private String uidUrl;
    @Value("${auth.weibo.userinfoUrl}")
    private String userinfoUrl;

    @Autowired
    private WeiboAuthInfoDao weiboAuthInfoDao;

    @Autowired
    private UserService userService;


    @Override
    public User authorize(String code) {
        String accessToken = this.getAccessToken(code);
        if (!StringUtils.hasLength(accessToken)) {
            LOGGER.error("获取access失败,code:{}", code);
            throw new ServiceException("获取AccessToken失败");
        }

        String uid = getUid(accessToken);
        if (!StringUtils.hasLength(uid)) {
            LOGGER.error("获取Uid失败,code:{},access_token:{}", code, accessToken);
            throw new ServiceException("获取Uid失败");
        }
        WeiboAuthInfo authInfo = weiboAuthInfoDao.selectByWeiboId(uid);

        // 未查询到认证信息，第一次使用QQ登录
        // 新增用户信息，新增qq auth信息
        if (authInfo == null) {
            Map<String, String> userinfoMap = getUserinfo(accessToken, uid);
            User user = new User();
            user.setPassword("0");
            user.setUsername(userinfoMap.get("name"));
            user.setAvatar(userinfoMap.get("avatar_large"));
            boolean result = userService.save(user);

            if (!result) {
                throw new ServiceException("新增用户失败");
            }

            LOGGER.info("add user userId:{}", user.getId());
            WeiboAuthInfo weiboAuthInfo = new WeiboAuthInfo();
            weiboAuthInfo.setWbId(uid);
            weiboAuthInfo.setUserId(user.getId());
            boolean addQQAuthResult = weiboAuthInfoDao.insert(weiboAuthInfo) > 0;
            if (!addQQAuthResult) {
                LOGGER.error("新增Weibo Auth 信息失败");
                throw new ServiceException("新增用户失败");
            }
            LOGGER.info("add auth info user_id:{}, auth_info_id:{}", user.getId(), weiboAuthInfo.getId());
            return user;
        }

        User user = userService.get(authInfo.getUserId());
        if (user == null) {
            LOGGER.error("user not found by uid-{}", uid);
            throw new ServiceException("登录失败");
        }

        LOGGER.info("用户登录:{}", user);
        return user;
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

            HttpPost httpPost = new HttpPost(builder.build());

            CloseableHttpResponse response = request.execute(httpPost);
            HttpEntity entity = response.getEntity();
            /*
             * {
             *     "access_token": "2.00jhvvpG1vS9gC1ca022e1e7zjhudB",
             *     "remind_in": "157679999",
             *     "expires_in": 157679999,
             *     "uid": "6264196643",
             *     "isRealName": "true"
             * }
             */
            String content = EntityUtils.toString(entity);
            Map data = OBJECT_MAPPER.readValue(content, Map.class);
            Object accessToken = data.get("access_token");
            if (accessToken == null) {
                return null;
            }
            LOGGER.info("access_token:{}", accessToken);
            return accessToken.toString();
        } catch (Exception ignored) {
        }

        return null;
    }

    private String getUid(String accessToken) {
        try (CloseableHttpClient request = HttpClients.createDefault()) {
            URIBuilder builder = new URIBuilder(uidUrl);
            builder.setParameter("access_token", accessToken);
            HttpGet httpGet = new HttpGet(builder.build());
            CloseableHttpResponse response = request.execute(httpGet);
            HttpEntity entity = response.getEntity();
            // {"uid":6264196643}
            String content = EntityUtils.toString(entity);

            ObjectMapper objectMapper = new ObjectMapper();
            Map data = objectMapper.readValue(content, Map.class);
            Object uid = data.get("uid");
            if (uid == null) {
                return null;
            }
            LOGGER.info("Uid:{}", uid);
            return uid.toString();
        } catch (Exception ignored) {
        }
        return null;
    }

    private Map<String, String> getUserinfo(String accessToken, String uid) {
        try (CloseableHttpClient request = HttpClients.createDefault()) {
            URIBuilder builder = new URIBuilder(userinfoUrl);
            builder
                    .setParameter("access_token", accessToken)
                    .setParameter("uid", uid);

            HttpGet httpGet = new HttpGet(builder.build());

            CloseableHttpResponse response = request.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);

            /*
                {
                    "id": 6264196643,
                    "idstr": "6264196643",
                    "screen_name": "浅浅梦汐丷",
                    "name": "浅浅梦汐丷",
                    "location": "四川",
                    "description": "低头不是认识，是要看清自己的路；仰头不是骄傲，是要看清自己的天空。",
                    "gender": "m",
                    "avatar_large": "https://tvax1.sinaimg.cn/crop.0.0.1073.1073.180/006PVVHJly8gxy53ajbqxj30tt0tttab.jpg?KID=imgbed,tva&Expires=1669640400&ssig=%2ByfWJ2bBlt"
                }
             */

            Map data = OBJECT_MAPPER.readValue(content, Map.class);

            LOGGER.info(data.toString());

            Map<String, String> map = new HashMap<>();
            map.put("id", data.get("id").toString());
            map.put("name", data.get("name").toString());
            map.put("location", data.get("location").toString());
            map.put("description", data.get("description").toString());
            map.put("avatar_large", data.get("avatar_large").toString());
            map.put("gender", data.get("gender").toString());
            return map;
        } catch (Exception ignored) {
        }
        return new HashMap<>();
    }
}
