package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.ConstructionPieceException;
import net.ent.etrs.voiture.model.entities.exceptions.ConstructionVoitureException;
import net.ent.etrs.voiture.model.entities.exceptions.PieceException;
import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

public final class EntitiesFactory {

    public static Piece fabriquerPiece(final String numSerie, final TypePiece typePiece) throws ConstructionPieceException{
        try {
            Piece piece = new Piece(numSerie, typePiece);
            piece.setTypePiece(typePiece);
            return piece;
        }catch (PieceException e){
            throw new ConstructionPieceException();
        }
    }

    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model) throws ConstructionVoitureException{
        try {
            Voiture voiture = new Voiture(numSerie, marque, model);
            return  voiture;
        }catch (VoitureException e){
            throw new ConstructionVoitureException();
        }
    }

    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur) throws ConstructionVoitureException{
        try {
            Voiture voiture = new Voiture(numSerie, marque, model);
            return  voiture;
        }catch (VoitureException e){
            throw new ConstructionVoitureException();
        }
    }

}
