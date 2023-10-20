package com.example.asmduanmau_pbc.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.asmduanmau_pbc.R;
import com.example.asmduanmau_pbc.dao.PhieuMuonDAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DoanhThuFragment extends Fragment {

    PhieuMuonDAO phieuMuonDAO;
    TextView doanhthu, fromdate, todate;
    Button kiemtra;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_doanh_thu, container, false);

        doanhthu = v.findViewById(R.id.doanhthu_tongdoanhthu);
        fromdate = v.findViewById(R.id.doanhthu_fromdate);
        todate = v.findViewById(R.id.doanhthu_todate);
        kiemtra = v.findViewById(R.id.doanhthu_kiemtra);
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month =c.get(Calendar.MONTH)+1;
        int year = c.get(Calendar.YEAR);

        fromdate.setText(year+"/"+month+"/"+(day-1));
        todate.setText(year+"/"+month+"/"+day);

        fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        fromdate.setText(i+"/"+i1+"/"+i2);
                    }
                }, year, month ,day);
                dialog.show();
            }
        });
        todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        todate.setText(i+"/"+i1+"/"+i2);
                    }
                }, year, month ,day);
                dialog.show();
            }
        });

        kiemtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    Date fromngay1 = sdf.parse(fromdate.getText().toString());
                    Date todate1 = sdf.parse(todate.getText().toString());
                    Log.d("atuan 76", fromngay1+"----" + todate1);

                    int intdoanhthu = phieuMuonDAO.getDoanhThu(fromngay1+"" , todate1+"");
                    doanhthu.setText(intdoanhthu+"");
                }catch (Exception e){
                    Log.d("atuan", e.getMessage());
                }
            }
        });

        return v;
    }
}