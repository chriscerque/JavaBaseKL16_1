package net.ent.etrs.KL16_VOITURE.Model.entities;


import net.ent.etrs.KL16_VOITURE.Model.entities.excpetions.VoitureException;
import net.ent.etrs.KL16_VOITURE.Model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Creation de la classe voiture.
 */
public class Voiture extends AbstractEntity{
    //ATTRIBUTS
    private String marque;
    private String model;
    private Couleur couleur;
    private List<Piece> pieces = new ArrayList<>();


   //CONSTRUCTEUR

    protected Voiture(final String numSerie, final String marque, final String model) {
        super(numSerie);
        this.marque = marque;
        this.model = model;
    }


    //SETTER GETTER

    public String getMarque() {
        return this.marque;
    }

    private void setMarque(final String marque) throws VoitureException {
        if(marque == null){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MARQUE_NON_RENSEIGNE_EXCEPTION);
        }
        if(marque.length()> ConstantesMetier.MARQUE_TAILLE_MAX || marque.length()<ConstantesMetier.MARQUE_TAILLE_MIN){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_MARQUE_TAILLE_EXCEPTION);
        }
        this.marque = marque;
    }

    public String getModel() {
        return this.model;
    }

   private void setModel(final String model) throws VoitureException {
       if(model == null){
           throw new VoitureException(ConstantesMetier.MSG_VOITURE_MODEL_NON_RENSEIGNE_EXCEPTION );
       }
       if(model.length()> ConstantesMetier.MODEL_TAILLE_MAX || model.length()<ConstantesMetier.MODEL_TAILLE_MIN){
           throw new VoitureException(ConstantesMetier.MSG_VOITURE_MODEL_NON_RENSEIGNE_EXCEPTION);
       }
        this.model = model;
    }

    public Couleur getCouleur() {
        return this.couleur;
    }

    protected void setCouleur(final Couleur couleur) throws VoitureException {
        if(couleur ==null){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_COULEUR_NON_RENSEIGNE_EXCEPTION);
        }
        this.couleur = couleur;
    }

    public List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);

    }




    //METHODES
    public void ajouterPiece(Piece piece) throws VoitureException {
        if (Objects.isNull(piece)){
            throw new VoitureException();
        }
        if (this.pieces.contains(piece)){
            throw new VoitureException();
        }
        this.pieces.add(piece);

    }

    public void supprimerPiece(final Piece piece) throws VoitureException {
        if (Objects.isNull(piece)){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECE_NON_RENSEIGNE_EXCEPTION );
        }
        if (!this.pieces.contains(piece)){
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_PIECE_EXISTE_EXCEPTION);
        }
        this.pieces.remove(piece);
    }


    public String getLibelle() {
    }
}
