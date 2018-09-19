package com.example.cice.sqlite.api;

import android.content.Context;

import com.example.cice.sqlite.database.DatabaseException;
import com.example.cice.sqlite.database.TrastosDatabaseManager;
import com.example.cice.sqlite.Model.Trasto;


import java.util.ArrayList;

/**
 * Created by CICE on 21/1/16.
 */
public class ApiManager {

    TrastosDatabaseManager trastosDatabaseManager;

    public ApiManager(Context context) {
        this.trastosDatabaseManager = new TrastosDatabaseManager(context);
    }

    public Trasto insertTrasto (Trasto trasto) throws TrastoException {
        long id = 0;
        try {
            id = trastosDatabaseManager.insertTrastos(trasto);
            trasto.setId(id);
        } catch (DatabaseException e) {
            throw new TrastoException(e.getMessage());
        }

        return trasto;
    }

    public ArrayList<Trasto> getTrastos(){
        return trastosDatabaseManager.getTrastos();
    }

    public Trasto getTrasto(long id) throws TrastoException {
        try {
            return trastosDatabaseManager.getTrasto(id);
        } catch (DatabaseException e) {
            throw new TrastoException(e.getMessage());
        }
    }

    public void deleteTrasto(Trasto trasto){
        trastosDatabaseManager.deleteTrasto(trasto);
    }
}
