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

import java.util.ArrayList;

public class FragmentPage2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_mission, container, false);

        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0; i < 10; i++)
        {
            list.add("미션"+i+""); //서버에서 데이터 받아서 add
        }

        RecyclerView re = (RecyclerView)rootView.findViewById(R.id.recyclerView);
        MissionAdapter adapter = new MissionAdapter(list);
        re.setAdapter(adapter);
        re.setLayoutManager(new LinearLayoutManager(getActivity()));
        re.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));

        // 부모일때 미션승인만 보이기, 자식일때 미션완료만 보이기
        Button bt_c = (Button) rootView.findViewById(R.id.m_complete);
        Button bt_p = (Button) rootView.findViewById(R.id.m_success);
        //if(부모)
        // bt_c.visibility="gone";
        // else
        // bt_p.visibility="gone";


        return rootView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
