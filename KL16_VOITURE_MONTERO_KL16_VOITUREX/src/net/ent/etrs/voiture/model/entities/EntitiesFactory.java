package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.*;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

import java.util.ArrayList;
import java.util.List;

public final class EntitiesFactory {

//attribut(s)

//constructeur(s)

    private EntitiesFactory() {
    }


//getter et setter

//egal et hash

//to string

    private static void controlerRoue(final Voiture voiture) throws VoitureException, ControleRoueVoitureException {
        List<Piece> pieces = new ArrayList<>();
        for (Piece piece : voiture.getPieces()) {
            pieces.add(piece);
        }

        if (!pieces.contains(TypePiece.ROUE_ARD) || !pieces.contains(TypePiece.ROUE_AVD) || !pieces.contains(TypePiece.ROUE_AVG) || !pieces.contains(TypePiece.ROUE_ARD)) {
            throw new ControleRoueVoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_ROUE_MANQUANTE_EXCEPTION);
        }
    }

    private static void ajouterPieces(final List<Piece> pieces, final Voiture voiture) throws VoitureException {
        for (Piece piece : pieces) {
            voiture.ajouterPiece(piece);
        }
    }

    //autre(s) methode(s)
    public static Piece fabriquerPiece(final String numSerie, final TypePiece typePiece) throws ConstructionPieceException {
        try {
            Piece piece = new Piece(numSerie, typePiece);
            return piece;
        } catch (VoitureException | NumSerieException | PieceException e) {
            throw new ConstructionPieceException(ConstantesMetier.MSG_CONSTRUCTION_PIECE_EXCEPTION, e);
        }
    }

    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, Couleur couleur, List<Piece> pieces) throws ConstructionVoitureException {
        try {
            Voiture voiture = new Voiture(numSerie, marque, model);
            voiture.setCouleur(couleur);
            ajouterPieces(pieces, voiture);
            controlerRoue(voiture);
            return voiture;
        } catch (VoitureException | NumSerieException | ControleRoueVoitureException e) {
            throw new ConstructionVoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION, e);
        }
    }

    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, Couleur couleur) throws ConstructionVoitureException {
        try {
            Voiture voiture = new Voiture(numSerie, marque, model);
            voiture.setCouleur(couleur);
            return voiture;
        } catch (VoitureException | NumSerieException e) {
            throw new ConstructionVoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION, e);
        }
    }


}
