package com.example.asmduanmau_pbc.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asmduanmau_pbc.R;
import com.example.asmduanmau_pbc.adapters.PhieuMuonAdapter;
import com.example.asmduanmau_pbc.dao.PhieuMuonDAO;
import com.example.asmduanmau_pbc.dao.SachDAO;
import com.example.asmduanmau_pbc.dao.ThanhVienDAO;
import com.example.asmduanmau_pbc.dao.ThuThuDAO;
import com.example.asmduanmau_pbc.model.PhieuMuon;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;



public class PhieuMuonAddFragment extends BottomSheetDialogFragment {

    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    ThuThuDAO thuThuDAO;
     PhieuMuonDAO phieuMuonDAO;
     PhieuMuon phieuMuon;

     Spinner spnmatt, spnmatv, spnmasach;
     TextView ngaymuon, giatien;
     Button addpm;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    PhieuMuonAdapter.Xuli xuli;

    public PhieuMuonAddFragment(PhieuMuonAdapter.Xuli xuli) {
        this.xuli = xuli;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phieu_muon_add, container, false);
        sachDAO = new SachDAO(getContext());
        thanhVienDAO = new ThanhVienDAO(getContext());
        thuThuDAO = new ThuThuDAO(getContext());
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        spnmasach = v.findViewById(R.id.phieumuon_add_masach);
        spnmatt = v.findViewById(R.id.phieumuon_add_matt);
        spnmatv = v.findViewById(R.id.phieumuon_add_matv);
        ngaymuon = v.findViewById(R.id.phieumuon_add_ngay);
        giatien = v.findViewById(R.id.phieumuon_add_giatien);
        addpm =v.findViewById(R.id.phieumuon_add_btn);
        giatien.setEnabled(false);
        phieuMuon = new PhieuMuon();

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
            ngaymuon.setText(year+"/"+(month+1)+"/"+day);


        setdata();
        ngaymuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String ngay = i + "/" + (i1 + 1) + "/" + i2;
                        ngaymuon.setText(ngay);
                    }
                }, year , month, day);

                dialog.show();
            }
        });


        return v;
    }
    private void setdata(){
        ArrayList<String> mathuthu = new ArrayList<>();
        ArrayList<String> matv = new ArrayList<>();
        ArrayList<String> masach = new ArrayList<>();

        for (int i = 0; i < thuThuDAO.getALL().size(); i++) {
            mathuthu.add(thuThuDAO.getALL().get(i).getMaTT() + " - " + thuThuDAO.getALL().get(i).getHoTen());
        }
        spnmatt.setAdapter(new ArrayAdapter<String>(getContext() , androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, mathuthu));
        for (int i = 0; i < thanhVienDAO.getALL().size(); i++) {
            matv.add(thanhVienDAO.getALL().get(i).getMaTV() + " - " + thanhVienDAO.getALL().get(i).getHoTen());
        }
        for (int i = 0; i < sachDAO.getALL().size(); i++) {
            masach.add(sachDAO.getALL().get(i).getMaSach() + " - " + sachDAO.getALL().get(i).getTenSach());
        }
        ArrayAdapter adaptertt = new ArrayAdapter<String>(getContext() , androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, mathuthu);
        ArrayAdapter adaptertv = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, matv);
        ArrayAdapter adaptersach = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, masach);
        spnmatt.setAdapter(adaptertt);
        spnmatv.setAdapter(adaptertv);
        spnmasach.setAdapter(adaptersach);
        spnmasach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                giatien.setText(sachDAO.getALL().get(i).getGiaThue()+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        addpm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//                LocalDate date1 = LocalDate.parse(ngaymuon.getText().toString().trim(), formatter);
                Date ngay = null;
                try {
                    String ngaymuon1 = ngaymuon.getText().toString().trim();
                    ngay = sdf.parse(ngaymuon1);
                    Log.d("ngayyy" ,  ngay +" - " + ngaymuon.getText().toString().trim());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                phieuMuon.setMaTT(thuThuDAO.getALL().get(spnmatt.getSelectedItemPosition()).getMaTT());
                phieuMuon.setMaTV(thanhVienDAO.getALL().get(spnmatv.getSelectedItemPosition()).getMaTV());
                phieuMuon.setMaSach(sachDAO.getALL().get(spnmasach.getSelectedItemPosition()).getMaSach());
                phieuMuon.setTienThue(Integer.parseInt(giatien.getText().toString()));
                Log.d("ngayyy1111111" ,  ngay +" - " + ngaymuon.getText().toString().trim());
                phieuMuon.setNgay(ngay);
                add(phieuMuon);
            }
        });




    }
    private void add(PhieuMuon phieuMuon){
        try {
            phieuMuonDAO.insert(phieuMuon);
            Toast.makeText(getContext(), "Thêm phiếu mượn thành công", Toast.LENGTH_SHORT).show();
            xuli.xuli(null);
            dismiss();
        }catch (Exception e){
            Toast.makeText(getContext(), "Something wrong", Toast.LENGTH_SHORT).show();
        }
    }








}