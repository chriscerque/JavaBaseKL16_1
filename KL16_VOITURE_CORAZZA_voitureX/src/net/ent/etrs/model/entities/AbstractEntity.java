package net.ent.etrs.model.entities;

import net.ent.etrs.model.entities.exceptions.NumSerieException;

import java.util.Objects;

public class AbstractEntity {
    private String numSerie;

    public AbstractEntity(final String numSerie) throws NumSerieException {
        setNumSerie(numSerie);
    }

    // setter
    private void setNumSerie(final String numSerie) throws NumSerieException {
        if (numSerie.length() == 5) {
            this.numSerie = numSerie;
        }else{
            throw new NumSerieException();
        }
    }

    // getter
    public String getNumSerie() {
        return numSerie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(numSerie, that.numSerie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numSerie);
    }


}
