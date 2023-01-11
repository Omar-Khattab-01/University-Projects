/*** Helper Functions ***/

/* Checks if the person with the given ID is a patient. */
CREATE FUNCTION is_patient (patient_id INTEGER)
RETURNS BOOLEAN AS $$
DECLARE
	entries INTEGER;
BEGIN
	SELECT COUNT(*) INTO entries FROM patient WHERE p_id=patient_id;
	IF entries = 1
		THEN RETURN 1;
	ELSE
		RETURN 0;
	END IF;
END;
$$ LANGUAGE plpgsql;

/* Checks if the person with the given ID is a dentist */
CREATE FUNCTION is_dentist (dentist_id INTEGER)
RETURNS BOOLEAN AS $$
DECLARE
	entries INTEGER;
BEGIN
	SELECT COUNT(*) INTO entries FROM employee WHERE p_id=dentist_id AND job_title='dentist';
	IF entries = 1
		THEN RETURN 1;
	ELSE
		RETURN 0;
	END IF;
END;
$$ LANGUAGE plpgsql;

/* Checks if the person with the given ID is a manager */
CREATE FUNCTION is_manager (manager_id INTEGER)
RETURNS BOOLEAN AS $$
DECLARE
	entries INTEGER;
BEGIN
	SELECT COUNT(*) INTO entries FROM employee WHERE p_id=manager_id AND job_title='manager';
	IF entries = 1
		THEN RETURN 1;
	ELSE
		RETURN 0;
	END IF;
END;
$$ LANGUAGE plpgsql;

/* Checks if the given employee works at the specified branch */
CREATE FUNCTION is_employee_of (employee_id INTEGER, branch_name VARCHAR(20))
RETURNS BOOLEAN AS $$
DECLARE
	entries INTEGER;
BEGIN
	SELECT COUNT(*) INTO entries FROM employee WHERE p_id=employee_id AND employer=branch_name;
	IF ENTRIES = 1
		THEN RETURN 1;
	ELSE
		RETURN 0;
	END IF;
END;
$$ LANGUAGE plpgsql;

/* Checks if the given branch has a manager */
CREATE FUNCTION has_manager (branch_id VARCHAR(20))
RETURNS BOOLEAN AS $$
DECLARE
	entries INTEGER;
BEGIN
	SELECT COUNT(*) INTO entries FROM branch WHERE city=branch_id AND manager <> NULL;
	IF ENTRIES = 1
		THEN RETURN 1;
	ELSE
		RETURN 0;
	END IF;
END;
$$ LANGUAGE plpgsql;

/* Gets the number of receptionists of a branch */
CREATE FUNCTION num_receptionists (branch_name VARCHAR(20))
RETURNS INTEGER AS $$
DECLARE
	num INTEGER;
BEGIN
	SELECT COUNT(*) INTO num FROM employee WHERE employer=branch_name AND job_title='receptionist';
	RETURN num;
END;
$$ LANGUAGE plpgsql;

/* Gets the age of the specified person */
CREATE FUNCTION get_age (person_id INTEGER)
RETURNS INTEGER AS $$
DECLARE
	dob DATE;
	diff INTEGER;
BEGIN
	SELECT date_of_birth INTO dob FROM person WHERE p_id=person_id;
	
	diff := 0;
	
	IF EXTRACT(MONTH FROM CURRENT_DATE) < EXTRACT(MONTH FROM dob)
		THEN diff := -1;
	END IF;
	
	IF EXTRACT(MONTH FROM CURRENT_DATE) = EXTRACT(MONTH FROM dob) 
 	AND EXTRACT(DAY FROM CURRENT_DATE) < EXTRACT(DAY FROM dob)
		THEN diff := -1;
	END IF;
	
	RETURN ((DATE_PART('year', CURRENT_DATE) - DATE_PART('year', dob)) + diff);
END;
$$ LANGUAGE plpgsql;

/*** Table Definitions ***/

CREATE TABLE branch(
	city VARCHAR(20) NOT NULL,
	manager INTEGER,
	PRIMARY KEY(city),
	CHECK(manager = NULL OR is_manager(manager))
); 

CREATE TABLE insurance_company(
	insurance_id VARCHAR(100),
	PRIMARY KEY(insurance_id)
);

CREATE TABLE person(
	p_id SERIAL PRIMARY KEY,
	first_name VARCHAR(20) NOT NULL,
	middle_name VARCHAR(20),
	last_name VARCHAR(20) NOT NULL,
	gender VARCHAR(1) NOT NULL,
	date_of_birth DATE NOT NULL,
	CHECK(date_of_birth < CURRENT_DATE),
	CHECK(gender='M' OR gender='F')
);

