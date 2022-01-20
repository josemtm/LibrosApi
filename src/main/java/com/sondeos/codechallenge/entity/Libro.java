package com.sondeos.codechallenge.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
/**
 * Entidad utilizada para guardar informacion de libros y realizar peticiones
 * @author Jose Torrealba
 *
 */
@Entity
@Data
@Table(uniqueConstraints= @UniqueConstraint(columnNames = {"titulo", "autor"}) )
public class Libro  implements Serializable {

    private static final long serialVersionUID = -5168569355148403347L;
    /**
     * columna de identificacion de la entidad
     */
    @Id
    @GeneratedValue(generator="uuid2")
    @GenericGenerator(name="uuid2",strategy = "uuid2")
    private String id;

    /**
     * Titulo del libro
     */
    @NotBlank
    private String titulo;
    /**
     * Titulo del autor del libro
     */
    @NotBlank
    private String autor;
    /**
     * Precio asociado al libro
     */
    @Min(0)
    @NotNull
    private Double precio;
    /**
     * Fecha de lanzamiento de libro
     */
    @NotNull
    private Date fechaLanzamiento;

    public Libro() {
    }

    public Libro(String titulo, String autor, Double precio, Date fechaLanzamiento) {
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.fechaLanzamiento = fechaLanzamiento;
    }
}
