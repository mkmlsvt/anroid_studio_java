package com.example.seksenyediproject;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentProfil extends Fragment {
    Button btnKartGor;
    TextView txtProfilKullaniciAdi, txtProfilOrtalama, txtProfilEmail;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_profil,container,  false);
        btnKartGor = (Button) rootView.findViewById(R.id.btnKartGor);
        txtProfilEmail = (TextView) rootView.findViewById(R.id.txtProfilEmail);
        txtProfilKullaniciAdi = (TextView) rootView.findViewById(R.id.txtProfilKullaniciAdi);
        txtProfilOrtalama = (TextView) rootView.findViewById(R.id.txtProfilOrtalama);
        Users userPro = Users.userList.get(0);
        txtProfilEmail.setText(userPro.eMail);
        userPro.ortalama = (userPro.capkinlik+ userPro.caliskanlik+userPro.guc+ userPro.disGorunus+ userPro.haysiyet+ userPro.hayvanSevgisi+ userPro.sadakat+ userPro.zeka+ userPro.saygi+ userPro.takimUyumu ) / 10;
        txtProfilOrtalama.setText(String.valueOf(userPro.ortalama));
        txtProfilKullaniciAdi.setText(userPro.kullaniciAdi);
        btnKartGor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Fragment newFragment = new FragmentCart();
                  Bundle args = new Bundle();
                  args.putString("id",userPro.userid);
                  newFragment.setArguments(args);
                  FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                  transaction.replace(R.id.fl_wrapper,newFragment);
                  transaction.addToBackStack(null);
                  transaction.commit();
            }
        });
        return rootView;
    }

}
