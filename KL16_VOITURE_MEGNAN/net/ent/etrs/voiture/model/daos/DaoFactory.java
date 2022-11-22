package net.ent.etrs.voiture.voiture.model.daos;

public class DaoFactory {

    private DaoFactory() {
    }

    public static DaoVoitureImpl fabriquerDao(){
        return new DaoVoitureImpl();
    }
}
