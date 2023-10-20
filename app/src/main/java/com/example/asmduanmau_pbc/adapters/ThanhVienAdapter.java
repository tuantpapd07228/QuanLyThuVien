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
import com.example.asmduanmau_pbc.model.ThanhVien;

import java.util.List;
import java.util.Objects;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder> {
    List<ThanhVien> arr;
    Context context;
    public interface XuLi{void xuli(Object obj);}
    XuLi xoa;
    XuLi update;

    public ThanhVienAdapter(List<ThanhVien> arr, Context context, XuLi xoa, XuLi update) {
        this.update = update;
        this.xoa = xoa;
        this.arr = arr;
        this.context = context;

    }

    @NonNull
    @Override
    public ThanhVienAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_thanh_vien, parent , false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ThanhVienAdapter.ViewHolder holder, int position) {
        ThanhVien t = arr.get(position);
        if (t == null) return;
        holder.name.setText(t.getHoTen());
        holder.year.setText(t.getNamSinh());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa.xuli(t);
                notifyDataSetChanged();
            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update.xuli(t);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (arr != null) return arr.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, year;
        ImageView delete, update;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.thanhvien_hoten);
            year = itemView.findViewById(R.id.thanhvien_namsinh);
            delete = itemView.findViewById(R.id.thanhvien_delete);
            update = itemView.findViewById(R.id.thanhvien_update);
        }
    }
}
