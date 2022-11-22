package net.ent.etrs.model.entities;

import net.ent.etrs.model.entities.exceptions.ConstructionVoitureException;
import net.ent.etrs.model.entities.exceptions.ControleRoueVoitureException;
import net.ent.etrs.model.entities.exceptions.NumSerieException;
import net.ent.etrs.model.entities.exceptions.PieceException;
import net.ent.etrs.model.entities.references.ConstantesMetier;
import net.ent.etrs.model.entities.references.Couleur;
import net.ent.etrs.model.entities.references.FamilleTypePiece;
import net.ent.etrs.model.entities.references.TypePiece;

import java.util.List;

public class EntitiesFactory {

    public EntitiesFactory() {
    }

    /**
     * ajoute les pieces d'une liste à une voiture
     * @param voiture la voiture recevant la liste
     * @param pieces le liste de pieces à ajouter
     * @throws PieceException en cas de piece déjà existante
     */
    private static void ajouterPieces (Voiture voiture, List<Piece> pieces) throws PieceException {
        try {
            for (Piece p : pieces) {
                voiture.ajouterPiece(p);
            }
        } catch (ConstructionVoitureException e) {
            throw new PieceException(ConstantesMetier.MSG_VOITURE_PIECE_EXISTE_EXCEPTION, e.getCause());
        }
    }

    /**
     * contrôle la présence de 4 roues dans la liste voitture
     * @param voiture voiture à véritier
     * @throws ControleRoueVoitureException dans le cas où la voiture n'a pas 4 roues
     */
    private static void controlerRoues(Voiture voiture) throws ControleRoueVoitureException {
        int compte = 0;
        for (Piece p: voiture.getPieces()) {
            if (p.getTypePiece().getFamilleTypePiece().equals(FamilleTypePiece.ROUE)) {
                compte++;
            }
        }
        if (compte != 4) {
            throw new ControleRoueVoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_ROUE_MANQUANTE_EXCEPTION);
        }

    }

    /**
     * créer un objet Piece
     * @param numSerie numéro de dérie de la piece
     * @param typePiece type de la pièce
     * @return la pièce
     */
    public static Piece fabriquerPiece(String numSerie, TypePiece typePiece) throws NumSerieException {
        Piece piece = new Piece(numSerie, typePiece);
        return piece;
    }

    /**
     * créer un objet Voiture
     * @param numSerie numéro de série de la voiture
     * @param marque marque de la voiture
     * @param modele modele de la voiture
     * @param couleur couleur de la voiture
     * @return l'objet voiture
     */
    public static Voiture fabriquerVoiture(String numSerie, String marque, String modele, Couleur couleur) throws NumSerieException {
        Voiture voiture = new Voiture(numSerie, marque, modele);
        voiture.setCouleur(couleur);
        return voiture;
    }

    /**
     * créer un objet Voiture
     * @param numSerie numéro de série de la voiture
     * @param marque marque de la voiture
     * @param modele modele de la voiture
     * @param couleur couleur de la voiture
     * @param pieces liste de pieces à ajouter à la création de la voiture
     * @return l'objet voiture
     */
    public static Voiture fabriquerVoiture(String numSerie, String marque, String modele, Couleur couleur, List<Piece> pieces) throws ConstructionVoitureException {
       try {
           Voiture voiture = new Voiture(numSerie, marque, modele);
           voiture.setCouleur(couleur);
           ajouterPieces(voiture, pieces);
           return voiture;
       } catch (PieceException | NumSerieException e) {
           throw new ConstructionVoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION, e.getCause());
       }
    }



}
