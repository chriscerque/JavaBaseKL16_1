package net.ent.etrs.voiture.voiture.model.entities;

import net.ent.etrs.voiture.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.voiture.model.entities.references.ConstantesMetier;

import java.util.Objects;

public abstract class AbstractEntity {

    //Attribut
    private String numSerie;

    //Constructeur
    public AbstractEntity(String numSerie) {
        this.numSerie = numSerie;
    }

    //Ascesseurs
    public String getNumSerie() {
        return this.numSerie;
    }

    public void setNumSerie(final String numSerie) throws NumSerieException {
        if(this.numSerie.isEmpty()){
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_NON_RENSEIGNE_EXCEPTION);
        }else if(this.numSerie.length() != ConstantesMetier.NUM_SERIE_TAILLE){
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_TAILLE_EXCEPTION);
        }
        this.numSerie = numSerie;
    }

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
