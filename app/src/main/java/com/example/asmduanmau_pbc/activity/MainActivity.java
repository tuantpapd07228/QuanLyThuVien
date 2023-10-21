package com.example.asmduanmau_pbc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asmduanmau_pbc.R;
import com.example.asmduanmau_pbc.dao.ThuThuDAO;
import com.example.asmduanmau_pbc.databinding.ActivityMainBinding;
import com.example.asmduanmau_pbc.fragments.DoanhThuFragment;
import com.example.asmduanmau_pbc.fragments.LoaiSachFragment;
import com.example.asmduanmau_pbc.fragments.PhieuMuonFragment;
import com.example.asmduanmau_pbc.fragments.SachFragment;
import com.example.asmduanmau_pbc.fragments.ThanhVienFragment;
import com.example.asmduanmau_pbc.fragments.TopFragment;
import com.example.asmduanmau_pbc.model.ThuThu;
import com.example.asmduanmau_pbc.model.Top;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ThuThuDAO thuThuDAO;
    LinearLayout doanhthu, phieumuon, topSach, thanhvien, sach ,loaisach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        doanhthu = findViewById(R.id.home_doanhthu);
        phieumuon = findViewById(R.id.home_quanlyphieumuon);
        thanhvien = findViewById(R.id.home_thanhvien);
        topSach = findViewById(R.id.home_admin);
        sach =findViewById(R.id.home_sach);
        loaisach = findViewById(R.id.home_loaisach);
        //
        loaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar.setTitle("Loại Sách");
                Fragment f = new LoaiSachFragment();
                binding.homeLinealayoutchinh.setVisibility(View.GONE);
                replaceFragment(f);
            }
        });
        sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar.setTitle("Sách");
                Fragment f = new SachFragment();
                binding.homeLinealayoutchinh.setVisibility(View.GONE);
                replaceFragment(f);
            }
        });
        topSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar.setTitle("Top Sách Được thuê");
                Fragment f = new TopFragment();
                binding.homeLinealayoutchinh.setVisibility(View.GONE);
                replaceFragment(f);
            }
        });
        thanhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar.setTitle("Thành viên");
                Fragment f = new ThanhVienFragment();
                binding.homeLinealayoutchinh.setVisibility(View.GONE);
                replaceFragment(f);
            }
        });
        doanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar.setTitle("Doanh thu");
                Fragment f = new DoanhThuFragment();
                binding.homeLinealayoutchinh.setVisibility(View.GONE);
                replaceFragment(f);
            }
        });
        phieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar.setTitle("Phiếu mượn");
                Fragment f = new PhieuMuonFragment();
                binding.homeLinealayoutchinh.setVisibility(View.GONE);
                replaceFragment(f);
            }
        });






        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.money_icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,
                binding.drawablelayout,
                toolbar,
                R.string.open,
                R.string.close);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        Intent i = getIntent();
        String user = i.getStringExtra("user");

        NavigationView navigationView = findViewById(R.id.navigationview);
        View headerView = navigationView.getHeaderView(0);

        TextView tenuser = headerView.findViewById(R.id.txtuser);

        tenuser.setText(user);
        toolbar.setTitle("Trang chủ");

        binding.drawablelayout.addDrawerListener(toggle);
        toolbar.setTitle("Trang chủ");


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                try {
                    Fragment fragment = null;
                    if (item.getItemId() == R.id.nav_phieumuon){
                        toolbar.setTitle("Phiếu mượn");
                        fragment = new PhieuMuonFragment();
                    }else if (item.getItemId() == R.id.nav_loai_sach){
                        toolbar.setTitle("Loại sách");
                        fragment = new LoaiSachFragment();
                    }else if (item.getItemId() == R.id.nav_sach){
                        toolbar.setTitle("Sách");
                        fragment = new SachFragment();
                    }else if (item.getItemId() == R.id.nav_thanh_vien){
                        toolbar.setTitle("Thành viên");
                        fragment = new ThanhVienFragment();
                    }else if (item.getItemId() == R.id.subtop){
                        toolbar.setTitle("Top sách bán chạy");
                        fragment = new TopFragment();
                    }else if (item.getItemId() == R.id.subdoanhthu){
                        toolbar.setTitle("Doanh thu");
                        fragment = new DoanhThuFragment();
                    }else if (item.getItemId() == R.id.nav_themthuthu){
                        startActivity(new Intent(MainActivity.this, SignUpActivity.class));

                    }else if (item.getItemId() == R.id.nav_logout){
//                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                    }

                    binding.homeLinealayoutchinh.setVisibility(View.GONE);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.flCotent, fragment);
                    fragmentTransaction.commit();
                }catch (Exception e){

                }
                binding.drawablelayout.close();
                return false;
            }
        });
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.framelayout, new TrangChuFragment()).commit();


    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction f = getSupportFragmentManager().beginTransaction();
        f.replace(R.id.flCotent, fragment).commit();
    }
}