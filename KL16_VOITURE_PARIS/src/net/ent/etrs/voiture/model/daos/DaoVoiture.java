package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.entities.Voiture;

import java.util.List;

public interface DaoVoiture {
    Voiture create(Voiture voiture) throws net.ent.etrs.voiture.model.daos.exceptions.DaoException;

    Voiture read(String libelle) throws net.ent.etrs.voiture.model.daos.exceptions.DaoException;

    void delete(String libelle) throws net.ent.etrs.voiture.model.daos.exceptions.DaoException;

    Voiture update(Voiture voiture) throws net.ent.etrs.voiture.model.daos.exceptions.DaoException;

    List<Voiture> readAll();

    boolean exist(Voiture voiture) throws net.ent.etrs.voiture.model.daos.exceptions.DaoException;

    default void init() {
        throw new UnsupportedOperationException();
    }

}
