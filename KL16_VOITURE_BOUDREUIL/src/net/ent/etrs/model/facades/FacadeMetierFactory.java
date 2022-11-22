package net.ent.etrs.model.facades;

public class FacadeMetierFactory {

    public static FacadeMetier fabriquerFacadeMetier(){
        return new FacadeMetierImpl();
    }
}
