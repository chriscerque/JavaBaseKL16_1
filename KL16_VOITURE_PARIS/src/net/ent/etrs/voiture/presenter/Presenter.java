package net.ent.etrs.voiture.presenter;

import net.ent.etrs.commons.utils.AffichageConsole;
import net.ent.etrs.commons.utils.LectureConsole;
import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.facades.FacadeMetier;
import net.ent.etrs.voiture.model.facades.FacadeMetierFactory;
import net.ent.etrs.voiture.view.ConstantesView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Presenter {

    private static FacadeMetier metier;

    public static void main(String[] args) {
        metier = FacadeMetierFactory.fabriquerFacadeMetier();
        metier.initialiserApplication();
        int choix = 0;
        do {
            // afficher le menu
            choix = afficherMenu();

            // traiter les options du menu
            traiterChoix(choix);
        }while (choix != 0);
    }

    private static int afficherMenu(){
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesView.MENU), "MaCommande");
        return LectureConsole.lectureChoixInt(0, ConstantesView.MENU.length);
    }

    private static void traiterChoix(final int choix){
        switch (choix){
            case 1: // créer une voiture
                creerVoiture();
                break;
            case 2: // Lister les voitures
                listerVoiture();
                break;
            case 3: // Supprimer une voiture
                supprimerVoiture();
                break;
            default:
                break;
        }
    }

    private static void creerVoiture(){
        try {
            final String numSerie = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
            final String marque = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MARQUE);
            final String model = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MODEL);
        }catch (Exception e){
            AffichageConsole.afficherErreur(e.getMessage());
        }
    }

    private static void listerVoiture(){
        for (Voiture voiture : metier.listerVoiture()){
            afficherVoiture(voiture);
        }
    }

    private static void afficherVoiture(final Voiture voiture){
        afficherVoiture(voiture);
    }

    private static void supprimerVoiture(){
        try {
            metier.supprimerVoiture(selectionnerVoiture(metier.listerVoiture()));
            AffichageConsole.afficherErreur(ConstantesView.SUPPRESSION_REUSSIE);
        }catch (Exception e){
            AffichageConsole.afficherErreur(e.getMessage());
        }
    }

//    private static String selectionnerVoiture(final List<Voiture> lstVoiture) throws Exception{
//        if (lstVoiture.size() == 0){
//            throw new Exception(ConstantesView.LISTER_VOITURE_VIDE);
//        }else {
//            List<String> lstVoitureLibelle = new ArrayList<>();
//            AffichageConsole.afficherErreur(ConstantesView.);
//            for (Voiture voiture : lstVoiture){
//                lstVoitureLibelle.add(voiture.getNumSerie());
//            }
//            return lstVoiture;
//        }
//        return null;
//    }

    private static void afficherPiece(final Piece piece){
        afficherPiece(piece);
    }

    private static List<Voiture> getLstVoiture(){
        return null;
    }

    private static Piece ajouterPiece(){
        return null;
    }


}
