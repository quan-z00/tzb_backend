/*
 Navicat Premium Data Transfer

 Source Server         : tzb
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36-0ubuntu0.22.04.1)
 Source Host           : 8.219.8.34:3306
 Source Schema         : tzb

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36-0ubuntu0.22.04.1)
 File Encoding         : 65001

 Date: 11/05/2024 21:16:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `parentId` int NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `layout` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `keepAlive` tinyint NULL DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `show` tinyint NOT NULL DEFAULT 1 COMMENT '是否展示在页面菜单',
  `enable` tinyint NOT NULL DEFAULT 1,
  `order` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `IDX_30e166e8c6359970755c5727a2`(`code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '资源管理', 'Resource_Mgt', 'MENU', 2, '/pms/resource', NULL, 'i-fe:list', '/src/views/pms/resource/index.vue', NULL, NULL, NULL, NULL, 1, 1, 1);
INSERT INTO `permission` VALUES (2, '系统管理', 'SysMgt', 'MENU', NULL, NULL, NULL, 'i-fe:grid', NULL, NULL, NULL, NULL, NULL, 1, 1, 2);
INSERT INTO `permission` VALUES (3, '角色管理', 'RoleMgt', 'MENU', 2, '/pms/role', NULL, 'i-fe:user-check', '/src/views/pms/role/index.vue', NULL, NULL, NULL, NULL, 1, 1, 2);
INSERT INTO `permission` VALUES (4, '用户管理', 'UserMgt', 'MENU', 2, '/pms/user', NULL, 'i-fe:user', '/src/views/pms/user/index.vue', NULL, 1, NULL, NULL, 1, 1, 3);
INSERT INTO `permission` VALUES (5, '分配用户', 'RoleUser', 'MENU', 3, '/pms/role/user/:roleId', NULL, 'i-fe:user-plus', '/src/views/pms/role/role-user.vue', 'full', NULL, NULL, NULL, 0, 1, 1);
INSERT INTO `permission` VALUES (6, '业务示例', 'Demo', 'MENU', NULL, NULL, NULL, 'i-fe:grid', NULL, NULL, NULL, NULL, NULL, 1, 1, 1);
INSERT INTO `permission` VALUES (7, '图片上传', 'ImgUpload', 'MENU', 6, '/demo/upload', NULL, 'i-fe:image', '/src/views/demo/upload/index.vue', '', 1, NULL, NULL, 1, 1, 2);
INSERT INTO `permission` VALUES (8, '个人资料', 'UserProfile', 'MENU', NULL, '/profile', NULL, 'i-fe:user', '/src/views/profile/index.vue', NULL, NULL, NULL, NULL, 0, 1, 99);
INSERT INTO `permission` VALUES (9, '基础功能', 'Base', 'MENU', NULL, '', NULL, 'i-fe:grid', NULL, '', NULL, NULL, NULL, 1, 1, 0);
INSERT INTO `permission` VALUES (10, '基础组件', 'BaseComponents', 'MENU', 9, '/base/components', NULL, 'i-me:awesome', '/src/views/base/index.vue', NULL, NULL, NULL, NULL, 1, 1, 1);
INSERT INTO `permission` VALUES (11, 'Unocss', 'Unocss', 'MENU', 9, '/base/unocss', NULL, 'i-me:awesome', '/src/views/base/unocss.vue', NULL, NULL, NULL, NULL, 1, 1, 2);
INSERT INTO `permission` VALUES (12, 'KeepAlive', 'KeepAlive', 'MENU', 9, '/base/keep-alive', NULL, 'i-me:awesome', '/src/views/base/keep-alive.vue', NULL, 1, NULL, NULL, 1, 1, 3);
INSERT INTO `permission` VALUES (13, '创建新用户', 'AddUser', 'BUTTON', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, 1);
INSERT INTO `permission` VALUES (14, '图标 Icon', 'Icon', 'MENU', 9, '/base/icon', NULL, 'i-fe:feather', '/src/views/base/unocss-icon.vue', '', NULL, NULL, NULL, 1, 1, 0);
INSERT INTO `permission` VALUES (15, 'MeModal', 'TestModal', 'MENU', 9, '/testModal', NULL, 'i-me:dialog', '/src/views/base/test-modal.vue', NULL, NULL, NULL, NULL, 1, 1, 5);

-- ----------------------------
-- Table structure for profile
-- ----------------------------
DROP TABLE IF EXISTS `profile`;
CREATE TABLE `profile`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `gender` int NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `userId` int NOT NULL,
  `nickName` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `IDX_a24972ebd73b106250713dcddd`(`userId` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of profile
-- ----------------------------
INSERT INTO `profile` VALUES (1, 1, 'https://img2.baidu.com/it/u=3904576940,647717226&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', '四川省德阳市广汉市', '2900221581@qq.com', 1, 'Admin');
INSERT INTO `profile` VALUES (2, NULL, NULL, '2', NULL, 3, NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enable` tinyint NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `IDX_ee999bb389d7ac0fd967172c41`(`code` ASC) USING BTREE,
  UNIQUE INDEX `IDX_ae4578dcaed5adff96595e6166`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'SUPER_ADMIN', '超级管理员', 1);
INSERT INTO `role` VALUES (2, 'ROLE_QA', '质检员', 1);

-- ----------------------------
-- Table structure for role_permissions_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permissions_permission`;
CREATE TABLE `role_permissions_permission`  (
  `roleId` int NOT NULL,
  `permissionId` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_b36cb2e04bc353ca4ede00d87b`(`roleId` ASC) USING BTREE,
  INDEX `IDX_bfbc9e263d4cea6d7a8c9eb3ad`(`permissionId` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role_permissions_permission
-- ----------------------------
INSERT INTO `role_permissions_permission` VALUES (2, 1, 15);
INSERT INTO `role_permissions_permission` VALUES (2, 7, 16);
INSERT INTO `role_permissions_permission` VALUES (2, 12, 17);
INSERT INTO `role_permissions_permission` VALUES (2, 9, 18);
INSERT INTO `role_permissions_permission` VALUES (2, 14, 19);
INSERT INTO `role_permissions_permission` VALUES (2, 10, 20);
INSERT INTO `role_permissions_permission` VALUES (2, 11, 21);
INSERT INTO `role_permissions_permission` VALUES (2, 15, 22);

-- ----------------------------
-- Table structure for special_exhibits
-- ----------------------------
DROP TABLE IF EXISTS `special_exhibits`;
CREATE TABLE `special_exhibits`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `era` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `material` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `width` decimal(10, 2) NULL DEFAULT NULL,
  `height` decimal(10, 2) NULL DEFAULT NULL,
  `depth` decimal(10, 2) NULL DEFAULT NULL,
  `weight` decimal(10, 2) NULL DEFAULT NULL,
  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `story` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `protection_status` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `exhibition_history` json NULL,
  `related_artifacts` json NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of special_exhibits
-- ----------------------------
INSERT INTO `special_exhibits` VALUES (1, '青铜人像', '青铜人像是三星堆出土的文物之一，被认为是古代巴蜀文明的重要代表之一。', 'https://img.zcool.cn/community/012c0a5f9e38a511013fdcc7a5b141.png?x-oss-process=image/auto-orient,1/resize,m_lfit,w_1280,limit_1/sharpen,100', '古蜀时期', '青铜', 50.00, 100.00, 20.00, 200.00, '珍贵', '这些青铜人像代表了古代巴蜀人民的劳动和生活情景。', '完好', '[\"曾在多次国内外展览中展出，备受关注。\"]', '[\"与其他出土文物有密切关联，展示出三星堆古文明的独特魅力。\"]');
INSERT INTO `special_exhibits` VALUES (2, '神树纹玉璧', '神树纹玉璧是三星堆遗址中出土的一种玉器，具有很高的历史和文化价值。', 'https://img1.baidu.com/it/u=940092778,1476839712&fm=253&fmt=auto&app=120&f=JPEG?w=800&h=500', '古蜀时期', '玉石', 30.00, 30.00, 3.00, 50.00, '稀世珍品', '这件神树纹玉璧是三星堆文明中玉石工艺的杰作之一，刻有神树纹图案，寓意祈福。', '完好', '[\"曾在国内外博物馆举办的特展中展出，引起了广泛关注和讨论。\"]', '[\"与其他出土文物一起展示了三星堆文明的宗教信仰和艺术表现\"]');
INSERT INTO `special_exhibits` VALUES (3, '青铜面具', '青铜面具是三星堆遗址中发现的一种珍贵文物，展现了古代巴蜀文明的艺术水平和审美观念。', 'https://i.ibb.co/NsKk0QW/u-3084165683-3475638012-fm-253-fmt-auto-app-138-f-JPEG.webp', '古蜀时期', '青铜', 25.00, 20.00, 5.00, 80.00, '珍贵文物', '这些青铜面具具有独特的工艺和设计，反映了古代巴蜀人民的生活和信仰。', '完好', '[\"多次在国内外博物馆举办的特展中展出，备受瞩目和赞誉。\"]', '[\"与其他文物一同展示了古代巴蜀人的文化传承和创造力。\"]');
INSERT INTO `special_exhibits` VALUES (4, '青铜神树', '青铜神树是三星堆遗址中出土的一种装饰品，被认为是古代巴蜀文明中的神圣象征。', 'https://img0.baidu.com/it/u=450736165,4167186160&fm=253&fmt=auto&app=138&f=JPEG?w=600&h=400', '古蜀时期', '青铜', 40.00, 80.00, 10.00, 150.00, '文化遗产', '青铜神树象征着古代巴蜀人民的信仰和文化传统，是一种珍贵的宗教艺术品。', '完好', '[\"曾在多次国内外博物馆举办的特展中展出，备受观众喜爱。\"]', '[\"与其他出土文物共同展示了古代巴蜀文明的宗教信仰和文化底蕴\"]');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enable` tinyint NOT NULL DEFAULT 1,
  `createTime` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updateTime` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `IDX_78a916df40e02a9deb1c4b75ed`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$mStlKYfFw.U54E5IXKLfv.Fe0DVaKSV.FwaybfJJrKRMu0kXmPK8O', 1, '2023-11-18 16:18:59.150632', '2023-11-18 16:18:59.150632');
INSERT INTO `user` VALUES (3, '123456', '$2a$10$7LelK5qwaJdEeDnD4LvR9eiF73Anf9tbRVFKV8OFnn2ZczyqO8NOq', 1, '2024-05-11 15:28:40.346713', '2024-05-11 15:28:40.346713');

-- ----------------------------
-- Table structure for user_roles_role
-- ----------------------------
DROP TABLE IF EXISTS `user_roles_role`;
CREATE TABLE `user_roles_role`  (
  `userId` int NOT NULL,
  `roleId` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_5f9286e6c25594c6b88c108db7`(`userId` ASC) USING BTREE,
  INDEX `IDX_4be2f7adf862634f5f803d246b`(`roleId` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_roles_role
-- ----------------------------
INSERT INTO `user_roles_role` VALUES (1, 1, 1);
INSERT INTO `user_roles_role` VALUES (1, 2, 2);
INSERT INTO `user_roles_role` VALUES (3, 2, 6);

SET FOREIGN_KEY_CHECKS = 1;
