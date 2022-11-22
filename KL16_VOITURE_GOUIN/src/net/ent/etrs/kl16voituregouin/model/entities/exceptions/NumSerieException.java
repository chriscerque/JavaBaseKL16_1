package net.ent.etrs.kl16voituregouin.model.entities.exceptions;

public class NumSerieException extends Exception {
    public NumSerieException() {
    }

    public NumSerieException(String message) {
        super(message);
    }

    public NumSerieException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumSerieException(Throwable cause) {
        super(cause);
    }

    public NumSerieException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
