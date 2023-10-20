package com.example.asmduanmau_pbc.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmduanmau_pbc.R;
import com.example.asmduanmau_pbc.dao.LoaiSachDAO;
import com.example.asmduanmau_pbc.model.LoaiSach;
import com.example.asmduanmau_pbc.model.Sach;
import com.example.asmduanmau_pbc.model.ThanhVien;

import java.util.List;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder> {
    List<Sach> arr;
    Context context;
    public interface XuLi{void xuli(Object obj);}
    ThanhVienAdapter.XuLi xoa;
    ThanhVienAdapter.XuLi update;
    LoaiSachDAO dao;

    public SachAdapter(List<Sach> arr, Context context, ThanhVienAdapter.XuLi xoa, ThanhVienAdapter.XuLi update) {
        this.arr = arr;
        this.context = context;
        this.xoa = xoa;
        this.update = update;
        dao = new LoaiSachDAO(context);
    }

    @NonNull
    @Override
    public SachAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachAdapter.ViewHolder holder, int position) {
        Sach s = arr.get(position);
        if (s == null) return;

        LoaiSach loaiSach = dao.getById(String.valueOf(s.getMaLoai()));
        holder.masach.setText(String.valueOf(s.getMaSach()));
        holder.tensach.setText(s.getTenSach());
        holder.theloai.setText(loaiSach.getTenLoai());
        holder.giasach.setText(s.getGiaThue()+"");
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa.xuli(s);
            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update.xuli(s);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tensach, giasach, theloai, masach;
        ImageView delete, update;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tensach = itemView.findViewById(R.id.sach_tensach);
            giasach = itemView.findViewById(R.id.sach_giathue);
            theloai = itemView.findViewById(R.id.sach_loaisach);
            masach = itemView.findViewById(R.id.sach_masach);
            delete = itemView.findViewById(R.id.sach_delete);
            update = itemView.findViewById(R.id.sach_update);
        }
    }
}
