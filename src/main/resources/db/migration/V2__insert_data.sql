-- Datos iniciales para la tabla Perfil
INSERT INTO Perfil (nombre) VALUES
('ADMIN'),
('MODERADOR'),
('USUARIO');

-- Datos iniciales para la tabla Usuario
INSERT INTO Usuario (nombre, correoElectronico, contrasena) VALUES
('Joel Smarlin', 'joel@forohub.com', 'hashed_password_1'),
('Maria Perez', 'maria@forohub.com', 'hashed_password_2');

-- Relación Usuario-Perfil
INSERT INTO Usuario_Perfil (usuario_id, perfil_id) VALUES
(1, 1), -- Joel es ADMIN
(2, 3); -- Maria es USUARIO

-- Datos iniciales para la tabla Curso
INSERT INTO Curso (nombre, categoria) VALUES
('Introducción a Spring Boot', 'Programación'),
('Desarrollo Web con Java', 'Desarrollo Web');

-- Datos iniciales para la tabla Tópico
INSERT INTO Tópico (titulo, mensaje, status, autor, curso) VALUES
('¿Cómo configurar Spring Security?', 'Estoy teniendo problemas con la configuración.', 'ABIERTO', 1, 1),
('Errores comunes en Hibernate', '¿Cómo resolver este error?', 'CERRADO', 2, 1);

-- Datos iniciales para la tabla Respuesta
INSERT INTO Respuesta (mensaje, solucion, autor, topico) VALUES
('Revisa la documentación oficial.', TRUE, 2, 1),
('Prueba actualizando las dependencias.', FALSE, 1, 2);
