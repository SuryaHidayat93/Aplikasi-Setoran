package com.example.aplikassetoran;

import com.google.gson.annotations.SerializedName;

public class Mahasiswa {
    @SerializedName("NIM")
    private String nim;

    @SerializedName("Nama")
    private String nama;

    @SerializedName("Semester")
    private int semester;

    public Mahasiswa(String nim, String nama, int semester) {
        this.nim = nim;
        this.nama = nama;
        this.semester = semester;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public int getSemester() {
        return semester;
    }
}
