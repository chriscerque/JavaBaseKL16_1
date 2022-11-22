package net.ent.etrs.voiture.model.facades;

import net.ent.etrs.voiture.model.daos.DaoFactory;
import net.ent.etrs.voiture.model.daos.DaoVoiture;
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

public class FacadeMetierImpl implements FacadeMetier{

    private DaoVoiture daoVoiture = DaoFactory.fabriquerDaoVoiture();

    public FacadeMetierImpl() {}

    @Override
    public Piece creerPiece(final String numSerie, final TypePiece typePiece) throws BusinessException {
        try {
            Piece piece = EntitiesFactory.fabriquerPiece(numSerie, typePiece);
            return piece;
        } catch (ConstructionPieceException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Piece> ListerPiece(final String numSerieVoiture) throws BusinessException {
        try {
            final Voiture voiture = daoVoiture.read(numSerieVoiture);
            return voiture.getPieces();
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Voiture creerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur, final List<Piece> pieces) throws BusinessException {
        try {
            final Voiture voiture = EntitiesFactory.fabriquerVoiture(numSerie, marque, model, couleur, pieces);
            controlerVoitureExiste(voiture);
            daoVoiture.create(voiture);
            return voiture;
        } catch (ConstructionVoitureException | DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void supprimerVoiture(final String numSerie) throws BusinessException {
        try {
            daoVoiture.delete(numSerie);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }

    }

    @Override
    public List<Voiture> listerVoiture() {
        return daoVoiture.readAll();
    }

    private void controlerVoitureExiste(final Voiture voiture) throws BusinessException {
        try {
            daoVoiture.exist(voiture);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
