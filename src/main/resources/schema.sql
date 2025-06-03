CREATE TABLE tickets (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      description VARCHAR(255) NOT NULL,
                      priority int NOT NULL,
                      status VARCHAR(255) NOT NULL,
                      assigned_user_id INT,
                      created_user_id INT
);