-- Step 1: Create the database if it doesn't exist
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'hb_04_one_to_many_uni')
BEGIN
    CREATE DATABASE hb_04_one_to_many_uni;
END;

-- Step 2: Use the newly created database
USE hb_04_one_to_many_uni;

-- Step 3: Create the schema
IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'hb_04_one_to_many_uni')
BEGIN
    -- Schema creation (no need to reference schema in table creation)
    EXEC('CREATE SCHEMA hb_04_one_to_many_uni');
END;

-- Step 4: Create the tables under the schema
-- Create `instructor_detail` table
CREATE TABLE hb_04_one_to_many_uni.instructor_detail (
    id INT IDENTITY(1,1) PRIMARY KEY, -- Equivalent to AUTO_INCREMENT
    youtube_channel VARCHAR(128) NULL,
    hobby VARCHAR(45) NULL
);

-- Create `instructor` table
CREATE TABLE hb_04_one_to_many_uni.instructor (
    id INT IDENTITY(1,1) PRIMARY KEY, -- Equivalent to AUTO_INCREMENT
    first_name VARCHAR(45) NULL,
    last_name VARCHAR(45) NULL,
    email VARCHAR(45) NULL,
    instructor_detail_id INT NULL,
    CONSTRAINT FK_DETAIL FOREIGN KEY (instructor_detail_id) 
        REFERENCES hb_04_one_to_many_uni.instructor_detail (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Create `course` table
CREATE TABLE hb_04_one_to_many_uni.course (
    id INT IDENTITY(1,1) PRIMARY KEY, -- Equivalent to AUTO_INCREMENT
    title VARCHAR(128) NULL,
    instructor_id INT NULL,
    CONSTRAINT TITLE_UNIQUE UNIQUE (title),
    CONSTRAINT FK_INSTRUCTOR FOREIGN KEY (instructor_id) 
        REFERENCES hb_04_one_to_many_uni.instructor (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Create `review` table
CREATE TABLE hb_04_one_to_many_uni.review (
    id INT IDENTITY(1,1) PRIMARY KEY, -- Equivalent to AUTO_INCREMENT
    comment VARCHAR(256) NULL,
    course_id INT NULL,
    CONSTRAINT FK_COURSE FOREIGN KEY (course_id) 
        REFERENCES hb_04_one_to_many_uni.course (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);
