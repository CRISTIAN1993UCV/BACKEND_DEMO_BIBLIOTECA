package pe.dynamic.software.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.dynamic.software.biblioteca.dto.PrestamoDTO;
import pe.dynamic.software.biblioteca.exception.ObjectNotFoundException;
import pe.dynamic.software.biblioteca.persistence.entity.Libro;
import pe.dynamic.software.biblioteca.persistence.entity.Prestamo;
import pe.dynamic.software.biblioteca.persistence.interfaces.IPrestamo;
import pe.dynamic.software.biblioteca.persistence.repository.LibroRepo;
import pe.dynamic.software.biblioteca.persistence.repository.PrestamoRepo;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService implements IPrestamo {

    @Autowired
    private PrestamoRepo prestamoRepo;

    @Autowired
    private LibroRepo libroRepo;

    @Override
    public Page<Prestamo> getPrestamos(Pageable pageable) {
        return prestamoRepo.findAll(pageable);
    }

    @Override
    public Optional<Prestamo> getPrestamo(Long idPrestamo) {
        return prestamoRepo.findById(idPrestamo);
    }

    @Override
    public Prestamo savePrestamo(PrestamoDTO prestamoDTO) {

        Libro libro = libroRepo.findById(prestamoDTO.getLibroId())
                .orElseThrow(() -> new ObjectNotFoundException("Libro no encontrado"));


        Prestamo prestamo = new Prestamo();
        prestamo.setFechaPrestamo(prestamoDTO.getFechaPrestamo());
        prestamo.setFechaDevolucion(prestamoDTO.getFechaDevolucion());
        prestamo.setEstado(prestamoDTO.getEstado());
        prestamo.setLibro(libro);

        return  prestamoRepo.save(prestamo);
    }

    @Override
    public Prestamo updatePrestamo(Long idPrestamo, PrestamoDTO prestamoDTO) {

        Prestamo prestamo = prestamoRepo.findById(idPrestamo)
                .orElseThrow(() -> new ObjectNotFoundException("Préstamo no encontrado"));

        // Verificar que el libro esté disponible
        Libro libro = libroRepo.findById(prestamoDTO.getLibroId())
                .orElseThrow(() -> new ObjectNotFoundException("Libro no encontrado"));

        prestamo.setFechaPrestamo(prestamoDTO.getFechaPrestamo());
        prestamo.setFechaDevolucion(prestamoDTO.getFechaDevolucion());
        prestamo.setEstado(prestamoDTO.getEstado());
        prestamo.setLibro(libro);

        return prestamoRepo.save(prestamo);
    }

    @Override
    public void deletePrestamo(Long idPrestamo) {
        prestamoRepo.deleteById(idPrestamo);
    }

    public List<Prestamo> getPrestamosPorLibro(Long idLibro) {
        return prestamoRepo.findByLibroId(idLibro);
    }




}
