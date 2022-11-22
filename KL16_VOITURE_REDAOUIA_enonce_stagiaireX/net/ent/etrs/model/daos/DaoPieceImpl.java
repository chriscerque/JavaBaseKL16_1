package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.daos.references.ConstantesDao;
import net.ent.etrs.voiture.model.entities.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaoPieceImpl implements DaoPiece{

    private List<Piece> lstPiece = new ArrayList<>();

    protected DaoPieceImpl(){

    }
    @Override
    public Piece create(Piece piece) throws DaoException {
        if(piece == null){
            throw new DaoException(ConstantesDao.DAO_PIECE_EXISTE_PAS);
        }
        if (lstPiece.contains(piece)){
            throw new DaoException(ConstantesDao.DAO_VOITURE_EXISTE_EXCEPTION);
        }
        lstPiece.add(piece);
        return piece;
    }

    @Override
    public Piece read(String numSerie) throws DaoException {
       if (numSerie.isBlank() || numSerie.isEmpty()){
           throw new DaoException(ConstantesDao.DAO_PIECE_EXISTE_PAS);
       }
       for (Piece p:lstPiece){
           if (p.getNumSerie().equals(numSerie)){
               return p;
           }
       }
       throw new DaoException(ConstantesDao.DAO_PIECE_EXISTE_PAS);
    }

    @Override
    public Piece update(Piece piece) throws DaoException {
        if (piece == null){
            throw new DaoException(ConstantesDao.DAO_PIECE_EXISTE_PAS);
        }
        if (lstPiece.contains(piece)){
         int indice = lstPiece.indexOf(piece);
         lstPiece.set(indice, piece);
         return lstPiece.get(indice);
        }
        throw new DaoException(ConstantesDao.DAO_PIECE_EXISTE_PAS);
    }

    @Override
    public void delete(String numSerie) throws DaoException {
        if (numSerie.isBlank() || numSerie.isEmpty()){
            throw new DaoException(ConstantesDao.DAO_PIECE_EXISTE_PAS);
        }
        for (Piece p:lstPiece){
            if (p.getNumSerie().equals(numSerie)){
                lstPiece.remove(p);
            }
        }
        throw new DaoException(ConstantesDao.DAO_PIECE_EXISTE_PAS);

    }

    @Override
    public boolean exist(Piece piece) throws DaoException {
        if (piece == null){
            throw new DaoException(ConstantesDao.DAO_PIECE_EXISTE_PAS);
        }
        if (lstPiece.contains(piece)) {
            return true;
        }
        return false;
    }

    @Override
    public List<Piece> readAll() {
        return Collections.unmodifiableList(lstPiece);
    }

    @Override
    public void init() {

    }
}
