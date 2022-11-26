package net.ent.etrs.model.entities.exceptions;

public class ConstructionPieceException extends Exception {
    public ConstructionPieceException() {
    }

    public ConstructionPieceException(String message) {
        super(message);
    }

    public ConstructionPieceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConstructionPieceException(Throwable cause) {
        super(cause);
    }

    public ConstructionPieceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
