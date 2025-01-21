package pe.dynamic.software.biblioteca.persistence.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.dynamic.software.biblioteca.dto.PrestamoDTO;
import pe.dynamic.software.biblioteca.persistence.entity.Prestamo;

import java.util.Optional;

public interface IPrestamo {

    Page<Prestamo> getPrestamos(Pageable pageable);

    Optional<Prestamo> getPrestamo(Long idPrestamo);

    Prestamo savePrestamo(PrestamoDTO prestamoDTO);

    Prestamo updatePrestamo(Long idPrestamo , PrestamoDTO prestamoDTO);

    void deletePrestamo(Long idPrestamo);
}
