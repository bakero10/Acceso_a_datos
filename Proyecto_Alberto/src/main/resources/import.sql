INSERT INTO doctor (dni, apellido, especialidad, nombre) VALUES ('12345678B', 'Martínez', 'Osteopata', 'María');
INSERT INTO doctor (dni, apellido, especialidad, nombre) VALUES ('23456789C', 'Pérez', 'Fisioterapéuta', 'Pedro');
INSERT INTO doctor (dni, apellido, especialidad, nombre) VALUES ('34567890D', 'Gómez', 'Fisioterapéuta', 'Ana');
INSERT INTO doctor (dni,apellido,especialidad,nombre) VALUES ('18053523D','Garcia','Osteopata','Daniel');

INSERT INTO paciente (dni,apellido,direccion,email,fecha_alta,nombre,telefono,id_doctor) VALUES ('18049608J','Andres Perez','c/Tetilla 12','bakero@hotmail.com','2023-03-11','Jose Miguel',699687316,'18053523D');
INSERT INTO paciente (dni, apellido, direccion, email, fecha_alta, nombre, telefono, id_doctor) VALUES ('12345678A', 'García Pérez', 'Calle Mayor 12', 'garcia@gmail.com', '2023-03-15', 'María', 601234567, '12345678B');
INSERT INTO paciente (dni, apellido, direccion, email, fecha_alta, nombre, telefono, id_doctor) VALUES ('23456789B', 'Martínez Gómez', 'Avenida de la Libertad 20', 'martinez@gmail.com', '2023-03-14', 'Carlos', 602345678, '12345678B');
INSERT INTO paciente (dni, apellido, direccion, email, fecha_alta, nombre, telefono, id_doctor) VALUES ('34567890C', 'López García', 'Plaza Mayor 5', 'lopez@hotmail.com', '2023-03-13', 'Ana', 603456789, '34567890D');
INSERT INTO paciente (dni, apellido, direccion, email, fecha_alta, nombre, telefono, id_doctor) VALUES ('45678901D', 'Rodríguez López', 'Calle San Francisco 7', 'rodriguez@hotmail.com', '2023-03-12', 'Pedro', 604567890, '23456789C');
INSERT INTO paciente (dni, apellido, direccion, email, fecha_alta, nombre, telefono, id_doctor) VALUES ('56789012E', 'Sánchez Martínez', 'Paseo de la Castellana 80', 'sanchez@gmail.com', '2023-03-11', 'Laura', 605678901, '23456789C');