package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.*;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.FamilleTypePiece;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

import java.util.ArrayList;
import java.util.List;

/**
 * Fabrique de classe métier de l'application.
 * @author christophe.cerqueira
 */
public final class EntitiesFactory {
    private EntitiesFactory() {
    }

    /**
     * Fabrique une {@link Piece pièce}.
     * @param numSerie {@link AbstractEntity numéro de série}
     * @param typePiece {@link TypePiece type de pièce}
     * @return la {@link Piece pièce} instanciée.
     * @throws ConstructionPieceException si l'une des contraintes de création d'une {@link Piece#Piece(String, TypePiece) pièce} n'est pas respectée.
     */
    public static Piece fabriquerPiece(final String numSerie, final TypePiece typePiece) throws ConstructionPieceException {
        try {
            return new Piece(numSerie,typePiece);
        } catch (NumSerieException |PieceException e) {
            e.printStackTrace();
            throw new ConstructionPieceException(ConstantesMetier.MSG_CONSTRUCTION_PIECE_EXCEPTION,e);
        }
    }

    /**
     * Fabrique une {@link Voiture voiture}.
     * @param numSerie {@link AbstractEntity numéro de série}
     * @param marque {@link Voiture marque}
     * @param model {@link Voiture modèle}
     * @param couleur {@link Couleur couleur}
     * @return la {@link Voiture voiture} instanciée.
     * @throws ConstructionVoitureException si l'une des contraintes de création d'une {@link Voiture#Voiture(String, String, String) voiture} n'est pas respectée.
     * @throws ControleRoueVoitureException si l'une des contraintes de {@link #controlerRoues(Voiture)  contrôle des roues} n'est pas respectée.
     */
    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur) throws ConstructionVoitureException, ControleRoueVoitureException {
        try {
            Voiture v = new Voiture(numSerie, marque, model);
            v.setCouleur(couleur);
            return v;
        } catch (NumSerieException | VoitureException e) {
            throw new ConstructionVoitureException(ConstantesMetier.MSG_CONSTRUCTION_PIECE_EXCEPTION,e);
        }
    }

    /**
     /**
     * Fabrique une {@link Voiture voiture}.
     * @param numSerie {@link AbstractEntity numéro de série}
     * @param marque {@link Voiture marque}
     * @param model {@link Voiture modèle}
     * @param couleur {@link Couleur couleur}
     * @param pieces liste des {@link Piece pièces}
     * @return la {@link Voiture voiture} instanciée.
     * @throws ConstructionVoitureException si l'une des contraintes de création d'une {@link Voiture#Voiture(String, String, String)  voiture} n'est pas respectée.
     * @throws ControleRoueVoitureException si l'une des contraintes de {@link #controlerRoues(Voiture)  contrôle des roues} n'est pas respectée.
     */
    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur, final List<Piece> pieces) throws ConstructionVoitureException, ControleRoueVoitureException {
        try {
            Voiture v = EntitiesFactory.fabriquerVoiture(numSerie, marque, model, couleur);
            ajouterPieces(v, pieces);
            controlerRoues(v);
            return v;
        } catch (VoitureException e) {
            throw new ConstructionVoitureException(ConstantesMetier.MSG_CONSTRUCTION_PIECE_EXCEPTION,e);
        }
    }

    /**
     * Contrôle les roues de la voiture passée en paramètre.
     * @param v {@link Voiture voiture}
     * @throws ControleRoueVoitureException si la {@link Voiture voiture} ne possède pas l'une des quatres roues ({@link TypePiece#ROUE_AVD}, {@link TypePiece#ROUE_AVG}, {@link TypePiece#ROUE_ARD}, {@link TypePiece#ROUE_ARG}).
     */
    private static void controlerRoues(Voiture v) throws ControleRoueVoitureException {
        List<TypePiece> lstTypeRoue = new ArrayList<>();
        for (Piece p : v.getPieces()){
            if(p.getTypePiece().getFamilleTypePiece().equals(FamilleTypePiece.ROUE)){
                lstTypeRoue.add(p.getTypePiece());
            }
        }

        for (TypePiece tp : TypePiece.listerPieceByFamille(FamilleTypePiece.ROUE)){
            if(!lstTypeRoue.contains(tp)) {
                throw new ControleRoueVoitureException(String.format(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_ROUE_MANQUANTE_EXCEPTION, tp.getLibelle()));
            }
        }
    }

    /**
     * Ajoute les {@link Piece pièces} à la {@link Voiture voiture}.
     * @param voiture {@link Voiture voiture}
     * @param pieces liste des {@link Piece pièces}
     * @throws VoitureException si l'une des contraintes d{@link Voiture#ajouterPiece(Piece) 'ajout d'une pièce} n'est pas respectée.
     */
    private static void ajouterPieces(final Voiture voiture, List<Piece> pieces) throws VoitureException {
        for (Piece p : pieces){
            voiture.ajouterPiece(p);
        }

    }
}
