DROP TABLE IF EXISTS users;
CREATE TABLE users (
                       id INTEGER PRIMARY KEY AUTOINCREMENT ,
                       name varchar(30),
                       email varchar(30),
                       password varchar(30),
                       dob varchar(30),
                       gender varchar(30)
);

-- Example user insertion
INSERT INTO users (name, email, password, dob, gender) VALUES
    ('John Doe', 'john@example.com', 'securepassword123', '1990-01-01', 'Male');
SELECT * FROM users;