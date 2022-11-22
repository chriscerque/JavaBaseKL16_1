package net.ent.etrs.KL16_VOITURE.Model.entities.excpetions;

public class PieceException extends Exception {
    public PieceException() {
    }

    public PieceException(String message) {
        super(message);
    }

    public PieceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PieceException(Throwable cause) {
        super(cause);
    }

    public PieceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
