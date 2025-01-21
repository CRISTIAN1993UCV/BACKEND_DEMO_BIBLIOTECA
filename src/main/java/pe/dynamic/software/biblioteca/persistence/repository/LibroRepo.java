package pe.dynamic.software.biblioteca.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.dynamic.software.biblioteca.persistence.entity.Libro;
import pe.dynamic.software.biblioteca.persistence.util.EstadoPrestamo;

public interface LibroRepo extends JpaRepository<Libro, Long> {

}
