package com.example.seksenyediproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;

public class SignUp extends AppCompatActivity {
    Button btnKaydol, btnGirisYapaGit;
    String strEmail,strPassword,strKullaniciAdi;
    EditText txtKaydolEmail, txtKaydolParola, txtKaydolKullaniciAdi;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mReference;
    FirebaseFirestore mFireStore;
    HashMap<String,Object> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        btnGirisYapaGit = findViewById(R.id.btnGirisYapaGit);
        txtKaydolEmail = findViewById(R.id.txtKayitMail);
        txtKaydolKullaniciAdi = findViewById(R.id.txtKaydolKullaniciAdi);
        txtKaydolParola = findViewById(R.id.txtKaydolParola);
        btnKaydol = findViewById(R.id.btnKaydol);
        mAuth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();
        mFireStore = FirebaseFirestore.getInstance();
        btnKaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kayitOl();

            }
        });
        btnGirisYapaGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent girisyapagit = new Intent(SignUp.this,Login.class);
                startActivity(girisyapagit);
            }
        });
    }
    public void kayitOl(){
        strEmail = txtKaydolEmail.getText().toString();
        strPassword = txtKaydolParola.getText().toString();
        strKullaniciAdi = txtKaydolKullaniciAdi.getText().toString();
        if(!TextUtils.isEmpty(strEmail) && !TextUtils.isEmpty(strPassword) && !TextUtils.isEmpty(strKullaniciAdi))
        {
            mAuth.createUserWithEmailAndPassword(strEmail,strPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                int sayiOn = 10;
                                mUser = mAuth.getCurrentUser();
                                mData = new HashMap<>();
                                mData.put("kullaniciAdi" , strKullaniciAdi);
                                mData.put("kullaniciEmail",strEmail);
                                mData.put("kullaniciParola", strPassword);
                                mData.put("kullaniciId", mUser.getUid());
                                mData.put("disGorunus",sayiOn);
                                mData.put("saygi",sayiOn);
                                mData.put("sadakat",sayiOn);
                                mData.put("capkinlik",sayiOn);
                                mData.put("guc",sayiOn);
                                mData.put("caliskanlik",sayiOn);
                                mData.put("zeka",sayiOn);
                                mData.put("hayvanSevgisi",sayiOn);
                                mData.put("takimUyumu",sayiOn);
                                mData.put("kacTane",0);
                                mData.put("haysiyet",sayiOn);

                                mFireStore.collection("Users").document(mUser.getUid())
                                        .set(mData)
                                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful())
                                                {
                                                    Toast.makeText(SignUp.this, "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
                                                    Intent goGiris = new Intent(SignUp.this,Login.class);
                                                    startActivity(goGiris);
                                                }
                                                else {
                                                    Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });



                            }
                            else
                                Toast.makeText(SignUp.this, task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    });

        }
        else{
            Toast.makeText(this, "Boş Alanları doldurun", Toast.LENGTH_SHORT).show();
        }
    }
}