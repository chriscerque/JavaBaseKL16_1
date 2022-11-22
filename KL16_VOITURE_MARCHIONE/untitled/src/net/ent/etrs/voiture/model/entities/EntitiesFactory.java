package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

public class EntitiesFactory {

//-------------------------------
//    Classe EntitiesFactory
//-------------------------------


    /**
     * Constructeur.
     */

    public EntitiesFactory() {

    }


    /**
     * Methode Ajouter Piece.
     */
//    private void ajouterPieces(Voiture) {
//        Piece lstPieces = ;
//    }



    /**
     * Methode Fabriquer Piece.
     */
    public void fabriquerPiece() {
        Piece p1 = new Piece(TypePiece.MOTEUR_V12_AR);
    }

    /**
     * Methode Fabriquer Voiture.
     *
     * @return
     */
    public static Voiture fabriquerVoiture(String marque, String model, Couleur couleur) {
        Voiture v = new Voiture("Mercedes", "C-65-AMG", Couleur.NOIR);
        return v;
    }





}
