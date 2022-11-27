package net.ent.etrs.voiture.model.entities;


import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.exceptions.PieceException;
import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

import java.util.List;

public class EntitiesFactory {

    public EntitiesFactory() {
    }

    public static Voiture fabriquerVoiture(String serie, String marque, String model, Couleur couleur) throws VoitureException, NumSerieException {

            Voiture voiture = new Voiture(serie, marque,model);
            voiture.setCouleur(couleur);
        return voiture;
    }
    public static Voiture fabriquerVoiture(String serie, String marque, String model, Couleur couleur, List piece) throws VoitureException, NumSerieException {

        Voiture voiture = new Voiture(serie, marque,model);
        voiture.setCouleur(couleur);
        voiture.getPiece(piece) ;
        return voiture;
    }
    public static Piece fabriquerPiece(String  numSerie, TypePiece piece) throws
            PieceException, NumSerieException {
        Piece piece1 = new Piece(numSerie, piece);

        return piece1;



    }




    private void ajouterPiece(Voiture voiture, List piece){



    }




}



