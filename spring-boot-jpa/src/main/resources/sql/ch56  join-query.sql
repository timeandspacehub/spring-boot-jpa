DELETE FROM spring.address WHERE id > 0;
DELETE FROM spring.student WHERE id > 0;
ALTER TABLE spring.address AUTO_INCREMENT = 1;
ALTER TABLE spring.student AUTO_INCREMENT = 1;

INSERT INTO address (street, city)
VALUES('123 Manhattan St','NY');

INSERT INTO address (street, city)
VALUES('461 Humbert St','CA');

INSERT INTO address (street, city)
VALUES('789 West Virginia','NY');

INSERT INTO address (street, city)
VALUES('780 Court Ave','WA');

INSERT INTO student (first_name, last_name, email, address_id)
VALUES('John','Smith','john@gmail.com',1);

INSERT INTO student (first_name, last_name, email, address_id)
VALUES('Raj','Dave','raj@gmail.com',2);

INSERT INTO student (first_name, last_name, email, address_id)
VALUES('Virat','Sharma','virat@gmail.com',3);

INSERT INTO student (first_name, last_name, email, address_id)
VALUES('Sachin','Patel','sachin@gmail.com',4);