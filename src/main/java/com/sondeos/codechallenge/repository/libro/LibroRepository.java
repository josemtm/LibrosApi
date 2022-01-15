package com.sondeos.codechallenge.repository.libro;


import com.sondeos.codechallenge.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface utilizada para dar funcion al repositorio de libros
 *
 * @author Jose Torrealba
 *
 */
public interface LibroRepository extends JpaRepository<Libro, String> {
}
