package com.example.aplikassetoran;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aplikassetoran.ApiService;
import com.example.aplikassetoran.Mahasiswa;
import com.example.aplikassetoran.MahasiswaAdapter;
import java.security.cert.CertificateException;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewMahasiswa);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Konfigurasi TrustManager
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };

        // Install TrustManager ke SSLContext
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (Exception e) {
            Log.e("MainActivity", "Error creating SSL context: " + e.getMessage());
        }

        // Buat OkHttpClient dengan TrustManager yang dikonfigurasi
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
                .build();

        // Buat Retrofit dengan OkHttpClient yang disesuaikan
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/Setoran/") // Ganti dengan alamat IP lokal Anda
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Membuat instance ApiService
        ApiService apiService = retrofit.create(ApiService.class);

        // Melakukan panggilan ke server untuk mendapatkan data mahasiswa
        Call<List<Mahasiswa>> call = apiService.getMahasiswa();
        call.enqueue(new Callback<List<Mahasiswa>>() {
            @Override
            public void onResponse(Call<List<Mahasiswa>> call, Response<List<Mahasiswa>> response) {
                if (response.isSuccessful()) {
                    List<Mahasiswa> mahasiswaList = response.body();

                    // Menginisialisasi adapter dan mengatur data ke RecyclerView
                    adapter = new MahasiswaAdapter(MainActivity.this, mahasiswaList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("MainActivity", "Gagal mendapatkan data mahasiswa: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Mahasiswa>> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
            }
        });
    }
}