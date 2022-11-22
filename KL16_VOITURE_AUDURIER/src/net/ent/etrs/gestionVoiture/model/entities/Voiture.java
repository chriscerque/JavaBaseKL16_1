package net.ent.etrs.gestionVoiture.model.entities;

import net.ent.etrs.gestionVoiture.model.entities.exceptions.ConstructionVoitureException;
import net.ent.etrs.gestionVoiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.gestionVoiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.gestionVoiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.gestionVoiture.model.entities.references.Couleur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Voiture extends AbstractEntity {
    //Attributs
    private String marque;
    private String model;
    private Couleur couleur;
    private List<Piece> pieces = new ArrayList<>();


    //Constructor
    protected Voiture(final String numSerie, final String marque, final String model)
            throws NumSerieException, ConstructionVoitureException {
        super(numSerie);
        try {
            this.setMarque(marque);
            this.setModel(model);
        } catch (VoitureException e) {
            throw new ConstructionVoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION);
        }
    }
    //Getter/Setter

    public String getMarque() {
        return this.marque;
    }

    private void setMarque(final String marque) throws VoitureException {
        if (marque == null || marque.isBlank()) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MARQUE_NON_RENSEIGNE_EXCEPTION);
        }
        if (marque.length() < ConstantesMetier.MARQUE_TAILLE_MIN || marque.length() > ConstantesMetier.MARQUE_TAILLE_MAX) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MARQUE_TAILLE_EXCEPTION);
        }
        this.marque = marque;
    }

    public String getModel() {
        return this.model;
    }

    private void setModel(final String model) throws VoitureException {
        if (model == null || model.isBlank()) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MODEL_NON_RENSEIGNE_EXCEPTION);
        }
        if (model.length() < ConstantesMetier.MODEL_TAILLE_MIN || model.length() > ConstantesMetier.MODEL_TAILLE_MAX) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MODEL_TAILLE_EXCEPTION);
        }
        this.model = model;
    }

    public Couleur getCouleur() {
        return this.couleur;
    }

    protected void setCouleur(final Couleur couleur) throws VoitureException {
        if (couleur == null) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_COULEUR_NON_RENSEIGNE_EXCEPTION);
        }
        this.couleur = couleur;
    }

    public List<Piece> getPieces() {
        return Collections.unmodifiableList(this.pieces);
    }

    //Other methods
    protected void ajouterPiece(final Piece piece) throws VoitureException {
        if (piece == null) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECE_NON_RENSEIGNE_EXCEPTION);
        }
        if (pieces.contains(piece)) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECE_EXISTE_EXCEPTION);
        }
        pieces.add(piece);
    }
    //Equals/Hashcode

    //To String

    @Override
    public String toString() {
        return "Voiture{" +
                "marque='" + marque + '\'' +
                ", model='" + model + '\'' +
                ", couleur='" + couleur + '\'' +
                "} " + super.toString();
    }
}
