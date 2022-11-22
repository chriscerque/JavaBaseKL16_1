package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.daos.references.ConstantesDao;
import net.ent.etrs.voiture.model.entities.Voiture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VoitureDaoImpl implements VoitureDao {

    private List<Voiture> persistence = new ArrayList<>();

    @Override
    public Voiture create(Voiture voiture) throws DaoException {
        if(voiture == null){
            throw new DaoException(ConstantesDao.DAO_VOITURE_CREATION_EXCEPTION);
        }
        if(persistence.contains(voiture)){
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_EXCEPTION);
        }
        this.persistence.add(voiture);
        return this.read(voiture.getNumSerie());
    }

    @Override
    public Voiture read(String numSerie) {
        Voiture p = null;
        for (Voiture voiture : this.persistence) {
            if(voiture.getNumSerie().equals(numSerie)) {
                p = voiture;
            }
        }
        return p;
    }

    @Override
    public void delete(String numSerie) throws DaoException {
        if(numSerie == null){
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_PAS_EXCEPTION);
        }
        Voiture voiture = this.read(numSerie);
        this.persistence.remove(voiture);
    }

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


    @Override
    public List<Voiture> readAll() throws DaoException {
        if(persistence == null || persistence.isEmpty()){
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_NULL_EXCEPTION);
        }
        return Collections.unmodifiableList(this.persistence);
    }
}
