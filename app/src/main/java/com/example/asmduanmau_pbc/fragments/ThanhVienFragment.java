package com.example.asmduanmau_pbc.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asmduanmau_pbc.R;
import com.example.asmduanmau_pbc.adapters.ThanhVienAdapter;
import com.example.asmduanmau_pbc.dao.PhieuMuonDAO;
import com.example.asmduanmau_pbc.dao.ThanhVienDAO;
import com.example.asmduanmau_pbc.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ThanhVienFragment extends Fragment  {
    RecyclerView recyclerView;
    ArrayList<ThanhVien> arr;
    ThanhVienDAO dao;
    ThanhVienAdapter adapter;
    FloatingActionButton fbtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        recyclerView = v.findViewById(R.id.thanhvien_recycle);
        fbtn = v.findViewById(R.id.floatingbtn);
        LinearLayoutManager l = new LinearLayoutManager(getContext());
        l.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(l);
        dao = new ThanhVienDAO(getContext());
        arr = (ArrayList<ThanhVien>) dao.getALL();
        adapter = new ThanhVienAdapter(arr, getContext(), new ThanhVienAdapter.XuLi() {
            @Override
            public void xuli(Object obj) {
                ThanhVien tdlt = (ThanhVien) obj;
                delete(tdlt.getHoTen(), String.valueOf(tdlt.getMaTV()));
            }
        }, new ThanhVienAdapter.XuLi() {
            @Override
            public void xuli(Object obj) {
                update((ThanhVien) obj);
            }
        });

        recyclerView.setAdapter(adapter);
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });

        return v;
    }

    private void add(){
        ThanhVienAddFragment bottomSheetFragment = new ThanhVienAddFragment(new ThanhVienAdapter.XuLi() {
            @Override
            public void xuli(Object obj) {
                arr.add((ThanhVien) obj);
                adapter.notifyDataSetChanged();
            }
        });
        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());

    }
    private void update(ThanhVien thanhVien){
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.fragment_bottom_sheet_update);
        Button update = dialog.findViewById(R.id.thanhvien_update_add);
        EditText edname = dialog.findViewById(R.id.thanhvien_update_hoten);
        EditText ednamsinh = dialog.findViewById(R.id.thanhvien_update_namsinh);
        ednamsinh.setText(thanhVien.getNamSinh());
        edname.setText(thanhVien.getHoTen());
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edname.getText().toString().trim();
                String namsinh = ednamsinh.getText().toString().trim();
                if (name != "" && namsinh != ""){
                    thanhVien.setHoTen(name);
                    thanhVien.setNamSinh(namsinh);
                    dao.update(thanhVien);
                    Toast.makeText(getContext(), "Đã update thành viên", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    for (int i = 0; i < arr.size(); i++) {
                        if (arr.get(i).getMaTV() == thanhVien.getMaTV()){
                            arr.get(i).setNamSinh(thanhVien.getNamSinh());
                            arr.get(i).setHoTen(thanhVien.getHoTen());
                            adapter.notifyDataSetChanged();
                            return;
                        }
                    }
                }else{
                    Toast.makeText(getContext(), "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });



        dialog.show();
    }
    private void delete(String name, String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Bạn có muốn xóa thành viên "+name+" không?");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getContext());
//                for (int j = 0; j < phieuMuonDAO.getALL().size(); j++) {
//                    if (phieuMuonDAO.getALL().get(j).getMaSach() == a.getMaSach()){
//                        Toast.makeText(getContext(), "Thành viên đang được sử dụng trong phiếu mượn không thể xóa!", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                }
                dao.delete(id);
                Toast.makeText(getContext(), "Đã xóa thành viên", Toast.LENGTH_SHORT).show();
                for (int j = 0; j < arr.size(); j++) {
                    if (arr.get(j).getMaTV() == Integer.parseInt(id)){
                        arr.remove(j);
                        adapter.notifyDataSetChanged();
                        return;
                    }
                }

            }
        });
        builder.setNegativeButton("Không", null);
        builder.show();

    }



}