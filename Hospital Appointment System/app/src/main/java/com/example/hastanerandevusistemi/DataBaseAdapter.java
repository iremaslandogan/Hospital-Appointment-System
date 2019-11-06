package com.example.hastanerandevusistemi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.widget.Toast;

public class DataBaseAdapter
{
    static final String DATABASE_NAME = "Hastanesonveriler";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;

    static final String kisiler = "CREATE TABLE Kisi (KisiID INTEGER PRIMARY KEY AUTOINCREMENT,Ad TEXT,TC INTEGER,Soyad TEXT,Email TEXT,Sifre TEXT," +
            "Telefon INTEGER,Rol TEXT);";
    static final String doktorlar = "CREATE TABLE Doktor(DoktorID INTEGER,doktorAd TEXT,doktorTC INTEGER,doktorSoyad TEXT,doktorEmail TEXT,doktorSifre TEXT,doktorTelefon INTEGER,HastaneID INTEGER,BolumID);";
    static final String hastaneler= "CREATE TABLE Hastane (HastaneID INTEGER PRIMARY KEY AUTOINCREMENT,HastaneAd TEXT,Sehir TEXT,Adres TEXT); ";
    static final String bolumler = "CREATE TABLE Bolum (BolumID INTEGER PRIMARY KEY AUTOINCREMENT,BolumAd TEXT); ";
    static final String hastanebolumler = "CREATE TABLE HastaneBolum (HastaneBolumID INTEGER PRIMARY KEY AUTOINCREMENT,HastaneID INTEGER,BolumID INTEGER);";
    static final String favoriler = "CREATE TABLE Favori(FavoriID INTEGER PRIMARY KEY AUTOINCREMENT,FavoriHasta INTEGER,FavoriDoktor INTEGER,FavoriDurum TEXT);";
    static final String randevular = "CREATE TABLE Randevu(RandevuID INTEGER PRIMARY KEY AUTOINCREMENT,RandevuTarih TEXT,RandevuSaat TEXT,Doktor INTEGER,Hasta INTEGER,RandevuDurum TEXT);";
    static  final String tarihler="CREATE TABLE Tarih(TarihBilgi INTEGER PRIMARY KEY AUTOINCREMENT);";
    static  final String saatler="CREATE TABLE Saat(SaatBilgi INTEGER PRIMARY KEY AUTOINCREMENT);";

