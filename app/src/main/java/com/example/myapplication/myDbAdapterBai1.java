package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDbAdapterBai1 {
    myDbHelper myhelper;
    public myDbAdapterBai1(Context context) {
        myhelper = new myDbHelper(context);
    }
    public long insertData(String name, int quantity, int unitPrice, String unit) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.QUANTITY, quantity);
        contentValues.put(myDbHelper.UNITPRICE, unitPrice);
        contentValues.put(myDbHelper.UNIT, unit);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }
    public String getData() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.NAME, myDbHelper.QUANTITY, myhelper.UNITPRICE, myhelper.UNIT};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name = cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            int quantity = cursor.getInt(cursor.getColumnIndex(myhelper.QUANTITY));
            int unitPrice = cursor.getInt(cursor.getColumnIndex(myhelper.UNITPRICE));
            String unit
                    = cursor.getString(cursor.getColumnIndex(myDbHelper.UNIT));
            buffer.append(cid + "  " + name + "   " + unit + "  \n");
        }
        return buffer.toString();
    }
    public int delete(String uname) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs = {uname};
        int count = db.delete(myDbHelper.TABLE_NAME, myDbHelper.NAME + "  =  ?", whereArgs);
        return count;
    }
    public int updateName(String oldName, String newName) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, newName);
        String[] whereArgs = {oldName};
        int count = db.update(myDbHelper.TABLE_NAME, contentValues, myDbHelper.NAME + "  = ? ",whereArgs );
        return count;
    }

    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "Android";
        private static final String TABLE_NAME = "product";
        private static final int DATABASE_Version = 1;
        private static final String UID="id";
        private static final String NAME  =  "Name";
        private static final String QUANTITY= "Quantity";
        private static final String UNITPRICE= "UnitPrice";
        private static final String UNIT= "Unit";private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"  ("+UID+"  INTEGER  PRIMARY  KEY  AUTOINCREMENT,  "+NAME+"  VARCHAR(255)  ,"+ QUANTITY+" INTEGER,"+ UNITPRICE+" INTEGER,"+ UNIT+" VARCHAR(255) ;";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;
        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context, "OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }
    }
}
