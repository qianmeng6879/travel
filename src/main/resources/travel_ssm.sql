/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : travel_ssm

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 30/09/2022 09:19:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `recommended` tinyint(1) NULL DEFAULT 0,
  `introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES (1, '北京', 1, '今日的北京，已发展成为一座现代化的大都市：北京大学、清华大学、中国科学院等教育和科研机构座落于北京市区；金融街是中国金融监管机构办公地点和金融业聚集地；北京商务中心区是北京经济的象征；798艺术区是世界知名的当代艺术中心；此外，中国国家大剧院、北京首都国际机场3号航站楼、中央电视台总部大楼、“鸟巢”、“水立方”、中国尊等具有现代风格的建筑成为古老北京新的名片。每年有超过1亿4700万人到北京旅游。', '2022-09-23 23:42:50', NULL);
INSERT INTO `area` VALUES (2, '长春', 1, '长春，被誉为“北国春城”，绿化率居于亚洲大城市前列，中国四大园林城市之一；连续十次蝉联“中国最具幸福感城市”；“中国制造2025”试点城市；“首批全国城市设计试点城市”；位列《2015中国自然指数》中国十大科研城市第六位。', '2022-09-23 23:43:08', NULL);
INSERT INTO `area` VALUES (3, '天津', 0, '这是天津', '2022-09-23 23:43:22', NULL);
INSERT INTO `area` VALUES (12, '成都', 0, '成都 ---\"天府之国\"', '2022-09-27 15:26:20', '2022-09-27 16:09:00');
INSERT INTO `area` VALUES (14, '西藏', 0, '青藏高原', '2022-09-27 15:41:52', '2022-09-28 08:58:41');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `scenic_id` int(11) NULL DEFAULT NULL COMMENT '景区ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES (6, 1, 2, '2022-09-28 18:53:55');

-- ----------------------------
-- Table structure for scenic
-- ----------------------------
DROP TABLE IF EXISTS `scenic`;
CREATE TABLE `scenic`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `star` int(11) NULL DEFAULT 1,
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `address` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `hot` tinyint(1) NULL DEFAULT 0,
  `recommended` tinyint(1) NULL DEFAULT 0,
  `area_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of scenic
