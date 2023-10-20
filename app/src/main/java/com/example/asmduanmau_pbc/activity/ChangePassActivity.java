package com.example.asmduanmau_pbc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.asmduanmau_pbc.R;
import com.example.asmduanmau_pbc.dao.ThuThuDAO;
import com.example.asmduanmau_pbc.databinding.ActivityRePassBinding;
import com.example.asmduanmau_pbc.model.ThuThu;

public class ChangePassActivity extends AppCompatActivity {
    ActivityRePassBinding binding;
    ThuThuDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRePassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dao = new ThuThuDAO(ChangePassActivity.this);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        
        binding.changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String us = binding.edus.getText().toString().trim();
                String oldpw = binding.edoldpw.getText().toString().trim();
                String pw = binding.edpw.getText().toString().trim();
                String pwcf = binding.edpwcf.getText().toString().trim();
                ThuThu t = dao.getById(us);
                if (t != null && oldpw.equalsIgnoreCase(t.getMatKhau())){
                    if (pw.equalsIgnoreCase(pwcf)){
                        ThuThu tupdate = t;
                        tupdate.setMatKhau(pw);
                        dao.update(tupdate);
                        Toast.makeText(ChangePassActivity.this, "Đã thay đổi password", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(ChangePassActivity.this, "Đã có lỗi xảy ra, kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                }
            }
            
        });
    }
}