
use testjdbc;


DELIMITER $$
CREATE PROCEDURE get_user_by_id(IN user_id INT)
BEGIN
    SELECT users.name, users.email, users.country
    FROM users
    where users.id = user_id;
    END$$
DELIMITER ;




DELIMITER $$
CREATE PROCEDURE insert_user(
    IN user_name varchar(50),
    IN user_email varchar(50),
    IN user_country varchar(50)
)
BEGIN
    INSERT INTO users(name, email, country) VALUES(user_name, user_email, user_country);
    END$$
DELIMITER ;




DELIMITER $$
CREATE PROCEDURE delete_by_id (
    IN id_users bigint
)
BEGIN
    DELETE FROM users WHERE id = id_users;
END $$
DELIMITER ;



DELIMITER $$
CREATE PROCEDURE update_users (
    IN name_users varchar(100),
    IN email_users varchar(100),
    IN country_users VARCHAR(100),
    IN id_users bigint
)
UPDATE users SET name = name_users, email = email_users, country = country_users WHERE id = id_users;
DELIMITER ;

