package net.ent.etrs.voiture.presenter;


import net.ent.etrs.voiture.model.entities.EntitiesFactory;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.references.Constantes;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.FamilleTypePiece;
import net.ent.etrs.voiture.model.entities.references.TypePiece;
import net.ent.etrs.voiture.model.facades.FacadeMetier;
import net.ent.etrs.voiture.utils.AffichageConsole;
import net.ent.etrs.voiture.utils.LectureConsole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class presenter {
    private static FacadeMetier metier;
    private static FacadeMetier facadeMetier;


    public static void main(String[] args) throws net.ent.etrs.model.entities.exceptions.VoitureException, net.ent.etrs.model.entities.exceptions.NumSerieException, net.ent.etrs.model.facades.exceptions.BusinessException {

        int choix = 0;
        do {
            // afficher le menu
            choix = afficherMenu();
            // traiter les options du menu
            traiterOption(choix);

        } while (choix != 0);
    }

    private static void traiterOption(int choix) throws net.ent.etrs.model.entities.exceptions.VoitureException, net.ent.etrs.model.entities.exceptions.NumSerieException, net.ent.etrs.model.facades.exceptions.BusinessException {
        switch (choix) {
            case 1:
                creerVoiture();
                break;
            case 2:
                listerVoiture();
                break;
            case 3:
               // supprimerVoiture();
                break;


            default:
                break;
        }
    }

    private static void listerVoiture() {
        for (Voiture voiture : facadeMetier.listerVoiture()){
            affichervoiture(voiture);
        }
    }

    private static void affichervoiture(Voiture voiture) {
        if(voiture != null){
            afficherVoitutre(voiture);
        }

    }

    private static void afficherVoitutre(Voiture voiture) {
        StringBuilder sb = new StringBuilder((voiture.getNumSerie()) );
        sb.append(String.format("\ttype de matériel : %s",voiture.getMarque()));
        sb.append(System.lineSeparator());
        AffichageConsole.afficherErreur(sb.toString());
    }


    private static   void creerVoiture() throws net.ent.etrs.model.entities.exceptions.VoitureException, net.ent.etrs.model.entities.exceptions.NumSerieException, net.ent.etrs.model.facades.exceptions.BusinessException {

        Voiture nouvelleVoiture = presenter.saisirVoiture();
        List<String> lstPiece = new ArrayList<>();


        metier.creerVoiture(nouvelleVoiture.getNumSerie(),nouvelleVoiture.getMarque(),nouvelleVoiture.getModel(),nouvelleVoiture.getCouleur());
    }

    private static Voiture saisirVoiture() throws net.ent.etrs.model.entities.exceptions.VoitureException, net.ent.etrs.model.entities.exceptions.NumSerieException {

        String numSerie = LectureConsole.lectureChaineCaracteres("Entrer Numero de serie ?");

        String marque = LectureConsole.lectureChaineCaracteres("Entrer la marque ?");

        String model = LectureConsole.lectureChaineCaracteres("Entrer le model ");
        Couleur couleur = saisirCouleur();
        TypePiece piece = saisirPiece();

        List<String> lstPiece = new ArrayList<>();





        return EntitiesFactory.fabriquerVoiture(numSerie, marque, model,couleur);
    }

    private static TypePiece saisirPiece() {
        List<String> lstPiece = new ArrayList<>();
        for (FamilleTypePiece typePiece : FamilleTypePiece.values()){
            lstPiece.add(typePiece.getLibelle());
        }
        int choix = getChoixUtilisateur(lstPiece, 1);
        return TypePiece.valueOf(lstPiece.get(choix-1));
    }


    private static Couleur saisirCouleur() {

        List<String> lstCouleur = new ArrayList<>();
        for (Couleur couleur : Couleur.values()){
            lstCouleur.add(couleur.name());
        }
        int choix = getChoixUtilisateur(lstCouleur, 1);
        return Couleur.valueOf(lstCouleur.get(choix-1));
    }

    private static int getChoixUtilisateur(final List<String> strings, final int indiceDepart) {
        AffichageConsole.afficherMenuSimple(strings);
        return LectureConsole.lectureChoixInt(indiceDepart, strings.size());
    }

    private static int afficherMenu() {
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(Constantes.MENU),"Gestion Voiture");
        return LectureConsole.lectureChoixInt(0, Constantes.MENU.length);
    }
}
