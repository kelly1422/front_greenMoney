package com.cookandroid.front_greenmoney;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MissionActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);

        ArrayList<String> list = null;
        list.add("hello");
        list.add("1");
        list.add("hello2");
        list.add("hello3");
        list.add("hello4");
        list.add("hello5");
        RecyclerView re = (RecyclerView)findViewById(R.id.recyclerView);
        MissionAdapter adapter = new MissionAdapter(list);
        re.setAdapter(adapter);
        re.setLayoutManager(new LinearLayoutManager(this));
        re.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
    }
}
