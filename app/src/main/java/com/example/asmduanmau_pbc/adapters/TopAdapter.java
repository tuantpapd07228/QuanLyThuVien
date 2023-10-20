package com.example.asmduanmau_pbc.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmduanmau_pbc.R;
import com.example.asmduanmau_pbc.model.Top;

import java.util.ArrayList;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder> {
    ArrayList<Top> arr;

    public TopAdapter(ArrayList<Top> arr) {
        this.arr = arr;

    }

    @NonNull
    @Override
    public TopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_top_sach, parent , false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TopAdapter.ViewHolder holder, int position) {
        Top top = arr.get(position);
        if (top == null) return;
        holder.soluong.setText(top.getSoLuong()+"");
        holder.tensach.setText(top.getTenSach());
//        holder.up.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                update.xuli(top);
//            }
//        });
//        holder.del.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                xoa.xuli(top);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tensach, soluong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tensach = itemView.findViewById(R.id.top_tensach);
            soluong = itemView.findViewById(R.id.top_soluong);

        }
    }
}
