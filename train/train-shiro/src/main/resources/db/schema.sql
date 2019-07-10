drop table member if exists;

-- ----------------------------
-- Table structure for manager
-- ----------------------------
create table member(
	id smallint primary key auto_increment,
	username varchar(40),
	password varchar(40)
);



