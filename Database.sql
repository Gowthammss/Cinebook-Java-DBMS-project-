
CREATE DATABASE CineBook;

USE CineBook;

CREATE TABLE Movies (
    movie_id INT AUTO_INCREMENT PRIMARY KEY,
    movie_name VARCHAR(100) NOT NULL
);

CREATE TABLE Seats (
    seat_code VARCHAR(5) PRIMARY KEY,
    seat_type ENUM('REGULAR', 'PREMIUM', 'VIP') NOT NULL,
    price INT NOT NULL,
    status ENUM('AVAILABLE', 'BOOKED') DEFAULT 'AVAILABLE'
);

CREATE TABLE Bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    movie_id INT NOT NULL,
    seat_code VARCHAR(5) NOT NULL,
    booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (movie_id) REFERENCES Movies(movie_id),
    FOREIGN KEY (seat_code) REFERENCES Seats(seat_code),
    UNIQUE (movie_id, seat_code)
);

INSERT INTO Movies (movie_name)
VALUES
('Leo'),
('Jailer'),
('GOAT'),
('Interstellar');

INSERT INTO Seats (seat_code, seat_type, price)
VALUES
('A1', 'REGULAR', 150),
('A2', 'REGULAR', 150),
('A3', 'REGULAR', 150),
('A4', 'REGULAR', 150),
('A5', 'REGULAR', 150),
('B1', 'PREMIUM', 250),
('B2', 'PREMIUM', 250),
('B3', 'PREMIUM', 250),
('B4', 'PREMIUM', 250),
('B5', 'PREMIUM', 250),
('C1', 'VIP', 400),
('C2', 'VIP', 400),
('C3', 'VIP', 400),
('C4', 'VIP', 400),
('C5', 'VIP', 400);

SELECT * FROM Movies;
SELECT * FROM Seats;
SELECT * FROM Bookings;
SELECT * FROM Movies,Seats,Bookings;
SHOW TABLES;