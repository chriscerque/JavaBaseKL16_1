package net.ent.etrs.KL16_VOITURE.Model.Facades;

import net.ent.etrs.KL16_VOITURE.Model.Facades.excpetions.BusinessException;
import net.ent.etrs.KL16_VOITURE.Model.entities.Piece;
import net.ent.etrs.KL16_VOITURE.Model.entities.Voiture;
import net.ent.etrs.KL16_VOITURE.Model.Facades.exceptions.BusinessException;

import java.util.List;

/**
* Représente la facade métier de l'application.
*/
public interface FacadeMetier {

    /**
     * Creation piece.
     * @param piece
     * @return
     * @throws BusinessException
     */
    Piece creerPiece(Piece piece) throws BusinessException;

    /**
     * Retourne la liste des pièces d'une voiture.
     *
     * @param numSerieVoiture numéroe de série de la voiture.
     * @return la liste des pièces de la voiture correspondant au numéroe de série passé en paramètre.
     */
    List<Voiture> ListerPiece(final String numSerieVoiture) throws BusinessException;

    /**
     *  Creation de voiture.
     * @param voiture
     * @return
     * @throws BusinessException
     */
    Voiture creerVoiture(Voiture voiture) throws BusinessException;

    /**
     * Suppression voiture.
     * @param voiture
     * @throws BusinessException
     */
    void supprimerVoiture(Voiture voiture) throws BusinessException;

    /**
     * Retourne la liste des voitures de l'application.
     * @return la liste des voitures.
     */
    List<Voiture> listerVoiture();

    void creerVoiture(String numSerie, String marque, String model);
}
