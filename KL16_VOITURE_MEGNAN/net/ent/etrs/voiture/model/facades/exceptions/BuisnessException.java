package net.ent.etrs.voiture.voiture.model.facades.exceptions;

public class BuisnessException extends Exception {
    public BuisnessException() {
    }

    public BuisnessException(String message) {
        super(message);
    }

    public BuisnessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BuisnessException(Throwable cause) {
        super(cause);
    }

    public BuisnessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
