package net.ent.etrs.voiture.model.daos;

public class DaoFactory {

    public static DaoVoiture fabriquerDaoVoiture(){
        return new DaoVoitureImpl();
    }

    public static DaoPiece fabriquerDaoPiece(){
        return new DaoPieceImpl();
    }
}
