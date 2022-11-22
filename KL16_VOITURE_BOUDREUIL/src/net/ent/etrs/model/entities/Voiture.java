package net.ent.etrs.model.entities;

import net.ent.etrs.model.entities.exceptions.NumSerieException;
import net.ent.etrs.model.entities.exceptions.VoitureException;
import net.ent.etrs.model.entities.references.ConstantesMetier;
import net.ent.etrs.model.entities.references.Couleur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Voiture extends AbstractEntity{

    // Attribut(s)

    private String marque;
    private String model;
    private Couleur couleur;
    private List<Piece> pieces = new ArrayList();

    // Constructeur(s)

    public Voiture(final String numSerie, final String marque, final String model) throws NumSerieException, VoitureException {
        super(numSerie);
        this.setMarque(marque);
        this.setModel(model);
    }

    // Accesseur(s)
    public String getMarque() {
        return this.marque;
    }

    private void setMarque(final String marque) throws VoitureException {
        if (marque == null || marque.isBlank()) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MARQUE_NON_RENSEIGNE_EXCEPTION);
        }
        if (marque.length() < ConstantesMetier.MARQUE_TAILLE_MIN || marque.length() > ConstantesMetier.MARQUE_TAILLE_MAX){
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
        if (model.length() < ConstantesMetier.MODEL_TAILLE_MIN || model.length() > ConstantesMetier.MODEL_TAILLE_MAX){
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
        if (pieces.isEmpty()) {
            throw new VoitureException(ConstantesMetier.VOITURE_PIECES_VIDE_EXCEPTION);
        }
        return Collections.unmodifiableList(this.pieces);
    }
    // Autres méthode(s)

    protected void ajouterPiece(Piece piece) throws VoitureException {
        if (piece == null) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECE_NON_RENSEIGNE_EXCEPTION);
        }
        if(pieces.contains(piece)){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECE_EXISTE_EXCEPTION);
        }
        pieces.add(piece);
    }
}
