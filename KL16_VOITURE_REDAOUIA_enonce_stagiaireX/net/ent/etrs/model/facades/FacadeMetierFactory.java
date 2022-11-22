package net.ent.etrs.voiture.model.facades;

public class FacadeMetierFactory {

    public FacadeMetierFactory() {
    }

    public static FacadeMetierImpl fabriquerFacadeMetier(){
        return new FacadeMetierImpl();
    }
}
