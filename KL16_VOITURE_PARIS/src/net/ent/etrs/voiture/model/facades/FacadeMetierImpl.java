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

    @Override
    public void supprimerVoiture(final String numSerie) throws BusinessException{
        try {
            this.daoVoiture.delete(numSerie);
        }catch (net.ent.etrs.voiture.model.daos.exceptions.DaoException e){
            throw new BusinessException(e.getMessage(), e);
        }
    }

//    @Override
//    public List<Piece> listerPiece(String numSerie) throws BusinessException{
//        return this.daoPiece.readAll();
//    }

    @Override
    public Voiture creerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur, final List<Piece> pieces) throws BusinessException {
        try {
            final Voiture voiture = EntitiesFactory.fabriquerVoiture(numSerie, model, marque);
            this.daoVoiture.create(voiture);
            return voiture;
        } catch (ConstructionVoitureException | net.ent.etrs.voiture.model.daos.exceptions.DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Voiture> listerVoiture(){
        return this.daoVoiture.readAll();
    }

    @Override
    public void initialiserApplication() {

    }

    @Override
    public Piece creerPiece(final String numSerie, final TypePiece typePiece) throws BusinessException{
        try {
            final Piece piece = EntitiesFactory.fabriquerPiece(numSerie, typePiece);
            return piece;
        }catch (ConstructionPieceException e){
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Piece> ListerPiece(String numSerieVoiture) throws BusinessException {
        return null;
    }
}
