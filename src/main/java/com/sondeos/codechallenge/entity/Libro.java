package com.sondeos.codechallenge.entity;

import com.sondeos.codechallenge.entity.superclass.ID;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
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
public class Libro extends ID implements Serializable {

    private static final long serialVersionUID = -5168569355148403347L;
    /**
     * Titulo del libro
     */
    @Getter
    @Setter
    @NotBlank
    private String titulo;
    /**
     * Titulo del autor del libro
     */
    @Getter
    @Setter
    @NotBlank
    private String autor;
    /**
     * Precio asociado al libro
     */
    @Getter
    @Setter
    @Min(1)
    private Double precio;
    /**
     * Fecha de lanzamiento de libro
     */
    @Getter
    @Setter
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
