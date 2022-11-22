package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.daos.exceptions.DaoException;

import java.util.List;

public class DaoVoitureImpl implements DaoVoiture{


    @Override
    public Voiture create(Voiture voiture) throws DaoException {
        return null;
    }

    @Override
    public Voiture read(String libelle) throws DaoException {
        return null;
    }

    @Override
    public void delete(String libelle) throws DaoException {

    }

    @Override
    public Voiture update(Voiture voiture) throws DaoException {
        return null;
    }

    @Override
    public List<Voiture> readAll() {
        return null;
    }

    @Override
    public boolean exist(Voiture voiture) throws DaoException {
        return false;
    }
}
