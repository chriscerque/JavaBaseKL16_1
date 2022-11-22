package net.ent.etrs.presenter;

import net.ent.etrs.model.commons.utils.AffichageConsole;
import net.ent.etrs.model.entities.Piece;
import net.ent.etrs.model.entities.Voiture;
import net.ent.etrs.model.entities.references.Couleur;
import net.ent.etrs.model.entities.references.FamilleTypePiece;
import net.ent.etrs.model.entities.references.TypePiece;
import net.ent.etrs.model.facades.FacadeMetier;
import net.ent.etrs.model.facades.FacadeMetierImpl;

import java.util.List;

public class Presenter {

    private FacadeMetier metier;

    private static void afficherPiece(final Piece piece){
        AffichageConsole.afficherMessageSansSautLigne(piece.getTypePiece().toString());
    }

    private static void traiterChoix(final int choix){

    }

    private static Piece ajouterPiece(){

        return null;
    }

    private static void listerVoiture(){

    }

    private static List<Voiture> getLstVoiture(){
        //List<Voiture> lst =  FacadeMetierImpl.listerVoiture();
        return null;
    }

    private static Couleur selectionnerCouleur(){
        return null;
    }

    private static String selectionnerVoiture(final List<Voiture> voitures){
        return null;
    }

    private static void creerVoiture(){}

    private static TypePiece selectionnerTypePiece(final FamilleTypePiece ftp){
        return null;
    }

    public static void main(String[] args) {

    }

    private static  void afficherVoiture(final Voiture voiture){

    }

    private static void validerCreerVoiture(final String numSerie, final String marque, final String model, Couleur couleur, List<Piece> pieces){

    }

    private static void supprimerVoiture(){}

    private static FamilleTypePiece selectionnerFamilleTypePiece(){
        return null;
    }
}
