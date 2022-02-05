package com.cookandroid.front_greenmoney;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HEAD;

public class FragmentPage2 extends Fragment {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL;

    private List<MonthMission> arr = new ArrayList<MonthMission>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup mission = (ViewGroup) inflater.inflate(R.layout.activity_mission, container, false);

        BASE_URL = "https://greenmoney-340309.du.r.appspot.com";
        Bundle bundle = getArguments();
        String token = bundle.getString("token");

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);


        MonthMission a = new MonthMission();
        a.setExistMonth("2021년 10월");
        MonthInfo b = new MonthInfo();
        b.setCompleted("10 / 13");
        b.setMoney(10000);
        a.setMonthInfo(b);
        arr.add(a);

        MonthMission a2 = new MonthMission();
        a2.setExistMonth("2021년 11월");
        MonthInfo b2 = new MonthInfo();
        b2.setCompleted("7 / 10");
        b2.setMoney(7000);
        a2.setMonthInfo(b2);
        arr.add(a2);

        MonthMission a3 = new MonthMission();
        a3.setExistMonth("2021년 12월");
        MonthInfo b3 = new MonthInfo();
        b3.setCompleted("20 / 20");
        b3.setMoney(40000);
        a3.setMonthInfo(b3);
        arr.add(a3);

        MonthMission a4 = new MonthMission();
        a4.setExistMonth("2022년 01월");
        MonthInfo b4 = new MonthInfo();
        b4.setCompleted("6 / 15");
        b4.setMoney(5000);
        a4.setMonthInfo(b4);
        arr.add(a4);

        MonthMission a5 = new MonthMission();
        a5.setExistMonth("2021년 10월");
        MonthInfo b5 = new MonthInfo();
        b5.setCompleted("22 / 25");
        b5.setMoney(25000);
        a5.setMonthInfo(b5);
        arr.add(a5);

        Log.d(TAG, "token!!!!~~~ "+ token);

        HashMap<String, String> map = new HashMap<>();

        map.put("token", token);
        Call<List<MonthMission>> call;
        call = retrofitInterface.executeAllMission(map);

        call.enqueue(new Callback<List<MonthMission>>() {
            @Override
            public void onResponse(Call<List<MonthMission>> call, Response<List<MonthMission>> response) {
                arr = response.body();
                Log.d(TAG, "onResponse:  왜 이래");
            }

            @Override
            public void onFailure(Call<List<MonthMission>> call, Throwable t) {
                Log.d(TAG, "onResponse: 시이댕 ");
            }
        });

        RecyclerView re = (RecyclerView)mission.findViewById(R.id.recyclerView);
        MissionAdapter adapter = new MissionAdapter(arr);
        re.setAdapter(adapter);
        re.setLayoutManager(new LinearLayoutManager(getActivity()));
        re.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));

        // 부모일때 미션승인만 보이기, 자식일때 미션완료만 보이기
        // Button bt_c = (Button) mission.findViewById(R.id.m_complete);
        // Button bt_p = (Button) mission.findViewById(R.id.m_success);
        // if(부모)
        // bt_c.visibility="gone";
        // else
        // bt_p.visibility="gone";
        return mission;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
