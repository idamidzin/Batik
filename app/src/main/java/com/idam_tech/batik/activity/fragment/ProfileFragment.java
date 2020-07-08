package com.idam_tech.batik.activity.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.idam_tech.batik.R;
import com.idam_tech.batik.SecondActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    TextView txtNama, txtEmail, txtID;
    ImageView imgPerson;
    Button btnSignOut;
    GoogleSignInClient mGoogleSignInClient;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        txtNama = view.findViewById(R.id.txtNama);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtID = view.findViewById(R.id.txtID);
        imgPerson = view.findViewById(R.id.imgPerson);
        btnSignOut = view.findViewById(R.id.btnSignOut);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            txtNama.setText(personName);
            txtEmail.setText(personEmail);
            txtID.setText(personId);
            Glide.with(this).load(String.valueOf(personPhoto)).into(imgPerson);
            btnSignOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.btnSignOut:
                            signOut();
                            break;
                    }
                }
            });
        }

        return view;
    }

    private void signOut() {
        mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getContext().getApplicationContext(), "SignOut Succesfully", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
    }
}
