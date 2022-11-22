package net.ent.etrs.gestionVoiture.model.daos;

import net.ent.etrs.gestionVoiture.model.daos.exceptions.DaoException;
import net.ent.etrs.gestionVoiture.model.daos.references.ConstantesDao;
import net.ent.etrs.gestionVoiture.model.entities.Voiture;
import net.ent.etrs.gestionVoiture.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaoVoitureMemImpl implements DaoVoitureInter {

    /**
     * Liste permettant la persistance en mémoire des pièces.
     */
    private List<Voiture> persistance = new ArrayList<>();

    protected DaoVoitureMemImpl() {
    }

    @Override
    public Voiture create(final Voiture voiture) throws DaoException {
        if (voiture == null) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_CREATION_EXCEPTION);
        }
        if (this.persistance.contains(voiture)) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_EXCEPTION);
        }
        this.persistance.add(voiture);
        return persistance.get(persistance.indexOf(voiture));
    }

    @Override
    public Voiture read(final String numSerie) throws DaoException {
        if (numSerie == null) {
            throw new DaoException(ConstantesMetier.MSG_NUM_SERIE_NON_RENSEIGNE_EXCEPTION);
        }
        final List<Voiture> listTemp = new ArrayList<>(this.persistance);
        for (Voiture voiture : listTemp) {
            if (voiture.getNumSerie().equals(numSerie)) {
                return voiture;
            }
        }
        throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_PAS_EXCEPTION);
    }

    @Override
    public List<Voiture> readAll() {
        return Collections.unmodifiableList(this.persistance);
    }

    @Override
    public Voiture update(final Voiture voiture) throws DaoException {
        if (voiture == null) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_MODIFICATION_EXCEPTION);
        }
        int indiceVoitureModif = this.persistance.indexOf(voiture);
        this.persistance.set(indiceVoitureModif, voiture);
        return this.persistance.get(indiceVoitureModif);
    }

    @Override
    public void delete(final String numSerie) throws DaoException {
        if (numSerie == null) {
            throw new DaoException(ConstantesMetier.MSG_NUM_SERIE_NON_RENSEIGNE_EXCEPTION);
        }
        Voiture v = this.read(numSerie);
        this.persistance.remove(v);
    }

    @Override
    public boolean exist(final Voiture voiture) throws DaoException {
        if (voiture == null) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_NULL_EXCEPTION);
        }
        if (persistance.contains(voiture)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void init() {

    }
}
