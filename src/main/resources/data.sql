INSERT INTO user (first_name, last_name, pesel) VALUES
('Jan', 'Kowalski', '789456123'),
('Maciej', 'Zalewski', '456123789'),
('Aneta', 'Korczynska', '321654987');

INSERT INTO category (name) VALUES ('Laptops'), ('Vehicles');

INSERT INTO asset (name, description, serial_number, category_id) VALUES
('Asus MateBook D', '15` laptop, i5, 8GB DDR3, color black', 'ASMBD198723', 1),
('Apple MacBook Pro 2015', '13` laptop, i5, 16GB DDR3, SSD256GB, color silver', 'MBP15X0925336', 1),
('Dell Inspirion 3576', '13` laptop, i7, 8GB DDR4, SSD 512GB, color black', 'DI3576XO526716', 1);


INSERT INTO assignment (start, end, asset_id, user_id) VALUES
 ('2017-10-08 15:00:00', '2018-10-08 15:00:00', 1, 1),
 ('2018-10-09 12:00:00', null, 2, 1),
 ('2018-10-10 16:00:00', null, 1, 1);

