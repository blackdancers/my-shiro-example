
CREATE TABLE `db_shiro`.`tb_user_roles` (
  `id` INT NOT NULL,
  `username` VARCHAR(45) NULL,
  `role_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;