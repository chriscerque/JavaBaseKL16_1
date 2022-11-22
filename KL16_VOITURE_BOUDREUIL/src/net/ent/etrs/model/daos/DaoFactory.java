package net.ent.etrs.model.daos;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.model.entities.Voiture;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DaoFactory {

    private static DaoVoitureImpl daoVoitureInstance;

    /**
     * Fabrique une DAO pour la gestion des entités {@link Voiture}.
     * @return l'instance de {@link DaoVoitureImpl}
     */
    public static DaoVoiture fabriquerDaoVoiture() {
        if (daoVoitureInstance == null) {
            daoVoitureInstance = new DaoVoitureImpl();
        }
        return daoVoitureInstance;
    }
}
