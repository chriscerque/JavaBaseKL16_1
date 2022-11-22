package net.ent.etrs.voiture.presenter;

import net.ent.etrs.commons.utils.AffichageConsole;
import net.ent.etrs.commons.utils.LectureConsole;
import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.FamilleTypePiece;
import net.ent.etrs.voiture.model.entities.references.TypePiece;
import net.ent.etrs.voiture.model.facades.FacadeMetier;
import net.ent.etrs.voiture.model.facades.FacadeMetierFactory;
import net.ent.etrs.voiture.model.facades.exceptions.BusinessException;
import net.ent.etrs.voiture.view.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Presenter {

    private static FacadeMetier metier = FacadeMetierFactory.fabriquerFacadeMetier();

    public static void main(String[] args) {
        int choix = 0;
        do {
            AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesView.MENU), ConstantesView.TITRE);
            choix = LectureConsole.lectureChoixInt(0, ConstantesView.MENU.length);
            traiterChoix(choix);
        } while (choix != 0);
    }

    private static void traiterChoix(final int choix) {
        switch(choix){
            //Créer une voiture
            case 1 : creerVoiture();
                break;
            //Lister les voitures
            case 2 : listerVoiture();
                break;
            //Supprimer une voiture
            case 3 : supprimerVoiture();
                break;
            //Sortir
            case 0 :
                break;
        }
    }

    private static void supprimerVoiture() {
        try {
            List<Voiture> lstVoitures = getLstVoiture();
            if(lstVoitures.size() == 0){
                AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.LISTER_VOITURE_SUPPRESSION_VIDE);
            } else {
                AffichageConsole.afficherMessageAvecSautLigne(String.format("%-20s | %-20s | %-10s | %-10s", ConstantesView.ENTETE_VOITURE_MARQUE, ConstantesView.ENTETE_VOITURE_MODELE, ConstantesView.ENTETE_VOITURE_COULEUR, ConstantesView.ENTETE_VOITURE_NUM));
                String numVoiture = selectionnerVoiture(lstVoitures);
                if(numVoiture == ""){
                    AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.AUCUNE_VOITURE_SUPPR);
                }else {
                    metier.supprimerVoiture(numVoiture);
                    AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SUPPRESSION_REUSSIE);
                }
            }
        } catch (BusinessException e) {
            AffichageConsole.afficherErreur(ConstantesView.MSG_AJOUTER_VOITURE_EXCEPTION);
            AffichageConsole.afficherErreur(e.getMessage());
            Throwable ex = e.getCause();
            while(ex != null){
                AffichageConsole.afficherErreur(ex.getMessage());
                ex = ex.getCause();
            }
        }

    }

    private static String selectionnerVoiture(final List<Voiture> lstVoitures) {
        for(int i = 0; i < lstVoitures.size(); i++){
            AffichageConsole.afficherMessageAvecSautLigne(String.format("%s - %-20s | %-20s | %-10s | %-10s", i + 1, lstVoitures.get(i).getMarque(), lstVoitures.get(i).getModel(), lstVoitures.get(i).getCouleur(), lstVoitures.get(i).getNumSerie()));
        }
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SORTIE_MENU);
        AffichageConsole.afficherMessageSansSautLigne(ConstantesView.SELECTIONNER_VOITURE);
        final int choix = LectureConsole.lectureChoixInt(0, TypePiece.values().length);
        if(choix == 0){
            return "";
        } else {
            return lstVoitures.get(choix - 1).getNumSerie();
        }
    }

    private static void listerVoiture() {
        List<Voiture> lstVoitures = getLstVoiture();
        if(lstVoitures.size() == 0){
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.LISTER_VOITURE_VIDE);
        } else {
            AffichageConsole.afficherMessageAvecSautLigne(String.format("%-20s | %-20s | %-10s | %-10s", ConstantesView.ENTETE_VOITURE_MARQUE, ConstantesView.ENTETE_VOITURE_MODELE, ConstantesView.ENTETE_VOITURE_COULEUR, ConstantesView.ENTETE_VOITURE_NUM));
            for (Voiture voiture : lstVoitures) {
                afficherVoiture(voiture);
            }
        }
    }

    private static List<Voiture> getLstVoiture() {
        return metier.listerVoiture();
    }

    private static void creerVoiture() {

        try {
            //Demander numéro de série
            String numSerie = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
            //Demander marque
            String marque = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MARQUE);
            //Demander modèle
            String model = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MODEL);
            //Demander saisie couleur
            Couleur couleur = selectionnerCouleur();
            //Ajout de pièce(s)
            boolean ajout = false;
            List<Piece> lstPiece = new ArrayList<>();
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.AJOUT_PIECE);
            do{
                lstPiece.add(ajouterPiece());
                ajout = LectureConsole.lectureBoolean(ConstantesView.AJOUT_PIECE_OUI_NON);
            } while (ajout);
            Voiture voiture = metier.creerVoiture(numSerie, marque, model, couleur, lstPiece);
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.CREATION_REUSSIE);
            afficherVoiture(voiture);
        } catch (BusinessException e) {
            AffichageConsole.afficherErreur(ConstantesView.MSG_AJOUTER_VOITURE_EXCEPTION);
            AffichageConsole.afficherErreur(e.getMessage());
            Throwable ex = e.getCause();
            while(ex != null){
                AffichageConsole.afficherErreur(ex.getMessage());
                ex = ex.getCause();
            }
        }
    }

    private static void afficherVoiture(final Voiture voiture) {
        AffichageConsole.afficherMessageAvecSautLigne(String.format("%-20s | %-20s | %-10s | %-10s", voiture.getMarque(), voiture.getModel(), voiture.getCouleur(), voiture.getNumSerie()));
        AffichageConsole.afficherMessageAvecSautLigne(String.format("%s %-20s | %-20s", ConstantesView.ESPACE, ConstantesView.ENTETE_PIECE_TYPE, ConstantesView.ENTETE_PIECE_NUM));
        for (Piece piece : voiture.getPieces()){
            AffichageConsole.afficherMessageAvecSautLigne(String.format("%s %-20s | %-20s", ConstantesView.ESPACE, piece.getTypePiece().getLibelle(), piece.getNumSerie()));
        }
    }

    private static Piece ajouterPiece() {
        try {
            //Selectionner famille type pièce
            FamilleTypePiece familleTypePiece = selectionnerFamilleTypePiece();
            //Selectionner type pièce
            TypePiece typePiece = selectionnerTypePiece(familleTypePiece);
            //Saisir numéro de série de la pièce
            String numSerie = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
            //Construction pièce
            Piece piece = metier.creerPiece(numSerie, typePiece);
            return piece;
        } catch (BusinessException e) {
            AffichageConsole.afficherErreur(ConstantesView.MSG_AJOUTER_PIECE_EXCEPTION);
            AffichageConsole.afficherErreur(e.getMessage());
            Throwable ex = e.getCause();
            while(ex != null){
                AffichageConsole.afficherErreur(ex.getMessage());
                ex = ex.getCause();
            }
        }
        return null;
    }

    private static TypePiece selectionnerTypePiece(final FamilleTypePiece familleTypePiece) {
        List<TypePiece> types = TypePiece.listerPieceByFamille(familleTypePiece);
        for(int i = 0; i < types.size(); i++){
            AffichageConsole.afficherMessageAvecSautLigne(String.format("%s - %s", i + 1, types.get(i).getLibelle()));
        }
        AffichageConsole.afficherMessageSansSautLigne(ConstantesView.SELECTIONNER_TYPE_PIECE);
        final int choix = LectureConsole.lectureChoixInt(1, TypePiece.values().length);
        return TypePiece.values()[choix - 1];
    }

    private static FamilleTypePiece selectionnerFamilleTypePiece() {
        for(int i = 0; i < FamilleTypePiece.values().length; i++){
            AffichageConsole.afficherMessageAvecSautLigne(String.format("%s - %s", i + 1, FamilleTypePiece.values()[i].getLibelle()));
        }
        AffichageConsole.afficherMessageSansSautLigne(ConstantesView.SELECTIONNER_FAMILLE_TYPE_PIECE);
        final int choix = LectureConsole.lectureChoixInt(1, FamilleTypePiece.values().length);
        return FamilleTypePiece.values()[choix - 1];
    }

    private static Couleur selectionnerCouleur() {
        for(int i = 0; i < Couleur.values().length; i++){
            AffichageConsole.afficherMessageAvecSautLigne(String.format("%s - %s", i + 1, Couleur.values()[i]));
        }
        AffichageConsole.afficherMessageSansSautLigne(ConstantesView.SELECTIONNER_COULEUR);
        final int choix = LectureConsole.lectureChoixInt(1, Couleur.values().length);
        return Couleur.values()[choix - 1];
    }

}
