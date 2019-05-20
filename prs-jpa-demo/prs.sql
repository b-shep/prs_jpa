DROP DATABASE IF EXISTS prs;
CREATE DATABASE prs;
USE prs;


CREATE TABLE User(
	ID				INT				NOT NULL 		AUTO_INCREMENT		PRIMARY KEY,
    UserName		NVARCHAR(25)	NOT NULL		UNIQUE,
    Password		NVARCHAR(25)	NOT NULL,
    FirstName		NVARCHAR(25)	NOT NULL,
    LastName		NVARCHAR(25)	NOT NULL,
    PhoneNumber		NVARCHAR(12)	NOT NULL,
    Email			NVARCHAR(50)	NOT NULL		UNIQUE,
    IsReviewer 		TINYINT(1)		NOT NULL,
	IsAdmin			TINYINT(1)		NOT NULL		DEFAULT 1,
	IsActive 		TINYINT(1) 		NOT NULL		DEFAULT 1,
	DateCreated 	DATETIME 		NOT NULL 		DEFAULT CURRENT_TIMESTAMP,
	DateUpdated 	DATETIME		NOT NULL 		DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	UpdatedByUser 	INT				NOT NULL 		DEFAULT 1,
    CONSTRAINT user_info UNIQUE (UserName, Email)
);
    
CREATE TABLE Vendor(
	ID				INT				NOT NULL 		AUTO_INCREMENT		PRIMARY KEY,
    Code 			VARCHAR(10)		NOT NULL,
	Name 			VARCHAR(255)	NOT NULL,
	Address 		VARCHAR(255)	NOT NULL,
	City 			VARCHAR(255)	NOT NULL,
	State 			VARCHAR(2)		NOT NULL,
	Zip 			VARCHAR(5)		NOT NULL,
	PhoneNumber 	VARCHAR(12)		NOT NULL,
	Email 			VARCHAR(100)	NOT NULL,
	IsPreApproved 	TINYINT(1)		NOT NULL,
	IsActive 		TINYINT(1) 		NOT NULL		DEFAULT 1,
	DateCreated 	DATETIME 		NOT NULL 		DEFAULT CURRENT_TIMESTAMP,
	DateUpdated 	DATETIME		NOT NULL 		DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	UpdatedByUser 	INT				NOT NULL 		DEFAULT 1
);

CREATE TABLE Product(
	ID				INT				NOT NULL 		AUTO_INCREMENT		PRIMARY KEY,
    VendorID 		INT				NOT NULL,
	PartNumber 		VARCHAR(50)		NOT NULL,
	Name 			VARCHAR(150)	NOT NULL,
	Price 			DECIMAL(10,2)	NOT NULL,
	Unit 			VARCHAR(255),
	PhotoPath 		VARCHAR(255),
	IsActive 		TINYINT(1) 		NOT NULL		DEFAULT 1,
	DateCreated 	DATETIME 		NOT NULL 		DEFAULT CURRENT_TIMESTAMP,
	DateUpdated 	DATETIME		NOT NULL 		DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	UpdatedByUser 	INT				NOT NULL 		DEFAULT 1,
    
    FOREIGN KEY(VendorID) REFERENCES Vendor(ID),
	CONSTRAINT vendor_part UNIQUE (VendorID, PartNumber)
);

CREATE TABLE PurchaseRequest(
	ID				INT				NOT NULL 		AUTO_INCREMENT		PRIMARY KEY,
    UserID 			INT				NOT NULL,
	Description 	VARCHAR(100)	NOT NULL,
	Justification 	VARCHAR(255)	NOT NULL,
	DateNeeded 		DATE			NOT NULL,
	DeliveryMode 	VARCHAR(25)		NOT NULL,
	Status 			VARCHAR(20)		NOT NULL		DEFAULT 'New',
	Total 			DECIMAL(10,2)	NOT NULL,
	SubmittedDate 	DATETIME		NOT NULL,
	ReasonForRejection VARCHAR(100),
	IsActive 		TINYINT(1) 		NOT NULL		DEFAULT 1,
	DateCreated 	DATETIME 		NOT NULL 		DEFAULT CURRENT_TIMESTAMP,
	DateUpdated 	DATETIME		NOT NULL 		DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	UpdatedByUser 	INT				NOT NULL 		DEFAULT 1,
    
    FOREIGN KEY (UserID) REFERENCES User(ID)
);

CREATE TABLE PurchaseRequestLineItem(
	ID				INT				NOT NULL 		AUTO_INCREMENT		PRIMARY KEY,
    PurchaseRequestID INT			NOT NULL,
	ProductID 		INT				NOT NULL,
	Quantity 		INT				NOT NULL,
	IsActive 		TINYINT(1) 		NOT NULL		DEFAULT 1,
	DateCreated 	DATETIME 		NOT NULL 		DEFAULT CURRENT_TIMESTAMP,
	DateUpdated 	DATETIME		NOT NULL 		DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	UpdatedByUser 	INT				NOT NULL 		DEFAULT 1,
    
    FOREIGN KEY (PurchaseRequestID) REFERENCES PurchaseRequest(ID),
    FOREIGN KEY (ProductID) REFERENCES Product(ID),
	CONSTRAINT req_pdt UNIQUE (PurchaseRequestID, ProductID)
);


