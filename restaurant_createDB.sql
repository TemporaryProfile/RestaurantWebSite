DROP DATABASE IF EXISTS restaurantDB;

CREATE DATABASE restaurantDB;

USE restaurantDB;

CREATE TABLE UserPass (
  Username varchar(15) NOT NULL PRIMARY KEY,
  Password varchar(15) NOT NULL
);
  
INSERT INTO UserPass 
VALUES ('alex', 'alex');

CREATE TABLE UserRole (   
  Username VARCHAR(15) NOT NULL,
  Rolename VARCHAR(15) NOT NULL,

  PRIMARY KEY (Username, Rolename)
);
  
INSERT INTO UserRole 
VALUES ('alex', 'programmer');

CREATE TABLE Dish (
	DishID INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR(50),
    Calories BIGINT NOT NULL DEFAULT 0,
    Type ENUM ('MEET', 'FISH', 'NONE') NOT NULL DEFAULT 'NONE',
    IsVegeterian ENUM ('YES', 'NO') NOT NULL DEFAULT 'NO',
	Price DECIMAL(4, 2) NOT NULL DEFAULT 0.00,
    
    PRIMARY KEY (DishID)
);

INSERT INTO Dish 
	(Name, Calories, Type, IsVegeterian, Price)
VALUES
	('Fried Chicken', 1000, 'MEET', 'NO', 10.00),
    ('French Fries', 150, 'NONE' , 'YES', 8.00),
    ('Boiled Shark', 350, 'FISH', 'YES', 35.00);


CREATE TABLE User (
	UserID INT NOT NULL AUTO_INCREMENT,
    Email VARCHAR(30) NOT NULL,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    IsSubscribed enum ('y', 'n') not null default 'n',
    
    PRIMARY KEY(UserID)
);

INSERT INTO USER (Email, FirstName, LastName, IsSubscribed) 
VALUES 
	('sarzamastsev5@gmail.com', 'Alex', 'Arzamastsev', 'y');
    

CREATE TABLE Invoice(
    InvoiceID INT NOT NULL AUTO_INCREMENT,
    UserID INT NOT NULL,
    TotalAmount DECIMAL(7,2) NOT NULL DEFAULT '0.00',
    InvoiceDate DATETIME NOT NULL DEFAULT '2019-01-01 00:00:00',
    IsProcessed enum('YES','NO') NOT NULL DEFAULT 'NO',
  
    PRIMARY KEY (InvoiceID),
    FOREIGN KEY (UserID) REFERENCES User (UserID)
);

insert into Invoice
	(userId, totalAmount, isProcessed)
values
	(1, 10.00, 'yes');

create table CartItem (
	CartItemID int not null auto_increment,
    InvoiceID int not null,
    DishID int not null default 0,
    Quantity int not null default 0,
    
    primary key (CartItemID),
    foreign key (InvoiceID) references Invoice (InvoiceID)
);

insert into cartItem 
	(invoiceId, dishId, quantity)
values 
	(1, 2, 30);

-- Create restaurant_user and grant privileges --
DELIMITER //
CREATE PROCEDURE drop_user_if_exists()
BEGIN
    DECLARE userCount BIGINT DEFAULT 0 ;

    SELECT COUNT(*) INTO userCount FROM mysql.user
    WHERE User = 'restaurant_user' and  Host = 'localhost';

    IF userCount > 0 THEN
        DROP USER restaurant_user@localhost;
    END IF;
END ; //
DELIMITER ;

CALL drop_user_if_exists();

CREATE USER restaurant_user@localhost IDENTIFIED BY 'sesame';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP
ON restaurantDB.*
TO restaurant_user@localhost;

USE restaurantDB;