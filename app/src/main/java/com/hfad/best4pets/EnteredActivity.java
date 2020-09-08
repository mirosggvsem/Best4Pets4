package com.hfad.best4pets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EnteredActivity extends AppCompatActivity {
    private EditText eMail_editTxt;
    private EditText mPassword_editTxt;
    private Button mSigIn_btn;
    private Button mRegister_btn;
    private ProgressBar mProgressBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entered);
        mAuth = FirebaseAuth.getInstance();
        eMail_editTxt = (EditText)findViewById(R.id.name_editText);
        mPassword_editTxt = (EditText)findViewById(R.id.number_editText);
        mSigIn_btn=(Button)findViewById(R.id.enter_button);
        mRegister_btn=(Button)findViewById(R.id.registred_button);
        mProgressBar=(ProgressBar)findViewById(R.id.loading_progressBar_zakaz_info);

        mSigIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmpty()) return;
                inProgress(true);
                mAuth.signInWithEmailAndPassword(eMail_editTxt.getText().toString(), mPassword_editTxt.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(EnteredActivity.this, "Успешная авторизация", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(EnteredActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        return;
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress(false);
                        Toast.makeText(EnteredActivity.this, "Ошибка в логине или пароле!"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        mRegister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmpty()) return;
                inProgress(true);
                mAuth.createUserWithEmailAndPassword(eMail_editTxt.getText().toString(), mPassword_editTxt.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(EnteredActivity.this, "Пользователь зарегестрирован!", Toast.LENGTH_LONG).show();
                        inProgress(false);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress(false);
                        Toast.makeText(EnteredActivity.this, "Ошибка в регистрации!"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void inProgress(boolean x){
        if(x){
            mProgressBar.setVisibility(View.VISIBLE);
            mSigIn_btn.setEnabled(false);
        }else {
            mProgressBar.setVisibility(View.GONE);
            mSigIn_btn.setEnabled(true);

        }
    }
    private boolean isEmpty(){
        if(TextUtils.isEmpty(eMail_editTxt.getText().toString())){
            eMail_editTxt.setError("Ошибка!");
            return true;
        }
        if (TextUtils.isEmpty(mPassword_editTxt.getText().toString())){
            mPassword_editTxt.setError("Ошибка!");
            return true;
        }
        return false;
    }
}
