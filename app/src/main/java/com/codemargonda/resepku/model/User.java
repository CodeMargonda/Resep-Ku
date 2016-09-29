package com.codemargonda.resepku.model;

/**
 * Created by Jefri Yushendri on 29/9/2016.
 */

public class User {

    String nama, email, password;

    public User(){

    }

    public String getNama(){
        return this.nama;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
