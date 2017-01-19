package com.example.tunguyen.manga.view.activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.fragment.FraListChapter;
import com.example.tunguyen.manga.view.fragment.FraRelateChapter;
import com.example.tunguyen.manga.view.fragment.FraAllAdvert;
import com.example.tunguyen.manga.view.fragment.FraUpdateAdvert;
import com.example.tunguyen.manga.view.fragment.FraHome;
//import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    //private TabLayout tabLayout;
    //private NoSwipeableViewpager viewPager;
    DrawerLayout drawer;
    NavigationView navigationView;

    View header;
    TextView tvHeaderName, tvHeaderEmail,txtUpdateAdvert,txtAllAdvert,txtFeauture;
    ImageView imgHeaderUser;


    boolean doubleBackToExitPressedOnce = false;

    //string
    private static String PREF_NAME = "pref";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.tunguyen.manga",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //viewPager = (NoSwipeableViewpager) findViewById(R.id.viewpager);
        //tabLayout = (TabLayout) findViewById(R.id.tabs);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        //Add Header

        //setupViewPager(viewPager);
        //setupTabLayout(tabLayout);
        setupActionBar();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        /////replace fragment/////
        Fragment fragment = null;
        fragment = new FraHome();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_main, fragment);
        transaction.commit();

        txtFeauture=(TextView) findViewById(R.id.txtFeauture);
        txtAllAdvert=(TextView) findViewById(R.id.txtAllAdvert);
        txtUpdateAdvert=(TextView) findViewById(R.id.txtUpdateAdvert);
        ///End Advert///

        ///Event Button///
        txtFeauture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtUpdateAdvert.setBackgroundColor(getResources().getColor(R.color.black));
                txtUpdateAdvert.setTextColor(getResources().getColor(R.color.colorTextOnButton));
                txtAllAdvert.setBackgroundColor(getResources().getColor(R.color.black));
                txtAllAdvert.setTextColor(getResources().getColor(R.color.colorTextOnButton));
                txtFeauture.setBackgroundColor(getResources().getColor(R.color.backgroundIcon));
                txtFeauture.setTextColor(getResources().getColor(R.color.TextColor));

                Fragment fragment = null;
                fragment = new FraHome();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_main, fragment);
                transaction.commit();

            }
        });

        txtAllAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtUpdateAdvert.setBackgroundColor(getResources().getColor(R.color.black));
                txtUpdateAdvert.setTextColor(getResources().getColor(R.color.colorTextOnButton));
                txtFeauture.setBackgroundColor(getResources().getColor(R.color.black));
                txtFeauture.setTextColor(getResources().getColor(R.color.colorTextOnButton));
                txtAllAdvert.setBackgroundColor(getResources().getColor(R.color.backgroundIcon));
                txtAllAdvert.setTextColor(getResources().getColor(R.color.TextColor));

                Fragment fragment = null;
                fragment = new FraAllAdvert();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_main, fragment);
                transaction.commit();

            }
        });

        txtUpdateAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtAllAdvert.setBackgroundColor(getResources().getColor(R.color.black));
                txtAllAdvert.setTextColor(getResources().getColor(R.color.colorTextOnButton));
                txtFeauture.setBackgroundColor(getResources().getColor(R.color.black));
                txtFeauture.setTextColor(getResources().getColor(R.color.colorTextOnButton));
                txtUpdateAdvert.setBackgroundColor(getResources().getColor(R.color.backgroundIcon));
                txtUpdateAdvert.setTextColor(getResources().getColor(R.color.TextColor));

                Fragment fragment = null;
                fragment = new FraUpdateAdvert();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_main, fragment);
                transaction.commit();

            }
        });



    }//end Oncreate


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    private void setupTabLayout(TabLayout tabLayout) {

        //tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new FraHome(), getResources().getString(R.string.title_special));
        adapter.addFrag(new FraAllAdvert(), getResources().getString(R.string.title_all));
        adapter.addFrag(new FraUpdateAdvert(), getResources().getString(R.string.title_update));
        viewPager.setAdapter(adapter);

        viewPager.setOffscreenPageLimit(3);
    }

    public void setupActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.actionbar, null);
        ImageView logo = (ImageView) mCustomView.findViewById(R.id.img_logo);
        logo.setImageResource(R.drawable.ic_launcher);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();


    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

}
