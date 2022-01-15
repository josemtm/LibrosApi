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
public class DuplicatedObjectDataException extends Exception {

    private static final long serialVersionUID = -3308050758983922769L;

    public DuplicatedObjectDataException() {
        super();
    }

    public DuplicatedObjectDataException(String message) {
        super(message);
    }

    public DuplicatedObjectDataException(String typeObject, String id) {
        super(String.format("el dato %s con el id %s ya se encuentra almacenado en la base de datos.", typeObject, id));
    }
}
