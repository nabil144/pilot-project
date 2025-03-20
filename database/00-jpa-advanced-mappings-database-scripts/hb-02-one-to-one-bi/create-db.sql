-- Step 1: Create Schema if it does not exist
IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'hb-01-one-to-one-uni')
BEGIN
    EXEC('CREATE SCHEMA [hb-01-one-to-one-uni]');
END
GO

-- Step 2: Disable Foreign Key Checks Temporarily
EXEC sp_MSforeachtable "ALTER TABLE ? NOCHECK CONSTRAINT ALL";
GO

-- Step 3: Create instructor_detail Table
CREATE TABLE [hb-01-one-to-one-uni].[instructor_detail] (
    id INT IDENTITY(1,1) PRIMARY KEY, -- Auto-increment using Identity
    youtube_channel VARCHAR(128) NULL,
    hobby VARCHAR(45) NULL
);
GO

-- Step 4: Create instructor Table
CREATE TABLE [hb-01-one-to-one-uni].[instructor] (
    id INT IDENTITY(1,1) PRIMARY KEY, -- Auto-increment using Identity
    first_name VARCHAR(45) NULL,
    last_name VARCHAR(45) NULL,
    email VARCHAR(45) NULL,
    instructor_detail_id INT NULL,
    CONSTRAINT FK_DETAIL FOREIGN KEY (instructor_detail_id)
        REFERENCES [hb-01-one-to-one-uni].[instructor_detail](id)
);
GO

-- Step 5: Re-enable Foreign Key Checks
EXEC sp_MSforeachtable "ALTER TABLE ? WITH CHECK CHECK CONSTRAINT ALL";
GO
