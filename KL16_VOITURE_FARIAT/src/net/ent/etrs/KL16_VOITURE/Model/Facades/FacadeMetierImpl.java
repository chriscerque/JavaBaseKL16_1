package net.ent.etrs.KL16_VOITURE.Model.Facades;

import jdk.internal.misc.JavaNetUriAccess;
import net.ent.etrs.KL16_VOITURE.Model.DAOS.DaoFactory;
import net.ent.etrs.KL16_VOITURE.Model.DAOS.DaoVoiture;
import net.ent.etrs.KL16_VOITURE.Model.DAOS.exceptions.DaoException;
import net.ent.etrs.KL16_VOITURE.Model.Facades.excpetions.BusinessException;
import net.ent.etrs.KL16_VOITURE.Model.entities.EntitiesFactory;
import net.ent.etrs.KL16_VOITURE.Model.entities.Piece;
import net.ent.etrs.KL16_VOITURE.Model.entities.Voiture;
import net.ent.etrs.KL16_VOITURE.Model.entities.excpetions.ConstructionVoitureException;
import net.ent.etrs.KL16_VOITURE.Model.entities.excpetions.VoitureException;
import net.ent.etrs.KL16_VOITURE.Model.entities.references.TypePiece;

import java.util.List;

/**
 * Creation de classe representant l'implementation de l'interface FacadeMetier.
 */

public class FacadeMetierImpl implements FacadeMetier {

    private DaoVoiture piece;

    private DaoVoiture daoVoiture=DaoFactory.fabriquerDaoVoiture;
    private DaoVoiture persistence =DaoFactory.fabriquerDaoVoiture();

    @Override
    public Piece creerPiece(final Piece piece) throws BusinessException {
        try {
            this.piece.create(piece);
        } catch (DaoException e) {
//            //todo e.printStackTrace()
//            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }


    }

    @Override
    public List<Voiture> ListerPiece(final String numSerieVoiture) throws BusinessException {
        return this.piece.readAll();
    }

    @Override
    public Voiture creerVoiture(final String numSerie, String marque, String model) throws BusinessException, VoitureException, ConstructionVoitureException, DaoException {
    try{
        Voiture voiture = EntitiesFactory.fabriquerVoiture(numSerie, marque, model);

        persistence.create(voiture);
    }catch(DaoException | VoitureException e){
        throw new BusinessException(e.getMessage(), e);
    }

    }

    @Override
    public void supprimerVoiture(final Voiture voiture) throws BusinessException {
        try {
            this.daoVoiture.delete(voiture.getNumSerie());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage());
        }

    }

    @Override
    public List<Voiture> listerVoiture() {
        return this.daoVoiture.readAll();
    }
}
