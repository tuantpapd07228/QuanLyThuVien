package com.example.asmduanmau_pbc.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asmduanmau_pbc.R;
import com.example.asmduanmau_pbc.adapters.ThanhVienAdapter;
import com.example.asmduanmau_pbc.dao.ThanhVienDAO;
import com.example.asmduanmau_pbc.model.ThanhVien;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ThanhVienAddFragment extends BottomSheetDialogFragment {
    ThanhVienDAO dao;
    EditText edhoten;
    EditText ednamsinh;
    Button add;
    ThanhVienAdapter.XuLi xuLi;

    public ThanhVienAddFragment(ThanhVienAdapter.XuLi xuLi) {
        this.xuLi = xuLi;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        dao = new ThanhVienDAO(getContext());
        add = v.findViewById(R.id.thanhvien_bottomsheet_add);
        edhoten = v.findViewById(R.id.thanhvien_bottomsheet_hoten);
        ednamsinh = v.findViewById(R.id.thanhvien_bottomsheet_namsinh);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten = edhoten.getText().toString().trim();
                String namsinh = ednamsinh.getText().toString().trim();
                if (hoten != null && namsinh != null){
                    ThanhVien t = new ThanhVien(0, hoten, namsinh);
                    dao.insert(t);
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    xuLi.xuli(t);
                    dismiss();

                }else{
                    Toast.makeText(getContext(), "Chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return v;
    }
}