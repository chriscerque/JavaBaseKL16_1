package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.references.Couleur;

import java.util.List;

public class Voiture extends AbstractEntity {

    // ---------------------------------------
    //                Constantes
    // ---------------------------------------
    private String marque;
    private String model;
    private Couleur couleur;
    private List<Piece> pieces;

    // ---------------------------------------
    //                Constructeur
    // ---------------------------------------
    public Voiture(String numSerie, String marque, String model) {
        super(numSerie);
        this.marque = marque;
        this.model = model;
    }

    // ---------------------------------------
    //                Accesseurs
    // ---------------------------------------
    private void setModel(String model) {
        this.model = model;
    }

    protected void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public String getMarque() {
        return marque;
    }

    public String getModel() {
        return model;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * ajouterPiece.
     * @param p
     */
    protected void ajouterPiece(Piece p){
        pieces.add(p);
    }


}
