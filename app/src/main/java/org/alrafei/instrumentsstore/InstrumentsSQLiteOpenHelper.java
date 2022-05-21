package org.alrafei.instrumentsstore;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class InstrumentsSQLiteOpenHelper extends SQLiteOpenHelper{
        private static final String DB_NAME = "Instruments_database";
        private static final int DB_VERSION = 1;

        public InstrumentsSQLiteOpenHelper(@Nullable Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE INSTRUMENTS (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NAME TEXT," +
                    "TYPE TEXT," +
                    "IMAGE_RESOURCE_ID INTEGER," +
                    "OUT_IMAGE_RESOURCE_ID INTEGER," +
                    "PRICE FLOAT," +
                    "DESCRIPTION TEXT," +
                    "STOCK INTEGER," +
                    "SOLD INTEGER)");
            db.execSQL("CREATE TABLE CART_INSTRUMENTS (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NAME TEXT," +
                    "TYPE TEXT," +
                    "IMAGE_RESOURCE_ID INTEGER," +
                    "OUT_IMAGE_RESOURCE_ID INTEGER," +
                    "PRICE FLOAT," +
                    "DESCRIPTION TEXT," +
                    "STOCK INTEGER," +
                    "SOLD INTEGER)");


            db.execSQL("CREATE TABLE CREDITCARD (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "FIRSTNAME TEXT," +
                    "LASTNAME TEXT," +
                    "ADDRESS1 TEXT," +
                    "ADDRESS2 TEXT," +
                    "CARDNUMBER INTEGER," +
                    "EXPIRYDATEYEAR INTEGER," +
                    "EXPIRYDATEMONTH INTEGER," +
                    "SECURITYNUMBER INTEGER)");

            insertInstrument(db, "Les Paul", R.drawable.lespaul800, "guitar", 799.99f, "This guitar was first designed by the legendary guitarist and inventor Les Paul and was named after him.", 0,  R.drawable.lespaul800outofstock);
            insertInstrument(db, "Ibanez", R.drawable.ibanez800, "guitar", 399.99f, "The Ibanez RG421AHMBMT is known for its easy to memorize name. It is mostly aimed for intermediate to professional guitarists with an emphasis on speed.", 2,  R.drawable.ibanez800outofstock);
            insertInstrument(db, "PRS", R.drawable.prs800, "guitar", 999.99f, "This is the Carlos Santana signature guitar, it was made famous by the guitar player himself when he played it in the music video to the song Smooth.", 1, R.drawable.prs800outofstock);
            insertInstrument(db, "Fender", R.drawable.strat800, "guitar", 509.99f, "This is the most recognizable guitar due to its shape that is well known to guitarists and non-guitarists alike, it is called a Fender Stratocaster.", 10,  R.drawable.strat800outofstock);

            insertInstrument(db, "Prodipe", R.drawable.pb70rasunbsunburstbassguitarprodipe800, "bass", 299.99f, "Prodige Bass", 26,  R.drawable.pb70rasunbsunburstbassguitarprodipe800outofstock);
            insertInstrument(db, "Yamaha 235", R.drawable.yamahabb235electric5stringbass, "bass", 235.99f, "intermediate level Yamaha Bass", 30,  R.drawable.yamahabb235electric5stringbassoutofstock);
            insertInstrument(db, "Yamaha 735", R.drawable.yamahabb735aelectric5stringbass, "bass", 735.99f, "high end Yamaha Bass", 80,  R.drawable.yamahabb735aelectric5stringbassoutofstock);
            insertInstrument(db, "Stagg", R.drawable.staggbc300fusionbass, "bass", 499.99f, "Stagg bass", 14,  R.drawable.staggbc300fusionbassoutofstock);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        private static void insertInstrument(SQLiteDatabase db, String name, int imageId, String type, float price, String description, int stock, int outOfStockImage){
            ContentValues contentValues = new ContentValues();
            contentValues.put("NAME", name);
            contentValues.put("IMAGE_RESOURCE_ID", imageId);
            contentValues.put("OUT_IMAGE_RESOURCE_ID", outOfStockImage);
            contentValues.put("TYPE", type);
            contentValues.put("PRICE", price);
            contentValues.put("DESCRIPTION", description);
            contentValues.put("STOCK", stock);
            contentValues.put("SOLD", 0);
            db.insert("INSTRUMENTS", null, contentValues);
        }

    public static void insertInstrumentCart(SQLiteDatabase db, String name, int imageId, String type, float price, String description, int stock, int outOfStockImage){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("IMAGE_RESOURCE_ID", imageId);
        contentValues.put("OUT_IMAGE_RESOURCE_ID", outOfStockImage);
        contentValues.put("TYPE", type);
        contentValues.put("PRICE", price);
        contentValues.put("DESCRIPTION", description);
        contentValues.put("STOCK", stock);
        contentValues.put("SOLD", 0);
        db.insert("CART_INSTRUMENTS", null, contentValues);
    }

        public static void insertCreditCard(SQLiteDatabase db, String firstName, String lastName, String add1, String add2, int cardNumber, int expiryDateYear, int expiryDateMonth, int securityNumber){
            ContentValues contentValues = new ContentValues();
            contentValues.put("FIRSTNAME", firstName);
            contentValues.put("LASTNAME", lastName);
            contentValues.put("ADDRESS1", add1);
            contentValues.put("ADDRESS2", add2);
            contentValues.put("CARDNUMBER", cardNumber);
            contentValues.put("EXPIRYDATEYEAR", expiryDateYear);
            contentValues.put("EXPIRYDATEMONTH", expiryDateMonth);
            contentValues.put("SECURITYNUMBER", securityNumber);
            db.insert("CREDITCARD", null, contentValues);
        }
    }
