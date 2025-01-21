package pe.dynamic.software.biblioteca.persistence.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.dynamic.software.biblioteca.dto.LibroDTO;
import pe.dynamic.software.biblioteca.persistence.entity.Libro;

import java.util.Optional;

public interface ILibro {

    Page<Libro> getLibros(Pageable pageable);

    Optional<Libro> getLibro(Long idLibro);

    Libro saveLibro(LibroDTO libroDTO);

    Libro updateLibro(Long idLibro, LibroDTO libroDTO);

    void deleteLibro(Long idLibro);
}
