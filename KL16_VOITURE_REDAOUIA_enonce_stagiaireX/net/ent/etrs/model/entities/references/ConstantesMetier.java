package net.ent.etrs.model.entities.references;

public final class ConstantesMetier {
    public static final String MSG_NUM_SERIE_NON_RENSEIGNE_EXCEPTION = "Le numéro de série doit être renseigné.";
    public static final String MSG_PIECE_TYPE_PIECE_NON_RENSEIGNE_EXCEPTION = "Le type de pièce doit être renseigné.";
    public static final String MSG_VOITURE_MARQUE_NON_RENSEIGNE_EXCEPTION = "La marque de la voiture doit être renseigne.";
    public static final String MSG_VOITURE_MODEL_NON_RENSEIGNE_EXCEPTION = "Le modèle de la voiture doit être renseign.";
    public static final String MSG_VOITURE_PIECE_NON_RENSEIGNE_EXCEPTION = "La pièce doit être renseigne.";
    public static final String MSG_VOITURE_PIECE_EXISTE_EXCEPTION = "La pièce est déjà présente dans la voiture.";
    public static final String MSG_CONSTRUCTION_PIECE_EXCEPTION = "Une erreure est survenue lors de la création de la pièce.";
    public static final String MSG_CONSTRUCTION_VOITURE_EXCEPTION = "Une erreure est survenue lors de la création de la voiture.";
    public static final String MSG_VOITURE_COULEUR_NON_RENSEIGNE_EXCEPTION = "La couleur de la voiture doit être renseigné.";
    public static final String MSG_FACADE_VOITURE_EXISTE_EXCEPTION = "La voiture existe déjà.";
    public static final String MSG_FACADE_VOITURE_NON_TROUVEE_EXCEPTION = "La voiture n'a pas été trouvée dans l'application.";
    public static final String MSG_CONSTRUCTION_VOITURE_ROUE_MANQUANTE_EXCEPTION = "Il manque la pièce : %s";
    public static final int MARQUE_TAILLE_MAX = 20;
    public static final int MARQUE_TAILLE_MIN = 3;
    public static final int MODEL_TAILLE_MAX = 20;
    public static final int MODEL_TAILLE_MIN = 3;
    public static final int NUM_SERIE_TAILLE = 5;
    public static final String MSG_VOITURE_MARQUE_TAILLE_EXCEPTION = String.format("La marque doit contenir entre %d et %d caractères.", ConstantesMetier.MARQUE_TAILLE_MIN, ConstantesMetier.MARQUE_TAILLE_MAX);
    public static final String MSG_VOITURE_MODEL_TAILLE_EXCEPTION = String.format("La modèle doit contenir entre %d et %d caractères.", ConstantesMetier.MODEL_TAILLE_MIN, ConstantesMetier.MODEL_TAILLE_MAX);
    public static final String MSG_NUM_SERIE_TAILLE_EXCEPTION = String.format("Le numéro de série doit contenir %d caractères.", ConstantesMetier.NUM_SERIE_TAILLE, ConstantesMetier.NUM_SERIE_TAILLE);
    public static final String AJOUTER_PIECE = "Ajout des pièces :";
    public static final String MSG_FAMILLE_TYPE_PIECE_NON_TROUVE_EXCEPTION = "La famille de type de pièce n'a pas été trouvée.";
    public static final String MSG_TYPE_PIECE_NON_TROUVE_EXCEPTION = "Le type de pièce n'a pas été trouvée.";
    public static final String MSG_SUPPRESSION_VOITURE = "Erreur lors de la suppression de la voiture";


    private ConstantesMetier() {
    }


}
