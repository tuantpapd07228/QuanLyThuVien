package com.example.asmduanmau_pbc.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asmduanmau_pbc.R;
import com.example.asmduanmau_pbc.adapters.PhieuMuonAdapter;
import com.example.asmduanmau_pbc.adapters.TopAdapter;
import com.example.asmduanmau_pbc.dao.PhieuMuonDAO;
import com.example.asmduanmau_pbc.model.Top;

import java.util.ArrayList;


public class TopFragment extends Fragment {

    ArrayList<Top> arr;
    PhieuMuonDAO phieuMuonDAO;
    RecyclerView recyclerView;
    TopAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top, container, false);
        recyclerView = v.findViewById(R.id.top_recycle_view);
        LinearLayoutManager l = new LinearLayoutManager(getContext());
        l.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(l);
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        setdata();











        return v;
    }
    private void setdata(){
        arr = (ArrayList<Top>) phieuMuonDAO.getDataTop();
        adapter = new TopAdapter(arr);
        recyclerView.setAdapter(adapter);
    }
}