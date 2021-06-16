-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='';

-- -----------------------------------------------------
-- Schema test
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `test` ;

-- -----------------------------------------------------
-- Schema test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `test` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `test` ;

-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `users` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `users` (
                                       `id` INT NOT NULL AUTO_INCREMENT,
                                       `login` VARCHAR(45) NOT NULL,
                                       `password` VARCHAR(45) NOT NULL,
                                       `role` VARCHAR(45) NULL,
                                       `block` TINYINT NULL,
                                       `name` VARCHAR(45) NULL,
                                       `surname` VARCHAR(45) NULL,
                                       `patronymic` VARCHAR(45) NULL,
                                       PRIMARY KEY (`id`))
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `test`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `test` (
                                      `id` INT NOT NULL AUTO_INCREMENT,
                                      `name` VARCHAR(45) NOT NULL,
                                      `subdgect` VARCHAR(45) NOT NULL,
                                      `hardnest` INT NOT NULL,
                                      `time` INT NOT NULL,
                                      PRIMARY KEY (`id`))
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `question` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `question` (
                                          `id` INT NOT NULL AUTO_INCREMENT,
                                          `idtest` INT NOT NULL,
                                          `text` VARCHAR(45) NOT NULL,
                                          `number` INT NOT NULL,
                                          PRIMARY KEY (`id`))
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `answers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `answers` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `answers` (
                                         `id` INT NOT NULL AUTO_INCREMENT,
                                         `qutions_idqutions` INT NOT NULL,
                                         `anser` VARCHAR(45) NOT NULL,
                                         `corect` TINYINT NOT NULL,
                                         PRIMARY KEY (`id`))
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `results`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `results` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `results` (
                                         `id` INT NOT NULL AUTO_INCREMENT,
                                         `mark` INT NOT NULL,
                                         `users_id` INT NOT NULL,
                                         `testname` VARCHAR(45) NULL,
                                         PRIMARY KEY (`id`))
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Data for table `users`
-- -----------------------------------------------------
START TRANSACTION;
USE `test`;
INSERT INTO `users` (`id`, `login`, `password`, `role`, `block`, `name`, `surname`, `patronymic`) VALUES (1, 'admin', 'pass', 'teacher', NULL, 'Иван', 'Иванов', 'Ивановичь');
INSERT INTO `users` (`id`, `login`, `password`, `role`, `block`, `name`, `surname`, `patronymic`) VALUES (2, 'stud', 'pass', 'student', NULL, 'Петр', 'Петров', 'Петровичь');

COMMIT;


-- -----------------------------------------------------
-- Data for table `test`
-- -----------------------------------------------------
START TRANSACTION;
USE `test`;
INSERT INTO `test` (`id`, `name`, `subdgect`, `hardnest`, `time`) VALUES (1, 'тест', 'тест', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `question`
-- -----------------------------------------------------
START TRANSACTION;
USE `test`;
INSERT INTO `question` (`id`, `idtest`, `text`, `number`) VALUES (1, 1, 'тест работает?', 1);
INSERT INTO `question` (`id`, `idtest`, `text`, `number`) VALUES (2, 1, 'переход работает?', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `answers`
-- -----------------------------------------------------
START TRANSACTION;
USE `test`;
INSERT INTO `answers` (`id`, `qutions_idqutions`, `anser`, `corect`) VALUES (1, 1, 'да', true);
INSERT INTO `answers` (`id`, `qutions_idqutions`, `anser`, `corect`) VALUES (2, 1, 'нет', false);
INSERT INTO `answers` (`id`, `qutions_idqutions`, `anser`, `corect`) VALUES (3, 2, 'да', true);
INSERT INTO `answers` (`id`, `qutions_idqutions`, `anser`, `corect`) VALUES (4, 2, 'нет', false);

COMMIT;


-- -----------------------------------------------------
-- Data for table `results`
-- -----------------------------------------------------
START TRANSACTION;
USE `test`;
INSERT INTO `results` (`id`, `mark`, `users_id`, `testname`) VALUES (1, 100, 2, 'тест');

COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
