-- ============================================
-- ============== TABLA: AUTOR ===============
-- ============================================
CREATE TABLE tb_autor (
                          idAutor NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                          nombre VARCHAR2(100) NOT NULL,
                          nacionalidad VARCHAR2(50) NOT NULL,
                          fechaNacimiento DATE NOT NULL
);

-- ============================================
-- ============== TABLA: LIBRO ===============
-- ============================================
CREATE TABLE tb_libro (
                          idLibro NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                          titulo VARCHAR2(150) NOT NULL,
                          idAutor NUMBER NOT NULL,
                          isbn VARCHAR2(20) UNIQUE NOT NULL,
                          fechaPublicacion DATE,
                          estado VARCHAR2(20) NOT NULL CHECK (estado IN ('DISPONIBLE', 'NO DISPONIBLE')),

    -- Relación con la tabla tb_autor
                          CONSTRAINT fk_libro_autor FOREIGN KEY (idAutor) REFERENCES tb_autor (idAutor)
);

-- ============================================
-- ============== TABLA: PRÉSTAMO =============
-- ============================================
CREATE TABLE tb_prestamo (
                             idPrestamo NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                             idLibro NUMBER NOT NULL,
                             fechaPrestamo TIMESTAMP NOT NULL,
                             fechaDevolucion TIMESTAMP NOT NULL,
                             estado VARCHAR2(20) NOT NULL CHECK (estado IN ('ACTIVO', 'FINALIZADO')),

    -- Relación con la tabla tb_libro
                             CONSTRAINT fk_prestamo_libro FOREIGN KEY (idLibro) REFERENCES tb_libro (idLibro)
);
