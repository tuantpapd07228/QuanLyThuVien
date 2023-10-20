package com.example.asmduanmau_pbc.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asmduanmau_pbc.R;
import com.example.asmduanmau_pbc.adapters.SachAdapter;
import com.example.asmduanmau_pbc.adapters.ThanhVienAdapter;
import com.example.asmduanmau_pbc.dao.PhieuMuonDAO;
import com.example.asmduanmau_pbc.dao.SachDAO;
import com.example.asmduanmau_pbc.model.Sach;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class SachFragment extends Fragment {
    ArrayList<Sach> arr;
    SachDAO dao;
    SachAdapter adapter;
    RecyclerView r;
    FloatingActionButton f;
    SachUpdateFragment sachUpdateFragment;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_sach, container, false);

        dao = new SachDAO(getContext());
        LinearLayoutManager l = new LinearLayoutManager(getContext());
        l.setOrientation(RecyclerView.VERTICAL);
        r = v.findViewById(R.id.sach_recycle);
        f = v.findViewById(R.id.sach_floatingbtn);
        r.setLayoutManager(l);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
        setdata();

        return v;
    }



    private void setdata(){
        arr = (ArrayList<Sach>) dao.getALL();
        adapter = new SachAdapter(arr, getContext(), new ThanhVienAdapter.XuLi() {
            @Override
            public void xuli(Object obj) {
                //xoa
                delete((Sach) obj);
            }
        }, new ThanhVienAdapter.XuLi() {
            @Override
            public void xuli(Object obj) {
                //update
                update((Sach) obj);
            }
        });
        r.setAdapter(adapter);
    }
    private void update(Sach sach){
        sachUpdateFragment = new SachUpdateFragment(sach, new SachUpdateFragment.SachUpdate() {
            @Override
            public void xuli() {
                setdata();
            }
        });
        sachUpdateFragment.show(getChildFragmentManager(), sachUpdateFragment.getTag());

    }
    private void delete(Sach s){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Bạn có muốn xóa "+s.getTenSach()+" không?");
        //  Không
        builder.setNegativeButton("Không", null);
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getContext());
                for (int j = 0; j < phieuMuonDAO.getALL().size(); j++) {
                    if (phieuMuonDAO.getALL().get(j).getMaSach() == s.getMaSach()){
                        Toast.makeText(getContext(), "Sách đang được sử dụng trong phiếu mượn không thể xóa!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                dao.delete(s.getMaSach()+"");
                setdata();
                Toast.makeText(getContext(), "Đã xóa "+s.getTenSach(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
    private void add(){
        SachAddFragment sachAddFragment = new SachAddFragment(new SachAdapter.XuLi() {
            @Override
            public void xuli(Object obj) {
                setdata();
            }
        });
        sachAddFragment.show(getChildFragmentManager(), sachAddFragment.getTag());
    }
}