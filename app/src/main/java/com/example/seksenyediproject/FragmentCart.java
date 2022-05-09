package com.example.seksenyediproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentCart extends Fragment {
    TextView txtProfilKartKullaniciAdi, lblSaygi, lblSadakat, lblDisGorunus, lblCapinlik, lblGuc, lblCaliskanlik, lblZeka, lblHayvanSevgisi, lblTakimUyumu, lblHaysiyet ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_cart,container,false);
        lblSaygi = (TextView) rootView.findViewById(R.id.lblSaygi);
        String bndlKullaniciId = getArguments().getString("id");
        lblSadakat = (TextView) rootView.findViewById(R.id.lblSadakat);
        lblDisGorunus = (TextView) rootView.findViewById(R.id.lblDisGorunus);
        lblCapinlik = (TextView) rootView.findViewById(R.id.lblCapkinlik);
        lblGuc = (TextView) rootView.findViewById(R.id.lblGuc);
        lblCaliskanlik = (TextView) rootView.findViewById(R.id.lblCaliskanlik);
        lblHayvanSevgisi = (TextView) rootView.findViewById(R.id.lblHayvanSevgisi);
        lblZeka = (TextView) rootView.findViewById(R.id.lblZeka);
        lblTakimUyumu = (TextView) rootView.findViewById(R.id.lblTakimUyumu);
        lblHaysiyet = (TextView) rootView.findViewById(R.id.lblHaysiyet);
        txtProfilKartKullaniciAdi = (TextView) rootView.findViewById(R.id.txtProfilKartKullaniciAdi);
        Users userPro = Users.userList.get(0);
        if(!userPro.userid.equals(bndlKullaniciId))
        {
            userPro = Users.userList.get(1);
        }
        lblCaliskanlik.setText(String.valueOf(userPro.caliskanlik));
        lblSaygi.setText(String.valueOf(userPro.saygi));
        lblSadakat.setText(String.valueOf(userPro.sadakat));
        lblCapinlik.setText(String.valueOf(userPro.capkinlik));
        lblGuc.setText(String.valueOf(userPro.guc));
        lblHayvanSevgisi.setText(String.valueOf(userPro.hayvanSevgisi));
        lblZeka.setText(String.valueOf(userPro.zeka));
        lblTakimUyumu.setText(String.valueOf(userPro.takimUyumu));
        lblHaysiyet.setText(String.valueOf(userPro.haysiyet));
        lblDisGorunus.setText(String.valueOf(userPro.disGorunus));
        txtProfilKartKullaniciAdi.setText(userPro.kullaniciAdi);
        return rootView;
    }
}
