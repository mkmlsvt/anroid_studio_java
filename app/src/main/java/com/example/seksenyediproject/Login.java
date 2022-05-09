package com.example.seksenyediproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;
import com.google.firebase.firestore.auth.User;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText txtGirisEmail,txtGirisPassword;
    String strGirisEmail, strGirisPassword;
    Button btnGiris;
    Users u = new Users();
    String s ;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mReference;
    HashMap<String,Object> mData;
    FirebaseFirestore mFireStore;
    DocumentReference myDocRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        btnGiris = findViewById(R.id.btnGirisYap);
        txtGirisEmail = findViewById(R.id.txtGirisEmail);
        txtGirisPassword = findViewById(R.id.txtGirisPassword);
        mAuth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();
        mFireStore = FirebaseFirestore.getInstance();
        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });
    }
    public void logIn()
    {
        strGirisEmail = txtGirisEmail.getText().toString();
        strGirisPassword = txtGirisPassword.getText().toString();
        if(!TextUtils.isEmpty(strGirisEmail) && !TextUtils.isEmpty(strGirisPassword)){
            mAuth.signInWithEmailAndPassword(strGirisEmail,strGirisPassword)
                    .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            mUser = mAuth.getCurrentUser();
                            verileriGetir(mUser.getUid());
                            Intent goMain = new Intent(Login.this,SplashGoMain.class);
                            startActivity(goMain);
                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(this, "Boş Alanları Doldurun", Toast.LENGTH_SHORT).show();

        }
    }
    public void verileriGetir(String uid){
        myDocRef = mFireStore.collection("Users").document(uid);
        myDocRef.get(Source.CACHE)
                .addOnSuccessListener(Login.this, new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            Users user1 = new Users();
                            user1.kullaniciAdi = documentSnapshot.getData().get("kullaniciAdi").toString();
                            user1.userid = documentSnapshot.getData().get("kullaniciId").toString();
                            user1.password = documentSnapshot.getData().get("kullaniciParola").toString();
                            user1.eMail = documentSnapshot.getData().get("kullaniciEmail").toString();
                            user1.saygi = Integer.parseInt(documentSnapshot.getData().get("saygi").toString());
                            user1.zeka = Integer.parseInt(documentSnapshot.getData().get("zeka").toString());
                            user1.takimUyumu = Integer.parseInt(documentSnapshot.getData().get("takimUyumu").toString());
                            user1.sadakat = Integer.parseInt(documentSnapshot.getData().get("sadakat").toString());
                            user1.hayvanSevgisi = Integer.parseInt(documentSnapshot.getData().get("hayvanSevgisi").toString());
                            user1.haysiyet = Integer.parseInt(documentSnapshot.getData().get("haysiyet").toString());;
                            user1.guc = Integer.parseInt(documentSnapshot.getData().get("guc").toString());
                            user1.disGorunus = Integer.parseInt(documentSnapshot.getData().get("disGorunus").toString());
                            user1.capkinlik= Integer.parseInt(documentSnapshot.getData().get("capkinlik").toString());
                            user1.caliskanlik = Integer.parseInt(documentSnapshot.getData().get("caliskanlik").toString());
                            user1.kacTane = Integer.parseInt(documentSnapshot.getData().get("kacTane").toString());
                            if(Users.userList.size()>0)
                            {
                                Users.userList.clear();
                            }
                            Users.userList.add(user1);
                            Users.currentUserId = uid;

                        }
                        else{
                            System.out.println("alex");
                        }
                    }

                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setMessage("alex");
                builder.show();
            }
        });
    }
}