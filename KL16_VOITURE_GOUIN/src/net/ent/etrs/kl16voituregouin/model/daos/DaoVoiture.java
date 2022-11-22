package net.ent.etrs.kl16voituregouin.model.daos;

import net.ent.etrs.kl16voituregouin.model.daos.exceptions.DaoException;
import net.ent.etrs.kl16voituregouin.model.entities.Voiture;

import java.util.List;

public interface DaoVoiture {

    void create(Voiture voiture) throws DaoException;

    Voiture read(String numSerie) throws DaoException;

    void delete(String numSerie) throws DaoException;

    boolean exist(Voiture voiture) throws DaoException;

    List<Voiture> readAll();
}
