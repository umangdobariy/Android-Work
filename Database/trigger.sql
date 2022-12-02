use shoppy;

CREATE TABLE contacts
( contact_id INT(11) NOT NULL AUTO_INCREMENT,
  last_name VARCHAR(30) NOT NULL,
  first_name VARCHAR(25),
  birthday DATE,
  CONSTRAINT contacts_pk PRIMARY KEY (contact_id)
);

insert into contacts (last_name,first_name,birthday) values ('12','12','2012-12-12') 
select * from contacts;
select * from contacts_audit;


DELIMITER //

CREATE TRIGGER contacts_after_insert
AFTER INSERT
   ON contacts FOR EACH ROW

BEGIN

   DECLARE vUser varchar(50);

   -- Find username of person performing the INSERT into table
   SELECT USER() INTO vUser;

   -- Insert record into audit table
   INSERT INTO contacts_audit
   ( contact_id,
     created_date,
     created_by)
   VALUES
   ( NEW.contact_id,
     SYSDATE(),
     vUser );

END; //
DELIMITER ;
