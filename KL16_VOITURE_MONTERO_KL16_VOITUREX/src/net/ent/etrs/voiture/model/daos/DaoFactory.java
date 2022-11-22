package net.ent.etrs.voiture.model.daos;


public final class DaoFactory {


    /**
     * Fabrique une DAO pour la gestion des entités {@link net.ent.etrs.voiture.model.entities.Voiture}.
     *
     * @return l'instance de {@link DaoVoitureImpl}
     */
    public static DaoVoiture fabriquerDaoVoitureImplDao() {
        return new DaoVoitureImpl();
    }

}
