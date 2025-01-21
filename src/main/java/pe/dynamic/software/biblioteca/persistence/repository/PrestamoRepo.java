package pe.dynamic.software.biblioteca.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.dynamic.software.biblioteca.persistence.entity.Prestamo;

import java.util.List;

public interface PrestamoRepo extends JpaRepository<Prestamo, Long> {

    @Query("SELECT p FROM Prestamo p WHERE p.libro.idLibro = :libroId")
    List<Prestamo> findByLibroId(@Param("libroId") Long libroId);
}
