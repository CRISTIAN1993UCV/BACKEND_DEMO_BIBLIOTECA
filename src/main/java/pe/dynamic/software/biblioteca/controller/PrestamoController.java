package pe.dynamic.software.biblioteca.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.dynamic.software.biblioteca.dto.PrestamoDTO;
import pe.dynamic.software.biblioteca.exception.ObjectNotFoundException;
import pe.dynamic.software.biblioteca.persistence.entity.Prestamo;
import pe.dynamic.software.biblioteca.services.PrestamoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/prestamo")
@CrossOrigin(origins = "*")
@Tag(name = "Préstamos", description = "Operaciones relacionadas con los préstamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @Operation(summary = "Listar todos los préstamos", description = "Retorna una lista paginada de todos los préstamos realizados.")
    @GetMapping("/listarPrestamos")
    public ResponseEntity<Page<Prestamo>> listarPrestamos(Pageable pageable) {
        Page<Prestamo> prestamo = prestamoService.getPrestamos(pageable);
        return ResponseEntity.ok(prestamo);
    }

    @Operation(summary = "Listar préstamos de un libro", description = "Lista todos los préstamos asociados a un libro específico por su ID.")
    @GetMapping("/listarPorLibro/{libroId}")
    public ResponseEntity<List<Prestamo>> listarPrestamosPorLibro(@PathVariable Long libroId) {
        List<Prestamo> prestamos = prestamoService.getPrestamosPorLibro(libroId);
        return ResponseEntity.ok(prestamos);
    }

    @Operation(summary = "Buscar un préstamo por ID", description = "Obtiene un préstamo específico por su ID.")
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Prestamo> buscarPrestamo(@PathVariable Long id) {
        Optional<Prestamo> prestamo = prestamoService.getPrestamo(id);
        if (prestamo.isPresent()) {
            return ResponseEntity.ok(prestamo.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Agregar un nuevo préstamo", description = "Crea un nuevo préstamo en el sistema.")
    @PostMapping("/agregarPrestamo")
    public ResponseEntity<Prestamo> agregarPrestamo(@RequestBody PrestamoDTO prestamoDTO) {
        Prestamo prestamoSave = prestamoService.savePrestamo(prestamoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamoSave);
    }

    @Operation(summary = "Actualizar un préstamo", description = "Actualiza los detalles de un préstamo existente por su ID.")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Prestamo> actualizarPrestamo(@PathVariable Long id, @RequestBody PrestamoDTO prestamoDTO) {
        Prestamo prestamoSave = prestamoService.updatePrestamo(id, prestamoDTO);
        return ResponseEntity.ok(prestamoSave);
    }

    @Operation(summary = "Eliminar un préstamo", description = "Elimina un préstamo por su ID del sistema.")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, String>> eliminarPrestamo(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            prestamoService.deletePrestamo(id);
            response.put("mensaje", "El préstamo fue eliminado");
            return ResponseEntity.ok(response);
        } catch (ObjectNotFoundException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
