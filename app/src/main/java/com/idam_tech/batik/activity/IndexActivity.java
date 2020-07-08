package com.idam_tech.batik.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.idam_tech.batik.R;
import com.idam_tech.batik.activity.fragment.HomeFragment;
import com.idam_tech.batik.activity.fragment.ProfileFragment;
import com.idam_tech.batik.activity.fragment.ScannerFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class IndexActivity extends AppCompatActivity {
    ChipNavigationBar bottomNav;
    FragmentManager fragmentManager;
    private static final String TAG = IndexActivity.class.getSimpleName();

    public void findViewById(){
        bottomNav = findViewById(R.id.bottomNav);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        findViewById();
        if (savedInstanceState == null){
            bottomNav.setItemSelected(R.id.mHome, true);
            fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment=new HomeFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.mainFrame, homeFragment)
                    .commit();
        }
        setAksi();


    }

    private void setAksi() {
        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment=null;
                switch (id){
                    case R.id.mHome:
                        fragment =new HomeFragment();
                        break;
                    case R.id.mScanner:
                        fragment=new ScannerFragment();
                        break;
                    case R.id.mProfile:
                        fragment=new ProfileFragment();
                        break;
                }
                if (fragment !=null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.mainFrame, fragment)
                            .commit();
                }
            }
        });
    }
}
