package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.Couleur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Voiture extends AbstractEntity {

    //attribut(s)
    private String marque;
    private String model;
    private Couleur couleur;
    private List<Piece> pieces = new ArrayList<>();

    //constructeur(s)
    protected Voiture(final String numSerie, final String marque, final String model) throws VoitureException, NumSerieException {
        super(numSerie);
        this.setMarque(marque);
        this.setModel(model);
    }

    //getter et setter

    public String getMarque() {
        return this.marque;
    }

    private void setMarque(final String marque) throws VoitureException {
        if (marque == null || marque.isBlank()) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MARQUE_NON_RENSEIGNE_EXCEPTION);
        }
        if (marque.length() < 3 || marque.length() > 20) {
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
        if (model.length() < 3 || model.length() > 20) {
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

    public List<Piece> getPieces() throws VoitureException {
        if (pieces.size() == 0) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECES_VIDE_EXCEPTION);
        }
        return Collections.unmodifiableList(this.pieces);
    }

    protected void ajouterPiece(final Piece piece) throws VoitureException {
        if (piece == null) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECE_NON_RENSEIGNE_EXCEPTION);
        }
        if (pieces.contains(piece)) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECE_EXISTE_EXCEPTION);
        }

        pieces.add(piece);
    }


    //egal et hash


    //to string

    //autre(s) methode(s)


}
