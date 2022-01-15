/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sondeos.codechallenge.service;



import com.sondeos.codechallenge.exception.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;
/**
 * Interface para clases Crud Service
 *
 * @author Jose Torrealba
 *
 */
public interface CrudService<T, ID> {

    T save(T object) throws Exception;

    T update(T object, ID id) throws ObjectNotFoundException;

    Optional<T> findOne(ID id) throws ObjectNotFoundException;

    List<T> findAll();

    void delete(ID id);

    void delete(List<T> id);
}
