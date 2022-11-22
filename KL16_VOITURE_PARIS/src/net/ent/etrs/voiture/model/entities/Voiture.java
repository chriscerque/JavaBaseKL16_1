package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.ConstructionVoitureException;
import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.Couleur;

import java.util.List;
import java.util.Objects;

public class Voiture extends AbstractEntity{

    // attributs

    private String marque;
    private String model;
    private Couleur couleur;
    private List<Piece> pieces;




    // constructeur
    protected Voiture(final String numSerie, final String marque, final String model) throws VoitureException {
        super(numSerie);
        try {
            this.setMarque(marque);
            this.setModel(model);
        }catch (VoitureException e){
            throw new VoitureException(e.getMessage(), e);
        }

    }

    // accesseurs


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

    private void setMarque(String marque) throws VoitureException{
        if (Objects.isNull(marque)){
            throw new VoitureException();
        }
        if (marque.length() < ConstantesMetier.MARQUE_TAILLE_MIN || marque.length() > ConstantesMetier.MARQUE_TAILLE_MAX){
            throw new VoitureException();
        }
        this.marque = marque;
    }

    private void setModel(String model) throws VoitureException{
        if (Objects.isNull(model)){
            throw new VoitureException();
        }
        if (model.length() < ConstantesMetier.MARQUE_TAILLE_MIN || model.length() > ConstantesMetier.MARQUE_TAILLE_MAX){
            throw new VoitureException();
        }
        this.model = model;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    // méthodes

    protected void ajouterPiece(final Piece piece) throws VoitureException{
        if (Objects.isNull(piece)){
            throw new VoitureException();
        }if (this.pieces.contains(piece)){
            throw new VoitureException();
        }
        this.pieces.add(piece);
    }
}
