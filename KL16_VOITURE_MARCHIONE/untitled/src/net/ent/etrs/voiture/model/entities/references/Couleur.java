package net.ent.etrs.voiture.model.entities.references;

/**
 * Enumération regroupant toutes les couleurs.
 * Bordeaux, Jaune, Gris, Blanc, Rose, Noir, Rouge
 */
public enum Couleur {

    //-------------------------------
    //          ATTRIBUTS
    //-------------------------------

    BORDEAUX("Bordeaux"),
    JAUNE("Jaune"),
    GRIS("Gris"),
    BLANC("Blanc"),
    ROSE("Rose"),
    NOIR("Noir"),
    ROUGE("Rouge");

    private final String couleur;

    /**
     * Constructeur.
     * @param couleur
     */
    Couleur(String couleur) {
        this.couleur = couleur;
    }

    /**
     * GETTER couleur.
     * @return
     */
    public String getCouleur() {
        return this.couleur;
    }

    /**
     * Methode values()
     */
    final Couleur[] couleurs = Couleur.values();


//    /**
//     * Methode ValueOf()
//     */
//    Couleur couleurJaune = Enum.valueOf(Couleur.class, "JAUNE");

}
