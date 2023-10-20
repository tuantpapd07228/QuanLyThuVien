package com.example.asmduanmau_pbc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmduanmau_pbc.R;
import com.example.asmduanmau_pbc.model.LoaiSach;

import java.util.List;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {
    List<LoaiSach> arr;
    Context context;
    public interface XuLi{void xuli(Object obj);}
    ThanhVienAdapter.XuLi xoa;
    ThanhVienAdapter.XuLi update;

    public LoaiSachAdapter(List<LoaiSach> arr, Context context, ThanhVienAdapter.XuLi xoa, ThanhVienAdapter.XuLi update) {
        this.arr = arr;
        this.context = context;
        this.xoa = xoa;
        this.update = update;
    }

    @NonNull
    @Override
    public LoaiSachAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_loaisach, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSachAdapter.ViewHolder holder, int position) {
        LoaiSach loaiSach = arr.get(position);
        if (loaiSach == null) return;
        holder.maloai.setText(loaiSach.getMaLoai()+"");
        holder.tenloai.setText(loaiSach.getTenLoai());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa.xuli(loaiSach);
            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update.xuli(loaiSach);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView maloai, tenloai;
        ImageView delete, update;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            maloai = itemView.findViewById(R.id.loaisach_maloai);
            tenloai = itemView.findViewById(R.id.loaisach_mtenloai);
            delete = itemView.findViewById(R.id.loaisach_delete);
            update = itemView.findViewById(R.id.loaisach_update);
        }
    }
}
