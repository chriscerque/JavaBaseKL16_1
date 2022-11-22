package net.ent.etrs.voiture.voiture.model.entities.exceptions;

public class ConstructionVoitureException extends Exception {
    public ConstructionVoitureException() {
    }

    public ConstructionVoitureException(String message) {
        super(message);
    }

    public ConstructionVoitureException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConstructionVoitureException(Throwable cause) {
        super(cause);
    }

    public ConstructionVoitureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