    public static  SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public  DataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  DataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }


    public void insertHastane(String hastanead,String sehir,String adres)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("HastaneAd", hastanead);
        newValues.put("Sehir",sehir);
        newValues.put("Adres",adres);

        db.insert("Hastane", null, newValues);
        Toast.makeText(context, "Hastane başarılı bir şekilde eklendi", Toast.LENGTH_LONG).show();
    }
    public void insertRandevu(String hastane,String bolum,String doktor,String tarih,String saat)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("Hastane",hastane );
        newValues.put("Bolum",bolum);
        newValues.put("Doktor",doktor);
        newValues.put("Tarih",tarih);
        newValues.put("Saat",saat);


        db.insert("Randevu", null, newValues);
        Toast.makeText(context, "Randevu başarılı bir şekilde eklendi", Toast.LENGTH_LONG).show();
    }
    public void insertBolum(String bolumad)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("BolumAd", bolumad);

        db.insert("Bolum", null, newValues);
        Toast.makeText(context, "Bolum başarılı bir şekilde eklendi", Toast.LENGTH_LONG).show();
    }
    public void insertDoktor(String adınız,String soyadınız,String tcniz,String emailiniz,String sifreniz,String teliniz,String hastaneniz,String bolumunuz)

    {

        Cursor has=db.query("Hastane", null, " HastaneAd=?",new String[]{hastaneniz}, null,null,null);
        if(has.getCount()<1)
        {
            has.close();
        }
        has.moveToFirst();
        String hastaneID= has.getString(has.getColumnIndex("HastaneID"));

        Cursor bol=db.query("Bolum", null, " BolumAd=?",new String[]{bolumunuz}, null,null,null);
        if(bol.getCount()<1)
        {
            bol.close();
        }
        bol.moveToFirst();
        String bolumID= bol.getString(bol.getColumnIndex("BolumID"));

        ContentValues newValues = new ContentValues();
        newValues.put("doktorTC", tcniz);
        newValues.put("doktorAd",adınız);
        newValues.put("doktorSoyad",soyadınız);
        newValues.put("doktorEmail",emailiniz);
        newValues.put("doktorSifre",sifreniz);
        newValues.put("doktorTelefon",teliniz);
        newValues.put("HastaneID",hastaneID);
        newValues.put("BolumID",bolumID);

        db.insert("Doktor", null, newValues);
        Toast.makeText(context, "Doktor başarılı bir şekilde eklendi", Toast.LENGTH_LONG).show();
    }
    public void insertKisi(String adınız,String soyadınız,String tcniz,String emailiniz,String sifreniz,String teliniz)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("TC", tcniz);
        newValues.put("Ad",adınız);
        newValues.put("Soyad",soyadınız);
        newValues.put("Email",emailiniz);
        newValues.put("Sifre",sifreniz);
        newValues.put("Telefon",teliniz);
        newValues.put("Rol","Hasta");

        db.insert("Kisi", null, newValues);
        Toast.makeText(context, "Üye oldunuz.\nLütfen giriş yapınız.", Toast.LENGTH_LONG).show();
    }
    public List<String> HastaneleriGetir()
    {

        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM  Hastane";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }
    public List<String> BolumleriGetir()
    {

        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM  Bolum";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }
    public List<String> DoktorGetir()
    {

        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM  Doktor";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }
    public List<String>TarihGetir()
    {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM  Tarih";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }
    public List<String>SaatGetir()
    {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM  Saat";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public String giris(String tc,String sifre)
    {

        Cursor cursor=db.query("Kisi", null, " TC=?",new String[]{tc}, null,null,null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("Sifre"));
        /*Toast.makeText(context, "ok", Toast.LENGTH_LONG).show();
        if(password == sifre){
            String rol= cursor.getString(cursor.getColumnIndex("Rol"));
            return rol;
        }
        cursor.close();
        return "NOT EXIST";*/
        return password;
    }
    public void hastanesil(String hastaneniz){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Hastane", "HastaneAd" + " = ?",
                new String[] { String.valueOf(hastaneniz) });
        db.close();
        Toast.makeText(context, "Hastane silindi", Toast.LENGTH_LONG).show();
    }
    public void hastaneguncelle(String hastanead,String sehir,String adres,String secilenhastanead) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("HastaneAd", hastanead);
        newValues.put("Sehir",sehir);
        newValues.put("Adres",adres);

        db.update("Hastane", newValues, "HastaneAd" + " = ?",
                new String[] { String.valueOf(secilenhastanead) });
        Toast.makeText(context, "Hastane güncellendi", Toast.LENGTH_LONG).show();
    }
    public void bolumsil(String bolumunuz){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Bolum", "BolumAd" + " = ?",
                new String[] { String.valueOf(bolumunuz) });
        db.close();
        Toast.makeText(context, "Bölüm silindi", Toast.LENGTH_LONG).show();
    }
    public void bolumguncelle(String bolumad,String secilenbolumad) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("BolumAd", bolumad);

        db.update("Bolum", newValues, "BolumAd" + " = ?",
                new String[] { String.valueOf(secilenbolumad) });
        Toast.makeText(context, "Bölüm güncellendi", Toast.LENGTH_LONG).show();
    }
    public void doktorsil(String doktorumuz){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Doktor", "doktorAd" + " = ?",


                new String[] { String.valueOf(doktorumuz) });
        db.close();
        Toast.makeText(context, "Doktor silindi", Toast.LENGTH_LONG).show();
    }
    public void doktorguncelle(String adınız,String soyadınız,String tcniz,String emailiniz,String sifreniz,String teliniz,String secilendoktorad,String secilenhastanead,String Secilenbolum) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor has=db.query("Hastane", null, " HastaneAd=?",new String[]{secilenhastanead}, null,null,null);
        if(has.getCount()<1)
        {
            has.close();
        }
        has.moveToFirst();
        String hastaneID= has.getString(has.getColumnIndex("HastaneID"));

        Cursor bol=db.query("Bolum", null, " BolumAd=?",new String[]{Secilenbolum}, null,null,null);
        if(bol.getCount()<1)
        {
            bol.close();
        }
        bol.moveToFirst();
        String bolumID= bol.getString(bol.getColumnIndex("BolumID"));

        ContentValues newValues = new ContentValues();
        newValues.put("doktorTC", tcniz);
        newValues.put("doktorAd",adınız);
        newValues.put("doktorSoyad",soyadınız);
        newValues.put("doktorEmail",emailiniz);
        newValues.put("doktorSifre",sifreniz);
        newValues.put("doktorTelefon",teliniz);
        newValues.put("HastaneID",hastaneID);
        newValues.put("BolumID",bolumID);

        db.update("Doktor", newValues, "DoktorAd" + " = ?",
                new String[] { String.valueOf(secilendoktorad) });
        Toast.makeText(context, "Doktor güncellendi", Toast.LENGTH_LONG).show();
    }

    public String urunFiyati(String urun)
    {

        Cursor cursor=db.query("urunlerim", null, " URUN=?",new String[]{urun}, null,null,null);
        cursor.moveToFirst();
        String fiyat= cursor.getString(cursor.getColumnIndex("FIYAT"));
        cursor.close();
        return fiyat;
    }

    public void getSiparis(String siparis, String toplam){

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String strTime = sdf.format(now);

        ContentValues newValues3 = new ContentValues();

        newValues3.put("SIPARIS",siparis);
        newValues3.put("TOPLAM",toplam + " TL");
        newValues3.put("TARIH", strTime);

        try {

            db.insert("siparislerim",null,newValues3);

        }catch(Exception ex){

            Toast.makeText(context, "Başarısız işlem.", Toast.LENGTH_LONG).show();

        }
    }




}
