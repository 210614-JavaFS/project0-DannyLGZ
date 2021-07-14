DROP TABLE IF EXISTS accounts;  
DROP TABLE IF EXISTS employees;  
DROP TABLE IF EXISTS customer;  
DROP TABLE IF EXISTS balance_change;  


CREATE TABLE customer(
	user_id VARCHAR(50) PRIMARY KEY,
	user_password VARCHAR(50) NOT NULL,
	first_name VARCHAR(30),
	last_name VARCHAR(30),
	address VARCHAR(150),
	email VARCHAR(30),
	phone_number VARCHAR(50),
	last4_SSN INTEGER UNIQUE
);

CREATE TABLE employees(
	index_id VARCHAR(20) UNIQUE,
	employee_role VARCHAR(50) NOT NULL,
	employee_id VARCHAR(20) PRIMARY KEY,
	employee_password VARCHAR(50) NOT NULL,
	employee_firstname VARCHAR(50) NOT NULL,
	employee_lastname VARCHAR(50) NOT NULL
);

CREATE TABLE accounts(
	user_id VARCHAR(50) NOT NULL,
	user_password VARCHAR(50) NOT NULL,
	acc_type VARCHAR(50) NOT NULL,
	acc_number INTEGER PRIMARY KEY,
	balance NUMERIC CHECK (balance >= 0),
	acc_status BOOLEAN DEFAULT TRUE,
	employee_id VARCHAR(20),
	FOREIGN KEY (user_id) REFERENCES customer(user_id),
	FOREIGN KEY (employee_id) REFERENCES employees(index_id)
);


CREATE TABLE balance_change(
	log_id SERIAL PRIMARY KEY,
	acc_type VARCHAR(50) NOT NULL,
	acc_number INTEGER UNIQUE NOT NULL,
	old_balance NUMERIC CHECK (old_balance >= 0),
	new_balance NUMERIC CHECK (new_balance >= 0),
	time TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
);


--create trigger function
CREATE OR REPLACE FUNCTION log_balance() RETURNS TRIGGER AS 
$BODY$
BEGIN
	IF NEW.balance <> OLD.balance THEN 
	INSERT INTO balance_change (acc_type, acc_number, old_balance, new_balance)
		VALUES (OLD.acc_type, OLD.acc_number,OLD.balance,NEW.balance);
	END IF;
	RETURN NEW;
END
$BODY$
LANGUAGE plpgsql;

--create trigger
CREATE TRIGGER update_balance AFTER UPDATE ON accounts 
	FOR EACH ROW EXECUTE PROCEDURE log_balance();



INSERT INTO customer (user_id,user_password,first_name,last_name,address,email,phone_number,last4_ssn)
	VALUES ('tester','123','tim','cool','1036 Paul Wayne Haggerty Road,Kenner,LA,70062','tester@test.com','7657414205',6724),
	('tester1','idontknow','steve','jobs','505 Winifred Way,north liberty,IN,46554','steve.jobs@gmail.com','7657414205',7913),
	('tester2','whatsthat','jj','love','3959 Frank Avenue,pittsburgh,PA,15219','karlqqq@gmail.com','4129604951',4782),
	('tester3','cantremember','tracer','oxton','501 Jadewood Farms,New Brunswick,NJ,08901','besttracerontheworld@gmail.com','9733969270',1195);

INSERT INTO employees (index_id,employee_role,employee_id,employee_password,employee_firstname,employee_lastname)
	VALUES
	(0,'n/a','n/a','n/a','n/a','n/a'),
	(1001,'admin','jesse.mccree','headsup','jesse','mccree'),
	(001,'employee','bob.lee','dosomething','bob','lee'),
	(002,'employee','ana.amari','gotosleep','ana','amari'),
	(003,'employee','elizabeth.ashe','excuseme','elizabeth','ashe');


INSERT INTO accounts (user_id, user_password,acc_type, acc_number,balance,acc_status,employee_id)
	VALUES 
	('tester','123','saving',9762,9500,TRUE,001),
	('tester','123','checking',10382,250.74,TRUE,001),
	('tester1','idontknow','checking',46805,7516.74,TRUE,003),
	('tester2','whatsthat','saving',7109,500,TRUE,002),
	('tester2','cantremember','checking',78143,0,FALSE,0);
	












