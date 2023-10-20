package com.example.asmduanmau_pbc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.asmduanmau_pbc.dao.ThuThuDAO;
import com.example.asmduanmau_pbc.databinding.ActivityLoginBinding;
import com.example.asmduanmau_pbc.model.ThuThu;

public class LoginActivity extends AppCompatActivity {
    ThuThuDAO thuThuDAO;
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        thuThuDAO = new ThuThuDAO(getApplicationContext());

        try {
            thuThuDAO.insert(new ThuThu("admin" , "nguyen van a", "fpt123"));
            thuThuDAO.insert(new ThuThu("admin1" , "nguyen van b", "fpt123"));
            thuThuDAO.insert(new ThuThu("admin2" , "nguyen van c", "fpt123"));
            thuThuDAO.insert(new ThuThu("admin3" , "nguyen van d", "fpt123"));
        }catch (Exception e){

        }
        setUSPW();
        setButton();

    }
    private void setButton(){
        binding.changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ChangePassActivity.class));
            }
        });
        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String us  = binding.edusername.getText().toString().trim();
                String pw  = binding.edpw.getText().toString().trim();
                Log.d("atuan", us + "- " + pw);
                checkLogin(us, pw);
            }
        });
    }
    private void checkLogin(String us, String pw){
        try {
            ThuThu thuThu = thuThuDAO.getById(us);
            Log.d("atuan", thuThu.toString());
            if (thuThu != null && thuThu.getMatKhau().equalsIgnoreCase(pw)){
                if (binding.remember.isChecked()){
                    remember(us, pw);
                }
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("user", thuThu.getHoTen());
                startActivity(i);
            }else{
                Toast.makeText(this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Log.e("atuan", e.getMessage());
        }
    }
    private void remember(String us, String pw){
        SharedPreferences s = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor e = s.edit();
        e.putString("us",us);
        e.putString("pw", pw);
        e.putBoolean("c", true);
        e.commit();
    }
    private void setUSPW (){
        SharedPreferences s = getSharedPreferences("login", MODE_PRIVATE);
        if (s.getBoolean("c", false)){
            binding.edusername.setText(s.getString("us", ""));
            binding.edpw.setText(s.getString("pw",""));
            binding.remember.setChecked(true);
        }
    }
}