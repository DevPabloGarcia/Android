package com.ejemploporti.database;

import android.database.Cursor;

import com.ejemploporti.database.exception.CursorParserException;
import com.ejemploporti.model.Trasto;

import java.util.ArrayList;

/**
 * Created by CICE on 21/1/16.
 */
public class TrastosDatabaseParser {

    protected static ArrayList<Trasto> parseTrastos(Cursor cursor){

        ArrayList<Trasto> trastos = new ArrayList<>();

        if(cursor.moveToFirst()) {

            do{
                try {
                    trastos.add(parseTrasto(cursor));
                } catch (CursorParserException e) {
                    e.printStackTrace();
                }
            }while (cursor.moveToNext());

        }
        return trastos;

    }

    protected static Trasto parseTrasto(Cursor cursor) throws CursorParserException {

        Trasto trasto = new Trasto();
        if(cursor.getCount()>0){
            int index;

            index = cursor.getColumnIndex(TrastosTable.COLUMN_ID);
            if(index!=-1)
                trasto.setId(cursor.getLong(index));

            index = cursor.getColumnIndex(TrastosTable.COLUMN_NAME);
            if(index!=-1)
                trasto.setName(cursor.getString(index));

            index = cursor.getColumnIndex(TrastosTable.COLUMN_INFO);
            if(index!=-1)
                trasto.setInfo(cursor.getString(index));

            index = cursor.getColumnIndex(TrastosTable.COLUMN_ONSALE);
            if(index!=-1)
                trasto.setOnSale(cursor.getInt(index) == 1);

            index = cursor.getColumnIndex(TrastosTable.COLUMN_PRICE);
            if(index!=-1)
                trasto.setPrice(cursor.getFloat(index));

        } else throw new CursorParserException("No existe el trasto");

        return trasto;
    }


}
