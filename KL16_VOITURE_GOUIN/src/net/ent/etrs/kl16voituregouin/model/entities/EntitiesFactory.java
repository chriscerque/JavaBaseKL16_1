package net.ent.etrs.kl16voituregouin.model.entities;

import net.ent.etrs.kl16voituregouin.model.entities.exceptions.*;
import net.ent.etrs.kl16voituregouin.model.entities.references.ConstantesMetier;
import net.ent.etrs.kl16voituregouin.model.entities.references.Couleur;
import net.ent.etrs.kl16voituregouin.model.entities.references.TypePiece;

import java.util.List;

public final class EntitiesFactory {

    //region  Constructeurs

    private EntitiesFactory() {
    }

    //endregion

    //region  Méthodes de classe

    /**
     * méthodes permettant l'ajout de pièces à une voiture
     * @param voiture
     * @param pieces
     */
    private static void ajouterPieces(Voiture voiture, List<Piece> pieces) throws VoitureException {
        for (Piece piece: pieces) {
            voiture.ajouterPiece(piece);
        }
    }

    /**
     * méthodes permettant de contrôler que la voiture a bien ces 4 roues
     * @param voiture
     */
    private static void controlerRoues(Voiture voiture) throws ControleRoueVoitureException {
        // controle des 4 roues
        boolean roueAvDroite = false;
        boolean roueAvGauche = false;
        boolean roueArDroite = false;
        boolean roueArGauche = false;
        for (Piece piece : voiture.getPieces()) {
            if(piece.getTypePiece().equals(TypePiece.ROUE_AVD)){
                roueAvDroite = true;
            }
            if(piece.getTypePiece().equals(TypePiece.ROUE_AVG)){
                roueAvGauche = true;
            }
            if(piece.getTypePiece().equals(TypePiece.ROUE_ARD)){
                roueArDroite = true;
            }
            if(piece.getTypePiece().equals(TypePiece.ROUE_ARG)){
                roueArGauche = true;
            }
        }
        if(!roueAvDroite){
            throw new ControleRoueVoitureException(String.format(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_ROUE_MANQUANTE_EXCEPTION,TypePiece.ROUE_AVD.getLibelle() ));
        }
        if(!roueAvGauche){
            throw new ControleRoueVoitureException(String.format(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_ROUE_MANQUANTE_EXCEPTION,TypePiece.ROUE_AVG.getLibelle() ));
        }
        if(!roueArDroite){
            throw new ControleRoueVoitureException(String.format(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_ROUE_MANQUANTE_EXCEPTION,TypePiece.ROUE_ARD.getLibelle() ));
        }if(!roueArGauche){
            throw new ControleRoueVoitureException(String.format(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_ROUE_MANQUANTE_EXCEPTION,TypePiece.ROUE_ARG.getLibelle() ));
        }


    }

    /**
     * méthodes permettant de créer une piece
     * @param numSerie
     * @param typePiece
     * @return
     */
    public static Piece fabriquerPiece(final String numSerie, final TypePiece typePiece) throws PieceException, NumSerieException {
        return new Piece(numSerie,typePiece);
    }

    /**
     * méthodes permettant de créer une voiture avec tous les paramêtre requis(4 roues)
     * @param numSerie
     * @param marque
     * @param model
     * @param couleur
     * @param pieces
     * @return
     */
    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur, final List<Piece> pieces) throws ConstructionVoitureException {

        try {
            Voiture voiture = new Voiture(numSerie, marque, model);
            voiture.setCouleur(couleur);
            ajouterPieces(voiture, pieces);
            controlerRoues(voiture);
            return voiture;
        } catch (NumSerieException | VoitureException | ControleRoueVoitureException e) {
            throw new ConstructionVoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION,e);
        }
    }

    /**
     * méthodes permettant de créer une voiture sans toutes les pièces (4roues)
     * @param numSerie
     * @param marque
     * @param model
     * @param couleur
     * @return
     */
    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur) throws ConstructionVoitureException {
        try {
            Voiture voiture = new Voiture(numSerie, marque, model);
            voiture.setCouleur(couleur);
            return voiture;
        } catch (NumSerieException | VoitureException e) {
            throw new ConstructionVoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION,e);
        }
    }
    //endregion
}
