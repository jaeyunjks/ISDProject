DROP TABLE IF EXISTS OrderItems;
DROP TABLE IF EXISTS Orders;
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
CREATE TABLE Orders (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        userId INT NOT NULL,
                        totalPrice DOUBLE,
                        status VARCHAR(20),
                        orderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (userId) REFERENCES users(id)
);
CREATE TABLE OrderItems (
                              id INT PRIMARY KEY AUTO_INCREMENT,
                              orderId INT NOT NULL,
                              productId INT,
                              quantity INT,
                              unitPrice DOUBLE,
                              FOREIGN KEY (orderId) REFERENCES Orders(id)
  );
INSERT INTO Orders (userId, totalPrice, status) VALUES
    (1, 199.99, 'Pending');
INSERT INTO OrderItems (orderId, productId, quantity, unitPrice) VALUES
    (1, 101, 2, 49.99),
    (1, 102, 1, 99.99);
SELECT * FROM users;
SELECT * FROM Orders;
SELECT * FROM OrderItems;