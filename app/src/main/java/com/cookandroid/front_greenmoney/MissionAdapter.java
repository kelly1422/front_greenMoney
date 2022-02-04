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

    private ArrayList<MonthMission> arr = null;

    public class MissonViewHolder extends RecyclerView.ViewHolder {
        TextView txView1;

        MissonViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            txView1 = itemView.findViewById(R.id.m_tx);
        }
    }

    // 생성자에서 텍스트 리스트 객체를 전달받음.
    MissionAdapter(ArrayList<MonthMission> list) {
        arr = list;
    }

    @NonNull
    @Override
    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    public MissionAdapter.MissonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.mission_item, parent, false);
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
        String text = arr.get(position);
        MissonViewHolder MissonHolder = (MissonViewHolder) holder;
        MissonHolder.txView1.setText(text);
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mText.size() ;
    }
}
