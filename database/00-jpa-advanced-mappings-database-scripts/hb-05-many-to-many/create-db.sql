-- Step 1: Create the database if it doesn't exist
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'hb_05_many_to_many')
BEGIN
    CREATE DATABASE hb_05_many_to_many;
END;

-- Step 2: Use the newly created database
USE hb_05_many_to_many;

-- Step 3: Create the schema
IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'hb_05_many_to_many')
BEGIN
    EXEC('CREATE SCHEMA hb_05_many_to_many');
END;

-- Step 4: Create tables

-- Create `instructor_detail` table
CREATE TABLE hb_05_many_to_many.instructor_detail (
    id INT IDENTITY(1,1) PRIMARY KEY,  -- Equivalent to AUTO_INCREMENT
    youtube_channel VARCHAR(128) NULL,
    hobby VARCHAR(45) NULL
);

-- Create `instructor` table
CREATE TABLE hb_05_many_to_many.instructor (
    id INT IDENTITY(1,1) PRIMARY KEY,  -- Equivalent to AUTO_INCREMENT
    first_name VARCHAR(45) NULL,
    last_name VARCHAR(45) NULL,
    email VARCHAR(45) NULL,
    instructor_detail_id INT NULL,
    CONSTRAINT FK_DETAIL FOREIGN KEY (instructor_detail_id) 
        REFERENCES hb_05_many_to_many.instructor_detail (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Create `course` table
CREATE TABLE hb_05_many_to_many.course (
    id INT IDENTITY(1,1) PRIMARY KEY,  -- Equivalent to AUTO_INCREMENT
    title VARCHAR(128) NULL,
    instructor_id INT NULL,
    CONSTRAINT TITLE_UNIQUE UNIQUE (title),
    CONSTRAINT FK_INSTRUCTOR FOREIGN KEY (instructor_id) 
        REFERENCES hb_05_many_to_many.instructor (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Create `review` table
CREATE TABLE hb_05_many_to_many.review (
    id INT IDENTITY(1,1) PRIMARY KEY,  -- Equivalent to AUTO_INCREMENT
    comment VARCHAR(256) NULL,
    course_id INT NULL,
    CONSTRAINT FK_COURSE FOREIGN KEY (course_id) 
        REFERENCES hb_05_many_to_many.course (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Create `student` table
CREATE TABLE hb_05_many_to_many.student (
    id INT IDENTITY(1,1) PRIMARY KEY,  -- Equivalent to AUTO_INCREMENT
    first_name VARCHAR(45) NULL,
    last_name VARCHAR(45) NULL,
    email VARCHAR(45) NULL
);

-- Create `course_student` table (many-to-many relationship between courses and students)
CREATE TABLE hb_05_many_to_many.course_student (
    course_id INT NOT NULL,
    student_id INT NOT NULL,
    PRIMARY KEY (course_id, student_id),
    CONSTRAINT FK_COURSE_05 FOREIGN KEY (course_id) 
        REFERENCES hb_05_many_to_many.course (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT FK_STUDENT FOREIGN KEY (student_id) 
        REFERENCES hb_05_many_to_many.student (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Enable foreign key checks (this is the default behavior in SQL Server)
-- No need to explicitly disable foreign key checks like in MySQL.
