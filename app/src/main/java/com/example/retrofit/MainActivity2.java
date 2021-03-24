package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.retrofit.models.Models;
import com.example.retrofit.models.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    private RecyclerView rvUserList;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rvUserList = findViewById(R.id.rvUserList);
        progressBar = findViewById(R.id.progressBar);

        final UserAdapter adapter = new UserAdapter();
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
        rvUserList.setAdapter(adapter);

        RetrofitInterface client = RetrofitClient.getClient().create(RetrofitInterface.class);
        client.fetchUsers(0).enqueue(new Callback<Models>() {
            @Override
            public void onResponse(Call<Models> call, Response<Models> response) {
                List<Result> mlist = response.body().getResults();
                progressBar.setVisibility(View.INVISIBLE);
                adapter.setData(mlist);
            }

            @Override
            public void onFailure(Call<Models> call, Throwable t) {
                Log.e("Main", t.toString());
            }
        });
    }
}