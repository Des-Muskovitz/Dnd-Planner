DROP TABLE IF EXISTS specific_day;
DROP TABLE IF EXISTS days_of_week;
DROP TABLE IF EXISTS campaign_person;
DROP TABLE IF EXISTS campaign;
DROP TABLE IF EXISTS person;

CREATE TABLE person (
	person_id SERIAL NOT NULL,
	name VARCHAR (100) NOT NULL,
	character_name VARCHAR (100) NULL,
	
	CONSTRAINT pk_person PRIMARY KEY(person_id)
);

CREATE TABLE campaign(
	campaign_id SERIAL NOT NULL,
	name VARCHAR (100) NOT NULL,
	description TEXT,
	
	CONSTRAINT pk_campaign PRIMARY KEY (campaign_id)
);

CREATE TABLE campaign_person(
	campaign_id INT NOT NULL,
	person_id INT NOT NULL,
	
	CONSTRAINT pk_campaign_person PRIMARY KEY (campaign_id, person_id),
	CONSTRAINT fk_campaign_person_campaign FOREIGN KEY (campaign_id) REFERENCES campaign (campaign_id),
	CONSTRAINT fk_campaign_person_person FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE TABLE days_of_week(
	person_id INT NOT NULL,
	campaign_id INT NOT NULL,
	day_code CHAR(3) NOT NULL,
	is_free BOOLEAN NULL,
	start_time TIME NULL,
	end_time TIME NULL,
	
	CONSTRAINT pk_days_of_week PRIMARY KEY(person_id, day_code),
	CONSTRAINT fk_days_of_week_person FOREIGN KEY (person_id) REFERENCES person (person_id),
	CONSTRAINT fk_days_of_week_campaign FOREIGN KEY (campaign_id) REFERENCES campaign (campaign_id)
);

CREATE TABLE specific_day(
	person_id INT NOT NULL,
	specific_date DATE NOT NULL,
	is_free BOOLEAN NULL,
	
	CONSTRAINT pk_specific_day PRIMARY KEY (person_id, specific_date),
	CONSTRAINT fk_specific_day_person FOREIGN KEY (person_id) REFERENCES person (person_id)
);


INSERT INTO person (name, character_name) VALUES ('Sam', 'GM');
INSERT INTO person (name, character_name) VALUES ('John', 'John');
INSERT INTO person (name, character_name) VALUES ('Jeanna', 'Jeanna');
INSERT INTO person (name, character_name) VALUES ('Brandon', 'Brandon');
INSERT INTO person (name, character_name) VALUES ('Matt', 'Matt');
INSERT INTO person (name, character_name) VALUES ('Dana', 'Dana');

INSERT INTO campaign (name, description) VALUES ('Un-titled Call of Cathulu', 'A call of Cathulu campaign where there is a time loop set in 2020');

INSERT INTO days_of_week (person_id, campaign_id, day_code, is_free) VALUES (1, 1, 'MON', true);
INSERT INTO days_of_week (person_id, campaign_id, day_code, is_free) VALUES (2, 1, 'MON', false);