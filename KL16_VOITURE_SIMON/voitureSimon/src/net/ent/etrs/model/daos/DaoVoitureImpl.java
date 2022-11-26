package net.ent.etrs.model.daos;

import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.daos.references.ConstantesDao;
import net.ent.etrs.voiture.model.entities.Voiture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * The type Dao voiture.
 */
public class DaoVoitureImpl implements  DaoVoiture{
    private List<Voiture> persistence = new ArrayList<>();


    @Override
    public Voiture create(final Voiture voiture) throws DaoException {
        if (Objects.isNull(voiture)){
            throw new DaoException(ConstantesDao.DAO_VOITURE_CREATION_EXCEPTION);
        }
        if (exist(voiture)){
            throw new DaoException((ConstantesDao.DAO_VOITURE_EXIST_EXCEPTION));
        }
        this.persistence.add(voiture);
        return this.read(voiture.getNumSerie());
    }

    @Override
    public Voiture read(final String numSerie) throws DaoException {
        Voiture v =null;
        for (Voiture voiture : this.persistence){
            if(voiture.getNumSerie().split("_")[1].equals(numSerie)){
                v = voiture;
            }
        }
        return v;
    }

    @Override
    public void delete(final String numSerie) throws DaoException {
        Voiture v = this.read(numSerie);
        if (!exist(v)){
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_PAS_EXCEPTION);
        }
        this.persistence.remove(v);
    }

    @Override
    public Voiture update(final Voiture voiture) throws DaoException {
        try {
            this.persistence.remove(voiture);
            this.persistence.add(voiture);
        }catch (Exception e) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_MODIFICATION_EXCEPTION);
        }
        return this.read(voiture.getNumSerie());
    }


    @Override
    public List<Voiture> readAll() {
        return Collections.unmodifiableList(this.persistence);
    }

    @Override
    public boolean exist(final Voiture voiture) throws DaoException {
        try {
            return this.persistence.contains(voiture);
        }catch (Exception e){
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_NULL_EXCEPTION);
        }
    }

}
