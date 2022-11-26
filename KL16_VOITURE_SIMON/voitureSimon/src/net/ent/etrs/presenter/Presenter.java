package net.ent.etrs.presenter;

import net.ent.etrs.voiture.commons.utils.AffichageConsole;
import net.ent.etrs.voiture.commons.utils.LectureConsole;
import net.ent.etrs.voiture.model.facades.FacadeMetier;
import net.ent.etrs.voiture.model.facades.FacadeMetierFactory;
import net.ent.etrs.voiture.view.ConstantesView;

import java.net.FileNameMap;
import java.util.Arrays;

public class Presenter {
    private static FacadeMetier facadeMetier;

    public static void main(String[] args) {
        facadeMetier = FacadeMetierFactory.fabriquerFacadeMetier();

        int choix = 0 ;
        do{
            //afficher le menu
            choix = afficherMenu();
            //traiter les options du menu
            traiterOption(choix);
        }while (choix != 0);

    }

    private static int afficherMenu() {
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesView.MENU,"MaCommande"));
        return LectureConsole.lectureChoixInt(0, ConstantesView.MENU.length);
    }

    private static void traiterOption(final int choix){
        switch (choix){
            case 1: //Créer une voiture
                creerVoiture();
                break;
            case 2: //lister les voitures
               listerVoiture();
                break;
            case 3: //Supprimer une voiture
                supprimerVoiture();
                break;
            default:
                break;
        }
    }

    private static void supprimerVoiture() {

    }

    private static void listerVoiture() {
    }

    private static void creerVoiture() {
        try {
            final String numSerie = LectureConsole.lectureChaineCaracteres("Numero de série");
            AffichageConsole.afficherMessageSansSautLigne();

        }
    }
}
