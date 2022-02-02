package com.cookandroid.front_greenmoney;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MissionActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);

        ArrayList<String> list = new ArrayList<String>();

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
