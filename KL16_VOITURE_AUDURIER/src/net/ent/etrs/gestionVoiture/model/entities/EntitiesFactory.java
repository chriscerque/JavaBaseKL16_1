package net.ent.etrs.gestionVoiture.model.entities;

import net.ent.etrs.gestionVoiture.model.entities.exceptions.ConstructionPieceException;
import net.ent.etrs.gestionVoiture.model.entities.exceptions.ConstructionVoitureException;
import net.ent.etrs.gestionVoiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.gestionVoiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.gestionVoiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.gestionVoiture.model.entities.references.Couleur;
import net.ent.etrs.gestionVoiture.model.entities.references.TypePiece;

import java.util.List;

public final class EntitiesFactory {
    private EntitiesFactory() {
    }

    private static void controlerRoues(final Voiture voiture) {
        //TODO
    }

    public static Piece fabriquerPiece(final String numSerie, final TypePiece typePiece)
            throws ConstructionPieceException {
        try {
            return new Piece(numSerie, typePiece);
        } catch (NumSerieException |
                 ConstructionPieceException e) {
            throw new ConstructionPieceException(e.getMessage(), e);
        }
    }

    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur, final List<Piece> lstPieces)
            throws ConstructionVoitureException {
        try {
            Voiture v = new Voiture(numSerie, marque, model);
            v.setCouleur(couleur);
            ajouterPieces(v, lstPieces);
            return v;
        } catch (NumSerieException |
                 ConstructionVoitureException |
                 VoitureException e) {
            throw new ConstructionVoitureException(e.getMessage(), e);
        }
    }

    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur)
            throws ConstructionVoitureException {
        try {
            Voiture v = new Voiture(numSerie, marque, model);
            v.setCouleur(couleur);
            return v;
        } catch (NumSerieException |
                 ConstructionVoitureException |
                 VoitureException e) {
            throw new ConstructionVoitureException(e.getMessage(), e);
        }
    }

    private static void ajouterPieces(final Voiture voiture, final List<Piece> lstPieces) throws VoitureException {
        if (voiture == null) {
            throw new VoitureException(ConstantesMetier.MSG_VOITURE_NULL_EXCEPTION);
        }
        if (lstPieces == null) {
            throw new VoitureException((ConstantesMetier.MSG_VOITURE_AJOUTER_LIST_PIECES_EXCEPTION));
        }
        for (Piece piece : lstPieces) {
            voiture.ajouterPiece(piece);
        }
    }

}
