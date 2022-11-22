package net.ent.etrs.presenter;

import net.ent.etrs.commons.utils.AffichageConsole;
import net.ent.etrs.commons.utils.LectureConsole;
import net.ent.etrs.model.entities.EntitiesFactory;
import net.ent.etrs.model.entities.Voiture;
import net.ent.etrs.model.entities.exceptions.ConstructionVoitureException;
import net.ent.etrs.model.entities.references.Couleur;
import net.ent.etrs.model.facades.FacadeMetier;
import net.ent.etrs.model.facades.FacadeMetierFactory;
import net.ent.etrs.view.ConstantesView;

import java.util.Arrays;
import java.util.List;

public class Presenter {

    private static FacadeMetier facadeMetier;

    private static Voiture saisirVoiture() {
        String numSerie = saisirNumSerie();
        String marque = saisirMarque();
        String model = saisirModel();
        String couleur = saisirCouleur();
        try {
            return EntitiesFactory.fabriquerVoiture(numSerie, marque, model, Couleur.valueOf(couleur));
        } catch (ConstructionVoitureException e) {
            throw new RuntimeException(e);
        }
    }

    private static String saisirCouleur() {
        return LectureConsole.lectureChaineCaracteres(ConstantesView.SELECTIONNER_COULEUR);
    }

    private static String saisirModel() {
        return LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MODEL);
    }

    private static String saisirMarque() {
        return LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MARQUE);
    }

    private static String saisirNumSerie() {
        return LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
    }

    public static void main(String[] args) {
        facadeMetier = FacadeMetierFactory.fabriquerFacadeMetier();
        int choix = 0;
        do {
            choix = afficherMenu();
            traiterOption(choix);
        } while (choix != 0);


    }

    private static void traiterOption(final int choix) {
        switch (choix) {
            //Créer une voiture
            case 1:
                creerVoiture();
                break;
            //Lister les voitures
            case 2:
                listerVoiture();
                break;
            //Supprimer une voiture
            case 3:
                supprimerVoiture();
                break;
            default:
                break;


        }
    }

    private static void creerVoiture() {
        facadeMetier.creerVoiture(saisirVoiture());

    }

    private static void supprimerVoiture() {
        try {
            facadeMetier.supprimerVoiture(selectionnerVoiture(facadeMetier.listerVoiture()));
            AffichageConsole.afficherErreur(ConstantesView.SUPPRESSION_REUSSIE);
        } catch (Exception e) {
            AffichageConsole.afficherErreur(e.getMessage());
        }

    }

    private static String selectionnerVoiture(List<Voiture> listerVoiture) {
    }


    private static void listerVoiture() {
        if (facadeMetier.listerVoiture().size() != 0) {
            for (Voiture v : facadeMetier.listerVoiture()) {
                AffichageConsole.afficherMessageAvecSautLigne(String.format("%s %s %s %s %s", v.getCouleur(), v.getMarque(), v.getModel(), v.getPieces(), v.getNumSerie()));
            }
        } else {
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.LISTER_VOITURE_VIDE);
        }
    }

    private static int afficherMenu() {
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesView.MENU), "MaCommande");
        return LectureConsole.lectureChoixInt(0, ConstantesView.MENU.length);
    }


}
