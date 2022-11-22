package net.ent.etrs.gestionVoiture.model.daos;

public final class DaoFactory {
    private DaoFactory() {
    }

    public static DaoVoitureMemImpl creerDaoVoiture() {
        return new DaoVoitureMemImpl();
    }
}
