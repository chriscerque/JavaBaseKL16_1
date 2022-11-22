package net.ent.etrs.voiture.presenter;

import net.ent.etrs.commons.utils.AffichageConsole;
import net.ent.etrs.commons.utils.LectureConsole;
import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.exceptions.PieceException;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.FamilleTypePiece;
import net.ent.etrs.voiture.model.entities.references.TypePiece;
import net.ent.etrs.voiture.model.facades.FacadeMetier;
import net.ent.etrs.voiture.model.facades.FacadeMetierFactory;
import net.ent.etrs.voiture.model.facades.exceptions.BusinessException;
import net.ent.etrs.voiture.presenter.exceptions.PresenterException;
import net.ent.etrs.voiture.view.ConstantesView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Presenter {
    private static FacadeMetier facadeMetier;

    public Presenter() {
    }

    public static void main(String[] args) {
        facadeMetier = FacadeMetierFactory.fabriquerFacadeMetier();
        int choix = 0;
        do {
            // afficher le menu
            choix = afficherMenu();
            // traiter les options du menu
            traiterChoix(choix);

        } while (choix != 0);
    }

    private static int afficherMenu() {
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesView.MENU),"Gestion de parc informatique");
        return LectureConsole.lectureChoixInt(0, ConstantesView.MENU.length);
    }

    private static void afficherPiece(Piece piece) {
        AffichageConsole.afficherMessageAvecSautLigne(piece.getTypePiece().getFamilleTypePiece().getLibelle());
        AffichageConsole.afficherMessageAvecSautLigne(piece.getTypePiece().getLibelle());
        AffichageConsole.afficherMessageAvecSautLigne(piece.getNumSerie());
    }

    private static void traiterChoix(int choix) {
        switch (choix) {
            case 1: //Créer une voiture
                creerVoiture();
                break;
            case 2: //Liste les voitures
                listerVoiture();
                break;
            case 3: //Supprimer une voiture
                supprimerVoiture();
                break;
            default:
                break;
        }
    }

    private static Piece ajouterPiece() {
        return null;
    }

    private static void listerVoiture() {
        if (facadeMetier.listerVoiture().isEmpty()) {
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.LISTER_VOITURE_VIDE);
        } else {
            for (Voiture voiture : facadeMetier.listerVoiture()){
                afficherVoiture(voiture);
            }
        }

    }

    private static List<Voiture> getLstVoiture() {
        return null;
    }

    private static Couleur selectionnerCouleur() {
        for (Couleur couleur :
                Couleur.values()) {
            AffichageConsole.afficherMessageAvecSautLigne((couleur.ordinal()+1) + " - " + couleur.name());
        }

        AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SELECTIONNER_COULEUR);
        int choix = LectureConsole.lectureChoixInt(1, Couleur.values().length);
        return Couleur.values()[choix - 1];
    }

    private static String selectionnerVoiture(List<Voiture> voitures) {
        return null;
    }

    private static void creerVoiture() {
        try {
            String numSerie = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
            String marque = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MARQUE);
            String model = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MODEL);

            Couleur couleur = selectionnerCouleur();

            List<Piece> pieces = new ArrayList<>();
            String numserie;
            boolean encore = true;
            do {
                TypePiece typePiece = selectionnerTypePiece(selectionnerFamilleTypePiece());
                numSerie = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
                pieces.add(new Piece(numSerie, typePiece));
                encore = LectureConsole.lectureBoolean(ConstantesView.AJOUT_PIECE_OUI_NON);
            } while (encore);
            facadeMetier.creerVoiture(numSerie, marque, model, couleur, pieces);
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SUPPRESSION_REUSSIE);
        } catch (NumSerieException | PieceException | BusinessException e) {
            AffichageConsole.afficherErreur(ConstantesView.MSG_AJOUTER_PIECE_EXCEPTION);
        }
    }

    private static TypePiece selectionnerTypePiece(FamilleTypePiece familleTypePiece) {
        int i = 1;
        for (TypePiece typePiece :
                TypePiece.listerPieceByFamille(familleTypePiece)) {
            AffichageConsole.afficherMessageAvecSautLigne(i + " - " + typePiece.getPosition());
            i++;
        }
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SELECTIONNER_TYPE_PIECE);
        int choix = LectureConsole.lectureChoixInt(1, TypePiece.listerPieceByFamille(familleTypePiece).size());
        return TypePiece.listerPieceByFamille(familleTypePiece).get(choix - 1);
    }

    private static void afficherVoiture(Voiture voiture) {
        AffichageConsole.afficherMessageAvecSautLigne("Marque : " + voiture.getMarque());
        AffichageConsole.afficherMessageAvecSautLigne("Marque : " + voiture.getModel());
        AffichageConsole.afficherMessageAvecSautLigne("Marque : " + voiture.getCouleur().name());
        AffichageConsole.afficherMessageAvecSautLigne("Marque : " + voiture.getNumSerie());
        for (Piece piece :
                voiture.getPieces()) {
            afficherPiece(piece);
        }
    }

    private static void validerCreerVoiture(String numSerie, String marque, String model, Couleur couleur, List<Piece> pieces) {

    }

    private static void supprimerVoiture() {
        try {
            if (facadeMetier.listerVoiture().isEmpty()) {
                AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.LISTER_VOITURE_SUPPRESSION_VIDE);
            } else {
                listerVoiture();
                int choix = LectureConsole.lectureChoixInt(1, facadeMetier.listerVoiture().size());

                facadeMetier.supprimerVoiture(facadeMetier.listerVoiture().get(choix).getNumSerie());
                AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SUPPRESSION_REUSSIE);
            }
        }catch (BusinessException e) {
            AffichageConsole.afficherErreur(ConstantesView.LISTER_VOITURE_SUPPRESSION_VIDE);
        }
    }

    private static FamilleTypePiece selectionnerFamilleTypePiece() {
        for (FamilleTypePiece familleTypePiece :
                FamilleTypePiece.values()) {
            AffichageConsole.afficherMessageAvecSautLigne((familleTypePiece.ordinal()+1) + " - " + familleTypePiece.name());
        }
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SELECTIONNER_FAMILLE_TYPE_PIECE);
        int choix = LectureConsole.lectureChoixInt(1, FamilleTypePiece.values().length);
        return FamilleTypePiece.values()[choix - 1];
    }
}
