-- MySQL dump 10.13  Distrib 8.2.0, for Win64 (x86_64)
--
-- Host: localhost    Database: tzb
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments`
(
    `id`         int  NOT NULL AUTO_INCREMENT COMMENT '评论ID',
    `father_id`  int           DEFAULT NULL COMMENT '父评论ID',
    `post_id`    int  NOT NULL COMMENT '帖子ID',
    `user_id`    int  NOT NULL COMMENT '用户ID',
    `reply_id`   int           DEFAULT NULL COMMENT '回复者ID',
    `content`    text NOT NULL COMMENT '评论内容',
    `status`     int  NOT NULL DEFAULT '0' COMMENT '审核状态',
    `created_at` datetime      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` datetime      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY          `post_id` (`post_id`),
    KEY          `user_id` (`user_id`),
    CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`),
    CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `frontend_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK
TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments`
VALUES (1, NULL, 1, 1, NULL, '我是消息1', 1, '2024-05-16 18:18:57', '2024-05-16 18:18:57'),
       (2, 1, 1, 4, 1, '我是消息2,回复消息1', 1, '2024-05-16 18:18:57', '2024-05-16 18:20:26'),
       (3, 2, 1, 1, 4, '我是消息3,回复消息2', 1, '2024-05-16 18:18:57', '2024-05-16 18:20:26');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `front_user_profile`
--

DROP TABLE IF EXISTS `front_user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `front_user_profile`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `user_id`    int NOT NULL,
    `avatar`     varchar(255) DEFAULT NULL,
    `signature`  varchar(255) DEFAULT NULL,
    `nickname`   varchar(100) DEFAULT NULL,
    `gender`     tinyint(1) DEFAULT NULL,
    `birthday`   date         DEFAULT NULL,
    `location`   varchar(100) DEFAULT NULL,
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY          `user_id` (`user_id`),
    CONSTRAINT `front_user_profile_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `frontend_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `front_user_profile`
--

LOCK
TABLES `front_user_profile` WRITE;
/*!40000 ALTER TABLE `front_user_profile` DISABLE KEYS */;
INSERT INTO `front_user_profile`
VALUES (1, 1, NULL, 'Hello word', '老王', 1, '2024-05-15', NULL, '2023-12-31 16:00:00', '2024-05-14 15:26:15'),
       (3, 4, NULL, NULL, NULL, 2, '2024-05-03', NULL, '2024-05-14 16:20:25', '2024-05-15 13:21:38'),
       (4, 5, NULL, NULL, NULL, NULL, NULL, NULL, '2024-05-15 13:24:29', '2024-05-15 13:24:29'),
       (5, 6, NULL, NULL, NULL, NULL, NULL, NULL, '2024-05-15 13:24:35', '2024-05-15 13:24:35'),
       (6, 7, NULL, NULL, NULL, NULL, NULL, NULL, '2024-05-15 13:24:41', '2024-05-15 13:24:41'),
       (7, 8, NULL, NULL, '用户3708', NULL, NULL, NULL, '2024-05-15 13:30:07', '2024-05-15 13:30:07'),
       (8, 14, NULL, NULL, '用户408', NULL, NULL, NULL, '2024-05-15 13:38:40', '2024-05-15 13:38:40'),
       (9, 15, NULL, NULL, '用户7782', NULL, NULL, NULL, '2024-05-15 13:38:50', '2024-05-15 13:38:50'),
       (10, 16, NULL, NULL, '用户615', NULL, NULL, NULL, '2024-05-15 13:38:55', '2024-05-15 13:38:55'),
       (11, 17, NULL, NULL, '用户9784', NULL, NULL, NULL, '2024-05-15 13:39:00', '2024-05-15 13:39:00'),
       (12, 18, NULL, NULL, '用户641', NULL, NULL, NULL, '2024-05-15 13:39:05', '2024-05-15 13:39:05'),
       (13, 19, NULL, NULL, '用户9603', NULL, NULL, NULL, '2024-05-15 13:39:44', '2024-05-15 13:39:44');
