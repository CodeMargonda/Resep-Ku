package com.codemargonda.resepku.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.codemargonda.resepku.model.Resep;
import com.codemargonda.resepku.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jefri Yushendri on 5/9/2016.
 */


public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "resepdb";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_RESEP = "resep";

    public static final String TABLE_USER = "user";

    public static final String KEY_ID_RESEP = "id";
    public static final String KEY_NAMA_RESEP = "nama";
    public static final String KEY_DESKRIPSI_RESEP = "deskripsi";
    public static final String KEY_GAMBAR_RESEP = "gambar";
    public static final String KEY_FAVORIT = "favorit";
    public static final String KEY_USER_RESEP= "user";


    public static final String KEY_ID_USER = "id";
    public static final String KEY_NAMA_USER = "nama";
    public static final String KEY_EMAIL_USER = "email";
    public static final String KEY_PASSWORD_USER = "password";


    public static final String CREATE_RESEP_TABLE = "CREATE TABLE " + TABLE_RESEP + "("
            + KEY_ID_RESEP + " INTEGER PRIMARY KEY, "
            + KEY_NAMA_RESEP + " TEXT, "
            + KEY_DESKRIPSI_RESEP + " TEXT,"
            + KEY_GAMBAR_RESEP + " BLOB,"
            + KEY_FAVORIT + " INTEGER DEFAULT 0,"
            + KEY_USER_RESEP+ " TEXT"
            + ")";

    public static final String CREATE_RESEP_USER = "CREATE TABLE " + TABLE_USER + "("
            + KEY_ID_USER + " INTEGER PRIMARY KEY, "
            + KEY_NAMA_USER + " TEXT, "
            + KEY_PASSWORD_USER + " TEXT,"
            + KEY_EMAIL_USER + " TEXT"
            + ")";

    private static DatabaseHandler instance;

    public static synchronized DatabaseHandler getHelper(Context context) {
        if (instance == null)
            instance = new DatabaseHandler(context);
        return instance;
    }

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RESEP_TABLE);
        db.execSQL(CREATE_RESEP_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    // Tambah resep baru
    public void addResep(Resep resep) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA_RESEP, resep.getNama()); // Nama Resep
        values.put(KEY_DESKRIPSI_RESEP, resep.getDeskripsi()); // Deskripsi Resep
        values.put(KEY_GAMBAR_RESEP, resep.getGambar()); // Gambar Resep
        values.put(KEY_USER_RESEP, resep.getUser()); // User resep

        // Inserting Row
        db.insert(TABLE_RESEP, null, values);
        db.close(); // Closing database connection
    }

    // Get satu resep
    public Resep getResep(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_RESEP + " WHERE " + KEY_ID_RESEP + " =" + id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null)
            cursor.moveToFirst();
        Resep resep = new Resep(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getBlob(3));
        // return resep
        return resep;
    }

    // Get Semua Resep
    public List<Resep> getAllResep() {
        List<Resep> resepList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RESEP;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping pada setiap baris hasil query kemudian menambahknnya pada resepList
        if (cursor.moveToFirst()) {
            do {
                Resep resep = new Resep();
                resep.setID(Integer.parseInt(cursor.getString(0)));
                resep.setNama(cursor.getString(1));
                resep.setDeskripsi(cursor.getString(2));
                resep.setGambar(cursor.getBlob(3));
                resep.setFavorit(cursor.getInt(4));
                resep.setUser(cursor.getString(5));
                // Adding contact to list
                resepList.add(resep);
            } while (cursor.moveToNext());
        }

        // return contact list
        return resepList;
    }

    // Get jumlah resep
    public int getResepCount() {
        String countQuery = "SELECT  * FROM " + TABLE_RESEP;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Update satu resep
    public int updateResep(Resep resep) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA_RESEP, resep.getNama());
        values.put(KEY_DESKRIPSI_RESEP, resep.getDeskripsi());
        values.put(KEY_GAMBAR_RESEP, resep.getGambar());
        values.put(KEY_FAVORIT, resep.getFavorit());
        values.put(KEY_USER_RESEP, resep.getUser());

        // update baris
        return db.update(TABLE_RESEP, values, KEY_ID_RESEP + " = ?",
                new String[]{String.valueOf(resep.getID())});
    }

    // Hapus satu resep
    public void deleteResep(Resep resep) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESEP, KEY_ID_RESEP + " = ?",
                new String[]{String.valueOf(resep.getID())});
        db.close();
    }


    // Tambah resep baru
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA_USER, user.getNama()); // Nama Resep
        values.put(KEY_EMAIL_USER, user.getEmail()); // Deskripsi Resep
        values.put(KEY_PASSWORD_USER, user.getPassword()); // Gambar Resep

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
    }

    public User checkUser(String email, String password) {
        User user = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_EMAIL_USER + " = '" + email + "' AND " + KEY_PASSWORD_USER + " = '" + password+"'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
            user = new User();
            user.setNama(cursor.getString(1));
        }
        // return user
        return user;
    }


}