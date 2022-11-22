package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.Couleur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Représente une entité voiture de l'application.
 * @author christophe.cerqueira
 */
public class Voiture  extends AbstractEntity{

    /**
     * Représente la marque de la voiture.
     */
    private String marque;

    /**
     * Représente le modèle de la voiture.
     */
    private String model;

    /**
     * Représente la {@link Couleur couleur de la voiture}.
     */
    private Couleur couleur;

    /**
     * Représente la liste des {@link Piece pièces} de la voiture.
     */
    private List<Piece> pieces = new ArrayList<>();

    /**
     * Construit une voiture en fonction des paramètre passés.
     * @param numSerie {@link AbstractEntity numéro de série}
     * @param marque {@link #marque marque}
     * @param model {@link #model modèle}
     * @throws NumSerieException si le numéro de série est null ou vide ou s'il ne contient pas {@link ConstantesMetier#NUM_SERIE_TAILLE}
     * @throws VoitureException si la {@link #marque  marque} ou le {@link #model modèle} ne respectent pas les contraintes fixées {@link #setMarque(String) setMarque} et {@link #setModel(String) setModel}.
     */
    protected Voiture(final String numSerie, final String marque, final String model) throws NumSerieException, VoitureException {
        super(numSerie);
        this.setMarque(marque);
        this.setModel(model);
    }

    public String getMarque() {
        return marque;
    }

    /**
     * Affecte la {@link #marque marque}.
     * @param marque la {@link #marque numéro de série} à affecter.
     * @throws VoitureException si la marque est null ou vide ou si elle ne contient pas entre {@link ConstantesMetier#MARQUE_TAILLE_MIN} et {@link ConstantesMetier#MARQUE_TAILLE_MAX}
     */
    private void setMarque(final String marque) throws VoitureException {
        if (Objects.isNull(marque) ||marque.isBlank()){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MARQUE_NON_RENSEIGNE_EXCEPTION);
        }
        if(marque.length() > ConstantesMetier.MARQUE_TAILLE_MAX  || marque.length() < ConstantesMetier.MARQUE_TAILLE_MIN){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MARQUE_TAILLE_EXCEPTION);
        }
        this.marque = marque;
    }

    public String getModel() {
        return model;
    }

    /**
     * Affecte le {@link #model modèle}.
     * @param model le {@link #model modèle} à affecter.
     * @throws VoitureException si le modèle est null ou vide ou s'il ne contient pas entre {@link ConstantesMetier#MODEL_TAILLE_MIN} et {@link ConstantesMetier#MODEL_TAILLE_MAX}
     */
    private void setModel(final String model) throws VoitureException {
        if (Objects.isNull(model) ||model.isBlank()){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MODEL_NON_RENSEIGNE_EXCEPTION);
        }
        if(model.length() > ConstantesMetier.MODEL_TAILLE_MAX  || model.length() < ConstantesMetier.MODEL_TAILLE_MIN){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MODEL_TAILLE_EXCEPTION);
        }
        this.model = model;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * Affecte la {@link #couleur couleur}.
     * @param couleur la {@link #couleur couleur} à affecter.
     * @throws VoitureException si la couleur est null.
     */
    protected void setCouleur(Couleur couleur) throws VoitureException {
        if (Objects.isNull(couleur)){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_COULEUR_NON_RENSEIGNE_EXCEPTION);
        }
        this.couleur = couleur;
    }

    public List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

    /**
     * Ajoute une {@link Piece pièce}.
     * @param piece {@link Piece pièce} à ajouter.
     * @throws VoitureException si la pièce est null ou si la même pièce ou bien que le même {@link net.ent.etrs.voiture.model.entities.references.TypePiece type de pièce} est déjà dans la liste des pièces.
     */
    protected void ajouterPiece(final Piece piece) throws VoitureException {
        if (Objects.isNull(piece)){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECE_NON_RENSEIGNE_EXCEPTION);
        }
        if (this.pieces.contains(piece)){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECE_EXISTE_EXCEPTION);
        }
        for (Piece p : this.pieces){
            if(p.getTypePiece().equals(piece)){
                throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECE_EXISTE_EXCEPTION);
            }
        }
        this.pieces.add(piece);
    }

//    public void supprimerPiece(final Piece piece) throws VoitureException {
//        if (Objects.isNull(piece)){
//            throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECE_NULL_EXCEPTION);
//        }
//        if (!this.pieces.contains(piece)){
//            throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECE_EXISTE_EXCEPTION);
//        }
//
//        this.pieces.remove(piece);
//    }
}
