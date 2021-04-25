package com.betul.bas;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class OneriList implements Parcelable {
    String turu,ismi,yayinYili,sayfaSayisi,imdbPuani,not;
    int id;
    public OneriList(int id,String turu, String ismi, String yayinYili, String sayfaSayisi, String imdbPuani
            , String not) {
        this.id = id;
        this.turu = turu;
        this.ismi = ismi;
        this.yayinYili = yayinYili;
        this.sayfaSayisi = sayfaSayisi;
        this.imdbPuani = imdbPuani;
        this.not = not;
    }

    public String getTuru() {
        return turu;
    }

    public String getIsmi() {
        return ismi;
    }

    public String getYayinYili() {
        return yayinYili;
    }

    public String getSayfaSayisi() {
        return sayfaSayisi;
    }

    public String getImdbPuani() {
        return imdbPuani;
    }

    public String getNot() {
        return not;
    }

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
