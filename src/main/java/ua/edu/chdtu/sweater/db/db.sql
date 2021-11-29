DROP SCHEMA IF EXISTS `web-task4`;
CREATE SCHEMA IF NOT EXISTS `web-task4`;

CREATE TABLE IF NOT EXISTS `web-task4`.users
(
    id          INT           NOT NULL  AUTO_INCREMENT,
    full_name   VARCHAR(100)  NOT NULL,
    email       VARCHAR(50)   NOT NULL,
    phone       VARCHAR(20)   NOT NULL,
    job         VARCHAR(20)   NOT NULL,
    password    INT		      NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
)ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `web-task4`.users VALUES (DEFAULT, 'admin full name', 'admin', '+380 93 111 11 11', 'Administration', '48690');
INSERT INTO `web-task4`.users VALUES (DEFAULT, 'design full name', 'design@task4.edu', '+380 93 222 22 22', 'Design', '48690');

