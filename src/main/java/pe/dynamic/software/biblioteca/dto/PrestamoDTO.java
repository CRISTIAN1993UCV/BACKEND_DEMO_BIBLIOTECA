package pe.dynamic.software.biblioteca.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pe.dynamic.software.biblioteca.persistence.util.EstadoPrestamo;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PrestamoDTO implements Serializable {


    private Long libroId;

    @NotNull(message = "La fecha de préstamo es obligatoria")
    @FutureOrPresent(message = "La fecha de préstamo no puede ser en el pasado")
    private LocalDate fechaPrestamo;

    @NotNull(message = "La fecha de devolución es obligatoria")
    @FutureOrPresent(message = "La fecha de devolución debe ser igual o posterior a la fecha de préstamo")
    private LocalDate fechaDevolucion;

    @NotNull(message = "El estado del préstamo es obligatorio")
    private EstadoPrestamo estado;




}
