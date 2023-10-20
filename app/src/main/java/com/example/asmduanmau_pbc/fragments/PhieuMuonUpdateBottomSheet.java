package com.example.asmduanmau_pbc.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

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


public class PhieuMuonUpdateBottomSheet extends BottomSheetDialogFragment {

    SachDAO sachDAO;
    String TAG ="atuan";
    ThanhVienDAO thanhVienDAO;
    ThuThuDAO thuThuDAO;
    PhieuMuonDAO phieuMuonDAO;
    PhieuMuon phieuMuon;

    Spinner spnmatt, spnmatv, spnmaSach, spnstatus;
    TextView ngaymuon, giatien;
    Button updatebtn;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    PhieuMuonAdapter.Xuli xuli;

    public PhieuMuonUpdateBottomSheet(PhieuMuon phieuMuon, PhieuMuonAdapter.Xuli xuli) {
        this.phieuMuon = phieuMuon;
        this.xuli = xuli;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_phieu_muon_update_bottom_sheet, container, false);

        sachDAO = new SachDAO(getContext());
        thanhVienDAO = new ThanhVienDAO(getContext());
        thuThuDAO = new ThuThuDAO(getContext());
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        spnmaSach = v.findViewById(R.id.phieumuon_update_masach);
        spnmatt = v.findViewById(R.id.phieumuon_update_matt);
        spnmatv = v.findViewById(R.id.phieumuon_update_matv);
        ngaymuon = v.findViewById(R.id.phieumuon_update_ngay);
        giatien = v.findViewById(R.id.phieumuon_update_giatien);
        updatebtn = v.findViewById(R.id.phieumuon_update_btn);
        spnstatus =v.findViewById(R.id.phieumuon_update_trangthai);
        giatien.setEnabled(false);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String date = sdf.format(phieuMuon.getNgay());
        ngaymuon.setText(date);

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

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date ngay = null;
                try {
                    String ngaymuon1 = ngaymuon.getText().toString().trim();
                    ngay = sdf.parse(ngaymuon1);
                } catch (ParseException e) {
                    Log.d("atuan err" ,  e.getMessage());
                }
                phieuMuon.setMaTT(thuThuDAO.getALL().get(spnmatt.getSelectedItemPosition()).getMaTT());
                phieuMuon.setMaTV(thanhVienDAO.getALL().get(spnmatv.getSelectedItemPosition()).getMaTV());
                phieuMuon.setMaSach(sachDAO.getALL().get(spnmaSach.getSelectedItemPosition()).getMaSach());
                phieuMuon.setTienThue(Integer.parseInt(giatien.getText().toString()));
                if (spnstatus.getSelectedItemPosition() == 1){
                    phieuMuon.setStattus(1);
                }else {
                    phieuMuon.setStattus(0);
                }
                phieuMuon.setNgay(ngay);
                update(phieuMuon);
            }
        });
        return v;
    }
    private void setdata(){
        ArrayList<String> mathuthu = new ArrayList<>();
        ArrayList<String> matv = new ArrayList<>();
        ArrayList<String> masach = new ArrayList<>();
        ArrayList<String> arrstatus = new ArrayList<>();
        arrstatus.add("Chưa trả");
        arrstatus.add("Đã trả");
        for (int i = 0; i < thuThuDAO.getALL().size(); i++) {
            mathuthu.add(thuThuDAO.getALL().get(i).getMaTT() + " - " + thuThuDAO.getALL().get(i).getHoTen());
            if (thuThuDAO.getALL().get(i).getMaTT().equals(phieuMuon.getMaTT())){
                spnmatt.setSelection(i);

            }
        }
        spnmatt.setAdapter(new ArrayAdapter<String>(getContext() , androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, mathuthu));
        for (int i = 0; i < thanhVienDAO.getALL().size(); i++) {
            matv.add(thanhVienDAO.getALL().get(i).getMaTV() + " - " + thanhVienDAO.getALL().get(i).getHoTen());
            if (thanhVienDAO.getALL().get(i).getMaTV() == phieuMuon.getMaTV()){
                spnmatv.setSelection(i);

            }
        }
        for (int i = 0; i < sachDAO.getALL().size(); i++) {
            masach.add(sachDAO.getALL().get(i).getMaSach() + " - " + sachDAO.getALL().get(i).getTenSach());
            if (sachDAO.getALL().get(i).getMaSach() == phieuMuon.getMaSach()){
                spnmaSach.setSelection(i);

            }
        }

        ArrayAdapter adaptertt = new ArrayAdapter<String>(getContext() , androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, mathuthu);
        ArrayAdapter adaptertv = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, matv);
        ArrayAdapter adaptersach = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, masach);
        spnstatus.setAdapter(new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrstatus));
        spnstatus.setSelection(phieuMuon.getStattus());

        spnmatt.setAdapter(adaptertt);
        spnmatv.setAdapter(adaptertv);
        spnmaSach.setAdapter(adaptersach);
        giatien.setText(phieuMuon.getTienThue()+"");

        for (int i = 0; i < thuThuDAO.getALL().size(); i++) {
            if (thuThuDAO.getALL().get(i).getMaTT().equals(phieuMuon.getMaTT())){
                spnmatt.setSelection(i);
                break;
            }
        }
        for (int i = 0; i < thanhVienDAO.getALL().size(); i++) {
            if (thanhVienDAO.getALL().get(i).getMaTV() == phieuMuon.getMaTV()){
                spnmatv.setSelection(i);
                break;
            }
        }
        for (int i = 0; i < sachDAO.getALL().size(); i++) {
            if (sachDAO.getALL().get(i).getMaSach() == phieuMuon.getMaSach()){
                spnmaSach.setSelection(i);
                break;
            }
        }
        spnmaSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                giatien.setText(sachDAO.getALL().get(i).getGiaThue()+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        if (phieuMuon.getStattus() == 0){
//            spnstatus.setSelection(0);
//        }else {
//            spnstatus.setSelection(1);
//        }
//        spnstatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d(TAG, "onItemSelected: " + i);
//                phieuMuon.setStattus(i);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });




    }
    private void update(PhieuMuon phieuMuon){
        try {
            phieuMuonDAO.update(phieuMuon);
            Toast.makeText(getContext(), "Update phiếu mượn thành công", Toast.LENGTH_SHORT).show();
            xuli.xuli(null);
            Log.d("atuan" , phieuMuon.toString());
            dismiss();
        }catch (Exception e){
            Toast.makeText(getContext(), "Something wrong", Toast.LENGTH_SHORT).show();
            Log.d("atuan err" ,  e.getMessage());
        }
    }








}