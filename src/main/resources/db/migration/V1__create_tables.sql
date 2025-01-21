-- Tabla Usuario
CREATE TABLE Usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correoElectronico VARCHAR(150) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL
);

-- Tabla Perfil
CREATE TABLE Perfil (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

-- Relación Usuario-Perfil (muchos a muchos)
CREATE TABLE Usuario_Perfil (
    usuario_id BIGINT NOT NULL,
    perfil_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, perfil_id),
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (perfil_id) REFERENCES Perfil(id) ON DELETE CASCADE
);

-- Tabla Curso
CREATE TABLE Curso (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL
);

-- Tabla Tópico
CREATE TABLE Tópico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    mensaje TEXT NOT NULL,
    fechaCreacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL,
    autor BIGINT NOT NULL,
    curso BIGINT NOT NULL,
    FOREIGN KEY (autor) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (curso) REFERENCES Curso(id) ON DELETE CASCADE
);

-- Tabla Respuesta
CREATE TABLE Respuesta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje TEXT NOT NULL,
    fechaCreacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    solucion BOOLEAN DEFAULT FALSE,
    autor BIGINT NOT NULL,
    topico BIGINT NOT NULL,
    FOREIGN KEY (autor) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (topico) REFERENCES Tópico(id) ON DELETE CASCADE
);
