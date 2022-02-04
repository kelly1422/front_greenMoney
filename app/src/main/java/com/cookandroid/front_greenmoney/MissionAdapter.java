package com.cookandroid.front_greenmoney;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.MissonViewHolder> {

    private List<MonthMission> arrMission = new ArrayList<MonthMission>();

    public class MissonViewHolder extends RecyclerView.ViewHolder {
        TextView month;
        TextView completed;
        TextView money;

        MissonViewHolder(View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            month = itemView.findViewById(R.id.month);
            completed = itemView.findViewById(R.id.completed);
            money = itemView.findViewById(R.id.money);
        }
    }

    // 생성자에서 텍스트 리스트 객체를 전달받음.
    MissionAdapter(List<MonthMission> list) {
        Log.d(TAG, "MissionAdapter: 어댑터 만듬...");
        int a = getItemCount();
        Log.d(TAG, "아이템개수 " + a);
        arrMission = list;
    }

    @NonNull
    @Override
    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    public MissionAdapter.MissonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.month_mission_item, parent, false);
        MissionAdapter.MissonViewHolder vh = new MissionAdapter.MissonViewHolder(view);
        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull MissionAdapter.MissonViewHolder holder, int position) {
        String month = arrMission.get(position).getExistMonth();
        MonthInfo monthinfo = arrMission.get(position).getMonthInfo();
        MissonViewHolder MissonHolder = (MissonViewHolder) holder;
        MissonHolder.month.setText(month);
        MissonHolder.completed.setText(monthinfo.getCompleted());
        MissonHolder.money.setText(monthinfo.getMoney().toString());
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return arrMission.size() ;
    }
}