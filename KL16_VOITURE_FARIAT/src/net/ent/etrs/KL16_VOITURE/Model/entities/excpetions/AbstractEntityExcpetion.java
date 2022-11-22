package net.ent.etrs.KL16_VOITURE.Model.entities.excpetions;

public class AbstractEntityExcpetion extends Exception {
    public AbstractEntityExcpetion(final String message) {
        super(message);
    }

    public AbstractEntityExcpetion(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AbstractEntityExcpetion(final Throwable cause) {
        super(cause);
    }

    public AbstractEntityExcpetion(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
