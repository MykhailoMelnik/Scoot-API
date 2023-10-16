CREATE TABLE external_user
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    api_key  VARCHAR(255) NOT NULL
);

INSERT INTO external_user (username, api_key)
VALUES ('user1', 'key1');
INSERT INTO external_user (username, api_key)
VALUES ('user2', 'key2');
