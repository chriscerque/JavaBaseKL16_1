package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.*;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

import java.util.List;

public final class EntitiesFactory {

    private EntitiesFactory() {}

    private void ajouterPieces(final Voiture voiture, final List<Piece> lstPieces) throws VoitureException {
        for(Piece piece : lstPieces){
            voiture.ajouterPiece(piece);
        }
    }

    private void controlerRoues(final Voiture voiture){
        //TODO BONUS
    }

    /**
     * Fabrication de pièce
     * @param numSerie
     * @param typePiece
     * @return la pièce fabriquée
     * @throws ConstructionPieceException
     */
    public static Piece fabriquerPiece(final String numSerie, final TypePiece typePiece) throws ConstructionPieceException {
        try {
            Piece piece = new Piece(numSerie, typePiece);
            return piece;
        } catch (NumSerieException | PieceException e) {
            throw new ConstructionPieceException(ConstantesMetier.MSG_CONSTRUCTION_PIECE_EXCEPTION, e);
        }
    }

    /**
     * Fabrication de voiture avec liste de pièces
     * @param numSerie
     * @param marque
     * @param model
     * @param couleur
     * @param lstPieces
     * @return la voiture crée
     * @throws ConstructionVoitureException
     */
    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur, final List<Piece> lstPieces) throws ConstructionVoitureException {
        try {
            Voiture voiture = new Voiture(numSerie, marque, model);
            voiture.setCouleur(couleur);
            for (Piece piece : lstPieces) {
                voiture.ajouterPiece(piece);
            }
            return voiture;
        } catch (VoitureException | NumSerieException e){
            throw new ConstructionVoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION, e);
        }
    }

    /**
     * Fabrication de voiture avec liste de pièces
     * @param numSerie
     * @param marque
     * @param model
     * @param couleur
     * @return la voiture crée
     * @throws ConstructionVoitureException
     */
    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur) throws ConstructionVoitureException {
        try {
            Voiture voiture = new Voiture(numSerie, marque, model);
            voiture.setCouleur(couleur);
            return voiture;
        } catch (VoitureException | NumSerieException e){
            throw new ConstructionVoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION, e);
        }
    }

}