/*!40000 ALTER TABLE `front_user_profile` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `frontend_user`
--

DROP TABLE IF EXISTS `frontend_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `frontend_user`
(
    `id`         int          NOT NULL AUTO_INCREMENT,
    `username`   varchar(50)  NOT NULL,
    `password`   varchar(255) NOT NULL,
    `email`      varchar(100) NOT NULL,
    `enable`     tinyint(1) DEFAULT '1',
    `type`       int DEFAULT '0',
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `score`      int DEFAULT '0',
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`),
    UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frontend_user`
--

LOCK
TABLES `frontend_user` WRITE;
/*!40000 ALTER TABLE `frontend_user` DISABLE KEYS */;
INSERT INTO `frontend_user`
VALUES (1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'admin@example.com', 0, 0,
        '2024-05-14 10:26:22', '2024-05-17 09:32:53', 0),
       (4, 'John', '008c70392e3abfbd0fa47bbc2ed96aa99bd49e159727fcba0f2e6abeb3a9d601', 'john.doe@example.com', 0, 0,
        '2024-05-14 16:20:25', '2024-05-17 09:34:56', 0),
       (5, 'John1', '008c70392e3abfbd0fa47bbc2ed96aa99bd49e159727fcba0f2e6abeb3a9d601', 'john.doe1@example.com', 1, 0,
        '2024-05-15 13:24:29', '2024-05-17 09:37:35', 0),
       (6, 'John2', '008c70392e3abfbd0fa47bbc2ed96aa99bd49e159727fcba0f2e6abeb3a9d601', 'john.doe2@example.com', 1, 1,
        '2024-05-15 13:24:35', '2024-05-17 09:37:32', 0),
       (7, 'John3', '008c70392e3abfbd0fa47bbc2ed96aa99bd49e159727fcba0f2e6abeb3a9d601', 'john.doe3@example.com', 1, 0,
        '2024-05-15 13:24:41', '2024-05-17 09:37:34', 0),
       (8, 'TestUser1', '008c70392e3abfbd0fa47bbc2ed96aa99bd49e159727fcba0f2e6abeb3a9d601', 'test@example.com', 1, 0,
        '2024-05-15 13:30:06', '2024-05-17 09:37:33', 0),
       (14, 'TestUser2', '008c70392e3abfbd0fa47bbc2ed96aa99bd49e159727fcba0f2e6abeb3a9d601', 'test1@example.com', 1, 1,
        '2024-05-15 13:38:40', '2024-05-17 09:35:22', 0),
       (15, 'TestUser3', '008c70392e3abfbd0fa47bbc2ed96aa99bd49e159727fcba0f2e6abeb3a9d601', 'test2@example.com', 0, 0,
        '2024-05-15 13:38:50', '2024-05-17 09:35:23', 0),
       (16, 'TestUser4', '008c70392e3abfbd0fa47bbc2ed96aa99bd49e159727fcba0f2e6abeb3a9d601', 'test4@example.com', 0, 1,
        '2024-05-15 13:38:55', '2024-05-17 09:35:21', 0),
       (17, 'TestUser5', '008c70392e3abfbd0fa47bbc2ed96aa99bd49e159727fcba0f2e6abeb3a9d601', 'test5@example.com', 1, 1,
        '2024-05-15 13:39:00', '2024-05-17 09:35:12', 0),
       (18, 'TestUser6', '008c70392e3abfbd0fa47bbc2ed96aa99bd49e159727fcba0f2e6abeb3a9d601', 'test6@example.com', 1, 1,
        '2024-05-15 13:39:05', '2024-05-17 09:32:33', 0),
       (19, 'TestUser7', '008c70392e3abfbd0fa47bbc2ed96aa99bd49e159727fcba0f2e6abeb3a9d601', 'test7@example.com', 0, 0,
        '2024-05-15 13:39:44', '2024-05-15 14:45:38', 0);