-- ----------------------------
INSERT INTO `scenic` VALUES (1, '故宫博物院', 4, '20180814133357a1167f9d31c7472eb2b482571696b050', '故宫又名紫禁城，是中国乃至世界上保存最完整，规模最大的木质结构古建筑群，被誉为“世界五大宫之首”。\r\n故宫由永乐帝朱棣下令建造，依据其布局与功用分为“外朝”与“内廷”两大部分。以乾清门为界，乾清门以南为外朝，以北为内廷。\r\n外朝也称为“前朝”，以太和殿(金銮殿)、中和殿、保和殿三大殿为中心，是封建皇帝行使权力、举行盛典的地方。\r\n内廷以乾清宫、交泰殿、坤宁宫后三宫为中心，以及东西两侧的东六宫和西六宫，是封建帝王与后妃居住之所，也就是俗称的“三宫六院”。\r\n故宫内珍藏有大量珍贵文物，据统计有上百万件，占全国文物总数的六分之一。钟表馆每天11点和14点有钟表演示，不可错过。', '<p>北京故宫是中国明清两代的皇家宫殿，旧称为紫禁城，位于北京中轴线的中心，是中国古代宫廷建筑之精华。北京故宫以三大殿为中心，占地面积72万平方米，建筑面积约15万平方米，有大小宫殿七十多座，房屋九千余间。是世界上现存规模最大、保存最为完整的木质结构古建筑之一。<br />\r\n北京故宫于明成祖永乐四年（1406年）开始建设，以南京故宫为蓝本营建，到永乐十八年（1420年）建成。它是一座长方形城池，南北长961米，东西宽753米，四面围有高10米的城墙，城外有宽52米的护城河。紫禁城内的建筑分为外朝和内廷两部分。外朝的中心为太和殿、中和殿、保和殿，统称三大殿，是国家举行大典礼的地方。内廷的中心是乾清宫、交泰殿、坤宁宫，统称后三宫，是皇帝和皇后居住的正宫。<br />\r\n北京故宫被誉为世界五大宫之首（北京故宫、法国凡尔赛宫、英国白金汉宫、美国白宫、俄罗斯克里姆林宫），是国家AAAAA级旅游景区， 1961年被列为第一批全国重点文物保护单位，1987年被列为世界文化遗产。</p>\r\n\r\n<p><img alt=\"\" src=\"/static/uploads/ckeditor/201803240935436d8289e1792e4a7ca38dbb90855404dd.jpg\" style=\"height:454px; width:1258px\" /></p>\r\n\r\n<blockquote>\r\n<h2><strong>旅游须知：</strong></h2>\r\n\r\n<p><big>电话：010-85007421</big></p>\r\n\r\n<p><big>网址：<a href=\"http://www.dpm.org.cn/\" target=\"_blank\">http://www.dpm.org.cn</a></big></p>\r\n\r\n<p><big>开放时间：除法定节假日和暑期（每年7月1日至8月31日）外，故宫博物院全年实行周一全天闭馆的措施。</big></p>\r\n\r\n<p><big>门票：（1）每年4月1日至10月31日为旺季，大门票60元/人；<br />\r\n（2）每年11月1日至次年3月31日为淡季，大门票40元/人。</big></p>\r\n</blockquote>\r\n', '北京市东城区景山前街4号', 1, 1, 1, '2022-09-23 23:44:00', NULL);
INSERT INTO `scenic` VALUES (2, '八达岭长城', 5, '2018081110544934001363864d498aaef4076eb0691469.jpg', '八达岭长城是明长城中保存最好的一段，也是最具代表性的一段，是长城重要关口居庸关的前哨，海拔高达1015米，地势险要，城关坚固，历来是兵家必争之地。登上八达岭长城，极目远望，山峦起伏，雄沉刚劲的北方山势绵延不绝。八达岭长城因山势而雄伟，山势因长城更加险峻。“不到长城非好汉”，八达岭长城是古今中外各界人士到北京游览的必到之所，迄今为止，已有包括尼克松、撒切尔夫人等在内的三百多位世界知名人士曾登上八达岭长城一览这里的山河秀色。', '<p>八达岭最著名的是它的长城。它是中国开放最早的一段长城，也是至今为止保护最好，最著名的一段明代长城。其可行部分全长3741米。它建于1504年，关城有东西二门，东门额题&ldquo;居庸外镇&rdquo;，刻于嘉靖十八年（1539年）；西门额题&ldquo;北门锁钥&rdquo;，刻于万历十年（1582年）。<br />\r\n万里长城八达岭段建于1505年（明朝弘治十八年），东窄西宽，有东西二门，南北两面各开一豁口，接连关城城墙，台上四周有砖砌垛口。城墙顶部地面铺缦方砖，嵌缝密实。内侧为宇墙，外侧为垛墙，垛墙上方有垛口，下方有射洞。城墙高低宽窄不一，平均高7米多，有些地段高达14米。墙基平均宽6.5米，顶宽5米多，可容五马并驰或十人并进。<br />\r\n八达岭和附近的长城被称为燕京八景之一的居庸叠翠。</p>\r\n\r\n<p><img alt=\"\" src=\"/static/uploads/ckeditor/201803241117457230a0ef87ce408eb920e45593fce6dc.jpg\" style=\"height:173px; width:291px\" />&nbsp; &nbsp; &nbsp;<img alt=\"\" src=\"/static/uploads/ckeditor/201803241118181610a8b216f341eeb7d055ba2a73a97a.jpg\" style=\"height:180px; width:280px\" /></p>\r\n\r\n<h2><strong>旅游须知：</strong></h2>\r\n\r\n<blockquote>\r\n<ul>\r\n	<li>\r\n	<p>电话</p>\r\n\r\n	<p>010-69121383;010-69121226</p>\r\n	</li>\r\n	<li>\r\n	<p>网址</p>\r\n\r\n	<p><a href=\"http://www.badaling.cn/\" target=\"_blank\">http://www.badaling.cn</a></p>\r\n	</li>\r\n	<li>\r\n	<p>门票</p>\r\n\r\n	<p>淡季（11-次年3月）35元/人，旺季（4-10月）40元/人<br />\r\n	淡季学生及老人等17.5元/人；旺季学生及老人等20元/人</p>\r\n	</li>\r\n	<li>\r\n	<p>开放时间</p>\r\n\r\n	<p>旺季（4~10月）：06:30~19:00<br />\r\n	淡季（11~3月）：07:00~18:00</p>\r\n	</li>\r\n</ul>\r\n</blockquote>\r\n', '北京市延庆县军都山关沟古道北口', 1, 1, 1, '2022-09-23 23:45:15', NULL);
INSERT INTO `scenic` VALUES (3, '颐和园', 4, '20180324130127eb365ba129fb4636a8f033e111c8efa2.jpg', '颐和园坐落于北京西郊，是中国古典园林之首，总面积约290公顷，由万寿山和昆明湖组成。全园分3个区域：以仁寿殿为中心的政治活动区；以玉澜堂、乐寿堂为主体的帝后生活区；以万寿山和昆明湖组成的风景旅游区。全园以西山群峰为背景，加之建筑群与园内山湖形势融为一体，使景色变幻无穷。1998年12月2日列入《世界遗产名录》。', '<p>颐和园以万寿山和昆明湖为主，昆明湖占颐和园总面积的四分之三。除了湖山，还有殿堂景区、耕织图景区。重要建筑集中在万寿山南北中轴线上。万寿山分为前山、后山两部分，前山自东向西有养云轩、无尽意轩、介寿堂、排云殿、清华轩、宝云阁、共一楼、听鹂馆、画中游等知名景观。后山南北中轴线为规模宏大的汉藏风格寺庙殿宇，包括四大部洲、须弥灵境、香岩宗印之阁等等，周围点缀以数座小型山间园林，有苏州街、寅辉城关、花承阁、赅春园、绘芳堂等建筑。昆明湖中有三座岛屿，分别名为南湖岛、藻鉴堂岛、治镜阁岛。昆明湖由一条西堤将大湖一分为二，光绪时建立围墙，修筑起了东堤。<br />\r\n颐和园的主要区域可包括六个部分，分别是殿堂景区（是帝后料理朝政和住宿所在）、万寿山景区、昆明湖景区、耕织图景区（独特的农牧色彩）、长廊景区和中轴景区（起于前山云辉玉宇牌楼，止于后山慈福慧因牌楼）。作为一座知名园林博物馆，拥有丰富制式的园林建筑和景观营造手法，涵盖了中国传统名著中的亭台楼阁，轩榭台堂。</p>\r\n\r\n<p><img alt=\"\" src=\"/static/uploads/ckeditor/20180324130057a797628bce3446ebae3b149d8c6e5358.jpeg\" style=\"height:370px; width:690px\" /></p>\r\n', '北京市海淀区新建宫门路19号', 1, 1, 1, '2022-09-23 23:45:55', NULL);
INSERT INTO `scenic` VALUES (4, '净月潭国家森林公园', 5, '20180324130554b0efff8d6f114c34802ee773dbe996e2.jpeg', '净月潭因其弯月状而得名，被誉为台湾日月潭的姊妹潭。公园内树木茂盛、空气清新，处处皆景致，四季貌不同。\r\n地貌呈低山丘陵状，有大小山峰119座，其中海拔高于200米的就有近120座，而独以潭北的山色为最。这里有86座山岭自北向南延伸至潭边，不仅沟壑纵横，层峦叠嶂，而且可以登山鸟瞰潭水全景。\r\n整座山仿佛巨蟒蜿蜒，其中犹以大架山海拔最高，是绝佳的度假胜地。此外，园内植物格局模拟长白山植物垂地分布特征，有山花、药用植物等千余种。', '<p>净月潭景区位于吉林省长春市东南部长春净月经济开发区，距市中心人民广场仅18公里，景区面积为96.38平方公里，其中水域面积为5.3平方公里，森林覆盖率达到96%以上。净月潭因形似弯月状而得名，与台湾日月潭互为姊妹潭。<br />\r\n净月潭是在1934年由人工修建的第一座为长春市城区供水的水源地，在沦陷时期称&rdquo;水源地&ldquo;或&rdquo;贮水池&ldquo;。净月潭的名字是由&rdquo;满洲国总理大臣&ldquo;郑孝胥的二儿子，时任&rdquo;满洲国国都建设局局长&ldquo;的郑禹所起。景区内的森林为人工建造，含有30个树种的完整森林生态体系，得天独厚的区位优势，使之成为&ldquo;喧嚣都市中的一块净土&rdquo;，有&ldquo;亚洲第一大人工林海&rdquo;、&ldquo;绿海明珠&rdquo;、&ldquo;都市氧吧&rdquo;之美誉，是长春市的生态绿核和城市名片。<br />\r\n净月潭景区处处皆景致，四季貌不同。亚洲最大的人工森林与山、水相依的生态景象构成了净月潭四季变幻的风情画卷。净月潭已然成为春踏青、夏避暑、秋赏景、冬玩冰雪的理想去处。<br />\r\n净月潭不仅是生态休闲中心，更是体育健身的中心，作为长春市消夏节和长春冰雪节的主场地，相继开展了净月潭瓦萨国际滑雪节、净月潭森林马拉松、净月潭森林定向赛、净月潭自行车马拉松、青少年阳光体育大会、龙舟赛、旅游大集等赛事和活动，致力于倡导健康、时尚、休闲的生活方式，打造国际知名旅游文化活动的聚集地。</p>\r\n\r\n<p><img alt=\"\" src=\"/static/uploads/ckeditor/2018032413055096c1be099bdf427d8cdd3e985d76cae0.jpg\" style=\"height:125px; width:402px\" /></p>\r\n', '吉林省长春市净月开发区', 1, 1, 2, '2022-09-23 23:46:22', NULL);
INSERT INTO `scenic` VALUES (5, '伪满皇宫博物院', 5, '20180324131008f4e5802528d847279cdfb84d8974cdc1.jpeg', '伪满皇宫博物院的主要建筑有勤民楼、缉熙楼、怀远楼、嘉乐殿、同德殿、书画库、莱薰门、保康门、兴运门、建国神庙遗迹和御用防空洞等，对游客开放的还有跑马场和东北沦陷史陈列馆。', '<p>1931年九一八事变后，日本扶植清朝末代皇帝爱新觉罗&middot;溥仪建立满洲国，以此控制中国东北。当时的中华民国及1949年成立的中华人民共和国均不承认满洲国，所以称其为&ldquo;伪满洲国&rdquo;或&ldquo;伪满&rdquo;。1932年3月9日溥仪就任满洲国执政，4月3日执政府迁入原吉黑榷运局官署（民国时期管理吉林、黑龙江两省盐务）旧址。1934年3月1日溥仪称帝，执政府机关改为宫内府。因日本天皇的宫廷叫皇宫，溥仪的这个宫廷就只能对外称为&ldquo;帝宫&rdquo;。至1945年8月日本投降前，满洲国帝宫一直作为溥仪办公和生活的地方，其宫廷区域分为用于政务办公的外廷和帝室生活的内廷两个部分，建筑风格东西合璧。1945年满洲国解体以后，帝宫建筑毁损严重。<br />\r\n1962年在满洲国帝宫旧址上成立伪皇宫陈列馆，1964年与吉林省博物馆合署办公，1982年恢复建制，1984年正式对外开放。2000年7月1日划归长春市管理，2001年2月8日改名为伪满皇宫博物院；现已全面整修，基本恢复旧貌。2013年，中华人民共和国国务院将之以伪满皇宫及日伪军政机构旧址之名列入第七批全国重点文物保护单位（近现代重要史迹及代表性建筑）。<br />\r\n2017年，伪满皇宫博物院被中国博物馆协会评为第三批国家一级博物馆。</p>\r\n\r\n<p><img alt=\"\" src=\"/static/uploads/ckeditor/201803241309567f4e4bffb6f04a8498ff137b9a3558d6.jpg\" style=\"height:498px; width:750px\" /></p>\r\n', '吉林省长春市宽城区光复北路5号', 1, 1, 2, '2022-09-23 23:46:57', NULL);
INSERT INTO `scenic` VALUES (6, '童话世界', 5, 'scenic/304d0f3c-4258-482e-ab2b-3ec0b38fb7d5.jpeg', '成都童话世界', '<h1>成都童话世界</h1>\r\n', '成都童话世界', 0, 1, 12, '2022-09-27 22:59:52', NULL);
INSERT INTO `scenic` VALUES (7, '北京胡同', 5, 'scenic/5098cec4-c52e-4b32-b17d-e519ff8919c6.png', '北京胡同北京胡同', '<h1>北京胡同北京胡同</h1>\r\n', '北京胡同', 0, 0, 1, '2022-09-27 23:01:41', NULL);

