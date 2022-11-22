package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.references.Couleur;

import java.util.ArrayList;
import java.util.List;

public class Voiture {

    /**
     * Classe représantant la voiture.
     */

    //-------------------------------
    //          ATTRIBUTS
    //-------------------------------

    private String marque;
    private String model;
    private Couleur couleur;
    private List <Piece> pieces = new ArrayList<>();

    /**
     * Constructeur.
     * @param marque
     * @param model
     * @param couleur
     */

    public Voiture(String marque, String model, Couleur couleur) {
        this.marque = marque;
        this.model = model;
        this.couleur = couleur;
    }


    /**
     * GETTER
     * @return
     */

    public String getMarque() {
        return this.marque = marque;
    }

    public String getModel() {
        return this.model = model;
    }

    public Couleur getCouleur() {
        return this.couleur = couleur;
    }

    public List<Piece> getPieces() {
        return this.pieces = pieces;
    }


    /**
     * SETTER
     * @param marque
     */
    private void setMarque(String marque) {
        this.marque = marque;
    }

    private void setModel(String model) {
        this.model = model;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    void ajouterPiece() {

    }


}
