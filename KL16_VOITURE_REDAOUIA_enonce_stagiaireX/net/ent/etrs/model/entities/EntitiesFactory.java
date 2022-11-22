package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.model.entities.exceptions.ConstructionPieceException;
import net.ent.etrs.model.entities.exceptions.ConstructionVoitureException;
import net.ent.etrs.model.entities.exceptions.NumSerieException;
import net.ent.etrs.model.entities.exceptions.TypePieceException;
import net.ent.etrs.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;
import net.ent.etrs.model.entities.exceptions.VoitureException;

import java.util.List;

public class EntitiesFactory {

    private EntitiesFactory() {
    }

    private void ajouterPieces(Voiture voiture, List<Piece> pieces){
        if (!pieces.isEmpty()){
            for (Piece p:pieces){
                voiture.ajouterPiece(p);
            }
        }

    }

    private void controlerRoues(Voiture voiture){
        //TODO bonus

    }

    public static Piece fabriquerPiece(String numSerie, TypePiece typePiece) throws TypePieceException, NumSerieException, ConstructionPieceException {
        try {
            Piece piece = new Piece(numSerie, typePiece);
            return piece;
        }catch(TypePieceException | NumSerieException e){
            throw new ConstructionPieceException(ConstantesMetier.MSG_CONSTRUCTION_PIECE_EXCEPTION, e.getCause());
        }

    }

    public static Voiture fabriquerVoiture(String numSerie, String marque, String model) throws  ConstructionVoitureException {
        try {
            Voiture voiture = new Voiture(numSerie, marque, model);
            return voiture;
        }catch(net.ent.etrs.model.entities.exceptions.VoitureException | NumSerieException e){
            throw new ConstructionVoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION, e.getCause());
        }

    }

    public static Voiture fabriquerVoiture(String numSerie, String marque, String model, Couleur couleur, List<Piece> pieces) throws ConstructionVoitureException {
        try {
            Voiture voiture = new Voiture(numSerie, marque, model);
            voiture.setCouleur(couleur);
            for (Piece p:pieces){
                voiture.ajouterPiece(p);
            }
            return voiture;
        }catch(VoitureException | NumSerieException e){
            throw new ConstructionVoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION, e.getCause());
        }

    }

}
