package org.alrafei.instrumentsstore;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {
    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase db;
    List<Instruments> instrumentsList = new ArrayList<>();
    Context context;

    public DatabaseAdapter(Context context, SQLiteOpenHelper sqLiteOpenHelper, SQLiteDatabase db) {
        this.sqLiteOpenHelper = new InstrumentsSQLiteOpenHelper(context);
        this.db = sqLiteOpenHelper.getReadableDatabase();
        this.context = context;
    }

    public List<Instruments> getAllOrderedInstruments(){
        try {
            Cursor cursor = db.query("CART_INSTRUMENTS", null, null, null, null, null, null);

            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("NAME"));
                String type = cursor.getString(cursor.getColumnIndex("TYPE"));
                int image_resource = cursor.getInt(cursor.getColumnIndex("IMAGE_RESOURCE_ID"));
                float price = cursor.getFloat(cursor.getColumnIndex("PRICE"));
                int stock = cursor.getInt(cursor.getColumnIndex("STOCK"));
                String description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
                int sold = cursor.getInt(cursor.getColumnIndex("SOLD"));
                int outOfStock = cursor.getInt(cursor.getColumnIndex("OUT_IMAGE_RESOURCE_ID"));
                int id = cursor.getInt(cursor.getColumnIndex("_id"));

                Instruments instrument = new Instruments(id, name, description, image_resource, type, stock, price, sold, outOfStock);
                instrumentsList.add(instrument);

            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(context, "No Elements In Cart", Toast.LENGTH_SHORT).show();
        }
        return instrumentsList;
    }

    public List<Instruments> getMostSoldInstruments(){
        Cursor cursor = db.query("INSTRUMENTS", null, null, null, null, null, "SOLD DESC");

        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            String type = cursor.getString(cursor.getColumnIndex("TYPE"));
            int image_resource = cursor.getInt(cursor.getColumnIndex("IMAGE_RESOURCE_ID"));
            float price = cursor.getFloat(cursor.getColumnIndex("PRICE"));
            int stock = cursor.getInt(cursor.getColumnIndex("STOCK"));
            String description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
            int sold = cursor.getInt(cursor.getColumnIndex("SOLD"));
            int outOfStock = cursor.getInt(cursor.getColumnIndex("OUT_IMAGE_RESOURCE_ID"));
            int id = cursor.getInt(cursor.getColumnIndex("_id"));

            Instruments instrument = new Instruments(id, name, description, image_resource, type, stock, price, sold, outOfStock);
            instrumentsList.add(instrument);

        }
        cursor.close();
        return instrumentsList;
    }

    public List<Instruments> getAllInstruments(){
        Cursor cursor = db.query("INSTRUMENTS", null, null, null, null, null, "NAME");

        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            String type = cursor.getString(cursor.getColumnIndex("TYPE"));
            int image_resource = cursor.getInt(cursor.getColumnIndex("IMAGE_RESOURCE_ID"));
            float price = cursor.getFloat(cursor.getColumnIndex("PRICE"));
            int stock = cursor.getInt(cursor.getColumnIndex("STOCK"));
            String description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
            int sold = cursor.getInt(cursor.getColumnIndex("SOLD"));
            int outOfStock = cursor.getInt(cursor.getColumnIndex("OUT_IMAGE_RESOURCE_ID"));
            int id = cursor.getInt(cursor.getColumnIndex("_id"));

            Instruments instrument = new Instruments(id, name, description, image_resource, type, stock, price, sold, outOfStock);
            instrumentsList.add(instrument);

        }
        cursor.close();
        return instrumentsList;
    }

    public List<Instruments> getInstrumentsPriceOrder(){
        Cursor cursor = db.query("INSTRUMENTS", null, null, null, null, null, "PRICE");

        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            String type = cursor.getString(cursor.getColumnIndex("TYPE"));
            int image_resource = cursor.getInt(cursor.getColumnIndex("IMAGE_RESOURCE_ID"));
            float price = cursor.getFloat(cursor.getColumnIndex("PRICE"));
            int stock = cursor.getInt(cursor.getColumnIndex("STOCK"));
            String description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
            int sold = cursor.getInt(cursor.getColumnIndex("SOLD"));
            int outOfStock = cursor.getInt(cursor.getColumnIndex("OUT_IMAGE_RESOURCE_ID"));
            int id = cursor.getInt(cursor.getColumnIndex("_id"));

            Instruments instrument = new Instruments(id, name, description, image_resource, type, stock, price, sold, outOfStock);
            instrumentsList.add(instrument);

        }
        cursor.close();
        return instrumentsList;
    }

    public List<Instruments> getGuitars(){
        Cursor cursor = db.query("INSTRUMENTS", null, "TYPE= ?", new String[]{"guitar"}, null, null, "NAME");

        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            String type = cursor.getString(cursor.getColumnIndex("TYPE"));
            int image_resource = cursor.getInt(cursor.getColumnIndex("IMAGE_RESOURCE_ID"));
            float price = cursor.getFloat(cursor.getColumnIndex("PRICE"));
            int stock = cursor.getInt(cursor.getColumnIndex("STOCK"));
            String description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
            int sold = cursor.getInt(cursor.getColumnIndex("SOLD"));
            int outOfStock = cursor.getInt(cursor.getColumnIndex("OUT_IMAGE_RESOURCE_ID"));
            int id = cursor.getInt(cursor.getColumnIndex("_id"));

            Instruments instrument = new Instruments(id, name, description, image_resource, type, stock, price, sold, outOfStock);
            instrumentsList.add(instrument);

        }
        cursor.close();
        return instrumentsList;
    }

    public List<Instruments> getGuitarsBestSeller(){
        Cursor cursor = db.query("INSTRUMENTS", null, "TYPE= ?", new String[]{"guitar"}, null, null, "SOLD DESC");

        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            String type = cursor.getString(cursor.getColumnIndex("TYPE"));
            int image_resource = cursor.getInt(cursor.getColumnIndex("IMAGE_RESOURCE_ID"));
            float price = cursor.getFloat(cursor.getColumnIndex("PRICE"));
            int stock = cursor.getInt(cursor.getColumnIndex("STOCK"));
            String description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
            int sold = cursor.getInt(cursor.getColumnIndex("SOLD"));
            int outOfStock = cursor.getInt(cursor.getColumnIndex("OUT_IMAGE_RESOURCE_ID"));
            int id = cursor.getInt(cursor.getColumnIndex("_id"));

            Instruments instrument = new Instruments(id, name, description, image_resource, type, stock, price, sold, outOfStock);
            instrumentsList.add(instrument);

        }
        cursor.close();
        return instrumentsList;
    }

    public List<Instruments> getGuitarsPriceOrder(){
        Cursor cursor = db.query("INSTRUMENTS", null, "TYPE= ?", new String[]{"guitar"}, null, null, "PRICE");

        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            String type = cursor.getString(cursor.getColumnIndex("TYPE"));
            int image_resource = cursor.getInt(cursor.getColumnIndex("IMAGE_RESOURCE_ID"));
            float price = cursor.getFloat(cursor.getColumnIndex("PRICE"));
            int stock = cursor.getInt(cursor.getColumnIndex("STOCK"));
            String description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
            int sold = cursor.getInt(cursor.getColumnIndex("SOLD"));
            int outOfStock = cursor.getInt(cursor.getColumnIndex("OUT_IMAGE_RESOURCE_ID"));
            int id = cursor.getInt(cursor.getColumnIndex("_id"));

            Instruments instrument = new Instruments(id, name, description, image_resource, type, stock, price, sold, outOfStock);
            instrumentsList.add(instrument);

        }
        cursor.close();
        return instrumentsList;
    }

    public List<Instruments> getBass(){
        Cursor cursor = db.query("INSTRUMENTS", null, "TYPE= ?", new String[]{"bass"}, null, null, "NAME", null);

        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            String type = cursor.getString(cursor.getColumnIndex("TYPE"));
            int image_resource = cursor.getInt(cursor.getColumnIndex("IMAGE_RESOURCE_ID"));
            float price = cursor.getFloat(cursor.getColumnIndex("PRICE"));
            int stock = cursor.getInt(cursor.getColumnIndex("STOCK"));
            String description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
            int sold = cursor.getInt(cursor.getColumnIndex("SOLD"));
            int outOfStock = cursor.getInt(cursor.getColumnIndex("OUT_IMAGE_RESOURCE_ID"));
            int id = cursor.getInt(cursor.getColumnIndex("_id"));

            Instruments instrument = new Instruments(id, name, description, image_resource, type, stock, price, sold, outOfStock);
            instrumentsList.add(instrument);

        }
        cursor.close();
        return instrumentsList;
    }

    public List<Instruments> getBassBestSeller(){
        Cursor cursor = db.query("INSTRUMENTS", null, "TYPE= ?", new String[]{"bass"}, null, null, "SOLD DESC", null);

        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            String type = cursor.getString(cursor.getColumnIndex("TYPE"));
            int image_resource = cursor.getInt(cursor.getColumnIndex("IMAGE_RESOURCE_ID"));
            float price = cursor.getFloat(cursor.getColumnIndex("PRICE"));
            int stock = cursor.getInt(cursor.getColumnIndex("STOCK"));
            String description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
            int sold = cursor.getInt(cursor.getColumnIndex("SOLD"));
            int outOfStock = cursor.getInt(cursor.getColumnIndex("OUT_IMAGE_RESOURCE_ID"));
            int id = cursor.getInt(cursor.getColumnIndex("_id"));

            Instruments instrument = new Instruments(id, name, description, image_resource, type, stock, price, sold, outOfStock);
            instrumentsList.add(instrument);

        }
        cursor.close();
        return instrumentsList;
    }

    public List<Instruments> getBassPriceOrder(){
        Cursor cursor = db.query("INSTRUMENTS", null, "TYPE= ?", new String[]{"bass"}, null, null, "PRICE", null);

        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            String type = cursor.getString(cursor.getColumnIndex("TYPE"));
            int image_resource = cursor.getInt(cursor.getColumnIndex("IMAGE_RESOURCE_ID"));
            float price = cursor.getFloat(cursor.getColumnIndex("PRICE"));
            int stock = cursor.getInt(cursor.getColumnIndex("STOCK"));
            String description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
            int sold = cursor.getInt(cursor.getColumnIndex("SOLD"));
            int outOfStock = cursor.getInt(cursor.getColumnIndex("OUT_IMAGE_RESOURCE_ID"));
            int id = cursor.getInt(cursor.getColumnIndex("_id"));

            Instruments instrument = new Instruments(id, name, description, image_resource, type, stock, price, sold, outOfStock);
            instrumentsList.add(instrument);

        }
        cursor.close();
        return instrumentsList;
    }
}
