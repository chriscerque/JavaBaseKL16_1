package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

import java.util.List;

public final class EntitiesFactory {

    private EntitiesFactory() {
    }

    /**
     * ajouterPieces.
     * @param v
     * @param piece
     */
    private static void ajouterPieces(final Voiture v, final List<Piece> piece){

    }

    /**
     * Permet de fabriquer une piece.
     * @param nomPiece
     * @param typePiece
     * @return
     */

    public static Piece fabriquerPiece(final String nomPiece, final TypePiece typePiece){
        Piece piece = new Piece(nomPiece,typePiece);
        return piece;
    }

    /**
     * Permet de frabriquer une voiture avec les options placer en parametres.
     * @param numSerie
     * @param marque
     * @param model
     * @param couleur
     * @param piece
     * @return
     */

    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur, final List<Piece> piece){
        Voiture voiture = new Voiture(numSerie,marque,model);
        voiture.setCouleur(couleur);
        //voiture.ajouterPiece(piece);
        return voiture;
    }

    /**
     * Permet de frabriquer une voiture avec les options placer en parametres.
     * @param numSerie
     * @param marque
     * @param model
     * @param couleur
     * @return
     */

    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur){
        Voiture voiture = new Voiture(numSerie,marque,model);
        voiture.setCouleur(couleur);
        return voiture;
    }
}
