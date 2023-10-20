package com.example.asmduanmau_pbc.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asmduanmau_pbc.R;
import com.example.asmduanmau_pbc.adapters.LoaiSachAdapter;
import com.example.asmduanmau_pbc.dao.LoaiSachDAO;
import com.example.asmduanmau_pbc.model.LoaiSach;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class LoaiSachUpdateBottomSheet extends BottomSheetDialogFragment {
    LoaiSachAdapter.XuLi xuLi;
    EditText edtenloaisach;
    Button update;
    LoaiSachDAO loaiSachDAO;
    LoaiSach loaiSach;

    public LoaiSachUpdateBottomSheet(LoaiSach loaiSach, LoaiSachAdapter.XuLi xuLi) {
        this.xuLi = xuLi;
        this.loaiSach =loaiSach;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_loai_sach_update_bottom_sheet, container, false);


        edtenloaisach = v.findViewById(R.id.loaisach_update_tenloai);
        update = v.findViewById(R.id.loaisach_updatebtn);
        loaiSachDAO = new LoaiSachDAO(getContext());
        edtenloaisach.setText(loaiSach.getTenLoai());
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String tenloai = edtenloaisach.getText().toString().trim();

                    if (tenloai != null && tenloai != ""){
                        loaiSach.setTenLoai(tenloai);
                        loaiSachDAO.update(loaiSach);
                        Toast.makeText(getContext(), "Update thành công", Toast.LENGTH_SHORT).show();
                        xuLi.xuli(null);
                        dismiss();
                    }else {
                        Toast.makeText(getContext(), "Kiểm tra lại thông tin đã nhập", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.e("atuan", e.getMessage());
                }
            }
        });











        return v;
    }
}