

CREATE TABLE department(
                    department_code INT AUTO_INCREMENT PRIMARY KEY,
                    department_name VARCHAR(255) NOT NULL,
                    department_head INT
);

CREATE TABLE users (
                    user_id INT AUTO_INCREMENT PRIMARY KEY,
                    forename VARCHAR(255) NOT NULL,
                    surname VARCHAR(255) NOT NULL,
                    email VARCHAR(255) NOT NULL,
                    password_hash VARCHAR(255) NOT NULL,
                    department_code INT NULL,
                    CONSTRAINT fk_users_department
                        FOREIGN KEY (department_code) REFERENCES department(department_code)
);

CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role    VARCHAR(32) NOT NULL,
  PRIMARY KEY (user_id, role),
  CONSTRAINT fk_user_roles_user
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE tickets (
                      ticket_id INT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      description VARCHAR(255) NOT NULL,
                      priority INT NOT NULL,
                      status VARCHAR(255) NOT NULL,
                      assigned_user INT,
                      created_user_id INT,
                      CONSTRAINT fk_tickets_created_user
                        FOREIGN KEY (created_user_id) REFERENCES users(user_id),
                      CONSTRAINT fk_tickets_assigned_user
                        FOREIGN KEY (assigned_user) REFERENCES users(user_id)
);

ALTER TABLE department
  ADD CONSTRAINT fk_department_head
  FOREIGN KEY (department_head) REFERENCES users(user_id);