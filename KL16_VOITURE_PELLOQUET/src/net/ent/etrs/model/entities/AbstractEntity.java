package net.ent.etrs.model.entities;

import net.ent.etrs.model.entities.exceptions.NumSerieException;

import java.util.Objects;

public abstract class AbstractEntity {

    private String numSerie;

    public AbstractEntity(String numSerie) throws NumSerieException {
        this.setNumSerie(numSerie);
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) throws NumSerieException {
        if (numSerie.length() != 5) {
            throw new NumSerieException();
        }
        this.numSerie = numSerie;
    }

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
