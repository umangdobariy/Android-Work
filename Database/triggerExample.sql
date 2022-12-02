use shopping_site;
create table contact(
	uid int auto_increment,
    uname varchar(50),
    primary key (uid)
);
create table contacts_audit(
contact_id int auto_increment,
created_date date,
created_by varchar(50),
primary key(contact_id)
);

DELIMITER //

CREATE TRIGGER contacts_after_insert
AFTER INSERT
   ON contact FOR EACH ROW
BEGIN
   DECLARE vUser varchar(50);
   -- Find username of person performing the INSERT into table
   SELECT USER() INTO vUser;
   -- Insert record into audit table
 INSERT INTO contacts_audit
   ( created_date,created_by)
   VALUES
   ( SYSDATE(),vUser );
END; 







