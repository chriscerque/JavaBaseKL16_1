package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.Couleur;

import java.util.List;
import java.util.Objects;

/**
 * Classe héritant de {@link Entity}.
 * Représentant une voiture dans l'application.
 */
public class Voiture extends Entity {
    //region ATTRIBUTS
    private String marque;
    private String model;
    private Couleur couleur;
    private List<Piece> pieces;
    //endregion
    //region CONSTRUCTEUR(S)

    protected Voiture(final String numSerie, final String model, final String marque) throws NumSerieException, VoitureException {
        super(numSerie);
        this.setModel(model);
        this.setMarque(marque);
    }

    //endregion
    //region GETTER SETTER

    public List<Piece> getPieces() {
        return this.pieces;
    }

    public String getMarque() {
        return this.marque;
    }

    private void setMarque(final String marque) throws VoitureException {
        if (Objects.isNull(marque) || marque.isBlank()) {
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

    public void setModel(final String model) throws VoitureException {
        if (Objects.isNull(model) || model.isBlank()) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MODEL_NON_RENSEIGNE_EXCEPTION);
        }
        if (model.length() < ConstantesMetier.MARQUE_TAILLE_MIN || model.length() > ConstantesMetier.MARQUE_TAILLE_MAX) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MODEL_TAILLE_EXCEPTION);
        }
        this.model = model;
    }

    public Couleur getCouleur() {
        return this.couleur;
    }

    public void setCouleur(final Couleur couleur) throws VoitureException {
        if (Objects.isNull(couleur)) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_COULEUR_NON_RENSEIGNE_EXCEPTION);
        }
        this.couleur = couleur;
    }

    protected void ajouterPiece(final Piece piece) throws VoitureException {
        if (Objects.isNull(piece)) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECE_NON_RENSEIGNE_EXCEPTION);
        }
        if (this.pieces.contains(piece)) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECE_EXISTE_EXCEPTION);
        }
        this.pieces.add(piece);
    }
    //endregion
}
