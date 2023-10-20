package com.example.asmduanmau_pbc.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asmduanmau_pbc.R;
import com.example.asmduanmau_pbc.adapters.LoaiSachAdapter;
import com.example.asmduanmau_pbc.adapters.ThanhVienAdapter;
import com.example.asmduanmau_pbc.dao.LoaiSachDAO;
import com.example.asmduanmau_pbc.dao.SachDAO;
import com.example.asmduanmau_pbc.model.LoaiSach;
import com.example.asmduanmau_pbc.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class LoaiSachFragment extends Fragment {

    LoaiSachDAO loaiSachDAO;
    RecyclerView r;

    LoaiSachAdapter adapter;
    ArrayList<LoaiSach> arr;
    SachDAO sachDAO;
    FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        r = v.findViewById(R.id.loaisach_recycleview);
        LinearLayoutManager l = new LinearLayoutManager(getContext());
        l.setOrientation(RecyclerView.VERTICAL);
        r.setLayoutManager(l);
        loaiSachDAO = new LoaiSachDAO(getContext());
        floatingActionButton = v.findViewById(R.id.loaisach_floatingbtn);
        sachDAO = new SachDAO(getContext());
        setData();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });

        return v;
    }
    private void add(){
        LoaiSachAddBottomsheet loaiSachAddBottomsheet = new LoaiSachAddBottomsheet(new LoaiSachAdapter.XuLi() {
            @Override
            public void xuli(Object obj) {
                setData();
            }
        });
        loaiSachAddBottomsheet.show(getChildFragmentManager(), loaiSachAddBottomsheet.getTag());
    }

    private void setData(){
        arr = (ArrayList<LoaiSach>) loaiSachDAO.getALL();
        adapter = new LoaiSachAdapter(arr, getContext(), new ThanhVienAdapter.XuLi() {
            @Override
            public void xuli(Object obj) {
                delete((LoaiSach) obj);
            }
        }, new ThanhVienAdapter.XuLi() {
            @Override
            public void xuli(Object obj) {
                update((LoaiSach) obj);
            }
        });
        r.setAdapter(adapter);
    }
    private void delete(LoaiSach loaiSach){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Bạn có muốn xóa "+loaiSach.getTenLoai()+" không?");
        //  Không
        builder.setNegativeButton("Không", null);
        ArrayList<Sach> arrayList = (ArrayList<Sach>) sachDAO.getALL();

        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                try {
                    for (int j = 0; j < arrayList.size(); j++) {
                            if (arrayList.get(j).getMaLoai() == loaiSach.getMaLoai()){
                                Toast.makeText(getContext(), "Thể loại đang được sử đụng, không thể xóa", Toast.LENGTH_SHORT).show();
                                return;
                            }
                    }
                    loaiSachDAO.delete(loaiSach.getMaLoai()+"");
                    setData();
                    Toast.makeText(getContext(), "Đã xóa "+loaiSach.getTenLoai(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getContext(), "Something wrong", Toast.LENGTH_SHORT).show();
                    Log.e("atuan arr" , e.getMessage());
                }
                
            }
        });
        builder.show();
    }
    private void update(LoaiSach loaiSach){
        LoaiSachUpdateBottomSheet loaiSachUpdateBottomSheet = new LoaiSachUpdateBottomSheet(loaiSach, new LoaiSachAdapter.XuLi() {
            @Override
            public void xuli(Object obj) {
                setData();
            }
        });
        loaiSachUpdateBottomSheet.show(getChildFragmentManager(), loaiSachUpdateBottomSheet.getTag());
    }
}