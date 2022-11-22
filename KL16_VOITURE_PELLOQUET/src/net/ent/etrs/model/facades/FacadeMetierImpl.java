package net.ent.etrs.model.facades;

import net.ent.etrs.model.daos.DaoFactory;
import net.ent.etrs.model.daos.DaoVoiture;
import net.ent.etrs.model.daos.exceptions.DaoException;
import net.ent.etrs.model.entities.EntitiesFactory;
import net.ent.etrs.model.entities.Piece;
import net.ent.etrs.model.entities.Voiture;
import net.ent.etrs.model.entities.exceptions.ConstructionVoitureException;
import net.ent.etrs.model.entities.references.Couleur;
import net.ent.etrs.model.entities.references.TypePiece;
import net.ent.etrs.model.facades.exceptions.BusinessException;
import net.ent.etrs.presenter.Presenter;

import java.util.List;

public class FacadeMetierImpl implements FacadeMetier {
    private DaoVoiture daoVoiture = DaoFactory.fabriquerDaoVoiture();

    public FacadeMetierImpl() {

    }



    @Override
    public Piece creerPiece(String numSerie, TypePiece typePiece) throws BusinessException {
        return null;
    }

    @Override
    public List<Piece> ListerPiece(String numSerieVoiture) throws BusinessException {
        try {
            Voiture voiture = daoVoiture.read(numSerieVoiture);
            return voiture.getPieces();
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public Voiture creerVoiture(String numSerie, String marque, String model, Couleur couleur, List<Piece> pieces) throws BusinessException {
        try {
        Voiture voiture = EntitiesFactory.fabriquerVoiture(numSerie, marque, model, couleur, pieces);
            this.daoVoiture.create(voiture);
            return voiture;
        } catch (DaoException | ConstructionVoitureException e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public void supprimerVoiture(String numSerie) throws BusinessException {
        try {
            this.daoVoiture.delete(numSerie);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List<Voiture> listerVoiture() {
        return this.daoVoiture.readall();
    }
}
