package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.model.entities.exceptions.NumSerieException;
import net.ent.etrs.model.entities.references.ConstantesMetier;

import java.util.Objects;

public class AbstractEntity {

    //CONSTRUCTEUR

    protected AbstractEntity(String numSerie) throws NumSerieException {
        this.setNumSerie(numSerie);
    }

    //ATTRIBUTS
    private String numSerie;


    //GETTER et SETTER

    private void setNumSerie(String numSerie) throws NumSerieException {

        if (numSerie.isEmpty()|| numSerie.isBlank()){
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_NON_RENSEIGNE_EXCEPTION);
        }
        if (numSerie.length()!=ConstantesMetier.NUM_SERIE_TAILLE){
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_TAILLE_EXCEPTION);

        }
        this.numSerie = numSerie;
    }

    public String getNumSerie() {
        return numSerie;
    }

    //AUTRES METHODES


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(getNumSerie(), that.getNumSerie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumSerie());
    }
}
