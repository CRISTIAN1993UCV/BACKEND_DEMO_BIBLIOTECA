package pe.dynamic.software.biblioteca.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.dynamic.software.biblioteca.dto.LibroDTO;
import pe.dynamic.software.biblioteca.exception.ObjectNotFoundException;
import pe.dynamic.software.biblioteca.persistence.entity.Libro;
import pe.dynamic.software.biblioteca.persistence.repository.LibroRepo;
import pe.dynamic.software.biblioteca.persistence.util.EstadoLibro;
import pe.dynamic.software.biblioteca.services.LibroService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/libro")
@CrossOrigin(origins = "*")
@Tag(name = "Libros", description = "Operaciones relacionadas con los libros")
public class LibroController {

    @Autowired
    private LibroService libroService;
    @Autowired
    private LibroRepo libroRepo;

    @Operation(summary = "Listar libros", description = "Retorna una lista paginada de todos los libros.")
    @GetMapping("/listarLibros")
    public ResponseEntity<Page<Libro>> listarLibros(Pageable pageable) {
        Page<Libro> listar = libroService.getLibros(pageable);
        return ResponseEntity.ok(listar);
    }

    @Operation(summary = "Verificar disponibilidad", description = "Verifica si un libro está disponible para préstamo.")
    @GetMapping("/verificarDisponibilidad/{idLibro}")
    public ResponseEntity<Map<String, Object>> verificarDisponibilidad(@PathVariable Long idLibro) {
        boolean disponible = libroService.isDisponible(idLibro);
        Map<String, Object> response = new HashMap<>();
        response.put("libroId", idLibro);
        response.put("disponible", disponible);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar libro por ID", description = "Busca un libro específico por su ID.")
    @GetMapping("/buscar/{idLibro}")
    public ResponseEntity<Libro> buscarLibro(@PathVariable Long idLibro) {
        Optional<Libro> libro = libroService.getLibro(idLibro);
        if (libro.isPresent()) {
            return ResponseEntity.ok(libro.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Agregar un nuevo libro", description = "Agrega un nuevo libro al sistema.")
    @PostMapping("/agregarLibro")
    public ResponseEntity<Libro> agregarLibro(@RequestBody LibroDTO libroDTO) {
        Libro libroSave = libroService.saveLibro(libroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(libroSave);
    }

    @Operation(summary = "Actualizar un libro", description = "Actualiza la información de un libro existente.")
    @PutMapping("/actualizar/{idLibro}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long idLibro, @RequestBody LibroDTO libroDTO) {
        Libro libroSave = libroService.updateLibro(idLibro, libroDTO);
        return ResponseEntity.ok(libroSave);
    }

    @Operation(summary = "Eliminar un libro", description = "Elimina un libro del sistema por su ID.")
    @DeleteMapping("/eliminar/{idLibro}")
    public ResponseEntity<Map<String, String>> eliminarLibro(@PathVariable Long idLibro) {
        Map<String, String> response = new HashMap<>();
        try {
            libroService.deleteLibro(idLibro);
            response.put("mensaje", "El libro fue eliminado");
            return ResponseEntity.ok(response);
        } catch (ObjectNotFoundException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


}
