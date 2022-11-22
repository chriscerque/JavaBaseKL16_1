package net.ent.etrs.model.entities;

import net.ent.etrs.model.entities.exceptions.NumSerieException;
import net.ent.etrs.model.entities.exceptions.VoitureException;
import net.ent.etrs.model.entities.references.Couleur;

import java.util.List;

public class Voiture extends AbstractEntity{

    private String marque;
    private String model;
    private Couleur couleur;
    private List<Piece> pieces;


    // constructeur
    public Voiture(final String numSerie, final String marque, final String model) throws NumSerieException, VoitureException {
        super(numSerie);
        setMarque(marque);
        setModel(model);
    }


    protected void ajouterPiece(final Piece piece) throws VoitureException {
        for (Piece p : pieces) {
            if(p == piece){
                throw new VoitureException("la piece existe déja");
            }
        }
        pieces.add(piece);
    }

    // setters
    public void setMarque(final String marque) throws VoitureException {
        if(marque == null | marque.length() < 3 | marque.length() > 30){
            throw new VoitureException();
        }
        this.marque = marque;
    }
    public void setModel(final String model) throws VoitureException {
        if(model == null | model.length() < 3 | model.length() > 30){
            throw new VoitureException();
        }
        this.model = model;
    }
    public void setCouleur(final Couleur couleur) throws VoitureException {
        if(couleur == null){
            throw new VoitureException();
        }
        this.couleur = couleur;
    }

    // getters
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
}
