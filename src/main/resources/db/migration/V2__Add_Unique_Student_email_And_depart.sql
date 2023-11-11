ALTER TABLE student
ADD CONSTRAINT student_idNumber_unique UNIQUE (idnumber);

ALTER TABLE student
ADD CONSTRAINT student_email_unique UNIQUE (email);

