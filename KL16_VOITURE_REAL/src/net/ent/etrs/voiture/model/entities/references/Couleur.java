package net.ent.etrs.voiture.model.entities.references;

import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;

import java.util.ArrayList;
import java.util.List;

public enum Couleur {

    BORDEAUX("BORDEAUX"),
    JAUNE("JAUNE"),
    GRIS("GRIS"),
    BLANC("BLANC"),
    ROSE("ROSE"),
    NOIR("NOIR"),
    ROUGE("ROUGE");

    private final String libelle;

    Couleur(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public static List<String> getLstLibelleCouleur() {
        List<String> lst = new ArrayList<>();

        for (Couleur c : Couleur.values()) {
            lst.add(c.getLibelle());
        }
        return lst;
    }

    public static Couleur getCouleurByLibelle(final String libelle) throws VoitureException {
        for (Couleur g : Couleur.values()) {
            if (g.getLibelle().equals(libelle)) {
                return g;
            }
        }

        throw new VoitureException("");
    }
}
