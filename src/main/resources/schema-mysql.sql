CREATE table IF NOT EXISTS `register`(
  `account` VARCHAR(20) NOT NULL,
  `pwd` VARCHAR(45) NOT NULL,
  `reg_time` DATETIME  DEFAULT null,
  `active` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`account`)
 );