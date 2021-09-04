/* Студенты */

INSERT INTO student (dateofbirth, fam, name, otch, phonenumber) VALUES ('1999-07-13', 'Булаев', 'Александр', 'Николаевич', '890000001');
INSERT INTO student (dateofbirth, fam, name, otch, phonenumber) VALUES ('1971-07-13', 'Васильев', 'Виталий', 'Сергеевич', '890000000');
INSERT INTO student (dateofbirth, fam, name, otch, phonenumber) VALUES ('1971-07-13', 'Вечтомов', 'Дмитрий', 'Викторович', '890000003');
/* insert with namy entities + generation type change     добавлять уже с id?  */

/* Группы */
INSERT INTO groups (title) VALUES ('142');


INSERT INTO student_groups VALUES (1,1);
INSERT INTO student_groups VALUES (1,2);
INSERT INTO student_groups VALUES (1,3);