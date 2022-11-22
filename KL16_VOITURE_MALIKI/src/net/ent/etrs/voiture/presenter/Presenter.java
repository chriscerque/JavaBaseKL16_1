package net.ent.etrs.voiture.presenter;

import net.ent.etrs.voiture.commons.utils.AffichageConsole;
import net.ent.etrs.voiture.commons.utils.LectureConsole;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.facades.FacadeMetier;
import net.ent.etrs.voiture.model.entities.facades.FacadeMetierFactory;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Presenter {
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


    private static int afficherMenu() {
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesPresenter.MENU), "MaCommande");
        return LectureConsole.lectureChoixInt(0, ConstantesPresenter.MENU.length);
    }

    private static void traiterOption(int choix) {
        switch (choix) {
            case 1: //Créer une voiture
                CreerVoiture();
                break;
            case 2: //Supprimer une voiture
                listerVoitures();
                break;
//            case 3: //Lister voitures
//                SupprimerVoiture();
//                break;
        }
    }

    private static void listerVoitures() {
        for (Voiture voiture : facadeMetier.listerVoiture()){
        }
    }

    private static void CreerVoiture() {
        String numSerie = LectureConsole.lectureChaineCaracteres(ConstantesPresenter.SAISIE_NUM_SERIE_VOITURE);
        String marque = LectureConsole.lectureChaineCaracteres(ConstantesPresenter.SAISIE_MARQUE_VOITURE);
        String model = LectureConsole.lectureChaineCaracteres(ConstantesPresenter.SAISIE_MODEL_VOITURE);
        Couleur couleur = recupererCouleur();
        TypePiece typePiece = recuperTypePieces();
    }

    private static TypePiece recuperTypePieces() {
        List<String> lstpieces = new ArrayList<>();
        for (TypePiece typePiece : TypePiece.values()){
            lstpieces.add(typePiece.name());
        }
        int choix = getChoixUtilisateur(lstpieces, 1);
        return TypePiece.valueOf(lstpieces.get(choix-1));
    }



    private static Couleur recupererCouleur() {
        List<String> lstCouleurs = new ArrayList<>();
        for (Couleur couleur : Couleur.values()){
            lstCouleurs.add(couleur.name());
        }
        int choix = getChoixUtilisateur(lstCouleurs, 1);
        return Couleur.valueOf(lstCouleurs.get(choix-1));
    }
    private static int getChoixUtilisateur(final List<String> strings, final int indiceDepart) {
        AffichageConsole.afficherMenuSimple(strings);
        return LectureConsole.lectureChoixInt(indiceDepart, strings.size());
    }
}
