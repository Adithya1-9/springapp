-- SQL script to create ShowManagement database and grant privileges to root
-- Run as a MySQL user with CREATE/GRANT privileges (e.g., root)

CREATE DATABASE IF NOT EXISTS `ShowManagement` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- If you want to (re)create a dedicated user instead of using root, uncomment and adjust below:
-- CREATE USER 'showuser'@'localhost' IDENTIFIED BY 'strong_password';
-- GRANT ALL PRIVILEGES ON `ShowManagement`.* TO 'showuser'@'localhost';
-- FLUSH PRIVILEGES;

-- If using root (already typically has privileges), no grant is necessary.

-- Optional: create a table example
-- USE `ShowManagement`;
-- CREATE TABLE IF NOT EXISTS shows (
--   id BIGINT AUTO_INCREMENT PRIMARY KEY,
--   title VARCHAR(255) NOT NULL,
--   description TEXT,
--   release_date DATE,
--   rating DECIMAL(2,1)
-- );
