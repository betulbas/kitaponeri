package com.betul.bas;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class ProfilList implements Parcelable {

    int kid;
    String isimSoyisim,ePosta,telefon,sifre;
    public ProfilList(int kid, String isimSoyisim, String ePosta, String telefon, String sifre)
    {
        this.kid = kid;
        this.isimSoyisim = isimSoyisim;
        this.ePosta = ePosta;
        this.telefon = telefon;
        this.sifre = sifre;
    }

    public int getKid() {
        return kid;
    }

    public String getIsimSoyisim() {
        return isimSoyisim;
    }

    public String getePosta() {
        return ePosta;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getSifre() {
        return sifre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
