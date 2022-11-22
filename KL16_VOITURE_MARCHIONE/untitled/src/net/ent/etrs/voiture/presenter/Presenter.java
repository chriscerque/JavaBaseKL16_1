package net.ent.etrs.voiture.presenter;

import net.ent.etrs.commons.utils.AffichageConsole;
import net.ent.etrs.commons.utils.LectureConsole;
import net.ent.etrs.voiture.model.entities.EntitiesFactory;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.facades.FacadeMetier;
import net.ent.etrs.voiture.model.facades.exceptions.BusinessException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Presenter {

    //-------------------------------
    //          Presenter
    //-------------------------------


    private FacadeMetier metier;

    public Presenter(FacadeMetier metier) {
        ;
        this.metier = metier;
    }



    public static void main(String[] args) {

        initialiserVoiture();
        executer();
    }


    private static void initialiserVoiture() {
//        Voiture v = new Voiture("Mercedes", "C-65-AMG", Couleur.NOIR);
//        Voiture v1 = new Voiture("Porsche", "Cayenne", Couleur.BLANC);
    }
    private static void lstVoitures() {

    }


    private static void executer() {
        int choix = 0;
        do {
            //Afficher le menu
            AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesPresenter.MENU), ConstantesPresenter.MENU_NAME);
            //Récupérer choix utilisateur
            choix = LectureConsole.lectureChoixInt(0, ConstantesPresenter.MENU.length);
            //Traiter le choix de l'utilisateur

            traiterChoix(choix);
        } while (choix != 0);

    }

    private static void traiterChoix(int choix) {
        switch (choix) {
            case 1:
                //Afficher les voitures

                break;
            case 2:
                //Créer une voiture

                break;
            case 3:
                //
                break;
            case 4:
                //

                break;
            case 5:
                //
                break;
            case 6:
                //
                break;
            case 0:
                //Afficher message sortie
                break;
        }
    }


    //-------------------------------
    //          MENU - 2
    //-------------------------------

    private void creerVoiture()  {

    }

    private static Voiture saisirVoiture() {


        String marque = LectureConsole.lectureChaineCaracteres("Marque de la nouvelle voiture ?");

        String model = LectureConsole.lectureChaineCaracteres("Model de la nouvelle voiture ?");

        Couleur couleur = Couleur.valueOf(LectureConsole.lectureChaineCaracteres("Couleur de la nouvelle voiture ?"));

        return EntitiesFactory.fabriquerVoiture(marque, model, couleur);

    }
}




