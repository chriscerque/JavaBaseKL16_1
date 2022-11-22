package net.ent.etrs.voiture.model.facades;

import net.ent.etrs.voiture.model.daos.DaoFactory;
import net.ent.etrs.voiture.model.daos.DaoVoitureImpl;
import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.daos.exceptions.DaoVoiture;
import net.ent.etrs.voiture.model.daos.pieceDao;
import net.ent.etrs.voiture.model.entities.Voiture;

public class FacadeMetierImpl {
    private Voiture voiture = DaoFactory.fabriquerVoitureDao();
    private pieceDao piece  = DaoFactory.fabriquerPiece();
    private DaoVoiture VoitureDao = DaoFactory.fabriquerMaterielInformatiqueDao();


    }

