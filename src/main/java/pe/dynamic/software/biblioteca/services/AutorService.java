package pe.dynamic.software.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.dynamic.software.biblioteca.dto.AutorDTO;
import pe.dynamic.software.biblioteca.exception.ObjectNotFoundException;
import pe.dynamic.software.biblioteca.persistence.entity.Autor;
import pe.dynamic.software.biblioteca.persistence.interfaces.IAutor;
import pe.dynamic.software.biblioteca.persistence.repository.AutorRepo;

import java.util.Optional;

@Service
public class AutorService implements IAutor {

    @Autowired
    private AutorRepo autorRepo;

    @Override
    public Page<Autor> getAutors(Pageable pageable) {
        return  autorRepo.findAll(pageable);
    }

    @Override
    public Optional<Autor> getAutor(Long idAutor) {
        return autorRepo.findById(idAutor);
    }

    @Override
    public Autor createAutor(AutorDTO autorDTO) {

        Autor autor = new Autor();
        autor.setNombre(autorDTO.getNombre());
        autor.setNacionalidad(autorDTO.getNacionalidad());
        autor.setFechaNacimiento(autorDTO.getFechaNacimiento());

        return autorRepo.save(autor);
    }

    @Override
    public Autor updateAutor(Long idAutor, AutorDTO autorDTO) {

        Autor autorPro = autorRepo.findById(idAutor).orElseThrow(()
                -> new ObjectNotFoundException("Autor no encontrado"));

        autorPro.setNombre(autorDTO.getNombre());
        autorPro.setNacionalidad(autorDTO.getNacionalidad());
        autorPro.setFechaNacimiento(autorDTO.getFechaNacimiento());

        return autorRepo.save(autorPro);
    }

    @Override
    public void deleteAutor(Long idAutor) {
        autorRepo.deleteById(idAutor);
    }
}
