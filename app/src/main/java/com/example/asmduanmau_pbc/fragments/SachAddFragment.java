package com.example.asmduanmau_pbc.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asmduanmau_pbc.R;
import com.example.asmduanmau_pbc.adapters.SachAdapter;
import com.example.asmduanmau_pbc.dao.LoaiSachDAO;
import com.example.asmduanmau_pbc.dao.SachDAO;
import com.example.asmduanmau_pbc.model.Sach;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;


public class SachAddFragment extends BottomSheetDialogFragment {
    EditText edtensach, edgiathue;
    Spinner spn;
    Button add;
    LoaiSachDAO loaiSachDAO;
    SachDAO sachDAO;
    ArrayList<String> arr;
    SachAdapter.XuLi xuLi;

    public SachAddFragment(SachAdapter.XuLi xuLi) {
        this.xuLi = xuLi;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_sach_add, container, false);
        edtensach = v.findViewById(R.id.sach_add_tensach);
        edgiathue = v.findViewById(R.id.sach_add_giathue);
        spn = v.findViewById(R.id.sach_add_spn);
        add = v.findViewById(R.id.sach_add_btn);
        loaiSachDAO = new LoaiSachDAO(getContext());
        sachDAO = new SachDAO(getContext());
        arr = new ArrayList<>();
        for (int i = 0; i < loaiSachDAO.getALL().size(); i++) {
            arr.add(loaiSachDAO.getALL().get(i).getMaLoai() + " - " +loaiSachDAO.getALL().get(i).getTenLoai() );
        }
        Adapter adapter = new ArrayAdapter<String>(getContext(),  androidx.appcompat.R.layout.support_simple_spinner_dropdown_item ,arr);
        spn.setAdapter((SpinnerAdapter) adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String tensach = edtensach.getText().toString().trim();
                    String giathue = edgiathue.getText().toString().trim();
                    int maloai = loaiSachDAO.getALL().get(spn.getSelectedItemPosition()).getMaLoai();
                    if (tensach != "" && giathue != ""){
                        sachDAO.insert(new Sach(0, tensach, Integer.parseInt(giathue) , maloai));
                        Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                        xuLi.xuli(null);
                        dismiss();
                        // Đã có lỗi xảy ra
                    } else {
                        Toast.makeText(getContext(), "Đã có lỗi xảy ra  ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.d("atuan", e.getMessage());
                }
            }
        });


        return v;
    }
}