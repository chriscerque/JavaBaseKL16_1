package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.daos.references.ConstantesDao;
import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DaoVoitureImpl implements DaoVoiture {

    private List<Voiture> persistence = new ArrayList<>();


    @Override
    public Voiture create(final Voiture voiture) throws DaoException {
        if (Objects.isNull(voiture)) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_CREATION_EXCEPTION);
        }
        if (exist(voiture)) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_EXCEPTION);
        }
        this.persistence.add(voiture);
        return this.read(voiture.getNumSerie());
    }

    @Override
    public Voiture read(final String numSerie) throws DaoException {
        Voiture voiture = null;

        for (Voiture voitureTemps : this.persistence) {
            if (voitureTemps.getNumSerie().equals(numSerie)) {
                voiture = voitureTemps;
            }
        }

        if (voiture == null) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_NULL_EXCEPTION);
        }


        return voiture;
    }

    @Override
    public void delete(final String numSerie) throws DaoException {
        Voiture voiture = read(numSerie);

        if (Objects.isNull(voiture)) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_PAS_EXCEPTION);
        }
        if (!exist(voiture)) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_PAS_EXCEPTION);
        }

        this.persistence.remove(voiture);

    }

    @Override
    public Voiture update(final Voiture voiture) throws DaoException {
        try {
            this.persistence.remove(voiture);
            this.persistence.add(voiture);
        } catch (Exception e) {
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
        } catch (Exception e) {
            throw new DaoException(ConstantesDao.DAO_PERSISTENCE_EXIST_EXCEPTION, e);
        }
    }

    @Override
    public List<Piece> readAllPiecesByVoiture(final String numSerie) throws DaoException, VoitureException {
        return Collections.unmodifiableList(read(numSerie).getPieces());
    }
}
