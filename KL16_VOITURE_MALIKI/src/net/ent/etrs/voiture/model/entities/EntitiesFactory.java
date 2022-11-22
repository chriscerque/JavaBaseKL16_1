package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.*;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

import java.util.List;

/**
 * The type Entities factory.
 */
public class EntitiesFactory {

    private EntitiesFactory() {
    }

//private static void ajouterPieces (final Voiture voiture, final List<Piece> listpiece) throws PieceException, NumSerieException {
//        Piece piece = new Piece(voiture,listpiece);
//}
//
//private static void controlerRoues(final Voiture voiture){
//
//}

    /**
     * Fabrique piece piece.
     *
     * @param numSerie  the num serie
     * @param typePiece the type piece
     * @return the piece
     * @throws PieceException the piece exception
     */
    public static Piece fabriquePiece(final String numSerie, final TypePiece typePiece) throws PieceException {
    try {
        Piece piece= new Piece(numSerie,typePiece);
        return piece;
    } catch (NumSerieException|PieceException e) {
        throw new PieceException(e.getMessage(),e);
    }
}

    /**
     * Fabriquer voiture voiture.
     *
     * @param numSerie the num serie
     * @param marque   the marque
     * @param model    the model
     * @param couleur  the couleur
     * @param pieces   the pieces
     * @return the voiture
     * @throws VoitureException the voiture exception
     */
    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur,List<Piece> pieces) throws VoitureException {

    try {
        Voiture voiture = new Voiture(numSerie,marque,model);
        voiture.ajouterPiece((Piece) pieces);
        return voiture;
    } catch (NumSerieException | ConstructionVoitureException | PieceException e) {
        throw new VoitureException(e.getMessage(),e);
    }
    }

    /**
     * Fabriquer voiture voiture.
     *
     * @param numSerie the num serie
     * @param marque   the marque
     * @param model    the model
     * @param couleur  the couleur
     * @return the voiture
     * @throws VoitureException the voiture exception
     */
    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur) throws VoitureException {

       try {
           Voiture voiture = new Voiture(numSerie, marque, model);
            voiture.setCouleur(couleur);
           return voiture;
       } catch (NumSerieException | ConstructionVoitureException | CouleurException e) {
           throw new VoitureException(e.getMessage(),e);
       }
   }

}