/*!40000 ALTER TABLE `frontend_user` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission`
(
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `parentId`  int                                                           DEFAULT NULL,
  `path`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `redirect`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `icon`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `layout`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `keepAlive` tinyint                                                       DEFAULT NULL,
  `method`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `show`      tinyint NOT NULL                                              DEFAULT '1' COMMENT '是否展示在页面菜单',
  `enable`    tinyint NOT NULL                                              DEFAULT '1',
  `order`     int                                                           DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `IDX_30e166e8c6359970755c5727a2` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK
TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission`
VALUES (1, '资源管理', 'Resource_Mgt', 'MENU', 2, '/pms/resource', NULL, 'i-fe:list',
        '/src/views/pms/resource/index.vue', NULL, NULL, NULL, NULL, 1, 1, 1),
       (2, '系统管理', 'SysMgt', 'MENU', NULL, NULL, NULL, 'i-fe:grid', NULL, NULL, NULL, NULL, NULL, 1, 1, 300),
       (3, '角色管理', 'RoleMgt', 'MENU', 2, '/pms/role', NULL, 'i-fe:user-check', '/src/views/pms/role/index.vue',
        NULL, NULL, NULL, NULL, 1, 1, 2),
       (4, '用户管理', 'UserMgt', 'MENU', 2, '/pms/user', NULL, 'i-fe:user', '/src/views/pms/user/index.vue', NULL, 1,
        NULL, NULL, 1, 1, 3),
       (5, '分配用户', 'RoleUser', 'MENU', 3, '/pms/role/user/:roleId', NULL, 'i-fe:user-plus',
        '/src/views/pms/role/role-user.vue', 'full', NULL, NULL, NULL, 0, 1, 1),
       (6, '业务示例', 'Demo', 'MENU', NULL, NULL, NULL, 'i-fe:grid', NULL, NULL, NULL, NULL, NULL, 1, 0, 500),
       (7, '图片上传', 'ImgUpload', 'MENU', 6, '/demo/upload', NULL, 'i-fe:image', '/src/views/demo/upload/index.vue',
        '', 1, NULL, NULL, 1, 1, 2),
       (8, '个人资料', 'UserProfile', 'MENU', NULL, '/profile', NULL, 'i-fe:user', '/src/views/profile/index.vue', NULL,
        NULL, NULL, NULL, 0, 1, 99),
       (9, '基础功能', 'Base', 'MENU', NULL, '', NULL, 'i-fe:grid', NULL, '', NULL, NULL, NULL, 1, 1, 400),
       (10, '基础组件', 'BaseComponents', 'MENU', 9, '/base/components', NULL, 'i-me:awesome',
        '/src/views/base/index.vue', NULL, NULL, NULL, NULL, 1, 1, 1),
       (11, 'Unocss', 'Unocss', 'MENU', 9, '/base/unocss', NULL, 'i-me:awesome', '/src/views/base/unocss.vue', NULL,
        NULL, NULL, NULL, 1, 1, 2),
       (12, 'KeepAlive', 'KeepAlive', 'MENU', 9, '/base/keep-alive', NULL, 'i-me:awesome',
        '/src/views/base/keep-alive.vue', NULL, 1, NULL, NULL, 1, 1, 3),
       (13, '创建新用户', 'AddUser', 'BUTTON', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 0, 1),
       (14, '图标 Icon', 'Icon', 'MENU', 9, '/base/icon', NULL, 'i-fe:feather', '/src/views/base/unocss-icon.vue', '',
        NULL, NULL, NULL, 1, 1, 0),
       (15, 'MeModal', 'TestModal', 'MENU', 9, '/testModal', NULL, 'i-me:dialog', '/src/views/base/test-modal.vue',
        NULL, NULL, NULL, NULL, 1, 1, 5),
       (21, '前台用户管理', 'User', 'MENU', NULL, '', NULL, 'i-fe:user', '/src/views/user/list/index.vue', '', 0, NULL,
        NULL, 1, 1, 100),
       (25, '用户列表', 'List', 'MENU', 21, '/user/list', NULL, 'i-fe:users', '/src/views/user/list/index.vue', '', 1,
        NULL, NULL, 1, 1, 1),
       (26, '预约列表', 'UserBooking', 'MENU', 21, '/user/booking', NULL, 'i-fe:feather',
        '/src/views/user/booking/index.vue', '', 1, NULL, NULL, 1, 1, 2),
       (27, 'DIY分享交流管理', 'DiyShare', 'MENU', NULL, NULL, NULL, 'i-fe:twitch', NULL, '', NULL, NULL, NULL, 1, 1,
        200),
       (28, '帖子列表', 'Post', 'MENU', 27, '/diy-share/post', NULL, 'i-fe:book-open',
        '/src/views/diy-share/post/index.vue', '', 1, NULL, NULL, 1, 1, 0),
       (29, '评论列表', 'Comment', 'MENU', 27, '/diy-share/comment', NULL, 'i-fe:message-circle',
        '/src/views/diy-share/comment/index.vue', '', 1, NULL, NULL, 1, 1, 1),
       (30, '首页', 'Home', 'MENU', NULL, '/', NULL, 'i-fe:airplay', '/src/views/home/index.vue', '', NULL, NULL, NULL,
        1, 1, -100),
       (31, '动态管理', 'News', 'MENU', NULL, '/news', NULL, 'i-fe:command', '/src/views/news/index.vue', '', 1, NULL,
        NULL, 1, 1, 101),
       (32, '增加新闻', 'AddNews', 'BUTTON', 31, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, 1, 1, NULL);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts`
(
    `id`         int          NOT NULL AUTO_INCREMENT,
    `title`      varchar(255) NOT NULL,
    `content`    text         NOT NULL,
    `author_id`  int          NOT NULL,
    `tags`       varchar(255) DEFAULT NULL,
    `status`     int          DEFAULT '3',
    `views`      int          DEFAULT '0',
    `likes`      int          DEFAULT '0',
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY          `author_id` (`author_id`),
    CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `frontend_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK
TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts`
VALUES (1, 'Getting started with VueJS', 'This is a post about VueJS...', 1, 'VueJS,JavaScript', 1, 0, 0,
        '2024-05-14 10:26:22', '2024-05-14 10:26:22'),
       (2, 'Building a REST API with SpringBoot', 'This is a post about SpringBoot...', 8, 'SpringBoot', 1, 0, 0,
        '2024-05-14 10:26:22', '2024-05-17 02:29:16');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile`
(
  `id` int NOT NULL AUTO_INCREMENT,
  `gender`   int                                                           DEFAULT NULL,
  `avatar`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80',
  `address`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `userId` int NOT NULL,
  `nickName` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `IDX_a24972ebd73b106250713dcddd` (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK
TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile`
VALUES (1, 1, 'https://img2.baidu.com/it/u=3904576940,647717226&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500',
        '四川省德阳市广汉市', '2900221581@qq.com', 1, 'Admin'),
       (2, NULL, NULL, '2', NULL, 3, NULL);
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role`
(
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enable` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `IDX_ee999bb389d7ac0fd967172c41` (`code`) USING BTREE,
  UNIQUE KEY `IDX_ae4578dcaed5adff96595e6166` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK
TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role`
VALUES (1, 'SUPER_ADMIN', '超级管理员', 1),
       (2, 'ROLE_QA', '质检员', 1);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `role_permissions_permission`
--

DROP TABLE IF EXISTS `role_permissions_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permissions_permission`
(
  `roleId` int NOT NULL,
  `permissionId` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `IDX_b36cb2e04bc353ca4ede00d87b` (`roleId`) USING BTREE,
  KEY `IDX_bfbc9e263d4cea6d7a8c9eb3ad` (`permissionId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permissions_permission`
--

LOCK
TABLES `role_permissions_permission` WRITE;
/*!40000 ALTER TABLE `role_permissions_permission` DISABLE KEYS */;
INSERT INTO `role_permissions_permission`
VALUES (2, 7, 43),
       (2, 12, 44),
       (2, 9, 45),
       (2, 14, 46),
       (2, 10, 47),
       (2, 11, 48),
       (2, 15, 49),
       (2, 21, 50),
       (2, 25, 51),
       (2, 26, 52),
       (2, 8, 53),
       (2, 6, 54);
