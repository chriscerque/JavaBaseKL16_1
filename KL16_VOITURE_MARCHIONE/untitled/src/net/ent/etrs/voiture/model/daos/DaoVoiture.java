package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.entities.Voiture;

public interface DaoVoiture {

    void create(Voiture v) throws DaoException;

    void delete(Voiture v) throws DaoException;

    Voiture readAll();
}
