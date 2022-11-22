package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.entities.Voiture;

/**
 * Fabrique de {@link VoitureDaoImpl}.
 */
public final class DaoFactory {
    //region CONSTRUCTEUR(S)

    private DaoFactory() {
    }

    //endregion
    //region MÉTHODES

    /**
     * Fabrique une DAO pour la gestion des entités @see {@link Voiture}.
     *
     * @return une instance de {@link VoitureDaoImpl}.
     */
    public static VoitureDaoImpl fabriquervoituredao() {
        return new VoitureDaoImpl();
    }
    //endregion
}
