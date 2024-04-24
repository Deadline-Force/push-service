CREATE TABLE IF NOT EXISTS roles (
    role_id SERIAL PRIMARY KEY NOT NULL,
    title VARCHAR(32) NOT NULL UNIQUE
);

INSERT INTO roles (title) VALUES
        ('USER'), ('EMPLOYEE'), ('ADMIN');

CREATE TABLE IF NOT EXISTS users (
    user_id BIGSERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(128) NOT NULL,
    login VARCHAR(128) NOT NULL,
    password VARCHAR(128) NOT NULL,
    user_role INT NOT NULL,
    FOREIGN KEY (user_role) REFERENCES roles (role_id)
);

CREATE TABLE IF NOT EXISTS notifications (
    notification_id BIGSERIAL PRIMARY KEY NOT NULL,
    title VARCHAR(64) NOT NULL,
    message VARCHAR(128) NOT NULL,
    created_at DATE NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS recipients (
    user_id BIGINT NOT NULL,
    notification_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (notification_id) REFERENCES notifications (notification_id) ON DELETE CASCADE
);