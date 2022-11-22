package net.ent.etrs.voiture.model.entities.exceptions;

public class CouleurException extends Exception {
    public CouleurException(String message) {
        super(message);
    }

    public CouleurException(String message, Throwable cause) {
        super(message, cause);
    }

    public CouleurException(Throwable cause) {
        super(cause);
    }

    public CouleurException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
