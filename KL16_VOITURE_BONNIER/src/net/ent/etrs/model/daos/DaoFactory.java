package net.ent.etrs.model.daos;

public final class DaoFactory {

    private static VoitureDaoImpl voitureDaoInstance;


    public static VoitureDaoImpl fabriquerVoitureDao() {
        if (voitureDaoInstance == null) {
            voitureDaoInstance = new VoitureDaoImpl();
        }
        return voitureDaoInstance;
    }

}
