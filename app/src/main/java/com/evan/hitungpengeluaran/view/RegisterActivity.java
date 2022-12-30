package com.evan.hitungpengeluaran.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.evan.hitungpengeluaran.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private Button btRegister;
    private TextView tvToLogin;
    EditText etUsername, etEmail, etPassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btRegister = findViewById(R.id.btRegister);

        mAuth = FirebaseAuth.getInstance();

        btRegister = findViewById(R.id.btRegister);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!etUsername.getText().toString().trim().equals("") || !etEmail.getText().toString().trim().equals("") || !etPassword.getText().toString().trim().equals("")){
                    String username, email, password;
                    username = etUsername.getText().toString().trim();
                    email = etEmail.getText().toString().trim();
                    password = etPassword.getText().toString().trim();
                    mAuth.createUserWithEmailAndPassword(email,password)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                FirebaseUser user = mAuth.getCurrentUser();
                                                startActivity(new Intent(getApplicationContext(), MainActivity.class)
                                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                            }else {
                                                Log.d("auth_error", "Registrasi Gagal", task.getException());
                                            }
                                        }
                                    });

                }else {
                    Toast.makeText(RegisterActivity.this, "Lengkapi isian yang tersedia !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvToLogin = findViewById(R.id.tvToLogin);
        tvToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
//        tvToLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String username, email, password, register;
//                username = etUsername.getText().toString();
//                email = etEmail.getText().toString();
//                password = etPassword.getText().toString();
//                register = btRegister.getText().toString();
//
//                if (password.equals(register)){
//                    mAuth.createUserWithEmailAndPassword(email,password)
//                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<AuthResult> task) {
//                                            if(task.isSuccessful()){
//                                                FirebaseUser user = mAuth.getCurrentUser();
//                                                startActivity(new Intent(getApplicationContext(), MainActivity.class)
//                                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
//                                            }else {
//                                                Log.d("auth_error", "Registrasi Gagal", task.getException());
//                                            }
//                                        }
//                                    });
//
//                }else {
//                    Toast.makeText(RegisterActivity.this, "Password Salah", Toast.LENGTH_SHORT).show();
//                }
//                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}