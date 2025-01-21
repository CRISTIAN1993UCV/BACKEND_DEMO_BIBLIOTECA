package pe.dynamic.software.biblioteca.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.dynamic.software.biblioteca.persistence.util.EstadoLibro;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibro;

    @Column(nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "idAutor", nullable = false)
    private Autor autor;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = true)
    private LocalDate fechaPublicacion;

    @Enumerated(EnumType.STRING)
    private EstadoLibro estado;

}
