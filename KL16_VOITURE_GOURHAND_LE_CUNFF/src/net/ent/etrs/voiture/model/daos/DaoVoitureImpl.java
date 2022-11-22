package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.daos.references.ConstantesDao;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.daos.exceptions.DaoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DaoVoitureImpl implements DaoVoiture {
    private List<Voiture> persistence = new ArrayList<>();

    /**
     * @param voiture
     * @return
     */
    @Override
    public Voiture create(Voiture voiture) throws DaoException {
        if (Objects.isNull(voiture)) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_CREATION_EXCEPTION);
        }
        if (exist(voiture)) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_EXCEPTION);
        }
        this.persistence.add(voiture);
        return this.read(voiture.getNumSerie());
    }

    /**
     * @param numSerie
     * @return
     * @throws DaoException
     */
    @Override
    public Voiture read(String numSerie) throws DaoException {
        Voiture v = null;
        for (Voiture voiture : this.persistence) {
            if(voiture.getNumSerie().equals(numSerie)) {
                v = voiture;
            }
        }
        if (v == null) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_NULL_EXCEPTION);
        }
        return v;
    }

    /**
     * @param numSerie
     * @throws DaoException
     */
    @Override
    public void delete(String numSerie) throws DaoException {
        Voiture v = this.read(numSerie);
        if (!exist(v)) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_PAS_EXCEPTION);
        }
        this.persistence.remove(v);
    }

    /**
     * @param voiture
     * @return
     * @throws DaoException
     */
    @Override
    public Voiture update(Voiture voiture) throws DaoException {
        try {
            this.persistence.remove(voiture);
            this.persistence.add(voiture);
        }catch (Exception e) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_MODIFICATION_EXCEPTION);
        }
        return this.read(voiture.getNumSerie());
    }

    /**
     * @return
     */
    @Override
    public List<Voiture> readAll() {
        return Collections.unmodifiableList(this.persistence);
    }

    /**
     * @param voiture
     * @return
     * @throws DaoException
     */
    @Override
    public boolean exist(Voiture voiture) throws DaoException {
        try {
            return this.persistence.contains(voiture);
        } catch (Exception e) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_NULL_EXCEPTION, e);
        }
    }

    /**
     *
     */
    @Override
    public void init() {
        DaoVoiture.super.init();
    }
}
