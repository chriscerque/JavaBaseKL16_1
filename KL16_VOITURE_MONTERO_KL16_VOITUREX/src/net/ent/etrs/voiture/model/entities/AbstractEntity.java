package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;

import java.util.Objects;

public class AbstractEntity {

    //attribut(s)

    private String numSerie;

    //constructeur(s)

    protected AbstractEntity(final String numSerie) throws VoitureException, NumSerieException {
        this.setNumSerie(numSerie);
    }


    //getter et setter

    public String getNumSerie() {
        return this.numSerie;
    }

    private void setNumSerie(final String numSerie) throws NumSerieException, VoitureException {
        if (numSerie == null) {
            throw new VoitureException(ConstantesMetier.MSG_NUM_SERIE_NON_RENSEIGNE_EXCEPTION);
        }
        if (numSerie.length() != 5) {
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_TAILLE_EXCEPTION);
        }
        this.numSerie = numSerie;
    }


    //egal et hash

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


    //to string

    //autre(s) methode(s)


}
