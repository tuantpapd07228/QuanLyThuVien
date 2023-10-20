package com.example.asmduanmau_pbc.fragments;

import android.app.AlertDialog;
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
import com.example.asmduanmau_pbc.adapters.PhieuMuonAdapter;
import com.example.asmduanmau_pbc.dao.PhieuMuonDAO;
import com.example.asmduanmau_pbc.model.PhieuMuon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PhieuMuonFragment extends Fragment {

    PhieuMuonDAO phieuMuonDAO;
    FloatingActionButton flbtn;
    RecyclerView r;
    PhieuMuonAdapter adapter;
    ArrayList<PhieuMuon> arr;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        LinearLayoutManager l = new LinearLayoutManager(getContext());
        flbtn = v.findViewById(R.id.phieumuon_floatingbtn);
//        try {
//            Date ngay = sdf.parse("2023/02/02");
//            phieuMuonDAO.insert(new PhieuMuon(0, "tt1", 0 , 0 , ngay, 15000, 1 ));
//            Log.d("atuan", "add thanh cong");
//        } catch (ParseException e) {
//            Log.d("atuan", "add that bai" +e.getMessage());
//
//        }
        r = v.findViewById(R.id.phieumuon_recycleview);
        l.setOrientation(RecyclerView.VERTICAL);
        r.setLayoutManager(l);

        setData();
        flbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhieuMuonAddFragment phieuMuonAddFragment = new PhieuMuonAddFragment(new PhieuMuonAdapter.Xuli() {
                    @Override
                    public void xuli(Object obj) {
                        setData();
                    }
                });
                phieuMuonAddFragment.show(getChildFragmentManager(), phieuMuonAddFragment.getTag());
            }
        });






        return v;
    }
    private void setData(){
        arr = (ArrayList<PhieuMuon>) phieuMuonDAO.getALL();
        for (int i = 0; i < arr.size(); i++) {
            Log.d("atuan 83", "setData: "+arr.get(i).toString());
        }
        adapter = new PhieuMuonAdapter(arr, new PhieuMuonAdapter.Xuli() {
            @Override
            public void xuli(Object obj) {
                delete((PhieuMuon) obj);
            }
        }, new PhieuMuonAdapter.Xuli() {
            @Override
            public void xuli(Object obj) {
                update((PhieuMuon) obj);
            }
        });
        r.setAdapter(adapter);
    }
    private void update(PhieuMuon phieuMuon){
        PhieuMuonUpdateBottomSheet phieuMuonUpdateBottomSheet = new PhieuMuonUpdateBottomSheet(phieuMuon, new PhieuMuonAdapter.Xuli() {
            @Override
            public void xuli(Object obj) {
                setData();
            }
        });
        phieuMuonUpdateBottomSheet.show(getChildFragmentManager(), phieuMuonUpdateBottomSheet.getTag());
    }
    private void delete(PhieuMuon phieuMuon){
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Bạn có muốn xóa phiếu mượn mã "+ phieuMuon.getMaPM() +" không?"); //
            builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    phieuMuonDAO.delete(phieuMuon.getMaPM()+"");
                    Toast.makeText(getContext(), "Đã xóa phiếu mượn", Toast.LENGTH_SHORT).show();
                    setData();
                }
            });
            builder.setNegativeButton("Không", null);
            builder.show();
        }catch (Exception e){
            Log.e("atuan err" , e.getMessage());
        }
    }
}