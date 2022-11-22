package net.ent.etrs.voiture.model.daos;

public class DaoFactory {
    public static VoitureDao fabriquerVoiture() {
        return new VoitureDaoImpl();
    }
}
