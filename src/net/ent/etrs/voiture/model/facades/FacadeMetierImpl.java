package net.ent.etrs.voiture.model.facades;

import net.ent.etrs.voiture.model.daos.DaoFactory;
import net.ent.etrs.voiture.model.daos.DaoVoiture;
import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.entities.EntitiesFactory;
import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.exceptions.ConstructionPieceException;
import net.ent.etrs.voiture.model.entities.exceptions.ConstructionVoitureException;
import net.ent.etrs.voiture.model.entities.exceptions.ControleRoueVoitureException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;
import net.ent.etrs.voiture.model.facades.exceptions.BusinessException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FacadeMetierImpl implements FacadeMetier{

    private DaoVoiture daoVoiture;

    public FacadeMetierImpl() {
        this.daoVoiture = DaoFactory.fabriquerVoitureDao();
    }

    @Override
    public Piece creerPiece(final String numSerie, final TypePiece typePiece) throws BusinessException {
        try {
            return EntitiesFactory.fabriquerPiece(numSerie, typePiece);
        }catch (ConstructionPieceException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Piece> ListerPiece(final String numSerieVoiture) throws BusinessException {
        return Collections.unmodifiableList(this.daoVoiture.read(numSerieVoiture).getPieces());
    }


    @Override
    public Voiture creerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur, final List<Piece> pieces) throws BusinessException {
        try {
            final Voiture voiture = EntitiesFactory.fabriquerVoiture(numSerie, marque, model, couleur, pieces);
            this.controlerVoitureExiste(voiture);
            this.daoVoiture.create(voiture);
            return voiture;
        }catch (ConstructionVoitureException | ControleRoueVoitureException | DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    private void controlerVoitureExiste(final Voiture voiture) throws BusinessException {
        try {
            if(this.daoVoiture.exist(voiture)){
                throw new BusinessException(ConstantesMetier.MSG_FACADE_VOITURE_EXISTE_EXCEPTION);
            }
        } catch (DaoException e) {
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
        return Collections.unmodifiableList(this.daoVoiture.readAll());
    }
}
