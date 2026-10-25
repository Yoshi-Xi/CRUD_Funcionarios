CREATE DATABASE IF NOT EXISTS gestion_rrhh;
USE gestion_rrhh;

-- 1. Tabla Funcionarios
CREATE TABLE funcionarios (
    id_funcionario INT AUTO_INCREMENT PRIMARY KEY,
    tipo_identificacion VARCHAR(20) NOT NULL,
    numero_identificacion VARCHAR(20) UNIQUE NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    estado_civil VARCHAR(50),
    sexo VARCHAR(20),
    direccion VARCHAR(150),
    telefono VARCHAR(20),
    fecha_nacimiento DATE
);

-- 2. Tabla Grupo Familiar
CREATE TABLE grupo_familiar (
    id_familiar INT AUTO_INCREMENT PRIMARY KEY,
    id_funcionario INT NOT NULL,
    nombre_completo VARCHAR(150) NOT NULL,
    rol VARCHAR(50) NOT NULL,
    tipo_identificacion VARCHAR(20),
    numero_identificacion VARCHAR(20),
    fecha_nacimiento DATE,
    sexo VARCHAR(20),
    FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id_funcionario) ON DELETE CASCADE
);

-- 3. Tabla Formación Académica
CREATE TABLE formacion_academica (
    id_formacion INT AUTO_INCREMENT PRIMARY KEY,
    id_funcionario INT NOT NULL,
    universidad VARCHAR(150) NOT NULL,
    nivel_estudio VARCHAR(50) NOT NULL,
    titulo_estudio VARCHAR(150) NOT NULL,
    FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id_funcionario) ON DELETE CASCADE
);

-- =========================================
-- POBLADO (Datos iniciales para que la app arranque)
-- =========================================

-- Insertamos 1 Funcionario
INSERT INTO funcionarios (tipo_identificacion, numero_identificacion, nombres, apellidos, estado_civil, sexo, direccion, telefono, fecha_nacimiento) 
VALUES ('CC', '10203040', 'Juan', 'Perez', 'Casado', 'M', 'Calle 123', '3001234567', '1990-05-15');

-- Insertamos 2 miembros del Grupo Familiar para Juan Perez (id 1)
INSERT INTO grupo_familiar (id_funcionario, nombre_completo, rol, tipo_identificacion, numero_identificacion, fecha_nacimiento, sexo) 
VALUES 
(1, 'Maria Gomez', 'Esposa', 'CC', '10203041', '1992-08-20', 'F'),
(1, 'Pedrito Perez', 'Hijo', 'TI', '10987654', '2015-03-10', 'M');

-- Insertamos la Formación Académica de Juan Perez (id 1)
INSERT INTO formacion_academica (id_funcionario, universidad, nivel_estudio, titulo_estudio) 
VALUES 
(1, 'Universidad de Antioquia', 'Pregrado', 'Ingeniería de Sistemas'),
(1, 'Universidad Nacional', 'Posgrado', 'Especialización en Software');