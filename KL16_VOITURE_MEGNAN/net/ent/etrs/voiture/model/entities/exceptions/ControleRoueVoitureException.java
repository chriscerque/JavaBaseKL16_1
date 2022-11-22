package net.ent.etrs.voiture.voiture.model.entities.exceptions;

public class ControleRoueVoitureException extends Exception {
    public ControleRoueVoitureException() {
    }

    public ControleRoueVoitureException(String message) {
        super(message);
    }

    public ControleRoueVoitureException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControleRoueVoitureException(Throwable cause) {
        super(cause);
    }

    public ControleRoueVoitureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
