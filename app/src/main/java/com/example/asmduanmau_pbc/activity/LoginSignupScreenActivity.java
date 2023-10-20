package com.example.asmduanmau_pbc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.asmduanmau_pbc.R;
import com.example.asmduanmau_pbc.databinding.ActivityLoginScreenBinding;

public class LoginSignupScreenActivity extends AppCompatActivity {
    ActivityLoginScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginSignupScreenActivity.this, LoginActivity.class));
            }
        });

        binding.btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginSignupScreenActivity.this, SignUpActivity.class));
            }
        });
    }
}