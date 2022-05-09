package com.example.seksenyediproject;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import org.w3c.dom.Text;

import java.util.HashMap;

public class FragmentHome extends Fragment {
    Button btnAramaYap;
    TextView txtProfilArama;
    FirebaseFirestore mFireStore;
    CollectionReference usersRef;
    HashMap<String,Object> mData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home,container,  false);
        mFireStore = FirebaseFirestore.getInstance();
        usersRef = mFireStore.collection("Users");
        txtProfilArama = (EditText) rootView.findViewById(R.id.txtProfilArama);
        btnAramaYap = (Button) rootView.findViewById(R.id.btnAramaYap);
        btnAramaYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(txtProfilArama.getText())){
                    Toast.makeText(getActivity(), "Arama alanı boşken arama yapamazsınız", Toast.LENGTH_SHORT).show();
                }
                else{
                    Query query = usersRef.whereEqualTo("kullaniciAdi",txtProfilArama.getText().toString());
                    query.get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if(task.isSuccessful()){
                                        if (task.getResult().size()!=0){
                                            for(QueryDocumentSnapshot document : task.getResult()){
                                                if(Users.userList.size()>1)
                                                {
                                                    Users.userList.remove(1);
                                                }
                                                Users userAranan = new Users();
                                                userAranan.kullaniciAdi = document.getData().get("kullaniciAdi").toString();
                                                userAranan.userid = document.getData().get("kullaniciId").toString();
                                                userAranan.password = document.getData().get("kullaniciParola").toString();
                                                userAranan.eMail = document.getData().get("kullaniciEmail").toString();
                                                userAranan.saygi = Integer.parseInt(document.getData().get("saygi").toString());
                                                userAranan.zeka = Integer.parseInt(document.getData().get("zeka").toString());
                                                userAranan.takimUyumu = Integer.parseInt(document.getData().get("takimUyumu").toString());
                                                userAranan.sadakat = Integer.parseInt(document.getData().get("sadakat").toString());
                                                userAranan.hayvanSevgisi = Integer.parseInt(document.getData().get("hayvanSevgisi").toString());
                                                userAranan.haysiyet = Integer.parseInt(document.getData().get("haysiyet").toString());;
                                                userAranan.guc = Integer.parseInt(document.getData().get("guc").toString());
                                                userAranan.disGorunus = Integer.parseInt(document.getData().get("disGorunus").toString());
                                                userAranan.capkinlik= Integer.parseInt(document.getData().get("capkinlik").toString());
                                                userAranan.kacTane = Integer.parseInt(document.getData().get("kacTane").toString());
                                                userAranan.caliskanlik = Integer.parseInt(document.getData().get("caliskanlik").toString());
                                                Users.userList.add(userAranan);
                                                Users userControl = Users.userList.get(0);
                                                if(userAranan.userid.equals(userControl.userid))
                                                {
                                                    Fragment newFragment = new FragmentProfil();
                                                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                                    transaction.replace(R.id.fl_wrapper,newFragment);
                                                    transaction.addToBackStack(null);
                                                    transaction.commit();
                                                }
                                                else
                                                {
                                                    System.out.println(document.getData().get("kullaniciId"));
                                                    Fragment newFragment = new FragmentOtherProfil();
                                                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                                    transaction.replace(R.id.fl_wrapper,newFragment);
                                                    transaction.addToBackStack(null);
                                                    transaction.commit();
                                                }

                                            }
                                       }
                                        else
                                        {
                                            Toast.makeText(getActivity(), "Kullanıcı Bulunamadı", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else{
                                        Toast.makeText(getActivity(), "Kullanıcı Bulunamadı", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });
        return rootView;
    }
}
