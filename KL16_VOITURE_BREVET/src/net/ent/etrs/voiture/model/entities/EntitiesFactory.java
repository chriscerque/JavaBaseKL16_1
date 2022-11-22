package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.*;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

import java.util.List;

/**
 * Fabrique d'entités.
 */
public final class EntitiesFactory {
    //region CONSTRUCTEUR(S)

    private EntitiesFactory() {
    }

    //endregion
    //region MÉTHODES
    private static void ajouterPieces(final Voiture voiture, final List<Piece> pieces) throws VoitureException {
        for (Piece p : pieces) {
            voiture.ajouterPiece(p);
        }
    }

    private static void controlerRoues(final Voiture voiture) {

    }

    public static Piece fabriquerPiece(final String numSerie, final TypePiece typePiece) throws ConstructionPieceException {
        try {
            return new Piece(numSerie, typePiece);
        } catch (NumSerieException | PieceException e) {
            throw new ConstructionPieceException(ConstantesMetier.MSG_CONSTRUCTION_PIECE_EXCEPTION, e);
        }
    }

    public static Voiture fabriquerVoiture(final String numSerie, final String model, final String marque, final Couleur couleur, final List<Piece> pieces) throws ConstructionVoitureException {
        Voiture voiture;
        try {
            voiture = new Voiture(numSerie, model, marque);
            voiture.setCouleur(couleur);
            for (Piece p : pieces) {
                voiture.ajouterPiece(p);
            }
        } catch (NumSerieException | VoitureException e) {
            throw new ConstructionVoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION, e);
        }
        return voiture;
    }

    public static Voiture fabriquerVoiture(final String numSerie, final String model, final String marque, final Couleur couleur) throws ConstructionVoitureException {
        Voiture voiture;
        try {
            voiture = new Voiture(numSerie, model, marque);
            voiture.setCouleur(couleur);
        } catch (NumSerieException | VoitureException e) {
            throw new ConstructionVoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION, e);
        }
        return voiture;
    }
    //endregion
}
