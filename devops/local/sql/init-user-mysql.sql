CREATE USER IF NOT EXISTS 'bbq_user'@'%' IDENTIFIED BY 'qwerty';
GRANT ALL PRIVILEGES ON *.* TO 'bbq_user'@'%' WITH GRANT OPTION;