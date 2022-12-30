package com.evan.hitungpengeluaran.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.evan.hitungpengeluaran.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    int[] tabIcons = {R.drawable.ic_pengeluaran, R.drawable.ic_pemasukan};

    TextView tvName;

    FirebaseUser user;
    FirebaseAuth mAuth;
    ImageView ivLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setIdLayout();
        setInitLayout();

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        tvName.setText(user.getEmail());

        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                // set title dialog
                alertDialogBuilder.setTitle("Logout ?");
                // set pesan dari dialog
                alertDialogBuilder
                        .setMessage("Apakah anda yakin logout dari akun anda ?")
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(false)
                        .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // jika tombol diklik, eksekusi panggil menghapus nilai yang sudah tersimpan pada sharedpreference
                                SharedPreferences.Editor editor = getSharedPreferences("SP_CK", Context.MODE_PRIVATE).edit();
                                editor.putString("email", "");
                                editor.commit();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            }
                        })
                        .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // jika tombol ini diklik, akan menutup dialog
                                // dan tidak terjadi apa2
                                dialog.cancel();
                            }
                        });
                // membuat alert dialog dari builder
                AlertDialog alertDialog = alertDialogBuilder.create();
                // menampilkan alert dialog
                alertDialog.show();
            }
        });

    }

    private void setIdLayout() {
        tabLayout = findViewById(R.id.tabsLayout);
        viewPager = findViewById(R.id.viewPager);
        tvName = findViewById(R.id.tvName);
        ivLogout = findViewById(R.id.ivLogout);
    }

    private void setInitLayout() {
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

}