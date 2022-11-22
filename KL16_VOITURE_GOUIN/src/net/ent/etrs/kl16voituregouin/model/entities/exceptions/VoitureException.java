package net.ent.etrs.kl16voituregouin.model.entities.exceptions;

public class VoitureException extends Exception {
    public VoitureException() {
    }

    public VoitureException(String message) {
        super(message);
    }

    public VoitureException(String message, Throwable cause) {
        super(message, cause);
    }

    public VoitureException(Throwable cause) {
        super(cause);
    }

    public VoitureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
