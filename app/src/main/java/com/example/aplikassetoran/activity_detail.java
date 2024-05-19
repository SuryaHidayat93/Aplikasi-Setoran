package com.example.aplikassetoran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class activity_detail extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HafalanSurahAdapter adapter;
    private List<HafalanSurah> hafalanSurahList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recyclerView = findViewById(R.id.recyclerViewHafalan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String nimMahasiswa = getIntent().getStringExtra("nim");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/setoran/") // Ganti dengan URL server lokal Anda
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<HafalanSurah>> call = apiService.getSurahMahasiswa(nimMahasiswa);

        call.enqueue(new Callback<List<HafalanSurah>>() {

            @Override
            public void onResponse(Call<List<HafalanSurah>> call, Response<List<HafalanSurah>> response) {
                if (response.isSuccessful()) {
                    hafalanSurahList = response.body();
                    Log.d("activity_detail", "Response: " + new Gson().toJson(hafalanSurahList));
                    if (hafalanSurahList != null && !hafalanSurahList.isEmpty()) {
                        adapter = new HafalanSurahAdapter(hafalanSurahList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.e("activity_detail", "No data found");
                    }
                } else {
                    Log.e("activity_detail", "Response not successful: " + response.errorBody());
                }
            }


            @Override
            public void onFailure(Call<List<HafalanSurah>> call, Throwable t) {
                Log.e("activity_detail", "Failed to get data: ", t);
            }
        });
    }
}
