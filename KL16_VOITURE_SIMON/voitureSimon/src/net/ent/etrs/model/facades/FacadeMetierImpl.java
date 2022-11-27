package net.ent.etrs.model.facades;


import net.ent.etrs.model.daos.DaoFactory;
import net.ent.etrs.model.daos.DaoVoiture;
import net.ent.etrs.model.daos.exceptions.DaoException;
import net.ent.etrs.model.entities.EntitiesFactory;
import net.ent.etrs.model.entities.Piece;
import net.ent.etrs.model.entities.Voiture;
import net.ent.etrs.model.entities.exceptions.ConstructionPieceException;
import net.ent.etrs.model.entities.references.Couleur;
import net.ent.etrs.model.entities.references.TypePiece;
import net.ent.etrs.model.facades.exceptions.BusinessException;

import java.util.List;

/**
 * The type Facade metier.
 */
public class FacadeMetierImpl implements FacadeMetier {

    private DaoVoiture daoVoiture = DaoFactory.fabriquerVoitureDao();

    @Override
    public Piece creerPiece(final String numSerie, final TypePiece typePiece) throws BusinessException {
//
        try {
            Piece p = EntitiesFactory.fabriquerPiece(numSerie,typePiece);
        }catch ( ConstructionPieceException e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List<Piece> ListerPiece(final String numSerieVoiture) throws BusinessException {
        return null;
    }

    @Override
    public Voiture creerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur, final List<Piece> pieces) throws BusinessException {
        try {
            this.daoVoiture.create(numSerie,marque,model,couleur,pieces);
        }catch (DaoException e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public void supprimerVoiture(final String numSerie) throws BusinessException {
        try {
            this.daoVoiture.delete(numSerie);
        }catch (DaoException e){
            throw new BusinessException(e.getMessage());
        }

    }

    @Override
    public List<Voiture> listerVoiture() {
        return this.daoVoiture.readAll();
    }
}
