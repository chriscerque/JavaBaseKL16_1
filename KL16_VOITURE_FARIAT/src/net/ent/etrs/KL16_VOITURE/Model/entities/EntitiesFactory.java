package net.ent.etrs.KL16_VOITURE.Model.entities;

import net.ent.etrs.KL16_VOITURE.Model.entities.excpetions.ConstructionPieceException;
import net.ent.etrs.KL16_VOITURE.Model.entities.excpetions.ConstructionVoitureException;
import net.ent.etrs.KL16_VOITURE.Model.entities.excpetions.PieceException;
import net.ent.etrs.KL16_VOITURE.Model.entities.excpetions.VoitureException;
import net.ent.etrs.KL16_VOITURE.Model.entities.references.ConstantesMetier;
import net.ent.etrs.KL16_VOITURE.Model.entities.references.TypePiece;

import java.util.List;

public final class EntitiesFactory {
    private static Object numSerieException;

    /**
     * Cette methode permet d'ajouter une piece à une liste.
     * @param voiture
     * @param piece
     */

    private static void ajouterPiece(Voiture voiture, List<Piece> pieces){


        for(Piece piece :pieces{
            voiture.ajouterPiece((piece);
        }


    }

    /**
     * Cette methode permet de fabriquer une piece.
     * @param piece
     * @param typePiece
     */

    public static  Piece fabriquerPiece(String numSerie, TypePiece typePiece) throws ConstructionPieceException {
        try {
            return new Piece(numSerie);
        } catch (PieceException e) {
            throw new ConstructionPieceException();
        }
    }





    /**
     * Fabriquer une voiture.
     *
     * @param numSerie
     * @param marque
     * @param model
     * @return
     */


    public static Voiture fabriquerVoiture(String numSerie, String marque, String model) throws ConstructionVoitureException, VoitureException {
        try{
        Voiture voiture = new Voiture(numSerie, marque, model);
            Couleur couleur;
            voiture.setCouleur(couleur);
        for (Piece piece :pieces){
            voiture.ajouterPiece(piece);
            return voiture;
        }
        catch Object VoitureException;

            (numSerieException | VoitureException){
            throw new ConstructionVoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION, e);
            }





        /**
     * Fabriquer une voiture.
     * @param numSerie
     * @param marque
     * @param model
     * @param couleur
     */


    public static void fabriquerVoiture(String numSerie, String marque, String model, Couleur couleur){

    }

}
