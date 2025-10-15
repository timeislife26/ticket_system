
INSERT INTO department(department_name, department_head_id) VALUES
('IT', NULL);

INSERT INTO users (forename, surname, email, password_hash, department_code) VALUES
('assigner', 'user', 'assigner@example.com', 'password', 1),
('receiver', 'user', 'receiver@example.com', 'pass', 1);

UPDATE department SET department_head_id = 1 WHERE department_code = 1;

INSERT INTO tickets (title, description, priority, status, assigned_user_id, created_user_id) VALUES
('Test', 'Testing a Ticket', 1, 'TO_BE_ASSIGNED', 2 , 1);


INSERT INTO user_roles (user_id, role) VALUES
  (1, 'ADMIN'), (1, 'USER'), (1, 'MANAGER'),
  (2, 'USER'),  (2, 'EMPLOYEE');
