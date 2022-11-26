package net.ent.etrs.model.daos;

import net.ent.etrs.voiture.model.daos.DaoVoiture;
import net.ent.etrs.voiture.model.daos.DaoVoitureImpl;
import net.ent.etrs.voiture.model.entities.Voiture;

public class DaoFactory {

    public static DaoVoiture fabriquerVoitureDao(){
        return new DaoVoitureImpl();
    }

}
