use DAS2

-- Create Roles table
CREATE TABLE Roles (
    RoleID INT PRIMARY KEY,
    RoleName NVARCHAR(50)
);

-- Create tblUsers table with a foreign key reference to Roles
CREATE TABLE tblUsers (
    UserID INT NOT NULL PRIMARY KEY,
    Name VARCHAR(50),
    Password VARCHAR(50),
    Email VARCHAR(100),
    Phone VARCHAR(20),
    Address NVARCHAR(100),
    CCCD INT,
    RoleID INT,
	Status BIT,
    FOREIGN KEY (RoleID) REFERENCES Roles(RoleID)
);

-- Create Services table
CREATE TABLE Services (
    SID INT NOT NULL PRIMARY KEY,
    ServiceName NVARCHAR(100)
);

-- Create ServicePriceList table with a foreign key reference to Services
CREATE TABLE ServicePriceList (
    PriceListID INT NOT NULL PRIMARY KEY,
    SID INT,
    SizeFrom Decimal(18,3),
    SizeTo Decimal(18,3),
    InitPrice INT,
	PriceUnit INT,
    FOREIGN KEY (SID) REFERENCES Services(SID)

);

-- Create Transactions table with more details
CREATE TABLE Payments(
    PaymentsID INT NOT NULL PRIMARY KEY,
    UserID INT,
    TSDate DATETIME,
    ServiceType NVARCHAR(100),
    TotalAmount INT,
    Status BIT,
    FOREIGN KEY (UserID) REFERENCES tblUsers(UserID)

);

-- Create AssessmentRequests table with foreign key references to tblUsers and Services
CREATE TABLE AssessmentRequests (
    ARequestID INT NOT NULL PRIMARY KEY,
    UserID INT,
    SID INT,
	PaymentsID INT,
    Totalamout INT,
	Status BIT,
    FOREIGN KEY (UserID) REFERENCES tblUsers(UserID),
	FOREIGN KEY (PaymentsID) REFERENCES Payments(PaymentsID),
    FOREIGN KEY (SID) REFERENCES Services(SID)
);

-- Create AssessmentRequestDetail table
CREATE TABLE AssessmentRequestDetail(
    DetailID INT NOT NULL PRIMARY KEY,
	SampleSize Decimal(18,3),
    ARequestID INT,
    isDia BIT,
    UserID INT,
    Price INT,
    AssessmentID INT,
    isCheckIN BIT, 
    byAssessmentID INT,
    Status BIT,
    FOREIGN KEY (ARequestID) REFERENCES AssessmentRequests(ARequestID),
    FOREIGN KEY (UserID) REFERENCES tblUsers(UserID)
	--caculate price: Price = SizePrice+(SampleSize-SizeFrom)xPriceUnit
);

-- Create Assessment table with a foreign key reference to Sample and ensuring 1-1 relationship
CREATE TABLE Assessment (
    AssessmentID INT NOT NULL PRIMARY KEY,
    isDia BIT,
    DetailID INT, 
    Comments NVARCHAR(100),	
    Depth DECIMAL(18,3),
	Tablee DECIMAL(18,3),
	CrowAngle DECIMAL(18,3),
	CrowHeight DECIMAL(18,3),
	PavillionAngle DECIMAL(18,3),
	PavillionDepth DECIMAL(18,3),
	StarLength DECIMAL(18,3),
	LowerHalf DECIMAL(18,3),
	Girdle NVARCHAR(100),
	CaratWeight DECIMAL(18,3),
    ColorGrade NVARCHAR(100),
    ClarityGrade NVARCHAR(100),
    CutGrade NVARCHAR(100),
    Proportions NVARCHAR(100),
    Polish NVARCHAR(100),
    Symmetry NVARCHAR(100),
    Flourescence NVARCHAR(100),
	Measurememt NVARCHAR(100),
	ShapeAndCut NVARCHAR(100),
	Dates DATE,
	Number INT,
    FOREIGN KEY (DetailID) REFERENCES AssessmentRequestDetail(DetailID)
);



-- Create Receipt table with foreign key references to tblUsers, AssessmentRequests, and Transactions
--CREATE TABLE Receipt (
--    ReceiptID INT NOT NULL PRIMARY KEY,
--    UserID INT,
 --   ARequestID INT,
--    ReceiptDate DATETIME,
--    PaymentsID INT,
--    FOREIGN KEY (UserID) REFERENCES tblUsers(UserID),
--    FOREIGN KEY (ARequestID) REFERENCES AssessmentRequests(ARequestID),
--    FOREIGN KEY (PaymentsID) REFERENCES Payments(PaymentsID)
--);
-------------------------------------------------------------------------------------------
-- Insert data into Roles table
INSERT INTO Roles (RoleID, RoleName) VALUES 
(1, N'Admin'),
(2, N'User');

