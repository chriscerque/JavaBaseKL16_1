package net.ent.etrs.model.daos;

import net.ent.etrs.model.entities.Voiture;

public class DaoFactory {

    public static DaoVoiture fabriquerDaoVoiture() {
        return new DaoVoitureImpl();
    }
}
