package net.ent.etrs.gestionVoiture.view;

public final class ConstantesView {

    public static final String MENU_NOM = "Gestion voitures";
    public static final String[] MENU = {
            "Créer une voiture",
            "Lister les voitures",
            "Supprimer une voiture"
    };

    public static final String MSG_INITIALISATION_PIECES_ERROR = "Une erreur est survenue lors de l'initialisation des pièces.";
    public static final String SAISIR_NUM_SERIE = "Veuillez saisir le numéro de série :";
    public static final String SAISIR_MARQUE = "Veuillez saisir la marque :";
    public static final String SAISIR_MODEL = "Veuillez saisir le model :";
    public static final String SELECTIONNER_COULEUR = "Veuillez sélectionner une couleur";
    public static final String SELECTIONNER_FAMILLE_TYPE_PIECE = "Veuillez sélectionner un type de pièce : ";
    public static final String SELECTIONNER_TYPE_PIECE = "Veuillez sélectionner un type de pièce : ";
    public static final String MSG_AJOUTER_PIECE_EXCEPTION = "Impossible d'ajouter une pièce.";
    public static final String SUPPRESSION_REUSSIE = "la voiture a bien été supprimée.";
    public static final String SUPPRESSION_ECHEC = "la voiture a bien été supprimée.";
    public static final String CREATION_REUSSIE = "La voiture a bien été créée.";
    public static final String LISTER_VOITURE_VIDE = "Aucune voiture à afficher.";
    public static final String LISTER_VOITURE_SUPPRESSION_VIDE = "Aucune voiture à supprimer.";
    public static final String AJOUT_PIECE_OUI_NON = "Voulez-vous ajouter une autre pièce ?";
    public static final String LISTER_PIECE_VIDE = "Aucune pièce à afficher.";
    public static final String MSG_RECOMMENCER = "Voulez-vous recommencer? ";
    public static final String LISTER_VOITURE_PATTERN = "%15s| %15s| %15s | %15s\n";
    public static final String SUPPRESSION_VOITURE_PATTERN = "%11s| %15s| %15s | %15s\n";
    public static final String LISTER_VOITURE_PIECE_PATTERN = "\t%15s| %15s\n";
    public static final String LISTER_VOITURE_MARQUE = "MARQUE";
    public static final String LISTER_VOITURE_MODELE = "MODELE";
    public static final String LISTER_VOITURE_COULEUR = "COULEUR";
    public static final String LISTER_VOITURE_NUM_SERIE = "NUM SERIE";
    public static final String LISTER_PIECE_TYPE = "TYPE DE PIECE";


    private ConstantesView() {
    }

}
