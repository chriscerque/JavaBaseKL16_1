package net.ent.etrs.voiture.model.facades;

import net.ent.etrs.voiture.commons.utils.AffichageConsole;
import net.ent.etrs.voiture.model.daos.DaoFactory;
import net.ent.etrs.voiture.model.daos.DaoVoiture;
import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.entities.EntitiesFactory;
import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.exceptions.ConstructionPieceException;
import net.ent.etrs.voiture.model.entities.exceptions.ConstructionVoitureException;
import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;
import net.ent.etrs.voiture.model.facades.exceptions.BusinessException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FacadeMetierImpl implements FacadeMetier {

    private DaoVoiture daoVoiture = DaoFactory.fabriquerDaoVoitureImplDao();

    public FacadeMetierImpl() {
    }

    @Override
    public Piece creerPiece(final String numSerie, final TypePiece typePiece) throws BusinessException {
        try {
            Piece piece = EntitiesFactory.fabriquerPiece(numSerie, typePiece);
            return piece;
        } catch (ConstructionPieceException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Piece> ListerPiece(final String numSerieVoiture) throws BusinessException {
        try {
            return Collections.unmodifiableList(this.daoVoiture.readAllPiecesByVoiture(numSerieVoiture));
        } catch (DaoException | VoitureException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Voiture creerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur, final List<Piece> pieces) throws BusinessException {
        try {
            Voiture voiture = EntitiesFactory.fabriquerVoiture(numSerie, marque, model, couleur, pieces);
            this.daoVoiture.create(voiture);
            return this.daoVoiture.read(numSerie);
        } catch (ConstructionVoitureException | DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }


    // je ne lutilise pas pour le create car deja present dans les exceptions
    // cree car vu diagramme
    private void controlerVoitureExist(Voiture voiture) throws BusinessException {
        try {
            this.daoVoiture.exist(voiture);
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

    @Override
    public void initialerApp() {

        try {
            List<Piece> pieces = new ArrayList<>();

            Piece piece = EntitiesFactory.fabriquerPiece("32145", TypePiece.MOTEUR_V6_AV);
            Piece piece2 = EntitiesFactory.fabriquerPiece("55555", TypePiece.ROUE_ARD);


            pieces.add(piece);
            pieces.add(piece2);
            Voiture voiture = EntitiesFactory.fabriquerVoiture("12345", "renault", "clio", Couleur.BLANC, pieces);
            daoVoiture.create(voiture);
        } catch (ConstructionVoitureException | ConstructionPieceException | DaoException e) {
            AffichageConsole.afficherErreur("Erreur lors de linitialisation");
        }

    }
}
