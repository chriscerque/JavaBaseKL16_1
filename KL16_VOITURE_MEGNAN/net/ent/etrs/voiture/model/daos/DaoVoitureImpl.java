package net.ent.etrs.voiture.voiture.model.daos;

import net.ent.etrs.voiture.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.voiture.model.daos.references.ConstantesDao;
import net.ent.etrs.voiture.voiture.model.entities.Voiture;

import java.util.ArrayList;
import java.util.List;

public class DaoVoitureImpl {

    //Attribut
    private final List<Voiture> voitures = new ArrayList<>();

    //Constructeur
    public DaoVoitureImpl() {
    }

    //Autres Methodes
    public void creat(Voiture voiture) throws DaoException {
        for(Voiture v : voitures) {
            if (voiture.getNumSerie().equals(voiture.getNumSerie())){
                throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_EXCEPTION);
            }
        }
        this.voitures.add(voiture);
    }

    public void delete(Voiture voiture) throws DaoException {
        for (Voiture c:voitures) {
            if(c.getNumSerie().equals(c.getNumSerie())){
                throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_PAS_EXCEPTION);
            }
        }
        this.voitures.remove(voiture);
    }

    public boolean exist(final Voiture voiture){
        for (Voiture c:voitures) {
            voiture.getNumSerie().equals(voiture.getNumSerie());
            return true;
        }
        return false;
    }

    public Voiture read(final Voiture voiture) {
        Voiture voiture1 = null;
        for (Voiture c : voitures) {
            c.getNumSerie().equals(voiture.getNumSerie());
            voiture1 = c;
        }
        return voiture1;
    }

    public List<Voiture> readAll(){
        return this.voitures;
    }

    public void update(final Voiture voiture) throws DaoException {
        if(voiture == null){
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXIST_NULL_EXCEPTION);
        }
        int indexClient = this.voitures.indexOf(voiture);
        this.voitures.set(indexClient, voiture);
    }
}
