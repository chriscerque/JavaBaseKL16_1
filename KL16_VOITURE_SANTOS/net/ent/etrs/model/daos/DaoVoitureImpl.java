package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.daos.references.ConstantesDao;
import net.ent.etrs.voiture.model.daos.exceptions.DaoVoiture;
import net.ent.etrs.voiture.model.entities.Voiture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DaoVoitureImpl implements DaoVoiture {

    private Voiture VoitureDao;
    private List<String> persistence = new ArrayList<>();

    @Override
    public Voiture create(Voiture voiture) throws DaoException {
        if (Objects.isNull(voiture)) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_CREATION_EXCEPTION);
        }
        if (exist(voiture)) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_EXCEPTION);
        }
        this.persistence.add(String.valueOf(voiture));
        return this.read(voiture.getNumSerie());
    }

    @Override
    public Voiture read(String numSerie) throws DaoException {
        return null;
    }


//    @Override
//    //Todo a finir erreur read
//    public Voiture read(final String libelle) throws DaoException {
//        Voiture v = null;
//        for (Voiture voiture : this.persistence) {
//            if(voiture.getLibelle().equals(libelle)) {
//                v = voiture;
//            }
//        }
//        return v;
//    }


    @Override
    public void delete(String numSerie) throws DaoException {

        Voiture voiture = this.read(numSerie);
        if (!exist(voiture)) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_PAS_EXCEPTION);
        }
        this.persistence.remove(voiture);
    }

    @Override
    public Voiture update(Voiture voiture) throws DaoException {
        return null;
    }

    @Override
    public List<String> readAll() {
        return Collections.unmodifiableList(this.persistence);
    }

    @Override
    public boolean exist(Voiture voiture) throws DaoException {
        try {
            return this.persistence.contains(voiture);
        } catch (Exception e) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_NULL_EXCEPTION, e);
        }
    }
    public void voitureDaoImpl() {
        this.VoitureDao = DaoFactory.fabriquerVoitureDao();
    }

    public DaoVoitureImpl() {
        this.VoitureDao = DaoFactory.fabriquerVoitureDao();
    }
}