-- ----------------------------
-- Table structure for suggestion
-- ----------------------------
DROP TABLE IF EXISTS `suggestion`;
CREATE TABLE `suggestion`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of suggestion
-- ----------------------------
INSERT INTO `suggestion` VALUES (1, '张三', 'zhangsan@qq.com', '测试', '2022-09-27 23:19:41');
INSERT INTO `suggestion` VALUES (2, '施密特', 'smith@qq.com', '建议的内容是。。。。。。。', '2022-09-27 23:55:13');
INSERT INTO `suggestion` VALUES (3, 'das', 'das@qq.com', 'dasdasds', '2022-09-28 20:36:44');
INSERT INTO `suggestion` VALUES (4, 'das', 'das@qq.com', 'dasdasds', '2022-09-28 20:36:45');
INSERT INTO `suggestion` VALUES (5, 'das', 'das@qq.com', 'dasdasds', '2022-09-28 20:36:46');
INSERT INTO `suggestion` VALUES (6, 'das', 'das@qq.com', 'dasdasds', '2022-09-28 20:36:47');
INSERT INTO `suggestion` VALUES (7, 'das', 'das@qq.com', 'dasdasds', '2022-09-28 20:36:47');
INSERT INTO `suggestion` VALUES (8, 'das', 'das@qq.com', 'dasdasds', '2022-09-28 20:36:47');
INSERT INTO `suggestion` VALUES (9, 'das', 'das@qq.com', 'dasdasds', '2022-09-28 20:36:47');
INSERT INTO `suggestion` VALUES (10, 'das', 'das@qq.com', 'dasdasds', '2022-09-28 20:36:47');
INSERT INTO `suggestion` VALUES (11, 'das', 'das@qq.com', 'dasdasds', '2022-09-28 20:36:48');
INSERT INTO `suggestion` VALUES (12, 'das', 'das@qq.com', 'dasdasds', '2022-09-28 20:36:48');
INSERT INTO `suggestion` VALUES (13, 'das', 'das@qq.com', 'dasdasds', '2022-09-28 20:36:48');
INSERT INTO `suggestion` VALUES (14, 'das', 'das@qq.com', 'dasdasds', '2022-09-28 20:36:48');
INSERT INTO `suggestion` VALUES (15, 'das', 'das@qq.com', 'dasdasds', '2022-09-28 20:36:48');
INSERT INTO `suggestion` VALUES (16, 'das', 'das@qq.com', 'dasdasds', '2022-09-28 20:36:48');
INSERT INTO `suggestion` VALUES (17, 'das', 'das@qq.com', 'dasdasds', '2022-09-28 20:36:48');
INSERT INTO `suggestion` VALUES (18, 'das', 'das@qq.com', 'dasdasds', '2022-09-28 20:36:49');
INSERT INTO `suggestion` VALUES (19, 'das', 'das@qq.com', 'dasdasds', '2022-09-28 20:36:49');

