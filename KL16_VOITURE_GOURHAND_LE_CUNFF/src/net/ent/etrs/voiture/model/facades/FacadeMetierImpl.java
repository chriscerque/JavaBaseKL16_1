package net.ent.etrs.voiture.model.facades;

import net.ent.etrs.voiture.model.daos.DaoFactory;
import net.ent.etrs.voiture.model.daos.DaoVoiture;
import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.entities.EntitiesFactory;
import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.exceptions.ConstructionVoitureException;
import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.exceptions.PieceException;
import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;
import net.ent.etrs.voiture.model.facades.exceptions.BusinessException;

import java.time.LocalDate;
import java.util.List;

public class FacadeMetierImpl implements FacadeMetier{
    private DaoVoiture daoVoiture = DaoFactory.fabriquerDaoVoiture();

    /**
     * Créer une pièce dans la l'application.
     *
     * @param numSerie  numéro de série de la pièce
     * @param typePiece type de la pièce
     * @return la pièce créée.
     */
    @Override
    public Piece creerPiece(String numSerie, TypePiece typePiece) throws BusinessException {
        try {
            return new Piece(numSerie, typePiece);
        } catch (PieceException | NumSerieException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * Retourne la liste des pièces d'une voiture.
     *
     * @param numSerieVoiture numéroe de série de la voiture.
     * @return la liste des pièces de la voiture correspondant au numéroe de série passé en paramètre.
     */
    @Override
    public List<Piece> ListerPiece(String numSerieVoiture) throws BusinessException {
        try {
            return this.daoVoiture.read(numSerieVoiture).getPieces();
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage());
        }

    }

    /**
     * Créer une voiture dans la l'application.
     *
     * @param numSerie numéro de série de la voiture
     * @param marque   marque de la voiture
     * @param model    modèle de la voiture
     * @param couleur  couleur de la voiture
     * @param pieces   liste des pièces pour la construction de la voiture
     * @return la voiture créée.
     */
    @Override
    public Voiture creerVoiture(String numSerie, String marque, String model, Couleur couleur, List<Piece> pieces) throws BusinessException {
        try {
            Voiture voiture = new Voiture(numSerie, marque, model);
            voiture.setCouleur(couleur);
            for (Piece piece :
                    pieces) {
                voiture.ajouterPiece(piece);
            }
            return this.daoVoiture.create(voiture);
        } catch (DaoException | NumSerieException | VoitureException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * Supprime la voiture de l'application.
     *
     * @param numSerie numéro de serie de la voiture à supprimer.
     */
    @Override
    public void supprimerVoiture(String numSerie) throws BusinessException {
        try {
            this.daoVoiture.delete(this.daoVoiture.read(numSerie).getNumSerie());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * Retourne la liste des voitures de l'application.
     *
     * @return la liste des voitures.
     */
    @Override
    public List<Voiture> listerVoiture() {
        return this.daoVoiture.readAll();
    }

    private void controlerVoitureExiste(Voiture voiture) {

    }
}
