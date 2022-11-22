package net.ent.etrs.presenter;

import net.ent.etrs.commons.utils.AffichageConsole;
import net.ent.etrs.commons.utils.LectureConsole;
import net.ent.etrs.model.entities.Piece;
import net.ent.etrs.model.entities.Voiture;
import net.ent.etrs.model.entities.references.Couleur;
import net.ent.etrs.model.entities.references.FamilleTypePiece;
import net.ent.etrs.model.entities.references.TypePiece;
import net.ent.etrs.model.facades.FacadeMetier;
import net.ent.etrs.model.facades.FacadeMetierFactory;
import net.ent.etrs.model.facades.exceptions.BusinessException;
import net.ent.etrs.view.ConstantesView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Presenter {
    private static FacadeMetier facadeMetier;

    public static void main(String[] args) {
        facadeMetier = FacadeMetierFactory.fabriquerFacadeMetier();
        int choix = 0;
        do {
            //afficher le menu
            choix = afficherMenu();
            //traiter les options du menu
            traiterOption(choix);
        } while (choix != 0);
    }

    private static void traiterOption(final int choix) {
        switch (choix) {
            case 1: //créer une voiture
                creerVoiture();
                break;
            case 2: //lister les voitures
                listerVoitures();
                break;
            case 3: //supprimer une voiture
                supprimerVoiture();
                break;
            default:
                break;
        }
    }

    private static void creerVoiture() {
        String numSerieChoisi = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
        String marqueChoisie = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MARQUE);
        String modeleChoisi = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MODEL);
        Couleur couleurChoisie = selectionnerCouleur();
        List<Piece> piecesChoisies = new ArrayList<>();
        boolean boucle = true;
        try {
        do {
            ajouterPiece();
            boucle = LectureConsole.lectureBoolean(ConstantesView.AJOUT_PIECE_OUI_NON);
        } while (boucle != false);

            Voiture v = facadeMetier.creerVoiture(numSerieChoisi, marqueChoisie, modeleChoisi, couleurChoisie, piecesChoisies);
            afficherVoiture(v);
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.CREATION_REUSSIE);
        } catch (BusinessException e) {
            AffichageConsole.afficherMessageAvecSautLigne(e.getMessage());
        }
    }

    private static Piece ajouterPiece() throws BusinessException {
        FamilleTypePiece familleTypePieceChoisi = selectionnerFammilleTypePiece();
        Piece pieceChoisi = selectionnerTypepiece(familleTypePieceChoisi);
        return pieceChoisi;
    }

    private static Piece selectionnerTypepiece(FamilleTypePiece familleTypePiece) throws BusinessException {
        List<TypePiece> TypePieces = TypePiece.listerPieceByFamille(familleTypePiece);
        List<String> libelleTypePiece = new ArrayList<>();
        for (TypePiece c: TypePieces) {
            libelleTypePiece.add(c.toString());
        }
        AffichageConsole.afficherMenuSimple(libelleTypePiece);
        int choix = LectureConsole.lectureChoixInt(1, TypePieces.size() + 1);

        String numSerieChoisi = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
        Piece piece = facadeMetier.creerPiece(numSerieChoisi,TypePieces.get(choix - 1));
        return piece;
    }

    private static FamilleTypePiece selectionnerFammilleTypePiece() {
        List<FamilleTypePiece> familleTypePieces = Arrays.asList(FamilleTypePiece.values());
        List<String> libellefamilleTypePiece = new ArrayList<>();
        for (FamilleTypePiece c: familleTypePieces) {
            libellefamilleTypePiece.add(c.toString());
        }
        AffichageConsole.afficherMenuSimple(libellefamilleTypePiece);

        int choix = LectureConsole.lectureChoixInt(1, familleTypePieces.size() + 1);
        return familleTypePieces.get(choix - 1);
    }

    private static Couleur selectionnerCouleur() {
        List<Couleur> couleurs = Arrays.asList(Couleur.values());
        List<String> libellecouleurs = new ArrayList<>();
        for (Couleur c: couleurs) {
            libellecouleurs.add(c.toString());
        }
        AffichageConsole.afficherMenuSimple(libellecouleurs);

        int choix = LectureConsole.lectureChoixInt(1, couleurs.size() + 1);
        return couleurs.get(choix - 1);
    }

    private static void supprimerVoiture() {
        try {
            if (facadeMetier.listerVoiture().size() == 0) {
                throw new Exception((ConstantesView.LISTER_VOITURE_SUPPRESSION_VIDE));
            }
            facadeMetier.supprimerVoiture((selectionnerVoiture(facadeMetier.listerVoiture())));
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.SUPPRESSION_REUSSIE);
        } catch (Exception e) {
            AffichageConsole.afficherMessageAvecSautLigne(e.getMessage());
        }
    }

    private static String selectionnerVoiture(List<Voiture> listerVoiture) {
        List<String> listvoit = new ArrayList<>();

        for (Voiture v: listerVoiture) {
            listvoit.add(v.getMarque() + "  |" + v.getModele() + "  |" + v.getCouleur() + " |" + v.getNumSerie());
        }
        AffichageConsole.afficherMessageAvecSautLigne("TYPE DE PIECE   |NUM SERIE");
        int choixVoiture = getChoixUtilisateur(listvoit, 1);
        return listerVoiture.get(choixVoiture - 1).getNumSerie();
    }

    private static int getChoixUtilisateur(final List<String> strings, final int indiceDepart) {
        AffichageConsole.afficherMenuSimple(strings);
        return LectureConsole.lectureChoixInt(indiceDepart, strings.size());
    }

    private static void listerVoitures() {
        if (facadeMetier.listerVoiture().size() == 0) {
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesView.LISTER_VOITURE_VIDE);
        }
        for (Voiture v : facadeMetier.listerVoiture()) {
            afficherVoiture(v);
        }
    }

    private static void afficherVoiture(Voiture v) {
        AffichageConsole.afficherMessageAvecSautLigne("MARQUE    |MODELE    |COULEUR    |NUM SERIE");
        AffichageConsole.afficherMessageAvecSautLigne(v.getMarque() + "    |" + v.getModele() + "    |" + v.getCouleur() + "    |" + v.getNumSerie());
        AffichageConsole.afficherMessageAvecSautLigne("    TYPE DE PIECE    |NUM SERIE");
        for (Piece p: v.getPieces()) {
            affcherPiece(p);
        }
    }

    private static void affcherPiece(Piece p) {
        AffichageConsole.afficherMessageAvecSautLigne("    " + p.getTypePiece().getLibelle() + "    |" + p.getNumSerie());
    }

    /**
     * affiche un menu à partir de la constante MENU
     * @return un choix sous forme de int
     */
    private static int afficherMenu() {
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesView.MENU), "Gestion voitures");
        return LectureConsole.lectureChoixInt(0, ConstantesView.MENU.length);
    }
}
