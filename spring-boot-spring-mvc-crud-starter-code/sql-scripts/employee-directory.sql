USE employee_directory;

DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  id int NOT NULL IDENTITY(1,1),
  first_name varchar(45) DEFAULT NULL,
  last_name varchar(45) DEFAULT NULL,
  email varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
) ;

INSERT INTO employee VALUES
	('Leslie','Andrews','leslie@luv2code.com'),
	('Emma','Baumgarten','emma@luv2code.com'),
	('Avani','Gupta','avani@luv2code.com'),
	('Yuri','Petrov','yuri@luv2code.com'),
	('Juan','Vega','juan@luv2code.com');

