package com.inducesmile.taxirental;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.inducesmile.taxirental.fragment.ProfileFragment;
import com.inducesmile.taxirental.fragment.ReservationFragment;

public class AccountActivity extends AppCompatActivity {

    private static final String TAG = AccountActivity.class.getSimpleName();

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragments = null;
            switch (item.getItemId()) {
                case R.id.navigation_profile:
                    fragments = new ProfileFragment();
                    break;
                case R.id.navigation_reservation:
                    fragments = new ReservationFragment();
                    break;
            }

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content, fragments).commit();

            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setElevation(0);
        }

        fragment = new ProfileFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
