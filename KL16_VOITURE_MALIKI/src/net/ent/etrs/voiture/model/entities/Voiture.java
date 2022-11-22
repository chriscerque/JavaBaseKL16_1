package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.*;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.Couleur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * The type Voiture.
 */
public class Voiture extends AbstractEntity{

 //ATTRIBUTS
 private String marque;
 private String model;
 private Couleur couleur;
 private List<Piece> lstPieces = new ArrayList<>();

    //CONSTRUCTEURS

    /**
     * Instantiates a new Abstract entity.
     *
     * @param numSerie the num serie
     * @param marque   the marque
     * @param model    the model
     * @throws NumSerieException            the num serie exception
     * @throws ConstructionVoitureException the construction voiture exception
     */
    protected Voiture(final String numSerie,final String marque, final String model) throws NumSerieException, ConstructionVoitureException {
                super(numSerie);
        try {
            this.setMarque(marque);
            this.setModel(model);
        } catch (MarqueException | ModelException e) {
            throw new ConstructionVoitureException(e.getMessage(),e);
        }

    }


 //ACCESSEURS

    /**
     * Gets marque.
     *
     * @return the marque
     */
    public String getMarque() {
        return marque;
    }

    /**
     * Sets marque.
     *
     * @param marque the marque
     * @throws MarqueException the marque exception
     */
    private void setMarque(final String marque) throws MarqueException {
        if (marque == null || marque.length() < ConstantesMetier.MODEL_TAILLE_MIN || marque.length()>ConstantesMetier.MARQUE_TAILLE_MIN) {
            throw new MarqueException(ConstantesMetier.MSG_VOITURE_MARQUE_TAILLE_EXCEPTION);
        }
        this.marque = marque;
    }

    /**
     * Gets model.
     *
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets model.
     *
     * @param model the model
     * @throws ModelException the model exception
     */
    private void setModel(final String model) throws ModelException {
        if (model == null || model.length() < ConstantesMetier.MODEL_TAILLE_MIN || model.length()>ConstantesMetier.MODEL_TAILLE_MAX) {
            throw new ModelException(ConstantesMetier.MSG_VOITURE_MODEL_TAILLE_EXCEPTION);
        }
        this.model = model;
    }

    /**
     * Gets couleur.
     *
     * @return the couleur
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * Sets couleur.
     *
     * @param couleur the couleur
     * @throws CouleurException the couleur exception
     */
    public void setCouleur(final Couleur couleur) throws CouleurException {
        if (couleur == null ) {
            throw new CouleurException(ConstantesMetier.MSG_VOITURE_COULEUR_NON_RENSEIGNE_EXCEPTION);
        }
        this.couleur = couleur;
    }

    /**
     * Gets lst pieces.
     *
     * @return the lst pieces
     */
    public List<Piece> getLstPieces() {
        return Collections.unmodifiableList(this.lstPieces);
    }

    /**
     * Ajouter piece.
     *
     * @param piece the piece
     * @throws PieceException the piece exception
     */
    protected void ajouterPiece(final Piece piece) throws PieceException {
        if (Objects.isNull(piece)){
            throw new PieceException();
        }
        if (this.lstPieces.contains(piece)){
            throw new PieceException(ConstantesMetier.MSG_VOITURE_PIECE_EXISTE_EXCEPTION);
        }
        this.lstPieces.add(piece);
    }


    //EQUALS & HASHCODE

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    //TOSTRING


 //AUTRES METHODES

}
