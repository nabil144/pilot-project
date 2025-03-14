USE employee_directory;

-- Drop tables if they exist
IF OBJECT_ID('dbo.authorities', 'U') IS NOT NULL
    DROP TABLE dbo.authorities;

IF OBJECT_ID('dbo.users', 'U') IS NOT NULL
    DROP TABLE dbo.users;

-- Table structure for table 'users'
CREATE TABLE dbo.users (
  [username] VARCHAR(50) NOT NULL,
  [password] VARCHAR(50) NOT NULL,
  [enabled] BIT NOT NULL,
  CONSTRAINT PK_users PRIMARY KEY ([username])
);

-- Inserting data into 'users'
INSERT INTO dbo.users ([username], [password], [enabled])
VALUES
('john', '{noop}test123', 1),
('mary', '{noop}test123', 1),
('susan', '{noop}test123', 1);

-- Table structure for table 'authorities'
CREATE TABLE dbo.authorities (
  [username] VARCHAR(50) NOT NULL,
  [authority] VARCHAR(50) NOT NULL,
  CONSTRAINT authorities_idx_1 UNIQUE ([username], [authority]),
  CONSTRAINT FK_authorities_users FOREIGN KEY ([username]) REFERENCES dbo.users([username])
);

-- Inserting data into 'authorities'
INSERT INTO dbo.authorities ([username], [authority])
VALUES
('john', 'ROLE_EMPLOYEE'),
('mary', 'ROLE_EMPLOYEE'),
('mary', 'ROLE_MANAGER'),
('susan', 'ROLE_EMPLOYEE'),
('susan', 'ROLE_MANAGER'),
('susan', 'ROLE_ADMIN');
