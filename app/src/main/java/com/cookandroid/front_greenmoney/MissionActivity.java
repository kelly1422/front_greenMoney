package com.cookandroid.front_greenmoney;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MissionActivity extends AppCompatActivity {

    private String BASE_URL;
    private RetrofitInterface retrofitInterface;
    private Retrofit retrofit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);

        BASE_URL = "https://greenmoney-340309.du.r.appspot.com";

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        HashMap<String, String> map = new HashMap<>();
        map.put("email", login_email.getText().toString());
        map.put("pw", login_pw.getText().toString());

        Call<List<MonthMission>> call = retrofitInterface.executeAllMission(map);
        call.enqueue(new Callback<List<MonthMission>>() {
            @Override
            public void onResponse(Call<List<Rank>> call, Response<List<Rank>> response) {
                ranking = response.body(); // 데이터 받음 List<Rank> ranking
                myRecyclerAdapter.setList(ranking);
                //Log.d("NickName", ranking.get(0).getScore());
//                for (int i=0 ; i <5 ; i++){
//                    Toast.makeText(RankActivity.this, ranking.get(i).getScore(), Toast.LENGTH_SHORT).show();
//                }
//                mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
//                myRecyclerAdapter = new MyRecyclerAdapter(ranking);
//                mRecyclerView.setAdapter(myRecyclerAdapter);
//                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//                mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
                MiniComparator comp = new MiniComparator();
                Collections.sort(ranking,comp);
            }
            @Override
            public void onFailure(Call<List<Rank>> call, Throwable t) {
                Toast.makeText(RankActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        for(int i = 0; i < 10; i++)
        {
            list.add("미션"+i+"");
        }
        Log.e(TAG, "onCreate: after");

        RecyclerView re = (RecyclerView)findViewById(R.id.recyclerView);
        MissionAdapter adapter = new MissionAdapter(list);
        re.setAdapter(adapter);

        Log.e(TAG, "onCreate: setting");

        re.setLayoutManager(new LinearLayoutManager(this));
        re.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
    }
}
