package net.ent.etrs.voiture.presenter;

import net.ent.etrs.commons.utils.AffichageConsole;
import net.ent.etrs.commons.utils.LectureConsole;
import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.exceptions.TypePieceException;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.FamilleTypePiece;
import net.ent.etrs.voiture.model.entities.references.TypePiece;
import net.ent.etrs.voiture.model.facades.FacadeMetier;
import net.ent.etrs.voiture.model.facades.FacadeMetierFactory;
import net.ent.etrs.voiture.model.facades.exceptions.BusinessException;
import net.ent.etrs.voiture.presenter.references.ConstantesPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Presenter {
    //region ATTRIBUTS
    private static FacadeMetier metier;

    //endregion
    //region CONSTRUCTEUR(S)

    public Presenter() {
    }

    //endregion
    public static void main(String[] args) {
        metier = FacadeMetierFactory.fabriquerFacadeMtier();
        int choix;
        do {
            //Afficher le menu
            AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesPresenter.MENU), ConstantesPresenter.NOM_MENU);
            //Récupérer le choix de l'utilisateur
            choix = LectureConsole.lectureChoixInt(0, ConstantesPresenter.MENU.length);
            //Traiter les options du menu
            traiterChoix(choix);
        } while (choix != 0);

    }

    private static void traiterChoix(final int choix) {
        switch (choix) {
            case 1: //Créer une voiture
                creerVoiture();
                break;
            case 2: //Lister les voitures
                listerVoiture();
                break;
            case 3: //Supprimer une voiture
                supprimerVoiture();
                break;
        }
    }

    private static void supprimerVoiture() {
        try {
            String numSerie = selectionnerVoiture(getLstVoiture());
            metier.supprimerVoiture(numSerie);
        } catch (BusinessException e) {
            AffichageConsole.afficherMessageAvecSautLigne(e.getMessage());
        }
    }

    private static String selectionnerVoiture(final List<Voiture> lstVoiture) {
        List<String> lstLibelleVoiture = new ArrayList<>();
        for (Voiture v : lstVoiture) {
            lstLibelleVoiture.add(String.format(ConstantesPresenter.AFFICHAGE_VOITURE, v.getNumSerie(), v.getCouleur().name()));
        }
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesPresenter.SELECTION_VOITURE);
        AffichageConsole.afficherMenuSimple(lstLibelleVoiture);
        int choixVoiture = LectureConsole.lectureChoixInt(1, lstLibelleVoiture.size());
        return lstVoiture.get(choixVoiture - 1).getNumSerie();
    }

    private static void listerVoiture() {
        for (Voiture v : getLstVoiture()) {
            afficherVoiture(v);
            for (Piece p : v.getPieces()) {
                afficherPiece(p);
            }
        }

    }

    private static List<Voiture> getLstVoiture() {
        if (metier.listerVoiture().size() == 0) {
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesPresenter.LISTE_VOITURE_VIDE);
        }
        return metier.listerVoiture();
    }

    private static void afficherVoiture(final Voiture v) {
        AffichageConsole.afficherMessageAvecSautLigne((String.format(ConstantesPresenter.AFFICHAGE_VOITURE, v.getNumSerie(), v.getCouleur().name())));
    }

    private static void afficherPiece(final Piece p) {
        AffichageConsole.afficherMessageAvecSautLigne((String.format(ConstantesPresenter.AFFICHAGE_PIECE, p.getNumSerie(), p.getTypePiece().getLibelle())));
    }

    private static void creerVoiture() {
        try {
            //Entrer les informations nécessaire
            String numSerie = LectureConsole.lectureChaineCaracteres(ConstantesPresenter.SAISIE_NUM_SERIE);
            String model = LectureConsole.lectureChaineCaracteres(ConstantesPresenter.SAISIE_MODEL);
            String marque = LectureConsole.lectureChaineCaracteres(ConstantesPresenter.SAISIE_MARQUE);
            Couleur couleur = selectionnerCouleur();
            //Ajout des pièces
            int choixPiece;
            List<Piece> pieces = new ArrayList<>();
            do {
                AffichageConsole.afficherMenuSimple(Arrays.asList(ConstantesPresenter.MENU_PIECE));
                choixPiece = LectureConsole.lectureChoixInt(1, ConstantesPresenter.MENU_PIECE.length);
                //Choix des paramètres de la pièce
                pieces.add(ajouterPiece());
            } while (choixPiece != 1);
            validerCreerVoiture(numSerie, model, marque, couleur, pieces);
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesPresenter.SUCCES_VOITURE_CREER);
        } catch (BusinessException | TypePieceException e) {
            AffichageConsole.afficherMessageAvecSautLigne(e.getMessage());
        }
    }

    private static void validerCreerVoiture(final String numSerie, final String model, final String marque, final Couleur couleur, final List<Piece> pieces) {
        try {
            metier.creerVoiture(numSerie, model, marque, couleur, pieces);
        } catch (BusinessException e) {
            AffichageConsole.afficherMessageAvecSautLigne(e.getMessage());
        }
    }

    private static Piece ajouterPiece() throws BusinessException, TypePieceException {
        String numSeriePiece = LectureConsole.lectureChaineCaracteres(ConstantesPresenter.SAISIE_NUM_SERIE_PIECE);
        return metier.creerPiece(numSeriePiece, selectionnerTypePiece(selectionnerFamilleTypePiece()));
    }

    private static TypePiece selectionnerTypePiece(FamilleTypePiece choixFamille) throws TypePieceException {
        List<String> lstTypePiece = new ArrayList<>();
        for (TypePiece f : TypePiece.listerPieceByFamille(choixFamille)) {
            lstTypePiece.add(f.getLibelle());
        }
        AffichageConsole.afficherMenuSimple(lstTypePiece);
        int choixType = LectureConsole.lectureChoixInt(1, lstTypePiece.size());
        return Arrays.asList(TypePiece.values()).get(choixType - 1);
    }

    private static FamilleTypePiece selectionnerFamilleTypePiece() {
        List<String> lstFamilleTypePiece = new ArrayList<>();
        for (FamilleTypePiece f : FamilleTypePiece.values()) {
            lstFamilleTypePiece.add(f.getLibelle());
        }
        AffichageConsole.afficherMenuSimple(lstFamilleTypePiece);
        int choixFamile = LectureConsole.lectureChoixInt(1, lstFamilleTypePiece.size());
        return Arrays.asList(FamilleTypePiece.values()).get(choixFamile - 1);
    }


    private static Couleur selectionnerCouleur() {
        List<String> lstCouleur = new ArrayList<>();
        for (Couleur c : Couleur.values()) {
            lstCouleur.add(c.name());
        }
        AffichageConsole.afficherMenuSimple(lstCouleur);
        int choixCouleur = LectureConsole.lectureChoixInt(1, Couleur.values().length);
        return Arrays.asList(Couleur.values()).get(choixCouleur - 1);
    }
}
