package net.ent.etrs.model.entities;


import net.ent.etrs.voiture.model.entities.AbstractEntity;
import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.Couleur;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static net.ent.etrs.voiture.model.entities.references.ConstantesMetier.MARQUE_TAILLE_MAX;
import static net.ent.etrs.voiture.model.entities.references.ConstantesMetier.MARQUE_TAILLE_MIN;

/**
 * The type Voiture.
 */
public class Voiture extends AbstractEntity {

    //Attributs
    private String marque;
    private String model;
    private Couleur couleur;
    private List<Piece> pieces = new ArrayList<>();


    /**
     * Instantiates a new Voiture.
     *
     * @param numSerie the num serie
     * @param marque   the marque
     * @param model    the model
     * @throws NumSerieException the num serie exception
     * @throws VoitureException  the voiture exception
     */
    protected Voiture(final String numSerie, final String marque, final String model) throws NumSerieException, VoitureException {
        super(numSerie);

        try {
            this.setMarque(marque);
            this.setModel(model);
        }catch (VoitureException e ){
            throw new VoitureException(e.getMessage(), e);
        }
    }
//Constructeur


    //Accesseur

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
     * @throws VoitureException the voiture exception
     */
    public void setMarque(final String marque) throws VoitureException {
        // Vérifie que la marque ne soit ni null ni vide.
        if (Objects.isNull(marque) || marque.isBlank()){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MARQUE_NON_RENSEIGNE_EXCEPTION );
        }
        // Vérifie que la marque contient entre 3 et 20 caractères
        if (marque.length() <MARQUE_TAILLE_MIN && marque.length()>MARQUE_TAILLE_MAX){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MARQUE_TAILLE_EXCEPTION);
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
     * @throws VoitureException the voiture exception
     */
    public void setModel(final String model) throws VoitureException {
        // Vérifie que le model ne soit ni null ni vide.
        if (Objects.isNull(marque) || marque.isBlank()){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MODEL_NON_RENSEIGNE_EXCEPTION );
        }
        // Vérifie que le model contient entre 3 et 20 caractères
        if (marque.length() <MARQUE_TAILLE_MIN && marque.length()>MARQUE_TAILLE_MAX){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MODEL_TAILLE_EXCEPTION);
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
     */
    public void setCouleur(final Couleur couleur) {
        this.couleur = couleur;
    }

    /**
     * Gets pieces.
     *
     * @return the pieces
     */
    public List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }
    //Autres méthodes

    /**
     * Ajouter piece.
     *
     * @param piece the piece
     * @throws VoitureException the voiture exception
     */
    protected void ajouterPiece(Piece piece) throws VoitureException {
        if (Objects.isNull(piece)){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECE_NON_RENSEIGNE_EXCEPTION);
        }
        if (this.pieces.contains(piece)){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECE_EXISTE_EXCEPTION);
        }
        this.pieces.add(piece);

    }
}
