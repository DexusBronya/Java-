/*
 Navicat Premium Data Transfer

 Source Server         : ss
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : eems

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 25/03/2024 10:43:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ee
-- ----------------------------
DROP TABLE IF EXISTS `ee`;
CREATE TABLE `ee`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `enumber` char(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '员工工号 5位',
  `ename` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '员工年龄 ',
  `esex` char(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '员工性别 男/女',
  `eborn` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '员工出生日期 格式2023-3-23',
  `eentry` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '员工入职日期 格式2023-3-23',
  `esalary` int NULL DEFAULT NULL COMMENT '员工薪水 10000',
  `eauthority` char(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '2' COMMENT '0超级管理员 1管理员 2普通员工',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for eecheck
-- ----------------------------
DROP TABLE IF EXISTS `eecheck`;
CREATE TABLE `eecheck`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `enumber` char(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '员工工号',
  `date` datetime NULL DEFAULT NULL COMMENT '是否考勤的日期',
  `dateSearch` datetime NULL DEFAULT NULL COMMENT '检索日期',
  `goonwork` char(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '1/0 上班是否考勤 1考勤 0未考勤 默认0',
  `gooffwork` char(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '1/0 下班是否考勤 1考勤 0未考勤 默认0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
