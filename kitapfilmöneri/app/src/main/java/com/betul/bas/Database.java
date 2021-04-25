
package com.betul.bas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    String TabloOneri = "OnerilerTablosu";
    String TabloUye = "UyelerTablosu";
    Context context;
    public Database(Context context){
        super(context,"Database", null, 1);
        this.context = context;
    };



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+""+TabloUye+""+" " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, isimSoyisim TEXT, EPosta  TEXT, Telefon TEXT, Sifre TEXT);");

        sqLiteDatabase.execSQL("CREATE TABLE "+""+TabloOneri+""+" " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, Turu TEXT, Ismi  TEXT, YayinYili TEXT, SayfaSayisi TEXT" +
                ",ImdbPuani TEXT, Notu TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public ArrayList<ProfilList> profilList(int kid){
        ArrayList<ProfilList> liste  = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String query = String.format("SELECT id,"+"isimSoyisim"+","+"EPosta"+","
                +"Telefon"+","+"Sifre"+" FROM UyelerTablosu WHERE id="+kid+"");
        Cursor cr=db.rawQuery(query, null);
        while (cr.moveToNext()){
            liste.add(new ProfilList(cr.getInt(0),cr.getString(1),cr.getString(2),cr.getString(3),cr.getString(4)));
        }
        return liste;
    }
    public void userAdd(String isimSoyisim,String ePosta,String telefon,String sifre){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("isimSoyisim",isimSoyisim);
        contentValues.put("EPosta",ePosta);
        contentValues.put("Telefon",telefon);
        contentValues.put("Sifre",sifre);
        database.insert(TabloUye,null,contentValues);
        Toast.makeText(context,"Üye kaydı başarılı.", Toast.LENGTH_LONG).show();
        database.close();

    }
    public void userUpdate(int kid,String isimSoyisim,String ePosta,String telefon,String sifre){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("isimSoyisim",isimSoyisim);
        contentValues.put("EPosta",ePosta);
        contentValues.put("Telefon",telefon);
        contentValues.put("Sifre",sifre);

        database.update(TabloUye,contentValues,"id"+"='"+kid+"'",null);
        Toast.makeText(context,"Bilgiler başarıyla güncellendi.", Toast.LENGTH_LONG).show();
        database.close();
    }
    public void oneriAdd(String turu,String ismi,String yayinYili,String sayfaSayisi,String imdbPuani,String not){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("Turu",turu);
        contentValues.put("Ismi",ismi);
        contentValues.put("YayinYili",yayinYili);
        contentValues.put("SayfaSayisi",sayfaSayisi);
        contentValues.put("ImdbPuani",imdbPuani);
        contentValues.put("Notu",not);

        database.insert(TabloOneri,null,contentValues);
        Toast.makeText(context,"Öneri kaydı başarılı.", Toast.LENGTH_LONG).show();
        database.close();

    }
    public void oneriUpdate(int id,String turu,String ismi,String yayinYili,String sayfaSayisi,String imdbPuani,String not){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Turu",turu);
        contentValues.put("Ismi",ismi);
        contentValues.put("YayinYili",yayinYili);
        contentValues.put("SayfaSayisi",sayfaSayisi);
        contentValues.put("ImdbPuani",imdbPuani);
        contentValues.put("Notu",not);
        database.update(TabloOneri,contentValues,"id"+"='"+id+"'",null);
        Toast.makeText(context,"Öneri kaydı güncellemesi başarılı.", Toast.LENGTH_LONG).show();
        database.close();
    }

    public Cursor query(String s){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(s,null);
    }
    public boolean oneriDelete(int id) {
        SQLiteDatabase sdb = this.getWritableDatabase();
        sdb.execSQL("delete from "+TabloOneri+" where id="+id);
        Toast.makeText(context,"Öneri silme işlemi başarılı.", Toast.LENGTH_LONG).show();
        return true;
    }

}
