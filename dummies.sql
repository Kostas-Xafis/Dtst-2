INSERT INTO Role (Role_id, Name) VALUES (1, 'ROLE_USER');
INSERT INTO Role (Role_id, Name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO User (Username, Password, FirstName, Lastname, Email)
VALUES ('Wackjon', '$2a$10$Nnua2EmpoyH1utde5w/GAudHsQctd2ldNir3eZ/2OpHoej0EiKtve', 'Jackson', 'Wagner', 'Wacky@mail.com');

INSERT INTO Real_Estate (Seller_id, Address, Road_number, Area_code, Area_size, Description)
VALUES (1, 'Longhill St.', 5, 921023, 521, 'Real estate description...');

INSERT INTO Tax_Declaration (Seller_id, Real_estate_id) VALUES (1, 1);
UPDATE Real_Estate SET Tax_Declaration_id = 1 WHERE Real_estate_id=1;

INSERT INTO User (Username, Password, FirstName, Lastname, Email)
VALUES ('Brandy' ,'$2a$10$Nnua2EmpoyH1utde5w/GAudHsQctd2ldNir3eZ/2OpHoej0EiKtve', 'Bob', 'Randy', 'Admin@mail.com');

INSERT INTO User (Username, Password, FirstName, Lastname, Email)
VALUES ('Joker', '$2a$10$Nnua2EmpoyH1utde5w/GAudHsQctd2ldNir3eZ/2OpHoej0EiKtve', 'Jeremiah', 'Brooker', 'Jerry@mail.com');

INSERT INTO User (Username, Password, FirstName, Lastname, Email)
VALUES ('Miles', '$2a$10$Nnua2EmpoyH1utde5w/GAudHsQctd2ldNir3eZ/2OpHoej0EiKtve', 'Claxton', 'Miles', 'Clax@mail.com');

INSERT INTO User (Username, Password, FirstName, Lastname, Email)
VALUES ('Chopper', '$2a$10$Nnua2EmpoyH1utde5w/GAudHsQctd2ldNir3eZ/2OpHoej0EiKtve', 'Jonathan', 'Hopper', 'Hopper@mail.com');

INSERT INTO User (Username, Password, FirstName, Lastname, Email)
VALUES ('McJordan', '$2a$10$Nnua2EmpoyH1utde5w/GAudHsQctd2ldNir3eZ/2OpHoej0EiKtve', 'Jordan', 'McGregory', 'Jordy@mail.com');


INSERT INTO User_Roles (User_id, Role_id) VALUES (1, 1);
INSERT INTO User_Roles (User_id, Role_id) VALUES (2, 2);
INSERT INTO User_Roles (User_id, Role_id) VALUES (3, 1);
INSERT INTO User_Roles (User_id, Role_id) VALUES (4, 1);
INSERT INTO User_Roles (User_id, Role_id) VALUES (5, 1);
INSERT INTO User_Roles (User_id, Role_id) VALUES (6, 1);
