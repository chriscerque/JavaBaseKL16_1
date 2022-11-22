package net.ent.etrs.voiture.model.entities.facades;

import net.ent.etrs.voiture.model.daos.DaoFactory;
import net.ent.etrs.voiture.model.daos.VoitureDao;
import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.entities.EntitiesFactory;
import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.model.entities.facades.exceptions.BusinessException;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

import java.util.List;

/**
 * The type Facade metier.
 */
public class FacadeMetierImpl implements FacadeMetier {
    private VoitureDao voitureDao = DaoFactory.fabriquerVoiture();

    @Override
    public Piece creerPiece(String numSerie, TypePiece typePiece) throws BusinessException {
        return null;
    }

    @Override
    public List<Piece> ListerPiece(String numSerieVoiture) throws BusinessException {
        return null;
    }

    @Override
    public Voiture creerVoiture(String numSerie, String marque, String model, Couleur couleur, List<Piece> pieces) throws BusinessException {
        try {
            Voiture voiture = EntitiesFactory.fabriquerVoiture(numSerie, marque, model,couleur,pieces);
            voiture.getLstPieces();
            return this.voitureDao.create(voiture);
        } catch (DaoException | VoitureException e) {
            throw new BusinessException(e.getMessage());
        }
    }


    @Override
    public void supprimerVoiture(String numSerie) throws BusinessException {
        try {
            this.voitureDao.delete(numSerie);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }

    }

    @Override
    public List<Voiture> listerVoiture() {
        return this.voitureDao.readAll();
    }
}
