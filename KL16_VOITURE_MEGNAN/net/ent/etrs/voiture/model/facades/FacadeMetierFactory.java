package net.ent.etrs.voiture.voiture.model.facades;

public class FacadeMetierFactory {

    //Constructeur
    private FacadeMetierFactory(){
    }

    //Autre methode
    public static FacadeMetierImpl fabriquerFacademetier(){
        return new FacadeMetierImpl();
    }
}
