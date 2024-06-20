INSERT INTO role (Role_id, Name) VALUES (1, 'ROLE_USER');
INSERT INTO role (Role_id, Name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO users (User_id, Username, Password, FirstName, Lastname, Email)
VALUES (1, 'Wackjon', '$2a$10$pTuiU.o5LCCdXnU06izgK.5HPWwT3udTAhRe7Q.Noug4FnObVZ7wq', 'Jackson', 'Wagner', 'Wacky@mail.com');

INSERT INTO real_estate (Seller_id, Address, Road_number, Area_code, Area_size, Description)
VALUES (1, 'Longhill St.', 5, 921023, 521, 'Real estate description...');

INSERT INTO tax_declaration (Seller_id, Real_estate_id) VALUES (1, 1);
UPDATE real_estate SET tax_declaration_id = 1 WHERE real_estate_id=1;

INSERT INTO users (User_id, Username, Password, FirstName, Lastname, Email)
VALUES (2, 'Brandy' ,'$2a$10$pTuiU.o5LCCdXnU06izgK.5HPWwT3udTAhRe7Q.Noug4FnObVZ7wq', 'Bob', 'Randy', 'Admin@mail.com');

INSERT INTO users (User_id, Username, Password, FirstName, Lastname, Email)
VALUES (3, 'Joker', '$2a$10$pTuiU.o5LCCdXnU06izgK.5HPWwT3udTAhRe7Q.Noug4FnObVZ7wq', 'Jeremiah', 'Brooker', 'Jerry@mail.com');

INSERT INTO users (User_id, Username, Password, FirstName, Lastname, Email)
VALUES (4, 'Miles', '$2a$10$pTuiU.o5LCCdXnU06izgK.5HPWwT3udTAhRe7Q.Noug4FnObVZ7wq', 'Claxton', 'Miles', 'Clax@mail.com');

INSERT INTO users (User_id, Username, Password, FirstName, Lastname, Email)
VALUES (5, 'Chopper', '$2a$10$pTuiU.o5LCCdXnU06izgK.5HPWwT3udTAhRe7Q.Noug4FnObVZ7wq', 'Jonathan', 'Hopper', 'Hopper@mail.com');

INSERT INTO users (User_id, Username, Password, FirstName, Lastname, Email)
VALUES (6, 'McJordan', '$2a$10$pTuiU.o5LCCdXnU06izgK.5HPWwT3udTAhRe7Q.Noug4FnObVZ7wq', 'Jordan', 'McGregory', 'Jordy@mail.com');


INSERT INTO user_roles (User_id, Role_id) VALUES (1, 1);
INSERT INTO user_roles (User_id, Role_id) VALUES (2, 2);
INSERT INTO user_roles (User_id, Role_id) VALUES (3, 1);
INSERT INTO user_roles (User_id, Role_id) VALUES (4, 1);
INSERT INTO user_roles (User_id, Role_id) VALUES (5, 1);
INSERT INTO user_roles (User_id, Role_id) VALUES (6, 1);