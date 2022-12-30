package com.evan.hitungpengeluaran.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import com.evan.hitungpengeluaran.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //hide keyboard
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            private void doNothing() {

            }

            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences("SP_CK", Context.MODE_PRIVATE);
                String sEmail = sp.getString("email", "");

                if (sEmail.equalsIgnoreCase("") || sEmail == null || sEmail.trim().equalsIgnoreCase("")){
                    Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent1);
                    finish();
                }else {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent1);
                    finish();
                }


            }
        }, 2000);
    }
}