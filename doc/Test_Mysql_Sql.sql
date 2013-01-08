-- 包含所有mysql 数据类型

drop table if EXISTS `table_fk1` ; 
drop table if EXISTS `table_fk2` ; 
drop table if EXISTS `table_fk3` ; 
drop table if EXISTS `table_fk4` ; 
drop table if EXISTS `table` ; 
CREATE TABLE `table` (
	`id1` bigint(10) not null AUTO_INCREMENT  comment ' 第一主键',
	`id2` bigint  not null  comment ' 第二主键',
	`field_a` bit null comment 'comment  test  bit entity:userName entityName:user',
	`field_b` tinyint NOT null default 1 comment ' comment  test  bit',
	`field_c` smallint null comment ' comment  test  smallint',
	`field_d` mediumint null comment ' comment  test  mediumint',
	`field_e` int null comment ' comment  test  int',
	`field_f` integer null comment ' comment  test  integer',
	`field_g` bigint null comment ' comment  test  bigint',
	`field_i` real(10,5) NOT null comment ' comment  test  real',
	`field_j` float null comment ' comment  test  float',
	`field_k` decimal(20,5) null default 1.1 comment ' comment  test  decimal',
	`field_l` numeric null comment ' comment  test  numeric',
	`field_m` double null comment ' comment  test  double',
	`field_n` char(6) null comment ' comment  test  char',
	`field_o` varchar(64) null comment ' comment  test  varchar',
	`field_p` date null comment ' comment  test  date',
	`field_q` time null comment ' comment  test  time',
	`field_r` year null comment ' comment  test  year',
	`field_s` timestamp null comment ' comment  test  timestamp',
	`field_t` datetime null comment ' comment  test  datetime',
	`field_u` tinyblob null comment ' comment  test  tinyblob',
	`field_v` blob null comment ' comment  test  blob',
	`field_w` mediumblob null comment ' comment  test  mediumblob',
	`field_x` longblob null comment ' comment  test  longblob',
	`field_y` tinytext null comment ' comment  test  tinytext',
	`field_z` text null comment ' comment  test  text',
	`field_0a` mediumtext null comment ' comment  test  mediumtext',
	`field_0b` longtext null comment ' comment  test  longtext',
	`field_0c` enum('1','2','3') null comment ' comment  test  enum',
	`field_0d` set('A','B','C') null comment ' comment  test  set',
	`field_0e` geometry null comment ' comment  test  geometry',
   PRIMARY KEY (`id1`,`id2`),
   UNIQUE KEY `field_b` (`field_m`) USING BTREE,
   UNIQUE KEY `field_m` (`field_m`) USING BTREE,
   UNIQUE KEY `field_i` (`field_i`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table if EXISTS `table_fk1` ; 
CREATE TABLE `table_fk1` (
  `id` bigint(20) DEFAULT NULL,
  `tableid` bigint(20) NOT NULL,
  `field_a1` varchar(255) DEFAULT NULL,
  `field_a2` tinyint(4) DEFAULT NULL,
  PRIMARY KEY `id` (`id`),
  CONSTRAINT  FOREIGN KEY (`id`) REFERENCES `table` (`id1`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


drop table if EXISTS `table_fk2` ; 
CREATE TABLE `table_fk2` (
  `id` bigint(20) DEFAULT NULL,
  `tableid` bigint(20) NOT NULL,
  `field_b1` varchar(64) DEFAULT NULL,
  `field_b2` date DEFAULT NULL,
  PRIMARY KEY `id` (`id`),
  CONSTRAINT  FOREIGN KEY (`id`) REFERENCES `table` (`id1`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

drop table if EXISTS `table_fk3` ; 
CREATE TABLE `table_fk3` (
  `id` bigint(20) DEFAULT NULL,
  `fk3` bigint(20) NOT NULL,
  `field_c1` varchar(255) DEFAULT NULL,
  `field_c2` date DEFAULT NULL,
  PRIMARY KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `table` ADD  INDEX (`id2`) ;
ALTER TABLE `table_fk3` ADD FOREIGN KEY (`fk3`) REFERENCES `table` (`id2`);




drop table if EXISTS `table_fk4` ; 
CREATE TABLE `table_fk4` (
  `id` bigint(20) DEFAULT NULL,
  `fk4` bigint(20) NOT NULL,
  `field_d1` varchar(255) DEFAULT NULL,
  `field_d2` date DEFAULT NULL,
  PRIMARY KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `table` ADD  INDEX (`field_g`) ;
ALTER TABLE `table_fk4` ADD FOREIGN KEY (`fk4`) REFERENCES `table` (`field_g`);




drop PROCEDURE if EXISTS NewProc;
CREATE DEFINER = CURRENT_USER PROCEDURE `NewProc`(
	IN `parm_a` bit ,
	IN `parm_b` tinyint,
	IN `parm_c` smallint,
	IN `parm_d` mediumint,
	IN `parm_e` int,
	IN `parm_f` integer,
	IN `parm_g` bigint,
	IN `parm_i` real(12,2),
	IN `parm_j` float,
	IN `parm_k` decimal(20,5),
	IN `parm_l` numeric,
	IN `parm_m` double,
	IN `parm_n` char,
	IN `parm_o` varchar(43),
	IN `parm_p` date,
	IN `parm_q` time,
	IN `parm_r` year,
	IN `parm_s` timestamp,
	IN `parm_t` datetime,
	IN `parm_u` tinyblob,
	IN `parm_v` blob,
	IN `parm_w` mediumblob,
	IN `parm_x` longblob,
	IN `parm_y` tinytext,
	IN `parm_z` text,
	IN `parm_0a` mediumtext,
	IN `parm_0b` longtext,
	IN `parm_0c` enum('1','2','3','4','5','6','7','8','9','0'),
	IN `parm_0d` set('A','B','C','D','E','F','G','H','I','J'),
	IN `parm_0e` geometry,
	out `parm_0f` longtext,
	out `parm_0g` date,
    inout `parm_0h` year
)
BEGIN
 #Routine body goes here...

END;


