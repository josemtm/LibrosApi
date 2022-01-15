/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sondeos.codechallenge.exception;

/**
 * Exception class
 *
 * @author Jose Torrealba
 *
 */
public class ObjectNotFoundException extends Exception {

    private static final long serialVersionUID = 7517402519869352660L;

    public ObjectNotFoundException() {
        super();
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String typeObject, String id) {
        super(String.format("No encontro el dato %s con el id: %s", typeObject, id));
    }
}
