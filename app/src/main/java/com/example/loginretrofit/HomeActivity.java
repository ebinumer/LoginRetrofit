package com.example.loginretrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.loginretrofit.list_adapter.ListViewAdapter;
import com.example.loginretrofit.model.ProdectReqModel;
import com.example.loginretrofit.model.ProdectRetriveModel;
import com.example.loginretrofit.session_manager.SessionManager;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HomeActivity extends AppCompatActivity {

    SessionManager sessionManager;
    ProgressDialog progressDialog;
    private List<ProdectRetriveModel> arrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListViewAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white));

        sessionManager = new SessionManager(getApplicationContext());
        progressDialog = new ProgressDialog(this);

        recyclerView = findViewById(R.id.recycler_view);
        mAdapter = new ListViewAdapter(this, arrayList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        Loaddata();


    }

    private void Loaddata() {

        ProdectReqModel pm = new ProdectReqModel();
        pm.setApi_key("cgokkgoocs0owog0kwkkk4kw40ks004c0gwcgso4");
        pm.setSlug("wedding-shall");
        pm.setStart("0");
        pm.setLimit("10");
        pm.setOrder("");
        pm.setOrder_by("");

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Content-Type", "application/json");

        Log.e("Header Params : ", new Gson().toJson(hashMap));
        Log.e("Param Pass : ", new Gson().toJson(pm));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        final Api api = retrofit.create(Api.class);
        Call<String> call = api.getProdectList(hashMap, pm);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("response", response + "");
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.e("Reponse Body : ", response.body());
                        String rtrivdata = response.body();
                        ProdectRetriveModel prm = new Gson().fromJson(rtrivdata, ProdectRetriveModel.class);
                        Log.e("prm", prm + "");
                        arrayList.addAll(prm.getProducts());
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("Error", t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        progressDialog = new ProgressDialog(HomeActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mlogout) {
            sessionManager.setLogin(false);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }


    protected void onDestroy() {
        super.onDestroy();


    }
}
