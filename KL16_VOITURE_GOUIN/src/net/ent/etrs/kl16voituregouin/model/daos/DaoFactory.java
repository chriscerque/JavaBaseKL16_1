package net.ent.etrs.kl16voituregouin.model.daos;

public final class DaoFactory {

    private DaoFactory() {
    }
    public static DaoVoiture fabriquerVoitureDao() {
        return new DaoVoitureImpl();
    }
}
