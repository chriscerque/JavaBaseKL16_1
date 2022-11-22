package net.ent.etrs.gestionVoiture.model.entities.references;

import java.util.ArrayList;
import java.util.List;

public enum Couleur {
    BORDEAUX("bordeaux"),
    JAUNE("jaune"),
    GRIS("gris"),
    BLANC("blanc"),
    ROSE("rose"),
    NOIR("noir"),
    ROUGE("rouge");

    private String libelle;

    Couleur(final String libelle) {
        this.libelle = libelle;
    }

    public static List<String> getAllLibelles() {
        List<String> lst = new ArrayList<>();
        for (Couleur c : Couleur.values()) {
            lst.add(c.getLibelle());
        }
        return lst;
    }

    public String getLibelle() {
        return this.libelle;
    }
}
