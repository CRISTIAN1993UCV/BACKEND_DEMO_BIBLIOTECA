-- ===========================================
-- =========== DATOS DE EJEMPLO =============
-- ===========================================

-- 1) Insertar múltiples autores
INSERT INTO tb_autor (id_autor, nombre, nacionalidad, fecha_nacimiento)
VALUES
    (1, 'Gabriel García Márquez', 'Colombiana', TO_DATE('1927-03-06','YYYY-MM-DD')),
    (2, 'Mario Vargas Llosa',    'Peruana',     TO_DATE('1936-03-28','YYYY-MM-DD')),
    (3, 'Isabel Allende',        'Chilena',     TO_DATE('1942-08-02','YYYY-MM-DD'));

-- 2) Insertar libros (asociados a los autores anteriores)
INSERT INTO tb_libro (id_libro, titulo, id_autor, isbn, fecha_publicacion, estado)
VALUES
    (1, 'Cien años de soledad',        1, 'ISBN-1234', TO_DATE('1967-05-30','YYYY-MM-DD'), 'DISPONIBLE'),
    (2, 'El amor en los tiempos del cólera', 1, 'ISBN-5678', TO_DATE('1985-05-28','YYYY-MM-DD'), 'DISPONIBLE'),
    (3, 'La ciudad y los perros',      2, 'ISBN-9999', TO_DATE('1963-01-01','YYYY-MM-DD'), 'DISPONIBLE'),
    (4, 'La casa de los espíritus',    3, 'ISBN-1111', TO_DATE('1982-01-01','YYYY-MM-DD'), 'DISPONIBLE');

-- 3) Insertar préstamos
INSERT INTO tb_prestamo (id_prestamo, id_libro, fecha_prestamo, fecha_devolucion, estado)
VALUES
    (1, 1, TO_DATE('2025-01-10 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-02-10', 'YYYY-MM-DD'), 'ACTIVO'),
    (2, 2, TO_DATE('2025-01-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-02-15 ', 'YYYY-MM-DD'), 'FINALIZADO'),
    (3, 3, TO_DATE('2025-01-20 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-03-01 ', 'YYYY-MM-DD'), 'ACTIVO'),
    (4, 4, TO_DATE('2025-02-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-02-15 ', 'YYYY-MM-DD '), 'FINALIZADO');
