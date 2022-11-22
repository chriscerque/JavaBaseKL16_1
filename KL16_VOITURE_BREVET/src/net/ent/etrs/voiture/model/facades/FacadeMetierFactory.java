package net.ent.etrs.voiture.model.facades;

public final class FacadeMetierFactory {
    //region CONSTRUCTEUR(S)

    private FacadeMetierFactory() {
    }

    //endregion
    //region MÉTHODES
    public static FacadeMetier fabriquerFacadeMtier() {
        return new FacadeMetierImpl();
    }
    //endregion
}
