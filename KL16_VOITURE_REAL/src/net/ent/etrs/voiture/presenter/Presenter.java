package net.ent.etrs.voiture.presenter;

import net.ent.etrs.voiture.model.daos.DaoFactory;
import net.ent.etrs.voiture.model.daos.VoitureDao;
import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.entities.EntitiesFactory;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.exceptions.TypePieceException;
import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;
import net.ent.etrs.voiture.presenter.references.ConstantePresenter;
import net.ent.etrs.voiture.utils.AffichageConsole;
import net.ent.etrs.voiture.utils.LectureConsole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Presenter {

    // Constante
    private static VoitureDao metier = DaoFactory.fabriquerDaoVoiture();

    public static void main(String[] args) throws Exception {
        Presenter.initVoiture();
        executer();
    }

    /**
     * Permet d'afficher le menu suivant
     * +-------- Gestion voiture --------+
     * |  1 - Créer une voiture          |
     * |  2 - Lister les voitures        |
     * |  3 - Supprimer une voiture      |
     * |  0 - sortir                     |
     * +---------------------------------+
     * @throws Exception
     */

    private static void executer() throws Exception {
        int choix = 0;
        do {
            //Afficher le menu
            AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantePresenter.MENU), ConstantePresenter.NOM_MENU);
            //Récupérer choix utilisateur
            choix = LectureConsole.lectureChoixInt(0, ConstantePresenter.MENU.length);
            //Traiter le choix de l'utilisateur
            traiterChoix(choix);
        } while (choix != 0);
    }

    /**
     * Permet de traiter les choix du menu afficher.
     * @param choix
     * @throws Exception
     */
    private static void traiterChoix(int choix) throws Exception {
        switch (choix){
            case 1:
                creerVoiture();
                break;
            case 2 :
                listerVoiture();
                break;
            case 3 :
                supprimerVoiture();
                break;
        }
    }

    /**
     * Permet de lister les voitures qui ont étaient crées.
     */
    private static void listerVoiture() throws DaoException {
        final List<Voiture> voitureList = Presenter.metier.readAll();

        if(voitureList.size() != 0){
            AffichageConsole.afficherMessageAvecSautLigne(String.format(ConstantesMetier.AFFICHAGE_INFORMATION_VOITURE));
            for(Voiture v : voitureList){

                AffichageConsole.afficherMessageAvecSautLigne(String.format(ConstantesMetier.AFFICHAGE_LISTE_VOITURE, v.getMarque(),v.getModel(),v.getCouleur(),v.getNumSerie()));
            }
        } else {
            AffichageConsole.afficherErreur(String.format(ConstantesMetier.MSG_AUCUNE_VOITURE_EXCEPTION));
        }
    }

    /**
     * Permet de demander la saisie des informations et permet de créer une voiture.
     */

    private static void creerVoiture() {
        try {
            String numSerie = LectureConsole.lectureChaineCaracteres(ConstantesMetier.MSG_PRESENTER_DEMANDER_SAISIE_NUM_SERIE);
            if(numSerie.isEmpty() || numSerie.isBlank() || numSerie.length() == ConstantesMetier.NUM_SERIE_TAILLE){
                AffichageConsole.afficherErreur(String.format(ConstantesMetier.MSG_NUM_SERIE_NON_RENSEIGNE_EXCEPTION,ConstantesMetier.NUM_SERIE_TAILLE));
                throw new VoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION);
            }
            String marque = LectureConsole.lectureChaineCaracteres(ConstantesMetier.MSG_PRESENTER_DEMANDER_SAISIE_MARQUE);
            if(marque.isEmpty() || marque.isBlank() || marque.length() < ConstantesMetier.MARQUE_TAILLE_MIN && marque.length() > ConstantesMetier.MARQUE_TAILLE_MAX){
                AffichageConsole.afficherErreur(String.format(ConstantesMetier.MSG_VOITURE_MARQUE_NON_RENSEIGNE_EXCEPTION));
                throw new VoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION);
            }
            String model = LectureConsole.lectureChaineCaracteres(ConstantesMetier.MSG_PRESENTER_DEMANDER_SAISIE_MODEL);
            if(model.isEmpty() || model.isBlank() || model.length() < ConstantesMetier.MODEL_TAILLE_MIN && model.length() > ConstantesMetier.MODEL_TAILLE_MAX){
                AffichageConsole.afficherErreur(String.format(ConstantesMetier.MSG_VOITURE_MODEL_NON_RENSEIGNE_EXCEPTION));
                throw new VoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION);
            }
            Couleur c = demanderChoixCouleurUtilisateur();

            if(c.equals(null)) {
                AffichageConsole.afficherErreur(String.format(ConstantesMetier.MSG_VOITURE_COULEUR_NON_RENSEIGNE_EXCEPTION));
                throw new VoitureException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION);
            }

