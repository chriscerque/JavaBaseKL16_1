package net.ent.etrs.voiture.presenter;

import net.ent.etrs.voiture.commons.utils.AffichageConsole;
import net.ent.etrs.voiture.commons.utils.LectureConsole;
import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.exceptions.ControleRoueVoitureException;
import net.ent.etrs.voiture.model.entities.exceptions.TypePieceException;
import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.FamilleTypePiece;
import net.ent.etrs.voiture.model.entities.references.TypePiece;
import net.ent.etrs.voiture.model.facades.FacadeMetier;
import net.ent.etrs.voiture.model.facades.FacadeMetierFactory;
import net.ent.etrs.voiture.model.facades.exceptions.BusinessException;
import net.ent.etrs.voiture.view.ConstantesView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Presenter {

    private static FacadeMetier metier;


    /**
     * MANQUE DE TEMPS POUR FINIR LA SAISIE DES 4 ROUE.
     */


    /**
     * Lance le programme avec quelques parametres.
     *
     * @param args
     */
    public static void main(String[] args) {
        metier = FacadeMetierFactory.fabriquerFacadeMetier();
        metier.initialerApp();
        executer();
    }

    /**
     * Lance le menu.
     */

    private static void executer() {
        int choix = 0;

        do {
            AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesView.MENU), ConstantesView.MENU_NAME);
            choix = LectureConsole.lectureChoixInt(0, ConstantesView.MENU.length);
            Presenter.traiterChoix(choix);
        } while (choix != 0);
    }

    /**
     * Action selon le choix de lutilisateur.
     *
     * @param choix
     */
    private static void traiterChoix(final int choix) {
        switch (choix) {
            case 0: //Message de fin
                AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SORTIE_TRAITERCHOIX);
            case 1: //Créer une voiture
                creerVoiture();
                break;
            case 2: //Lister les voitures
                listerVoiture();
                break;
            case 3: //Supprimer une voiture
                supprimerVoiture();
                break;
            default:
                AffichageConsole.afficherErreur(ConstantesView.ERREUR_TRAITERCHOIX);
                break;
        }
    }

    /**
     * Fonctione qui creer la voiture.
     */
    private static void creerVoiture() {

        String numSerie = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
        String marque = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MARQUE);
        String model = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MODEL);

        Couleur couleur = choisirCouleur();

        List<Piece> pieces = saisirPiece();

        try {
            metier.creerVoiture(numSerie, marque, model, couleur, pieces);
        } catch (BusinessException e) {
            boolean roueManquante = false;
            StringBuilder sb = new StringBuilder();
            sb.append(e.getMessage());
            sb.append(System.lineSeparator());
            Throwable ex = e.getCause();

            while (ex != null) {
                sb.append(String.format("\t"));
                sb.append(ex.getMessage());
                sb.append(System.lineSeparator());
                if (ex.getCause() instanceof ControleRoueVoitureException) {
                    roueManquante = true;
                }
                ex = ex.getCause();
            }


            if (roueManquante) {
                AffichageConsole.afficherMessageAvecSautLigne("roues manquantes");
                try {
                    pieces.add(creerRoues(pieces));
                } catch (BusinessException exc) {
                    throw new RuntimeException(exc);
                }
            } else {
                AffichageConsole.afficherErreur(sb.toString());
            }
        }
    }

    private static TypePiece saisirRoueManquantes(final List<Piece> pieces) {

        try {
            List<String> menu = new ArrayList<>();
            List<TypePiece> temps = new ArrayList<>();
            for (TypePiece typePiece : TypePiece.listerPieceByFamille(FamilleTypePiece.ROUE)) {
                if (!pieces.contains(typePiece)) {
                    menu.add(typePiece.getLibelle());
                    temps.add(typePiece);
                }
            }

            AffichageConsole.afficherMenuSimple(menu);
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SELECTIONNER_TYPE_PIECE);
            int choix = LectureConsole.lectureChoixInt(1, temps.size());
            return temps.get(choix - 1);


        } catch (TypePieceException e) {
            throw new RuntimeException(e);
        }

    }

    private static Piece creerRoues(final List<Piece> pieces) throws BusinessException {

        FamilleTypePiece familleTypePiece = FamilleTypePiece.ROUE;
        TypePiece typePiece = saisirRoueManquantes(pieces);
        String numSeriePiece = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
        return metier.creerPiece(numSeriePiece, typePiece);

    }

    /**
     * Permet le choix parmis les pieces existantes.
     *
     * @return
     */
    private static List<Piece> saisirPiece() {
        String choix = "non";
        List<Piece> pieces = new ArrayList<>();

        do {
            if (pieces.size() == 0) {
                try {
                    pieces.add(creerPiece());
                } catch (BusinessException e) {
                    AffichageConsole.afficherErreur(e.getMessage());
                }
                choix = LectureConsole.lectureChaineCaracteres(ConstantesView.AJOUT_PIECE_OUI_NON);
            } else {
                if (choix.contains("oui")) {
                    try {
                        pieces.add(creerPiece());
                    } catch (BusinessException e) {
                        AffichageConsole.afficherErreur(e.getMessage());
                    }
                    choix = LectureConsole.lectureChaineCaracteres(ConstantesView.AJOUT_PIECE_OUI_NON);
                }
            }
        } while (choix.contains("oui"));

        return pieces;
    }

    /**
     * Creation dune piece.
     *
     * @return
     * @throws BusinessException
     */
    private static Piece creerPiece() throws BusinessException {

        FamilleTypePiece familleTypePiece = choisirFamillePiece();
        TypePiece typePiece = choisirPlacement(familleTypePiece);
        String numSeriePiece = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
        return metier.creerPiece(numSeriePiece, typePiece);

    }

    /**
     * Choisir le placement de la piece.
     *
     * @param familleTypePiece
     * @return
     */
    private static TypePiece choisirPlacement(final FamilleTypePiece familleTypePiece) {
        try {

            List<String> typePieces = new ArrayList<>();
            for (TypePiece typePiece : TypePiece.listerPieceByFamille(familleTypePiece)) {
                typePieces.add(typePiece.getLibelle());
            }
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.AJOUTER_TYPE_PIECE);
            AffichageConsole.afficherMenuSimple(typePieces);
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SELECTIONNER_TYPE_PIECE);
            int choix = LectureConsole.lectureChoixInt(1, typePieces.size());
            TypePiece typePiece = TypePiece.listerPieceByFamille(familleTypePiece).get(choix - 1);
            return typePiece;
        } catch (TypePieceException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Choisir la famille de la piece.
     *
     * @return
     */
    private static FamilleTypePiece choisirFamillePiece() {

        List<String> famillePieces = new ArrayList<>();
        for (FamilleTypePiece famillePieceTemps : FamilleTypePiece.values()) {
            famillePieces.add(famillePieceTemps.getLibelle());
        }
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.AJOUTER_FAMILLE_TYPE_PIECE);
        AffichageConsole.afficherMenuSimple(famillePieces);
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SELECTIONNER_FAMILLE_TYPE_PIECE);
        int choix = LectureConsole.lectureChoixInt(1, famillePieces.size());
        FamilleTypePiece familleTypePiece = FamilleTypePiece.values()[choix - 1];
        return familleTypePiece;

    }

    /**
     * Choisir une couleur parmis la liste.
     *
     * @return
     */
    private static Couleur choisirCouleur() {

        List<String> couleurs = new ArrayList<>();
        for (Couleur couleur : Couleur.values()) {
            couleurs.add(couleur.name());
        }
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.AJOUTER_COULEUR);
        AffichageConsole.afficherMenuSimple(couleurs);
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SELECTIONNER_COULEUR);
        int choix = LectureConsole.lectureChoixInt(1, couleurs.size());
        Couleur couleurChoix = Couleur.values()[choix - 1];
        return couleurChoix;

    }

    /**
     * Supprime une voiture dans la liste.
     */
    private static void supprimerVoiture() {
        if (metier.listerVoiture().size() == 0) {
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.LISTER_VOITURE_SUPPRESSION_VIDE);
        }


        List<String> voitures = new ArrayList<>();
        List<String> menu = new ArrayList<>();
        for (Voiture voiture : metier.listerVoiture()) {
            voitures.add(voiture.getNumSerie());
            try {
                AffichageConsole.afficherMessageAvecSautLigne(afficherSupp(voiture).toString());
            } catch (VoitureException e) {
                AffichageConsole.afficherErreur(e.getMessage());
            }
        }
        AffichageConsole.afficherMenuSimple(voitures);
        int choix = LectureConsole.lectureChoixInt(1, voitures.size());
        try {
            metier.supprimerVoiture(voitures.get(choix - 1));
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SUPPRESSION_REUSSIE);
        } catch (BusinessException | DaoException e) {
            AffichageConsole.afficherErreur(e.getMessage());


        }
    }

    /**
     * Affiche propre pour la suppression.
     *
     * @param voiture
     * @return
     * @throws VoitureException
     */
    private static StringBuilder afficherSupp(final Voiture voiture) throws VoitureException {
        StringBuilder sb = new StringBuilder();
        sb.append("MARQUE").append("       |");
        sb.append("MODELE").append("       |");
        sb.append("COULEUR").append("       |");
        sb.append("NUM SERIE").append(System.lineSeparator());
        sb.append(voiture.getMarque()).append("        |");
        sb.append(voiture.getModel()).append("       |");
        sb.append(voiture.getCouleur()).append("       |");
        sb.append(voiture.getNumSerie()).append(System.lineSeparator());
        return sb;
    }

    /**
     * Affichage propre pour lutilisateur.
     */
    private static void listerVoiture() {
        if (metier.listerVoiture().size() == 0) {
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.LISTER_VOITURE_VIDE);
        }
        for (Voiture voiture : metier.listerVoiture()) {
            //AffichageConsole.afficherMessageAvecSautLigne(voiture.getNumSerie() + " " + voiture.getMarque() + " " + voiture.getCouleur());
            try {
                AffichageConsole.afficherMessageAvecSautLigne(afficher(voiture).toString());
            } catch (VoitureException e) {
                AffichageConsole.afficherErreur("Impossible a lister");
            }
        }
    }

    /**
     * Creation du "pattern" de laffichage.
     *
     * @param voiture
     * @return
     * @throws VoitureException
     */
    private static StringBuilder afficher(final Voiture voiture) throws VoitureException {
        StringBuilder sb = new StringBuilder();
        sb.append("MARQUE").append("       |");
        sb.append("MODELE").append("       |");
        sb.append("COULEUR").append("       |");
        sb.append("NUM SERIE").append(System.lineSeparator());
        sb.append(voiture.getMarque()).append("        |");
        sb.append(voiture.getModel()).append("       |");
        sb.append(voiture.getCouleur()).append("       |");
        sb.append(voiture.getNumSerie()).append(System.lineSeparator());
        sb.append("     TYPE DE PIECE").append("       |");
        sb.append("NUM SERIE").append(System.lineSeparator());
        for (Piece typePiece : voiture.getPieces()) {
            sb.append("     " + typePiece.getTypePiece()).append("       |").append(voiture.getNumSerie()).append(System.lineSeparator());

        }

        //.append(String.format("%s %s", numSerie, dateGarantie)).append(System.lineSeparator());

        return sb;
    }
}
