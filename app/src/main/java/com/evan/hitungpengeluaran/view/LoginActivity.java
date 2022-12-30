package com.evan.hitungpengeluaran.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.evan.hitungpengeluaran.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private Button btLogin;
    private TextView tvToRegister;

    EditText etEmail, etPassword;
    FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);

                if (!etEmail.getText().toString().trim().equals("") || !etPassword.getText().toString().trim().equals("")){
                    String username, email, password;
                    email = etEmail.getText().toString().trim();
                    password = etPassword.getText().toString().trim();
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        user = mAuth.getCurrentUser();
                                        SharedPreferences sharedPreferences = getSharedPreferences("SP_CK", Context.MODE_PRIVATE);
                                        sharedPreferences.edit()
                                                        .putString("email", user.getEmail())
                                                                .apply();
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class)
                                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                    }else {
                                        Log.d("auth_error", "Login Gagal", task.getException());
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(LoginActivity.this, "Login Gagal !" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            });


                }else {
                    Toast.makeText(LoginActivity.this, "Lengkapi isian yang tersedia !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvToRegister = findViewById(R.id.tvToRegister);
        tvToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}