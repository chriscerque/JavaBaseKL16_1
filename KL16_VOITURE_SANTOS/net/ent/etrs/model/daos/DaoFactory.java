package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.daos.exceptions.DaoVoiture;
import net.ent.etrs.voiture.model.entities.Voiture;

public final class DaoFactory {
    private static DaoVoiture VoitureDaoInstance;
    public static DaoVoitureImpl persistenceMemoireFactory() {
        return new DaoVoitureImpl() {
        };

    }
    public static pieceDao fabriquerPiece() {
        return new pieceDao();
    }
    public static Voiture fabriquerVoitureDao() {
        if (VoitureDaoInstance == null){
            VoitureDaoInstance = new DaoVoitureImpl();
        }
        return (Voiture) VoitureDaoInstance;
    }

    public static DaoVoitureImpl fabriquerMaterielInformatiqueDao() {
        return null;
    }
}
