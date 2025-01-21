package pe.dynamic.software.biblioteca.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.dynamic.software.biblioteca.dto.AutorDTO;
import pe.dynamic.software.biblioteca.dto.LibroDTO;
import pe.dynamic.software.biblioteca.exception.ObjectNotFoundException;
import pe.dynamic.software.biblioteca.persistence.entity.Autor;
import pe.dynamic.software.biblioteca.services.AutorService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/autor")
@CrossOrigin(origins = "*")
@Tag(name = "Autores", description = "Operaciones relacionadas con los autores")
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class AutorController {

    @Autowired
    private AutorService autorService;

    @Operation(summary = "Listar autores", description = "Retorna una lista paginada de todos los autores.")
    @GetMapping("/listarAutores")
    public ResponseEntity<Page<Autor>> listarAutores(Pageable pageable) {
        Page<Autor> listar = autorService.getAutors(pageable);
        return ResponseEntity.ok(listar);
    }

    @Operation(summary = "Buscar autor", description = "Busca un autor por su ID.")
    @GetMapping("/buscar/{idAutor}")
    public ResponseEntity<Autor> buscarAutor(@PathVariable Long idAutor) {
        Optional<Autor> autor = autorService.getAutor(idAutor);
        if(autor.isPresent()){
            return ResponseEntity.ok(autor.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Agregar autor", description = "Crea un nuevo autor en el sistema.")
    @PostMapping("/agregarAutor")
    public ResponseEntity<Autor> agregarAutor(@RequestBody AutorDTO autorDTO) {
        Autor autorSave = autorService.createAutor(autorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(autorSave);
    }

    @Operation(summary = "Actualizar autor", description = "Actualiza los datos de un autor existente.")
    @PutMapping("/actualizar/{idAutor}")
    public ResponseEntity<Autor> actualizarAutor(@PathVariable Long idAutor, @RequestBody AutorDTO autorDTO) {
        Autor autorSave = autorService.updateAutor(idAutor,autorDTO);
        return ResponseEntity.ok(autorSave);
    }

    @Operation(summary = "Eliminar autor", description = "Elimina un autor por su ID.")
    @DeleteMapping("/eliminar/{idAutor}")
    public ResponseEntity<Map<String, String>> eliminarAutor(@PathVariable Long idAutor) {
        Map<String, String> response = new HashMap<>();
        try {
            autorService.deleteAutor(idAutor);
            response.put("mensaje", "El Autor fue eliminado");
            return ResponseEntity.ok(response);
        } catch (ObjectNotFoundException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }



}
