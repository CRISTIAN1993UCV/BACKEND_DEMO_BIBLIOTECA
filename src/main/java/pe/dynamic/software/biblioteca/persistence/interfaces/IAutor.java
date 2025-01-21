package pe.dynamic.software.biblioteca.persistence.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.dynamic.software.biblioteca.dto.AutorDTO;
import pe.dynamic.software.biblioteca.persistence.entity.Autor;

import java.util.Optional;

public interface IAutor {

    Page<Autor> getAutors(Pageable pageable);

    Optional<Autor> getAutor(Long idAutor);

    Autor createAutor(AutorDTO autorDTO);

    Autor updateAutor(Long idAutor, AutorDTO autorDTO);

    void deleteAutor(Long idAutor);


}
