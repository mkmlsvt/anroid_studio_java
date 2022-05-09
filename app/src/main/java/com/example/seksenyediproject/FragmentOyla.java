package com.example.seksenyediproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class FragmentOyla extends Fragment {
    Handler hndlr;
    Button btnOylamayiKaydet;
    HashMap<String,Object> mData;
    FirebaseFirestore mFireStore;
    CollectionReference usersRef;
    DocumentReference docRef;
    private int caliskanlik,capkinlik,disGorunus,guc,haysiyet,hayvanSevgisi,sadakat,saygi,takimUyumu,zeka,kacTane;
    EditText txtOylaCaliskanlik,txtOylaCapkinlik,txtOylaDisGorunus,txtOylaGuc,txtOylaHaysiyet,txtOylaHayvanSevgisi,txtOylaSadakat,txtOylaSaygi,txtOylaTakimUyumu,txtOylaZeka;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_oyla,container,false);
        mFireStore = FirebaseFirestore.getInstance();
        btnOylamayiKaydet = (Button) rootView.findViewById(R.id.btnOylamayiKaydet);
        txtOylaCaliskanlik = (EditText) rootView.findViewById(R.id.txtOylaCaliskanlik);
        txtOylaCapkinlik = (EditText) rootView.findViewById(R.id.txtOylaCapkinlik);
        txtOylaDisGorunus = (EditText) rootView.findViewById(R.id.txtOylaDisGorunus);
        txtOylaGuc = (EditText) rootView.findViewById(R.id.txtOylaGuc);
        txtOylaHaysiyet = (EditText) rootView.findViewById(R.id.txtOylaHaysiyet);
        txtOylaHayvanSevgisi = (EditText) rootView.findViewById(R.id.txtOylaHayvanSevgisi);
        txtOylaSadakat = (EditText) rootView.findViewById(R.id.txtOylaSadakat);
        txtOylaSaygi = (EditText) rootView.findViewById(R.id.txtOylaSaygi);
        txtOylaTakimUyumu = (EditText) rootView.findViewById(R.id.txtOylaTakimUyumu);
        txtOylaZeka = (EditText) rootView.findViewById(R.id.txtOylaZeka);
        btnOylamayiKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextNullKontrol())
                {
                    if(editTextNumKontrol())
                    {
                        Users userOy = Users.userList.get(1);
                        kacTane = userOy.kacTane;
                        userOy.caliskanlik = ((userOy.caliskanlik*kacTane)+caliskanlik)/(kacTane+1);
                        userOy.capkinlik = ((userOy.capkinlik*kacTane)+capkinlik)/(kacTane+1);
                        userOy.disGorunus = ((userOy.disGorunus*kacTane)+disGorunus)/(kacTane+1);
                        userOy.guc = ((userOy.guc*kacTane)+guc)/(kacTane+1);
                        userOy.haysiyet = ((userOy.haysiyet*kacTane)+haysiyet)/(kacTane+1);
                        userOy.hayvanSevgisi = ((userOy.hayvanSevgisi*kacTane)+hayvanSevgisi)/(kacTane+1);
                        userOy.sadakat = ((userOy.sadakat*kacTane)+sadakat)/(kacTane+1);
                        userOy.saygi = ((userOy.saygi*kacTane)+saygi)/(kacTane+1);
                        userOy.takimUyumu = ((userOy.takimUyumu*kacTane)+takimUyumu)/(kacTane+1);
                        userOy.zeka = ((userOy.zeka*kacTane)+zeka)/(kacTane+1);
                        mData = new HashMap<>();
                        mData.put("disGorunus",userOy.disGorunus);
                        mData.put("saygi",userOy.saygi);
                        mData.put("sadakat",userOy.sadakat);
                        mData.put("capkinlik",userOy.capkinlik);
                        mData.put("guc",userOy.guc);
                        mData.put("caliskanlik",userOy.caliskanlik);
                        mData.put("zeka",userOy.zeka);
                        mData.put("hayvanSevgisi",userOy.hayvanSevgisi);
                        mData.put("takimUyumu",userOy.takimUyumu);
                        mData.put("kacTane",kacTane+1);
                        mData.put("haysiyet",userOy.haysiyet);
                        docRef = mFireStore.collection("Users").document(userOy.userid);
                        docRef.update(mData)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            Toast.makeText(getActivity(), "Oylamanız Başarılı", Toast.LENGTH_SHORT).show();
                                            Fragment newFragment = new FragmentHome();
                                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                            transaction.replace(R.id.fl_wrapper,newFragment);
                                            transaction.addToBackStack(null);
                                            transaction.commit();
                                        }
                                    }
                                });
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Degerlernizi Kontrol Edin Ve Tekrar Deneyin", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(getActivity(), "Degerlernizi Kontrol Edin Ve Tekrar Deneyin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }
    public boolean editTextNullKontrol(){
        if(txtOylaCaliskanlik.getText().toString().trim().length()>0 && txtOylaCapkinlik.getText().toString().trim().length()>0 && txtOylaDisGorunus.getText().toString().trim().length()>0
                && txtOylaGuc.getText().toString().trim().length()>0 && txtOylaHaysiyet.getText().toString().trim().length()>0 && txtOylaHayvanSevgisi.getText().toString().trim().length()>0
                && txtOylaSadakat.getText().toString().trim().length()>0 && txtOylaSaygi.getText().toString().trim().length()>0 && txtOylaTakimUyumu.getText().toString().trim().length()>0
                && txtOylaZeka.getText().toString().trim().length()>0)
        {
            return true;

        }
        else{
            return false;
        }
    }
    public boolean editTextNumKontrol(){
        caliskanlik = Integer.parseInt(txtOylaCaliskanlik.getText().toString());
        capkinlik = Integer.parseInt(txtOylaCapkinlik.getText().toString());
        disGorunus = Integer.parseInt(txtOylaDisGorunus.getText().toString());
        guc = Integer.parseInt(txtOylaGuc.getText().toString());
        haysiyet = Integer.parseInt(txtOylaHaysiyet.getText().toString());
        hayvanSevgisi = Integer.parseInt(txtOylaHayvanSevgisi.getText().toString());
        sadakat = Integer.parseInt(txtOylaSadakat.getText().toString());
        saygi = Integer.parseInt(txtOylaSaygi.getText().toString());
        takimUyumu = Integer.parseInt(txtOylaTakimUyumu.getText().toString());
        zeka = Integer.parseInt(txtOylaZeka.getText().toString());
        if(caliskanlik<0 || caliskanlik>20 || capkinlik<0 || capkinlik>20 || disGorunus<0 || disGorunus>20 || guc<0 || guc>20
                || haysiyet<0 || haysiyet>20  || hayvanSevgisi<0 || hayvanSevgisi>20 || sadakat<0 || sadakat>20 || saygi<0 || saygi>20
                || takimUyumu<0 || takimUyumu>20|| zeka<0 || zeka>20 )
        {
            return false;
        }
        else {
            return true;
        }

    }
}