-- USERS
INSERT INTO user (ID, UserName, Password, FirstName, LastName, PhoneNumber, Email, IsReviewer, IsAdmin) 
	VALUES 
		(1,'SYSTEM','xxxxx','System','System','XXX-XXX-XXXX','system@test.com',1,1),
		(2,'sblessing','login','Sean','Blessing','513-600-7096','sean@blessingtechnology.com',1,1),
        (3, 'bshep', 'password', 'Ben', 'Shepherd', '207-322-1753', 'ben1shep1herd@gmail.com', 1,1),
        (4, 'guwop', 'imightbe', 'Gucci', 'Mane', '231-434-5312', 'schucci@gmail.com', 0,0),
        (5, 'thugga', 'hercules', 'Young', 'Thug', '432-523-9034', 'jeffery@gmail.com', 0, 0),
		(6, 'bluemnm', 'leanleanlean', 'Peewee', 'Longway', '424-345-6543', 'bluebenjamin@gmail.com', 0,0);

-- VENDORS
INSERT INTO `vendor` (ID, Code, Name, Address, City, State, Zip, PhoneNumber, Email, isPreApproved) 
	VALUES 
		(1, 'BB-1001','Best Buy','100 Best Buy Street','Louisville','KY','40207','502-111-9099','geeksquad@bestbuy.com',1),
		(2, 'AP-1001','Apple Inc','1 Infinite Loop','Cupertino','CA','95014','800-123-4567','genius@apple.com',1),
		(3, 'AM-1001','Amazon','410 Terry Ave. North','Seattle','WA','98109','206-266-1000','amazon@amazon.com',0),
		(4, 'ST-1001','Staples','9550 Mason Montgomery Rd','Mason','OH','45040','513-754-0235','support@orders.staples.com',0),
		(5, 'MC-1001','Micro Center','11755 Mosteller Rd','Sharonville','OH','45241','513-782-8500','support@microcenter.com',0),
        (6, 'LI-1001', 'Linneman Funeral Home', '30 Commonwealth Ave', 'Erlanger', 'KY', '41018', '859-727-1250', 'linneman@gmail.com',1),
        (7, 'WA-1001', 'Walker Funeral Home', '7825 Hamilton Avenue', 'Cincinnati', 'OH', '49305', '513-521-2434', 'walker@gmail.com',0),
		(8, 'AR-1001', 'Arlington Memorial Gardens Incorporated', '2145 Compton Rd', 'Cincinnati', 'OH', '45231', '513-234-0908', 'arl@gmail.com', 1);

-- PRODUCTS
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (1,1,'ME280LL','iPad Mini 2',296.99,NULL,NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (2,2,'ME280LL','iPad Mini 2',299.99,NULL,NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (3,3,'105810','Hammermill Paper, Premium Multi-Purpose Paper Poly Wrap',8.99,'1 Ream / 500 Sheets',NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (4,4,'122374','HammerMill® Copy Plus Copy Paper, 8 1/2\" x 11\", Case',29.99,'1 Case, 10 Reams, 500 Sheets per ream',NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (5,4,'784551','Logitech M325 Wireless Optical Mouse, Ambidextrous, Black ',14.99,NULL,NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (6,4,'382955','Staples Mouse Pad, Black',2.99,NULL,NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (7,4,'2122178','AOC 24-Inch Class LED Monitor',99.99,NULL,NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (8,4,'2460649','Laptop HP Notebook 15-AY163NR',529.99,NULL,NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (9,4,'2256788','Laptop Dell i3552-3240BLK 15.6\"',239.99,NULL,NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (10,4,'IM12M9520','Laptop Acer Acer™ Aspire One Cloudbook 14\"',224.99,NULL,NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (11,4,'940600','Canon imageCLASS Copier (D530)',99.99,NULL,NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (12,5,'228148','Acer Aspire ATC-780A-UR12 Desktop Computer',399.99, NULL,'/images/AcerAspireDesktop.jpg');
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (13,5,'279364','Lenovo IdeaCentre All-In-One Desktop',349.99, NULL,'/images/LenovoIdeaCenter.jpg');

INSERT INTO Product(ID, VendorID, PartNumber, Name, Price, Unit, PhotoPath)
	VALUES
		(14, 7, '12342', 'Lords Prayer Antique White Finish with White Interior', 1199.99, NULL, NULL),
        (15, 8, '3453', 'Brush Gold with Light Gold Finish Almond Velvet Interior', 4499.99, NULL, NULL),
        (16, 6, '24551', 'Praying Hands Stainless Steel Monarch Blue Finish With Blue Velvet Interior', 1649.99, NULL, NULL);
        
-- create a user and grant privileges to that user
CREATE USER 'prs_user'@'localhost' IDENTIFIED BY 'sesame';
GRANT SELECT, INSERT, DELETE, UPDATE ON prs.* TO 'prs_user'@'localhost';
FLUSH privileges;