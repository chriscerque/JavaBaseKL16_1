package net.ent.etrs.gestionVoiture.model.facades;

import net.ent.etrs.gestionVoiture.model.daos.DaoFactory;
import net.ent.etrs.gestionVoiture.model.daos.DaoVoitureInter;
import net.ent.etrs.gestionVoiture.model.daos.exceptions.DaoException;
import net.ent.etrs.gestionVoiture.model.entities.EntitiesFactory;
import net.ent.etrs.gestionVoiture.model.entities.Piece;
import net.ent.etrs.gestionVoiture.model.entities.Voiture;
import net.ent.etrs.gestionVoiture.model.entities.exceptions.ConstructionPieceException;
import net.ent.etrs.gestionVoiture.model.entities.exceptions.ConstructionVoitureException;
import net.ent.etrs.gestionVoiture.model.entities.references.Couleur;
import net.ent.etrs.gestionVoiture.model.entities.references.TypePiece;
import net.ent.etrs.gestionVoiture.model.facades.exceptions.BusinessException;

import java.util.List;

public class FacadeMetierImpl implements FacadeMetier {

    DaoVoitureInter DaoVoiture = DaoFactory.creerDaoVoiture();

    public FacadeMetierImpl() {
    }

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
            Voiture voiture = DaoVoiture.read(numSerieVoiture);
            return voiture.getPieces();
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Voiture creerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur, final List<Piece> pieces)
            throws BusinessException {
        try {
            Voiture voiture;
            voiture = EntitiesFactory.fabriquerVoiture(numSerie, marque, model, couleur, pieces);
            DaoVoiture.create(voiture);
            return voiture;
        } catch (ConstructionVoitureException |
                 DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void supprimerVoiture(final String numSerie) throws BusinessException {
        try {
            DaoVoiture.delete(numSerie);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Voiture> listerVoiture() {
        return DaoVoiture.readAll();
    }
}
