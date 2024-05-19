package com.example.aplikassetoran;

import com.google.gson.annotations.SerializedName;

public class HafalanSurah {

    @SerializedName("nama")
    private String surah;

    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("kelancaran")
    private String kelancaran;

    public HafalanSurah(String surah, String tanggal, String kelancaran) {
        this.surah = surah;
        this.tanggal = tanggal;
        this.kelancaran = kelancaran;
    }

    public String getSurah() {
        return surah;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getKelancaran() {
        return kelancaran;
    }
}
