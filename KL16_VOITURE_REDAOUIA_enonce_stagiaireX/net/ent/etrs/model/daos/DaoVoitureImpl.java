package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.daos.references.ConstantesDao;
import net.ent.etrs.voiture.model.entities.Voiture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaoVoitureImpl implements DaoVoiture{

    private List<Voiture> lstVoiture = new ArrayList<>();

    protected DaoVoitureImpl() {

    }

    @Override
    public Voiture create(Voiture voiture) throws DaoException {
        if (voiture == null){
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_NULL_EXCEPTION);
        }
        if (lstVoiture.contains(voiture)){
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_EXCEPTION);
        }
        lstVoiture.add(voiture);
        return voiture;
    }

    @Override
    public Voiture read(String numSerie) throws DaoException {
        if (numSerie.isBlank() || numSerie.isEmpty()){
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_PAS_EXCEPTION);
        }
        for (Voiture v:lstVoiture){
            if (v.getNumSerie().equals(numSerie)){
                return v;
            }
        }
        throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_PAS_EXCEPTION);
    }

    @Override
    public Voiture update(Voiture voiture) throws DaoException {
        if (voiture == null){
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_PAS_EXCEPTION);
        }
        if (lstVoiture.contains(voiture)){
            int indice = this.lstVoiture.indexOf(voiture);
            this.lstVoiture.set(indice, voiture);
            return this.lstVoiture.get(indice);
        }
        throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_PAS_EXCEPTION);
    }

    @Override
    public void delete(String numSerie) throws DaoException {
        if (numSerie.isEmpty() || numSerie.isBlank()){
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_PAS_EXCEPTION);
        }
        for (Voiture v:lstVoiture){
            if (v.getNumSerie().equals(numSerie)){
                lstVoiture.remove(v);
            }
        }
        throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_PAS_EXCEPTION);
    }

    @Override
    public boolean exist(Voiture voiture) throws DaoException {
        if (voiture == null){
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_PAS_EXCEPTION);
        }
        if (lstVoiture.contains(voiture)){
            return true;
        }
        return false;
    }

    @Override
    public List<Voiture> readAll() {
        return Collections.unmodifiableList(lstVoiture);
    }

    @Override
    public void init() {

    }
}
