package com.example.seksenyediproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.firestore.auth.User;

public class FragmentOtherProfil extends Fragment {
    TextView lblOtherProfilKullaniciAdi, lblOtherProfilEmail, lblOtherOrtalama;
    Button btnOtherKartGor,btnOtherOyla;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_other_profil,container,false);
        Users userOtherPro = Users.userList.get(1);
        lblOtherOrtalama = (TextView) rootView.findViewById(R.id.lblOtherOrtalama);
        btnOtherKartGor = (Button) rootView.findViewById(R.id.btnOtherKartGor);
        btnOtherOyla = (Button) rootView.findViewById(R.id.btnOtherOyla);
        lblOtherProfilEmail = (TextView) rootView.findViewById(R.id.lblOtherProfilEmail);
        lblOtherProfilKullaniciAdi = (TextView) rootView.findViewById(R.id.lblOtherProfilKullaniciAdi);
        userOtherPro.ortalama = (userOtherPro.capkinlik+ userOtherPro.caliskanlik+userOtherPro.guc+ userOtherPro.disGorunus+ userOtherPro.haysiyet+ userOtherPro.hayvanSevgisi+ userOtherPro.sadakat+ userOtherPro.zeka+ userOtherPro.saygi+ userOtherPro.takimUyumu ) / 10;
        lblOtherOrtalama.setText(String.valueOf(userOtherPro.ortalama));
        lblOtherProfilEmail.setText(userOtherPro.eMail);
        lblOtherProfilKullaniciAdi.setText(userOtherPro.kullaniciAdi);
        btnOtherKartGor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentCart fragmentCart = new FragmentCart();
                Bundle args = new Bundle();
                args.putString("id",userOtherPro.userid);
                fragmentCart.setArguments(args);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_wrapper,fragmentCart);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        btnOtherOyla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragmentOyla = new FragmentOyla();
                FragmentTransaction transactionoyla = getActivity().getSupportFragmentManager().beginTransaction();
                transactionoyla.replace(R.id.fl_wrapper, fragmentOyla);
                transactionoyla.addToBackStack(null);
                transactionoyla.commit();
            }
        });
        return rootView;
    }
}