//            TypePiece p = demanderPiece();
            Voiture m = EntitiesFactory.fabriquerVoiture(numSerie,marque,model,c);
            metier.create(m);
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesMetier.MSG_VOITURE_CREATION_SUCCESS);
            AffichageConsole.afficherMessageAvecSautLigne(String.format(ConstantesMetier.AFFICHAGE_LISTE_VOITURE, m.getMarque(),m.getModel(),m.getCouleur(),m.getNumSerie()));
        } catch (VoitureException | DaoException e){
            AffichageConsole.afficherErreur(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION);
        }

    }

    /**
     * Permet de demander à l'utilisateur les pieces à ajoutée.
     * @return
     * @throws TypePieceException
     */
    private static TypePiece demanderPiece() throws TypePieceException {
        List<String> lstLibellePiece = TypePiece.getLstLibelleCouleur();
        AffichageConsole.afficherMenuSimple(lstLibellePiece);
        //Demander choix de la piece
        int choixCouleur = LectureConsole.lectureChoixInt(1, lstLibellePiece.size());
        String libelleCouleur = lstLibellePiece.get(choixCouleur - 1);
        return TypePiece.getByLibelle(libelleCouleur);
    }

    /**
     * Affiche la liste des couleurs disponible dans l'enumeration COULEUR.
     * @return
     * @throws VoitureException
     */

    public static Couleur demanderChoixCouleurUtilisateur() throws  VoitureException{
        List<String> lstLibelleCouleur = Couleur.getLstLibelleCouleur();

        AffichageConsole.afficherMenuSimple(lstLibelleCouleur);

        int choixCouleur = LectureConsole.lectureChoixInt(1, lstLibelleCouleur.size());
        //Je récupère le libellé dans la liste en fonction du choix de l'utilisateur
        String libelleCouleur = lstLibelleCouleur.get(choixCouleur - 1);
        return Couleur.getCouleurByLibelle(libelleCouleur);
    }

    /**
     * Renvoie une liste des voitures crées et suppirime une voiture selectionner.
     */

    private static void supprimerVoiture() throws DaoException {
        final List<Voiture> voitureList = Presenter.metier.readAll();
        final List<String> menu = new ArrayList<>();
        int index;
        for (Voiture v : voitureList) {
            menu.add(v.getNumSerie() + ConstantesMetier.MSG_PIPE + v.getMarque());
        }
        AffichageConsole.afficherMenuSimple(menu);
        index = LectureConsole.lectureChoixInt(1, menu.size());
        String voitureSupp = voitureList.get(index - 1).getNumSerie();
        Presenter.metier.delete(voitureSupp);
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesMetier.MSG_VOITURE_SUPPRESSION_SUCESS);
    }

    /**
     * Permet d'initialiser des voitures au démarrage de l'application.
     * @throws DaoException
     */
    private static void initVoiture() throws DaoException {
        Voiture ds3 = EntitiesFactory.fabriquerVoiture("01247","Citroen","ds3", Couleur.NOIR);
        Voiture peugeot = EntitiesFactory.fabriquerVoiture("58741","Peugeot","207", Couleur.JAUNE);
        metier.create(ds3);
        metier.create(peugeot);

    }


}
