package com.skripsi.dokterlele.Model;

import com.orm.SugarRecord;

public class History extends SugarRecord {
    public String waktu;
    public String penyakit;
    public int keterangan;
    public int solusi;

    public History() {
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getPenyakit() {
        return penyakit;
    }

    public void setPenyakit(String penyakit) {
        this.penyakit = penyakit;
    }

    public int getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(int keterangan) {
        this.keterangan = keterangan;
    }

    public int getSolusi() {
        return solusi;
    }

    public void setSolusi(int solusi) {
        this.solusi = solusi;
    }
}
