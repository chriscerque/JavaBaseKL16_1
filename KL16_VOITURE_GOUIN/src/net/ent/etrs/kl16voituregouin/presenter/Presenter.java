package net.ent.etrs.kl16voituregouin.presenter;

import net.ent.etrs.kl16voituregouin.commons.utils.AffichageConsole;
import net.ent.etrs.kl16voituregouin.commons.utils.LectureConsole;
import net.ent.etrs.kl16voituregouin.model.facades.FacadeMetier;
import net.ent.etrs.kl16voituregouin.model.facades.FacadeMetierFactory;
import net.ent.etrs.kl16voituregouin.view.ConstantesView;

import java.util.Arrays;

public final class Presenter {

    private static FacadeMetier facadeMetier;

    public static void main(String[] args) {
        facadeMetier = FacadeMetierFactory.fabriquerFacadeMetier();

        int choix = 0;
        do{
            choix = afficherMenu();

            traiterChoix(choix);
        }while(choix != 0);

    }


    private static int afficherMenu() {
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesView.MENU),"Gestion de parc informatique");
        return LectureConsole.lectureChoixInt(0, ConstantesView.MENU.length);
    }

    private static void traiterChoix(final int choix) {
        switch (choix) {
            case 1: //Créer un matériel informatique
                creerVoiture();
                break;
            case 2: //Supprimer un matériel informatique
                listerVoiture();
                break;
            case 3: //Lister les matériel informatiques
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

        AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SAISIR_NUM_SERIE);
        String numSerieVoiture = LectureConsole.lectureChaineCaracteres();

        AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SAISIR_MARQUE);
        String marqueVoiture = LectureConsole.lectureChaineCaracteres();

        AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SAISIR_MARQUE);
        String modelVoiture = LectureConsole.lectureChaineCaracteres();
        // je suis un escargot ....
    }


}
