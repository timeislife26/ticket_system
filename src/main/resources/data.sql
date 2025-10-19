
INSERT INTO department(department_name, department_head) VALUES
('IT', NULL),
('Sales', NULL);

INSERT INTO users (forename, surname, email, password_hash, department_code) VALUES
('John', 'Smith', 'JohnS@example.com', 'password', 1),
('Joe', 'Bloggs', 'JoeB@example.com', 'pass', 1),
('Jane', 'Doe', 'JaneD@example.com', 'test', 2);

UPDATE department SET department_head = 1 WHERE department_code = 1;
UPDATE department SET department_head = 3 WHERE department_code = 2;

INSERT INTO tickets (title, description, priority, status, assigned_user, created_user_id) VALUES
('Test', 'Testing a Ticket', 1, 'IN_PROGRESS', 2 , 1),
('Unassigned Ticket', 'A ticket with no assigned user', 2, 'TO_BE_ASSIGNED', 1 , 3),
('Sales Assigned Ticket', 'A ticket just assigned', 2, 'OPEN', 2 , 3);


INSERT INTO user_roles (user_id, role) VALUES
  (1, 'ADMIN'), (1, 'USER'), (1, 'MANAGER'),
  (2, 'USER'),  (2, 'EMPLOYEE'),
  (3, 'USER'),  (2, 'MANAGER');
