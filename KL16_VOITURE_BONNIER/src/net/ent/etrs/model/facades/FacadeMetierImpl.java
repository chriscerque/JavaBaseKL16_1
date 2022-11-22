package net.ent.etrs.model.facades;

import net.ent.etrs.model.daos.DaoFactory;
import net.ent.etrs.model.daos.VoitureDao;
import net.ent.etrs.model.daos.exceptions.DaoException;
import net.ent.etrs.model.entities.EntitiesFactory;
import net.ent.etrs.model.entities.Piece;
import net.ent.etrs.model.entities.Voiture;
import net.ent.etrs.model.entities.exceptions.ConstructionPieceException;
import net.ent.etrs.model.entities.exceptions.ConstructionVoitureException;
import net.ent.etrs.model.entities.exceptions.PieceException;
import net.ent.etrs.model.entities.references.ConstantesMetier;
import net.ent.etrs.model.entities.references.Couleur;
import net.ent.etrs.model.entities.references.TypePiece;
import net.ent.etrs.model.facades.exceptions.BusinessException;
import net.ent.etrs.view.ConstantesView;

import java.util.List;

public class FacadeMetierImpl implements FacadeMetier {

    private VoitureDao voitureDao = DaoFactory.fabriquerVoitureDao();


    @Override
    public Piece creerPiece(String numSerie, TypePiece typePiece) throws BusinessException {
        try {
            Piece piece = EntitiesFactory.fabriquerPiece(numSerie, typePiece);
            piece.setTypePiece(typePiece);
            return piece;
        } catch (PieceException | ConstructionPieceException e) {
            throw new BusinessException(ConstantesView.MSG_INITIALISATION_PIECES_ERROR);
        }
    }

    @Override
    public List<Piece> ListerPiece(String numSerieVoiture) throws BusinessException {
        return null;
    }

    @Override
    public VoitureDao creerVoiture(String numSerie, String marque, String model, Couleur couleur, List<Piece> pieces) throws BusinessException {
        try {
            Voiture voiture = EntitiesFactory.fabriquerVoiture(numSerie, marque, model, couleur, pieces);
            this.voitureDao.create(voiture);
        } catch (DaoException | ConstructionVoitureException e) {
            throw new BusinessException(ConstantesMetier.MSG_FACADE_VOITURE_NON_TROUVEE_EXCEPTION);
        }
        return voitureDao;
    }

    @Override
    public void supprimerVoiture(String numSerie) throws BusinessException {
        try {
            listerVoiture().contains(numSerie);
            this.voitureDao.delete(numSerie);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage());
        }
    }


    @Override
    public List<Voiture> listerVoiture() throws BusinessException {
        try {
            List<Voiture> voiture = voitureDao.readAll();
            return this.voitureDao.readAll();
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
