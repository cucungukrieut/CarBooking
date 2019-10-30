package com.inducesmile.taxirental;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

import com.inducesmile.taxirental.fragment.BookingFragment;
import com.inducesmile.taxirental.fragment.BusinessFragment;
import com.inducesmile.taxirental.fragment.InformationFragment;
import com.inducesmile.taxirental.fragment.SearchCarFragment;
import com.inducesmile.taxirental.utils.CustomApplication;

import java.util.concurrent.TimeUnit;

public class HomeActivity extends AppCompatActivity{

    private static final String TAG = HomeActivity.class.getSimpleName();

    private FragmentManager fragmentManager;
    private Fragment bookingFragment;

    private ShareActionProvider mShareActionProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        String uname = i.getStringExtra("names");
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        bookingFragment = new BookingFragment();

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content, bookingFragment).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.action_booking) {
                    bookingFragment = new BookingFragment();
                }else if(id == R.id.action_search){
                    bookingFragment = new SearchCarFragment();
                }else if (id == R.id.action_share) {
                    //mShareActionProvider = (ShareActionProvider) item.getActionProvider();
                    shareText();
                } else if (id == R.id.action_profile) {
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                TimeUnit.MILLISECONDS.sleep(250);
                                Intent accountIntent = new Intent(HomeActivity.this, AccountActivity.class);
                                startActivity(accountIntent);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    /*Intent profileIntent = new Intent();
                    profileIntent.setClass(HomeActivity.this, AccountActivity.class);
                    startActivity(profileIntent);
                    overridePendingTransition(R.anim.from_right, R.anim.from_left);*/

                } else if (id == R.id.action_about) {
                    bookingFragment = new BusinessFragment();
                }else if (id == R.id.action_info) {
                    bookingFragment = new InformationFragment();
                }
                else if (id == R.id.action_logout) {
                    ((CustomApplication)getApplication()).getShared().setUserData("");

                    Intent logoutIntent = new Intent(HomeActivity.this, SignInActivity.class);
                    logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(logoutIntent);
                    finish();
                }

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_content, bookingFragment).commit();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    public void shareText() {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBodyText = "Download and share this app";
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Car Rental");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(intent, "Choose sharing method"));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

}
