-- Category (category_id, category_desc)
-- Sub_Category (sub_category_id, category_id , sub_category_desc)
-- Admin_team (admin_id, name, emailId)
-- User (user_id, name, emailId)


DROP TABLE IF EXISTS sub_category;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS ticket;
DROP TABLE IF EXISTS admin_seq;
DROP TABLE IF EXISTS user_seq;
DROP TABLE IF EXISTS ticket_seq;
DROP TABLE IF EXISTS comment_genrator;
DROP TABLE IF EXISTS admin_team;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS priority;


----Category Table
CREATE TABLE category(
  category_id INT(11) NOT NULL,
  category_desc VARCHAR(50) NOT NULL,
  PRIMARY KEY (category_id)
);

----Sub_Category Table
CREATE TABLE sub_category(
  sub_category_id INT NOT NULL,
  sub_category_desc VARCHAR(50) NOT NULL,
  category_id INT,
  PRIMARY KEY (sub_category_id),
  CONSTRAINT FK_CategoryID FOREIGN KEY (category_id)
  REFERENCES category(category_id) 
);

--Admin_team Table
CREATE TABLE admin_team(
  admin_id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  email MEDIUMTEXT NOT NULL,
  PRIMARY KEY (admin_id)
);

----Users Table
CREATE TABLE users(
  user_id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  email MEDIUMTEXT NOT NULL,
  PRIMARY KEY (user_id)
);

----Status Table
CREATE TABLE status(
  status_id INT(11) NOT NULL,
  status_desc VARCHAR(200) NOT NULL,
  PRIMARY KEY (status_id)
);

----Priority Table
CREATE TABLE priority(
  priority_id INT(11) NOT NULL,
  priority_desc VARCHAR(50) NOT NULL,
  PRIMARY KEY (priority_id)
);
