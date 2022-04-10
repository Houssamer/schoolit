package com.schoolit.schoolit.Exception;

public class CoursException extends RuntimeException {

    public CoursException(String message) {
        super(message);
    }

    public CoursException() {
        super("Cours introuvable");
    }
}
