package net.ent.etrs.model.entities;

import net.ent.etrs.model.entities.exceptions.NumSerieException;
import net.ent.etrs.model.entities.exceptions.VoitureException;
import net.ent.etrs.model.entities.references.ConstantesMetier;
import net.ent.etrs.model.entities.references.Couleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Voiture extends AbstractEntity {

    //Attributs

    private String marque;

    private String model;

    private Couleur couleur;

    private List<Piece> pieces = new ArrayList<>();

    //Constructeur

    protected Voiture(String numSerie, String marque, String model) throws NumSerieException {
        super(numSerie);
        this.marque = marque;
        this.model = model;
    }


    //Getters setters

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) throws VoitureException {
        if (Objects.isNull(marque) || model.isBlank()) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MARQUE_NON_RENSEIGNE_EXCEPTION);
        }
        if (marque.length() < ConstantesMetier.MARQUE_TAILLE_MIN || model.length() > ConstantesMetier.MODEL_TAILLE_MAX) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MARQUE_TAILLE_EXCEPTION);
        }
        this.marque = marque;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) throws VoitureException {
        if (Objects.isNull(model) || model.isBlank()) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MODEL_NON_RENSEIGNE_EXCEPTION);
        }
        if (model.length() < ConstantesMetier.MODEL_TAILLE_MIN || model.length() > ConstantesMetier.MODEL_TAILLE_MAX) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MODEL_TAILLE_EXCEPTION);
        }
        this.model = model;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) throws VoitureException {
        if (Objects.isNull(couleur)) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_COULEUR_NON_RENSEIGNE_EXCEPTION);
        }
        this.couleur = couleur;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    //Ajouter une pièce a une voiture.

    public void ajouterPiece(Piece piece) throws VoitureException {
        if (Objects.isNull(piece)) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECE_NON_RENSEIGNE_EXCEPTION);
        }
        if (this.pieces.contains(piece)) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECE_EXISTE_EXCEPTION);
        }
        this.pieces.add(piece);
    }
}
