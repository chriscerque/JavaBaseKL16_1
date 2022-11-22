package net.ent.etrs.voiture.voiture.model.facades;

import net.ent.etrs.voiture.voiture.model.daos.DaoFactory;
import net.ent.etrs.voiture.voiture.model.daos.DaoVoitureImpl;
import net.ent.etrs.voiture.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.voiture.model.entities.EntitiesFactory;
import net.ent.etrs.voiture.voiture.model.entities.Piece;
import net.ent.etrs.voiture.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.voiture.model.entities.references.TypePiece;
import net.ent.etrs.voiture.voiture.model.facades.exceptions.BuisnessException;

import java.util.List;

public class FacadeMetierImpl implements FacadeMetier{

    //Attribut
    DaoVoitureImpl dao = DaoFactory.fabriquerDao();

    //Constructeur
    public FacadeMetierImpl() {
    }

    //CRUD
    @Override
    public Voiture creerVoiture(final String numSerie, final String model, final String marque, final Couleur couleur, final List<Piece> pieces) throws BuisnessException {
        try {
            final Voiture v = EntitiesFactory.fabriquerVoiture(numSerie, marque, model, couleur, pieces);
            this.dao.creat(v);
        } catch (DaoException e) {
            throw new BuisnessException(ConstantesMetier.MSG_CONSTRUCTION_VOITURE_EXCEPTION);
        }
        return null;
    }


    @Override
    public Piece creerPiece(String numSerie, TypePiece typePiece) throws BuisnessException {
        final Piece p = EntitiesFactory.fabriquerPiece(numSerie, typePiece);
        return p;
    }

//    @Override
//    public List<Piece> ListerPiece(String numSerieVoiture) throws BuisnessException {
//        Voiture v = ;
//        return v.getPieces();
//    }

//    @Override
//    public void supprimerVoiture(String numSerie) throws BuisnessException {
//
//    }

    @Override
    public List<Voiture> listerVoiture() {
        return this.dao.readAll();
    }
}