/*!40000 ALTER TABLE `role_permissions_permission` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `special_exhibits`
--

DROP TABLE IF EXISTS `special_exhibits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `special_exhibits`
(
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description`        text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `image`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `era`                varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `material`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `width`              decimal(10, 2)                                                DEFAULT NULL,
  `height`             decimal(10, 2)                                                DEFAULT NULL,
  `depth`              decimal(10, 2)                                                DEFAULT NULL,
  `weight`             decimal(10, 2)                                                DEFAULT NULL,
  `value`              varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `story`              text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `protection_status`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `exhibition_history` json                                                          DEFAULT NULL,
  `related_artifacts`  json                                                          DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `special_exhibits`
--

LOCK
TABLES `special_exhibits` WRITE;
/*!40000 ALTER TABLE `special_exhibits` DISABLE KEYS */;
INSERT INTO `special_exhibits`
VALUES (1, '青铜人像', '青铜人像是三星堆出土的文物之一，被认为是古代巴蜀文明的重要代表之一。',
        'https://img.zcool.cn/community/012c0a5f9e38a511013fdcc7a5b141.png?x-oss-process=image/auto-orient,1/resize,m_lfit,w_1280,limit_1/sharpen,100',
        '古蜀时期', '青铜', 50.00, 100.00, 20.00, 200.00, '珍贵', '这些青铜人像代表了古代巴蜀人民的劳动和生活情景。',
        '完好', '[
    \"曾在多次国内外展览中展出，备受关注。\"]', '[
    \"与其他出土文物有密切关联，展示出三星堆古文明的独特魅力。\"]'),
       (2, '神树纹玉璧', '神树纹玉璧是三星堆遗址中出土的一种玉器，具有很高的历史和文化价值。',
        'https://img1.baidu.com/it/u=940092778,1476839712&fm=253&fmt=auto&app=120&f=JPEG?w=800&h=500', '古蜀时期',
        '玉石', 30.00, 30.00, 3.00, 50.00, '稀世珍品',
        '这件神树纹玉璧是三星堆文明中玉石工艺的杰作之一，刻有神树纹图案，寓意祈福。', '完好', '[
         \"曾在国内外博物馆举办的特展中展出，引起了广泛关注和讨论。\"]', '[
         \"与其他出土文物一起展示了三星堆文明的宗教信仰和艺术表现\"]'),
       (3, '青铜面具', '青铜面具是三星堆遗址中发现的一种珍贵文物，展现了古代巴蜀文明的艺术水平和审美观念。',
        'https://i.ibb.co/NsKk0QW/u-3084165683-3475638012-fm-253-fmt-auto-app-138-f-JPEG.webp', '古蜀时期', '青铜',
        25.00, 20.00, 5.00, 80.00, '珍贵文物', '这些青铜面具具有独特的工艺和设计，反映了古代巴蜀人民的生活和信仰。',
        '完好', '[
         \"多次在国内外博物馆举办的特展中展出，备受瞩目和赞誉。\"]', '[
         \"与其他文物一同展示了古代巴蜀人的文化传承和创造力。\"]'),
       (4, '青铜神树', '青铜神树是三星堆遗址中出土的一种装饰品，被认为是古代巴蜀文明中的神圣象征。',
        'https://img0.baidu.com/it/u=450736165,4167186160&fm=253&fmt=auto&app=138&f=JPEG?w=600&h=400', '古蜀时期',
        '青铜', 40.00, 80.00, 10.00, 150.00, '文化遗产',
        '青铜神树象征着古代巴蜀人民的信仰和文化传统，是一种珍贵的宗教艺术品。', '完好', '[
         \"曾在多次国内外博物馆举办的特展中展出，备受观众喜爱。\"]', '[
         \"与其他出土文物共同展示了古代巴蜀文明的宗教信仰和文化底蕴\"]');
/*!40000 ALTER TABLE `special_exhibits` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `tb_news`
--

DROP TABLE IF EXISTS `tb_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_news`
(
    `id`         int      NOT NULL AUTO_INCREMENT,
    `title`      varchar(255)      DEFAULT NULL,
    `content`    text,
    `author`     varchar(255)      DEFAULT NULL,
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `image_url`  varchar(255)      DEFAULT NULL,
    `status`     int               DEFAULT '0',
    PRIMARY KEY (`id`),
    KEY          `tb_news_user_id_fk` (`author`),
    KEY          `tb_news_created_at_index` (`created_at`),
    KEY          `tb_news_status_index` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_news`
--

LOCK
TABLES `tb_news` WRITE;
/*!40000 ALTER TABLE `tb_news` DISABLE KEYS */;
INSERT INTO `tb_news`
VALUES (1, '三星堆遗址的发现与意义',
        '三星堆是中国古代文明的重要遗址之一，其发现对于研究古代文明、人类起源和文化交流具有重要意义。', '张三',
        '2023-04-12 09:00:00', 'http://localhost:8080/img/7e2c3182-78da-4a29-927c-443b121d771f.jpg', 0),
       (2, '三星堆文明的特点与成就',
        '三星堆文明以青铜器制作工艺和玉器雕刻工艺闻名于世，展现了古代巴蜀人民的智慧和技术水平。', '李四',
        '2023-04-13 10:30:00', '', 1),
       (3, '三星堆青铜神树的神秘之处', '三星堆青铜神树是古代巴蜀文明的重要象征之一，其神秘的图案和含义引人深思。', '王五',
        '2023-04-14 11:45:00', '', 2),
       (4, '三星堆遗址的考古发现',
        '近年来，对三星堆遗址的考古发掘工作取得了重要进展，发现了大量珍贵文物和遗迹，为研究古代文明提供了重要资料。',
        '赵六', '2023-04-15 13:00:00', '', 0),
       (5, '三星堆文化的传承与发展',
        '三星堆文化是中国古代文明的重要组成部分，其在艺术、宗教、政治等方面的影响持续至今，对于当代文化传承与发展具有重要意义。',
        '钱七', '2023-04-16 14:20:00', '', 1),
       (6, '三星堆遗址保护与管理',
        '为了保护三星堆遗址和相关文物，加强遗址保护工作和管理至关重要，需要政府、学界和社会各界的共同努力。', '孙八',
        '2023-04-17 15:40:00', '', 0),
       (7, '三星堆文物的特色与魅力',
        '三星堆文物以其独特的艺术风格、精美的工艺和丰富的历史内涵，吸引着大量游客和学者前来参观和研究。', '周九',
        '2023-04-18 16:55:00', '', 2),
       (8, '三星堆遗址的保护与利用',
        '三星堆遗址的保护和利用是一个重要课题，需要综合考虑文物保护、旅游开发、科研教育等多方面因素，实现保护和利用的双赢。',
        '吴十', '2023-04-19 17:30:00', '', 1),
       (9, '三星堆文明与世界文化',
        '三星堆文明作为中国古代文明的重要组成部分，对世界文化产生了重要影响，是世界文化遗产的重要组成部分。', '李白',
        '2023-04-20 18:15:00', 'http://localhost:8080/img/7e2c3182-78da-4a29-927c-443b121d771f.jpg', 2),
       (10, '三星堆文化遗产的保护与传承',
        '为了保护和传承三星堆文化遗产，需要加强对遗址和文物的保护工作，开展相关研究和教育，推动文化传承和创新发展。',
        '杜甫', '2023-04-21 19:00:00', 'http://localhost:8080/img/c6626feb-966e-4c03-b3d1-b8db629024b5.png', 1);
/*!40000 ALTER TABLE `tb_news` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user`
(
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enable` tinyint NOT NULL DEFAULT '1',
  `createTime` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updateTime` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `IDX_78a916df40e02a9deb1c4b75ed` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK
TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user`
VALUES (1, 'admin', '$2a$10$mStlKYfFw.U54E5IXKLfv.Fe0DVaKSV.FwaybfJJrKRMu0kXmPK8O', 1, '2024-05-15 14:59:21.762675',
        '2024-05-15 14:59:21.762675'),
       (3, '123456', '$2a$10$7LelK5qwaJdEeDnD4LvR9eiF73Anf9tbRVFKV8OFnn2ZczyqO8NOq', 1, '2024-05-16 16:00:58.477514',
        '2024-05-16 16:00:58.477514');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `user_roles_role`
--

DROP TABLE IF EXISTS `user_roles_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles_role`
(
  `userId` int NOT NULL,
  `roleId` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `IDX_5f9286e6c25594c6b88c108db7` (`userId`) USING BTREE,
  KEY `IDX_4be2f7adf862634f5f803d246b` (`roleId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles_role`
--

LOCK
TABLES `user_roles_role` WRITE;
/*!40000 ALTER TABLE `user_roles_role` DISABLE KEYS */;
INSERT INTO `user_roles_role`
VALUES (1, 1, 1),
       (1, 2, 2),
       (3, 2, 12),
       (3, 1, 13);
/*!40000 ALTER TABLE `user_roles_role` ENABLE KEYS */;
UNLOCK
TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-18 13:15:49
