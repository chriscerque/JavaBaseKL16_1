package net.ent.etrs.voiture.voiture.model.entities;

import net.ent.etrs.voiture.voiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.voiture.model.entities.references.TypePiece;

import java.util.List;

public class EntitiesFactory {

    //Constructeur
    private EntitiesFactory(){
    }

    //Autres Methodes
    public static Voiture fabriquerVoiture(String numSerie, String marque, String model, Couleur couleur, List<Piece> pieces){
        Voiture v = new Voiture(numSerie, marque, model);
        v.setCouleur(couleur);
        for(Piece p: pieces){
            v.ajouterPiece(p);
        }
        return v;
    }

    public static Voiture fabriquerVoiture(String numSerie, String marque, String model, Couleur couleur){
        Voiture v = new Voiture(numSerie, marque, model);
        v.setCouleur(couleur);
        return v;
    }

    public static Piece fabriquerPiece(String numSerie, TypePiece typePiece){
        Piece p = new Piece(numSerie, typePiece);
        return p;
    }

    private static void ajouterPieces(Voiture voiture, List<Piece> pieces){
        for (Piece p:pieces) {
            voiture.ajouterPiece(p);
        }
    }

}
