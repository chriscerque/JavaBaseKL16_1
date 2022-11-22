package net.ent.etrs.presenter;

import com.sun.tools.javac.Main;
import net.ent.etrs.commons.AffichageConsole;
import net.ent.etrs.commons.LectureConsole;
import net.ent.etrs.model.entities.Piece;
import net.ent.etrs.model.entities.Voiture;
import net.ent.etrs.model.entities.exceptions.TypePieceException;
import net.ent.etrs.model.entities.references.ConstantesMetier;
import net.ent.etrs.model.entities.references.Couleur;
import net.ent.etrs.model.entities.references.FamilleTypePiece;
import net.ent.etrs.model.entities.references.TypePiece;
import net.ent.etrs.model.facades.FacadeMetier;
import net.ent.etrs.model.facades.FacadeMetierFactory;
import net.ent.etrs.model.facades.exceptions.BusinessException;
import net.ent.etrs.view.ConstantesView;

import java.net.Proxy;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.ent.etrs.commons.AffichageConsole.afficherErreur;

public class Presenter {

    private static FacadeMetier facadeMetier;

    public static void Main(String[] args){
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
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesView.MENU),"Gestion de parc informatique");
        return LectureConsole.lectureChoixInt(0, ConstantesView.MENU.length);
    }

    private static void traiterOption(final int choix) {
        switch (choix) {
            case 1: // Creer une voiture
                creerVoiture();
                break;
            case 2: // Lister les voitures

                break;
            case 3: // Supprimmer une voiture
                supprimerVoiture();
                break;
            default:
                break;
        }
    }

    private static void creerVoiture() {
        try {
            final String numSerie = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
            final String marque = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MARQUE);
            final String model = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MODEL);
            for (int i = 0; i < Couleur.values().length; i++) {
                 final Couleur couleur = Couleur.values()[i];
                AffichageConsole.afficherMessageAvecSautLigne(String.format("%-10s - %-10s", i + 1, couleur));
            }
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SELECTIONNER_COULEUR);
            final int choixCouleur = LectureConsole.lectureChoixInt(1, FamilleTypePiece.values().length);
            final Couleur couleur = Couleur.values()[choixCouleur - 1];

            boolean arret = false;
            List<Piece> pieces = new ArrayList();
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.DEBUT_INSERTION_PIECES);
            do{
                for (int i = 0; i < FamilleTypePiece.values().length; i++) {
                    final FamilleTypePiece familleTypePiece = FamilleTypePiece.values()[i];
                    AffichageConsole.afficherMessageAvecSautLigne(String.format("%-10s - %-15s", i + 1, familleTypePiece.getLibelle()));
                }
                AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SELECTIONNER_FAMILLE_TYPE_PIECE);
                final int choixFamilleTypePiece = LectureConsole.lectureChoixInt(1, FamilleTypePiece.values().length);
                final FamilleTypePiece familleTypePiece = FamilleTypePiece.values()[choixFamilleTypePiece - 1];

                List<TypePiece> typePieces = TypePiece.listerPieceByFamille(familleTypePiece);
                for (int i = 0; i < typePieces.size(); i++ ) {
                    final TypePiece typePiece = TypePiece.values()[i];
                    AffichageConsole.afficherMessageAvecSautLigne(String.format("%-10s - %-15s", i + 1, typePiece.getLibelle()));
                }
                AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SELECTIONNER_TYPE_PIECE);
                final int choixTypePiece = LectureConsole.lectureChoixInt(1, FamilleTypePiece.values().length);
                final TypePiece typePiece = TypePiece.values()[choixFamilleTypePiece - 1];

                final String numSeriePiece = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
                Piece piece = Presenter.facadeMetier.creerPiece(numSeriePiece, typePiece);
                pieces.add(piece);

                if ("oui" == LectureConsole.lectureChaineCaracteres(ConstantesView.AJOUT_PIECE_OUI_NON)) {
                    arret = true;
                }
            }while (arret = false);
            Presenter.facadeMetier.creerVoiture(numSerie, marque, model, couleur, pieces);
        } catch (BusinessException e) {
            afficherErreur(e.getMessage());
        }
    }

    /**
     * Supprime une voiture sélectionnée.
     */

    private static void supprimerVoiture() {
        try {
            final Voiture voiture = choisirVoiture();
            if (voiture != null) {
                Presenter.facadeMetier.supprimerVoiture(voiture.getNumSerie());
                AffichageConsole.afficherMessageAvecSautLigne("Le client à bien été supprimé");
            }
        } catch (BusinessException e) {
            afficherErreur(e.getMessage());
        }
    }

    private static Voiture choisirVoiture(){
        final List<Voiture> voitures = Presenter.facadeMetier.listerVoiture();
        AffichageConsole.afficherMessageAvecSautLigne(String.format(" %-15s | %-15s | %-15s | %-15s", "Marque", "Model", "Couleur", "Num serie"));
        for (int i = 0; i < voitures.size(); i++) {
            final Voiture voiture = voitures.get(i);
            AffichageConsole.afficherMessageAvecSautLigne(String.format("%-15s | %-15s | %-15s | %-15s", voiture.getMarque(), voiture.getModel(), voiture.getCouleur().name(), voiture.getNumSerie()));
        }
        AffichageConsole.afficherMessageSansSautLigne("ID à supprimer : ");

        return null;
    }

    /**
     * Affiche et demande la sélection d'une voiture.
     * @param voitures la liste des voitures.
     * @return la voiture sélectionnée.
     * @throws Exception si la liste des voitures est vide.
     */
    private static Voiture selectionnerVoiture(final List<Voiture> voitures) throws Exception {
        if(voitures.size() == 0) {
            throw new Exception(ConstantesView.LISTER_VOITURE_VIDE);
        }else {
            AffichageConsole.afficherMessageAvecSautLigne(String.format("%-10s | %-10s | %-15s | %-15s", "Num Serie", "Marque", "Model", "Model"));
            for (Voiture voiture : voitures) {

            }
        }
        return null;
    }
}
