package net.ent.etrs.model.facades;

public class FacadeMetierFactory {

    private FacadeMetierFactory() {
    }

    public static FacadeMetier fabriquerFacadeMetier(){
        return new FacadeMetierImpl();
    }
}