CREATE TABLE user_account(
	email VARCHAR(100) NOT NULL,
	password_hash VARCHAR(40) NOT NULL,
	p_id INTEGER NOT NULL,
	PRIMARY KEY(email),
	FOREIGN KEY(p_id) REFERENCES person ON DELETE CASCADE
);

CREATE TABLE employee(
	p_id INTEGER UNIQUE NOT NULL,
	employer VARCHAR(20) NOT NULL,
	job_title VARCHAR(20) NOT NULL,
	salary FLOAT NOT NULL,
	FOREIGN KEY(p_id) REFERENCES person ON DELETE CASCADE,
	FOREIGN KEY(employer) REFERENCES branch ON DELETE CASCADE,
	CHECK(job_title IN ('receptionist', 'hygienist', 'dentist', 'manager')),
	CHECK(salary >= 40000),
	CHECK(job_title <> 'receptionist' OR num_receptionists(employer) < 2),
	CHECK(job_title <> 'manager' OR NOT has_manager(employer))
);

CREATE TABLE patient(
	p_id INTEGER NOT NULL,
	ssn INTEGER UNIQUE NOT NULL,
	insurance_id VARCHAR(100),
	responsible_party INTEGER,
	FOREIGN KEY(p_id) REFERENCES person ON DELETE CASCADE,
	FOREIGN KEY(insurance_id) REFERENCES insurance_company ON DELETE SET NULL,
	FOREIGN KEY(responsible_party) REFERENCES person ON DELETE CASCADE,
	CHECK(p_id <> responsible_party),
	CHECK(get_age(responsible_party) >= 18),
	CHECK(get_age(p_id) >= 15 OR responsible_party <> NULL)
);

CREATE TABLE phone_number(
	p_id INTEGER NOT NULL,
	phone_number VARCHAR(22) NOT NULL,
	FOREIGN KEY(p_id) REFERENCES person(p_id) ON DELETE CASCADE
);

CREATE TABLE invoice(
    invoice_id SERIAL PRIMARY KEY,
    date_of_issue DATE NOT NULL,
    penalty FLOAT,
    CHECK(penalty >= 0)
);

CREATE TABLE appointment(
	appointment_id SERIAL PRIMARY KEY,
	patient_id INTEGER NOT NULL,
	dentist_id INTEGER NOT NULL,
	appointment_date DATE NOT NULL,
	start_time TIME NOT NULL,
	end_time TIME NOT NULL,
	appointment_type VARCHAR(20) NOT NULL,
	appointment_status VARCHAR(20) NOT NULL,
	room INTEGER NOT NULL,
	appointment_location VARCHAR(20) NOT NULL,
	FOREIGN KEY(appointment_location) REFERENCES branch ON DELETE CASCADE,
	FOREIGN KEY(patient_id) REFERENCES person(p_id) ON DELETE CASCADE,
	FOREIGN KEY(dentist_id) REFERENCES person(p_id) ON DELETE CASCADE,
	CHECK(is_patient(patient_id)),
	CHECK(is_dentist(dentist_id)),
	CHECK(is_employee_of(dentist_id, appointment_location)),
	CHECK(end_time > start_time),
	CHECK(appointment_status IN ('no show', 'cancelled', 'completed', 'unscheduled')),
	UNIQUE (patient_id, dentist_id,appointment_date,start_time),
	UNIQUE (dentist_id,appointment_date,start_time),
	UNIQUE (patient_id,appointment_date,start_time)
	
);

CREATE TABLE appointment_procedure(
	procedure_id SERIAL PRIMARY KEY,
	appointment_id INTEGER NOT NULL,
    procedure_code VARCHAR(10) NOT NULL,
    procedure_type VARCHAR(30) NOT NULL,
    procedure_description VARCHAR(200),
	FOREIGN KEY(appointment_id) REFERENCES appointment ON DELETE CASCADE
);

CREATE TABLE fee_charge(
    fee_id SERIAL PRIMARY KEY,
    fee_code INTEGER NOT NULL,
    patient_charge FLOAT NOT NULL,
    insurance_charge FLOAT NOT NULL,
	discount FLOAT NOT NULL,
	procedure_id INTEGER UNIQUE NOT NULL,
	FOREIGN KEY(procedure_id) REFERENCES appointment_procedure ON DELETE CASCADE,
	CHECK(patient_charge >= 0),
	CHECK(insurance_charge >= 0),
	CHECK(discount >= 0)
);

