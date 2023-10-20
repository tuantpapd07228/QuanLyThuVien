package com.example.asmduanmau_pbc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.asmduanmau_pbc.R;
import com.example.asmduanmau_pbc.dao.ThuThuDAO;
import com.example.asmduanmau_pbc.databinding.ActivitySignUpBinding;
import com.example.asmduanmau_pbc.model.ThuThu;

public class SignUpActivity extends AppCompatActivity {
    ThuThuDAO thuThuDAO;
    ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        thuThuDAO = new ThuThuDAO(getApplicationContext());
//        thuThuDAO.delete("");
        binding.signupCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = binding.signupMatt.getText().toString().trim();
                String hoten = binding.signupHoten.getText().toString().trim();
                String matkhau = binding.signupMatkhau.getText().toString().trim();

                if (!id.equals("") && !hoten.equals("") && !matkhau.equals("")){
                    thuThuDAO.insert(new ThuThu(id, hoten, matkhau));
                    Toast.makeText(SignUpActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(SignUpActivity.this, "Thông tin chưa đủ hoặc tài khoản đã được sử dụng", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}