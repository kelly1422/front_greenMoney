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

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        Bundle bundle = getArguments();
        String email = bundle.getString("email");
        String isParent = bundle.getString("isParent");


        HashMap<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("isParent", isParent);

        Call<List<MonthMission>> call = retrofitInterface.executeAllMission(map);

        call.enqueue(new Callback<List<MonthMission>>() {
            @Override
            public void onResponse(Call<List<MonthMission>> call, Response<List<MonthMission>> response) {
                arr = response.body();

            }

            @Override
            public void onFailure(Call<List<MonthMission>> call, Throwable t) {

            }
        });
        RecyclerView re = (RecyclerView)mission.findViewById(R.id.recyclerView);
        MissionAdapter adapter = new MissionAdapter();
        re.setAdapter(adapter);
        re.setLayoutManager(new LinearLayoutManager(getActivity()));
        re.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));

        // 부모일때 미션승인만 보이기, 자식일때 미션완료만 보이기
        Button bt_c = (Button) mission.findViewById(R.id.m_complete);
        Button bt_p = (Button) mission.findViewById(R.id.m_success);
        //if(부모)
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
