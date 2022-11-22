package net.ent.etrs.model.facades;

public final class FacadeMetierFactory {

    public static FacadeMetier fabriquerFacadeMetier(){
        return new FacadeMetierImpl() {
        }
    }


}
