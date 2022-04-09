package com.schoolit.schoolit.Exception;

public class FormationException extends RuntimeException {
    public FormationException(String message) {
        super(message);
    }

    public FormationException() {
        super("Formation introuvable");
    }
}
