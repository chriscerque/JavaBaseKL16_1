package net.ent.etrs.voiture.model.daos;

public final class DaoFactory {

    public static DaoVoiture fabriquerDaoVoiture(){
        return new DaoVoitureImpl();
    }

}
