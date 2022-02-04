package com.cookandroid.front_greenmoney;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.MissonViewHolder> {

    private ArrayList<MonthMission> arrMission;

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
    MissionAdapter(ArrayList<MonthMission> list) {
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

//    public void setList(List<String> list){
//        this.mList = list;
//        i = 1;
//        notifyDataSetChanged();
//    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull MissionAdapter.MissonViewHolder holder, int position) {
        String month = arrMission.get(position).getMonth();
        MonthInfo monthinfo = arrMission.get(position).getMonthInfo();
        MissonViewHolder MissonHolder = (MissonViewHolder) holder;
        MissonHolder.month.setText(month);
        MissonHolder.completed.setText(monthinfo.getCompleted());
        MissonHolder.money.setText(monthinfo.getMoney());
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return arrMission.size() ;
    }
}
