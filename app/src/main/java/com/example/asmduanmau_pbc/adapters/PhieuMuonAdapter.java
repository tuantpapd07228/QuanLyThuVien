package com.example.asmduanmau_pbc.adapters;

import static com.example.asmduanmau_pbc.R.color.green;
import static com.example.asmduanmau_pbc.R.color.red;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmduanmau_pbc.R;
import com.example.asmduanmau_pbc.model.PhieuMuon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolDer> {
    ArrayList<PhieuMuon> arr;
    public interface Xuli{void xuli(Object obj);}
    Xuli xoa, update;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public PhieuMuonAdapter(ArrayList<PhieuMuon> arr, Xuli xoa, Xuli update) {
        this.arr = arr;
        this.xoa = xoa;
        this.update = update;
    }

    @NonNull
    @Override
    public PhieuMuonAdapter.ViewHolDer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_phieumuon, parent, false);
        return new ViewHolDer(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuMuonAdapter.ViewHolDer holder, int position) {
        PhieuMuon pm = arr.get(position);
        if (pm == null) return;
        String ngay = sdf.format(pm.getNgay());
        holder.mapm.setText(pm.getMaPM()+"");
        holder.matt.setText(pm.getMaTT()+"");
        holder.masach.setText(pm.getMaSach()+"");
        holder.matv.setText(pm.getMaTV()+"");
        if (pm.getStattus() == 1){
            holder.status.setText("Đã trả");
            holder.status.setTextColor(R.color.green);
        }else{
            holder.status.setText("Chưa trả");
            holder.status.setTextColor(R.color.red);
        }
        try {
            holder.ngay.setText(ngay);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa.xuli(pm);
            }
        });
        holder.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update.xuli(pm);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolDer extends RecyclerView.ViewHolder {
        TextView mapm, matt, matv, masach, ngay, status;
        ImageView del, up;

        public ViewHolDer(@NonNull View itemView) {
            super(itemView);
            mapm = itemView.findViewById(R.id.phieumuon_maphieumuon);
            matt = itemView.findViewById(R.id.phieumuon_mapthuthu);
            matv = itemView.findViewById(R.id.phieumuon_mathanhvien);
            masach = itemView.findViewById(R.id.phieumuon_masach);
            ngay = itemView.findViewById(R.id.phieumuon_ngaumuon);
            status = itemView.findViewById(R.id.phieumuon_status);
            del = itemView.findViewById(R.id.phieumuon_delete);
            up = itemView.findViewById(R.id.phieumuon_update);
        }
    }
}