-- ----------------------------
-- Table structure for travels
-- ----------------------------
DROP TABLE IF EXISTS `travels`;
CREATE TABLE `travels`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `scenic_id` int(11) NULL DEFAULT NULL,
  `addtime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `title`(`title`) USING BTREE,
  INDEX `scenic_id`(`scenic_id`) USING BTREE,
  INDEX `ix_travels_addtime`(`addtime`) USING BTREE,
  CONSTRAINT `travels_ibfk_1` FOREIGN KEY (`scenic_id`) REFERENCES `scenic` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of travels
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admin` tinyint(1) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zero', '$2a$10$7gSGgVcxqMUj4LKCzh.1ie.slW7AzwGRhMsbfrTpw4nyaxjqwkAxq', 'zero', 'fsadfsadfsdfsd', 'users/huazai.jpg', 1, '2022-09-23 17:51:49', '2022-09-28 11:55:56', NULL);
INSERT INTO `user` VALUES (10, '张三', '$2a$10$UliM.XFFzYcvLipCPwme8e7DskzxE4xLcxMr2Sm39JATVoCkqzrbq', 'zhangsan@qq.com', NULL, 'users/huazai.jpg', 0, '2022-09-23 19:06:08', NULL, NULL);
INSERT INTO `user` VALUES (11, 'asdfsdf', '$2a$10$ZjISadsKOHvBnlH63igFO.29CYGLdeNbJcQmNxq3zazBgGZXjW5Ne', 'qq@qq.com', NULL, NULL, 0, '2022-09-28 11:19:53', NULL, NULL);
INSERT INTO `user` VALUES (12, '赵启', '$2a$10$Tuudb3C505x..Eyp2kyR5et7H9TT7IYJZdNWvoN6pG/YF.RJHlgEC', 'zhaoqi@qq.com', NULL, NULL, 0, '2022-09-28 18:51:39', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
