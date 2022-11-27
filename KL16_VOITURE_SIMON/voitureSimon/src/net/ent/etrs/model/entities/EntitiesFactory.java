package net.ent.etrs.model.entities;


import net.ent.etrs.model.entities.exceptions.*;
import net.ent.etrs.model.entities.references.Couleur;
import net.ent.etrs.model.entities.references.TypePiece;

import java.util.List;

/**
 * The type Entities factory.
 */
public class EntitiesFactory {

    private void ajouterPieces(final Voiture voiture,final List<Piece> pieces){

    }

    /**
     * Fabriquer piece piece.
     *
     * @param numSerie  the num serie
     * @param typePiece the type piece
     * @return the piece
     * @throws ConstructionPieceException the construction piece exception
     */
    public static Piece fabriquerPiece(final String numSerie, final TypePiece typePiece) throws ConstructionPieceException {
        try {
            Piece piece = new Piece(numSerie,typePiece);
            return piece;
        }catch (PieceException | NumSerieException e){
            throw new ConstructionPieceException(e.getMessage());
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
     * @throws ConstructionVoitureException the construction voiture exception
     */
    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur, final List<Piece> pieces) throws ConstructionVoitureException {
        try {
            Voiture voiture = new Voiture(numSerie,marque,model,couleur,pieces);
            voiture.setCouleur(couleur);

            return voiture;
        }catch (VoitureException | NumSerieException e){
            throw new ConstructionVoitureException(e.getMessage());
        }
    }

    /**
     * Fabriquer voiture voiture.
     *
     * @param numserie the numserie
     * @param marque   the marque
     * @param model    the model
     * @param couleur  the couleur
     * @return the voiture
     * @throws ConstructionVoitureException the construction voiture exception
     */
    public static Voiture fabriquerVoiture(final String numserie, final String marque, final String model, final Couleur couleur) throws ConstructionVoitureException {
        try {
            Voiture voiture = new Voiture(numserie, marque, model, couleur);
            return voiture;
        } catch (VoitureException | NumSerieException e) {
            throw new ConstructionVoitureException(e.getMessage());
        }
    }
}
