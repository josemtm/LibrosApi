package com.sondeos.codechallenge.service.libro;

import com.sondeos.codechallenge.entity.Libro;
import com.sondeos.codechallenge.service.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface para la capa de servicio del crud de libros
 *
 * @author Jose Torrealba
 *
 */
public interface LibrosService extends CrudService<Libro, String> {
    Page<Libro> getPaginator(Pageable pageable);

}
