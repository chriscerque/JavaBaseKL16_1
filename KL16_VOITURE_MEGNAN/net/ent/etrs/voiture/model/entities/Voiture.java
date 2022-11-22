package net.ent.etrs.voiture.voiture.model.entities;

import net.ent.etrs.voiture.voiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.voiture.model.entities.references.Couleur;

import java.util.List;

public class Voiture extends AbstractEntity{

    //Attributs
    private String marque;
    private String model;
    private Couleur couleur;
    private List<Piece> pieces;

    //Constructeur


    public Voiture(String numSerie, String marque, String model) {
        super(numSerie);
        this.marque = marque;
        this.model = model;
    }

    //Ascesseurs
    public String getMarque() {
        return this.marque;
    }

    public String getModel() {
        return this.model;
    }

    public Couleur getCouleur() {
        return this.couleur;
    }

    public List<Piece> getPieces() {
        return this.pieces;
    }

    public void setModel(final String model) throws VoitureException {
        if(this.model.isEmpty()){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MODEL_NON_RENSEIGNE_EXCEPTION);
        } else if (this.model.length() < ConstantesMetier.MODEL_TAILLE_MIN || this.model.length() > ConstantesMetier.MODEL_TAILLE_MAX) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MODEL_TAILLE_EXCEPTION);
        }
        this.model = model;
    }

    public void setCouleur(final Couleur couleur) {
        this.couleur = couleur;
    }

    private void setMarque(final String marque) throws VoitureException {
        if(this.marque.isEmpty()){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MARQUE_NON_RENSEIGNE_EXCEPTION);
        } else if (this.marque.length() < ConstantesMetier.MODEL_TAILLE_MIN || this.marque.length() > ConstantesMetier.MODEL_TAILLE_MAX) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MARQUE_TAILLE_EXCEPTION);
        }
        this.marque = marque;
    }

    //Autres méthodes
    protected void ajouterPiece(final Piece piece){
        pieces.add(piece);
    }
}
