package net.ent.etrs.kl16voituregouin.model.facades;

import net.ent.etrs.kl16voituregouin.model.daos.DaoFactory;
import net.ent.etrs.kl16voituregouin.model.daos.DaoVoiture;
import net.ent.etrs.kl16voituregouin.model.daos.exceptions.DaoException;
import net.ent.etrs.kl16voituregouin.model.daos.references.ConstantesDao;
import net.ent.etrs.kl16voituregouin.model.entities.EntitiesFactory;
import net.ent.etrs.kl16voituregouin.model.entities.Piece;
import net.ent.etrs.kl16voituregouin.model.entities.Voiture;
import net.ent.etrs.kl16voituregouin.model.entities.exceptions.ConstructionVoitureException;
import net.ent.etrs.kl16voituregouin.model.entities.exceptions.NumSerieException;
import net.ent.etrs.kl16voituregouin.model.entities.exceptions.PieceException;
import net.ent.etrs.kl16voituregouin.model.entities.references.Couleur;
import net.ent.etrs.kl16voituregouin.model.entities.references.TypePiece;
import net.ent.etrs.kl16voituregouin.model.facades.exceptions.BusinessException;

import java.util.Collections;
import java.util.List;

public class FacadeMetierImpl implements FacadeMetier{

    DaoVoiture daoVoiture = DaoFactory.fabriquerVoitureDao();

    @Override
    public Piece creerPiece(String numSerie, TypePiece typePiece) throws BusinessException {
        try {
            return EntitiesFactory.fabriquerPiece(numSerie, typePiece);
        } catch (PieceException | NumSerieException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Piece> ListerPiece(String numSerieVoiture) throws BusinessException {

        try {
            return Collections.unmodifiableList(daoVoiture.read(numSerieVoiture).getPieces());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Voiture creerVoiture(String numSerie, String marque, String model, Couleur couleur, List<Piece> pieces) throws BusinessException {
        try{
            final Voiture voiture = EntitiesFactory.fabriquerVoiture(numSerie, marque, model, couleur,pieces);
            daoVoiture.create(voiture);
            return voiture;
        }catch (DaoException | ConstructionVoitureException e){
            throw new BusinessException(e.getMessage(), e);
        }

    }

    @Override
    public void supprimerVoiture(String numSerie) throws BusinessException {
        try {
            daoVoiture.delete(numSerie);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Voiture> listerVoiture() {
        return Collections.unmodifiableList(daoVoiture.readAll());
    }

    private void controlerVoitureExiste(Voiture voiture)throws BusinessException{
        try {
            this.daoVoiture.exist(voiture);
            } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
            }



    }
}
