BEGIN TRANSACTION;

DROP TABLE IF EXISTS person, campaign, campaign_person, days_of_week, specific_day CASCADE;

CREATE TABLE person (
	person_id SERIAL NOT NULL,
	name VARCHAR (100) NOT NULL,
	
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


INSERT INTO person (name) VALUES ('Sam Muskovitz');         --1
INSERT INTO person (name) VALUES ('John Greaves');          --2
INSERT INTO person (name) VALUES ('Jeanna Shellenburger');  --3
INSERT INTO person (name) VALUES ('Brandon Leong');         --4
INSERT INTO person (name) VALUES ('Matt VanTrease');        --5
INSERT INTO person (name) VALUES ('Dana Coyle');            --6
INSERT INTO person (name) VALUES ('Brandon Butler');        --7
INSERT INTO person (name) VALUES ('Darah Muhhamad');        --8
INSERT INTO person (name) VALUES ('Neha Kamireddi');        --9

INSERT INTO campaign (name, description) VALUES ('Un-titled Call of Cathulu', 'A call of Cathulu campaign where there is a time loop set in 2020');     --1
INSERT INTO campaign (name, description) VALUES ('The War Council', 'The evil half of the world spanning campaign');                                    --2
INSERT INTO campaign (name, description) VALUES ('The Adventure''s Guild', 'The good half of the world spanning campaign');                             --3

INSERT INTO campaign_person (campaign_id, person_id) VALUES (1,1);
INSERT INTO campaign_person (campaign_id, person_id) VALUES (1,2);
INSERT INTO campaign_person (campaign_id, person_id) VALUES (1,3);
INSERT INTO campaign_person (campaign_id, person_id) VALUES (1,4);
INSERT INTO campaign_person (campaign_id, person_id) VALUES (1,5);
INSERT INTO campaign_person (campaign_id, person_id) VALUES (1,6);

INSERT INTO campaign_person (campaign_id, person_id) VALUES (2,1);
INSERT INTO campaign_person (campaign_id, person_id) VALUES (2,3);
INSERT INTO campaign_person (campaign_id, person_id) VALUES (2,4);
INSERT INTO campaign_person (campaign_id, person_id) VALUES (2,7);
INSERT INTO campaign_person (campaign_id, person_id) VALUES (2,9);

INSERT INTO campaign_person (campaign_id, person_id) VALUES (3,2);
INSERT INTO campaign_person (campaign_id, person_id) VALUES (3,5);
INSERT INTO campaign_person (campaign_id, person_id) VALUES (3,6);
INSERT INTO campaign_person (campaign_id, person_id) VALUES (3,8);


INSERT INTO days_of_week (person_id, campaign_id, day_code, is_free, start_time, end_time) VALUES (1, 1, 'MON', true, '15:30:00', '20:45:00');
INSERT INTO days_of_week (person_id, campaign_id, day_code, is_free) VALUES (1, 1, 'TUE', false);
INSERT INTO days_of_week (person_id, campaign_id, day_code, is_free, start_time, end_time) VALUES (1, 1, 'WEN', true, '15:30:00', '20:45:00');
INSERT INTO days_of_week (person_id, campaign_id, day_code, is_free) VALUES (1, 1, 'THU', false);
INSERT INTO days_of_week (person_id, campaign_id, day_code, is_free, start_time, end_time) VALUES (1, 1, 'FRI', true, '15:30:00', '20:45:00');
INSERT INTO days_of_week (person_id, campaign_id, day_code, is_free) VALUES (1, 1, 'SAT', false);
INSERT INTO days_of_week (person_id, campaign_id, day_code, is_free, start_time, end_time) VALUES (1, 1, 'SUN', true, '15:30:00', '20:45:00');

INSERT INTO days_of_week (person_id, campaign_id, day_code, is_free, start_time, end_time) VALUES (2, 1, 'MON', true, '15:30:00', '20:45:00');
INSERT INTO days_of_week (person_id, campaign_id, day_code, is_free) VALUES (2, 1, 'TUE', false);
INSERT INTO days_of_week (person_id, campaign_id, day_code, is_free, start_time, end_time) VALUES (2, 1, 'WEN', true, '15:30:00', '20:45:00');
INSERT INTO days_of_week (person_id, campaign_id, day_code, is_free) VALUES (2, 1, 'THU', false);
INSERT INTO days_of_week (person_id, campaign_id, day_code, is_free, start_time, end_time) VALUES (2, 1, 'FRI', true, '15:30:00', '20:45:00');
INSERT INTO days_of_week (person_id, campaign_id, day_code, is_free) VALUES (2, 1, 'SAT', false);
INSERT INTO days_of_week (person_id, campaign_id, day_code, is_free, start_time, end_time) VALUES (2, 1, 'SUN', true, '15:30:00', '20:45:00');

COMMIT;