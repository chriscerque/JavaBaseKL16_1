package net.ent.etrs.voiture.model.facades;

public final class FacadeMetierFactory {

    private FacadeMetierFactory() {}

    public static FacadeMetier fabriquerFacadeMetier(){
        return new FacadeMetierImpl();
    }
}
