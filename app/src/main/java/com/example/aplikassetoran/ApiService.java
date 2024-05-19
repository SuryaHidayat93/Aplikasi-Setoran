package com.example.aplikassetoran;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("select_mahasiswa.php")
    Call<List<Mahasiswa>> getMahasiswa();

    @GET("get_surah_mahasiswa.php")
    Call<List<HafalanSurah>> getSurahMahasiswa(@Query("nim") String nim);
}
