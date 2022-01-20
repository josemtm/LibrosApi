package com.sondeos.codechallenge.controller.libro;


import com.sipios.springsearch.anotation.SearchSpec;
import com.sondeos.codechallenge.entity.Libro;
import com.sondeos.codechallenge.exception.DuplicatedObjectDataException;
import com.sondeos.codechallenge.exception.ObjectNotFoundException;
import com.sondeos.codechallenge.repository.libro.LibroRepositorySearchs;
import com.sondeos.codechallenge.service.libro.LibroServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final LibroRepositorySearchs repositorySearchs;
    @Autowired
    public LibroControllerImpl(LibroServiceImpl service,LibroRepositorySearchs repositorySearchs) {
        this.service = service;
        this.repositorySearchs = repositorySearchs;
    }
    /**
     * Get individual de libros
     * @param id id del objeto a solicitar
     * @return respuesta con el libro solicitado
     * @since 1.0
     */
    @Operation(summary = "Busca libro por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Libro.class)) }),
            @ApiResponse(responseCode = "404", description = "Object not found",
                    content = @Content) })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = {RequestMethod.GET}, produces = {"application/json"})
    public Libro doGet(@RequestParam String id) throws ObjectNotFoundException {
        return this.service.findOne(id).get();
    }
    /**
     * Get de todos los libros
     * @return lista de todos los libros
     * @since 1.0
     */
    @Operation(summary = "Busca libro por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Libro.class)) }),
            @ApiResponse(responseCode = "404", description = "Object not found",
                    content = @Content) })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/paginator"} ,method = {RequestMethod.GET}, produces = {"application/json"})
    public Page<Libro> doGetPaginator(Pageable pageable) {
        return this.service.getPaginator(pageable);
    }
    /**
     * Post para guardar libros en el sistema
     * @param ObjectT tipo Libro para persistir
     * @return objeto persistido
     * @since 1.0
     */
    @Operation(summary = "Crear libro por json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Libro.class)) }),
            @ApiResponse(responseCode = "409", description = "Object has already been persisted.",
                    content = @Content) })
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = {RequestMethod.POST}, produces = {"application/json"})
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
    @Operation(summary = "actualizar libro por json e id por url")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Libro.class)) }),
            @ApiResponse(responseCode = "404", description = "Object not found",
                    content = @Content) })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = {RequestMethod.PUT}, value = {"/{id}"}, produces = {"application/json"})
    public void doPut(@Valid @RequestBody Libro ObjectT, @PathVariable String id) throws ObjectNotFoundException {
        this.service.update(ObjectT, id);
    }
    /**
     * Delete para eliminar objeto de la tabla libro
     * @param id del objeto
     * @return response de status http
     * @since 1.0
     */
    @Operation(summary = "Borra un registro por medio de la id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Libro.class)) }),
            @ApiResponse(responseCode = "404", description = "Object not found",
                    content = @Content) })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = {RequestMethod.DELETE}, value = {"/{id}"}, produces = {"application/json"})
    public void doDelete(@PathVariable String id) throws ObjectNotFoundException {
        this.service.delete(id);
    }
    /**
     * Delete para borrar listas de datos
     * @param ids del tipo String
     * @return response de status http
     * @since 1.0
     */
    @Operation(summary = "Borra libro por lista de id en url")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Libro.class)) }),
            @ApiResponse(responseCode = "404", description = "Object not found",
                    content = @Content) })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = {RequestMethod.DELETE}, produces = {"application/json"})
    public void doDelete(@Valid @RequestParam  List<Libro> ids) throws ObjectNotFoundException {
        this.service.delete(ids);
    }
    @Operation(summary = "Crear libro por json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Libro.class)) })
             })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/search"}, method = {RequestMethod.GET}, produces = {"application/json"})
    public ResponseEntity<List<Libro>> libroSearch(@SearchSpec(caseSensitiveFlag = false) Specification<Libro> specs) {
        return new ResponseEntity(repositorySearchs.findAll(Specification.where(specs)), HttpStatus.OK);
    }

}
