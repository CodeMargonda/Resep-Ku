package com.codemargonda.resepku.model;

/**
 * Created by Jefri Yushendri on 19/9/2016.
 */
public class Resep {

    //private variables
    int _id;
    String _nama;
    String _deskripsi;
    byte[] _gambar;

    // Empty constructor
    public Resep(){

    }
    // constructor
    public Resep(int id, String nama, String _deskripsi, byte[] _gambar){
        this._id = id;
        this._nama = nama;
        this._deskripsi = _deskripsi;
        this._gambar = _gambar;
    }

    // constructor
    public Resep(String nama, String _deskripsi){
        this._nama = nama;
        this._deskripsi = _deskripsi;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting nama
    public String getNama(){
        return this._nama;
    }

    // setting nama
    public void setNama(String nama){
        this._nama = nama;
    }

    // getting deskripsi
    public String getDeskripsi(){
        return this._deskripsi;
    }

    // setting deskripsi
    public void setDeskripsi(String deskripsi){
        this._deskripsi = deskripsi;
    }


    // getting gambar
    public byte[] getGambar(){
        return this._gambar;
    }

    // setting gambar
    public void setGambar(byte[] gambar){
        this._gambar = gambar;
    }
}