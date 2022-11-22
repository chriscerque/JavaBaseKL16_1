package net.ent.etrs.voiture.model.entities.exceptions;

public class MarqueException extends Exception {
    public MarqueException(String message) {
        super(message);
    }

    public MarqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public MarqueException(Throwable cause) {
        super(cause);
    }

    public MarqueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
