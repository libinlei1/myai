# 导出表itheima.school结构
DROP TABLE IF EXISTS `school`;
CREATE TABLE IF NOT EXISTS school(
`id` int unSigned NOT NULL AUTO_INCREMENT COMMENT'主键',
`name` varchar(50) COLLATE utf8mb4_generaL_ci DEFAULT NULL COMMENT '校区名称',
`city` varchar(50) COLLATE utf8mb4_generaL_ci DEFAULT NULL COMMENT  '校区所在城市',
primary key(`id`))
ENGINE=InNODB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci COMMENT='校区表';

# 导出表itheima.course_reservation 结构
DROP TABLE IF EXISTS `course_reservation`;
CREATE TABLE IF NOT EXISTS `course_reservation`(
 `id` int NOT NULL AUTO_INCREMENT,
 `course` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
DEFAULT '' COMMENT '预约课程',
`student_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NoT
NULL COMMENT'学生姓名',
`contact_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NoT NULL COMMENT'联系方式',
`school` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '预约校区',
`remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT'备注',PRIMARY KEY(`id`))
ENGINE=InNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

# 导出表itheima.course 结构
DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course`(
  `id` int unSigned NOT NULL AUTO_INCREMENT COMMENT'主键',
  `name` varchar(50) COLLATE utf8mb4_general_Ci NOT NULL DEFAULT ''COMMENT '学科名称',
  `edu` int NOT NULL DEFAULT '0' COMMENT '学历背景要求:0-无，1-初中，2-高中、3-大专、4-本科以上',
  `type` varchar(50) COLLATE utf8mb4_generaL_Ci NOT NULL DEFAULT 'O' COMMENT '课程类型:编程、设计、自媒体、其它',
  `price` bigint NOT NULL DEFAULT '0' COMMENT '课程价格',
  `duration`int unsigned NOT NULL DEFAULT '0' COMMENT '学习时长，单位:天',
  PRIMARY KEY(`id`))
ENGINE=InNODB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci COMMENT='学科表';