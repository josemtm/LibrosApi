package com.sondeos.codechallenge.service.libro;

import com.sondeos.codechallenge.entity.Libro;
import com.sondeos.codechallenge.exception.ObjectNotFoundException;
import com.sondeos.codechallenge.repository.libro.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Capa del servicio de crud de libros
 *
 * @author Jose Torrealba
 *
 */
@Service
public class LibroServiceImpl implements LibrosService {

    private final LibroRepository repositorio;

    @Autowired
    public LibroServiceImpl(LibroRepository repositorio) {
        this.repositorio = repositorio;

    }
    /**
     * Metodo que persiste libros en el sistema
     * @param object del tipo Libro
     * @return Entidad persistida
     * @since 1.0
     */
    @Override
    public Libro save(Libro object) throws Exception {
        return this.repositorio.save(object);
    }
    /**
     * Metodo actualiza registros en la tabla libros
     * @param object del tipo Libro
     * @param id del libro
     * @return Entidad persistida
     * @since 1.0
     */
    @Override
    public Libro update(Libro object, String id) throws ObjectNotFoundException {
        Optional<Libro> result = findOne(id);

        if (result.isPresent()) {
            object.setId(id);
            return repositorio.save(object);
        }
        throw new ObjectNotFoundException("Objeto no encontrado");
    }
    /**
     * Metodo que busca libro por id
     * @param id del libro
     * @return objeto Libro
     * @since 1.0
     */
    @Override
    public Optional<Libro> findOne(String id) throws ObjectNotFoundException {
        Optional<Libro>result = repositorio.findById(id);
        if (result.isPresent()) {
            return result;
        }
        throw new ObjectNotFoundException("Objeto no encontrado");
    }
    /**
     * Metodo que busca lista completa de libros
     * @return Lista de Libros
     * @since 1.0
     */
    @Override
    public List<Libro> findAll() {
        return this.repositorio.findAll(/* sortByIdAsc()*/);
    }
    /**
     * Metodo que borra objetos de la tabla de libros
     * @param id del libro
     * @since 1.0
     */
    @Override
    public void delete(String id) {
        this.repositorio.deleteById(id);
    }
    /**
     * Metodo que borra lista de libros del sistema
     * @param ids ids de los libros a borrar
     * @since 1.0
     */
    @Override
    public void delete(List<Libro> ids) {
        this.repositorio.deleteInBatch(ids);
    }


    @Override
    public Page<Libro> getPaginator(Pageable pageable) {
        return this.repositorio.findAll(pageable);
    }
}
