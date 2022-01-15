/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sondeos.codechallenge.controller;

import com.sondeos.codechallenge.exception.DuplicatedObjectDataException;
import com.sondeos.codechallenge.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 * Interface para Clases del controlador Crud
 *
 * @author Jose Torrealba
 *
 */
public interface ControllerRestCrud<T, ID> {

    /**
     * Obtiene un elemento {@link T} dado su id.
     *
     * @param id id del elemento a buscar.
     * @return elemento asociado con el id especificado.
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{id}"}, produces = {"application/json"})
    T doGet(@PathVariable ID id) throws ObjectNotFoundException;

    /**
     * Obtiene todos los objetos {@link T}.
     *
     * @return lista de objeos {@link T}.
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = {RequestMethod.GET}, produces = {"application/json"})
    List<T> doGet();


    /**
     * Almacena una instancia de {@link T},
     *
     * @param ObjectT objeto {@link T} a almacenar.
     * @return onjeto {@link T} guardado.
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = {RequestMethod.POST}, produces = {"application/json"})
    T doPost(@Valid @RequestBody T ObjectT) throws DuplicatedObjectDataException, Exception;

    /**
     * Actualiza un objeto {@link T}.
     *
     * @param ObjectT objeto {@link T} a actualizar.
     * @param id      id del objeto {@link T} a actualizar.
     * @throws ObjectNotFoundException si no existe ningun elemento con el id
     *                                 especificado.
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = {RequestMethod.PUT}, value = {"/{id}"}, produces = {"application/json"})
    void doPut(@Valid @RequestBody T ObjectT, @PathVariable ID id) throws Exception;

    /**
     * Borra un objeto {@link T} dado su id.
     *
     * @param id id del objeto a borrar.
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = {RequestMethod.DELETE}, value = {"/{id}"}, produces = {"application/json"})
    void doDelete(@PathVariable ID id) throws ObjectNotFoundException;

    /**
     * Borra los objetos {@link T} que esten asociados a los ids especificados.
     *
     * @param ids lista de ids de los objetos a borrar.
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = {RequestMethod.DELETE}, produces = {"application/json"})
    void doDelete(@RequestParam List<T> ids) throws ObjectNotFoundException;
}
