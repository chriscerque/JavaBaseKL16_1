package net.ent.etrs.voiture.presenter;

import net.ent.etrs.model.entities.exceptions.ConstructionPieceException;
import net.ent.etrs.model.entities.exceptions.NumSerieException;
import net.ent.etrs.model.entities.exceptions.PieceException;
import net.ent.etrs.model.entities.exceptions.TypePieceException;
import net.ent.etrs.model.facades.exceptions.BusinessException;
import net.ent.etrs.view.ConstantesView;
import net.ent.etrs.voiture.commons.AffichageConsole;
import net.ent.etrs.voiture.commons.LectureConsole;
import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.FamilleTypePiece;
import net.ent.etrs.voiture.model.entities.references.TypePiece;
import net.ent.etrs.voiture.model.facades.FacadeMetier;
import net.ent.etrs.voiture.model.facades.FacadeMetierFactory;

import java.util.List;

public class Presenter {

    private static FacadeMetier fm;

    public static void main(String[] args) {
        fm = FacadeMetierFactory.fabriquerFacadeMetier();
        execute();
    }

    private static void execute()  {
        int choix = -1;

        do{
            AffichageConsole.afficherMenuEntoureAvecOptionSortie(List.of(ConstantesView.MENU), "Gestion Voitures");
            choix = LectureConsole.lectureChoixInt(0, List.of(ConstantesView.MENU).size());

            switch(choix){
                case 1:
                    //creer une voiture
                    creerVoiture();
                    break;
                case 2:
                    //Lister les voitures
                    listerVoiture();
                    break;
                case 3:
                    //supprimer une voiture
                    supprimerVoiture();
                    break;
            }
        }while (choix !=0);

    }

    private static void creerVoiture() {
        int numSerie = LectureConsole.lectureEntier(ConstantesView.SAISIR_NUM_SERIE);
        String marque = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MARQUE);
        String model = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MODEL);
        int i = 0;
        for (Couleur c:Couleur.values()){
            i++;
            AffichageConsole.afficherMessageAvecSautLigne(String.format("%s %s",i,c.name()));
        }
        int choixCouleur = LectureConsole.lectureEntier(ConstantesView.SELECTIONNER_COULEUR);
        i=0;
        String couleurChoisie;
        for (Couleur c:Couleur.values()){
           i++;
           if (choixCouleur == i){
               couleurChoisie = c.name();
           }
        }

        choixPiece();
    }

    private static Piece choixPiece() {
        int i=0;
        for (FamilleTypePiece ftp: FamilleTypePiece.values()){
            i++;
            AffichageConsole.afficherMessageAvecSautLigne(String.format("%s %s", i, ftp.getLibelle()));
        }
        AffichageConsole.afficherMessageSansSautLigne("Choisir un type de piece : ");
        int choixPiece = LectureConsole.lectureChoixInt(0, FamilleTypePiece.values().length);
        String typePieceChoisi = null;
        i=0;
        for (FamilleTypePiece ftp: FamilleTypePiece.values()){
            i++;
            if (choixPiece == i){
                typePieceChoisi = ftp.getLibelle();
            }
        }

       return  choixTypePiece(typePieceChoisi);
    }

    private static Piece choixTypePiece(String typePieceChoisi) {
        for (TypePiece tp: TypePiece.values()){
            if (tp.getFamilleTypePiece().getLibelle().equals(typePieceChoisi)){
                String numSerie = LectureConsole.lectureChaineCaracteres("veuillez saisir une numero de serie");
                try {
                  return Presenter.fm.creerPiece(numSerie, tp);
                } catch (BusinessException | DaoException | TypePieceException | NumSerieException |
                         ConstructionPieceException | PieceException e) {
                    AffichageConsole.afficherErreur(e.getMessage());
                }
            }
        }
        return null;
    }

    private static void supprimerVoiture()  {
        if (Presenter.fm.listerVoiture().isEmpty()){
            AffichageConsole.afficherMessageAvecSautLigne("aucune voiture à afficher");
        }else{


        AffichageConsole.afficherMessageAvecSautLigne("Selectionnez une voiture à supprimer");
        if (!Presenter.fm.listerVoiture().isEmpty()) {
            AffichageConsole.afficherMessageAvecSautLigne(String.format("%-5s %-15s %-15s %-15s %-15s", "CHOIX", "MARQUE", "MODELE", "COULEUR", "NUM SERIE"));
            int i = 0;
            for (Voiture v : Presenter.fm.listerVoiture()) {
                i++;
                AffichageConsole.afficherMessageAvecSautLigne(String.format("%-5s %-15s %-15s %-15s %-15s", i, v.getNumSerie(), v.getMarque(), v.getModel(), v.getCouleur()));
            }
        }
        int choix = LectureConsole.lectureChoixInt(0, Presenter.fm.listerVoiture().size());
        int i = 0;
        for (Voiture v : Presenter.fm.listerVoiture()) {
            i++;
            if (i == choix) {
                try {
                    Presenter.fm.supprimerVoiture(v.getNumSerie());
                    AffichageConsole.afficherMessageAvecSautLigne("la voiture a bien été supprimée");
                } catch (BusinessException | DaoException e) {
                    AffichageConsole.afficherErreur(e.getMessage());

                }
            }
        }
    }
    }


        private static void listerVoiture () {
                if (Presenter.fm.listerVoiture().isEmpty()){
                    AffichageConsole.afficherMessageAvecSautLigne("aucune voiture à afficher");
                }else{


                 AffichageConsole.afficherMessageAvecSautLigne("Aucune voiture à afficher");
                AffichageConsole.afficherMessageAvecSautLigne(String.format("%-15s %-15s %-15s %-15s", "MARQUE", "MODELE", "COULEUR", "NUM SERIE"));
                for (Voiture v : Presenter.fm.listerVoiture()) {
                    AffichageConsole.afficherMessageAvecSautLigne(String.format("%-15s %-15s %-15s", v.getMarque(), v.getModel(), v.getNumSerie()));
                    AffichageConsole.afficherMessageAvecSautLigne(String.format("   %-15s %-15s ", "TYPE DE PIECE", "NUM SERIE"));
                    for (Piece p : v.getPieces()) {
                        AffichageConsole.afficherMessageAvecSautLigne(String.format("   %-15s %-15s ", p.getTypePiece(), p.getNumSerie()));
                    }
                }
                }




    }


}