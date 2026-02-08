CREATE TABLE Book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255)
);

CREATE TABLE Reader (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE Status (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT,
    reader_id INT,
    status VARCHAR(50),
    FOREIGN KEY (book_id) REFERENCES Book(id),
    FOREIGN KEY (reader_id) REFERENCES Reader(id)
);

CREATE TABLE Review (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT,
    reader_id INT,
    text VARCHAR(500),
    rating INT,
    FOREIGN KEY (book_id) REFERENCES Book(id),
    FOREIGN KEY (reader_id) REFERENCES Reader(id)
);
