package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;

import java.util.Objects;

public class AbstractEntity {

    // attributs

    private String numSerie;

    // constructeur

    protected AbstractEntity(final String numSerie) throws NumSerieException{
        setNumSerie(numSerie);
    }

    // accesseurs


    public String getNumSerie() {
        return numSerie;
    }

    private void setNumSerie(String numSerie) throws NumSerieException {
        if (Objects.isNull(numSerie)){
            throw new NumSerieException();
        }
        if (numSerie.length()!= ConstantesMetier.NUM_SERIE_TAILLE){
            throw new NumSerieException();
        }
        this.numSerie = numSerie;
    }

    // equals hashCode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(getNumSerie(), that.getNumSerie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumSerie());
    }
}
