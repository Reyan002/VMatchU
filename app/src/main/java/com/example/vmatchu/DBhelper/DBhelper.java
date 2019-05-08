package com.example.vmatchu.DBhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.vmatchu.Pojo.CityAreaSubareaSectorDetailsResponse;
import com.example.vmatchu.Pojo.PropertyType;
import com.example.vmatchu.Pojo.PropertyTypeData;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 2; //44

    // Database Name // TODO I make this public for use in another class like DeleteAllData
    public static final String DATABASE_NAME = "VmatchU_DB";

    private static final String TABLE_USER_DETAILS = "userDetails";
    private static final String TABLE_PROPERTY_TYPE = "propertyType";
    private static final String TABLE_CITY = "city";
    private static final String TABLE_AREA = "area";
    private static final String TABLE_SUB_AREA = "subArea";
    private static final String TABLE_SECTOR = "sector";
    private static final String TABLE_AREA_TYPE = "areaType";
    private static final String TABLE_PROPERTY_STATUS = "propertyStatus";



    public static String TAG = "VmatchU";
    SQLiteDatabase db;

    private Context context;
    String DB_TABLE;

    public DBhelper(Context context) {

        super(context, DATABASE_NAME , null, DATABASE_VERSION);

        Log.d(TAG, "DBHelper: " + DATABASE_NAME);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        String createUserDetailsTable = "CREATE TABLE IF NOT EXISTS " + TABLE_USER_DETAILS + " (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "user_id text not null,display_name text not null,user_password text not null,user_email text not null)";

        String createPropertyTypeTable = "CREATE TABLE IF NOT EXISTS " + TABLE_PROPERTY_TYPE + " (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "term_id text not null,property_name text not null)";

        String createCityTable = "CREATE TABLE IF NOT EXISTS " + TABLE_CITY + " (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "term_id text not null,property_name text not null)";

        String createAreaTable = "CREATE TABLE IF NOT EXISTS " + TABLE_AREA + " (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "term_id text not null,property_name text not null)";

        String createSubAreaTable = "CREATE TABLE IF NOT EXISTS " + TABLE_SUB_AREA + " (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "term_id text not null,property_name text not null)";

        String createSectorTable = "CREATE TABLE IF NOT EXISTS " + TABLE_SECTOR + " (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "term_id text not null,property_name text not null)";

        String createAreaType = "CREATE TABLE IF NOT EXISTS " + TABLE_AREA_TYPE + " (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "term_id text not null,property_name text not null)";

        String createPropertyStatus = "CREATE TABLE IF NOT EXISTS " + TABLE_PROPERTY_STATUS + " (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "term_id text not null,property_name text not null)";




        db.execSQL(createUserDetailsTable);
        db.execSQL(createPropertyTypeTable);
        db.execSQL(createCityTable);
        db.execSQL(createAreaTable);
        db.execSQL(createSubAreaTable);
        db.execSQL(createSectorTable);
        db.execSQL(createAreaType);
        db.execSQL(createPropertyStatus);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(db);
    }


    public void addUserDetails(String user_id, String display_name, String user_password,String user_email) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("user_id", user_id);
        contentValues.put("display_name", display_name);
        contentValues.put("user_password", user_password);
        contentValues.put("user_email", user_email);

        long status = insertTableData(TABLE_USER_DETAILS, contentValues);
        if (status != -1) {
            Log.i(TAG, "Row Succesfully Inserted");
        }
    }

    public void addPropertyTypeData(String term_id, String property_name) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("term_id", term_id);
        contentValues.put("property_name", property_name);

        long status = insertTableData(TABLE_PROPERTY_TYPE, contentValues);
        if (status != -1) {
            Log.i(TAG, "Row Succesfully Inserted");
        }
    }

    public ArrayList<PropertyTypeData> getPropertyType() {
        ArrayList<PropertyTypeData> arrayList = new ArrayList<>();
        db = getWritableDatabase();
        String query = "SELECT term_id, property_name FROM " + TABLE_PROPERTY_TYPE + "";

        Cursor c = db.rawQuery(query, null);
        if (c != null && c.moveToFirst()) {
            do {

                arrayList.add(new PropertyTypeData(c.getString(1),
                        c.getString(2)));

            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return arrayList;
    }






    public void addCities(String term_id, String property_name) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("term_id", term_id);
        contentValues.put("property_name", property_name);

        long status = insertTableData(TABLE_CITY, contentValues);
        if (status != -1) {
            Log.i(TAG, "Row Succesfully Inserted");
        }
    }

    public ArrayList<CityAreaSubareaSectorDetailsResponse> getCities() {
        ArrayList<CityAreaSubareaSectorDetailsResponse> arrayList = new ArrayList<>();
        db = getWritableDatabase();
        String query = "SELECT term_id, property_name FROM " + TABLE_CITY + "";

        Cursor c = db.rawQuery(query, null);
        if (c != null && c.moveToFirst()) {
            do {

                arrayList.add(new CityAreaSubareaSectorDetailsResponse(c.getString(0),
                        c.getString(1)));

            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return arrayList;
    }

    public String getCityById(String term_id) {
        ArrayList<CityAreaSubareaSectorDetailsResponse> arrayList = new ArrayList<>();
        db = getWritableDatabase();
        String city = "";
        String query = "SELECT property_name FROM " + TABLE_CITY + " WHERE term_id = "+term_id+"";

        Cursor c = db.rawQuery(query, null);
        if (c != null && c.moveToFirst()) {
            do {

                city = c.getString(0);
//                arrayList.add(new CityAreaSubareaSectorDetailsResponse(c.getString(0),
//                        c.getString(1)));

            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return city;
    }

    public void addArea(String term_id, String property_name) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("term_id", term_id);
        contentValues.put("property_name", property_name);

        long status = insertTableData(TABLE_AREA, contentValues);
        if (status != -1) {
            Log.i(TAG, "Row Succesfully Inserted");
        }
    }

    public ArrayList<CityAreaSubareaSectorDetailsResponse> getArea() {
        ArrayList<CityAreaSubareaSectorDetailsResponse> arrayList = new ArrayList<>();
        db = getWritableDatabase();
        String query = "SELECT term_id, property_name FROM " + TABLE_AREA + "";

        Cursor c = db.rawQuery(query, null);
        if (c != null && c.moveToFirst()) {
            do {

                arrayList.add(new CityAreaSubareaSectorDetailsResponse(c.getString(0),
                        c.getString(1)));

            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return arrayList;
    }

    public String getAreaById(String term_id) {
        ArrayList<CityAreaSubareaSectorDetailsResponse> arrayList = new ArrayList<>();
        db = getWritableDatabase();
        String area = "";
        String query = "SELECT property_name FROM " + TABLE_AREA + " WHERE term_id = "+term_id+"";

        Cursor c = db.rawQuery(query, null);
        if (c != null && c.moveToFirst()) {
            do {

                area = c.getString(0);
//                arrayList.add(new CityAreaSubareaSectorDetailsResponse(c.getString(0),
//                        c.getString(1)));

            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return area;
    }

    public void addSubArea(String term_id, String property_name) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("term_id", term_id);
        contentValues.put("property_name", property_name);

        long status = insertTableData(TABLE_SUB_AREA, contentValues);
        if (status != -1) {
            Log.i(TAG, "Row Succesfully Inserted");
        }
    }

    public ArrayList<CityAreaSubareaSectorDetailsResponse> getSubArea() {
        ArrayList<CityAreaSubareaSectorDetailsResponse> arrayList = new ArrayList<>();
        db = getWritableDatabase();
        String query = "SELECT term_id, property_name FROM " + TABLE_SUB_AREA + "";

        Cursor c = db.rawQuery(query, null);
        if (c != null && c.moveToFirst()) {
            do {

                arrayList.add(new CityAreaSubareaSectorDetailsResponse(c.getString(0),
                        c.getString(1)));

            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return arrayList;
    }

    public String getSubAreaById(String term_id) {
        ArrayList<CityAreaSubareaSectorDetailsResponse> arrayList = new ArrayList<>();
        db = getWritableDatabase();
        String sub_area = "";
        String query = "SELECT property_name FROM " + TABLE_SUB_AREA + " WHERE term_id = "+term_id+"";

        Cursor c = db.rawQuery(query, null);
        if (c != null && c.moveToFirst()) {
            do {

                sub_area = c.getString(0);
//                arrayList.add(new CityAreaSubareaSectorDetailsResponse(c.getString(0),
//                        c.getString(1)));

            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return sub_area;
    }

    public void addSector(String term_id, String property_name) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("term_id", term_id);
        contentValues.put("property_name", property_name);

        long status = insertTableData(TABLE_SECTOR, contentValues);
        if (status != -1) {
            Log.i(TAG, "Row Succesfully Inserted");
        }
    }

    public ArrayList<CityAreaSubareaSectorDetailsResponse> getSector() {
        ArrayList<CityAreaSubareaSectorDetailsResponse> arrayList = new ArrayList<>();
        db = getWritableDatabase();
        String query = "SELECT term_id, property_name FROM " + TABLE_SECTOR + "";

        Cursor c = db.rawQuery(query, null);
        if (c != null && c.moveToFirst()) {
            do {

                arrayList.add(new CityAreaSubareaSectorDetailsResponse(c.getString(0),
                        c.getString(1)));

            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return arrayList;
    }

    public String getSectorById(String term_id) {
        ArrayList<CityAreaSubareaSectorDetailsResponse> arrayList = new ArrayList<>();
        db = getWritableDatabase();
        String sector = "";
        String query = "SELECT property_name FROM " + TABLE_SECTOR + " WHERE term_id = "+term_id+"";

        Cursor c = db.rawQuery(query, null);
        if (c != null && c.moveToFirst()) {
            do {

                sector = c.getString(0);
//                arrayList.add(new CityAreaSubareaSectorDetailsResponse(c.getString(0),
//                        c.getString(1)));

            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return sector;
    }

    public void addAreaType(String term_id, String property_name) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("term_id", term_id);
        contentValues.put("property_name", property_name);

        long status = insertTableData(TABLE_AREA_TYPE, contentValues);
        if (status != -1) {
            Log.i(TAG, "Row Succesfully Inserted");
        }
    }

    public ArrayList<CityAreaSubareaSectorDetailsResponse> getAreaType() {
        ArrayList<CityAreaSubareaSectorDetailsResponse> arrayList = new ArrayList<>();
        db = getWritableDatabase();
        String query = "SELECT term_id, property_name FROM " + TABLE_AREA_TYPE + "";

        Cursor c = db.rawQuery(query, null);
        if (c != null && c.moveToFirst()) {
            do {

                arrayList.add(new CityAreaSubareaSectorDetailsResponse(c.getString(1),
                        c.getString(2)));

            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return arrayList;
    }

    public void addPropertyStatus(String term_id, String property_name) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("term_id", term_id);
        contentValues.put("property_name", property_name);

        long status = insertTableData(TABLE_PROPERTY_STATUS, contentValues);
        if (status != -1) {
            Log.i(TAG, "Row Succesfully Inserted");
        }
    }

    public ArrayList<CityAreaSubareaSectorDetailsResponse> getPropertyStatus() {
        ArrayList<CityAreaSubareaSectorDetailsResponse> arrayList = new ArrayList<>();
        db = getWritableDatabase();
        String query = "SELECT term_id, property_name FROM " + TABLE_PROPERTY_STATUS + "";

        Cursor c = db.rawQuery(query, null);
        if (c != null && c.moveToFirst()) {
            do {

                arrayList.add(new CityAreaSubareaSectorDetailsResponse(c.getString(1),
                        c.getString(2)));

            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return arrayList;
    }


    public long insertTableData(String tablename, ContentValues values)
            throws SQLException {
        DB_TABLE = tablename;
        db = this.getWritableDatabase();
        long returnValue = db.insert(DB_TABLE, null, values);
        db.close();
        return returnValue;
    }

    public void emptyTable(String tablename)
            throws SQLException {
        DB_TABLE = tablename;
        db = this.getWritableDatabase();
        db.delete(DB_TABLE, null, null);
//        db.execSQL("delete from "+ DB_TABLE);
        db.close();
    }
}
