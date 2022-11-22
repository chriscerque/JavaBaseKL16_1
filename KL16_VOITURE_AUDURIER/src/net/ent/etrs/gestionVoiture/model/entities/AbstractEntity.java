package net.ent.etrs.gestionVoiture.model.entities;

import net.ent.etrs.gestionVoiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.gestionVoiture.model.entities.references.ConstantesMetier;

import java.util.Objects;

public abstract class AbstractEntity {
    //Attributs
    private String numSerie;

    //Constructor
    protected AbstractEntity(final String numSerie) throws NumSerieException {
        this.setNumSerie(numSerie);
    }

    //Getter/Setter

    public String getNumSerie() {
        return this.numSerie;
    }

    private void setNumSerie(final String numSerie) throws NumSerieException {
        if (numSerie == null || numSerie.isBlank()) {
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_NON_RENSEIGNE_EXCEPTION);
        }
        if (numSerie.length() != 5) {
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_TAILLE_EXCEPTION);
        }
        this.numSerie = numSerie;
    }


    //Equals/Hashcode

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(getNumSerie(), that.getNumSerie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumSerie());
    }

    //To String


    @Override
    public String toString() {
        return "AbstractEntity{" +
                "numSerie='" + numSerie + '\'' +
                '}';
    }
}
