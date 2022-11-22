package net.ent.etrs.voiture.model.daos;
import net.ent.etrs.voiture.model.entities.Voiture;

public final class DaoFactory {
    private static DaoVoitureImpl daoVoitureImpl;

    /**
     * Fabrique une DAO pour la gestion des entités {@link Voiture}.
     * @return l'instance de {@link DaoVoitureImpl}
     */
    public static DaoVoiture fabriquerDaoVoiture() {
        return new DaoVoitureImpl();
    }
}
