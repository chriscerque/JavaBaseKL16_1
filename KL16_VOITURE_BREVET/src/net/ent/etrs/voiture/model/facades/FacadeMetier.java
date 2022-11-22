package net.ent.etrs.voiture.model.facades;

import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;
import net.ent.etrs.voiture.model.facades.exceptions.BusinessException;

import java.util.List;

/**
 * Représente la facade métier de l'application.
 */
public interface FacadeMetier {

    /**
     * Créer une pièce dans la l'application.
     *
     * @param numSerie  numéro de série de la pièce
     * @param typePiece type de la pièce
     * @return la pièce créée.
     */
    Piece creerPiece(final String numSerie, final TypePiece typePiece) throws BusinessException;

    /**
     * Retourne la liste des pièces d'une voiture.
     *
     * @param numSerieVoiture numéroe de série de la voiture.
     * @return la liste des pièces de la voiture correspondant au numéroe de série passé en paramètre.
     */
    List<Piece> ListerPiece(final String numSerieVoiture) throws BusinessException;

    /**
     * Créer une voiture dans la l'application.
     *
     * @param numSerie numéro de série de la voiture
     * @param marque   marque de la voiture
     * @param model    modèle de la voiture
     * @param couleur  couleur de la voiture
     * @param pieces   liste des pièces pour la construction de la voiture
     * @return la voiture créée.
     */
    Voiture creerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur, final List<Piece> pieces) throws BusinessException;

    /**
     * Supprime la voiture de l'application.
     *
     * @param numSerie numéro de serie de la voiture à supprimer.
     */
    void supprimerVoiture(final String numSerie) throws BusinessException;

    /**
     * Retourne la liste des voitures de l'application.
     *
     * @return la liste des voitures.
     */
    List<Voiture> listerVoiture();
}
