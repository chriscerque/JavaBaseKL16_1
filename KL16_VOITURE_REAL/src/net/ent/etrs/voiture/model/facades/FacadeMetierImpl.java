package net.ent.etrs.voiture.model.facades;

import net.ent.etrs.voiture.model.daos.DaoFactory;
import net.ent.etrs.voiture.model.daos.PieceDao;
import net.ent.etrs.voiture.model.daos.VoitureDao;
import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.entities.EntitiesFactory;
import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;
import net.ent.etrs.voiture.model.facades.exceptions.BusinessException;

import java.util.List;

public class FacadeMetierImpl implements FacadeMetier{

    VoitureDao daoV = DaoFactory.fabriquerDaoVoiture();
    PieceDao daoP = DaoFactory.fabriquerDaoPiece();

    @Override
    public Piece creerPiece(String numSerie, TypePiece typePiece) throws BusinessException {
        final Piece piece;

        piece = EntitiesFactory.fabriquerPiece(numSerie, typePiece);
        this.daoP.create(piece);

        return piece;
    }

    @Override
    public List<Piece> ListerPiece(String numSerieVoiture) throws BusinessException {
        return this.daoP.readAll();
    }

    @Override
    public Voiture creerVoiture(String numSerie, String marque, String model, Couleur couleur, List<Piece> pieces) throws BusinessException, DaoException {
        Voiture voiture;

        voiture = EntitiesFactory.fabriquerVoiture(numSerie,marque,model,couleur,pieces);
        this.daoV.create(voiture);

        return voiture;
    }

    @Override
    public void supprimerVoiture(String numSerie) throws BusinessException, DaoException {
        this.daoV.delete(numSerie);
    }

//    @Override
//    public void supprimerVoiture(Voiture voiture) throws BusinessException {
//        this.daoV.delete(numSerie);
//    }

    @Override
    public List<Voiture> listerVoiture() throws DaoException {
        return this.daoV.readAll();
    }
}
