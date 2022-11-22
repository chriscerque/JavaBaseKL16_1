package net.ent.etrs.voiture.model.daos;

public final class DaoFactory {

    private DaoFactory() {
    }

    public static VoitureDao fabriquerDaoVoiture(){
        return new VoitureDaoImpl();
    }

    public static PieceDao fabriquerDaoPiece(){
        return new PieceDaoImpl();
    }
}
