package net.ent.etrs.KL16_VOITURE.Model.DAOS;

public final class DaoFactory {


    public static DaoVoiture fabriquerDaoVoiture;
    //ATTRIBUTS
    private static DaoVoitureImpl DaoVoitureInstance;

    public static DaoVoiture fabriquerDaoVoiture(){
        return new DaoVoitureImpl();
    }


}
