package com.example.hastanerandevusistemi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper
{

    public DataBaseHelper(Context context, String name,CursorFactory factory, int version)
    {

        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase _db)
    {
        _db.execSQL(DataBaseAdapter.kisiler);
        _db.execSQL(DataBaseAdapter.doktorlar);
        _db.execSQL(DataBaseAdapter.hastaneler);
        _db.execSQL(DataBaseAdapter.bolumler);
        _db.execSQL(DataBaseAdapter.hastanebolumler);
        _db.execSQL(DataBaseAdapter.favoriler);
        _db.execSQL(DataBaseAdapter.randevular);
        _db.execSQL(DataBaseAdapter.tarihler);
        _db.execSQL(DataBaseAdapter.saatler);
    }

    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {

        _db.execSQL("DROP TABLE IF EXISTS Kisi");
        _db.execSQL("DROP TABLE IF EXISTS Doktor");
        _db.execSQL("DROP TABLE IF EXISTS Hastane");
        _db.execSQL("DROP TABLE IF EXISTS Bolum");
        _db.execSQL("DROP TABLE IF EXISTS HastaneBolum");
        _db.execSQL("DROP TABLE IF EXISTS Favori");
        _db.execSQL("DROP TABLE IF EXISTS Randevu");
        _db.execSQL("DROP TABLE IF EXISTS Tarih");
        _db.execSQL("DROP TABLE IF EXISTS Saat");



        onCreate(_db);
    }

}