CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    avatar_path VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE goals (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    memo VARCHAR(255),
    target INT NOT NULL,
    unit VARCHAR(255) NOT NULL,
    current INT NOT NULL,
    deadline date,

    CONSTRAINT fk_goals_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);