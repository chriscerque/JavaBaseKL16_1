package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.model.entities.exceptions.NumSerieException;

import java.util.ArrayList;
import java.util.List;
import net.ent.etrs.model.entities.exceptions.VoitureException;
import net.ent.etrs.model.entities.references.ConstantesMetier;

public class Voiture extends AbstractEntity {

    //ATTRIBUTS
    private String marque;
    private String model;

    private Couleur couleur;

    private List<Piece> pieces = new ArrayList<>();

    //CONSTRUCTEUR

    public Voiture(String numSerie, String marque, String model) throws NumSerieException, VoitureException {

            super(numSerie);

            this.setMarque(marque);
            this.setModel(model);


    }

    //SETTER et GETTER

    public String getMarque() {
        return marque;
    }

    private void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModel() {
        return model;
    }

    private void setModel(String model) {
        this.model = model;
    }

    protected void setCouleur(Couleur couleur){
        this.couleur = couleur;
    }

    public Couleur getCouleur(){
        return this.couleur;
    }

    protected void ajouterPiece(Piece piece){
        pieces.add(piece);
    }

    public List<Piece> getPieces(){
        return this.pieces;
    }
}
