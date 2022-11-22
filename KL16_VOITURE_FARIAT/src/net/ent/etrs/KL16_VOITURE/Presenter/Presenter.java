package net.ent.etrs.KL16_VOITURE.Presenter;

import net.ent.etrs.KL16_VOITURE.Model.Facades.FacadeMetier;
import net.ent.etrs.KL16_VOITURE.Model.Facades.FacadeMetierFactory;
import net.ent.etrs.KL16_VOITURE.Model.Facades.excpetions.BusinessException;
import net.ent.etrs.KL16_VOITURE.Model.entities.Voiture;
import net.ent.etrs.KL16_VOITURE.commons.utils.AffichageConsole;
import net.ent.etrs.KL16_VOITURE.commons.utils.LectureConsole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Presenter {
    private static FacadeMetier facadeMetier;

    public static void main(String[] args) {
        facadeMetier = FacadeMetierFactory.fabriquerFacadeMetier();
        int choix = 0;
        do {
            // afficher le menu
            choix = afficherMenu();
            // traiter les options du menu
            traiterOption(choix);

        } while (choix != 0);
    }

    private static void traiterOption(final int choix) throws BusinessException {
        switch (choix) {
            case 1: //Créer une voiture
                creerVoiture();
                break;
            case 2: //Lister une voiture
                listerVoiture();
                break;
            case 3: //Supprimer une voiture
                supprimerVoiture();
                break;

            default:
                break;
        }


    }

    private static int afficherMenu() {
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesView.MENU),"Gestion de parc informatique");
        return LectureConsole.lectureChoixInt(0, ConstantesView.MENU.length);
    }

    /**
     * Lister une voiture.
     */

    private static void listerVoiture(){
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.LISTER_VOITURE_VIDE);
        for (Voiture v : facadeMetier.listerVoiture()) {
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView."");
        }

    }

    /**
     * Creation d'une voiture.
     */

    private static void creerVoiture(){
        try {
            final String numSerie = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
            final String  marque = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MARQUE);
            final String model = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MODEL);
            facadeMetier.creerVoiture(numSerie, marque, model);
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.CREATION_REUSSIE);
        } catch (BusinessException e) {
            AffichageConsole.afficherMessageAvecSautLigne(e.getMessage());
        }

    }

    /**
     * Suppression d'une voiture.
     */
    private static void supprimerVoiture() throws BusinessException {
        if(facadeMetier.listerVoiture().size()==0){
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.LISTER_VOITURE_SUPPRESSION_VIDE);
        }else{
            facadeMetier.supprimerVoiture(facadeMetier.listerVoiture().get(choisirVoiture()-1));
        }


    }

    private static int choisirVoiture() {
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.LISTER_VOITURE_VIDE);
        List<String> lstVoiture = new ArrayList<>();
        for (Voiture v : facadeMetier.listerVoiture()) {
            lstVoiture.add(v);
        }
        AffichageConsole.afficherMenuSimple(lstVoiture);
        return LectureConsole.lectureChoixInt(1, lstVoiture.size());
    }

}
