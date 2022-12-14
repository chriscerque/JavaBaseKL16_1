package net.ent.etrs.voiture.presenter;

import net.ent.etrs.commons.utils.AffichageConsole;
import net.ent.etrs.commons.utils.LectureConsole;
import net.ent.etrs.voiture.model.entities.AbstractEntity;
import net.ent.etrs.voiture.model.entities.EntitiesFactory;
import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.exceptions.ControleRoueVoitureException;
import net.ent.etrs.voiture.model.entities.exceptions.FamilleTypePieceException;
import net.ent.etrs.voiture.model.entities.exceptions.TypePieceException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.FamilleTypePiece;
import net.ent.etrs.voiture.model.entities.references.TypePiece;
import net.ent.etrs.voiture.model.facades.FacadeMetier;
import net.ent.etrs.voiture.model.facades.FacadeMetierFactory;
import net.ent.etrs.voiture.model.facades.exceptions.BusinessException;
import net.ent.etrs.voiture.presenter.exceptions.PresenterException;
import net.ent.etrs.voiture.view.ConstantesView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Presenter {

    private static FacadeMetier metier;


    public static void main(String[] args) {

        metier = FacadeMetierFactory.fabriquerFacadeMetier();

        int choix = -1;

        //Afficher le menu
        do {
            AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesView.MENU), "Gestion voitures");
            choix = LectureConsole.lectureChoixInt(0, ConstantesView.MENU.length);
            traiterChoix(choix);
        }while (choix != 0);

    }



    private static void traiterChoix(int choix) {
        switch (choix){
            case 1 : creerVoiture();
                break;
            case 2 : listerVoiture();
                break;
            case 3: supprimerVoiture();
                break;

        }
    }

    /**
     * Demande la saisie ?? l'utilisateur des diff??rentes donn??es pour la cr??ation d'une {@link Voiture voiture}
     */
    private static void creerVoiture() {
        String numSerie = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
        String marque = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MARQUE);
        String model = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MODEL);
        Couleur couleur = selectionnerCouleur();
        List<Piece> lstPiece = new ArrayList<>();
        validerCreerVoiture(numSerie, marque, model, couleur, lstPiece);

    }

    /**
     * Valide la cr??ation d'une {@link Voiture voiture}.
     * Propose d'ajouter plusieurs {@link Piece pi??ces} ?? la suite.
     * Si une {@link ControleRoueVoitureException} lors de la cr??ation de la voiture est l??v??e, l'utilisateur doit ajouter une nouvelle pi??ce.
     * @param numSerie {@link AbstractEntity num??ro de s??rie} de la {@link Voiture voiture}.
     * @param marque {@link Voiture marque}
     * @param model {@link Voiture mod??le}
     * @param couleur {@link Couleur couleur}
     * @param lstPiece liste des {@link Piece pi??ces} pour la construction de la voiture.
     */
    private static void validerCreerVoiture(String numSerie, String marque, String model, Couleur couleur, List<Piece> lstPiece) {
        try {
            do {
                Piece piece = ajouterPiece();
                lstPiece.add(piece);
            }while (LectureConsole.lectureBoolean(ConstantesView.AJOUT_PIECE_OUI_NON));
            Voiture voiture = metier.creerVoiture(numSerie, marque, model, couleur, lstPiece);
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.CREATION_REUSSIE);
            afficherVoiture(voiture);
        }catch (BusinessException | PresenterException e){
            if (e.getCause() instanceof ControleRoueVoitureException){
                AffichageConsole.afficherErreur(e.getCause().getMessage());
                validerCreerVoiture(numSerie, marque, model, couleur, lstPiece);
            }else {
                AffichageConsole.afficherException(e);
            }
        }
    }

    /**
     * Demande la s??lection d'une {@link FamilleTypePiece famille de type de piece} puis d'un {@link TypePiece type de piece} et la saisie d'une {@link AbstractEntity num??ro de s??rie} afin de cr??er une {@link Piece pi??ce}.
     * @return la {@link Piece pi??ce} cr????e.
     * @throws PresenterException si l'une des contraintes de cr??ation d'une {@link EntitiesFactory#fabriquerPiece(String, TypePiece)  pi??ce} n'est pas respect??e.
     */
    private static Piece ajouterPiece() throws PresenterException {
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesMetier.AJOUTER_PIECE);
        try {
            FamilleTypePiece familleTypePiece = selectionnerFamilleTypePiece();
            TypePiece typePiece = null;
            try {
                typePiece = selectionnerTypePiece(familleTypePiece);
                String numSerie = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
                try {
                    return metier.creerPiece(numSerie, typePiece);
                } catch (BusinessException e) {
                    e.printStackTrace();
                    AffichageConsole.afficherException(e);
                }
            } catch (TypePieceException e) {
                e.printStackTrace();
                AffichageConsole.afficherException(e);
            }


        } catch (FamilleTypePieceException e) {
            e.printStackTrace();
            AffichageConsole.afficherException(e);
        }
        throw new PresenterException(ConstantesView.MSG_AJOUTER_PIECE_EXCEPTION);
    }

    /**
     * Affiche et demande la s??lection d'un {@link TypePiece type de piece}.
     * @return la {@link TypePiece type de piece} s??lectionn??e.
     */
    private static TypePiece selectionnerTypePiece(FamilleTypePiece familleTypePiece) throws TypePieceException {
        List<String> lstTypePieceAffichage = new ArrayList<>();
        for (TypePiece tp : TypePiece.listerPieceByFamille(familleTypePiece)){
            lstTypePieceAffichage.add(tp.getLibelle());
        }
        AffichageConsole.afficherMenuSimple(lstTypePieceAffichage);
        int choix = LectureConsole.lectureChoixInt(1, lstTypePieceAffichage.size(),ConstantesView.SELECTIONNER_TYPE_PIECE);
        return TypePiece.getByLibelle(lstTypePieceAffichage.get(choix-1));
    }

    /**
     * Affiche et demande la s??lection d'une {@link FamilleTypePiece famille de type de piece}.
     * @return la {@link FamilleTypePiece famille de type de piece} s??lectionn??e.
     */
    private static FamilleTypePiece selectionnerFamilleTypePiece() throws FamilleTypePieceException {
        List<String> lstFamilleTypePieceAffichage = new ArrayList<>();
        for (FamilleTypePiece f : FamilleTypePiece.values()){
            lstFamilleTypePieceAffichage.add(f.getLibelle());
        }
        AffichageConsole.afficherMenuSimple(lstFamilleTypePieceAffichage);
        int choix = LectureConsole.lectureChoixInt(1, lstFamilleTypePieceAffichage.size(), ConstantesView.SELECTIONNER_FAMILLE_TYPE_PIECE);
        return FamilleTypePiece.getByLibelle(lstFamilleTypePieceAffichage.get(choix-1));
    }

    /**
     * Affiche et demande la s??lection d'une {@link Couleur couleur}.
     * @return la {@link Couleur couleur} s??lectionn??e.
     */
    private static Couleur selectionnerCouleur() {
        List<String> lstCouleurAffichage = new ArrayList<>();
        for (Couleur c : Couleur.values()){
            lstCouleurAffichage.add(c.name());
        }
        AffichageConsole.afficherMenuSimple(lstCouleurAffichage);
        return Couleur.valueOf(lstCouleurAffichage.get(LectureConsole.lectureChoixInt(1, lstCouleurAffichage.size(), ConstantesView.SELECTIONNER_COULEUR)-1));
    }

    /**
     * Liste les {@link Voiture voitures}.
     */
    private static void listerVoiture() {
        List<Voiture> lstVoiture = getLstVoiture();
        if (lstVoiture.size() != 0){
            AffichageConsole.afficherMessageAvecSautLigne(String.format("%-20s | %-20s | %-15s | %-5s", "MARQUE", "MODELE", "COULEUR", "NUM SERIE"));
            for (Voiture v : lstVoiture){
                afficherVoiture(v);
            }
        }else {
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.LISTER_VOITURE_VIDE);
        }

    }

    /**
     * Affiche une {@link Voiture voiture} avec ses {@link Piece pi??ces}.
     * @param v {@link Voiture voiture} ?? afficher.
     */
    private static void afficherVoiture(Voiture v) {
        AffichageConsole.afficherMessageAvecSautLigne(String.format("%-20s | %-20s | %-15s | %s", v.getMarque(), v.getModel(), v.getCouleur(), v.getNumSerie()));
        if (v.getPieces().size() != 0){
            AffichageConsole.afficherMessageAvecSautLigne(String.format("\t%-20s | %-5s", "TYPE DE PIECE", "NUM SERIE"));
            for (Piece p : v.getPieces()){
                afficherPiece(p);
            }
        }else{
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.LISTER_PIECE_VIDE);
        }

    }

    /**
     * Affiche une {@link Piece pi??ce}.
     * @param p {@link Piece pi??ce} ?? afficher.
     */
    private static void afficherPiece(Piece p) {
        AffichageConsole.afficherMessageAvecSautLigne(String.format("\t%-40s | %-5s", p.getTypePiece().getLibelle(), p.getNumSerie()));
    }

    /**
     * Recup??re la liste des {@link Voiture voitures} depuis le m??tier.
     * @return la liste des {@link Voiture voitures}.
     */
    private static List<Voiture> getLstVoiture() {
        return metier.listerVoiture();
    }

    /**
     * Demande ?? l'utilisateur de s??lectionner une {@link Voiture voiture} pour sa suppression.
     */
    private static void supprimerVoiture() {
        try {
            List<Voiture> lstVoiture = getLstVoiture();
            if(lstVoiture.size() != 0){
                metier.supprimerVoiture(selectionnerVoiture(lstVoiture));
                AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SUPPRESSION_REUSSIE);
            }else {
                AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.LISTER_VOITURE_SUPPRESSION_VIDE);
            }

        } catch (BusinessException e) {
            AffichageConsole.afficherException(e);
        }
    }

    /**
     * Demande ?? l'utilisateur de s??lectionner une {@link Voiture voiture}.
     * @param lstVoiture liste des {@link Voiture voitures} ?? afficher.
     * @return numSerie {@link AbstractEntity num??ro de s??rie} de la {@link Voiture voiture} s??lectionn??e.
     */
    private static String selectionnerVoiture(List<Voiture> lstVoiture) {


        List<String> lstVoitureAffichage = new ArrayList<>();
        for (Voiture v : lstVoiture){
            lstVoitureAffichage.add(String.format("%-20s | %-20s | %-15s | %s", v.getMarque(), v.getModel(), v.getCouleur(), v.getNumSerie()));
        }
        AffichageConsole.afficherMessageAvecSautLigne(String.format("%-20s | %-20s | %-15s | %-5s", "MARQUE", "MODELE", "COULEUR", "NUM SERIE"));
        AffichageConsole.afficherMenuSimpleAvecOptionSortie(lstVoitureAffichage,"retour");
        return lstVoiture.get(LectureConsole.lectureChoixInt(0, lstVoitureAffichage.size())-1).getNumSerie();
    }


}
