package net.ent.etrs.voiture.model.entities;

import java.util.Objects;

public abstract class AbstractEntity {


    // ---------------------------------------
    //                Constantes
    // ---------------------------------------

    private String numSerie;

    // ---------------------------------------
    //                Constructeur
    // ---------------------------------------

    protected AbstractEntity(String numSerie) {
        this.numSerie = numSerie;
    }

    // ---------------------------------------
    //                Accesseur
    // ---------------------------------------

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getNumSerie() {
        return numSerie;
    }

    // ---------------------------------------
    //               Equals | Hashcode
    // ---------------------------------------

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
