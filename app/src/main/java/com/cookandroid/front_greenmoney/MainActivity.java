package com.cookandroid.front_greenmoney;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNV;
    private FragmentManager fm;
    private FragmentTransaction ft;
    String isparents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent info = getIntent();
        isparents = info.getStringExtra("IsParent");

        mBottomNV = findViewById(R.id.nav_view);
        mBottomNV.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                BottomNavigate(item.getItemId());
                return true;
            }
        });
                mBottomNV.setSelectedItemId(R.id.navigation_1);
    }

    private void BottomNavigate(int id) {  //BottomNavigation 페이지 변경
        String tag = String.valueOf(id);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        Bundle bundle = new Bundle();

        Fragment currentFragment = fm.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            ft.hide(currentFragment);
        }

        Fragment fragment = fm.findFragmentByTag(tag);
        if (fragment == null) {
            if (id == R.id.navigation_1) {
                fragment = new FragmentPage1();
                bundle.putString("isparents", isparents);
                fragment.setArguments(bundle);

            } else if (id == R.id.navigation_2){

                fragment = new FragmentPage2();
            }else {
                fragment = new FragmentPage3();
            }
            ft.add(R.id.content_layout, fragment, tag);
        } else {
            ft.show(fragment);
        }

        ft.setPrimaryNavigationFragment(fragment);
        ft.setReorderingAllowed(true);
        ft.commitNow();
    }

}