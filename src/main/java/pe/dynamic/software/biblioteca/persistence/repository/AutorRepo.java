package pe.dynamic.software.biblioteca.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.dynamic.software.biblioteca.persistence.entity.Autor;

public interface AutorRepo extends JpaRepository<Autor, Long> {
}
