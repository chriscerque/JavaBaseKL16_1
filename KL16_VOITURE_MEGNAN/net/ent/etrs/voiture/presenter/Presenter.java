package net.ent.etrs.voiture.voiture.presenter;

import net.ent.etrs.voiture.voiture.commons.utils.LectureConsole;
import net.ent.etrs.voiture.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.voiture.model.entities.references.TypePiece;
import net.ent.etrs.voiture.voiture.model.facades.FacadeMetier;
import net.ent.etrs.voiture.voiture.view.ConstantesView;

import java.util.List;

public class Presenter {

    private FacadeMetier metier;

    //Main
    public static void main(String[] args) {
        executer();
    }

    //Constucteur
    public Presenter(FacadeMetier metier) {
        this.metier = metier;
    }

    //Autres methodes

    /**
     * choix de l'utilisateur pour le menu
     */
    public static void executer() {
        int choix = 0;
        do {
            choix = LectureConsole.lectureChoixInt(0, ConstantesView.MENU.length);
            traiterMenu(choix);
        } while (choix != 0);
    }

    /**
     * Fonstions du menu
     * @param choix
     */
    private static void traiterMenu(int choix){
        switch (choix) {
            case 1:
                //Créer une voiture
                creerVoiture();
                break;
            case 2:
                //Lister les voitures
                List<Voiture> voitures;
            case 3:
                //Supprimer une voiture
                supprimerVoiture();
            case 0:
                //Afficher message sortie
                System.out.println("Au revoir!");
                break;
        }
    }

    /**
     * Creation de la voiture
     */
    private static void creerVoiture(){
        //demande saisie n° serie
        String numSerie = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
        //demande sasie marque
        String marque = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MARQUE);
        //demande saisie model
        String model = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_MODEL);
        //demande saisie couleur
        Couleur couleur = Couleur.valueOf(LectureConsole.lectureChaineCaracteres(ConstantesView.SELECTIONNER_COULEUR));
        //demande saisie piece
        TypePiece typePiece = TypePiece.valueOf(LectureConsole.lectureChaineCaracteres(ConstantesView.SELECTIONNER_TYPE_PIECE));
    }

    /**
     * Suppression de la voiture
     */
    private static void supprimerVoiture(){
        String numSerie = LectureConsole.lectureChaineCaracteres(ConstantesView.SAISIR_NUM_SERIE);
    }
}
