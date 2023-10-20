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
import android.widget.Toast;

import com.example.asmduanmau_pbc.R;
import com.example.asmduanmau_pbc.adapters.SachAdapter;
import com.example.asmduanmau_pbc.dao.LoaiSachDAO;
import com.example.asmduanmau_pbc.dao.SachDAO;
import com.example.asmduanmau_pbc.dao.ThanhVienDAO;
import com.example.asmduanmau_pbc.model.Sach;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;


public class SachUpdateFragment extends BottomSheetDialogFragment {

    SachDAO sachDAO;
    EditText edtensach, edgiathue;
    Button update;
    Sach sach;
    LoaiSachDAO loaiSachDAO;
    Spinner spn;
    public interface SachUpdate{void xuli();}
    SachUpdate s;
    ArrayList<String> arr;
    public SachUpdateFragment(Sach sach, SachUpdate s) {
        this.sach = sach;
        this.s = s;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sach_update, container, false);
        update = v.findViewById(R.id.sach_update_btn);
        edtensach = v.findViewById(R.id.sach_update_tensach);
        edgiathue = v.findViewById(R.id.sach_update_giathue);
        spn = v.findViewById(R.id.sach_update_spn);
//        edmaloai.setEnabled(false);
        edgiathue.setText(sach.getGiaThue()+"");
        edtensach.setText(sach.getTenSach());
        sachDAO = new SachDAO(getContext());
        loaiSachDAO = new LoaiSachDAO(getContext());
        arr = new ArrayList<>();
        for (int i = 0; i < loaiSachDAO.getALL().size(); i++) {
            arr.add(loaiSachDAO.getALL().get(i).getMaLoai() + " - " +loaiSachDAO.getALL().get(i).getTenLoai() );
        }
        Adapter adapter = new ArrayAdapter<String>(getContext(),  androidx.appcompat.R.layout.support_simple_spinner_dropdown_item ,arr);
        spn.setAdapter((SpinnerAdapter) adapter);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String tensach = edtensach.getText().toString().trim();
                    String giathue = edgiathue.getText().toString().trim();
                    int maloai = loaiSachDAO.getALL().get(spn.getSelectedItemPosition()).getMaLoai();
                    if (tensach != null && giathue != null  ){

                        sach.setTenSach(tensach);
                        sach.setGiaThue(Integer.parseInt(giathue));
                        sach.setMaLoai(maloai);
                        sachDAO.update(sach);
                        Toast.makeText(getContext(), "Đã update", Toast.LENGTH_SHORT).show();
                        s.xuli();
                        dismiss();
                    }else{
                        Toast.makeText(getContext(), "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.e("atuan", e.getMessage());
                }
            }
        });



//
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    String tensach = edtensach.getText().toString().trim();
//                    String giathue = edgiathue.getText().toString().trim();
//
//                    if (tensach != "" && giathue != ""){
//                        sachDAO.insert(new Sach(0, tensach, Integer.parseInt(giathue) , maloai));
//                        Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
//                        xuLi.xuli(null);
//                        dismiss();
//                        // Đã có lỗi xảy ra
//                    } else {
//                        Toast.makeText(getContext(), "Đã có lỗi xảy ra  ", Toast.LENGTH_SHORT).show();
//                    }
//                }catch (Exception e){
//                    Log.d("atuan", e.getMessage());
//                }
//            }
//        });
        return v;
    }
}