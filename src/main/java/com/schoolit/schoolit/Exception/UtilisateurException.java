package com.schoolit.schoolit.Exception;

public class UtilisateurException extends RuntimeException {

    public UtilisateurException() {
        super("Utilisateur n'est pas trouve");
    }

    public UtilisateurException(String message) {
        super(message);
    }
}
