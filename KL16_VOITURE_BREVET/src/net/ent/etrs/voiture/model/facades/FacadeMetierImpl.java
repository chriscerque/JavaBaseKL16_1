package net.ent.etrs.voiture.model.facades;

import net.ent.etrs.voiture.model.daos.DaoFactory;
import net.ent.etrs.voiture.model.daos.VoitureDaoImpl;
import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.entities.EntitiesFactory;
import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.exceptions.ConstructionPieceException;
import net.ent.etrs.voiture.model.entities.exceptions.ConstructionVoitureException;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;
import net.ent.etrs.voiture.model.facades.exceptions.BusinessException;

import java.util.List;

/**
 * Implémentation de {@link FacadeMetier}
 */
public class FacadeMetierImpl implements FacadeMetier {
    //region ATTRIBUTS
    private VoitureDaoImpl daoVoiture = DaoFactory.fabriquervoituredao();
    //endregion
    //region MÉTHODES

    @Override
    public Piece creerPiece(final String numSerie, final TypePiece typePiece) throws BusinessException {
        try {
            return EntitiesFactory.fabriquerPiece(numSerie, typePiece);
        } catch (ConstructionPieceException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Piece> ListerPiece(final String numSerieVoiture) throws BusinessException {
        try {
            return this.daoVoiture.read(numSerieVoiture).getPieces();
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Voiture creerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur, final List<Piece> pieces) throws BusinessException {
        try {
            Voiture voiture = EntitiesFactory.fabriquerVoiture(numSerie, marque, model, couleur, pieces);
            this.daoVoiture.create(voiture);
            return voiture;
        } catch (DaoException | ConstructionVoitureException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void supprimerVoiture(final String numSerie) throws BusinessException {
        try {
            this.daoVoiture.delete(numSerie);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Voiture> listerVoiture() {
        return this.daoVoiture.readall();
    }

    private void controlerVoitureExist(final Voiture voiture) {
        this.daoVoiture.exist(voiture);
    }

    //endregion
}
