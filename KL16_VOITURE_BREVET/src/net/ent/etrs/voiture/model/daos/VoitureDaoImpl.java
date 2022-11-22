package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.daos.references.ConstantesDao;
import net.ent.etrs.voiture.model.entities.Voiture;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implémentation de {@link VoitureDao}.
 */
public class VoitureDaoImpl implements VoitureDao {
    //region ATTRIBUTS
    List<Voiture> persistence = new ArrayList<>();
    //endregion
    //region CONSTRUCTEUR(S)

    protected VoitureDaoImpl() {
    }

    //endregion
    @Override
    public void create(final Voiture voiture) throws DaoException {
        if (Objects.isNull(voiture)) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_CREATION_EXCEPTION);
        }
        if (exist(voiture)) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_EXCEPTION);
        }
        persistence.add(voiture);
    }

    @Override
    public Voiture read(final String numSerie) throws DaoException {
        for (Voiture v : persistence) {
            if (v.getNumSerie().equals(numSerie)) {
                return v;
            }
        }
        throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_NULL_EXCEPTION);
    }

    @Override
    public Voiture update(final Voiture voiture) throws DaoException {
        if (Objects.isNull(voiture)) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_MODIFICATION_EXCEPTION);
        }
        if (!exist(voiture)) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_NULL_EXCEPTION);
        }
        for (Voiture v : persistence) {
            if (v == voiture) {
                int indice = persistence.indexOf(v);
                persistence.set(indice, voiture);
            }
        }
        return voiture;
    }

    @Override
    public void delete(final String numSerie) throws DaoException {
        if (Objects.isNull(numSerie) || numSerie.isBlank()) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_NULL_EXCEPTION);
        }
        for (Voiture v : persistence) {
            if (v.getNumSerie().equals(numSerie)) {
                persistence.remove(v);
            }
        }
        throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_PAS_EXCEPTION);
    }

    @Override
    public boolean exist(final Voiture voiture) {
        return persistence.contains(voiture);
    }

    @Override
    public List<Voiture> readall() {
        return persistence;
    }
}
