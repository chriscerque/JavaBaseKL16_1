package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.*;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

import java.util.List;

public final class EntitiesFactory {
    private EntitiesFactory() {
    }

    private static void ajouterPieces(Voiture voiture, List<Piece> pieces) throws PieceException {
        try {
            for (Piece piece:
                    pieces) {
                voiture.ajouterPiece(piece);
            }
        } catch (VoitureException e) {
            throw new PieceException(e.getMessage());
        }
    }

    private static void controlerRoues(Voiture voiture) {

    }

    public static Piece fabriquerPiece(String numSerie, TypePiece typePiece) throws ConstructionPieceException {
        try {
            return new Piece(numSerie, typePiece);
        } catch (PieceException | NumSerieException e) {
            throw new ConstructionPieceException(e.getMessage());
        }
    }

    public static Voiture fabriquerVoiture(String numSerie, String marque, String model, Couleur couleur, List<Piece> pieces) throws ConstructionVoitureException {
        try {
            Voiture voiture = new Voiture(numSerie, marque, model);
            voiture.setCouleur(couleur);
            for (Piece piece :
                    pieces) {
                voiture.ajouterPiece(piece);
            }
            return voiture;
        } catch (VoitureException | NumSerieException e) {
            throw new ConstructionVoitureException(e.getMessage());
        }
    }

    public static Voiture fabriquerVoiture(String numSerie, String marque, String model, Couleur couleur) throws ConstructionVoitureException {
        try {
            Voiture voiture = new Voiture(numSerie, marque, model);
            voiture.setCouleur(couleur);
            return voiture;
        } catch (VoitureException | NumSerieException e) {
            throw new ConstructionVoitureException(e.getMessage());
        }
    }
}