CREATE TABLE invoice_fees(
    invoice_id INTEGER NOT NULL,
    fee_id INTEGER UNIQUE NOT NULL,
    FOREIGN KEY(invoice_id) REFERENCES invoice ON DELETE CASCADE,
    FOREIGN KEY(fee_id) REFERENCES fee_charge ON DELETE CASCADE
);

CREATE TABLE address(
	p_id INTEGER NOT NULL,
	house_number INTEGER not null,
	street VARCHAR(20) not null,
	city VARCHAR(20) not null,
	province VARCHAR(20) not null,
	FOREIGN KEY(p_id) REFERENCES person ON DELETE CASCADE
);

CREATE TABLE review(
	branch_name VARCHAR(20) NOT NULL,
	author INTEGER NOT NULL,
	professionalism INTEGER NOT NULL,
	communication INTEGER NOT NULL,
	cleanliness INTEGER NOT NULL,
	review_value INTEGER NOT NULL,
	review_comments VARCHAR(50) NOT NULL,
	FOREIGN KEY(branch_name) REFERENCES branch ON DELETE CASCADE,
	FOREIGN KEY(author) REFERENCES person(p_id) ON DELETE CASCADE,
	CHECK(NOT is_employee_of(author, branch_name)),
	CHECK(professionalism >= 1 AND professionalism <= 5),
	CHECK(communication >= 1 AND communication <= 5),
	CHECK(cleanliness >= 1 AND cleanliness <= 5),
	CHECK(review_value >= 1 AND review_value <= 5)
);

CREATE TABLE patient_bill (
	bill_id SERIAL PRIMARY KEY,
	patient_id INTEGER NOT NULL,
	invoice_id INTEGER UNIQUE NOT NULL,
	payment_due_date DATE,
	FOREIGN KEY(patient_id) REFERENCES person ON DELETE CASCADE,
	FOREIGN KEY(invoice_id) REFERENCES invoice ON DELETE CASCADE,
	CHECK(is_patient(patient_id))
);

CREATE TABLE insurance_claim (
	claim_id SERIAL PRIMARY KEY,
	bill_id INTEGER UNIQUE NOT NULL,
	insurance_id VARCHAR(100) NOT NULL,
	date_paid DATE NOT NULL,
	amount FLOAT NOT NULL,
	FOREIGN KEY(bill_id) REFERENCES patient_bill ON DELETE CASCADE,
	FOREIGN KEY(insurance_id) REFERENCES insurance_company ON DELETE CASCADE,
	CHECK(amount > 0)
);

CREATE TABLE patient_payment (
	payment_id SERIAL PRIMARY KEY,
	payer_id INTEGER NOT NULL,
	bill_id INTEGER NOT NULL,
	payment_type VARCHAR(10) NOT NULL,
	amount FLOAT NOT NULL,
	FOREIGN KEY(payer_id) REFERENCES person(p_id) ON DELETE CASCADE,
	FOREIGN KEY(bill_id) REFERENCES patient_bill ON DELETE CASCADE,
	CHECK(payment_type in ('Cash', 'Debit', 'Amex', 'Visa', 'Mastercard')),
	CHECK(amount > 0)
);

CREATE TABLE treatment(
	treatment_id SERIAL PRIMARY KEY,
	procedure_id INTEGER NOT NULL,
	patient_id INTEGER NOT NULL,
	treatment_type VARCHAR(30) NOT NULL,
	medication VARCHAR(30) NOT NULL,
	symptoms VARCHAR(50) NOT NULL,
	tooth VARCHAR(50) NOT NULL,
	FOREIGN KEY(procedure_id) REFERENCES appointment_procedure ON DELETE CASCADE,
	FOREIGN KEY(patient_id) REFERENCES person(p_id) ON DELETE CASCADE,
	CHECK(is_patient(patient_id))
);

CREATE TABLE treatment_comment(
	treatment_id INTEGER NOT NULL,
	comment VARCHAR(50) NOT NULL,
	FOREIGN KEY(treatment_id) REFERENCES treatment(treatment_id) ON DELETE CASCADE
);

/*** Views ***/

CREATE VIEW potential_responsible_parties AS
(SELECT * FROM person WHERE get_age(p_id) >= 18)
EXCEPT
(SELECT person.p_id, first_name, middle_name, last_name, gender, date_of_birth
FROM patient JOIN person ON patient.responsible_party = person.p_id)