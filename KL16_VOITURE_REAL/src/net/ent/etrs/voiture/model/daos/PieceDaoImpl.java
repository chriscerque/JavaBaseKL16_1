package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.daos.references.ConstantesDao;
import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PieceDaoImpl implements PieceDao{

    private List<Piece> persistence = new ArrayList<>();

    @Override
    public void create(Piece piece) {
        this.persistence.add(piece);

    }

    @Override
    public Piece read(String numSerie) {
        Piece p = null;
        for (Piece piece : this.persistence) {
            if(piece.getNumSerie().equals(numSerie)) {
                p = piece;
            }
        }
        return p;
    }

    @Override
    public void delete(String numSerie) {
        this.persistence.remove(numSerie);
    }

    @Override
    public Piece update(Piece piece) throws DaoException {
        try {
            this.persistence.remove(piece);
            this.persistence.add(piece);
        }catch (Exception e) {
            throw new DaoException(ConstantesDao.DAO_VOITURE_MODIFICATION_EXCEPTION);
        }
        return this.read(piece.getNumSerie());
    }

    @Override
    public List<Piece> readAll() {
        return Collections.unmodifiableList(this.persistence);
    }
}
