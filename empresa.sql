CREATE TABLE empleados(
    legajo integer NOT NULL,
    dni integer NOT NULL,
    apellido text NOT NULL,
    nombre text NOT NULL,
    fecha_nacimiento date NOT NULL,
    genero character(1) NOT NULL,
    domicilio text,
    localidad text,
    sector text,
    puesto text
);

CREATE TABLE clientes(
    dni integer NOT NULL,
    apellido text NOT NULL,
    nombre text NOT NULL,
    fecha_nacimiento date NOT NULL,
    genero character(1) NOT NULL,
    domicilio text,
    localidad text
);
