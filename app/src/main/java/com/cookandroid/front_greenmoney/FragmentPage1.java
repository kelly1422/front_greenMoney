package com.cookandroid.front_greenmoney;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.airbnb.lottie.LottieAnimationView;

public class FragmentPage1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup Tree_view = (ViewGroup) inflater.inflate(R.layout.activity_tree, container, false);
        TextView tx1 = (TextView) Tree_view.findViewById(R.id.ac_name);
        Bundle bundle = getArguments();
        String token = bundle.getString("token");
        String isParent = bundle.getString("isParent");

        tx1.setText(token + "님의 잔고:");
        return Tree_view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
