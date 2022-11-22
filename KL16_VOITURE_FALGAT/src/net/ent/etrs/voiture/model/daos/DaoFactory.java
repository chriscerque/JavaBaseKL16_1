package net.ent.etrs.voiture.model.daos;

public final class DaoFactory {

    private DaoFactory() {}

    public static DaoVoiture fabriquerDaoVoiture(){
        return new DaoVoitureImpl();
    }

}
