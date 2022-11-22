package net.ent.etrs.model.entities;

import net.ent.etrs.model.entities.exceptions.*;
import net.ent.etrs.model.entities.references.ConstantesMetier;
import net.ent.etrs.model.entities.references.Couleur;
import net.ent.etrs.model.entities.references.TypePiece;

import java.util.List;


public class EntitiesFactory {

    private EntitiesFactory() {
    }

    /**
     * Fabriquer piece piece.
     *
     * @param numSerie  the num serie
     * @param typePiece the type piece
     * @return the piece
     * @throws ConstructionPieceException the construction piece exception
     */
    private static void ajouterPieces(Voiture voiture, List<Piece> pieces) throws VoitureException {
        for (Piece p: pieces) {
            voiture.ajouterPiece(p);
        }
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
            Piece piece = new Piece(numSerie, typePiece);
            return piece;
        } catch ( NumSerieException | TypePieceException | PieceException e) {
            throw new ConstructionPieceException(ConstantesMetier.MSG_CONSTRUCTION_PIECE_EXCEPTION);
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
            Voiture voiture = EntitiesFactory.fabriquerVoiture(numSerie, marque, model, couleur);
            ajouterPieces(voiture, pieces);
            return voiture;
        } catch (ConstructionVoitureException | VoitureException e) {
            throw new ConstructionVoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION);
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
     * @throws ConstructionVoitureException the construction voiture exception
     */
    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur) throws ConstructionVoitureException {
        try {
            Voiture voiture = new Voiture(numSerie, marque, model);
            voiture.setCouleur(couleur);
            return voiture;
        } catch (NumSerieException | VoitureException e) {
            throw new ConstructionVoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION);
        }
    }
}
