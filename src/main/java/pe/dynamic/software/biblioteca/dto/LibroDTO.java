package pe.dynamic.software.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pe.dynamic.software.biblioteca.persistence.util.EstadoLibro;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class LibroDTO implements Serializable {

    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 50, message = "El título no debe exceder los 50 caracteres")
    private String titulo;

    @NotNull(message = "El ID del autor es obligatorio")
    private Long idAutor;

    @NotBlank(message = "El ISBN no puede estar vacío")
    @Size(min = 10, max = 13, message = "El ISBN debe tener entre 10 y 13 caracteres")
    private String isbn;

    @PastOrPresent(message = "La fecha de publicación no puede ser futura")
    private LocalDate fechaPublicacion;

    @NotNull(message = "El estado del libro es obligatorio")
    private EstadoLibro estado;




}
