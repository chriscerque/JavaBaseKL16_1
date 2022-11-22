package net.ent.etrs.model.facades;


import net.ent.etrs.model.daos.DaoFactory;
import net.ent.etrs.model.daos.DaoVoiture;
import net.ent.etrs.model.daos.exceptions.DaoException;
import net.ent.etrs.model.entities.EntitiesFactory;
import net.ent.etrs.model.entities.Piece;
import net.ent.etrs.model.entities.Voiture;
import net.ent.etrs.model.entities.exceptions.ConstructionPieceException;
import net.ent.etrs.model.entities.exceptions.ConstructionVoitureException;
import net.ent.etrs.model.entities.exceptions.VoitureException;
import net.ent.etrs.model.entities.references.ConstantesMetier;
import net.ent.etrs.model.entities.references.Couleur;
import net.ent.etrs.model.entities.references.TypePiece;
import net.ent.etrs.model.facades.exceptions.BusinessException;

import java.util.List;

public class FacadeMetierImpl implements FacadeMetier{

    private DaoVoiture daoVoiture = DaoFactory.fabriquerDaoVoiture();

    /**
     * Créer une pièce dans la l'application.
     *
     * @param numSerie  numéro de série de la pièce
     * @param typePiece type de la pièce
     * @return la pièce créée.
     */
    @Override
    public Piece creerPiece(final String numSerie, final TypePiece typePiece) throws BusinessException {
        try {
            return EntitiesFactory.fabriquerPiece(numSerie, typePiece);
        } catch (ConstructionPieceException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Retourne la liste des pièces d'une voiture.
     *
     * @param numSerieVoiture numéro de série de la voiture.
     * @return la liste des pièces de la voiture correspondant au numéroe de série passé en paramètre.
     */
    @Override
    public List<Piece> ListerPiece(final String numSerieVoiture) throws BusinessException {
        try {
            return daoVoiture.read(numSerieVoiture).getPieces();
        } catch (DaoException | VoitureException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

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
    @Override
    public Voiture creerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur, final List<Piece> pieces) throws BusinessException {
        try {
            Voiture voiture = EntitiesFactory.fabriquerVoiture(numSerie, marque, model, couleur, pieces);
             return this.daoVoiture.create(voiture);
        } catch (ConstructionVoitureException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.MSG_FACADE_VOITURE_EXISTE_EXCEPTION);
        }
    }

    /**
     * Supprime la voiture de l'application.
     *
     * @param numSerie numéro de serie de la voiture à supprimer.
     */
    @Override
    public void supprimerVoiture(final String numSerie) throws BusinessException {
        try {
            this.daoVoiture.delete(numSerie);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.MSG_FACADE_VOITURE_NON_TROUVEE_EXCEPTION, e);
        }
    }

    /**
     * Retourne la liste des voitures de l'application.
     *
     * @return la liste des voitures.
     */
    @Override
    public List<Voiture> listerVoiture() {
        try {
            return this.daoVoiture.readAll();
        } catch (DaoException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
