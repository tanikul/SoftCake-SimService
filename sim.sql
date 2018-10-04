/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : sim

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-10-04 10:17:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `amphur`
-- ----------------------------
DROP TABLE IF EXISTS `amphur`;
CREATE TABLE `amphur` (
  `AMPHUR_ID` int(5) NOT NULL AUTO_INCREMENT,
  `AMPHUR_CODE` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `AMPHUR_NAME` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `AMPHUR_NAME_ENG` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `GEO_ID` int(5) NOT NULL DEFAULT 0,
  `PROVINCE_ID` int(5) NOT NULL DEFAULT 0,
  PRIMARY KEY (`AMPHUR_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=999 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of amphur
-- ----------------------------
INSERT INTO `amphur` VALUES ('1', '1001', 'เขตพระนคร   ', 'Khet Phra Nakhon', '2', '1');
INSERT INTO `amphur` VALUES ('2', '1002', 'เขตดุสิต   ', 'Khet Dusit', '2', '1');
INSERT INTO `amphur` VALUES ('3', '1003', 'เขตหนองจอก   ', 'Khet  Nong Chok', '2', '1');
INSERT INTO `amphur` VALUES ('4', '1004', 'เขตบางรัก   ', 'Khet Bang Rak', '2', '1');
INSERT INTO `amphur` VALUES ('5', '1005', 'เขตบางเขน   ', 'Khet Bang Khen', '2', '1');
INSERT INTO `amphur` VALUES ('6', '1006', 'เขตบางกะปิ   ', 'Khet Bang Kapi', '2', '1');
INSERT INTO `amphur` VALUES ('7', '1007', 'เขตปทุมวัน   ', 'Khet Pathum Wan', '2', '1');
INSERT INTO `amphur` VALUES ('8', '1008', 'เขตป้อมปราบศัตรูพ่าย   ', 'Khet Pom Prap Sattru Phai', '2', '1');
INSERT INTO `amphur` VALUES ('9', '1009', 'เขตพระโขนง   ', 'Khet Phra Khanong', '2', '1');
INSERT INTO `amphur` VALUES ('10', '1010', 'เขตมีนบุรี   ', 'Khet Min Buri', '2', '1');
INSERT INTO `amphur` VALUES ('11', '1011', 'เขตลาดกระบัง   ', 'Khet Lat Krabang', '2', '1');
INSERT INTO `amphur` VALUES ('12', '1012', 'เขตยานนาวา   ', 'Khet Yan Nawa', '2', '1');
INSERT INTO `amphur` VALUES ('13', '1013', 'เขตสัมพันธวงศ์   ', 'Khet Samphanthawong', '2', '1');
INSERT INTO `amphur` VALUES ('14', '1014', 'เขตพญาไท   ', 'Khet Phaya Thai', '2', '1');
INSERT INTO `amphur` VALUES ('15', '1015', 'เขตธนบุรี   ', 'Khet Thon Buri', '2', '1');
INSERT INTO `amphur` VALUES ('16', '1016', 'เขตบางกอกใหญ่   ', 'Khet Bangkok Yai', '2', '1');
INSERT INTO `amphur` VALUES ('17', '1017', 'เขตห้วยขวาง   ', 'Khet Huai Khwang', '2', '1');
INSERT INTO `amphur` VALUES ('18', '1018', 'เขตคลองสาน   ', 'Khet Khlong San', '2', '1');
INSERT INTO `amphur` VALUES ('19', '1019', 'เขตตลิ่งชัน   ', 'Khet Taling Chan', '2', '1');
INSERT INTO `amphur` VALUES ('20', '1020', 'เขตบางกอกน้อย   ', 'Khet Bangkok Noi', '2', '1');
INSERT INTO `amphur` VALUES ('21', '1021', 'เขตบางขุนเทียน   ', 'Khet Bang Khun Thian', '2', '1');
INSERT INTO `amphur` VALUES ('22', '1022', 'เขตภาษีเจริญ   ', 'Khet Phasi Charoen', '2', '1');
INSERT INTO `amphur` VALUES ('23', '1023', 'เขตหนองแขม   ', 'Khet Nong Khaem', '2', '1');
INSERT INTO `amphur` VALUES ('24', '1024', 'เขตราษฎร์บูรณะ   ', 'Khet Rat Burana', '2', '1');
INSERT INTO `amphur` VALUES ('25', '1025', 'เขตบางพลัด   ', 'Khet Bang Phlat', '2', '1');
INSERT INTO `amphur` VALUES ('26', '1026', 'เขตดินแดง   ', 'Khet Din Daeng', '2', '1');
INSERT INTO `amphur` VALUES ('27', '1027', 'เขตบึงกุ่ม   ', 'Khet Bueng Kum', '2', '1');
INSERT INTO `amphur` VALUES ('28', '1028', 'เขตสาทร   ', 'Khet Sathon', '2', '1');
INSERT INTO `amphur` VALUES ('29', '1029', 'เขตบางซื่อ   ', 'Khet Bang Sue', '2', '1');
INSERT INTO `amphur` VALUES ('30', '1030', 'เขตจตุจักร   ', 'Khet Chatuchak', '2', '1');
INSERT INTO `amphur` VALUES ('31', '1031', 'เขตบางคอแหลม   ', 'Khet Bang Kho Laem', '2', '1');
INSERT INTO `amphur` VALUES ('32', '1032', 'เขตประเวศ   ', 'Khet Prawet', '2', '1');
INSERT INTO `amphur` VALUES ('33', '1033', 'เขตคลองเตย   ', 'Khet Khlong Toei', '2', '1');
INSERT INTO `amphur` VALUES ('34', '1034', 'เขตสวนหลวง   ', 'Khet Suan Luang', '2', '1');
INSERT INTO `amphur` VALUES ('35', '1035', 'เขตจอมทอง   ', 'Khet Chom Thong', '2', '1');
INSERT INTO `amphur` VALUES ('36', '1036', 'เขตดอนเมือง   ', 'Khet Don Mueang', '2', '1');
INSERT INTO `amphur` VALUES ('37', '1037', 'เขตราชเทวี   ', 'Khet Ratchathewi', '2', '1');
INSERT INTO `amphur` VALUES ('38', '1038', 'เขตลาดพร้าว   ', 'Khet Lat Phrao', '2', '1');
INSERT INTO `amphur` VALUES ('39', '1039', 'เขตวัฒนา   ', 'Khet Watthana', '2', '1');
INSERT INTO `amphur` VALUES ('40', '1040', 'เขตบางแค   ', 'Khet Bang Khae', '2', '1');
INSERT INTO `amphur` VALUES ('41', '1041', 'เขตหลักสี่   ', 'Khet Lak Si', '2', '1');
INSERT INTO `amphur` VALUES ('42', '1042', 'เขตสายไหม   ', 'Khet Sai Mai', '2', '1');
INSERT INTO `amphur` VALUES ('43', '1043', 'เขตคันนายาว   ', 'Khet Khan Na Yao', '2', '1');
INSERT INTO `amphur` VALUES ('44', '1044', 'เขตสะพานสูง   ', 'Khet Saphan Sung', '2', '1');
INSERT INTO `amphur` VALUES ('45', '1045', 'เขตวังทองหลาง   ', 'Khet Wang Thonglang', '2', '1');
INSERT INTO `amphur` VALUES ('46', '1046', 'เขตคลองสามวา   ', 'Khet Khlong Sam Wa', '2', '1');
INSERT INTO `amphur` VALUES ('47', '1047', 'เขตบางนา   ', 'Khet Bang Na', '2', '1');
INSERT INTO `amphur` VALUES ('48', '1048', 'เขตทวีวัฒนา   ', 'Khet Thawi Watthana', '2', '1');
INSERT INTO `amphur` VALUES ('49', '1049', 'เขตทุ่งครุ   ', 'Khet Thung Khru', '2', '1');
INSERT INTO `amphur` VALUES ('50', '1050', 'เขตบางบอน   ', 'Khet Bang Bon', '2', '1');
INSERT INTO `amphur` VALUES ('51', '1081', '*บ้านทะวาย   ', '*Bantawai', '2', '1');
INSERT INTO `amphur` VALUES ('52', '1101', 'เมืองสมุทรปราการ   ', 'Mueang Samut Prakan', '2', '2');
INSERT INTO `amphur` VALUES ('53', '1102', 'บางบ่อ   ', 'Bang Bo', '2', '2');
INSERT INTO `amphur` VALUES ('54', '1103', 'บางพลี   ', 'Bang Phli', '2', '2');
INSERT INTO `amphur` VALUES ('55', '1104', 'พระประแดง   ', 'Phra Pradaeng', '2', '2');
INSERT INTO `amphur` VALUES ('56', '1105', 'พระสมุทรเจดีย์   ', 'Phra Samut Chedi', '2', '2');
INSERT INTO `amphur` VALUES ('57', '1106', 'บางเสาธง   ', 'Bang Sao Thong', '2', '2');
INSERT INTO `amphur` VALUES ('58', '1201', 'เมืองนนทบุรี   ', 'Mueang Nonthaburi', '2', '3');
INSERT INTO `amphur` VALUES ('59', '1202', 'บางกรวย   ', 'Bang Kruai', '2', '3');
INSERT INTO `amphur` VALUES ('60', '1203', 'บางใหญ่   ', 'Bang Yai', '2', '3');
INSERT INTO `amphur` VALUES ('61', '1204', 'บางบัวทอง   ', 'Bang Bua Thong', '2', '3');
INSERT INTO `amphur` VALUES ('62', '1205', 'ไทรน้อย   ', 'Sai Noi', '2', '3');
INSERT INTO `amphur` VALUES ('63', '1206', 'ปากเกร็ด   ', 'Pak Kret', '2', '3');
INSERT INTO `amphur` VALUES ('64', '1251', 'เทศบาลนครนนทบุรี (สาขาแขวงท่าทราย)*   ', 'Tetsaban Nonthaburi', '2', '3');
INSERT INTO `amphur` VALUES ('65', '1297', 'เทศบาลเมืองปากเกร็ด*   ', 'Tetsaban Pak Kret', '2', '3');
INSERT INTO `amphur` VALUES ('66', '1301', 'เมืองปทุมธานี   ', 'Mueang Pathum Thani', '2', '4');
INSERT INTO `amphur` VALUES ('67', '1302', 'คลองหลวง   ', 'Khlong Luang', '2', '4');
INSERT INTO `amphur` VALUES ('68', '1303', 'ธัญบุรี   ', 'Thanyaburi', '2', '4');
INSERT INTO `amphur` VALUES ('69', '1304', 'หนองเสือ   ', 'Nong Suea', '2', '4');
INSERT INTO `amphur` VALUES ('70', '1305', 'ลาดหลุมแก้ว   ', 'Lat Lum Kaeo', '2', '4');
INSERT INTO `amphur` VALUES ('71', '1306', 'ลำลูกกา   ', 'Lam Luk Ka', '2', '4');
INSERT INTO `amphur` VALUES ('72', '1307', 'สามโคก   ', 'Sam Khok', '2', '4');
INSERT INTO `amphur` VALUES ('73', '1351', 'ลำลูกกา (สาขาตำบลคูคต)*   ', 'Khlong Luang(Kukod)', '2', '4');
INSERT INTO `amphur` VALUES ('74', '1401', 'พระนครศรีอยุธยา   ', 'Phra Nakhon Si Ayutthaya', '2', '5');
INSERT INTO `amphur` VALUES ('75', '1402', 'ท่าเรือ   ', 'Tha Ruea', '2', '5');
INSERT INTO `amphur` VALUES ('76', '1403', 'นครหลวง   ', 'Nakhon Luang', '2', '5');
INSERT INTO `amphur` VALUES ('77', '1404', 'บางไทร   ', 'Bang Sai', '2', '5');
INSERT INTO `amphur` VALUES ('78', '1405', 'บางบาล   ', 'Bang Ban', '2', '5');
INSERT INTO `amphur` VALUES ('79', '1406', 'บางปะอิน   ', 'Bang Pa-in', '2', '5');
INSERT INTO `amphur` VALUES ('80', '1407', 'บางปะหัน   ', 'Bang Pahan', '2', '5');
INSERT INTO `amphur` VALUES ('81', '1408', 'ผักไห่   ', 'Phak Hai', '2', '5');
INSERT INTO `amphur` VALUES ('82', '1409', 'ภาชี   ', 'Phachi', '2', '5');
INSERT INTO `amphur` VALUES ('83', '1410', 'ลาดบัวหลวง   ', 'Lat Bua Luang', '2', '5');
INSERT INTO `amphur` VALUES ('84', '1411', 'วังน้อย   ', 'Wang Noi', '2', '5');
INSERT INTO `amphur` VALUES ('85', '1412', 'เสนา   ', 'Sena', '2', '5');
INSERT INTO `amphur` VALUES ('86', '1413', 'บางซ้าย   ', 'Bang Sai', '2', '5');
INSERT INTO `amphur` VALUES ('87', '1414', 'อุทัย   ', 'Uthai', '2', '5');
INSERT INTO `amphur` VALUES ('88', '1415', 'มหาราช   ', 'Maha Rat', '2', '5');
INSERT INTO `amphur` VALUES ('89', '1416', 'บ้านแพรก   ', 'Ban Phraek', '2', '5');
INSERT INTO `amphur` VALUES ('90', '1501', 'เมืองอ่างทอง   ', 'Mueang Ang Thong', '2', '6');
INSERT INTO `amphur` VALUES ('91', '1502', 'ไชโย   ', 'Chaiyo', '2', '6');
INSERT INTO `amphur` VALUES ('92', '1503', 'ป่าโมก   ', 'Pa Mok', '2', '6');
INSERT INTO `amphur` VALUES ('93', '1504', 'โพธิ์ทอง   ', 'Pho Thong', '2', '6');
INSERT INTO `amphur` VALUES ('94', '1505', 'แสวงหา   ', 'Sawaeng Ha', '2', '6');
INSERT INTO `amphur` VALUES ('95', '1506', 'วิเศษชัยชาญ   ', 'Wiset Chai Chan', '2', '6');
INSERT INTO `amphur` VALUES ('96', '1507', 'สามโก้   ', 'Samko', '2', '6');
INSERT INTO `amphur` VALUES ('97', '1601', 'เมืองลพบุรี   ', 'Mueang Lop Buri', '2', '7');
INSERT INTO `amphur` VALUES ('98', '1602', 'พัฒนานิคม   ', 'Phatthana Nikhom', '2', '7');
INSERT INTO `amphur` VALUES ('99', '1603', 'โคกสำโรง   ', 'Khok Samrong', '2', '7');
INSERT INTO `amphur` VALUES ('100', '1604', 'ชัยบาดาล   ', 'Chai Badan', '2', '7');
INSERT INTO `amphur` VALUES ('101', '1605', 'ท่าวุ้ง   ', 'Tha Wung', '2', '7');
INSERT INTO `amphur` VALUES ('102', '1606', 'บ้านหมี่   ', 'Ban Mi', '2', '7');
INSERT INTO `amphur` VALUES ('103', '1607', 'ท่าหลวง   ', 'Tha Luang', '2', '7');
INSERT INTO `amphur` VALUES ('104', '1608', 'สระโบสถ์   ', 'Sa Bot', '2', '7');
INSERT INTO `amphur` VALUES ('105', '1609', 'โคกเจริญ   ', 'Khok Charoen', '2', '7');
INSERT INTO `amphur` VALUES ('106', '1610', 'ลำสนธิ   ', 'Lam Sonthi', '2', '7');
INSERT INTO `amphur` VALUES ('107', '1611', 'หนองม่วง   ', 'Nong Muang', '2', '7');
INSERT INTO `amphur` VALUES ('108', '1681', '*อ.บ้านเช่า  จ.ลพบุรี   ', '*Amphoe Ban Chao', '2', '7');
INSERT INTO `amphur` VALUES ('109', '1701', 'เมืองสิงห์บุรี   ', 'Mueang Sing Buri', '2', '8');
INSERT INTO `amphur` VALUES ('110', '1702', 'บางระจัน   ', 'Bang Rachan', '2', '8');
INSERT INTO `amphur` VALUES ('111', '1703', 'ค่ายบางระจัน   ', 'Khai Bang Rachan', '2', '8');
INSERT INTO `amphur` VALUES ('112', '1704', 'พรหมบุรี   ', 'Phrom Buri', '2', '8');
INSERT INTO `amphur` VALUES ('113', '1705', 'ท่าช้าง   ', 'Tha Chang', '2', '8');
INSERT INTO `amphur` VALUES ('114', '1706', 'อินทร์บุรี   ', 'In Buri', '2', '8');
INSERT INTO `amphur` VALUES ('115', '1801', 'เมืองชัยนาท   ', 'Mueang Chai Nat', '2', '9');
INSERT INTO `amphur` VALUES ('116', '1802', 'มโนรมย์   ', 'Manorom', '2', '9');
INSERT INTO `amphur` VALUES ('117', '1803', 'วัดสิงห์   ', 'Wat Sing', '2', '9');
INSERT INTO `amphur` VALUES ('118', '1804', 'สรรพยา   ', 'Sapphaya', '2', '9');
INSERT INTO `amphur` VALUES ('119', '1805', 'สรรคบุรี   ', 'Sankhaburi', '2', '9');
INSERT INTO `amphur` VALUES ('120', '1806', 'หันคา   ', 'Hankha', '2', '9');
INSERT INTO `amphur` VALUES ('121', '1807', 'หนองมะโมง   ', 'Nong Mamong', '2', '9');
INSERT INTO `amphur` VALUES ('122', '1808', 'เนินขาม   ', 'Noen Kham', '2', '9');
INSERT INTO `amphur` VALUES ('123', '1901', 'เมืองสระบุรี   ', 'Mueang Saraburi', '2', '10');
INSERT INTO `amphur` VALUES ('124', '1902', 'แก่งคอย   ', 'Kaeng Khoi', '2', '10');
INSERT INTO `amphur` VALUES ('125', '1903', 'หนองแค   ', 'Nong Khae', '2', '10');
INSERT INTO `amphur` VALUES ('126', '1904', 'วิหารแดง   ', 'Wihan Daeng', '2', '10');
INSERT INTO `amphur` VALUES ('127', '1905', 'หนองแซง   ', 'Nong Saeng', '2', '10');
INSERT INTO `amphur` VALUES ('128', '1906', 'บ้านหมอ   ', 'Ban Mo', '2', '10');
INSERT INTO `amphur` VALUES ('129', '1907', 'ดอนพุด   ', 'Don Phut', '2', '10');
INSERT INTO `amphur` VALUES ('130', '1908', 'หนองโดน   ', 'Nong Don', '2', '10');
INSERT INTO `amphur` VALUES ('131', '1909', 'พระพุทธบาท   ', 'Phra Phutthabat', '2', '10');
INSERT INTO `amphur` VALUES ('132', '1910', 'เสาไห้   ', 'Sao Hai', '2', '10');
INSERT INTO `amphur` VALUES ('133', '1911', 'มวกเหล็ก   ', 'Muak Lek', '2', '10');
INSERT INTO `amphur` VALUES ('134', '1912', 'วังม่วง   ', 'Wang Muang', '2', '10');
INSERT INTO `amphur` VALUES ('135', '1913', 'เฉลิมพระเกียรติ   ', 'Chaloem Phra Kiat', '2', '10');
INSERT INTO `amphur` VALUES ('136', '2001', 'เมืองชลบุรี   ', 'Mueang Chon Buri', '5', '11');
INSERT INTO `amphur` VALUES ('137', '2002', 'บ้านบึง   ', 'Ban Bueng', '5', '11');
INSERT INTO `amphur` VALUES ('138', '2003', 'หนองใหญ่   ', 'Nong Yai', '5', '11');
INSERT INTO `amphur` VALUES ('139', '2004', 'บางละมุง   ', 'Bang Lamung', '5', '11');
INSERT INTO `amphur` VALUES ('140', '2005', 'พานทอง   ', 'Phan Thong', '5', '11');
INSERT INTO `amphur` VALUES ('141', '2006', 'พนัสนิคม   ', 'Phanat Nikhom', '5', '11');
INSERT INTO `amphur` VALUES ('142', '2007', 'ศรีราชา   ', 'Si Racha', '5', '11');
INSERT INTO `amphur` VALUES ('143', '2008', 'เกาะสีชัง   ', 'Ko Sichang', '5', '11');
INSERT INTO `amphur` VALUES ('144', '2009', 'สัตหีบ   ', 'Sattahip', '5', '11');
INSERT INTO `amphur` VALUES ('145', '2010', 'บ่อทอง   ', 'Bo Thong', '5', '11');
INSERT INTO `amphur` VALUES ('146', '2011', 'เกาะจันทร์   ', 'Ko Chan', '5', '11');
INSERT INTO `amphur` VALUES ('147', '2051', 'สัตหีบ (สาขาตำบลบางเสร่)*   ', 'Sattahip(Bang Sre)*', '5', '11');
INSERT INTO `amphur` VALUES ('148', '2072', 'ท้องถิ่นเทศบาลเมืองหนองปรือ*   ', 'Tong Tin Tetsaban Mueang Nong Prue*', '5', '11');
INSERT INTO `amphur` VALUES ('149', '2093', 'เทศบาลตำบลแหลมฉบัง*   ', 'Tetsaban Tambon Lamsabang*', '5', '11');
INSERT INTO `amphur` VALUES ('150', '2099', 'เทศบาลเมืองชลบุรี*   ', 'Mueang Chon Buri', '5', '11');
INSERT INTO `amphur` VALUES ('151', '2101', 'เมืองระยอง   ', 'Mueang Rayong', '5', '12');
INSERT INTO `amphur` VALUES ('152', '2102', 'บ้านฉาง   ', 'Ban Chang', '5', '12');
INSERT INTO `amphur` VALUES ('153', '2103', 'แกลง   ', 'Klaeng', '5', '12');
INSERT INTO `amphur` VALUES ('154', '2104', 'วังจันทร์   ', 'Wang Chan', '5', '12');
INSERT INTO `amphur` VALUES ('155', '2105', 'บ้านค่าย   ', 'Ban Khai', '5', '12');
INSERT INTO `amphur` VALUES ('156', '2106', 'ปลวกแดง   ', 'Pluak Daeng', '5', '12');
INSERT INTO `amphur` VALUES ('157', '2107', 'เขาชะเมา   ', 'Khao Chamao', '5', '12');
INSERT INTO `amphur` VALUES ('158', '2108', 'นิคมพัฒนา   ', 'Nikhom Phatthana', '5', '12');
INSERT INTO `amphur` VALUES ('159', '2151', 'สาขาตำบลมาบข่า*   ', 'Saka Tambon Mabka', '5', '12');
INSERT INTO `amphur` VALUES ('160', '2201', 'เมืองจันทบุรี   ', 'Mueang Chanthaburi', '5', '13');
INSERT INTO `amphur` VALUES ('161', '2202', 'ขลุง   ', 'Khlung', '5', '13');
INSERT INTO `amphur` VALUES ('162', '2203', 'ท่าใหม่   ', 'Tha Mai', '5', '13');
INSERT INTO `amphur` VALUES ('163', '2204', 'โป่งน้ำร้อน   ', 'Pong Nam Ron', '5', '13');
INSERT INTO `amphur` VALUES ('164', '2205', 'มะขาม   ', 'Makham', '5', '13');
INSERT INTO `amphur` VALUES ('165', '2206', 'แหลมสิงห์   ', 'Laem Sing', '5', '13');
INSERT INTO `amphur` VALUES ('166', '2207', 'สอยดาว   ', 'Soi Dao', '5', '13');
INSERT INTO `amphur` VALUES ('167', '2208', 'แก่งหางแมว   ', 'Kaeng Hang Maeo', '5', '13');
INSERT INTO `amphur` VALUES ('168', '2209', 'นายายอาม   ', 'Na Yai Am', '5', '13');
INSERT INTO `amphur` VALUES ('169', '2210', 'เขาคิชฌกูฏ   ', 'Khoa Khitchakut', '5', '13');
INSERT INTO `amphur` VALUES ('170', '2281', '*กิ่ง อ.กำพุธ  จ.จันทบุรี   ', '*King Amphoe Kampud', '5', '13');
INSERT INTO `amphur` VALUES ('171', '2301', 'เมืองตราด   ', 'Mueang Trat', '5', '14');
INSERT INTO `amphur` VALUES ('172', '2302', 'คลองใหญ่   ', 'Khlong Yai', '5', '14');
INSERT INTO `amphur` VALUES ('173', '2303', 'เขาสมิง   ', 'Khao Saming', '5', '14');
INSERT INTO `amphur` VALUES ('174', '2304', 'บ่อไร่   ', 'Bo Rai', '5', '14');
INSERT INTO `amphur` VALUES ('175', '2305', 'แหลมงอบ   ', 'Laem Ngop', '5', '14');
INSERT INTO `amphur` VALUES ('176', '2306', 'เกาะกูด   ', 'Ko Kut', '5', '14');
INSERT INTO `amphur` VALUES ('177', '2307', 'เกาะช้าง   ', 'Ko Chang', '5', '14');
INSERT INTO `amphur` VALUES ('178', '2401', 'เมืองฉะเชิงเทรา   ', 'Mueang Chachoengsao', '5', '15');
INSERT INTO `amphur` VALUES ('179', '2402', 'บางคล้า   ', 'Bang Khla', '5', '15');
INSERT INTO `amphur` VALUES ('180', '2403', 'บางน้ำเปรี้ยว   ', 'Bang Nam Priao', '5', '15');
INSERT INTO `amphur` VALUES ('181', '2404', 'บางปะกง   ', 'Bang Pakong', '5', '15');
INSERT INTO `amphur` VALUES ('182', '2405', 'บ้านโพธิ์   ', 'Ban Pho', '5', '15');
INSERT INTO `amphur` VALUES ('183', '2406', 'พนมสารคาม   ', 'Phanom Sarakham', '5', '15');
INSERT INTO `amphur` VALUES ('184', '2407', 'ราชสาส์น   ', 'Ratchasan', '5', '15');
INSERT INTO `amphur` VALUES ('185', '2408', 'สนามชัยเขต   ', 'Sanam Chai Khet', '5', '15');
INSERT INTO `amphur` VALUES ('186', '2409', 'แปลงยาว   ', 'Plaeng Yao', '5', '15');
INSERT INTO `amphur` VALUES ('187', '2410', 'ท่าตะเกียบ   ', 'Tha Takiap', '5', '15');
INSERT INTO `amphur` VALUES ('188', '2411', 'คลองเขื่อน   ', 'Khlong Khuean', '5', '15');
INSERT INTO `amphur` VALUES ('189', '2501', 'เมืองปราจีนบุรี   ', 'Mueang Prachin Buri', '5', '16');
INSERT INTO `amphur` VALUES ('190', '2502', 'กบินทร์บุรี   ', 'Kabin Buri', '5', '16');
INSERT INTO `amphur` VALUES ('191', '2503', 'นาดี   ', 'Na Di', '5', '16');
INSERT INTO `amphur` VALUES ('192', '2504', '*สระแก้ว   ', 'Sa Kaeo', '5', '16');
INSERT INTO `amphur` VALUES ('193', '2505', '*วังน้ำเย็น   ', 'Wang Nam Yen', '5', '16');
INSERT INTO `amphur` VALUES ('194', '2506', 'บ้านสร้าง   ', 'Ban Sang', '5', '16');
INSERT INTO `amphur` VALUES ('195', '2507', 'ประจันตคาม   ', 'Prachantakham', '5', '16');
INSERT INTO `amphur` VALUES ('196', '2508', 'ศรีมหาโพธิ   ', 'Si Maha Phot', '5', '16');
INSERT INTO `amphur` VALUES ('197', '2509', 'ศรีมโหสถ   ', 'Si Mahosot', '5', '16');
INSERT INTO `amphur` VALUES ('198', '2510', '*อรัญประเทศ   ', 'Aranyaprathet', '5', '16');
INSERT INTO `amphur` VALUES ('199', '2511', '*ตาพระยา   ', 'Ta Phraya', '5', '16');
INSERT INTO `amphur` VALUES ('200', '2512', '*วัฒนานคร   ', 'Watthana Nakhon', '5', '16');
INSERT INTO `amphur` VALUES ('201', '2513', '*คลองหาด   ', 'Khlong Hat', '5', '16');
INSERT INTO `amphur` VALUES ('202', '2601', 'เมืองนครนายก   ', 'Mueang Nakhon Nayok', '2', '17');
INSERT INTO `amphur` VALUES ('203', '2602', 'ปากพลี   ', 'Pak Phli', '2', '17');
INSERT INTO `amphur` VALUES ('204', '2603', 'บ้านนา   ', 'Ban Na', '2', '17');
INSERT INTO `amphur` VALUES ('205', '2604', 'องครักษ์   ', 'Ongkharak', '2', '17');
INSERT INTO `amphur` VALUES ('206', '2701', 'เมืองสระแก้ว   ', 'Mueang Sa Kaeo', '5', '18');
INSERT INTO `amphur` VALUES ('207', '2702', 'คลองหาด   ', 'Khlong Hat', '5', '18');
INSERT INTO `amphur` VALUES ('208', '2703', 'ตาพระยา   ', 'Ta Phraya', '5', '18');
INSERT INTO `amphur` VALUES ('209', '2704', 'วังน้ำเย็น   ', 'Wang Nam Yen', '5', '18');
INSERT INTO `amphur` VALUES ('210', '2705', 'วัฒนานคร   ', 'Watthana Nakhon', '5', '18');
INSERT INTO `amphur` VALUES ('211', '2706', 'อรัญประเทศ   ', 'Aranyaprathet', '5', '18');
INSERT INTO `amphur` VALUES ('212', '2707', 'เขาฉกรรจ์   ', 'Khao Chakan', '5', '18');
INSERT INTO `amphur` VALUES ('213', '2708', 'โคกสูง   ', 'Khok Sung', '5', '18');
INSERT INTO `amphur` VALUES ('214', '2709', 'วังสมบูรณ์   ', 'Wang Sombun', '5', '18');
INSERT INTO `amphur` VALUES ('215', '3001', 'เมืองนครราชสีมา   ', 'Mueang Nakhon Ratchasima', '3', '19');
INSERT INTO `amphur` VALUES ('216', '3002', 'ครบุรี   ', 'Khon Buri', '3', '19');
INSERT INTO `amphur` VALUES ('217', '3003', 'เสิงสาง   ', 'Soeng Sang', '3', '19');
INSERT INTO `amphur` VALUES ('218', '3004', 'คง   ', 'Khong', '3', '19');
INSERT INTO `amphur` VALUES ('219', '3005', 'บ้านเหลื่อม   ', 'Ban Lueam', '3', '19');
INSERT INTO `amphur` VALUES ('220', '3006', 'จักราช   ', 'Chakkarat', '3', '19');
INSERT INTO `amphur` VALUES ('221', '3007', 'โชคชัย   ', 'Chok Chai', '3', '19');
INSERT INTO `amphur` VALUES ('222', '3008', 'ด่านขุนทด   ', 'Dan Khun Thot', '3', '19');
INSERT INTO `amphur` VALUES ('223', '3009', 'โนนไทย   ', 'Non Thai', '3', '19');
INSERT INTO `amphur` VALUES ('224', '3010', 'โนนสูง   ', 'Non Sung', '3', '19');
INSERT INTO `amphur` VALUES ('225', '3011', 'ขามสะแกแสง   ', 'Kham Sakaesaeng', '3', '19');
INSERT INTO `amphur` VALUES ('226', '3012', 'บัวใหญ่   ', 'Bua Yai', '3', '19');
INSERT INTO `amphur` VALUES ('227', '3013', 'ประทาย   ', 'Prathai', '3', '19');
INSERT INTO `amphur` VALUES ('228', '3014', 'ปักธงชัย   ', 'Pak Thong Chai', '3', '19');
INSERT INTO `amphur` VALUES ('229', '3015', 'พิมาย   ', 'Phimai', '3', '19');
INSERT INTO `amphur` VALUES ('230', '3016', 'ห้วยแถลง   ', 'Huai Thalaeng', '3', '19');
INSERT INTO `amphur` VALUES ('231', '3017', 'ชุมพวง   ', 'Chum Phuang', '3', '19');
INSERT INTO `amphur` VALUES ('232', '3018', 'สูงเนิน   ', 'Sung Noen', '3', '19');
INSERT INTO `amphur` VALUES ('233', '3019', 'ขามทะเลสอ   ', 'Kham Thale So', '3', '19');
INSERT INTO `amphur` VALUES ('234', '3020', 'สีคิ้ว   ', 'Sikhio', '3', '19');
INSERT INTO `amphur` VALUES ('235', '3021', 'ปากช่อง   ', 'Pak Chong', '3', '19');
INSERT INTO `amphur` VALUES ('236', '3022', 'หนองบุญมาก   ', 'Nong Bunnak', '3', '19');
INSERT INTO `amphur` VALUES ('237', '3023', 'แก้งสนามนาง   ', 'Kaeng Sanam Nang', '3', '19');
INSERT INTO `amphur` VALUES ('238', '3024', 'โนนแดง   ', 'Non Daeng', '3', '19');
INSERT INTO `amphur` VALUES ('239', '3025', 'วังน้ำเขียว   ', 'Wang Nam Khiao', '3', '19');
INSERT INTO `amphur` VALUES ('240', '3026', 'เทพารักษ์   ', 'Thepharak', '3', '19');
INSERT INTO `amphur` VALUES ('241', '3027', 'เมืองยาง   ', 'Mueang Yang', '3', '19');
INSERT INTO `amphur` VALUES ('242', '3028', 'พระทองคำ   ', 'Phra Thong Kham', '3', '19');
INSERT INTO `amphur` VALUES ('243', '3029', 'ลำทะเมนชัย   ', 'Lam Thamenchai', '3', '19');
INSERT INTO `amphur` VALUES ('244', '3030', 'บัวลาย   ', 'Bua Lai', '3', '19');
INSERT INTO `amphur` VALUES ('245', '3031', 'สีดา   ', 'Sida', '3', '19');
INSERT INTO `amphur` VALUES ('246', '3032', 'เฉลิมพระเกียรติ   ', 'Chaloem Phra Kiat', '3', '19');
INSERT INTO `amphur` VALUES ('247', '3049', 'ท้องถิ่นเทศบาลตำบลโพธิ์กลาง*   ', 'Pho Krang', '3', '19');
INSERT INTO `amphur` VALUES ('248', '3051', 'สาขาตำบลมะค่า-พลสงคราม*   ', 'Ma Ka-Pon Songkram*', '3', '19');
INSERT INTO `amphur` VALUES ('249', '3081', '*โนนลาว   ', '*Non Lao', '3', '19');
INSERT INTO `amphur` VALUES ('250', '3101', 'เมืองบุรีรัมย์   ', 'Mueang Buri Ram', '3', '20');
INSERT INTO `amphur` VALUES ('251', '3102', 'คูเมือง   ', 'Khu Mueang', '3', '20');
INSERT INTO `amphur` VALUES ('252', '3103', 'กระสัง', 'Krasang', '3', '20');
INSERT INTO `amphur` VALUES ('253', '3104', 'นางรอง   ', 'Nang Rong', '3', '20');
INSERT INTO `amphur` VALUES ('254', '3105', 'หนองกี่   ', 'Nong Ki', '3', '20');
INSERT INTO `amphur` VALUES ('255', '3106', 'ละหานทราย   ', 'Lahan Sai', '3', '20');
INSERT INTO `amphur` VALUES ('256', '3107', 'ประโคนชัย   ', 'Prakhon Chai', '3', '20');
INSERT INTO `amphur` VALUES ('257', '3108', 'บ้านกรวด   ', 'Ban Kruat', '3', '20');
INSERT INTO `amphur` VALUES ('258', '3109', 'พุทไธสง   ', 'Phutthaisong', '3', '20');
INSERT INTO `amphur` VALUES ('259', '3110', 'ลำปลายมาศ   ', 'Lam Plai Mat', '3', '20');
INSERT INTO `amphur` VALUES ('260', '3111', 'สตึก   ', 'Satuek', '3', '20');
INSERT INTO `amphur` VALUES ('261', '3112', 'ปะคำ   ', 'Pakham', '3', '20');
INSERT INTO `amphur` VALUES ('262', '3113', 'นาโพธิ์   ', 'Na Pho', '3', '20');
INSERT INTO `amphur` VALUES ('263', '3114', 'หนองหงส์   ', 'Nong Hong', '3', '20');
INSERT INTO `amphur` VALUES ('264', '3115', 'พลับพลาชัย   ', 'Phlapphla Chai', '3', '20');
INSERT INTO `amphur` VALUES ('265', '3116', 'ห้วยราช   ', 'Huai Rat', '3', '20');
INSERT INTO `amphur` VALUES ('266', '3117', 'โนนสุวรรณ   ', 'Non Suwan', '3', '20');
INSERT INTO `amphur` VALUES ('267', '3118', 'ชำนิ   ', 'Chamni', '3', '20');
INSERT INTO `amphur` VALUES ('268', '3119', 'บ้านใหม่ไชยพจน์   ', 'Ban Mai Chaiyaphot', '3', '20');
INSERT INTO `amphur` VALUES ('269', '3120', 'โนนดินแดง   ', 'Din Daeng', '3', '20');
INSERT INTO `amphur` VALUES ('270', '3121', 'บ้านด่าน   ', 'Ban Dan', '3', '20');
INSERT INTO `amphur` VALUES ('271', '3122', 'แคนดง   ', 'Khaen Dong', '3', '20');
INSERT INTO `amphur` VALUES ('272', '3123', 'เฉลิมพระเกียรติ   ', 'Chaloem Phra Kiat', '3', '20');
INSERT INTO `amphur` VALUES ('273', '3201', 'เมืองสุรินทร์   ', 'Mueang Surin', '3', '21');
INSERT INTO `amphur` VALUES ('274', '3202', 'ชุมพลบุรี   ', 'Chumphon Buri', '3', '21');
INSERT INTO `amphur` VALUES ('275', '3203', 'ท่าตูม   ', 'Tha Tum', '3', '21');
INSERT INTO `amphur` VALUES ('276', '3204', 'จอมพระ   ', 'Chom Phra', '3', '21');
INSERT INTO `amphur` VALUES ('277', '3205', 'ปราสาท   ', 'Prasat', '3', '21');
INSERT INTO `amphur` VALUES ('278', '3206', 'กาบเชิง   ', 'Kap Choeng', '3', '21');
INSERT INTO `amphur` VALUES ('279', '3207', 'รัตนบุรี   ', 'Rattanaburi', '3', '21');
INSERT INTO `amphur` VALUES ('280', '3208', 'สนม   ', 'Sanom', '3', '21');
INSERT INTO `amphur` VALUES ('281', '3209', 'ศีขรภูมิ   ', 'Sikhoraphum', '3', '21');
INSERT INTO `amphur` VALUES ('282', '3210', 'สังขะ   ', 'Sangkha', '3', '21');
INSERT INTO `amphur` VALUES ('283', '3211', 'ลำดวน   ', 'Lamduan', '3', '21');
INSERT INTO `amphur` VALUES ('284', '3212', 'สำโรงทาบ   ', 'Samrong Thap', '3', '21');
INSERT INTO `amphur` VALUES ('285', '3213', 'บัวเชด   ', 'Buachet', '3', '21');
INSERT INTO `amphur` VALUES ('286', '3214', 'พนมดงรัก   ', 'Phanom Dong Rak', '3', '21');
INSERT INTO `amphur` VALUES ('287', '3215', 'ศรีณรงค์   ', 'Si Narong', '3', '21');
INSERT INTO `amphur` VALUES ('288', '3216', 'เขวาสินรินทร์   ', 'Khwao Sinarin', '3', '21');
INSERT INTO `amphur` VALUES ('289', '3217', 'โนนนารายณ์   ', 'Non Narai', '3', '21');
INSERT INTO `amphur` VALUES ('290', '3301', 'เมืองศรีสะเกษ   ', 'Mueang Si Sa Ket', '3', '22');
INSERT INTO `amphur` VALUES ('291', '3302', 'ยางชุมน้อย   ', 'Yang Chum Noi', '3', '22');
INSERT INTO `amphur` VALUES ('292', '3303', 'กันทรารมย์   ', 'Kanthararom', '3', '22');
INSERT INTO `amphur` VALUES ('293', '3304', 'กันทรลักษ์   ', 'Kantharalak', '3', '22');
INSERT INTO `amphur` VALUES ('294', '3305', 'ขุขันธ์   ', 'Khukhan', '3', '22');
INSERT INTO `amphur` VALUES ('295', '3306', 'ไพรบึง   ', 'Phrai Bueng', '3', '22');
INSERT INTO `amphur` VALUES ('296', '3307', 'ปรางค์กู่   ', 'Prang Ku', '3', '22');
INSERT INTO `amphur` VALUES ('297', '3308', 'ขุนหาญ   ', 'Khun Han', '3', '22');
INSERT INTO `amphur` VALUES ('298', '3309', 'ราษีไศล   ', 'Rasi Salai', '3', '22');
INSERT INTO `amphur` VALUES ('299', '3310', 'อุทุมพรพิสัย   ', 'Uthumphon Phisai', '3', '22');
INSERT INTO `amphur` VALUES ('300', '3311', 'บึงบูรพ์   ', 'Bueng Bun', '3', '22');
INSERT INTO `amphur` VALUES ('301', '3312', 'ห้วยทับทัน   ', 'Huai Thap Than', '3', '22');
INSERT INTO `amphur` VALUES ('302', '3313', 'โนนคูณ   ', 'Non Khun', '3', '22');
INSERT INTO `amphur` VALUES ('303', '3314', 'ศรีรัตนะ   ', 'Si Rattana', '3', '22');
INSERT INTO `amphur` VALUES ('304', '3315', 'น้ำเกลี้ยง   ', 'Si Rattana', '3', '22');
INSERT INTO `amphur` VALUES ('305', '3316', 'วังหิน   ', 'Wang Hin', '3', '22');
INSERT INTO `amphur` VALUES ('306', '3317', 'ภูสิงห์   ', 'Phu Sing', '3', '22');
INSERT INTO `amphur` VALUES ('307', '3318', 'เมืองจันทร์   ', 'Mueang Chan', '3', '22');
INSERT INTO `amphur` VALUES ('308', '3319', 'เบญจลักษ์   ', 'Benchalak', '3', '22');
INSERT INTO `amphur` VALUES ('309', '3320', 'พยุห์   ', 'Phayu', '3', '22');
INSERT INTO `amphur` VALUES ('310', '3321', 'โพธิ์ศรีสุวรรณ   ', 'Pho Si Suwan', '3', '22');
INSERT INTO `amphur` VALUES ('311', '3322', 'ศิลาลาด   ', 'Sila Lat', '3', '22');
INSERT INTO `amphur` VALUES ('312', '3401', 'เมืองอุบลราชธานี   ', 'Mueang Ubon Ratchathani', '3', '23');
INSERT INTO `amphur` VALUES ('313', '3402', 'ศรีเมืองใหม่   ', 'Si Mueang Mai', '3', '23');
INSERT INTO `amphur` VALUES ('314', '3403', 'โขงเจียม   ', 'Khong Chiam', '3', '23');
INSERT INTO `amphur` VALUES ('315', '3404', 'เขื่องใน   ', 'Khueang Nai', '3', '23');
INSERT INTO `amphur` VALUES ('316', '3405', 'เขมราฐ   ', 'Khemarat', '3', '23');
INSERT INTO `amphur` VALUES ('317', '3406', '*ชานุมาน   ', '*Shanuman', '3', '23');
INSERT INTO `amphur` VALUES ('318', '3407', 'เดชอุดม   ', 'Det Udom', '3', '23');
INSERT INTO `amphur` VALUES ('319', '3408', 'นาจะหลวย   ', 'Na Chaluai', '3', '23');
INSERT INTO `amphur` VALUES ('320', '3409', 'น้ำยืน   ', 'Nam Yuen', '3', '23');
INSERT INTO `amphur` VALUES ('321', '3410', 'บุณฑริก   ', 'Buntharik', '3', '23');
INSERT INTO `amphur` VALUES ('322', '3411', 'ตระการพืชผล   ', 'Trakan Phuet Phon', '3', '23');
INSERT INTO `amphur` VALUES ('323', '3412', 'กุดข้าวปุ้น   ', 'Kut Khaopun', '3', '23');
INSERT INTO `amphur` VALUES ('324', '3413', '*พนา   ', '*Phana', '3', '23');
INSERT INTO `amphur` VALUES ('325', '3414', 'ม่วงสามสิบ   ', 'Muang Sam Sip', '3', '23');
INSERT INTO `amphur` VALUES ('326', '3415', 'วารินชำราบ   ', 'Warin Chamrap', '3', '23');
INSERT INTO `amphur` VALUES ('327', '3416', '*อำนาจเจริญ   ', '*Amnat Charoen', '3', '23');
INSERT INTO `amphur` VALUES ('328', '3417', '*เสนางคนิคม   ', '*Senangkhanikhom', '3', '23');
INSERT INTO `amphur` VALUES ('329', '3418', '*หัวตะพาน   ', '*Hua Taphan', '3', '23');
INSERT INTO `amphur` VALUES ('330', '3419', 'พิบูลมังสาหาร   ', 'Phibun Mangsahan', '3', '23');
INSERT INTO `amphur` VALUES ('331', '3420', 'ตาลสุม   ', 'Tan Sum', '3', '23');
INSERT INTO `amphur` VALUES ('332', '3421', 'โพธิ์ไทร   ', 'Pho Sai', '3', '23');
INSERT INTO `amphur` VALUES ('333', '3422', 'สำโรง   ', 'Samrong', '3', '23');
INSERT INTO `amphur` VALUES ('334', '3423', '*กิ่งอำเภอลืออำนาจ   ', '*King Amphoe Lue Amnat', '3', '23');
INSERT INTO `amphur` VALUES ('335', '3424', 'ดอนมดแดง   ', 'Don Mot Daeng', '3', '23');
INSERT INTO `amphur` VALUES ('336', '3425', 'สิรินธร   ', 'Sirindhorn', '3', '23');
INSERT INTO `amphur` VALUES ('337', '3426', 'ทุ่งศรีอุดม   ', 'Thung Si Udom', '3', '23');
INSERT INTO `amphur` VALUES ('338', '3427', '*ปทุมราชวงศา   ', '*Pathum Ratchawongsa', '3', '23');
INSERT INTO `amphur` VALUES ('339', '3428', '*กิ่งอำเภอศรีหลักชัย   ', '*King Amphoe Sri Lunk Chai', '3', '23');
INSERT INTO `amphur` VALUES ('340', '3429', 'นาเยีย   ', 'Na Yia', '3', '23');
INSERT INTO `amphur` VALUES ('341', '3430', 'นาตาล   ', 'Na Tan', '3', '23');
INSERT INTO `amphur` VALUES ('342', '3431', 'เหล่าเสือโก้ก   ', 'Lao Suea Kok', '3', '23');
INSERT INTO `amphur` VALUES ('343', '3432', 'สว่างวีระวงศ์   ', 'Sawang Wirawong', '3', '23');
INSERT INTO `amphur` VALUES ('344', '3433', 'น้ำขุ่น   ', 'Nam Khun', '3', '23');
INSERT INTO `amphur` VALUES ('345', '3481', '*อ.สุวรรณวารี  จ.อุบลราชธานี   ', '*Suwan Wari', '3', '23');
INSERT INTO `amphur` VALUES ('346', '3501', 'เมืองยโสธร   ', 'Mueang Yasothon', '3', '24');
INSERT INTO `amphur` VALUES ('347', '3502', 'ทรายมูล   ', 'Sai Mun', '3', '24');
INSERT INTO `amphur` VALUES ('348', '3503', 'กุดชุม   ', 'Kut Chum', '3', '24');
INSERT INTO `amphur` VALUES ('349', '3504', 'คำเขื่อนแก้ว   ', 'Kham Khuean Kaeo', '3', '24');
INSERT INTO `amphur` VALUES ('350', '3505', 'ป่าติ้ว   ', 'Pa Tio', '3', '24');
INSERT INTO `amphur` VALUES ('351', '3506', 'มหาชนะชัย   ', 'Maha Chana Chai', '3', '24');
INSERT INTO `amphur` VALUES ('352', '3507', 'ค้อวัง   ', 'Kho Wang', '3', '24');
INSERT INTO `amphur` VALUES ('353', '3508', 'เลิงนกทา   ', 'Loeng Nok Tha', '3', '24');
INSERT INTO `amphur` VALUES ('354', '3509', 'ไทยเจริญ   ', 'Thai Charoen', '3', '24');
INSERT INTO `amphur` VALUES ('355', '3601', 'เมืองชัยภูมิ   ', 'Mueang Chaiyaphum', '3', '25');
INSERT INTO `amphur` VALUES ('356', '3602', 'บ้านเขว้า   ', 'Ban Khwao', '3', '25');
INSERT INTO `amphur` VALUES ('357', '3603', 'คอนสวรรค์   ', 'Khon Sawan', '3', '25');
INSERT INTO `amphur` VALUES ('358', '3604', 'เกษตรสมบูรณ์   ', 'Kaset Sombun', '3', '25');
INSERT INTO `amphur` VALUES ('359', '3605', 'หนองบัวแดง   ', 'Nong Bua Daeng', '3', '25');
INSERT INTO `amphur` VALUES ('360', '3606', 'จัตุรัส   ', 'Chatturat', '3', '25');
INSERT INTO `amphur` VALUES ('361', '3607', 'บำเหน็จณรงค์   ', 'Bamnet Narong', '3', '25');
INSERT INTO `amphur` VALUES ('362', '3608', 'หนองบัวระเหว   ', 'Nong Bua Rawe', '3', '25');
INSERT INTO `amphur` VALUES ('363', '3609', 'เทพสถิต   ', 'Thep Sathit', '3', '25');
INSERT INTO `amphur` VALUES ('364', '3610', 'ภูเขียว   ', 'Phu Khiao', '3', '25');
INSERT INTO `amphur` VALUES ('365', '3611', 'บ้านแท่น   ', 'Ban Thaen', '3', '25');
INSERT INTO `amphur` VALUES ('366', '3612', 'แก้งคร้อ   ', 'Kaeng Khro', '3', '25');
INSERT INTO `amphur` VALUES ('367', '3613', 'คอนสาร   ', 'Khon San', '3', '25');
INSERT INTO `amphur` VALUES ('368', '3614', 'ภักดีชุมพล   ', 'Phakdi Chumphon', '3', '25');
INSERT INTO `amphur` VALUES ('369', '3615', 'เนินสง่า   ', 'Noen Sa-nga', '3', '25');
INSERT INTO `amphur` VALUES ('370', '3616', 'ซับใหญ่   ', 'Sap Yai', '3', '25');
INSERT INTO `amphur` VALUES ('371', '3651', 'เมืองชัยภูมิ (สาขาตำบลโนนสำราญ)*   ', 'Mueang Chaiyaphum(Non Sumran)*', '3', '25');
INSERT INTO `amphur` VALUES ('372', '3652', 'สาขาตำบลบ้านหว่าเฒ่า*   ', 'Ban Wha Tao*', '3', '25');
INSERT INTO `amphur` VALUES ('373', '3653', 'หนองบัวแดง (สาขาตำบลวังชมภู)*   ', 'Nong Bua Daeng', '3', '25');
INSERT INTO `amphur` VALUES ('374', '3654', 'กิ่งอำเภอซับใหญ่ (สาขาตำบลซับใหญ่)*   ', 'King Amphoe Sap Yai*', '3', '25');
INSERT INTO `amphur` VALUES ('375', '3655', 'สาขาตำบลโคกเพชร*   ', 'Coke Phet*', '3', '25');
INSERT INTO `amphur` VALUES ('376', '3656', 'เทพสถิต (สาขาตำบลนายางกลัก)*   ', 'Thep Sathit*', '3', '25');
INSERT INTO `amphur` VALUES ('377', '3657', 'บ้านแท่น (สาขาตำบลบ้านเต่า)*   ', 'Ban Thaen', '3', '25');
INSERT INTO `amphur` VALUES ('378', '3658', 'แก้งคร้อ (สาขาตำบลท่ามะไฟหวาน)*   ', 'Kaeng Khro*', '3', '25');
INSERT INTO `amphur` VALUES ('379', '3659', 'คอนสาร (สาขาตำบลโนนคูณ)*   ', 'Khon San*', '3', '25');
INSERT INTO `amphur` VALUES ('380', '3701', 'เมืองอำนาจเจริญ   ', 'Mueang Amnat Charoen', '3', '26');
INSERT INTO `amphur` VALUES ('381', '3702', 'ชานุมาน   ', 'Chanuman', '3', '26');
INSERT INTO `amphur` VALUES ('382', '3703', 'ปทุมราชวงศา   ', 'Pathum Ratchawongsa', '3', '26');
INSERT INTO `amphur` VALUES ('383', '3704', 'พนา   ', 'Phana', '3', '26');
INSERT INTO `amphur` VALUES ('384', '3705', 'เสนางคนิคม   ', 'Senangkhanikhom', '3', '26');
INSERT INTO `amphur` VALUES ('385', '3706', 'หัวตะพาน   ', 'Hua Taphan', '3', '26');
INSERT INTO `amphur` VALUES ('386', '3707', 'ลืออำนาจ   ', 'Lue Amnat', '3', '26');
INSERT INTO `amphur` VALUES ('387', '3901', 'เมืองหนองบัวลำภู   ', 'Mueang Nong Bua Lam Phu', '3', '27');
INSERT INTO `amphur` VALUES ('388', '3902', 'นากลาง   ', 'Na Klang', '3', '27');
INSERT INTO `amphur` VALUES ('389', '3903', 'โนนสัง   ', 'Non Sang', '3', '27');
INSERT INTO `amphur` VALUES ('390', '3904', 'ศรีบุญเรือง   ', 'Si Bun Rueang', '3', '27');
INSERT INTO `amphur` VALUES ('391', '3905', 'สุวรรณคูหา   ', 'Suwannakhuha', '3', '27');
INSERT INTO `amphur` VALUES ('392', '3906', 'นาวัง   ', 'Na Wang', '3', '27');
INSERT INTO `amphur` VALUES ('393', '4001', 'เมืองขอนแก่น   ', 'Mueang Khon Kaen', '3', '28');
INSERT INTO `amphur` VALUES ('394', '4002', 'บ้านฝาง   ', 'Ban Fang', '3', '28');
INSERT INTO `amphur` VALUES ('395', '4003', 'พระยืน   ', 'Phra Yuen', '3', '28');
INSERT INTO `amphur` VALUES ('396', '4004', 'หนองเรือ   ', 'Nong Ruea', '3', '28');
INSERT INTO `amphur` VALUES ('397', '4005', 'ชุมแพ   ', 'Chum Phae', '3', '28');
INSERT INTO `amphur` VALUES ('398', '4006', 'สีชมพู   ', 'Si Chomphu', '3', '28');
INSERT INTO `amphur` VALUES ('399', '4007', 'น้ำพอง   ', 'Nam Phong', '3', '28');
INSERT INTO `amphur` VALUES ('400', '4008', 'อุบลรัตน์   ', 'Ubolratana', '3', '28');
INSERT INTO `amphur` VALUES ('401', '4009', 'กระนวน   ', 'Kranuan', '3', '28');
INSERT INTO `amphur` VALUES ('402', '4010', 'บ้านไผ่   ', 'Ban Phai', '3', '28');
INSERT INTO `amphur` VALUES ('403', '4011', 'เปือยน้อย   ', 'Pueai Noi', '3', '28');
INSERT INTO `amphur` VALUES ('404', '4012', 'พล   ', 'Phon', '3', '28');
INSERT INTO `amphur` VALUES ('405', '4013', 'แวงใหญ่   ', 'Waeng Yai', '3', '28');
INSERT INTO `amphur` VALUES ('406', '4014', 'แวงน้อย   ', 'Waeng Noi', '3', '28');
INSERT INTO `amphur` VALUES ('407', '4015', 'หนองสองห้อง   ', 'Nong Song Hong', '3', '28');
INSERT INTO `amphur` VALUES ('408', '4016', 'ภูเวียง   ', 'Phu Wiang', '3', '28');
INSERT INTO `amphur` VALUES ('409', '4017', 'มัญจาคีรี   ', 'Mancha Khiri', '3', '28');
INSERT INTO `amphur` VALUES ('410', '4018', 'ชนบท   ', 'Chonnabot', '3', '28');
INSERT INTO `amphur` VALUES ('411', '4019', 'เขาสวนกวาง   ', 'Khao Suan Kwang', '3', '28');
INSERT INTO `amphur` VALUES ('412', '4020', 'ภูผาม่าน   ', 'Phu Pha Man', '3', '28');
INSERT INTO `amphur` VALUES ('413', '4021', 'ซำสูง   ', 'Sam Sung', '3', '28');
INSERT INTO `amphur` VALUES ('414', '4022', 'โคกโพธิ์ไชย   ', 'Khok Pho Chai', '3', '28');
INSERT INTO `amphur` VALUES ('415', '4023', 'หนองนาคำ   ', 'Nong Na Kham', '3', '28');
INSERT INTO `amphur` VALUES ('416', '4024', 'บ้านแฮด   ', 'Ban Haet', '3', '28');
INSERT INTO `amphur` VALUES ('417', '4025', 'โนนศิลา   ', 'Non Sila', '3', '28');
INSERT INTO `amphur` VALUES ('418', '4029', 'เวียงเก่า   ', 'Wiang Kao', '3', '28');
INSERT INTO `amphur` VALUES ('419', '4068', 'ท้องถิ่นเทศบาลตำบลบ้านเป็ด*   ', 'Ban Pet*', '3', '28');
INSERT INTO `amphur` VALUES ('420', '4098', 'เทศบาลตำบลเมืองพล*   ', 'Tet Saban Tambon Muang Phon*', '3', '28');
INSERT INTO `amphur` VALUES ('421', '4101', 'เมืองอุดรธานี   ', 'Mueang Udon Thani', '3', '29');
INSERT INTO `amphur` VALUES ('422', '4102', 'กุดจับ   ', 'Kut Chap', '3', '29');
INSERT INTO `amphur` VALUES ('423', '4103', 'หนองวัวซอ   ', 'Nong Wua So', '3', '29');
INSERT INTO `amphur` VALUES ('424', '4104', 'กุมภวาปี   ', 'Kumphawapi', '3', '29');
INSERT INTO `amphur` VALUES ('425', '4105', 'โนนสะอาด   ', 'Non Sa-at', '3', '29');
INSERT INTO `amphur` VALUES ('426', '4106', 'หนองหาน   ', 'Nong Han', '3', '29');
INSERT INTO `amphur` VALUES ('427', '4107', 'ทุ่งฝน   ', 'Thung Fon', '3', '29');
INSERT INTO `amphur` VALUES ('428', '4108', 'ไชยวาน   ', 'Chai Wan', '3', '29');
INSERT INTO `amphur` VALUES ('429', '4109', 'ศรีธาตุ   ', 'Si That', '3', '29');
INSERT INTO `amphur` VALUES ('430', '4110', 'วังสามหมอ   ', 'Wang Sam Mo', '3', '29');
INSERT INTO `amphur` VALUES ('431', '4111', 'บ้านดุง   ', 'Ban Dung', '3', '29');
INSERT INTO `amphur` VALUES ('432', '4112', '*หนองบัวลำภู   ', '*Nong Bua Lam Phu', '3', '29');
INSERT INTO `amphur` VALUES ('433', '4113', '*ศรีบุญเรือง   ', '*Si Bun Rueang', '3', '29');
INSERT INTO `amphur` VALUES ('434', '4114', '*นากลาง   ', '*Na Klang', '3', '29');
INSERT INTO `amphur` VALUES ('435', '4115', '*สุวรรณคูหา   ', '*Suwannakhuha', '3', '29');
INSERT INTO `amphur` VALUES ('436', '4116', '*โนนสัง   ', '*Non Sang', '3', '29');
INSERT INTO `amphur` VALUES ('437', '4117', 'บ้านผือ   ', 'Ban Phue', '3', '29');
INSERT INTO `amphur` VALUES ('438', '4118', 'น้ำโสม   ', 'Nam Som', '3', '29');
INSERT INTO `amphur` VALUES ('439', '4119', 'เพ็ญ   ', 'Phen', '3', '29');
INSERT INTO `amphur` VALUES ('440', '4120', 'สร้างคอม   ', 'Sang Khom', '3', '29');
INSERT INTO `amphur` VALUES ('441', '4121', 'หนองแสง   ', 'Nong Saeng', '3', '29');
INSERT INTO `amphur` VALUES ('442', '4122', 'นายูง   ', 'Na Yung', '3', '29');
INSERT INTO `amphur` VALUES ('443', '4123', 'พิบูลย์รักษ์   ', 'Phibun Rak', '3', '29');
INSERT INTO `amphur` VALUES ('444', '4124', 'กู่แก้ว   ', 'Ku Kaeo', '3', '29');
INSERT INTO `amphur` VALUES ('445', '4125', 'ประจักษ์ศิลปาคม   ', 'rachak-sinlapakhom', '3', '29');
INSERT INTO `amphur` VALUES ('446', '4201', 'เมืองเลย   ', 'Mueang Loei', '3', '30');
INSERT INTO `amphur` VALUES ('447', '4202', 'นาด้วง   ', 'Na Duang', '3', '30');
INSERT INTO `amphur` VALUES ('448', '4203', 'เชียงคาน   ', 'Chiang Khan', '3', '30');
INSERT INTO `amphur` VALUES ('449', '4204', 'ปากชม   ', 'Pak Chom', '3', '30');
INSERT INTO `amphur` VALUES ('450', '4205', 'ด่านซ้าย   ', 'Dan Sai', '3', '30');
INSERT INTO `amphur` VALUES ('451', '4206', 'นาแห้ว   ', 'Na Haeo', '3', '30');
INSERT INTO `amphur` VALUES ('452', '4207', 'ภูเรือ   ', 'Phu Ruea', '3', '30');
INSERT INTO `amphur` VALUES ('453', '4208', 'ท่าลี่   ', 'Tha Li', '3', '30');
INSERT INTO `amphur` VALUES ('454', '4209', 'วังสะพุง   ', 'Wang Saphung', '3', '30');
INSERT INTO `amphur` VALUES ('455', '4210', 'ภูกระดึง   ', 'Phu Kradueng', '3', '30');
INSERT INTO `amphur` VALUES ('456', '4211', 'ภูหลวง   ', 'Phu Luang', '3', '30');
INSERT INTO `amphur` VALUES ('457', '4212', 'ผาขาว   ', 'Pha Khao', '3', '30');
INSERT INTO `amphur` VALUES ('458', '4213', 'เอราวัณ   ', 'Erawan', '3', '30');
INSERT INTO `amphur` VALUES ('459', '4214', 'หนองหิน   ', 'Nong Hin', '3', '30');
INSERT INTO `amphur` VALUES ('460', '4301', 'เมืองหนองคาย   ', 'Mueang Nong Khai', '3', '31');
INSERT INTO `amphur` VALUES ('461', '4302', 'ท่าบ่อ   ', 'Tha Bo', '3', '31');
INSERT INTO `amphur` VALUES ('462', '4303', 'เมืองบึงกาฬ   ', 'Mueang Bueng Kan', '3', '97');
INSERT INTO `amphur` VALUES ('463', '4304', 'พรเจริญ   ', 'Phon Charoen', '3', '97');
INSERT INTO `amphur` VALUES ('464', '4305', 'โพนพิสัย   ', 'Phon Phisai', '3', '31');
INSERT INTO `amphur` VALUES ('465', '4306', 'โซ่พิสัย   ', 'So Phisai', '3', '97');
INSERT INTO `amphur` VALUES ('466', '4307', 'ศรีเชียงใหม่   ', 'Si Chiang Mai', '3', '31');
INSERT INTO `amphur` VALUES ('467', '4308', 'สังคม   ', 'Sangkhom', '3', '31');
INSERT INTO `amphur` VALUES ('468', '4309', 'เซกา   ', 'Seka', '3', '97');
INSERT INTO `amphur` VALUES ('469', '4310', 'ปากคาด   ', 'Pak Khat', '3', '97');
INSERT INTO `amphur` VALUES ('470', '4311', 'บึงโขงหลง   ', 'Bueng Khong Long', '3', '97');
INSERT INTO `amphur` VALUES ('471', '4312', 'ศรีวิไล   ', 'Si Wilai', '3', '97');
INSERT INTO `amphur` VALUES ('472', '4313', 'บุ่งคล้า   ', 'Bung Khla', '3', '97');
INSERT INTO `amphur` VALUES ('473', '4314', 'สระใคร   ', 'Sakhrai', '3', '31');
INSERT INTO `amphur` VALUES ('474', '4315', 'เฝ้าไร่   ', 'Fao Rai', '3', '31');
INSERT INTO `amphur` VALUES ('475', '4316', 'รัตนวาปี   ', 'Rattanawapi', '3', '31');
INSERT INTO `amphur` VALUES ('476', '4317', 'โพธิ์ตาก   ', 'Pho Tak', '3', '31');
INSERT INTO `amphur` VALUES ('477', '4401', 'เมืองมหาสารคาม   ', 'Mueang Maha Sarakham', '3', '32');
INSERT INTO `amphur` VALUES ('478', '4402', 'แกดำ   ', 'Kae Dam', '3', '32');
INSERT INTO `amphur` VALUES ('479', '4403', 'โกสุมพิสัย   ', 'Kosum Phisai', '3', '32');
INSERT INTO `amphur` VALUES ('480', '4404', 'กันทรวิชัย   ', 'Kantharawichai', '3', '32');
INSERT INTO `amphur` VALUES ('481', '4405', 'เชียงยืน   ', 'Kantharawichai', '3', '32');
INSERT INTO `amphur` VALUES ('482', '4406', 'บรบือ   ', 'Borabue', '3', '32');
INSERT INTO `amphur` VALUES ('483', '4407', 'นาเชือก   ', 'Na Chueak', '3', '32');
INSERT INTO `amphur` VALUES ('484', '4408', 'พยัคฆภูมิพิสัย   ', 'Phayakkhaphum Phisai', '3', '32');
INSERT INTO `amphur` VALUES ('485', '4409', 'วาปีปทุม   ', 'Wapi Pathum', '3', '32');
INSERT INTO `amphur` VALUES ('486', '4410', 'นาดูน   ', 'Na Dun', '3', '32');
INSERT INTO `amphur` VALUES ('487', '4411', 'ยางสีสุราช   ', 'Yang Sisurat', '3', '32');
INSERT INTO `amphur` VALUES ('488', '4412', 'กุดรัง   ', 'Kut Rang', '3', '32');
INSERT INTO `amphur` VALUES ('489', '4413', 'ชื่นชม   ', 'Chuen Chom', '3', '32');
INSERT INTO `amphur` VALUES ('490', '4481', '*หลุบ   ', '*Lub', '3', '32');
INSERT INTO `amphur` VALUES ('491', '4501', 'เมืองร้อยเอ็ด   ', 'Mueang Roi Et', '3', '33');
INSERT INTO `amphur` VALUES ('492', '4502', 'เกษตรวิสัย   ', 'Kaset Wisai', '3', '33');
INSERT INTO `amphur` VALUES ('493', '4503', 'ปทุมรัตต์   ', 'Pathum Rat', '3', '33');
INSERT INTO `amphur` VALUES ('494', '4504', 'จตุรพักตรพิมาน   ', 'Chaturaphak Phiman', '3', '33');
INSERT INTO `amphur` VALUES ('495', '4505', 'ธวัชบุรี   ', 'Thawat Buri', '3', '33');
INSERT INTO `amphur` VALUES ('496', '4506', 'พนมไพร   ', 'Phanom Phrai', '3', '33');
INSERT INTO `amphur` VALUES ('497', '4507', 'โพนทอง   ', 'Phon Thong', '3', '33');
INSERT INTO `amphur` VALUES ('498', '4508', 'โพธิ์ชัย   ', 'Pho Chai', '3', '33');
INSERT INTO `amphur` VALUES ('499', '4509', 'หนองพอก   ', 'Nong Phok', '3', '33');
INSERT INTO `amphur` VALUES ('500', '4510', 'เสลภูมิ   ', 'Selaphum', '3', '33');
INSERT INTO `amphur` VALUES ('501', '4511', 'สุวรรณภูมิ   ', 'Suwannaphum', '3', '33');
INSERT INTO `amphur` VALUES ('502', '4512', 'เมืองสรวง   ', 'Mueang Suang', '3', '33');
INSERT INTO `amphur` VALUES ('503', '4513', 'โพนทราย   ', 'Phon Sai', '3', '33');
INSERT INTO `amphur` VALUES ('504', '4514', 'อาจสามารถ   ', 'At Samat', '3', '33');
INSERT INTO `amphur` VALUES ('505', '4515', 'เมยวดี   ', 'Moei Wadi', '3', '33');
INSERT INTO `amphur` VALUES ('506', '4516', 'ศรีสมเด็จ   ', 'Si Somdet', '3', '33');
INSERT INTO `amphur` VALUES ('507', '4517', 'จังหาร   ', 'Changhan', '3', '33');
INSERT INTO `amphur` VALUES ('508', '4518', 'เชียงขวัญ   ', 'Chiang Khwan', '3', '33');
INSERT INTO `amphur` VALUES ('509', '4519', 'หนองฮี   ', 'Nong Hi', '3', '33');
INSERT INTO `amphur` VALUES ('510', '4520', 'ทุ่งเขาหลวง   ', 'Thung Khao Luangกิ่', '3', '33');
INSERT INTO `amphur` VALUES ('511', '4601', 'เมืองกาฬสินธุ์   ', 'Mueang Kalasin', '3', '34');
INSERT INTO `amphur` VALUES ('512', '4602', 'นามน   ', 'Na Mon', '3', '34');
INSERT INTO `amphur` VALUES ('513', '4603', 'กมลาไสย   ', 'Kamalasai', '3', '34');
INSERT INTO `amphur` VALUES ('514', '4604', 'ร่องคำ   ', 'Rong Kham', '3', '34');
INSERT INTO `amphur` VALUES ('515', '4605', 'กุฉินารายณ์   ', 'Kuchinarai', '3', '34');
INSERT INTO `amphur` VALUES ('516', '4606', 'เขาวง   ', 'Khao Wong', '3', '34');
INSERT INTO `amphur` VALUES ('517', '4607', 'ยางตลาด   ', 'Yang Talat', '3', '34');
INSERT INTO `amphur` VALUES ('518', '4608', 'ห้วยเม็ก   ', 'Huai Mek', '3', '34');
INSERT INTO `amphur` VALUES ('519', '4609', 'สหัสขันธ์   ', 'Sahatsakhan', '3', '34');
INSERT INTO `amphur` VALUES ('520', '4610', 'คำม่วง   ', 'Kham Muang', '3', '34');
INSERT INTO `amphur` VALUES ('521', '4611', 'ท่าคันโท   ', 'Tha Khantho', '3', '34');
INSERT INTO `amphur` VALUES ('522', '4612', 'หนองกุงศรี   ', 'Nong Kung Si', '3', '34');
INSERT INTO `amphur` VALUES ('523', '4613', 'สมเด็จ   ', 'Somdet', '3', '34');
INSERT INTO `amphur` VALUES ('524', '4614', 'ห้วยผึ้ง   ', 'Huai Phueng', '3', '34');
INSERT INTO `amphur` VALUES ('525', '4615', 'สามชัย   ', 'Sam Chai', '3', '34');
INSERT INTO `amphur` VALUES ('526', '4616', 'นาคู   ', 'Na Khu', '3', '34');
INSERT INTO `amphur` VALUES ('527', '4617', 'ดอนจาน   ', 'Don Chan', '3', '34');
INSERT INTO `amphur` VALUES ('528', '4618', 'ฆ้องชัย   ', 'Khong Chai', '3', '34');
INSERT INTO `amphur` VALUES ('529', '4701', 'เมืองสกลนคร   ', 'Mueang Sakon Nakhon', '3', '35');
INSERT INTO `amphur` VALUES ('530', '4702', 'กุสุมาลย์   ', 'Kusuman', '3', '35');
INSERT INTO `amphur` VALUES ('531', '4703', 'กุดบาก   ', 'Kut Bak', '3', '35');
INSERT INTO `amphur` VALUES ('532', '4704', 'พรรณานิคม   ', 'Phanna Nikhom', '3', '35');
INSERT INTO `amphur` VALUES ('533', '4705', 'พังโคน   ', 'Phang Khon', '3', '35');
INSERT INTO `amphur` VALUES ('534', '4706', 'วาริชภูมิ   ', 'Waritchaphum', '3', '35');
INSERT INTO `amphur` VALUES ('535', '4707', 'นิคมน้ำอูน   ', 'Nikhom Nam Un', '3', '35');
INSERT INTO `amphur` VALUES ('536', '4708', 'วานรนิวาส   ', 'Wanon Niwat', '3', '35');
INSERT INTO `amphur` VALUES ('537', '4709', 'คำตากล้า   ', 'Kham Ta Kla', '3', '35');
INSERT INTO `amphur` VALUES ('538', '4710', 'บ้านม่วง   ', 'Ban Muang', '3', '35');
INSERT INTO `amphur` VALUES ('539', '4711', 'อากาศอำนวย   ', 'Akat Amnuai', '3', '35');
INSERT INTO `amphur` VALUES ('540', '4712', 'สว่างแดนดิน   ', 'Sawang Daen Din', '3', '35');
INSERT INTO `amphur` VALUES ('541', '4713', 'ส่องดาว   ', 'Song Dao', '3', '35');
INSERT INTO `amphur` VALUES ('542', '4714', 'เต่างอย   ', 'Tao Ngoi', '3', '35');
INSERT INTO `amphur` VALUES ('543', '4715', 'โคกศรีสุพรรณ   ', 'Khok Si Suphan', '3', '35');
INSERT INTO `amphur` VALUES ('544', '4716', 'เจริญศิลป์   ', 'Charoen Sin', '3', '35');
INSERT INTO `amphur` VALUES ('545', '4717', 'โพนนาแก้ว   ', 'Phon Na Kaeo', '3', '35');
INSERT INTO `amphur` VALUES ('546', '4718', 'ภูพาน   ', 'Phu Phan', '3', '35');
INSERT INTO `amphur` VALUES ('547', '4751', 'วานรนิวาส (สาขาตำบลกุดเรือคำ)*   ', 'Wanon Niwat', '3', '35');
INSERT INTO `amphur` VALUES ('548', '4781', '*อ.บ้านหัน  จ.สกลนคร   ', '*Banhan', '3', '35');
INSERT INTO `amphur` VALUES ('549', '4801', 'เมืองนครพนม   ', 'Mueang Nakhon Phanom', '3', '36');
INSERT INTO `amphur` VALUES ('550', '4802', 'ปลาปาก   ', 'Pla Pak', '3', '36');
INSERT INTO `amphur` VALUES ('551', '4803', 'ท่าอุเทน   ', 'Tha Uthen', '3', '36');
INSERT INTO `amphur` VALUES ('552', '4804', 'บ้านแพง   ', 'Ban Phaeng', '3', '36');
INSERT INTO `amphur` VALUES ('553', '4805', 'ธาตุพนม   ', 'That Phanom', '3', '36');
INSERT INTO `amphur` VALUES ('554', '4806', 'เรณูนคร   ', 'Renu Nakhon', '3', '36');
INSERT INTO `amphur` VALUES ('555', '4807', 'นาแก   ', 'Na Kae', '3', '36');
INSERT INTO `amphur` VALUES ('556', '4808', 'ศรีสงคราม   ', 'Si Songkhram', '3', '36');
INSERT INTO `amphur` VALUES ('557', '4809', 'นาหว้า   ', 'Na Wa', '3', '36');
INSERT INTO `amphur` VALUES ('558', '4810', 'โพนสวรรค์   ', 'Phon Sawan', '3', '36');
INSERT INTO `amphur` VALUES ('559', '4811', 'นาทม   ', 'Na Thom', '3', '36');
INSERT INTO `amphur` VALUES ('560', '4812', 'วังยาง   ', 'Wang Yang', '3', '36');
INSERT INTO `amphur` VALUES ('561', '4901', 'เมืองมุกดาหาร   ', 'Mueang Mukdahan', '3', '37');
INSERT INTO `amphur` VALUES ('562', '4902', 'นิคมคำสร้อย   ', 'Nikhom Kham Soi', '3', '37');
INSERT INTO `amphur` VALUES ('563', '4903', 'ดอนตาล   ', 'Don Tan', '3', '37');
INSERT INTO `amphur` VALUES ('564', '4904', 'ดงหลวง   ', 'Dong Luang', '3', '37');
INSERT INTO `amphur` VALUES ('565', '4905', 'คำชะอี   ', 'Khamcha-i', '3', '37');
INSERT INTO `amphur` VALUES ('566', '4906', 'หว้านใหญ่   ', 'Wan Yai', '3', '37');
INSERT INTO `amphur` VALUES ('567', '4907', 'หนองสูง   ', 'Nong Sung', '3', '37');
INSERT INTO `amphur` VALUES ('568', '5001', 'เมืองเชียงใหม่   ', 'Mueang Chiang Mai', '1', '38');
INSERT INTO `amphur` VALUES ('569', '5002', 'จอมทอง   ', 'Chom Thong', '1', '38');
INSERT INTO `amphur` VALUES ('570', '5003', 'แม่แจ่ม   ', 'Mae Chaem', '1', '38');
INSERT INTO `amphur` VALUES ('571', '5004', 'เชียงดาว   ', 'Chiang Dao', '1', '38');
INSERT INTO `amphur` VALUES ('572', '5005', 'ดอยสะเก็ด   ', 'Doi Saket', '1', '38');
INSERT INTO `amphur` VALUES ('573', '5006', 'แม่แตง   ', 'Mae Taeng', '1', '38');
INSERT INTO `amphur` VALUES ('574', '5007', 'แม่ริม   ', 'Mae Rim', '1', '38');
INSERT INTO `amphur` VALUES ('575', '5008', 'สะเมิง   ', 'Samoeng', '1', '38');
INSERT INTO `amphur` VALUES ('576', '5009', 'ฝาง   ', 'Fang', '1', '38');
INSERT INTO `amphur` VALUES ('577', '5010', 'แม่อาย   ', 'Mae Ai', '1', '38');
INSERT INTO `amphur` VALUES ('578', '5011', 'พร้าว   ', 'Phrao', '1', '38');
INSERT INTO `amphur` VALUES ('579', '5012', 'สันป่าตอง   ', 'San Pa Tong', '1', '38');
INSERT INTO `amphur` VALUES ('580', '5013', 'สันกำแพง   ', 'San Kamphaeng', '1', '38');
INSERT INTO `amphur` VALUES ('581', '5014', 'สันทราย   ', 'San Sai', '1', '38');
INSERT INTO `amphur` VALUES ('582', '5015', 'หางดง   ', 'Hang Dong', '1', '38');
INSERT INTO `amphur` VALUES ('583', '5016', 'ฮอด   ', 'Hot', '1', '38');
INSERT INTO `amphur` VALUES ('584', '5017', 'ดอยเต่า   ', 'Doi Tao', '1', '38');
INSERT INTO `amphur` VALUES ('585', '5018', 'อมก๋อย   ', 'Omkoi', '1', '38');
INSERT INTO `amphur` VALUES ('586', '5019', 'สารภี   ', 'Saraphi', '1', '38');
INSERT INTO `amphur` VALUES ('587', '5020', 'เวียงแหง   ', 'Wiang Haeng', '1', '38');
INSERT INTO `amphur` VALUES ('588', '5021', 'ไชยปราการ   ', 'Chai Prakan', '1', '38');
INSERT INTO `amphur` VALUES ('589', '5022', 'แม่วาง   ', 'Mae Wang', '1', '38');
INSERT INTO `amphur` VALUES ('590', '5023', 'แม่ออน   ', 'Mae On', '1', '38');
INSERT INTO `amphur` VALUES ('591', '5024', 'ดอยหล่อ   ', 'Doi Lo', '1', '38');
INSERT INTO `amphur` VALUES ('592', '5051', 'เทศบาลนครเชียงใหม่ (สาขาแขวงกาลวิละ)*   ', 'Tet Saban Nakorn Chiangmai(Kan lawi la)*', '1', '38');
INSERT INTO `amphur` VALUES ('593', '5052', 'เทศบาลนครเชียงใหม่ (สาขาแขวงศรีวิชั)*   ', 'Tet Saban Nakorn Chiangmai(Sri Wi)*', '1', '38');
INSERT INTO `amphur` VALUES ('594', '5053', 'เทศบาลนครเชียงใหม่ (สาขาเม็งราย)*   ', 'Tet Saban Nakorn Chiangmai(Meng Rai)*', '1', '38');
INSERT INTO `amphur` VALUES ('595', '5101', 'เมืองลำพูน   ', 'Mueang Lamphun', '1', '39');
INSERT INTO `amphur` VALUES ('596', '5102', 'แม่ทา   ', 'Mae Tha', '1', '39');
INSERT INTO `amphur` VALUES ('597', '5103', 'บ้านโฮ่ง   ', 'Ban Hong', '1', '39');
INSERT INTO `amphur` VALUES ('598', '5104', 'ลี้   ', 'Li', '1', '39');
INSERT INTO `amphur` VALUES ('599', '5105', 'ทุ่งหัวช้าง   ', 'Thung Hua Chang', '1', '39');
INSERT INTO `amphur` VALUES ('600', '5106', 'ป่าซาง   ', 'Pa Sang', '1', '39');
INSERT INTO `amphur` VALUES ('601', '5107', 'บ้านธิ   ', 'Ban Thi', '1', '39');
INSERT INTO `amphur` VALUES ('602', '5108', 'เวียงหนองล่อง   ', 'Wiang Nong Long', '1', '39');
INSERT INTO `amphur` VALUES ('603', '5201', 'เมืองลำปาง   ', 'Mueang Lampang', '1', '40');
INSERT INTO `amphur` VALUES ('604', '5202', 'แม่เมาะ   ', 'Mae Mo', '1', '40');
INSERT INTO `amphur` VALUES ('605', '5203', 'เกาะคา   ', 'Ko Kha', '1', '40');
INSERT INTO `amphur` VALUES ('606', '5204', 'เสริมงาม   ', 'Soem Ngam', '1', '40');
INSERT INTO `amphur` VALUES ('607', '5205', 'งาว   ', 'Ngao', '1', '40');
INSERT INTO `amphur` VALUES ('608', '5206', 'แจ้ห่ม   ', 'Chae Hom', '1', '40');
INSERT INTO `amphur` VALUES ('609', '5207', 'วังเหนือ   ', 'Wang Nuea', '1', '40');
INSERT INTO `amphur` VALUES ('610', '5208', 'เถิน   ', 'Thoen', '1', '40');
INSERT INTO `amphur` VALUES ('611', '5209', 'แม่พริก   ', 'Mae Phrik', '1', '40');
INSERT INTO `amphur` VALUES ('612', '5210', 'แม่ทะ   ', 'Mae Tha', '1', '40');
INSERT INTO `amphur` VALUES ('613', '5211', 'สบปราบ   ', 'Sop Prap', '1', '40');
INSERT INTO `amphur` VALUES ('614', '5212', 'ห้างฉัตร   ', 'Hang Chat', '1', '40');
INSERT INTO `amphur` VALUES ('615', '5213', 'เมืองปาน   ', 'Mueang Pan', '1', '40');
INSERT INTO `amphur` VALUES ('616', '5301', 'เมืองอุตรดิตถ์   ', 'Mueang Uttaradit', '1', '41');
INSERT INTO `amphur` VALUES ('617', '5302', 'ตรอน   ', 'Tron', '1', '41');
INSERT INTO `amphur` VALUES ('618', '5303', 'ท่าปลา   ', 'Tha Pla', '1', '41');
INSERT INTO `amphur` VALUES ('619', '5304', 'น้ำปาด   ', 'Nam Pat', '1', '41');
INSERT INTO `amphur` VALUES ('620', '5305', 'ฟากท่า   ', 'Fak Tha', '1', '41');
INSERT INTO `amphur` VALUES ('621', '5306', 'บ้านโคก   ', 'Ban Khok', '1', '41');
INSERT INTO `amphur` VALUES ('622', '5307', 'พิชัย   ', 'Phichai', '1', '41');
INSERT INTO `amphur` VALUES ('623', '5308', 'ลับแล   ', 'Laplae', '1', '41');
INSERT INTO `amphur` VALUES ('624', '5309', 'ทองแสนขัน   ', 'Thong Saen Khan', '1', '41');
INSERT INTO `amphur` VALUES ('625', '5401', 'เมืองแพร่   ', 'Mueang Phrae', '1', '42');
INSERT INTO `amphur` VALUES ('626', '5402', 'ร้องกวาง   ', 'Rong Kwang', '1', '42');
INSERT INTO `amphur` VALUES ('627', '5403', 'ลอง   ', 'Long', '1', '42');
INSERT INTO `amphur` VALUES ('628', '5404', 'สูงเม่น   ', 'Sung Men', '1', '42');
INSERT INTO `amphur` VALUES ('629', '5405', 'เด่นชัย   ', 'Den Chai', '1', '42');
INSERT INTO `amphur` VALUES ('630', '5406', 'สอง   ', 'Song', '1', '42');
INSERT INTO `amphur` VALUES ('631', '5407', 'วังชิ้น   ', 'Wang Chin', '1', '42');
INSERT INTO `amphur` VALUES ('632', '5408', 'หนองม่วงไข่   ', 'Nong Muang Khai', '1', '42');
INSERT INTO `amphur` VALUES ('633', '5501', 'เมืองน่าน   ', 'Mueang Nan', '1', '43');
INSERT INTO `amphur` VALUES ('634', '5502', 'แม่จริม   ', 'Mae Charim', '1', '43');
INSERT INTO `amphur` VALUES ('635', '5503', 'บ้านหลวง   ', 'Ban Luang', '1', '43');
INSERT INTO `amphur` VALUES ('636', '5504', 'นาน้อย   ', 'Na Noi', '1', '43');
INSERT INTO `amphur` VALUES ('637', '5505', 'ปัว   ', 'Pua', '1', '43');
INSERT INTO `amphur` VALUES ('638', '5506', 'ท่าวังผา   ', 'Tha Wang Pha', '1', '43');
INSERT INTO `amphur` VALUES ('639', '5507', 'เวียงสา   ', 'Wiang Sa', '1', '43');
INSERT INTO `amphur` VALUES ('640', '5508', 'ทุ่งช้าง   ', 'Thung Chang', '1', '43');
INSERT INTO `amphur` VALUES ('641', '5509', 'เชียงกลาง   ', 'Chiang Klang', '1', '43');
INSERT INTO `amphur` VALUES ('642', '5510', 'นาหมื่น   ', 'Na Muen', '1', '43');
INSERT INTO `amphur` VALUES ('643', '5511', 'สันติสุข   ', 'Santi Suk', '1', '43');
INSERT INTO `amphur` VALUES ('644', '5512', 'บ่อเกลือ   ', 'Bo Kluea', '1', '43');
INSERT INTO `amphur` VALUES ('645', '5513', 'สองแคว   ', 'Song Khwae', '1', '43');
INSERT INTO `amphur` VALUES ('646', '5514', 'ภูเพียง   ', 'Phu Phiang', '1', '43');
INSERT INTO `amphur` VALUES ('647', '5515', 'เฉลิมพระเกียรติ   ', 'Chaloem Phra Kiat', '1', '43');
INSERT INTO `amphur` VALUES ('648', '5601', 'เมืองพะเยา   ', 'Mueang Phayao', '1', '44');
INSERT INTO `amphur` VALUES ('649', '5602', 'จุน   ', 'Chun', '1', '44');
INSERT INTO `amphur` VALUES ('650', '5603', 'เชียงคำ   ', 'Chiang Kham', '1', '44');
INSERT INTO `amphur` VALUES ('651', '5604', 'เชียงม่วน   ', 'Chiang Muan', '1', '44');
INSERT INTO `amphur` VALUES ('652', '5605', 'ดอกคำใต้   ', 'Dok Khamtai', '1', '44');
INSERT INTO `amphur` VALUES ('653', '5606', 'ปง   ', 'Pong', '1', '44');
INSERT INTO `amphur` VALUES ('654', '5607', 'แม่ใจ   ', 'Mae Chai', '1', '44');
INSERT INTO `amphur` VALUES ('655', '5608', 'ภูซาง   ', 'Phu Sang', '1', '44');
INSERT INTO `amphur` VALUES ('656', '5609', 'ภูกามยาว   ', 'Phu Kamyao', '1', '44');
INSERT INTO `amphur` VALUES ('657', '5701', 'เมืองเชียงราย   ', 'Mueang Chiang Rai', '1', '45');
INSERT INTO `amphur` VALUES ('658', '5702', 'เวียงชัย   ', 'Wiang Chai', '1', '45');
INSERT INTO `amphur` VALUES ('659', '5703', 'เชียงของ   ', 'Chiang Khong', '1', '45');
INSERT INTO `amphur` VALUES ('660', '5704', 'เทิง   ', 'Thoeng', '1', '45');
INSERT INTO `amphur` VALUES ('661', '5705', 'พาน   ', 'Phan', '1', '45');
INSERT INTO `amphur` VALUES ('662', '5706', 'ป่าแดด   ', 'Pa Daet', '1', '45');
INSERT INTO `amphur` VALUES ('663', '5707', 'แม่จัน   ', 'Mae Chan', '1', '45');
INSERT INTO `amphur` VALUES ('664', '5708', 'เชียงแสน   ', 'Chiang Saen', '1', '45');
INSERT INTO `amphur` VALUES ('665', '5709', 'แม่สาย   ', 'Mae Sai', '1', '45');
INSERT INTO `amphur` VALUES ('666', '5710', 'แม่สรวย   ', 'Mae Suai', '1', '45');
INSERT INTO `amphur` VALUES ('667', '5711', 'เวียงป่าเป้า   ', 'Wiang Pa Pao', '1', '45');
INSERT INTO `amphur` VALUES ('668', '5712', 'พญาเม็งราย   ', 'Phaya Mengrai', '1', '45');
INSERT INTO `amphur` VALUES ('669', '5713', 'เวียงแก่น   ', 'Wiang Kaen', '1', '45');
INSERT INTO `amphur` VALUES ('670', '5714', 'ขุนตาล   ', 'Khun Tan', '1', '45');
INSERT INTO `amphur` VALUES ('671', '5715', 'แม่ฟ้าหลวง   ', 'Mae Fa Luang', '1', '45');
INSERT INTO `amphur` VALUES ('672', '5716', 'แม่ลาว   ', 'Mae Lao', '1', '45');
INSERT INTO `amphur` VALUES ('673', '5717', 'เวียงเชียงรุ้ง   ', 'Wiang Chiang Rung', '1', '45');
INSERT INTO `amphur` VALUES ('674', '5718', 'ดอยหลวง   ', 'Doi Luang', '1', '45');
INSERT INTO `amphur` VALUES ('675', '5801', 'เมืองแม่ฮ่องสอน   ', 'Mueang Mae Hong Son', '1', '46');
INSERT INTO `amphur` VALUES ('676', '5802', 'ขุนยวม   ', 'Khun Yuam', '1', '46');
INSERT INTO `amphur` VALUES ('677', '5803', 'ปาย   ', 'Pai', '1', '46');
INSERT INTO `amphur` VALUES ('678', '5804', 'แม่สะเรียง   ', 'Mae Sariang', '1', '46');
INSERT INTO `amphur` VALUES ('679', '5805', 'แม่ลาน้อย   ', 'Mae La Noi', '1', '46');
INSERT INTO `amphur` VALUES ('680', '5806', 'สบเมย   ', 'Sop Moei', '1', '46');
INSERT INTO `amphur` VALUES ('681', '5807', 'ปางมะผ้า   ', 'Pang Mapha', '1', '46');
INSERT INTO `amphur` VALUES ('682', '5881', '*อ.ม่วยต่อ  จ.แม่ฮ่องสอน   ', 'Muen Tor', '1', '46');
INSERT INTO `amphur` VALUES ('683', '6001', 'เมืองนครสวรรค์   ', 'Mueang Nakhon Sawan', '2', '47');
INSERT INTO `amphur` VALUES ('684', '6002', 'โกรกพระ   ', 'Krok Phra', '2', '47');
INSERT INTO `amphur` VALUES ('685', '6003', 'ชุมแสง   ', 'Chum Saeng', '2', '47');
INSERT INTO `amphur` VALUES ('686', '6004', 'หนองบัว   ', 'Nong Bua', '2', '47');
INSERT INTO `amphur` VALUES ('687', '6005', 'บรรพตพิสัย   ', 'Banphot Phisai', '2', '47');
INSERT INTO `amphur` VALUES ('688', '6006', 'เก้าเลี้ยว   ', 'Kao Liao', '2', '47');
INSERT INTO `amphur` VALUES ('689', '6007', 'ตาคลี   ', 'Takhli', '2', '47');
INSERT INTO `amphur` VALUES ('690', '6008', 'ท่าตะโก   ', 'Takhli', '2', '47');
INSERT INTO `amphur` VALUES ('691', '6009', 'ไพศาลี   ', 'Phaisali', '2', '47');
INSERT INTO `amphur` VALUES ('692', '6010', 'พยุหะคีรี   ', 'Phayuha Khiri', '2', '47');
INSERT INTO `amphur` VALUES ('693', '6011', 'ลาดยาว   ', 'Phayuha Khiri', '2', '47');
INSERT INTO `amphur` VALUES ('694', '6012', 'ตากฟ้า   ', 'Tak Fa', '2', '47');
INSERT INTO `amphur` VALUES ('695', '6013', 'แม่วงก์   ', 'Mae Wong', '2', '47');
INSERT INTO `amphur` VALUES ('696', '6014', 'แม่เปิน   ', 'Mae Poen', '2', '47');
INSERT INTO `amphur` VALUES ('697', '6015', 'ชุมตาบง   ', 'Chum Ta Bong', '2', '47');
INSERT INTO `amphur` VALUES ('698', '6051', 'สาขาตำบลห้วยน้ำหอม*   ', 'Huen Nam Hom', '2', '47');
INSERT INTO `amphur` VALUES ('699', '6052', 'กิ่งอำเภอชุมตาบง (สาขาตำบลชุมตาบง)*   ', 'Chum Ta Bong', '2', '47');
INSERT INTO `amphur` VALUES ('700', '6053', 'แม่วงก์ (สาขาตำบลแม่เล่ย์)*   ', 'Mea Ley', '2', '47');
INSERT INTO `amphur` VALUES ('701', '6101', 'เมืองอุทัยธานี   ', 'Mueang Uthai Thani', '2', '48');
INSERT INTO `amphur` VALUES ('702', '6102', 'ทัพทัน   ', 'Thap Than', '2', '48');
INSERT INTO `amphur` VALUES ('703', '6103', 'สว่างอารมณ์   ', 'Sawang Arom', '2', '48');
INSERT INTO `amphur` VALUES ('704', '6104', 'หนองฉาง   ', 'Nong Chang', '2', '48');
INSERT INTO `amphur` VALUES ('705', '6105', 'หนองขาหย่าง   ', 'Nong Khayang', '2', '48');
INSERT INTO `amphur` VALUES ('706', '6106', 'บ้านไร่   ', 'Ban Rai', '2', '48');
INSERT INTO `amphur` VALUES ('707', '6107', 'ลานสัก   ', 'Lan Sak', '2', '48');
INSERT INTO `amphur` VALUES ('708', '6108', 'ห้วยคต   ', 'Huai Khot', '2', '48');
INSERT INTO `amphur` VALUES ('709', '6201', 'เมืองกำแพงเพชร   ', 'Mueang Kamphaeng Phet', '2', '49');
INSERT INTO `amphur` VALUES ('710', '6202', 'ไทรงาม   ', 'Sai Ngam', '2', '49');
INSERT INTO `amphur` VALUES ('711', '6203', 'คลองลาน   ', 'Khlong Lan', '2', '49');
INSERT INTO `amphur` VALUES ('712', '6204', 'ขาณุวรลักษบุรี   ', 'Khanu Woralaksaburi', '2', '49');
INSERT INTO `amphur` VALUES ('713', '6205', 'คลองขลุง   ', 'Khlong Khlung', '2', '49');
INSERT INTO `amphur` VALUES ('714', '6206', 'พรานกระต่าย   ', 'Phran Kratai', '2', '49');
INSERT INTO `amphur` VALUES ('715', '6207', 'ลานกระบือ   ', 'Lan Krabue', '2', '49');
INSERT INTO `amphur` VALUES ('716', '6208', 'ทรายทองวัฒนา   ', 'Sai Thong Watthana', '2', '49');
INSERT INTO `amphur` VALUES ('717', '6209', 'ปางศิลาทอง   ', 'Pang Sila Thong', '2', '49');
INSERT INTO `amphur` VALUES ('718', '6210', 'บึงสามัคคี   ', 'Bueng Samakkhi', '2', '49');
INSERT INTO `amphur` VALUES ('719', '6211', 'โกสัมพีนคร   ', 'Kosamphi Nakhon', '2', '49');
INSERT INTO `amphur` VALUES ('720', '6301', 'เมืองตาก   ', 'Mueang Tak', '4', '50');
INSERT INTO `amphur` VALUES ('721', '6302', 'บ้านตาก   ', 'Ban Tak', '4', '50');
INSERT INTO `amphur` VALUES ('722', '6303', 'สามเงา   ', 'Sam Ngao', '4', '50');
INSERT INTO `amphur` VALUES ('723', '6304', 'แม่ระมาด   ', 'Mae Ramat', '4', '50');
INSERT INTO `amphur` VALUES ('724', '6305', 'ท่าสองยาง   ', 'Tha Song Yang', '4', '50');
INSERT INTO `amphur` VALUES ('725', '6306', 'แม่สอด   ', 'Mae Sot', '4', '50');
INSERT INTO `amphur` VALUES ('726', '6307', 'พบพระ   ', 'Phop Phra', '4', '50');
INSERT INTO `amphur` VALUES ('727', '6308', 'อุ้มผาง   ', 'Umphang', '4', '50');
INSERT INTO `amphur` VALUES ('728', '6309', 'วังเจ้า   ', 'Wang Chao', '4', '50');
INSERT INTO `amphur` VALUES ('729', '6381', '*กิ่ง อ.ท่าปุย  จ.ตาก   ', '*King Ta Pui', '4', '50');
INSERT INTO `amphur` VALUES ('730', '6401', 'เมืองสุโขทัย   ', 'Mueang Sukhothai', '2', '51');
INSERT INTO `amphur` VALUES ('731', '6402', 'บ้านด่านลานหอย   ', 'Ban Dan Lan Hoi', '2', '51');
INSERT INTO `amphur` VALUES ('732', '6403', 'คีรีมาศ   ', 'Khiri Mat', '2', '51');
INSERT INTO `amphur` VALUES ('733', '6404', 'กงไกรลาศ   ', 'Kong Krailat', '2', '51');
INSERT INTO `amphur` VALUES ('734', '6405', 'ศรีสัชนาลัย   ', 'Si Satchanalai', '2', '51');
INSERT INTO `amphur` VALUES ('735', '6406', 'ศรีสำโรง   ', 'Si Samrong', '2', '51');
INSERT INTO `amphur` VALUES ('736', '6407', 'สวรรคโลก   ', 'Sawankhalok', '2', '51');
INSERT INTO `amphur` VALUES ('737', '6408', 'ศรีนคร   ', 'Si Nakhon', '2', '51');
INSERT INTO `amphur` VALUES ('738', '6409', 'ทุ่งเสลี่ยม   ', 'Thung Saliam', '2', '51');
INSERT INTO `amphur` VALUES ('739', '6501', 'เมืองพิษณุโลก   ', 'Mueang Phitsanulok', '2', '52');
INSERT INTO `amphur` VALUES ('740', '6502', 'นครไทย   ', 'Nakhon Thai', '2', '52');
INSERT INTO `amphur` VALUES ('741', '6503', 'ชาติตระการ   ', 'Chat Trakan', '2', '52');
INSERT INTO `amphur` VALUES ('742', '6504', 'บางระกำ   ', 'Bang Rakam', '2', '52');
INSERT INTO `amphur` VALUES ('743', '6505', 'บางกระทุ่ม   ', 'Bang Krathum', '2', '52');
INSERT INTO `amphur` VALUES ('744', '6506', 'พรหมพิราม   ', 'Phrom Phiram', '2', '52');
INSERT INTO `amphur` VALUES ('745', '6507', 'วัดโบสถ์   ', 'Wat Bot', '2', '52');
INSERT INTO `amphur` VALUES ('746', '6508', 'วังทอง   ', 'Wang Thong', '2', '52');
INSERT INTO `amphur` VALUES ('747', '6509', 'เนินมะปราง   ', 'Noen Maprang', '2', '52');
INSERT INTO `amphur` VALUES ('748', '6601', 'เมืองพิจิตร   ', 'Mueang Phichit', '2', '53');
INSERT INTO `amphur` VALUES ('749', '6602', 'วังทรายพูน   ', 'Wang Sai Phun', '2', '53');
INSERT INTO `amphur` VALUES ('750', '6603', 'โพธิ์ประทับช้าง   ', 'Pho Prathap Chang', '2', '53');
INSERT INTO `amphur` VALUES ('751', '6604', 'ตะพานหิน   ', 'Taphan Hin', '2', '53');
INSERT INTO `amphur` VALUES ('752', '6605', 'บางมูลนาก   ', 'Bang Mun Nak', '2', '53');
INSERT INTO `amphur` VALUES ('753', '6606', 'โพทะเล   ', 'Pho Thale', '2', '53');
INSERT INTO `amphur` VALUES ('754', '6607', 'สามง่าม   ', 'Sam Ngam', '2', '53');
INSERT INTO `amphur` VALUES ('755', '6608', 'ทับคล้อ   ', 'Tap Khlo', '2', '53');
INSERT INTO `amphur` VALUES ('756', '6609', 'สากเหล็ก   ', 'Sak Lek', '2', '53');
INSERT INTO `amphur` VALUES ('757', '6610', 'บึงนาราง   ', 'Bueng Na Rang', '2', '53');
INSERT INTO `amphur` VALUES ('758', '6611', 'ดงเจริญ   ', 'Dong Charoen', '2', '53');
INSERT INTO `amphur` VALUES ('759', '6612', 'วชิรบารมี   ', 'Wachirabarami', '2', '53');
INSERT INTO `amphur` VALUES ('760', '6701', 'เมืองเพชรบูรณ์   ', 'Mueang Phetchabun', '2', '54');
INSERT INTO `amphur` VALUES ('761', '6702', 'ชนแดน   ', 'Chon Daen', '2', '54');
INSERT INTO `amphur` VALUES ('762', '6703', 'หล่มสัก   ', 'Lom Sak', '2', '54');
INSERT INTO `amphur` VALUES ('763', '6704', 'หล่มเก่า   ', 'Lom Kao', '2', '54');
INSERT INTO `amphur` VALUES ('764', '6705', 'วิเชียรบุรี   ', 'Wichian Buri', '2', '54');
INSERT INTO `amphur` VALUES ('765', '6706', 'ศรีเทพ   ', 'Si Thep', '2', '54');
INSERT INTO `amphur` VALUES ('766', '6707', 'หนองไผ่   ', 'Nong Phai', '2', '54');
INSERT INTO `amphur` VALUES ('767', '6708', 'บึงสามพัน   ', 'Bueng Sam Phan', '2', '54');
INSERT INTO `amphur` VALUES ('768', '6709', 'น้ำหนาว   ', 'Nam Nao', '2', '54');
INSERT INTO `amphur` VALUES ('769', '6710', 'วังโป่ง   ', 'Wang Pong', '2', '54');
INSERT INTO `amphur` VALUES ('770', '6711', 'เขาค้อ   ', 'Khao Kho', '2', '54');
INSERT INTO `amphur` VALUES ('771', '7001', 'เมืองราชบุรี   ', 'Mueang Ratchaburi', '4', '55');
INSERT INTO `amphur` VALUES ('772', '7002', 'จอมบึง   ', 'Chom Bueng', '4', '55');
INSERT INTO `amphur` VALUES ('773', '7003', 'สวนผึ้ง   ', 'Suan Phueng', '4', '55');
INSERT INTO `amphur` VALUES ('774', '7004', 'ดำเนินสะดวก   ', 'Damnoen Saduak', '4', '55');
INSERT INTO `amphur` VALUES ('775', '7005', 'บ้านโป่ง   ', 'Ban Pong', '4', '55');
INSERT INTO `amphur` VALUES ('776', '7006', 'บางแพ   ', 'Bang Phae', '4', '55');
INSERT INTO `amphur` VALUES ('777', '7007', 'โพธาราม   ', 'Photharam', '4', '55');
INSERT INTO `amphur` VALUES ('778', '7008', 'ปากท่อ   ', 'Pak Tho', '4', '55');
INSERT INTO `amphur` VALUES ('779', '7009', 'วัดเพลง   ', 'Wat Phleng', '4', '55');
INSERT INTO `amphur` VALUES ('780', '7010', 'บ้านคา   ', 'Ban Kha', '4', '55');
INSERT INTO `amphur` VALUES ('781', '7074', 'ท้องถิ่นเทศบาลตำบลบ้านฆ้อง   ', 'Tet Saban Ban Kong', '4', '55');
INSERT INTO `amphur` VALUES ('782', '7101', 'เมืองกาญจนบุรี   ', 'Mueang Kanchanaburi', '4', '56');
INSERT INTO `amphur` VALUES ('783', '7102', 'ไทรโยค   ', 'Sai Yok', '4', '56');
INSERT INTO `amphur` VALUES ('784', '7103', 'บ่อพลอย   ', 'Bo Phloi', '4', '56');
INSERT INTO `amphur` VALUES ('785', '7104', 'ศรีสวัสดิ์   ', 'Si Sawat', '4', '56');
INSERT INTO `amphur` VALUES ('786', '7105', 'ท่ามะกา   ', 'Tha Maka', '4', '56');
INSERT INTO `amphur` VALUES ('787', '7106', 'ท่าม่วง   ', 'Tha Muang', '4', '56');
INSERT INTO `amphur` VALUES ('788', '7107', 'ทองผาภูมิ   ', 'Pha Phum', '4', '56');
INSERT INTO `amphur` VALUES ('789', '7108', 'สังขละบุรี   ', 'Sangkhla Buri', '4', '56');
INSERT INTO `amphur` VALUES ('790', '7109', 'พนมทวน   ', 'Phanom Thuan', '4', '56');
INSERT INTO `amphur` VALUES ('791', '7110', 'เลาขวัญ   ', 'Lao Khwan', '4', '56');
INSERT INTO `amphur` VALUES ('792', '7111', 'ด่านมะขามเตี้ย   ', 'Dan Makham Tia', '4', '56');
INSERT INTO `amphur` VALUES ('793', '7112', 'หนองปรือ   ', 'Nong Prue', '4', '56');
INSERT INTO `amphur` VALUES ('794', '7113', 'ห้วยกระเจา   ', 'Huai Krachao', '4', '56');
INSERT INTO `amphur` VALUES ('795', '7151', 'สาขาตำบลท่ากระดาน*   ', 'Tha Kra Dan', '4', '56');
INSERT INTO `amphur` VALUES ('796', '7181', '*บ้านทวน  จ.กาญจนบุรี   ', '*Ban Tuan', '4', '56');
INSERT INTO `amphur` VALUES ('797', '7201', 'เมืองสุพรรณบุรี   ', 'Mueang Suphan Buri', '2', '57');
INSERT INTO `amphur` VALUES ('798', '7202', 'เดิมบางนางบวช   ', 'Doem Bang Nang Buat', '2', '57');
INSERT INTO `amphur` VALUES ('799', '7203', 'ด่านช้าง   ', 'Dan Chang', '2', '57');
INSERT INTO `amphur` VALUES ('800', '7204', 'บางปลาม้า   ', 'Bang Pla Ma', '2', '57');
INSERT INTO `amphur` VALUES ('801', '7205', 'ศรีประจันต์   ', 'Si Prachan', '2', '57');
INSERT INTO `amphur` VALUES ('802', '7206', 'ดอนเจดีย์   ', 'Don Chedi', '2', '57');
INSERT INTO `amphur` VALUES ('803', '7207', 'สองพี่น้อง   ', 'Song Phi Nong', '2', '57');
INSERT INTO `amphur` VALUES ('804', '7208', 'สามชุก   ', 'Sam Chuk', '2', '57');
INSERT INTO `amphur` VALUES ('805', '7209', 'อู่ทอง   ', 'U Thong', '2', '57');
INSERT INTO `amphur` VALUES ('806', '7210', 'หนองหญ้าไซ   ', 'Nong Ya Sai', '2', '57');
INSERT INTO `amphur` VALUES ('807', '7301', 'เมืองนครปฐม   ', 'Mueang Nakhon Pathom', '2', '58');
INSERT INTO `amphur` VALUES ('808', '7302', 'กำแพงแสน   ', 'Kamphaeng Saen', '2', '58');
INSERT INTO `amphur` VALUES ('809', '7303', 'นครชัยศรี   ', 'Nakhon Chai Si', '2', '58');
INSERT INTO `amphur` VALUES ('810', '7304', 'ดอนตูม   ', 'Don Tum', '2', '58');
INSERT INTO `amphur` VALUES ('811', '7305', 'บางเลน   ', 'Bang Len', '2', '58');
INSERT INTO `amphur` VALUES ('812', '7306', 'สามพราน   ', 'Sam Phran', '2', '58');
INSERT INTO `amphur` VALUES ('813', '7307', 'พุทธมณฑล   ', 'Phutthamonthon', '2', '58');
INSERT INTO `amphur` VALUES ('814', '7401', 'เมืองสมุทรสาคร   ', 'Mueang Samut Sakhon', '2', '59');
INSERT INTO `amphur` VALUES ('815', '7402', 'กระทุ่มแบน   ', 'Krathum Baen', '2', '59');
INSERT INTO `amphur` VALUES ('816', '7403', 'บ้านแพ้ว   ', 'Ban Phaeo', '2', '59');
INSERT INTO `amphur` VALUES ('817', '7501', 'เมืองสมุทรสงคราม   ', 'Mueang Samut Songkhram', '2', '60');
INSERT INTO `amphur` VALUES ('818', '7502', 'บางคนที   ', 'Bang Khonthi', '2', '60');
INSERT INTO `amphur` VALUES ('819', '7503', 'อัมพวา   ', 'Amphawa', '2', '60');
INSERT INTO `amphur` VALUES ('820', '7601', 'เมืองเพชรบุรี   ', 'Mueang Phetchaburi', '4', '61');
INSERT INTO `amphur` VALUES ('821', '7602', 'เขาย้อย   ', 'Khao Yoi', '4', '61');
INSERT INTO `amphur` VALUES ('822', '7603', 'หนองหญ้าปล้อง   ', 'Nong Ya Plong', '4', '61');
INSERT INTO `amphur` VALUES ('823', '7604', 'ชะอำ   ', 'Cha-am', '4', '61');
INSERT INTO `amphur` VALUES ('824', '7605', 'ท่ายาง   ', 'Tha Yang', '4', '61');
INSERT INTO `amphur` VALUES ('825', '7606', 'บ้านลาด   ', 'Ban Lat', '4', '61');
INSERT INTO `amphur` VALUES ('826', '7607', 'บ้านแหลม   ', 'Ban Laem', '4', '61');
INSERT INTO `amphur` VALUES ('827', '7608', 'แก่งกระจาน   ', 'Kaeng Krachan', '4', '61');
INSERT INTO `amphur` VALUES ('828', '7701', 'เมืองประจวบคีรีขันธ์   ', 'Mueang Prachuap Khiri Khan', '4', '62');
INSERT INTO `amphur` VALUES ('829', '7702', 'กุยบุรี   ', 'Kui Buri', '4', '62');
INSERT INTO `amphur` VALUES ('830', '7703', 'ทับสะแก   ', 'Thap Sakae', '4', '62');
INSERT INTO `amphur` VALUES ('831', '7704', 'บางสะพาน   ', 'Bang Saphan', '4', '62');
INSERT INTO `amphur` VALUES ('832', '7705', 'บางสะพานน้อย   ', 'Bang Saphan Noi', '4', '62');
INSERT INTO `amphur` VALUES ('833', '7706', 'ปราณบุรี   ', 'Pran Buri', '4', '62');
INSERT INTO `amphur` VALUES ('834', '7707', 'หัวหิน   ', 'Hua Hin', '4', '62');
INSERT INTO `amphur` VALUES ('835', '7708', 'สามร้อยยอด   ', 'Sam Roi Yot', '4', '62');
INSERT INTO `amphur` VALUES ('836', '8001', 'เมืองนครศรีธรรมราช   ', 'Mueang Nakhon Si Thammarat', '6', '63');
INSERT INTO `amphur` VALUES ('837', '8002', 'พรหมคีรี   ', 'Phrom Khiri', '6', '63');
INSERT INTO `amphur` VALUES ('838', '8003', 'ลานสกา   ', 'Lan Saka', '6', '63');
INSERT INTO `amphur` VALUES ('839', '8004', 'ฉวาง   ', 'Chawang', '6', '63');
INSERT INTO `amphur` VALUES ('840', '8005', 'พิปูน   ', 'Phipun', '6', '63');
INSERT INTO `amphur` VALUES ('841', '8006', 'เชียรใหญ่   ', 'Chian Yai', '6', '63');
INSERT INTO `amphur` VALUES ('842', '8007', 'ชะอวด   ', 'Cha-uat', '6', '63');
INSERT INTO `amphur` VALUES ('843', '8008', 'ท่าศาลา   ', 'Tha Sala', '6', '63');
INSERT INTO `amphur` VALUES ('844', '8009', 'ทุ่งสง   ', 'Thung Song', '6', '63');
INSERT INTO `amphur` VALUES ('845', '8010', 'นาบอน   ', 'Na Bon', '6', '63');
INSERT INTO `amphur` VALUES ('846', '8011', 'ทุ่งใหญ่   ', 'Thung Yai', '6', '63');
INSERT INTO `amphur` VALUES ('847', '8012', 'ปากพนัง   ', 'Pak Phanang', '6', '63');
INSERT INTO `amphur` VALUES ('848', '8013', 'ร่อนพิบูลย์   ', 'Ron Phibun', '6', '63');
INSERT INTO `amphur` VALUES ('849', '8014', 'สิชล   ', 'Sichon', '6', '63');
INSERT INTO `amphur` VALUES ('850', '8015', 'ขนอม   ', 'Khanom', '6', '63');
INSERT INTO `amphur` VALUES ('851', '8016', 'หัวไทร   ', 'Hua Sai', '6', '63');
INSERT INTO `amphur` VALUES ('852', '8017', 'บางขัน   ', 'Bang Khan', '6', '63');
INSERT INTO `amphur` VALUES ('853', '8018', 'ถ้ำพรรณรา   ', 'Tham Phannara', '6', '63');
INSERT INTO `amphur` VALUES ('854', '8019', 'จุฬาภรณ์   ', 'Chulabhorn', '6', '63');
INSERT INTO `amphur` VALUES ('855', '8020', 'พระพรหม   ', 'Phra Phrom', '6', '63');
INSERT INTO `amphur` VALUES ('856', '8021', 'นบพิตำ   ', 'Nopphitam', '6', '63');
INSERT INTO `amphur` VALUES ('857', '8022', 'ช้างกลาง   ', 'Chang Klang', '6', '63');
INSERT INTO `amphur` VALUES ('858', '8023', 'เฉลิมพระเกียรติ   ', 'Chaloem Phra Kiat', '6', '63');
INSERT INTO `amphur` VALUES ('859', '8051', 'เชียรใหญ่ (สาขาตำบลเสือหึง)*   ', 'Chian Yai*', '6', '63');
INSERT INTO `amphur` VALUES ('860', '8052', 'สาขาตำบลสวนหลวง**   ', 'Suan Luang', '6', '63');
INSERT INTO `amphur` VALUES ('861', '8053', 'ร่อนพิบูลย์ (สาขาตำบลหินตก)*   ', 'Ron Phibun', '6', '63');
INSERT INTO `amphur` VALUES ('862', '8054', 'หัวไทร (สาขาตำบลควนชะลิก)*   ', 'Hua Sai', '6', '63');
INSERT INTO `amphur` VALUES ('863', '8055', 'ทุ่งสง (สาขาตำบลกะปาง)*   ', 'Thung Song', '6', '63');
INSERT INTO `amphur` VALUES ('864', '8101', 'เมืองกระบี่   ', 'Mueang Krabi', '6', '64');
INSERT INTO `amphur` VALUES ('865', '8102', 'เขาพนม   ', 'Khao Phanom', '6', '64');
INSERT INTO `amphur` VALUES ('866', '8103', 'เกาะลันตา   ', 'Ko Lanta', '6', '64');
INSERT INTO `amphur` VALUES ('867', '8104', 'คลองท่อม   ', 'Khlong Thom', '6', '64');
INSERT INTO `amphur` VALUES ('868', '8105', 'อ่าวลึก   ', 'Ao Luek', '6', '64');
INSERT INTO `amphur` VALUES ('869', '8106', 'ปลายพระยา   ', 'Plai Phraya', '6', '64');
INSERT INTO `amphur` VALUES ('870', '8107', 'ลำทับ   ', 'Lam Thap', '6', '64');
INSERT INTO `amphur` VALUES ('871', '8108', 'เหนือคลอง   ', 'Nuea Khlong', '6', '64');
INSERT INTO `amphur` VALUES ('872', '8201', 'เมืองพังงา   ', 'Mueang Phang-nga', '6', '65');
INSERT INTO `amphur` VALUES ('873', '8202', 'เกาะยาว   ', 'Ko Yao', '6', '65');
INSERT INTO `amphur` VALUES ('874', '8203', 'กะปง   ', 'Kapong', '6', '65');
INSERT INTO `amphur` VALUES ('875', '8204', 'ตะกั่วทุ่ง   ', 'Takua Thung', '6', '65');
INSERT INTO `amphur` VALUES ('876', '8205', 'ตะกั่วป่า   ', 'Takua Pa', '6', '65');
INSERT INTO `amphur` VALUES ('877', '8206', 'คุระบุรี   ', 'Khura Buri', '6', '65');
INSERT INTO `amphur` VALUES ('878', '8207', 'ทับปุด   ', 'Thap Put', '6', '65');
INSERT INTO `amphur` VALUES ('879', '8208', 'ท้ายเหมือง   ', 'Thai Mueang', '6', '65');
INSERT INTO `amphur` VALUES ('880', '8301', 'เมืองภูเก็ต   ', 'Mueang Phuket', '6', '66');
INSERT INTO `amphur` VALUES ('881', '8302', 'กะทู้   ', 'Kathu', '6', '66');
INSERT INTO `amphur` VALUES ('882', '8303', 'ถลาง   ', 'Thalang', '6', '66');
INSERT INTO `amphur` VALUES ('883', '8381', '*ทุ่งคา   ', '*Tung Ka', '6', '66');
INSERT INTO `amphur` VALUES ('884', '8401', 'เมืองสุราษฎร์ธานี   ', 'Mueang Surat Thani', '6', '67');
INSERT INTO `amphur` VALUES ('885', '8402', 'กาญจนดิษฐ์   ', 'Kanchanadit', '6', '67');
INSERT INTO `amphur` VALUES ('886', '8403', 'ดอนสัก   ', 'Don Sak', '6', '67');
INSERT INTO `amphur` VALUES ('887', '8404', 'เกาะสมุย   ', 'Ko Samui', '6', '67');
INSERT INTO `amphur` VALUES ('888', '8405', 'เกาะพะงัน   ', 'Ko Pha-ngan', '6', '67');
INSERT INTO `amphur` VALUES ('889', '8406', 'ไชยา   ', 'Chaiya', '6', '67');
INSERT INTO `amphur` VALUES ('890', '8407', 'ท่าชนะ   ', 'Tha Chana', '6', '67');
INSERT INTO `amphur` VALUES ('891', '8408', 'คีรีรัฐนิคม   ', 'Khiri Rat Nikhom', '6', '67');
INSERT INTO `amphur` VALUES ('892', '8409', 'บ้านตาขุน   ', 'Ban Ta Khun', '6', '67');
INSERT INTO `amphur` VALUES ('893', '8410', 'พนม   ', 'Phanom', '6', '67');
INSERT INTO `amphur` VALUES ('894', '8411', 'ท่าฉาง   ', 'Tha Chang', '6', '67');
INSERT INTO `amphur` VALUES ('895', '8412', 'บ้านนาสาร   ', 'Ban Na San', '6', '67');
INSERT INTO `amphur` VALUES ('896', '8413', 'บ้านนาเดิม   ', 'Ban Na Doem', '6', '67');
INSERT INTO `amphur` VALUES ('897', '8414', 'เคียนซา   ', 'Khian Sa', '6', '67');
INSERT INTO `amphur` VALUES ('898', '8415', 'เวียงสระ   ', 'Wiang Sa', '6', '67');
INSERT INTO `amphur` VALUES ('899', '8416', 'พระแสง   ', 'Phrasaeng', '6', '67');
INSERT INTO `amphur` VALUES ('900', '8417', 'พุนพิน   ', 'Phunphin', '6', '67');
INSERT INTO `amphur` VALUES ('901', '8418', 'ชัยบุรี   ', 'Chai Buri', '6', '67');
INSERT INTO `amphur` VALUES ('902', '8419', 'วิภาวดี   ', 'Vibhavadi', '6', '67');
INSERT INTO `amphur` VALUES ('903', '8451', 'เกาะพงัน (สาขาตำบลเกาะเต่า)*   ', 'Ko Pha-ngan', '6', '67');
INSERT INTO `amphur` VALUES ('904', '8481', '*อ.บ้านดอน  จ.สุราษฎร์ธานี   ', '*Ban Don', '6', '67');
INSERT INTO `amphur` VALUES ('905', '8501', 'เมืองระนอง   ', 'Mueang Ranong', '6', '68');
INSERT INTO `amphur` VALUES ('906', '8502', 'ละอุ่น   ', 'La-un', '6', '68');
INSERT INTO `amphur` VALUES ('907', '8503', 'กะเปอร์   ', 'Kapoe', '6', '68');
INSERT INTO `amphur` VALUES ('908', '8504', 'กระบุรี   ', 'Kra Buri', '6', '68');
INSERT INTO `amphur` VALUES ('909', '8505', 'สุขสำราญ   ', 'Suk Samran', '6', '68');
INSERT INTO `amphur` VALUES ('910', '8601', 'เมืองชุมพร   ', 'Mueang Chumphon', '6', '69');
INSERT INTO `amphur` VALUES ('911', '8602', 'ท่าแซะ   ', 'Tha Sae', '6', '69');
INSERT INTO `amphur` VALUES ('912', '8603', 'ปะทิว   ', 'Pathio', '6', '69');
INSERT INTO `amphur` VALUES ('913', '8604', 'หลังสวน   ', 'Lang Suan', '6', '69');
INSERT INTO `amphur` VALUES ('914', '8605', 'ละแม   ', 'Lamae', '6', '69');
INSERT INTO `amphur` VALUES ('915', '8606', 'พะโต๊ะ   ', 'Phato', '6', '69');
INSERT INTO `amphur` VALUES ('916', '8607', 'สวี   ', 'Sawi', '6', '69');
INSERT INTO `amphur` VALUES ('917', '8608', 'ทุ่งตะโก   ', 'Thung Tako', '6', '69');
INSERT INTO `amphur` VALUES ('918', '9001', 'เมืองสงขลา   ', 'Mueang Songkhla', '6', '70');
INSERT INTO `amphur` VALUES ('919', '9002', 'สทิงพระ   ', 'Sathing Phra', '6', '70');
INSERT INTO `amphur` VALUES ('920', '9003', 'จะนะ   ', 'Chana', '6', '70');
INSERT INTO `amphur` VALUES ('921', '9004', 'นาทวี   ', 'Na Thawi', '6', '70');
INSERT INTO `amphur` VALUES ('922', '9005', 'เทพา   ', 'Thepha', '6', '70');
INSERT INTO `amphur` VALUES ('923', '9006', 'สะบ้าย้อย   ', 'Saba Yoi', '6', '70');
INSERT INTO `amphur` VALUES ('924', '9007', 'ระโนด   ', 'Ranot', '6', '70');
INSERT INTO `amphur` VALUES ('925', '9008', 'กระแสสินธุ์   ', 'Krasae Sin', '6', '70');
INSERT INTO `amphur` VALUES ('926', '9009', 'รัตภูมิ   ', 'Rattaphum', '6', '70');
INSERT INTO `amphur` VALUES ('927', '9010', 'สะเดา   ', 'Sadao', '6', '70');
INSERT INTO `amphur` VALUES ('928', '9011', 'หาดใหญ่   ', 'Hat Yai', '6', '70');
INSERT INTO `amphur` VALUES ('929', '9012', 'นาหม่อม   ', 'Na Mom', '6', '70');
INSERT INTO `amphur` VALUES ('930', '9013', 'ควนเนียง   ', 'Khuan Niang', '6', '70');
INSERT INTO `amphur` VALUES ('931', '9014', 'บางกล่ำ   ', 'Bang Klam', '6', '70');
INSERT INTO `amphur` VALUES ('932', '9015', 'สิงหนคร   ', 'Singhanakhon', '6', '70');
INSERT INTO `amphur` VALUES ('933', '9016', 'คลองหอยโข่ง   ', 'Khlong Hoi Khong', '6', '70');
INSERT INTO `amphur` VALUES ('934', '9077', 'ท้องถิ่นเทศบาลตำบลสำนักขาม   ', 'Sum Nung Kam', '6', '70');
INSERT INTO `amphur` VALUES ('935', '9096', 'เทศบาลตำบลบ้านพรุ*   ', 'Ban Pru*', '6', '70');
INSERT INTO `amphur` VALUES ('936', '9101', 'เมืองสตูล   ', 'Mueang Satun', '6', '71');
INSERT INTO `amphur` VALUES ('937', '9102', 'ควนโดน   ', 'Khuan Don', '6', '71');
INSERT INTO `amphur` VALUES ('938', '9103', 'ควนกาหลง   ', 'Khuan Kalong', '6', '71');
INSERT INTO `amphur` VALUES ('939', '9104', 'ท่าแพ   ', 'Tha Phae', '6', '71');
INSERT INTO `amphur` VALUES ('940', '9105', 'ละงู   ', 'La-ngu', '6', '71');
INSERT INTO `amphur` VALUES ('941', '9106', 'ทุ่งหว้า   ', 'Thung Wa', '6', '71');
INSERT INTO `amphur` VALUES ('942', '9107', 'มะนัง   ', 'Manang', '6', '71');
INSERT INTO `amphur` VALUES ('943', '9201', 'เมืองตรัง   ', 'Mueang Trang', '6', '72');
INSERT INTO `amphur` VALUES ('944', '9202', 'กันตัง   ', 'Kantang', '6', '72');
INSERT INTO `amphur` VALUES ('945', '9203', 'ย่านตาขาว   ', 'Yan Ta Khao', '6', '72');
INSERT INTO `amphur` VALUES ('946', '9204', 'ปะเหลียน   ', 'Palian', '6', '72');
INSERT INTO `amphur` VALUES ('947', '9205', 'สิเกา   ', 'Sikao', '6', '72');
INSERT INTO `amphur` VALUES ('948', '9206', 'ห้วยยอด   ', 'Huai Yot', '6', '72');
INSERT INTO `amphur` VALUES ('949', '9207', 'วังวิเศษ   ', 'Wang Wiset', '6', '72');
INSERT INTO `amphur` VALUES ('950', '9208', 'นาโยง   ', 'Na Yong', '6', '72');
INSERT INTO `amphur` VALUES ('951', '9209', 'รัษฎา   ', 'Ratsada', '6', '72');
INSERT INTO `amphur` VALUES ('952', '9210', 'หาดสำราญ   ', 'Hat Samran', '6', '72');
INSERT INTO `amphur` VALUES ('953', '9251', 'อำเภอเมืองตรัง(สาขาคลองเต็ง)**   ', 'Mueang Trang(Krong Teng)**', '6', '72');
INSERT INTO `amphur` VALUES ('954', '9301', 'เมืองพัทลุง   ', 'Mueang Phatthalung', '6', '73');
INSERT INTO `amphur` VALUES ('955', '9302', 'กงหรา   ', 'Kong Ra', '6', '73');
INSERT INTO `amphur` VALUES ('956', '9303', 'เขาชัยสน   ', 'Khao Chaison', '6', '73');
INSERT INTO `amphur` VALUES ('957', '9304', 'ตะโหมด   ', 'Tamot', '6', '73');
INSERT INTO `amphur` VALUES ('958', '9305', 'ควนขนุน   ', 'Khuan Khanun', '6', '73');
INSERT INTO `amphur` VALUES ('959', '9306', 'ปากพะยูน   ', 'Pak Phayun', '6', '73');
INSERT INTO `amphur` VALUES ('960', '9307', 'ศรีบรรพต   ', 'Si Banphot', '6', '73');
INSERT INTO `amphur` VALUES ('961', '9308', 'ป่าบอน   ', 'Pa Bon', '6', '73');
INSERT INTO `amphur` VALUES ('962', '9309', 'บางแก้ว   ', 'Bang Kaeo', '6', '73');
INSERT INTO `amphur` VALUES ('963', '9310', 'ป่าพะยอม   ', 'Pa Phayom', '6', '73');
INSERT INTO `amphur` VALUES ('964', '9311', 'ศรีนครินทร์   ', 'Srinagarindra', '6', '73');
INSERT INTO `amphur` VALUES ('965', '9401', 'เมืองปัตตานี   ', 'Mueang Pattani', '6', '74');
INSERT INTO `amphur` VALUES ('966', '9402', 'โคกโพธิ์   ', 'Khok Pho', '6', '74');
INSERT INTO `amphur` VALUES ('967', '9403', 'หนองจิก   ', 'Nong Chik', '6', '74');
INSERT INTO `amphur` VALUES ('968', '9404', 'ปะนาเระ   ', 'Panare', '6', '74');
INSERT INTO `amphur` VALUES ('969', '9405', 'มายอ   ', 'Mayo', '6', '74');
INSERT INTO `amphur` VALUES ('970', '9406', 'ทุ่งยางแดง   ', 'Thung Yang Daeng', '6', '74');
INSERT INTO `amphur` VALUES ('971', '9407', 'สายบุรี   ', 'Sai Buri', '6', '74');
INSERT INTO `amphur` VALUES ('972', '9408', 'ไม้แก่น   ', 'Mai Kaen', '6', '74');
INSERT INTO `amphur` VALUES ('973', '9409', 'ยะหริ่ง   ', 'Yaring', '6', '74');
INSERT INTO `amphur` VALUES ('974', '9410', 'ยะรัง   ', 'Yarang', '6', '74');
INSERT INTO `amphur` VALUES ('975', '9411', 'กะพ้อ   ', 'Kapho', '6', '74');
INSERT INTO `amphur` VALUES ('976', '9412', 'แม่ลาน   ', 'Mae Lan', '6', '74');
INSERT INTO `amphur` VALUES ('977', '9501', 'เมืองยะลา   ', 'Mueang Yala', '6', '75');
INSERT INTO `amphur` VALUES ('978', '9502', 'เบตง   ', 'Betong', '6', '75');
INSERT INTO `amphur` VALUES ('979', '9503', 'บันนังสตา   ', 'Bannang Sata', '6', '75');
INSERT INTO `amphur` VALUES ('980', '9504', 'ธารโต   ', 'Than To', '6', '75');
INSERT INTO `amphur` VALUES ('981', '9505', 'ยะหา   ', 'Yaha', '6', '75');
INSERT INTO `amphur` VALUES ('982', '9506', 'รามัน   ', 'Raman', '6', '75');
INSERT INTO `amphur` VALUES ('983', '9507', 'กาบัง   ', 'Kabang', '6', '75');
INSERT INTO `amphur` VALUES ('984', '9508', 'กรงปินัง   ', 'Krong Pinang', '6', '75');
INSERT INTO `amphur` VALUES ('985', '9601', 'เมืองนราธิวาส   ', 'Mueang Narathiwat', '6', '76');
INSERT INTO `amphur` VALUES ('986', '9602', 'ตากใบ   ', 'Tak Bai', '6', '76');
INSERT INTO `amphur` VALUES ('987', '9603', 'บาเจาะ   ', 'Bacho', '6', '76');
INSERT INTO `amphur` VALUES ('988', '9604', 'ยี่งอ   ', 'Yi-ngo', '6', '76');
INSERT INTO `amphur` VALUES ('989', '9605', 'ระแงะ   ', 'Ra-ngae', '6', '76');
INSERT INTO `amphur` VALUES ('990', '9606', 'รือเสาะ   ', 'Rueso', '6', '76');
INSERT INTO `amphur` VALUES ('991', '9607', 'ศรีสาคร   ', 'Si Sakhon', '6', '76');
INSERT INTO `amphur` VALUES ('992', '9608', 'แว้ง   ', 'Waeng', '6', '76');
INSERT INTO `amphur` VALUES ('993', '9609', 'สุคิริน   ', 'Sukhirin', '6', '76');
INSERT INTO `amphur` VALUES ('994', '9610', 'สุไหงโก-ลก   ', 'Su-ngai Kolok', '6', '76');
INSERT INTO `amphur` VALUES ('995', '9611', 'สุไหงปาดี   ', 'Su-ngai Padi', '6', '76');
INSERT INTO `amphur` VALUES ('996', '9612', 'จะแนะ   ', 'Chanae', '6', '76');
INSERT INTO `amphur` VALUES ('997', '9613', 'เจาะไอร้อง   ', 'Cho-airong', '6', '76');
INSERT INTO `amphur` VALUES ('998', '9681', '*อ.บางนรา  จ.นราธิวาส   ', '*Bang Nra', '6', '76');

-- ----------------------------
-- Table structure for `booking`
-- ----------------------------
DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking` (
  `BOOKING_ID` varchar(255) NOT NULL,
  `BOOKING_STATUS` varchar(1) NOT NULL DEFAULT '',
  `MERCHANT_ID` varchar(255) NOT NULL DEFAULT '',
  `SUM_PRICE` double DEFAULT NULL,
  `SLIP` varchar(255) DEFAULT NULL,
  `PAY_TYPE` varchar(1) NOT NULL DEFAULT '',
  `BANK` varchar(2) NOT NULL DEFAULT '',
  `ADDRESS` varchar(255) DEFAULT NULL,
  `PROVINCE` varchar(2) DEFAULT NULL,
  `POSTCODE` varchar(5) DEFAULT NULL,
  `REJECT_REASON` text DEFAULT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `CREATED_BY` varchar(255) NOT NULL DEFAULT '',
  `LAST_UPDATED_DATE` datetime NOT NULL,
  `LAST_UPDATED_BY` varchar(255) NOT NULL DEFAULT '',
  `AUTHORIZED_DATE` datetime DEFAULT NULL,
  `AUTHORIZED_BY` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`BOOKING_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of booking
-- ----------------------------

-- ----------------------------
-- Table structure for `booking_detail`
-- ----------------------------
DROP TABLE IF EXISTS `booking_detail`;
CREATE TABLE `booking_detail` (
  `BOOKING_DETAIL_ID` varchar(255) NOT NULL,
  `BOOKING_ID` varchar(255) NOT NULL,
  `SIM_NUMBER` varchar(10) NOT NULL DEFAULT '',
  `ACTIVATE_FLAG` varchar(1) DEFAULT NULL,
  `BOOKING_STATUS` varchar(1) NOT NULL DEFAULT '',
  `EFFECTIVE_DATE` date DEFAULT NULL,
  `PRICE` double NOT NULL,
  `CUSTOMER_ID` varchar(120) DEFAULT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `CREATED_BY` varchar(255) NOT NULL DEFAULT '',
  `LAST_UPDATED_DATE` datetime NOT NULL,
  `LAST_UPDATED_BY` varchar(255) NOT NULL DEFAULT '',
  `AUTHORIZED_DATE` datetime DEFAULT NULL,
  `AUTHORIZED_BY` varchar(20) DEFAULT NULL,
  `REJECT_REASON` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`BOOKING_DETAIL_ID`,`BOOKING_ID`,`SIM_NUMBER`),
  KEY `BOOKING_ID` (`BOOKING_ID`),
  KEY `SIM_NUMBER` (`SIM_NUMBER`),
  CONSTRAINT `BOOKING_ID` FOREIGN KEY (`BOOKING_ID`) REFERENCES `booking` (`BOOKING_ID`),
  CONSTRAINT `SIM_NUMBER` FOREIGN KEY (`SIM_NUMBER`) REFERENCES `sim_mst` (`SIM_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of booking_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `error_message`
-- ----------------------------
DROP TABLE IF EXISTS `error_message`;
CREATE TABLE `error_message` (
  `ERROR_CODE` varchar(4) NOT NULL,
  `ERROR_MSG_EN` varchar(255) DEFAULT NULL,
  `ERROR_MSG_TH` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ERROR_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of error_message
-- ----------------------------
INSERT INTO `error_message` VALUES ('101', 'Username or password incorrect', 'Username หรือ password ผิด');
INSERT INTO `error_message` VALUES ('113', '', 'ไม่พบ Privilege & Program menu ของผู้ใช้');
INSERT INTO `error_message` VALUES ('114', '', 'ไม่สามารถลบ Role ได้ เนื่องจากมีการกำหนด Role ให้กับ User แล้ว');
INSERT INTO `error_message` VALUES ('115', '', 'ไม่สามารถลบ Role ได้ เนื่องจาก User อยู่ระหว่างการกำหนด Role นี้');
INSERT INTO `error_message` VALUES ('116', 'Delete role success.', 'ทำการลบ Role สำเร็จ');
INSERT INTO `error_message` VALUES ('117', '', 'ไม่สามารถเพิ่ม Role ได้ เนื่องจากมี Role ในระบบแล้ว');
INSERT INTO `error_message` VALUES ('999', 'Please contact admin', 'กรุณาติดต่อผู้ดูแลระบบ');

-- ----------------------------
-- Table structure for `master_setup`
-- ----------------------------
DROP TABLE IF EXISTS `master_setup`;
CREATE TABLE `master_setup` (
  `GROUP_TYPE` varchar(30) NOT NULL,
  `ID` int(3) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `IMAGE` varchar(255) DEFAULT NULL,
  `SUB_DESCRIPTION` text DEFAULT NULL,
  PRIMARY KEY (`GROUP_TYPE`,`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of master_setup
-- ----------------------------
INSERT INTO `master_setup` VALUES ('BANK', '1', 'ธนาคารกรุงเทพ', 'images/bbl.png', 'นายธนิกุล สกุลวรวิทย์ <br/><b>เลขบัญชี : </b> 000-0000-000 สาขาเมืองทองธานี');
INSERT INTO `master_setup` VALUES ('BANK', '2', 'ธนาคารไทยพาณิชย์', 'images/scb.png', 'นายธนิกุล สกุลวรวิทย์ <br/><b>เลขบัญชี : </b> 000-0000-000 สาขาเมืองทองธานี');
INSERT INTO `master_setup` VALUES ('BANK', '3', 'ธนาคารกสิกรไทย', 'images/kbank.png', 'นายธนิกุล สกุลวรวิทย์ <br/><b>เลขบัญชี : </b> 000-0000-000 สาขาเมืองทองธานี');
INSERT INTO `master_setup` VALUES ('BANK', '4', 'ธนาคารกรุงไทย', 'images/ktb.png', 'นายธนิกุล สกุลวรวิทย์ <br/><b>เลขบัญชี : </b> 000-0000-000 สาขาเมืองทองธานี');
INSERT INTO `master_setup` VALUES ('BANK', '5', 'ธนาคารทหารไทย', 'images/tmb.png', 'นายธนิกุล สกุลวรวิทย์ <br/><b>เลขบัญชี : </b> 000-0000-000 สาขาเมืองทองธานี');
INSERT INTO `master_setup` VALUES ('BANK', '6', 'ธนาคารกรุงศรีอยุธยา', 'images/aycap.png', 'นายธนิกุล สกุลวรวิทย์ <br/><b>เลขบัญชี : </b> 000-0000-000 สาขาเมืองทองธานี');
INSERT INTO `master_setup` VALUES ('PREFIX', '1', 'นาย', null, null);
INSERT INTO `master_setup` VALUES ('PREFIX', '2', 'นาง', null, null);
INSERT INTO `master_setup` VALUES ('PREFIX', '3', 'นางสาว', null, null);
INSERT INTO `master_setup` VALUES ('REQESUT_TYPE', '6', 'เบอร์โทรศัพท์', null, null);
INSERT INTO `master_setup` VALUES ('REQUEST_TYPE', '1', 'เลขนำหน้าที่ต้องการ', null, null);
INSERT INTO `master_setup` VALUES ('REQUEST_TYPE', '2', 'หมายเลขเรียง 4 ตัว', null, null);
INSERT INTO `master_setup` VALUES ('REQUEST_TYPE', '3', 'เลขที่ต้องการ', null, null);
INSERT INTO `master_setup` VALUES ('REQUEST_TYPE', '4', 'เลขที่ไม่ต้องการ', null, null);
INSERT INTO `master_setup` VALUES ('REQUEST_TYPE', '5', 'ผลรวมของเบอร์', null, null);
INSERT INTO `master_setup` VALUES ('ROLE', '1', 'Super Admin', null, null);
INSERT INTO `master_setup` VALUES ('ROLE', '2', 'Admin', null, null);
INSERT INTO `master_setup` VALUES ('ROLE', '3', 'Data Entry', null, null);
INSERT INTO `master_setup` VALUES ('ROLE', '4', 'USER', null, null);

-- ----------------------------
-- Table structure for `predict`
-- ----------------------------
DROP TABLE IF EXISTS `predict`;
CREATE TABLE `predict` (
  `PREDICT_ID` int(2) NOT NULL,
  `PREDICT_CONTENT` text DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(120) DEFAULT NULL,
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_BY` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`PREDICT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of predict
-- ----------------------------
INSERT INTO `predict` VALUES ('1', null, null, null, null, null);
INSERT INTO `predict` VALUES ('2', null, null, null, null, null);
INSERT INTO `predict` VALUES ('3', 'xxx', null, null, '2018-03-02 10:00:44', null);
INSERT INTO `predict` VALUES ('4', null, null, null, null, null);
INSERT INTO `predict` VALUES ('5', null, null, null, null, null);
INSERT INTO `predict` VALUES ('6', null, null, null, null, null);
INSERT INTO `predict` VALUES ('7', null, null, null, null, null);
INSERT INTO `predict` VALUES ('8', null, null, null, null, null);
INSERT INTO `predict` VALUES ('9', null, null, null, null, null);
INSERT INTO `predict` VALUES ('10', null, null, null, null, null);
INSERT INTO `predict` VALUES ('11', null, null, null, null, null);
INSERT INTO `predict` VALUES ('12', null, null, null, null, null);
INSERT INTO `predict` VALUES ('13', null, null, null, null, null);
INSERT INTO `predict` VALUES ('14', null, null, null, null, null);
INSERT INTO `predict` VALUES ('15', null, null, null, null, null);
INSERT INTO `predict` VALUES ('16', null, null, null, null, null);
INSERT INTO `predict` VALUES ('17', null, null, null, null, null);
INSERT INTO `predict` VALUES ('18', null, null, null, null, null);
INSERT INTO `predict` VALUES ('19', null, null, null, null, null);
INSERT INTO `predict` VALUES ('20', null, null, null, null, null);
INSERT INTO `predict` VALUES ('21', null, null, null, null, null);
INSERT INTO `predict` VALUES ('22', null, null, null, null, null);
INSERT INTO `predict` VALUES ('23', null, null, null, null, null);
INSERT INTO `predict` VALUES ('24', null, null, null, null, null);
INSERT INTO `predict` VALUES ('25', null, null, null, null, null);
INSERT INTO `predict` VALUES ('26', null, null, null, null, null);
INSERT INTO `predict` VALUES ('27', null, null, null, null, null);
INSERT INTO `predict` VALUES ('28', null, null, null, null, null);
INSERT INTO `predict` VALUES ('29', null, null, null, null, null);
INSERT INTO `predict` VALUES ('30', null, null, null, null, null);
INSERT INTO `predict` VALUES ('31', null, null, null, null, null);
INSERT INTO `predict` VALUES ('32', null, null, null, null, null);
INSERT INTO `predict` VALUES ('33', null, null, null, null, null);
INSERT INTO `predict` VALUES ('34', null, null, null, null, null);
INSERT INTO `predict` VALUES ('35', null, null, null, null, null);
INSERT INTO `predict` VALUES ('36', null, null, null, null, null);
INSERT INTO `predict` VALUES ('37', null, null, null, null, null);
INSERT INTO `predict` VALUES ('38', null, null, null, null, null);
INSERT INTO `predict` VALUES ('39', null, null, null, null, null);
INSERT INTO `predict` VALUES ('40', null, null, null, null, null);
INSERT INTO `predict` VALUES ('41', null, null, null, null, null);
INSERT INTO `predict` VALUES ('42', null, null, null, null, null);
INSERT INTO `predict` VALUES ('43', null, null, null, null, null);
INSERT INTO `predict` VALUES ('44', null, null, null, null, null);
INSERT INTO `predict` VALUES ('45', null, null, null, null, null);
INSERT INTO `predict` VALUES ('46', null, null, null, null, null);
INSERT INTO `predict` VALUES ('47', null, null, null, null, null);
INSERT INTO `predict` VALUES ('48', null, null, null, null, null);
INSERT INTO `predict` VALUES ('49', null, null, null, null, null);
INSERT INTO `predict` VALUES ('50', null, null, null, null, null);
INSERT INTO `predict` VALUES ('51', null, null, null, null, null);
INSERT INTO `predict` VALUES ('52', null, null, null, null, null);
INSERT INTO `predict` VALUES ('53', null, null, null, null, null);
INSERT INTO `predict` VALUES ('54', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p><span style=\"color: #0033ff; font-family: tahoma; font-size: large;\">&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;<strong>ผลรวม&nbsp; <span style=\"font-size: x-large;\">54</span></strong></span></p>\n<p>&nbsp; <strong>คุณภาพ &nbsp;58%</strong></p>\n<p><span style=\"color: #0033ff; font-family: tahoma; font-size: large;\"> &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;เกรด&nbsp; &nbsp;D&nbsp; :&nbsp; เบอร์เริ่มเหนื่อย</span></p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<ul>\n<li>เบอร์นี้ มีเลขรวมที่ดี และ</li>\n</ul>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>มีเลขภายในดี แต่มีเลขที่ต้องตัดคะแนนคือ&nbsp; 67 77 21</p>\n<p>&nbsp;</p>\n<p>&nbsp;<span style=\"color: #0033cc; font-family: tahoma; font-size: medium;\">&nbsp;</span></p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<ul>\n<li>เบอร์นี้ มีเลขภาย ส่งเสริมให้ใจกล้า กระฉับกระเฉง ว่องไว&nbsp; จึงไม่เหมาะกับผู้ที่มีนิสัยใจร้อน</li>\n</ul>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<ul>\n<li>บทวิเคราะห์ตัวเลขด้านล่าง อยู่ที่คุณเลือก ว่าเบอร์ชุดนี้ เหมาะกับคุณหรือไม่</li>\n</ul>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<h2><span style=\"color: red; font-size: large;\">จุดเด่น ของเบอร์ : <strong>086-777-9217</strong></span></h2>\n<p><span style=\"color: #623100; font-family: tahoma; font-size: medium;\"> &nbsp; &nbsp;&nbsp;&nbsp;</span></p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>เหมาะกับคนใจเย็น</p>\n<p>&nbsp;มีความเป็นผู้นำ ไฟแรง สมองเป็นเลิศ งานโครงการใหญ่ มีความคิดสร้างสรรค์ งานออกแบบ ศิลปะได้ทุกแขนง&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<h2><span style=\"color: red; font-family: Tahoma; font-size: large;\">วิเคราะห์ ผลรวมตัวเลข : <strong>54</strong></span></h2>\n<p>&nbsp;&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p><span style=\"color: #006666; font-family: tahoma; font-size: medium;\"><strong> กำลังพระเคราะห์ได้เลข 54 เป็นเลขที่แสดงถึงนิมิตแห่งความสำเร็จ </strong></span></p>\n<p><span style=\"font-family: tahoma; font-size: medium;\"> &nbsp; &nbsp;&nbsp; ชีวิตมักจะได้คุ้มครองจากสิ่งศักดิ์สิทธิ์ เป็นคนรู้ง่าย มีสติ ปัญญาแตกฉานเข้าใจอะไรได้งาย อุปสรรคในชีวิตมีน้อยแต่ต้องระวังอุบัติเหตุจากยานพาหนะ และระวังเรื่องเกี่ยวกับสายตา เพราะเลข 54 บวกกันได้ 9 ดาวเกตุธาตุลม</span></p>\n<p>&nbsp;</p>\n<p><span style=\"font-family: tahoma; font-size: medium;\"> &nbsp; &nbsp;&nbsp; สรุป : เป็นมงคลแก่ชีวิตยิ่งที่ได้ชื่อนี้ เพราะรวมกันได้ 9 พอดี เรื่องงาน ความรัก และอื่นๆ สำเร็จสมปรารถนา จะมีตำแหน่งสูง คนเคารพนับถือ ปัญญาดี เรียนอะไรก็แตกฉาน มีคนคอยค้ำจุนหนุนส่ง ไม่มีทางตกต่ำหรือขาดแคลน สิ่งศักดิ์สิทธิ์คุ้มครอง</span></p>\n<p><span style=\"color: #006666; font-family: tahoma; font-size: medium;\"><strong> หมายเลข 54 ดวงดาวแห่งโชค</strong></span></p>\n<p><br /><span style=\"font-family: tahoma; font-size: medium;\"> &nbsp; &nbsp;&nbsp; เป็นเลขดีมาก ถือว่าเป็นหมายเลขดีมากเลขหนึ่ง ชีวิตมักจะมีโชคดีอยู่เนืองๆ มักจะประสบโชคดีอย่างไม่คาดฝันอยู่บ่อยๆ สติปัญญาดี จะได้รับความร่วมมือ จากทุกวงการเป็นอย่างดี จะได้ลาภก้อนโตจากการเสี่ยงทุกรูปแบบ มีความมั่นคงในชีวิต</span></p>\n<p><span style=\"color: #006666; font-family: tahoma; font-size: medium;\"><strong> หมายเลข 54 พลังมหาราชาโชคความสำเร็จ</strong></span></p>\n<p><br /><span style=\"font-family: tahoma; font-size: medium;\"> &nbsp; &nbsp;&nbsp; เป็นเลขดีมาก มีมงคลแก่ชีวิต จะประสบความสำเร็จได้โดยง่าย มีตำแหน่งหน้าที่การงานใหญ่โต มีคนเคารพนับถือมากมาย การศึกษาจะประสบความสำเร็จอย่างดีเยี่ยม มีโอกาสได้ทุนหรือทำงานต่างประเทศ มีคนคอยช่วยเหลือ หนุนนำตลอด ชีวิตมักจะได้รับการคุ้มครอง จากสิ่งศักดิ์สิทธิ์ เป็นคนเรียนรู้ง่าย มีความสนุกกับการเรียนรู้ ความรักก็จะราบรื่น มีโอกาสมีรักที่ต่างประเทศได้</span></p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<h2><span style=\"color: red; font-size: large;\">วิเคราะห์เลขภายใน : <strong>086-777-9217</strong></span></h2>\n<p>&nbsp;</p>\n<p>เลขภายในจะบอกถึง ความเป็นตัวตนของผู้ที่ใช้เบอร์มือถือนี้ มีรายละเอียดดังนี้</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p><span style=\"color: #ccffff; font-family: tahoma; font-size: small;\">17</span></p>\n<p><span style=\"font-family: tahoma; font-size: medium;\">&nbsp; &nbsp;&nbsp; แสวงหาความมั่นคงในชีวิต ชอบอะไรชัวร์ ไฟแรง เก่งกล้า มีความมั่นใจตัวเองสูง มีความเป็นผู้นำ กล้าคิดกล้าทำ พร้อมลงมือทำ เป็นคนที่ทำงานดี&nbsp; มีสมองเป็นเลิศในด้านธุรกิจและการบริหาร แต่ติดนิสัยทนงตัว โมโหแรงแสดงออกเกินจริง ทำอะไรเอาแต่ใจไม่ฟังใคร เอาตัวเองเป็นศูนย์กลาง เพราะมั่นใจความสามารถของตน บางสถานการณ์ก็บ้าพลัง มักแสดงออกเกินจริง ใจร้อนวู่วาม รักใครรักจริง เกลียดใครก็เกลียดจนตาย เป็นคนที่ทำงานดีมากคนหนึ่ง จริงจัง ถนัดบู๊ ล้างผลาญ เผด็จการกับคนรอบตัวเสมอ เป็นม้ามืดไม่มีคนคิดว่าจะเก่ง มังกรซ่อนลาย จะได้ดีเพราะความเก่ง ควรระวังคนไม่พอใจ หมั่นไส้ วางตัวให้อ่อนลง อาจจะได้ความร่วมมือที่ดียิ่งขึ้น และเจ้าตัวชอบคิดการใหญ่ จึงได้ทำงานโครงการใหญ่ แบบBig Project ซึ่งผลงานก็ออกมาดีทีเดียว ความสัมพันธ์กับสังคมและเพื่อนฝูงดี แต่คนใกล้ตัวกันมักมีปัญหาเพราะจู้จี้และความช้า</span></p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p><span style=\"color: #ccffff; font-family: tahoma; font-size: small;\">21</span></p>\n<p><span style=\"font-family: tahoma; font-size: medium;\">&nbsp; &nbsp;&nbsp; อารมณ์แปรปรวน ขึ้นลงไม่แน่นอน หงุดหงิดง่าย เศร้าหมอง เก็บความรู้สึกไว้ลึกๆ ในที่สุดก็ระเบิด กับคนใกล้ตัว เดี๋ยวแข็งกร้าวเดี๋ยวอ่อนโยน เป็นคนสองบุคลิก มีจินตนาการ แนวคิดที่ไม่เหมือนใคร บทดี ดีใจหาย บทร้าย ก็ดุเหลือเกิน สองอารมณ์ตีกันจึงพาให้สับสน ขัดแย้งในตัวเอง ตัดสินใจอะไรยาก มีการเปลี่ยนใจบ่อยครั้ง มักมีความคิดเห็นแตกต่างจากผู้อื่น ออกความเห็นขวางโลก&nbsp; ทำให้ทะเลาะกับคนรอบข้างด้วยเรื่องเล็กๆน้อย เพราะเจ้าตัวไม่ยอมรับใครง่ายๆ พูดอะไรก็มักจะขัดขาตัวเองกลับไปกลับมา เช่น อันนี้ก็น่าจะดีนะ แต่คิดอีกทีไม่เอาดีกว่า มักเอาความคิดในกระบวนการตัดสินใจออกมาเป็นคำพูด ต้องระวังอาการของโรคจิตประสาท เกิดจากที่อารมณ์รุนแรงซึ่งยากที่จะยับยั้งของตัวเอง</span></p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p><span style=\"color: #ccffff; font-family: tahoma; font-size: small;\">92</span></p>\n<p><span style=\"font-family: tahoma; font-size: medium;\">&nbsp; &nbsp;&nbsp; รักสวยรักงาม ถนัดศิลปะ มีสุนทรียภาพสูง แต่งตัวเก่ง กระตือรืนร้น มีไหวพริบ เรียนรู้เร็ว มีความคิดสร้างสรรค์ มีจินตนาการสูงส่ง ไอเดียบรรเจิด ไหวพริบดีเรียนรู้เร็ว มีความคิดเพ้อฝันแบบมีโลกส่วนตัว บางครั้งก็ทำตัวลึกลับ มีส่วนขาดส่วนเกินในตัวเอง เหมือนคนโลเล อาจถูกชักจูงได้ง่าย ควรทำตัวให้มีจุดยืนในตัวเอง หากชอบสมาธิภานาจะมีสัมผัสที่หก มีลางสังหรณ์แล้วเกิดขึ้นจริง อาจชอบสะสมของเก่า ของขลัง ของแปลก หรือศึกษาข้อมูลเกี่ยวกับเรื่องเร้นลับ โหราศาสตร์ พิธีกรรม ปรัชญา หรือศาสนา&nbsp; เหมาะสำหรับทำงานเกี่ยวข้องกับศิลปะได้ทุกแขนง ร้องรำทำเพลง หนัง แสดงละคร ถ่ายภาพ วาดภาพ บันเทิง งานออกแบบ-ตกแต่ง ออกแบบเสื้อผ้า ของกิน กิจการเกี่ยวข้องกับสตรี มีผลดีมากสำหรับธุรกิจนำเข้าส่งออกระหว่างประเทศ</span></p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p><span style=\"color: #ccffff; font-family: tahoma; font-size: small;\">79</span></p>\n<p><span style=\"font-family: tahoma; font-size: medium;\">&nbsp; &nbsp;&nbsp; มองการณ์ไกล วิสัยทัศน์เยี่ยม มีมุมมองแปลกใหม่ น่าสนใจ แต่บางเรื่องคนก็ว่าเพี้ยน ชอบศึกษาเรื่องเทคโนโลยี ติดตามข่าวสารสมัยใหม่ ทันสมัยตลอด พร้อมกับชอบเรื่องราวโบราณ อารยธรรมโบราณ หนังสือโบราณ วิชาโบราณ ลี้ลับ หลุดโลก เหนือธรรมชาติ หาคำอธิบายได้ยาก บางทีชอบครุ่นคิดหมกมุ่นอยู่คนเดียว วางตัวอิสระ ไม่ผูกมัด และในบางราย อาจมีความผูกพันของขลังของเก่า วัตถุโบราณ สามารถทำมาค้าขึ้นกับสิ่งเหล่านี้ หรือชอบสะสมของแปลก เจ้าตัวมีสัมผัสที่หก คาดการณ์แม่น จะได้โชคลาภแบบแปลกๆบ่อยครั้ง เหมาะกับ นักคอมพิวเตอร์ นักธุรกิจไอที นักวางแผน ที่ดิน โรงงานอุตสาหกรรม นักลงทุน&nbsp; นักค้าวัตถุมงคลวัตถุโบราณ นักธุรกิจแฟรนไชส์ นักธุรกิจเครือข่าย</span></p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p><span style=\"color: #ccffff; font-family: tahoma; font-size: small;\">77</span></p>\n<p><span style=\"font-family: tahoma; font-size: medium;\">&nbsp; &nbsp;&nbsp; เป็นนักสู้ ทรหดอดทน สามารถเก็บรายละเอียดเล็กๆน้อยๆได้ดี รับผิดชอบสูงรอบคอบ เหมาะที่จะทำงานหนัก งานที่ยากลำบาก งานที่สำเร็จช้าๆ งานที่ต้องใช้ความอดทนสูง ชีวิตนี้มีอุปสรรค ปัญหารุมเร้า เครียดสุดๆ ไม่มีเรื่องให้ทุกข์ก็ทุกข์ ทุกข์เพราะความคิด จริงจังกับชีวิต มีแรงกดดันสูง ผ่อนคลายปล่อยไม่เป็น มีเรื่องผิดหวังแล้วจะประชดชีวิต&nbsp; พูดวาจาขวานผ่าซาก รักการทำงานมากกว่าการอยู่เฉย สู้ทำงานหนัก มักจะได้ทำงานที่ต้องใช้ความอดทนอย่างมาก คิดการใหญ่ มุ่งมั่นสร้างอนาคต เป็นคนคิดมาก คิดกังวลเรื่องวางแผนชีวิตอยู่ตลอด จะทำสิ่งใดมักต้องใช้เวลานาน เพราะเป็นคนย้ำคิดย้ำทำ ขี้จุกจิก ขี้สงสัย อาชีพที่เหมาะคือ ธุรกิจก่อสร้าง ช่างอัญมณี ออกแบบ จิตรกร ที่ดิน ป่าไม้ โรงงานอุตสาหกรรม ค้าของมือสอง งานใหญ่ๆ งานที่ต้องการการสังเกตมีรายละเอียดสูง</span></p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p><span style=\"color: #ccffff; font-family: tahoma; font-size: small;\">67</span></p>\n<p><span style=\"font-family: tahoma; font-size: medium;\">&nbsp; &nbsp;&nbsp; เป็นศัตรูกับการเงินและความรัก เป็นทุกข์เป็นกังวล แม้มุ่งมั่นหาเงินจนมีรายได้ดี แต่ก็มีรายจ่ายสูงเช่นกัน เพราะมีคนช่วยใช้ภาระรับผิดชอบมาก และเจ้าตัวเองก็ใช้เงินเก่งอยู่แล้ว รสนิยมดี จัดว่าเป็นพวกเสพสุขนิยมตัวจริง ภายนอกมีความเป็นอยู่ที่ดูดี แต่เบื้องหลังความหรูหรานั้นมีหนี้สินเยอะแยะ ชักหน้าไม่ถึงหลัง หากปล่อยเครดิตใครไปให้ใครกู้ยืม ทวงยากไม่ได้คืน ความรักรุนแรง คุ้มดี คุ้มร้าย ยากที่จะเดาใจ บทดีก็ดีเหลือเกิน บทร้ายก็ต้องหลีกให้ทัน บางครั้งโรแมนติก มีเสน่ห์เร้าร้อน หากรักใครยอมทุ่มให้หมด แต่รักมักไม่สมหวัง มีอุปสรรค เช่น พลัดพราก แยกจาก หรืออยู่ห่างไกลกัน รักต้องรอ รักที่ต้องอดทน และมีปากเสียงกับคู่ของตนบ่อยครั้ง&nbsp; อิทธิพลเลขชุดนี้ อาจทำอะไรก็ไม่ได้ดั่งใจ แต่ปัญหาทั้งหมดที่มารุมเร้า ก็ทำให้ผู้ใช้เลขคู่นี้แข็งแกร่งอย่างไร้ข้อสงสัย ระวังปัญหาสุขภาพ มดลูกสำหรับหญิง ต่อมลูกหมากสำหรับชาย สมรรถภาพทางเพศ และระบบกระเพาะปัสสาวะ</span></p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n</body>\n</html>', null, null, '2018-05-04 15:00:03', null);
INSERT INTO `predict` VALUES ('55', null, null, null, null, null);
INSERT INTO `predict` VALUES ('56', null, null, null, null, null);
INSERT INTO `predict` VALUES ('57', null, null, null, null, null);
INSERT INTO `predict` VALUES ('58', null, null, null, null, null);
INSERT INTO `predict` VALUES ('59', null, null, null, null, null);
INSERT INTO `predict` VALUES ('60', null, null, null, null, null);
INSERT INTO `predict` VALUES ('61', null, null, null, null, null);
INSERT INTO `predict` VALUES ('62', null, null, null, null, null);
INSERT INTO `predict` VALUES ('63', null, null, null, null, null);
INSERT INTO `predict` VALUES ('64', null, null, null, null, null);
INSERT INTO `predict` VALUES ('65', null, null, null, null, null);
INSERT INTO `predict` VALUES ('66', null, null, null, null, null);
INSERT INTO `predict` VALUES ('67', null, null, null, null, null);
INSERT INTO `predict` VALUES ('68', null, null, null, null, null);
INSERT INTO `predict` VALUES ('69', null, null, null, null, null);
INSERT INTO `predict` VALUES ('70', null, null, null, null, null);
INSERT INTO `predict` VALUES ('71', null, null, null, null, null);
INSERT INTO `predict` VALUES ('72', null, null, null, null, null);
INSERT INTO `predict` VALUES ('73', null, null, null, null, null);
INSERT INTO `predict` VALUES ('74', null, null, null, null, null);
INSERT INTO `predict` VALUES ('75', null, null, null, null, null);
INSERT INTO `predict` VALUES ('76', null, null, null, null, null);
INSERT INTO `predict` VALUES ('77', null, null, null, null, null);
INSERT INTO `predict` VALUES ('78', null, null, null, null, null);
INSERT INTO `predict` VALUES ('79', null, null, null, null, null);
INSERT INTO `predict` VALUES ('80', null, null, null, null, null);
INSERT INTO `predict` VALUES ('81', null, null, null, null, null);
INSERT INTO `predict` VALUES ('82', null, null, null, null, null);
INSERT INTO `predict` VALUES ('83', null, null, null, null, null);
INSERT INTO `predict` VALUES ('84', null, null, null, null, null);
INSERT INTO `predict` VALUES ('85', null, null, null, null, null);
INSERT INTO `predict` VALUES ('86', null, null, null, null, null);
INSERT INTO `predict` VALUES ('87', null, null, null, null, null);
INSERT INTO `predict` VALUES ('88', null, null, null, null, null);
INSERT INTO `predict` VALUES ('89', null, null, null, null, null);
INSERT INTO `predict` VALUES ('90', null, null, null, null, null);
INSERT INTO `predict` VALUES ('91', null, null, null, null, null);
INSERT INTO `predict` VALUES ('92', null, null, null, null, null);
INSERT INTO `predict` VALUES ('93', null, null, null, null, null);
INSERT INTO `predict` VALUES ('94', null, null, null, null, null);
INSERT INTO `predict` VALUES ('95', null, null, null, null, null);
INSERT INTO `predict` VALUES ('96', null, null, null, null, null);
INSERT INTO `predict` VALUES ('97', null, null, null, null, null);
INSERT INTO `predict` VALUES ('98', null, null, null, null, null);
INSERT INTO `predict` VALUES ('99', null, null, null, null, null);

-- ----------------------------
-- Table structure for `privilege`
-- ----------------------------
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege` (
  `ROLE_ID` bigint(20) NOT NULL,
  `PROGRAM_ID` bigint(20) NOT NULL,
  `MAKER` char(1) NOT NULL,
  `CHECKER` char(1) NOT NULL,
  `VIEWER` char(1) NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `CREATED_BY` varchar(120) NOT NULL,
  `LAST_UPDATED_DATE` datetime NOT NULL,
  `LAST_UPDATED_BY` varchar(120) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`PROGRAM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of privilege
-- ----------------------------
INSERT INTO `privilege` VALUES ('330', '1', 'Y', 'N', 'N', '2018-02-26 13:25:40', 'admin', '2018-02-26 13:25:40', 'admin');
INSERT INTO `privilege` VALUES ('330', '2', 'Y', 'N', 'N', '2018-02-26 17:09:11', 'SYSTEM', '2018-02-26 17:09:11', 'admin');
INSERT INTO `privilege` VALUES ('330', '3', 'Y', 'N', 'N', '2018-05-03 15:13:56', 'SYSTEM', '2018-05-03 15:13:56', 'admin');
INSERT INTO `privilege` VALUES ('330', '4', 'Y', 'N', 'N', '2018-03-12 21:18:03', 'SYSTEM', '2018-03-12 21:18:03', 'admin');
INSERT INTO `privilege` VALUES ('330', '5', 'Y', 'N', 'N', '2018-03-12 21:18:03', 'SYSTEM', '2018-03-12 21:18:03', 'admin');
INSERT INTO `privilege` VALUES ('330', '9', 'N', 'Y', 'N', '2018-05-03 15:13:56', 'SYSTEM', '2018-05-03 16:12:04', 'admin');
INSERT INTO `privilege` VALUES ('330', '14', 'N', 'Y', 'N', '2018-05-03 15:13:56', 'SYSTEM', '2018-05-03 16:12:04', 'admin');
INSERT INTO `privilege` VALUES ('330', '15', 'Y', 'N', 'N', '2018-04-27 15:00:23', 'SYSTEM', '2018-04-27 15:00:29', 'admin');
INSERT INTO `privilege` VALUES ('331', '6', 'N', 'N', 'Y', '2018-02-26 21:11:31', 'admin', '2018-02-26 21:11:31', 'admin');
INSERT INTO `privilege` VALUES ('331', '7', 'N', 'N', 'Y', '2018-02-27 09:37:45', 'SYSTEM', '2018-02-27 09:37:45', 'admin');
INSERT INTO `privilege` VALUES ('331', '8', 'N', 'N', 'Y', '2018-02-27 09:37:45', 'SYSTEM', '2018-02-27 09:37:45', 'admin');
INSERT INTO `privilege` VALUES ('331', '10', 'N', 'N', 'Y', '2018-05-04 16:02:39', 'SYSTEM', '2018-05-04 16:02:39', 'admin');
INSERT INTO `privilege` VALUES ('331', '11', 'N', 'N', 'Y', '2018-05-04 15:30:51', 'SYSTEM', '2018-05-04 15:30:51', 'admin');
INSERT INTO `privilege` VALUES ('331', '12', 'N', 'N', 'Y', '2018-05-04 15:30:51', 'SYSTEM', '2018-05-04 15:30:51', 'admin');
INSERT INTO `privilege` VALUES ('331', '13', 'N', 'N', 'Y', '2018-05-04 15:30:51', 'SYSTEM', '2018-05-04 15:30:51', 'admin');
INSERT INTO `privilege` VALUES ('332', '3', 'Y', 'N', 'N', '2018-03-12 17:01:31', 'admin', '2018-03-12 17:01:31', 'admin');
INSERT INTO `privilege` VALUES ('333', '3', 'N', 'Y', 'N', '2018-03-12 17:01:56', 'admin', '2018-03-12 17:01:56', 'admin');
INSERT INTO `privilege` VALUES ('333', '9', 'N', 'Y', 'N', '2018-03-19 02:23:11', 'SYSTEM', '2018-03-19 02:23:11', 'admin');
INSERT INTO `privilege` VALUES ('333', '14', 'N', 'Y', 'N', '2018-05-04 16:07:48', 'SYSTEM', '2018-05-04 16:07:48', 'admin');

-- ----------------------------
-- Table structure for `program`
-- ----------------------------
DROP TABLE IF EXISTS `program`;
CREATE TABLE `program` (
  `PROGRAM_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `PROGRAM_NAME` varchar(255) NOT NULL,
  `PROGRAM_REF` varchar(255) NOT NULL,
  `PROGRAM_TYPE` varchar(10) NOT NULL,
  `PROGRAM_LEVEL` int(5) unsigned NOT NULL,
  `PROGRAM_GROUP` varchar(255) NOT NULL,
  `GROUP_LEVEL` int(5) unsigned NOT NULL,
  `POSITION` varchar(10) DEFAULT NULL,
  `ELEMENT_ID` varchar(20) NOT NULL,
  `ACTIVE_STATUS` char(1) NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `CREATED_BY` varchar(120) NOT NULL,
  `LAST_UPDATED_DATE` datetime NOT NULL,
  `LAST_UPDATED_BY` varchar(120) NOT NULL,
  `EXCEPT_ROLE` varchar(255) DEFAULT NULL,
  `DISABLE_FLAG` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`PROGRAM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of program
-- ----------------------------
INSERT INTO `program` VALUES ('1', 'Role and privilege', '/Admin/RolePrivilege', 'ADMIN', '1', 'User Management', '5', 'HEADER', 'menu_ele_1', 'Y', '2017-08-18 15:14:00', 'SYSTEM', '2017-08-18 15:14:00', 'SYSTEM', 'MAKER,CHECKER', 'Y');
INSERT INTO `program` VALUES ('2', 'จัดการผู้ใช้งานระบบ', '/Admin/ManageUser', 'ADMIN', '2', 'User Management', '5', 'HEADER', 'menu_ele_2', 'Y', '2018-02-26 13:48:36', 'SYSTEM', '2018-02-26 13:48:44', 'SYSTEM', 'MAKER,CHECKER', 'Y');
INSERT INTO `program` VALUES ('3', 'จัดการซิมการ์ด', '/Admin/ManageData', 'ADMIN', '1', 'Manage Data', '6', 'HEADER', 'menu_ele_3', 'Y', '2018-02-26 13:50:58', 'SYSTEM', '2018-02-26 13:51:19', 'SYSTEM', null, 'N');
INSERT INTO `program` VALUES ('4', 'จัดการข้อมูลการวิเคราะห์เบอร์', '/Admin/ManagePredict', 'ADMIN', '2', 'Manage Data', '6', 'HEADER', 'menu_ele_4', 'Y', '2018-02-26 13:52:50', 'SYSTEM', '2018-02-26 13:52:57', 'SYSTEM', 'CHECKER,VIEWER', 'N');
INSERT INTO `program` VALUES ('5', 'Setup Email Info', '/Admin/SetupEmail', 'ADMIN', '4', 'Manage Data', '6', 'HEADER', 'menu_ele_5', 'Y', '2018-02-26 13:54:22', 'SYSTEM', '2018-02-26 13:54:28', 'SYSTEM', 'CHECKER,VIEWER', 'N');
INSERT INTO `program` VALUES ('6', 'เบอร์สวยคัดพิเศษ', '/', 'USER', '1', 'เบอร์สวยคัดพิเศษ', '1', 'HEADER', 'menu_ele_6', 'Y', '2018-02-26 13:56:47', 'SYSTEM', '2018-02-26 13:56:54', 'SYSTEM', null, 'N');
INSERT INTO `program` VALUES ('7', 'ขอเบอร์ที่ไม่มีในระบบ', '/RequestSimCard', 'USER', '1', 'ขอเบอร์ที่ไม่มีในระบบ', '2', 'HEADER', 'menu_ele_7', 'Y', '2018-02-27 09:33:59', 'SYSTEM', '2018-02-27 09:34:07', 'SYSTEM', null, 'N');
INSERT INTO `program` VALUES ('8', 'คำนวณผลรวมเบอร์', '/Predict', 'USER', '1', 'คำนวณผลรวมเบอร์', '3', 'HEADER', 'menu_ele_8', 'Y', '2018-02-27 09:35:36', 'SYSTEM', '2018-02-27 09:35:43', 'SYSTEM', null, 'N');
INSERT INTO `program` VALUES ('9', 'จัดการการจองเบอร์โทร', '/Admin/ManageBooking', 'ADMIN', '1', 'Manage Booking', '7', 'HEADER', 'menu_ele_9', 'Y', '2018-03-19 02:21:18', 'SYSTEM', '2018-03-19 02:21:28', 'SYSTEM', 'MAKER,VIEWER', 'N');
INSERT INTO `program` VALUES ('10', 'ติดต่อสอบถาม', '/ContractUs', 'USER', '1', 'ติดต่อสอบถาม', '1', 'FOOTER', 'menu_ele_10', 'Y', '2018-03-19 17:29:02', 'SYSTEM', '2018-03-19 17:29:09', 'SYSTEM', null, 'N');
INSERT INTO `program` VALUES ('11', 'วิธีการชำระเงิน', '/Payment', 'USER', '1', 'วิธีการชำระเงิน', '2', 'FOOTER', 'menu_ele_11', 'Y', '2018-03-19 17:30:58', 'SYSTEM', '2018-03-19 17:31:05', 'SYSTEM', null, 'N');
INSERT INTO `program` VALUES ('12', 'วิธีการรับสินค้า', '/RecieveProduct', 'USER', '1', 'วิธีการรับสินค้า', '3', 'FOOTER', 'menu_ele_12', 'Y', '2018-03-19 17:32:43', 'SYSTEM', '2018-03-19 17:32:41', 'SYSTEM', null, 'N');
INSERT INTO `program` VALUES ('13', 'เงื่อนไขการสั่งสินค้า', '/ProductCondition', 'USER', '1', 'เงื่อนไขการรับสินค้า', '4', 'FOOTER', 'menu_ele_13', 'Y', '2018-03-19 17:36:07', 'SYSTEM', '2018-03-19 17:36:10', 'SYSTEM', null, 'N');
INSERT INTO `program` VALUES ('14', 'จัดการคำขอเบอร์ที่ไม่มีในระบบ', '/Admin/ManageRequest', 'ADMIN', '2', 'Manage Booking', '7', 'HEADER', 'menu_ele_14', 'Y', '2018-04-09 14:16:16', 'SYSTEM', '2018-04-09 14:16:27', 'SYSTEM', 'MAKER,VIEWER', 'N');
INSERT INTO `program` VALUES ('15', 'จัดการผู้ใช้งานระบบ (ลูกค้า)', '/Admin/ManageCustomer', 'ADMIN', '3', 'User Management', '5', 'HEADER', 'menu_ele_2', 'Y', '2018-04-27 14:59:37', 'SYSTEM', '2018-04-27 14:59:43', 'SYSTEM', 'CHECKER', 'N');

-- ----------------------------
-- Table structure for `province`
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `PROVINCE_ID` int(5) NOT NULL AUTO_INCREMENT,
  `PROVINCE_CODE` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `PROVINCE_NAME` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `PROVINCE_NAME_ENG` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `GEO_ID` int(5) NOT NULL DEFAULT 0,
  PRIMARY KEY (`PROVINCE_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of province
-- ----------------------------
INSERT INTO `province` VALUES ('1', '10', 'กรุงเทพมหานคร   ', 'Bangkok', '2');
INSERT INTO `province` VALUES ('2', '11', 'สมุทรปราการ   ', 'Samut Prakan', '2');
INSERT INTO `province` VALUES ('3', '12', 'นนทบุรี   ', 'Nonthaburi', '2');
INSERT INTO `province` VALUES ('4', '13', 'ปทุมธานี   ', 'Pathum Thani', '2');
INSERT INTO `province` VALUES ('5', '14', 'พระนครศรีอยุธยา   ', 'Phra Nakhon Si Ayutthaya', '2');
INSERT INTO `province` VALUES ('6', '15', 'อ่างทอง   ', 'Ang Thong', '2');
INSERT INTO `province` VALUES ('7', '16', 'ลพบุรี   ', 'Loburi', '2');
INSERT INTO `province` VALUES ('8', '17', 'สิงห์บุรี   ', 'Sing Buri', '2');
INSERT INTO `province` VALUES ('9', '18', 'ชัยนาท   ', 'Chai Nat', '2');
INSERT INTO `province` VALUES ('10', '19', 'สระบุรี', 'Saraburi', '2');
INSERT INTO `province` VALUES ('11', '20', 'ชลบุรี   ', 'Chon Buri', '5');
INSERT INTO `province` VALUES ('12', '21', 'ระยอง   ', 'Rayong', '5');
INSERT INTO `province` VALUES ('13', '22', 'จันทบุรี   ', 'Chanthaburi', '5');
INSERT INTO `province` VALUES ('14', '23', 'ตราด   ', 'Trat', '5');
INSERT INTO `province` VALUES ('15', '24', 'ฉะเชิงเทรา   ', 'Chachoengsao', '5');
INSERT INTO `province` VALUES ('16', '25', 'ปราจีนบุรี   ', 'Prachin Buri', '5');
INSERT INTO `province` VALUES ('17', '26', 'นครนายก   ', 'Nakhon Nayok', '2');
INSERT INTO `province` VALUES ('18', '27', 'สระแก้ว   ', 'Sa Kaeo', '5');
INSERT INTO `province` VALUES ('19', '30', 'นครราชสีมา   ', 'Nakhon Ratchasima', '3');
INSERT INTO `province` VALUES ('20', '31', 'บุรีรัมย์   ', 'Buri Ram', '3');
INSERT INTO `province` VALUES ('21', '32', 'สุรินทร์   ', 'Surin', '3');
INSERT INTO `province` VALUES ('22', '33', 'ศรีสะเกษ   ', 'Si Sa Ket', '3');
INSERT INTO `province` VALUES ('23', '34', 'อุบลราชธานี   ', 'Ubon Ratchathani', '3');
INSERT INTO `province` VALUES ('24', '35', 'ยโสธร   ', 'Yasothon', '3');
INSERT INTO `province` VALUES ('25', '36', 'ชัยภูมิ   ', 'Chaiyaphum', '3');
INSERT INTO `province` VALUES ('26', '37', 'อำนาจเจริญ   ', 'Amnat Charoen', '3');
INSERT INTO `province` VALUES ('27', '39', 'หนองบัวลำภู   ', 'Nong Bua Lam Phu', '3');
INSERT INTO `province` VALUES ('28', '40', 'ขอนแก่น   ', 'Khon Kaen', '3');
INSERT INTO `province` VALUES ('29', '41', 'อุดรธานี   ', 'Udon Thani', '3');
INSERT INTO `province` VALUES ('30', '42', 'เลย   ', 'Loei', '3');
INSERT INTO `province` VALUES ('31', '43', 'หนองคาย   ', 'Nong Khai', '3');
INSERT INTO `province` VALUES ('32', '44', 'มหาสารคาม   ', 'Maha Sarakham', '3');
INSERT INTO `province` VALUES ('33', '45', 'ร้อยเอ็ด   ', 'Roi Et', '3');
INSERT INTO `province` VALUES ('34', '46', 'กาฬสินธุ์   ', 'Kalasin', '3');
INSERT INTO `province` VALUES ('35', '47', 'สกลนคร   ', 'Sakon Nakhon', '3');
INSERT INTO `province` VALUES ('36', '48', 'นครพนม   ', 'Nakhon Phanom', '3');
INSERT INTO `province` VALUES ('37', '49', 'มุกดาหาร   ', 'Mukdahan', '3');
INSERT INTO `province` VALUES ('38', '50', 'เชียงใหม่   ', 'Chiang Mai', '1');
INSERT INTO `province` VALUES ('39', '51', 'ลำพูน   ', 'Lamphun', '1');
INSERT INTO `province` VALUES ('40', '52', 'ลำปาง   ', 'Lampang', '1');
INSERT INTO `province` VALUES ('41', '53', 'อุตรดิตถ์   ', 'Uttaradit', '1');
INSERT INTO `province` VALUES ('42', '54', 'แพร่   ', 'Phrae', '1');
INSERT INTO `province` VALUES ('43', '55', 'น่าน   ', 'Nan', '1');
INSERT INTO `province` VALUES ('44', '56', 'พะเยา   ', 'Phayao', '1');
INSERT INTO `province` VALUES ('45', '57', 'เชียงราย   ', 'Chiang Rai', '1');
INSERT INTO `province` VALUES ('46', '58', 'แม่ฮ่องสอน   ', 'Mae Hong Son', '1');
INSERT INTO `province` VALUES ('47', '60', 'นครสวรรค์   ', 'Nakhon Sawan', '2');
INSERT INTO `province` VALUES ('48', '61', 'อุทัยธานี   ', 'Uthai Thani', '2');
INSERT INTO `province` VALUES ('49', '62', 'กำแพงเพชร   ', 'Kamphaeng Phet', '2');
INSERT INTO `province` VALUES ('50', '63', 'ตาก   ', 'Tak', '4');
INSERT INTO `province` VALUES ('51', '64', 'สุโขทัย   ', 'Sukhothai', '2');
INSERT INTO `province` VALUES ('52', '65', 'พิษณุโลก   ', 'Phitsanulok', '2');
INSERT INTO `province` VALUES ('53', '66', 'พิจิตร   ', 'Phichit', '2');
INSERT INTO `province` VALUES ('54', '67', 'เพชรบูรณ์   ', 'Phetchabun', '2');
INSERT INTO `province` VALUES ('55', '70', 'ราชบุรี   ', 'Ratchaburi', '4');
INSERT INTO `province` VALUES ('56', '71', 'กาญจนบุรี   ', 'Kanchanaburi', '4');
INSERT INTO `province` VALUES ('57', '72', 'สุพรรณบุรี   ', 'Suphan Buri', '2');
INSERT INTO `province` VALUES ('58', '73', 'นครปฐม   ', 'Nakhon Pathom', '2');
INSERT INTO `province` VALUES ('59', '74', 'สมุทรสาคร   ', 'Samut Sakhon', '2');
INSERT INTO `province` VALUES ('60', '75', 'สมุทรสงคราม   ', 'Samut Songkhram', '2');
INSERT INTO `province` VALUES ('61', '76', 'เพชรบุรี   ', 'Phetchaburi', '4');
INSERT INTO `province` VALUES ('62', '77', 'ประจวบคีรีขันธ์   ', 'Prachuap Khiri Khan', '4');
INSERT INTO `province` VALUES ('63', '80', 'นครศรีธรรมราช   ', 'Nakhon Si Thammarat', '6');
INSERT INTO `province` VALUES ('64', '81', 'กระบี่   ', 'Krabi', '6');
INSERT INTO `province` VALUES ('65', '82', 'พังงา   ', 'Phangnga', '6');
INSERT INTO `province` VALUES ('66', '83', 'ภูเก็ต   ', 'Phuket', '6');
INSERT INTO `province` VALUES ('67', '84', 'สุราษฎร์ธานี   ', 'Surat Thani', '6');
INSERT INTO `province` VALUES ('68', '85', 'ระนอง   ', 'Ranong', '6');
INSERT INTO `province` VALUES ('69', '86', 'ชุมพร   ', 'Chumphon', '6');
INSERT INTO `province` VALUES ('70', '90', 'สงขลา   ', 'Songkhla', '6');
INSERT INTO `province` VALUES ('71', '91', 'สตูล   ', 'Satun', '6');
INSERT INTO `province` VALUES ('72', '92', 'ตรัง   ', 'Trang', '6');
INSERT INTO `province` VALUES ('73', '93', 'พัทลุง   ', 'Phatthalung', '6');
INSERT INTO `province` VALUES ('74', '94', 'ปัตตานี   ', 'Pattani', '6');
INSERT INTO `province` VALUES ('75', '95', 'ยะลา   ', 'Yala', '6');
INSERT INTO `province` VALUES ('76', '96', 'นราธิวาส   ', 'Narathiwat', '6');
INSERT INTO `province` VALUES ('77', '97', 'บึงกาฬ', 'buogkan', '3');

-- ----------------------------
-- Table structure for `request_mst`
-- ----------------------------
DROP TABLE IF EXISTS `request_mst`;
CREATE TABLE `request_mst` (
  `REQUEST_ID` varchar(20) NOT NULL,
  `REQUEST_TYPE` varchar(1) NOT NULL DEFAULT '',
  `MERCHANT_ID` varchar(20) NOT NULL DEFAULT '',
  `REQUEST_VALUE` varchar(100) NOT NULL DEFAULT '',
  `REQUEST_STATUS` varchar(1) NOT NULL DEFAULT '',
  `REQUEST_DATE` date NOT NULL,
  `REJECT_REASON` varchar(255) DEFAULT NULL,
  `AUTHORIZED_DATE` date DEFAULT NULL,
  `AUTHORIZED_BY` varchar(20) DEFAULT '',
  `CREATED_DATE` date NOT NULL,
  `CREATED_BY` varchar(20) NOT NULL DEFAULT '',
  `LAST_UPDATED_DATE` date NOT NULL,
  `LAST_UPDATED_BY` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`REQUEST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of request_mst
-- ----------------------------
INSERT INTO `request_mst` VALUES ('RE201806071', '1', 'tanikul12', '061', 'A', '2018-06-07', null, '2018-06-07', 'checker', '2018-06-07', 'tanikul12', '2018-06-07', 'tanikul12');
INSERT INTO `request_mst` VALUES ('RE201806072', '1', 'tanikul12', '1111', 'A', '2018-06-07', null, '2018-06-12', 'checker', '2018-06-07', 'tanikul12', '2018-06-07', 'tanikul12');

-- ----------------------------
-- Table structure for `request_sim`
-- ----------------------------
DROP TABLE IF EXISTS `request_sim`;
CREATE TABLE `request_sim` (
  `SIM_NUMBER` varchar(10) NOT NULL,
  `REQUEST_MST_ID` varchar(20) DEFAULT '',
  `REQUEST_STATUS` varchar(1) NOT NULL,
  `AUTHORIZED_DATE` datetime DEFAULT NULL,
  `AUTHORIZED_BY` varchar(20) DEFAULT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `CREATED_BY` varchar(120) NOT NULL DEFAULT '',
  `LAST_UPDATED_DATE` datetime NOT NULL,
  `LAST_UPDATED_BY` varchar(120) NOT NULL DEFAULT '',
  `CREDIT_TERM` varchar(10) DEFAULT '0',
  `RECIEVED_DATE` datetime DEFAULT NULL,
  `PRICE` decimal(10,0) DEFAULT 0,
  `SUM_NUMBER` int(2) DEFAULT NULL,
  PRIMARY KEY (`SIM_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of request_sim
-- ----------------------------
INSERT INTO `request_sim` VALUES ('0867479228', 'RE201806072', 'A', '2018-06-12 17:20:13', 'checker', '2018-06-12 17:20:13', 'checker', '2018-06-12 17:20:13', 'checker', '90', null, '300', '53');
INSERT INTO `request_sim` VALUES ('5555555554', null, 'C', null, null, '2018-05-11 12:13:03', 'tanikul12', '2018-05-11 12:13:51', 'tanikul12', '0', null, '0', '49');
INSERT INTO `request_sim` VALUES ('5555555555', null, 'W', null, null, '2018-05-11 12:02:21', 'tanikul12', '2018-05-11 12:02:21', 'tanikul12', '0', null, '0', '50');
INSERT INTO `request_sim` VALUES ('5555555556', null, 'W', null, null, '2018-05-11 12:25:26', 'tanikul12', '2018-05-11 12:25:26', 'tanikul12', '0', null, '0', '51');
INSERT INTO `request_sim` VALUES ('5555555557', null, 'W', null, null, '2018-05-11 12:42:11', 'tanikul12', '2018-05-11 12:42:11', 'tanikul12', '0', null, '0', '52');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ROLE_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(255) NOT NULL,
  `ACTIVE_STATUS` char(1) NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `CREATED_BY` varchar(120) NOT NULL,
  `LAST_UPDATED_DATE` datetime NOT NULL,
  `LAST_UPDATED_BY` varchar(120) NOT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=334 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('330', 'System Admin', 'Y', '2018-02-26 13:25:39', 'admin', '2018-02-26 13:25:39', 'admin');
INSERT INTO `role` VALUES ('331', 'USER_DEFAULT', 'Y', '2018-02-26 21:11:31', 'admin', '2018-02-26 21:11:31', 'admin');
INSERT INTO `role` VALUES ('332', 'Maker', 'Y', '2018-03-12 17:01:31', 'admin', '2018-03-12 17:01:31', 'admin');
INSERT INTO `role` VALUES ('333', 'Checker', 'Y', '2018-03-12 17:01:56', 'admin', '2018-03-12 17:01:56', 'admin');

-- ----------------------------
-- Table structure for `sim_mst`
-- ----------------------------
DROP TABLE IF EXISTS `sim_mst`;
CREATE TABLE `sim_mst` (
  `SIM_NUMBER` varchar(10) NOT NULL,
  `CREDIT_TERM` varchar(10) DEFAULT '0',
  `RECIEVED_DATE` datetime DEFAULT NULL,
  `PRICE` decimal(10,0) NOT NULL DEFAULT 0,
  `SUM_NUMBER` int(2) DEFAULT NULL,
  `OPERATION_FLAG` varchar(1) DEFAULT NULL,
  `ACTIVE_STATUS` varchar(1) NOT NULL DEFAULT '',
  `BOOKING_FLAG` varchar(1) NOT NULL DEFAULT 'N',
  `REJECT_REASON` text DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(120) DEFAULT '',
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_BY` varchar(120) DEFAULT '',
  `AUTHORIZED_BY` varchar(120) DEFAULT NULL,
  `AUTHORIZED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`SIM_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sim_mst
-- ----------------------------
INSERT INTO `sim_mst` VALUES ('0865479228', '40', null, '300', null, 'N', 'Y', 'Y', null, '2018-06-12 17:37:46', 'tanikul12', '2018-06-12 17:37:46', 'tanikul12', null, null);
INSERT INTO `sim_mst` VALUES ('0867659228', '40', null, '300', null, 'N', 'Y', 'Y', null, '2018-06-12 17:37:46', 'tanikul12', '2018-06-12 17:37:46', 'tanikul12', null, null);
INSERT INTO `sim_mst` VALUES ('0867779217', '90', null, '300', '54', 'E', 'Y', 'Y', null, '2018-05-04 16:29:08', 'admin', '2018-05-14 16:47:40', 'tanikul12', 'checker', '2018-05-04 17:00:23');
INSERT INTO `sim_mst` VALUES ('0867779218', '90', null, '300', '55', 'N', 'Y', 'Y', null, '2018-05-04 16:29:08', 'admin', '2018-06-05 10:10:29', 'tanikul12', 'checker', '2018-05-04 16:42:59');
INSERT INTO `sim_mst` VALUES ('0867779219', '90', null, '300', '56', 'N', 'Y', 'Y', null, '2018-05-04 16:29:08', 'admin', '2018-05-04 17:10:40', 'tanikul12', 'checker', '2018-05-04 16:43:02');
INSERT INTO `sim_mst` VALUES ('0867779220', '90', null, '300', '48', 'N', 'Y', 'Y', null, '2018-05-04 16:29:08', 'admin', '2018-05-04 17:10:40', 'tanikul12', 'checker', '2018-05-04 16:32:18');
INSERT INTO `sim_mst` VALUES ('0867779221', '90', null, '300', '49', 'N', 'Y', 'Y', null, '2018-05-04 16:29:08', 'admin', '2018-06-05 10:10:29', 'tanikul12', 'checker', '2018-05-04 16:32:18');
INSERT INTO `sim_mst` VALUES ('0867779222', '90', null, '300', '50', 'N', 'Y', 'N', null, '2018-05-04 16:29:08', 'admin', '2018-05-04 16:29:08', 'admin', 'checker', '2018-05-04 16:32:18');
INSERT INTO `sim_mst` VALUES ('0867779223', '90', null, '300', '51', 'N', 'Y', 'Y', null, '2018-05-04 16:29:08', 'admin', '2018-05-21 12:03:19', 'tanikul12', 'checker', '2018-05-04 16:32:18');
INSERT INTO `sim_mst` VALUES ('0867779224', '90', null, '300', '52', 'N', 'Y', 'N', null, '2018-05-04 16:29:08', 'admin', '2018-05-04 16:29:08', 'admin', 'checker', '2018-05-04 16:32:18');
INSERT INTO `sim_mst` VALUES ('0867779225', '90', null, '300', '53', 'N', 'Y', 'Y', null, '2018-05-04 16:29:08', 'admin', '2018-06-04 09:16:23', 'tanikul12', 'checker', '2018-05-04 16:32:18');
INSERT INTO `sim_mst` VALUES ('0867779226', '90', null, '300', '54', 'N', 'Y', 'N', null, '2018-05-04 16:29:08', 'admin', '2018-05-04 16:29:08', 'admin', 'checker', '2018-05-04 16:32:18');
INSERT INTO `sim_mst` VALUES ('0867779227', '90', null, '300', '55', 'N', 'Y', 'Y', null, '2018-05-04 16:29:08', 'admin', '2018-05-11 11:32:32', 'tanikul12', 'checker', '2018-05-04 16:32:18');
INSERT INTO `sim_mst` VALUES ('0867779228', '90', null, '300', '56', 'N', 'Y', 'Y', null, '2018-05-04 16:29:08', 'admin', '2018-05-11 11:32:32', 'tanikul12', 'checker', '2018-05-04 16:32:18');
INSERT INTO `sim_mst` VALUES ('0867779229', '90', null, '300', '57', 'N', 'Y', 'N', null, '2018-05-04 16:29:08', 'admin', '2018-05-04 16:29:08', 'admin', 'checker', '2018-05-04 16:32:18');
INSERT INTO `sim_mst` VALUES ('0867779230', '90', null, '300', '49', 'N', 'Y', 'N', null, '2018-05-04 16:29:08', 'admin', '2018-05-04 16:29:08', 'admin', 'checker', '2018-05-04 16:32:18');
INSERT INTO `sim_mst` VALUES ('0867779231', '90', null, '300', '50', 'N', 'Y', 'Y', null, '2018-05-04 16:29:08', 'admin', '2018-05-21 12:03:19', 'tanikul12', 'checker', '2018-05-04 16:32:18');
INSERT INTO `sim_mst` VALUES ('0867779232', '90', null, '300', '51', 'N', 'Y', 'Y', null, '2018-05-04 16:29:08', 'admin', '2018-06-12 16:47:06', 'tanikul12', 'checker', '2018-05-04 16:32:18');
INSERT INTO `sim_mst` VALUES ('0867779233', '90', null, '300', '52', 'N', 'Y', 'N', null, '2018-05-04 16:29:08', 'admin', '2018-05-04 16:29:08', 'admin', 'checker', '2018-05-04 16:32:18');
INSERT INTO `sim_mst` VALUES ('0867779234', '90', null, '300', '53', 'N', 'Y', 'N', null, '2018-05-04 16:29:08', 'admin', '2018-05-04 16:29:08', 'admin', 'checker', '2018-05-04 16:32:18');
INSERT INTO `sim_mst` VALUES ('0867779235', '90', null, '300', '54', 'N', 'Y', 'Y', null, '2018-05-04 16:29:08', 'admin', '2018-05-21 13:32:23', 'tanikul12', 'checker', '2018-05-04 16:32:18');
INSERT INTO `sim_mst` VALUES ('0867779244', '90', null, '300', null, 'N', 'Y', 'Y', null, '2018-06-12 16:54:35', 'tanikul12', '2018-06-12 16:54:35', 'tanikul12', null, null);
INSERT INTO `sim_mst` VALUES ('0899999999', '90', null, '200000', null, 'N', 'Y', 'Y', null, '2018-05-11 11:40:10', 'tanikul12', '2018-05-11 11:40:10', 'tanikul12', null, null);
INSERT INTO `sim_mst` VALUES ('4545454545', '22222', null, '333333333', null, 'N', 'Y', 'Y', null, '2018-05-04 17:36:01', 'tanikul12', '2018-05-04 17:36:01', 'tanikul12', null, null);

-- ----------------------------
-- Table structure for `sim_tmp`
-- ----------------------------
DROP TABLE IF EXISTS `sim_tmp`;
CREATE TABLE `sim_tmp` (
  `SIM_NUMBER` varchar(10) NOT NULL,
  `CREDIT_TERM` varchar(10) DEFAULT '0',
  `RECIEVED_DATE` datetime DEFAULT NULL,
  `PRICE` decimal(10,0) NOT NULL DEFAULT 0,
  `SUM_NUMBER` int(2) DEFAULT NULL,
  `OPERATION_FLAG` varchar(1) DEFAULT NULL,
  `ACTIVE_STATUS` varchar(1) NOT NULL DEFAULT '',
  `BOOKING_FLAG` varchar(1) NOT NULL DEFAULT 'N',
  `REJECT_REASON` text DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(120) DEFAULT NULL,
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_BY` varchar(120) DEFAULT NULL,
  `AUTHORIZED_BY` varchar(120) DEFAULT NULL,
  `AUTHORIZED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`SIM_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sim_tmp
-- ----------------------------
INSERT INTO `sim_tmp` VALUES ('3233388889', '22', null, '3', '55', 'N', 'N', 'N', 'ไม่ขายครับเบอร์นี้', '2018-05-14 11:24:03', 'maker', '2018-05-14 11:24:03', 'maker', 'checker', '2018-05-14 16:22:29');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `USER_ID` varchar(10) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `PREFIX` int(1) NOT NULL,
  `FIRST_NAME` varchar(255) NOT NULL,
  `LAST_NAME` varchar(255) NOT NULL,
  `NICKNAME` varchar(120) DEFAULT NULL,
  `LINE` varchar(255) DEFAULT NULL,
  `WEBSITE` varchar(255) DEFAULT NULL,
  `ADDRESS` text DEFAULT NULL,
  `PROVINCE` int(2) DEFAULT NULL,
  `POSTCODE` varchar(5) DEFAULT NULL,
  `MOBILE` varchar(10) DEFAULT NULL,
  `EMAIL` varchar(100) NOT NULL DEFAULT '',
  `ACTIVATE_EMAIL` varchar(255) DEFAULT NULL,
  `FORGOT_PASSWORD` varchar(255) DEFAULT NULL,
  `ROLE` varchar(10) DEFAULT NULL,
  `USER_TYPE` varchar(10) DEFAULT NULL,
  `TOKEN_ID` varchar(255) DEFAULT NULL,
  `ACTIVE_STATUS` varchar(1) NOT NULL,
  `LAST_LOGGED_ON` datetime DEFAULT NULL,
  `CREATED_BY` varchar(10) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_BY` varchar(10) DEFAULT NULL,
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`USER_ID`,`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '1', 'ธนิกุล', 'สกุลวรวิทย์', null, null, null, '291/283', '1', '10210', '0867779217', 'admin@gmail.com', '', '', '330', 'ADMIN', 'eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjYjRiYTkxMTU4MzU0N2IxOTY2MzI3Mzg1OGMzMmI2YSIsImV4cCI6MTUzMzAyODMzOX0.0g7KUd9fTP6mnZftBSy4TKRdMIy99wE6G5bse8-_3iY', 'Y', null, 'admin', '2018-02-21 21:21:43', 'admin', '2018-05-11 15:58:17');
INSERT INTO `user` VALUES ('checker', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '1', 'อธิวัฒน์', 'สุ่นปาน', null, null, null, null, '0', null, null, 'admin2@gmail.com', '99f02cdbc20c2cb2e45a33fbbf7f509707cf1dc7783f5bd5170bfbf4e6fc8335', null, '333', 'ADMIN', 'eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4YmY0ZjU4ZDk5YmU0ZDhjYTU1MWNjZTZlNDY5MTNlZCIsImV4cCI6MTUyODc5OTY5OH0.a2xzAaB7hn0O8KwEh6WhkfAWEPM9dtV79ckpWe9P0QM', 'Y', null, 'admin', '2018-05-04 16:11:54', 'admin', '2018-05-04 16:11:54');
INSERT INTO `user` VALUES ('maker', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '2', 'วราภรณ์', 'คงสมพงษ์', null, null, null, null, '0', null, null, 'admin3@gmail.com', '5fc25f6f0cb7e73776ffe08d9f879b302d5aa15e1f9308364f2b94977f1ee45d', null, '332', 'ADMIN', 'eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ZDk0ODg4ZGM3M2U0YWQ5OGMxNzg2YTdiZmNhY2JjOCIsImV4cCI6MTUyNjI4OTczNn0.w2MtR3o_OwIb1-zvYadqYD2F3wTiYKtLqSQKuqBoQgU', 'Y', null, 'admin', '2018-05-04 16:16:24', 'admin', '2018-05-04 16:16:24');
INSERT INTO `user` VALUES ('tanikul12', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '1', 'ธนิกุล', 'สกุลวรวิทย์', 'nololay', 'pun.sa', '', '291/283 ม.ศุภาลัย ถ.เทิดราชัน 31 แขวงดอนเมือง เขตดอนเมือง', '1', '10210', '0867779217', 'tanikul.sa@gmail.com', '3e34ca920f68177fd4c869a2858693248229fed4471c6b62352586f08e38f92b', '1097a7b6f5848452115643b64bb556eed6191eadbf6367a9fa09362ccb3a2f8f', '331', 'CUSTOMER', 'eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NmJkY2FmMGIyNWI0MGMyYTExYjZmOGFiMzcwZjMyYyIsImV4cCI6MTUyODg3MDYwMn0.JOQyUfIo49DmVzd16o8JWX8u8k1kcZhDrOISobtY47Y', 'Y', null, 'tanikul12', '2018-05-04 17:08:00', 'admin', '2018-05-11 11:29:18');
