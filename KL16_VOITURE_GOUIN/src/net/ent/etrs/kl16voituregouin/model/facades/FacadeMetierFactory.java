package net.ent.etrs.kl16voituregouin.model.facades;

public final class FacadeMetierFactory {

    private FacadeMetierFactory() {
    }

    public static FacadeMetier fabriquerFacadeMetier(){
        return new FacadeMetierImpl();
    }
}
