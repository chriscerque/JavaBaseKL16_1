package net.ent.etrs.kl16voituregouin.model.daos;

import net.ent.etrs.kl16voituregouin.model.daos.exceptions.DaoException;
import net.ent.etrs.kl16voituregouin.model.daos.references.ConstantesDao;
import net.ent.etrs.kl16voituregouin.model.entities.Voiture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DaoVoitureImpl implements DaoVoiture{

    private List<Voiture> garage = new ArrayList<>();

    @Override
    public void create(final Voiture voiture) throws DaoException {
        if (Objects.isNull(voiture)) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_CREATION_EXCEPTION);
        }
        if (exist(voiture)) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_EXCEPTION);
        }
        this.garage.add(voiture);
    }

    @Override
    public void delete(String numSerie) throws DaoException {
        Voiture v = this.read(numSerie);
        if (!exist(v)) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_PAS_EXCEPTION);
        }
        this.garage.remove(v);
    }

    @Override
    public boolean exist(final Voiture voiture) throws DaoException {
        try {
            return this.garage.contains(voiture);
        } catch (Exception e) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_NULL_EXCEPTION, e);
        }
    }

    @Override
    public Voiture read(final String numSerie) throws DaoException {
        Voiture v = null;
        for (Voiture voiture : this.garage) {
            if(voiture.getNumSerie().equals(numSerie)) {
                v = voiture;
            }
        }
        return v;
    }

    @Override
    public List<Voiture> readAll() {
        return Collections.unmodifiableList(this.garage);
    }
}
