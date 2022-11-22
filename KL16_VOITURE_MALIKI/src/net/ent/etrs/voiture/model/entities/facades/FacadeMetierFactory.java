package net.ent.etrs.voiture.model.entities.facades;

/**
 * The type Facade metier factory.
 */
public class FacadeMetierFactory {
    /**
     * Fabriquer facade metier facade metier.
     *
     * @return the facade metier
     */
    public static FacadeMetier fabriquerFacadeMetier(){
        return new FacadeMetierImpl();
    }
}
