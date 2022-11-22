package net.ent.etrs.KL16_VOITURE.Model.Facades;

public final class FacadeMetierFactory {
    private FacadeMetierFactory() {
    }

    public static FacadeMetier fabriquerFacadeMetier(){
        return new FacadeMetierImpl();
    }
    }

