package net.ent.etrs.model.facades;

import net.ent.etrs.model.daos.DaoFactory;
import net.ent.etrs.model.daos.DaoVoiture;
import net.ent.etrs.model.entities.EntitiesFactory;
import net.ent.etrs.model.entities.Piece;
import net.ent.etrs.model.entities.Voiture;
import net.ent.etrs.model.entities.exceptions.NumSerieException;
import net.ent.etrs.model.entities.exceptions.VoitureException;
import net.ent.etrs.model.entities.references.Couleur;
import net.ent.etrs.model.entities.references.TypePiece;
import net.ent.etrs.model.facades.exceptions.BusinessException;
import net.ent.etrs.voiture.model.daos.exceptions.DaoException;

import java.util.List;

public class FacadeMetierImpl implements FacadeMetier{

    private DaoVoiture voitureDao = DaoFactory.fabriquerVoitureDao();

    // constructeur
    public FacadeMetierImpl() {
    }

    @Override
    public void supprimerVoiture(final String numSerie) throws BusinessException {
        try {
            this.voitureDao.delete(numSerie);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private void controlerVoitureExiste(final Voiture voiture) throws BusinessException, DaoException {
        if(voitureDao.exist(voiture)){
            throw new BusinessException("la voiture n'existe pas");
        }
    }

    @Override
    public List<Piece> ListerPiece(final String numSerieVoiture) throws BusinessException, DaoException {
        List<Piece> pieces = voitureDao.read(numSerieVoiture).getPieces();
        return pieces;
    }

    @Override
    public Voiture creerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur, final List<Piece> pieces) throws BusinessException, VoitureException, NumSerieException {
        Voiture v = EntitiesFactory.fabriquerVoiture(numSerie,marque,model,couleur);
        return v;
    }

    @Override
    public List<Voiture> listerVoiture() {
        return this.voitureDao.readAll();
    }

    @Override
    public Piece creerPiece(final String numSerie, final TypePiece typePiece) throws BusinessException{
        Piece p = EntitiesFactory.fabriquerPiece(numSerie,typePiece);
        return p;
    }
}
