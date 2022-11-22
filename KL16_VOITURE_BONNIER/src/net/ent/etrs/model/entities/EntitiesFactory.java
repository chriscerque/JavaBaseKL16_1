package net.ent.etrs.model.entities;

import net.ent.etrs.model.entities.exceptions.*;
import net.ent.etrs.model.entities.references.ConstantesMetier;
import net.ent.etrs.model.entities.references.Couleur;
import net.ent.etrs.model.entities.references.TypePiece;

import java.util.List;

public class EntitiesFactory {

    public EntitiesFactory() {
    }

    public static void ajouterPiece(String numSerie, TypePiece typePiece) {

    }


    public static Piece fabriquerPiece(final String numSerie, final TypePiece typePiece) throws ConstructionPieceException {
        try {
            Piece piece = new Piece(numSerie, typePiece);
            piece.setTypePiece(typePiece);
            return piece;

        } catch (PieceException | NumSerieException e) {
            throw new ConstructionPieceException(ConstantesMetier.MSG_CONSTRUCTION_PIECE_EXCEPTION);
        }
    }

    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur, final List<Piece> pieces) throws ConstructionVoitureException {
        try {
            Voiture voiture = new Voiture(numSerie, marque, model);
            voiture.setCouleur(couleur);
            voiture.ajouterPiece((Piece) pieces);
            return voiture;
        } catch (VoitureException | NumSerieException e) {
            throw new ConstructionVoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION);
        }

    }

    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur) throws ConstructionVoitureException {
        try {
            Voiture voiture = new Voiture(numSerie, marque, model);
            voiture.setCouleur(couleur);
            return voiture;
        } catch (VoitureException | NumSerieException e) {
            throw new ConstructionVoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION);
        }

    }
}
