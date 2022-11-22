package net.ent.etrs.KL16_VOITURE.Model.DAOS;

import net.ent.etrs.KL16_VOITURE.Model.DAOS.exceptions.DaoException;
import net.ent.etrs.KL16_VOITURE.Model.DAOS.references.ConstantesDao;
import net.ent.etrs.KL16_VOITURE.Model.entities.Voiture;
import net.ent.etrs.KL16_VOITURE.Model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DaoVoitureImpl implements DaoVoiture{

    //ATTRIBUTS
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
        return this.read(voiture.getLibelle());
    }

    @Override
    public Voiture read(final String libelle) throws DaoException {
        Voiture v = null;
        for (Voiture batiment : this.persistence) {
            if(batiment.getLibelle().equals(libelle)) {
                v = batiment;
            }
        }
        return v;
    }

    @Override
    public void delete(final String libelle) throws DaoException {
        Voiture v = this.read(libelle);
        if (!exist(v)) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_PAS_EXCEPTION);
        }
        this.persistence.remove(v);

    }

    @Override
    public Voiture update(final Voiture batiment) throws DaoException {
        try {
            this.persistence.remove(batiment);
            this.persistence.add(batiment);
        }catch (Exception e) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_MODIFICATION_EXCEPTION);
        }
        return this.read(batiment.getLibelle());
    }


    @Override
    public List<Voiture> readAll() {
        return null;
    }

    @Override
    public boolean exist(final Voiture voiture) throws DaoException {
        try {
            return this.persistence.contains(voiture);
        } catch (Exception e) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_NULL_EXCEPTION, e);
        }
    }
}