-- Insert data into tblUsers table
INSERT INTO tblUsers (UserID, Name, Password, Email, Phone, Address, CCCD, RoleID, Status) VALUES 
(1, 'Admin', '123', 'john.doe@example.com', '123-456-7890', N'123 Elm Street', 123456789, 1, 1),
(2, 'Jane Smith', '456', 'jane.smith@example.com', '098-765-4321', N'456 Oak Avenue', 987654321, 2, 1),
(3, 'Alice Johnson', '789', 'alice.johnson@example.com', '456-789-0123', N'789 Pine Road', 234567890, 2, 1),
(4, 'Bob Brown', '321', 'bob.brown@example.com', '321-654-0987', N'321 Maple Lane', 345678901, 2, 1),
(5, 'Carol White', '654', 'carol.white@example.com', '654-321-1234', N'654 Birch Boulevard', 456789012, 2, 1);

-- Insert data into Services table
INSERT INTO Services (SID, ServiceName) VALUES 
(1, N'Diamond Assessment');

-- Insert data into ServicePriceList table
INSERT INTO ServicePriceList (PriceListID, SID, SizeFrom, SizeTo, InitPrice, PriceUnit) VALUES 
(1, 1, 0.00, 0.99, 100, 50),
(2, 1, 1.00, 1.99, 150, 75),
(3, 1, 2.00, 2.99, 200, 100),
(4, 1, 3.00, 3.99, 250, 125),
(5, 1, 4.00, 4.99, 300, 150);

-- Insert data into Payments table
INSERT INTO Payments (PaymentsID, UserID, TSDate, ServiceType, TotalAmount, Status) VALUES 
(1, 1, '2023-05-01 10:00:00', N'Diamond Assessment', 150, 1),
(2, 2, '2023-05-02 14:00:00', N'Diamond Assessment', 225, 1),
(3, 3, '2023-05-03 09:30:00', N'Diamond Assessment', 275, 1),
(4, 4, '2023-05-04 11:45:00', N'Diamond Assessment', 325, 1),
(5, 5, '2023-05-05 16:20:00', N'Diamond Assessment', 375, 1);

-- Insert data into AssessmentRequests table
INSERT INTO AssessmentRequests (ARequestID, UserID, SID, PaymentsID, Totalamout, Status) VALUES 
(1, 1, 1, 1, 150, 1),
(2, 2, 1, 2, 225, 1),
(3, 3, 1, 3, 275, 1),
(4, 4, 1, 4, 325, 1),
(5, 5, 1, 5, 375, 1);

-- Insert data into AssessmentRequestDetail table
INSERT INTO AssessmentRequestDetail (DetailID, SampleSize, ARequestID, isDia, UserID, Price, AssessmentID, Status) VALUES 
(1, 1.50, 1, 1, 1, 175, 1, 1),
(2, 2.00, 2, 1, 2, 225, 2, 1),
(3, 2.50, 3, 1, 3, 275, 3, 1),
(4, 3.00, 4, 1, 4, 325, 4, 1),
(5, 3.50, 5, 1, 5, 375, 5, 1);

-- Insert data into Assessment table
INSERT INTO Assessment (AssessmentID, isDia, DetailID, Comments, Depth, Tablee, CrowAngle, CrowHeight, PavillionAngle, PavillionDepth, StarLength, LowerHalf, Girdle, CaratWeight, ColorGrade, ClarityGrade, CutGrade, Proportions, Polish, Symmetry, Flourescence, Measurememt, ShapeAndCut, Dates, Number) VALUES 
(1, 1, 1, N'GIA 1234567890', 60.2, 57.0, 35.0, 15.0, 40.8, 43.0, 50.0, 75.0, N'medium, faceted, 3.5%', 1.50, N'D', N'VVS1', N'Excellent', N'Excellent', N'Excellent', N'Excellent', N'None', N'5.50 - 6.88 x 3.44 mm', N'Round Brilliant', '2023-05-01', 1),
(2, 1, 2, N'GIA 0987654321', 61.0, 58.0, 34.5, 14.5, 41.0, 44.0, 51.0, 76.0, N'medium, faceted, 3.5%', 2.00, N'E', N'VS1', N'Very Good', N'Very Good', N'Very Good', N'Very Good', N'Faint', N'5.55 - 6.90 x 3.50 mm', N'Princess Cut', '2023-05-02', 2),
(3, 1, 3, N'GIA 1122334455', 60.5, 57.5, 34.8, 14.8, 40.9, 43.5, 50.5, 75.5, N'medium, faceted, 3.5%', 2.50, N'F', N'SI1', N'Good', N'Good', N'Good', N'Good', N'Faint', N'5.60 - 6.92 x 3.56 mm', N'Cushion Cut', '2023-05-03', 3),
(4, 1, 4, N'GIA 2233445566', 61.2, 58.2, 35.2, 15.2, 41.1, 44.2, 51.2, 76.2, N'medium, faceted, 3.5%', 3.00, N'G', N'SI2', N'Good', N'Good', N'Good', N'Good', N'Medium', N'5.65 - 6.94 x 3.62 mm', N'Oval Cut', '2023-05-04', 4),
(5, 1, 5, N'GIA 5566778899', 60.8, 57.8, 34.9, 14.9, 40.7, 43.8, 50.8, 75.8, N'medium, faceted, 3.5%', 3.50, N'H', N'I1', N'Fair', N'Fair', N'Fair', N'Fair', N'Strong', N'5.70 - 6.96 x 3.68 mm', N'Marquise Cut', '2023-05-05', 5);

