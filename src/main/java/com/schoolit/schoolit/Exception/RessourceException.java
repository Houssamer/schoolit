package com.schoolit.schoolit.Exception;

public class RessourceException extends RuntimeException{
    public RessourceException(String message) {
        super(message);
    }

    public RessourceException() {
        super("Ressource introuvable");
    }
}
