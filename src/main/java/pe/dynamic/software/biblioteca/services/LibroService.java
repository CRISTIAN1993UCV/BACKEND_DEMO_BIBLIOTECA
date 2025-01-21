package pe.dynamic.software.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.dynamic.software.biblioteca.dto.LibroDTO;
import pe.dynamic.software.biblioteca.exception.ObjectNotFoundException;
import pe.dynamic.software.biblioteca.persistence.entity.Autor;
import pe.dynamic.software.biblioteca.persistence.entity.Libro;
import pe.dynamic.software.biblioteca.persistence.interfaces.ILibro;
import pe.dynamic.software.biblioteca.persistence.repository.AutorRepo;
import pe.dynamic.software.biblioteca.persistence.repository.LibroRepo;
import pe.dynamic.software.biblioteca.persistence.repository.PrestamoRepo;
import pe.dynamic.software.biblioteca.persistence.util.EstadoLibro;
import pe.dynamic.software.biblioteca.persistence.util.EstadoPrestamo;

import java.util.Optional;

@Service
public class LibroService  implements ILibro {

    @Autowired
    private LibroRepo libroRepo;

    @Autowired
    private AutorRepo autorRepo;
    @Autowired
    private PrestamoRepo prestamoRepo;

    @Override
    public Page<Libro> getLibros(Pageable pageable) {
        return  libroRepo.findAll(pageable);
    }

    @Override
    public Optional<Libro> getLibro(Long idLibro) {
        return libroRepo.findById(idLibro);
    }

    @Override
    public Libro saveLibro(LibroDTO libroDTO) {

        Libro libro = new Libro();

        //BUSCAR AUTOR POR ID
        Autor autorPro = autorRepo.findById(libroDTO.getIdAutor()).orElseThrow(()
                -> new ObjectNotFoundException("Autor no encontrado"));

        libro.setTitulo(libroDTO.getTitulo());
        libro.setAutor(autorPro);
        libro.setIsbn(libroDTO.getIsbn());
        libro.setFechaPublicacion(libroDTO.getFechaPublicacion());
        libro.setEstado(libroDTO.getEstado());

        return libroRepo.save(libro);
    }

    @Override
    public Libro updateLibro(Long idLibro, LibroDTO libroDTO) {

        Libro libro = libroRepo.findById(idLibro)
                .orElseThrow(() -> new ObjectNotFoundException("No se encontró el libro con id: " + idLibro));

        Autor autorPro = autorRepo.findById(libroDTO.getIdAutor()).orElseThrow(()
                -> new ObjectNotFoundException("Autor no encontrado"));

        libro.setTitulo(libroDTO.getTitulo());
        libro.setAutor(autorPro);
        libro.setIsbn(libroDTO.getIsbn());
        libro.setFechaPublicacion(libroDTO.getFechaPublicacion());
        libro.setEstado(libroDTO.getEstado());


        return libroRepo.save(libro);
    }

    @Override
    public void deleteLibro(Long idLibro) {
        libroRepo.deleteById(idLibro);
    }


    public boolean isDisponible(Long idLibro) {
        Libro libro = libroRepo.findById(idLibro)
                .orElseThrow(() -> new ObjectNotFoundException("No existe el libro con id " + idLibro));

        return libro.getEstado() == EstadoLibro.DISPONIBLE;
    }

    
}
