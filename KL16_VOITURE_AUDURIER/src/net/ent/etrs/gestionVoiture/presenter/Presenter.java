package net.ent.etrs.gestionVoiture.presenter;

import net.ent.etrs.commons.AffichageConsole;
import net.ent.etrs.commons.GestionException;
import net.ent.etrs.commons.LectureConsole;
import net.ent.etrs.gestionVoiture.model.entities.Piece;
import net.ent.etrs.gestionVoiture.model.entities.Voiture;
import net.ent.etrs.gestionVoiture.model.entities.exceptions.FamilleTypePieceException;
import net.ent.etrs.gestionVoiture.model.entities.references.Couleur;
import net.ent.etrs.gestionVoiture.model.entities.references.FamilleTypePiece;
import net.ent.etrs.gestionVoiture.model.entities.references.TypePiece;
import net.ent.etrs.gestionVoiture.model.facades.FacadeMetier;
import net.ent.etrs.gestionVoiture.model.facades.FacadeMetierFactory;
import net.ent.etrs.gestionVoiture.model.facades.exceptions.BusinessException;
import net.ent.etrs.gestionVoiture.view.ConstantesView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Presenter {
    static FacadeMetier metier = FacadeMetierFactory.fabriquerFacadeMetier();

    public static void main(String[] args) {
        executer();
    }

    private static void executer() {
        boolean sortie = false;
        while (!sortie) {
            AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesView.MENU), ConstantesView.MENU_NOM);
            int choix = LectureConsole.lectureChoixInt(0, ConstantesView.MENU.length);
            sortie = traiterChoix(choix);
        }

    }

    private static boolean traiterChoix(final int choix) {
        switch (choix) {
            case 0:
                return true;
            case 1:
                creerVoiture();
                break;
            case 2:
                listerVoiture();
                break;
            case 3:
                supprimerVoiture();
                break;
        }
        return false;
    }

    private static void creerVoiture() {
        try {
            boolean recommencer = true;
            do {
                String numSerieVoiture = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
                String marque = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MARQUE);
                String model = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MODEL);
                Couleur couleur = selectionnerCouleur();
                List<Piece> lstPiece = ajouterPiece();
                validerCreerVoiture(numSerieVoiture, marque, model, couleur, lstPiece);
                recommencer = demanderRecommencer();
            } while (recommencer);
        } catch (BusinessException e) {
            System.out.println(GestionException.lastExceptionToString(e));
        }

    }

    private static void validerCreerVoiture(final String numSerieVoiture, final String marque, final String model, final Couleur couleur, final List<Piece> lstPiece)
            throws BusinessException {
        metier.creerVoiture(numSerieVoiture, marque, model, couleur, lstPiece);
        System.out.println(ConstantesView.CREATION_REUSSIE);
    }

    private static FamilleTypePiece selectionnerFamilleTypePiece() {
        AffichageConsole.afficherMenuSimple(FamilleTypePiece.getAllLibelles());
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SELECTIONNER_FAMILLE_TYPE_PIECE);
        int choix = LectureConsole.lectureChoixInt(1, FamilleTypePiece.getAllLibelles().size());
        return FamilleTypePiece.values()[choix - 1];
    }

    private static boolean demanderRecommencer() {
        return LectureConsole.lectureBoolean(ConstantesView.MSG_RECOMMENCER);
    }

    private static boolean demanderAjouterpiece() {
        return LectureConsole.lectureBoolean(ConstantesView.AJOUT_PIECE_OUI_NON);
    }

    private static TypePiece selectionnerTypePiece(final FamilleTypePiece familleTypePiece)
            throws FamilleTypePieceException {
        //TODO get all libelle by famille
        List<String> lstTypePieceString = new ArrayList<>();
        for (TypePiece typePiece : TypePiece.listerPieceByFamille(familleTypePiece)) {
            lstTypePieceString.add(typePiece.getPosition().getLibelle());
        }
        AffichageConsole.afficherMenuSimple(lstTypePieceString);
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SELECTIONNER_TYPE_PIECE);
        int choix = LectureConsole.lectureChoixInt(1, lstTypePieceString.size());
        return TypePiece.listerPieceByFamille(familleTypePiece).get(choix - 1);
    }

    private static List<Piece> ajouterPiece() {
        List<Piece> lstPiece = new ArrayList<>();
        boolean recommencer = true;
        do {
            try {
                FamilleTypePiece familleTypePiece = selectionnerFamilleTypePiece();
                TypePiece typePiece = selectionnerTypePiece(familleTypePiece);
                String numSeriePiece = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
                Piece p = metier.creerPiece(numSeriePiece, typePiece);
                lstPiece.add(p);
            } catch (BusinessException |
                     FamilleTypePieceException e) {
                System.out.println(ConstantesView.MSG_AJOUTER_PIECE_EXCEPTION);
                System.out.println(GestionException.lastExceptionToString(e));
            }
            recommencer = demanderAjouterpiece();
        } while (recommencer);
        return lstPiece;
    }

    private static Couleur selectionnerCouleur() {
        AffichageConsole.afficherMenuSimple(Couleur.getAllLibelles());
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SELECTIONNER_COULEUR);
        int choix = LectureConsole.lectureChoixInt(1, Couleur.getAllLibelles().size());
        return Couleur.values()[choix - 1];
    }

    private static void listerVoiture() {
        List<Voiture> lstVoitures = metier.listerVoiture();
        if (lstVoitures.size() == 0) {
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.LISTER_VOITURE_VIDE);
        } else {
            System.out.printf(ConstantesView.LISTER_VOITURE_PATTERN, ConstantesView.LISTER_VOITURE_MARQUE, ConstantesView.LISTER_VOITURE_MODELE, ConstantesView.LISTER_VOITURE_COULEUR, ConstantesView.LISTER_VOITURE_NUM_SERIE);
            for (Voiture voiture : lstVoitures) {
                System.out.printf(ConstantesView.LISTER_VOITURE_PATTERN, voiture.getMarque(), voiture.getModel(), voiture.getCouleur(), voiture.getNumSerie());
                if (voiture.getPieces().size() == 0) {
                    AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.LISTER_PIECE_VIDE);
                } else {
                    System.out.printf(ConstantesView.LISTER_VOITURE_PIECE_PATTERN, ConstantesView.LISTER_PIECE_TYPE, ConstantesView.LISTER_VOITURE_NUM_SERIE);
                    for (Piece piece : voiture.getPieces()) {
                        System.out.printf(ConstantesView.LISTER_VOITURE_PIECE_PATTERN, piece.getTypePiece(), piece.getNumSerie());
                    }
                }
            }
        }
    }

    private static void supprimerVoiture() {
        List<Voiture> lstVoitures = metier.listerVoiture();
        if (lstVoitures.size() == 0) {
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.LISTER_VOITURE_SUPPRESSION_VIDE);
        } else {
            List<String> lstVoitureString = new ArrayList<>();
            for (Voiture voiture : lstVoitures) {
                lstVoitureString.add(String.format(ConstantesView.SUPPRESSION_VOITURE_PATTERN, voiture.getMarque(), voiture.getModel(), voiture.getCouleur(), voiture.getNumSerie()));

            }
            AffichageConsole.afficherMenuSimpleAvecOptionSortie(lstVoitureString, String.format(ConstantesView.LISTER_VOITURE_PATTERN, ConstantesView.LISTER_VOITURE_MARQUE, ConstantesView.LISTER_VOITURE_MODELE, ConstantesView.LISTER_VOITURE_COULEUR, ConstantesView.LISTER_VOITURE_NUM_SERIE));
            int choix = LectureConsole.lectureChoixInt(0, lstVoitures.size());
            try {
                metier.supprimerVoiture(lstVoitures.get(choix).getNumSerie());
                System.out.println(ConstantesView.SUPPRESSION_REUSSIE);
            } catch (BusinessException e) {
                System.out.println(ConstantesView.SUPPRESSION_ECHEC);
            }
        }
    }


}
