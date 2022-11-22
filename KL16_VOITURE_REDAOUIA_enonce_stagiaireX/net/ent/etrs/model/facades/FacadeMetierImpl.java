package net.ent.etrs.voiture.model.facades;

import net.ent.etrs.model.entities.exceptions.ConstructionPieceException;
import net.ent.etrs.model.entities.exceptions.NumSerieException;
import net.ent.etrs.model.entities.exceptions.PieceException;
import net.ent.etrs.model.entities.exceptions.TypePieceException;
import net.ent.etrs.model.entities.references.ConstantesMetier;
import net.ent.etrs.model.facades.exceptions.BusinessException;
import net.ent.etrs.voiture.model.daos.DaoFactory;
import net.ent.etrs.voiture.model.daos.DaoPiece;
import net.ent.etrs.voiture.model.daos.DaoVoiture;
import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.entities.EntitiesFactory;
import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;
import net.ent.etrs.model.entities.exceptions.ConstructionVoitureException;

import java.util.Collections;
import java.util.List;

public class FacadeMetierImpl implements FacadeMetier{

    //ATTRIBUTS
    private DaoVoiture daoVoiture = DaoFactory.fabriquerDaoVoiture();
    private DaoPiece daoPiece = DaoFactory.fabriquerDaoPiece();

    //CONSTRUCTEUR

    protected FacadeMetierImpl() {
    }

    //METHODES



    @Override
    public Piece creerPiece(String numSerie, TypePiece typePiece) throws DaoException, TypePieceException, NumSerieException, ConstructionPieceException, PieceException {


           try {
               Piece p =  EntitiesFactory.fabriquerPiece(numSerie, typePiece);
               return p;
           } catch(ConstructionPieceException e){
               throw new PieceException(ConstantesMetier.MSG_CONSTRUCTION_PIECE_EXCEPTION, e.getCause());
           }


    }

    @Override
    public List<Piece> ListerPiece(String numSerieVoiture) throws  DaoException {
        return Collections.unmodifiableList(this.daoVoiture.read(numSerieVoiture).getPieces());
    }

    @Override
    public Voiture creerVoiture(String numSerie, String marque, String model, Couleur couleur, List<Piece> pieces) throws ConstructionVoitureException, BusinessException {
        try {
            Voiture v = EntitiesFactory.fabriquerVoiture(numSerie, marque, model, couleur, pieces);
            return v;
        } catch (ConstructionVoitureException e) {
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
    public List<Voiture> listerVoiture()  {

        return Collections.unmodifiableList(this.daoVoiture.readAll());
    }
}
