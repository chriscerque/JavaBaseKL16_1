package net.ent.etrs.model.daos;

public class DaoFactory {

    public static DaoVoiture fabriquerVoitureDao(){
        return new DaoVoitureImpl();
    }

}
