package com.example.loginretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginretrofit.model.Model;
import com.example.loginretrofit.model.ResultModel;
import com.example.loginretrofit.session_manager.SessionManager;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText Eusername, Epassword;
    Button btnLogin;
    SessionManager sessionManager;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Eusername = findViewById(R.id.edtusername);
        Epassword = findViewById(R.id.edtpassword);
        sessionManager = new SessionManager(getApplicationContext());

        if (sessionManager.isLoggedin()) {

            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
        }


        btnLogin = findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = Eusername.getText().toString().trim();
                final String password = Epassword.getText().toString().trim();

                loginUser(username, password);
            }
        });


    }

    private void loginUser(String username, String password) {



        Model m = new Model("cgokkgoocs0owog0kwkkk4kw40ks004c0gwcgso4", username, password);
        String s = new Gson().toJson(m);


        Log.e(TAG,s);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<String> call = api.getUserLogin(m);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e(TAG, "hit here");
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.e("Response Body : ", response.body() + "");
                        String datas=response.body().toString();
                        ResultModel rm= new Gson().fromJson(datas,ResultModel.class);
                        if(rm.getResult()){
                            sessionManager.setLogin(true);
                            Intent i=new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(i);
                            finish();

                        }
                        Log.e("data",rm+"");
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("fail", t + "");

            }
        });

    }

    protected void onDestroy() {
        super.onDestroy();
    }

}

