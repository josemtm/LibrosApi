package com.sondeos.codechallenge.controller.libro;


import com.sondeos.codechallenge.entity.Libro;
import com.sondeos.codechallenge.exception.DuplicatedObjectDataException;
import com.sondeos.codechallenge.exception.ObjectNotFoundException;
import com.sondeos.codechallenge.service.libro.LibroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 * Controlador principal para el crud de libro
 *
 * @author Jose Torrealba
 *
 */
@RestController
@RequestMapping(value = {"/sondeos/api/challenge/v1/libros"}, produces = {"application/json"})
public class LibroControllerImpl implements LibroController {

    private final LibroServiceImpl service;

    @Autowired
    public LibroControllerImpl(LibroServiceImpl service) {
        this.service = service;

    }
    /**
     * Get individual de libros
     * @param id id del objeto a solicitar
     * @return respuesta con el libro solicitado
     * @since 1.0
     */
    @Override
    public Libro doGet(@PathVariable String id) throws ObjectNotFoundException {
        return this.service.findOne(id).get();
    }
    /**
     * Get de todos los libros
     * @return lista de todos los libros
     * @since 1.0
     */
    @Override
    public List<Libro> doGet() {
        return this.service.findAll();
    }
    /**
     * Post para guardar libros en el sistema
     * @param ObjectT tipo Libro para persistir
     * @return objeto persistido
     * @since 1.0
     */
    @Override
    public Libro doPost(@Valid @RequestBody Libro ObjectT) throws DuplicatedObjectDataException {
        try {
            return this.service.save(ObjectT);
        } catch (Exception ex) {
            throw new DuplicatedObjectDataException(ex.getMessage());
        }
    }
    /**
     * Put para actualizar entidades en sistemas
     * @param ObjectT objeto con los datos a persistir
     * @param id del objeto
     * @return response de status http
     * @since 1.0
     */
    @Override
    public void doPut(@Valid @RequestBody Libro ObjectT, @PathVariable String id) throws ObjectNotFoundException {
        this.service.update(ObjectT, id);
    }
    /**
     * Delete para eliminar objeto de la tabla libro
     * @param id del objeto
     * @return response de status http
     * @since 1.0
     */
    @Override
    public void doDelete(@PathVariable String id) throws ObjectNotFoundException {
        this.service.delete(id);
    }
    /**
     * Delete para borrar listas de datos
     * @param ids del tipo String
     * @return response de status http
     * @since 1.0
     */
    @Override
    public void doDelete(@Valid @RequestParam  List<Libro> ids) throws ObjectNotFoundException {
        this.service.delete(ids);
    }

}
