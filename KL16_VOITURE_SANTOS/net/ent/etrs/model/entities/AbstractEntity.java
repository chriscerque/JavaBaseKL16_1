package net.ent.etrs.voiture.model.entities;
import net.ent.etrs.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.references.Constantes;

import java.util.Objects;


public class AbstractEntity {


    private String numSerie;


    public AbstractEntity(String numSerie) throws NumSerieException {
        setNumSerie( numSerie);


    }

    public String getNumSerie() {
        return this.numSerie;
    }

    private void setNumSerie(String numSerie) throws NumSerieException {
        if (numSerie == null || numSerie.isBlank() || numSerie.length() != 5) {
            throw new NumSerieException(Constantes.ABSTRACTENTITY_NUMSERIE_NULL_EXCEPTION);
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
