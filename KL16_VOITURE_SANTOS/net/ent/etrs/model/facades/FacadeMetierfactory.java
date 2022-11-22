package net.ent.etrs.voiture.model.facades;

import net.ent.etrs.voiture.model.facades.exceptions.FacadeMetierImpl;

public class FacadeMetierfactory {
    public static FacadeMetier fabriquerFacadeMetier(){
        return (FacadeMetier) new FacadeMetierImpl();
    }
}
