package com.example.tunguyen.manga.view.activity;

import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.fragment.FraInfoChapter;
import com.example.tunguyen.manga.view.fragment.FraListChapter;
import com.example.tunguyen.manga.view.fragment.FraRelateChapter;
import com.example.tunguyen.manga.view.fragment.NoSwipeableViewpager;
import com.example.tunguyen.manga.view.model.AdvertDto;
import com.example.tunguyen.manga.view.model.Preference;
import com.example.tunguyen.manga.view.model.clsAllAdvertDto;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DetailAdvert extends ActionBarActivity  {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private NoSwipeableViewpager viewPager;
    DrawerLayout drawer;
    NavigationView navigationView;

    View header;
    TextView tvHeaderName,tvHeaderEmail,txtNameAdvert,txtAuthor,txtCountChapter,txtStatusChap,txtInfo,txtChap,txtRelate;
    ImageView imgHeaderUser,imgAdvert;

    public FragmentTransaction ft;
    boolean doubleBackToExitPressedOnce = false;

    //string
    private static String PREF_NAME = "pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_advert);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        viewPager = (NoSwipeableViewpager) findViewById(R.id.viewpager);
//        tabLayout = (TabLayout) findViewById(R.id.tabs);


        /////replace fragment/////
        Fragment fragment = null;
        fragment = new FraInfoChapter();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_frame, fragment);
        transaction.commit();

        ///Advert///
        txtNameAdvert=(TextView) findViewById(R.id.txtNameAdvert);
        txtAuthor=(TextView) findViewById(R.id.txtAuthor);
        txtCountChapter=(TextView) findViewById(R.id.txtCountChapter);
        txtStatusChap=(TextView) findViewById(R.id.txtStatusChap);
        imgAdvert=(ImageView) findViewById(R.id.imgAdvert);

        txtInfo=(TextView) findViewById(R.id.txtInfo);
        txtChap=(TextView) findViewById(R.id.txtChap);
        txtRelate=(TextView) findViewById(R.id.txtRelate);
        ///End Advert///

        ///Event Button///
        txtInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtRelate.setBackgroundColor(getResources().getColor(R.color.black));
                txtRelate.setTextColor(getResources().getColor(R.color.colorTextOnButton));
                txtChap.setBackgroundColor(getResources().getColor(R.color.black));
                txtChap.setTextColor(getResources().getColor(R.color.colorTextOnButton));
                txtInfo.setBackgroundColor(getResources().getColor(R.color.backgroundIcon));
                txtInfo.setTextColor(getResources().getColor(R.color.TextColor));

                Fragment fragment = null;
               fragment = new FraInfoChapter();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_frame, fragment);
                transaction.commit();

            }
        });

        txtChap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtRelate.setBackgroundColor(getResources().getColor(R.color.black));
                txtRelate.setTextColor(getResources().getColor(R.color.colorTextOnButton));
                txtInfo.setBackgroundColor(getResources().getColor(R.color.black));
                txtInfo.setTextColor(getResources().getColor(R.color.colorTextOnButton));
                txtChap.setBackgroundColor(getResources().getColor(R.color.backgroundIcon));
                txtChap.setTextColor(getResources().getColor(R.color.TextColor));

                Fragment fragment = null;
                fragment = new FraListChapter();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_frame, fragment);
                transaction.commit();

            }
        });

        txtRelate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtChap.setBackgroundColor(getResources().getColor(R.color.black));
                txtChap.setTextColor(getResources().getColor(R.color.colorTextOnButton));
                txtInfo.setBackgroundColor(getResources().getColor(R.color.black));
                txtInfo.setTextColor(getResources().getColor(R.color.colorTextOnButton));
                txtRelate.setBackgroundColor(getResources().getColor(R.color.backgroundIcon));
                txtRelate.setTextColor(getResources().getColor(R.color.TextColor));

                Fragment fragment = null;
                fragment = new FraRelateChapter();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_frame, fragment);
                transaction.commit();

            }
        });

        ///End Event Button///

//        setupViewPager(viewPager);
//        setupTabLayout(tabLayout);
        setupActionBar();



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
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.greenToolBarBg1));
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new FraInfoChapter(),"Thông tin");
        adapter.addFrag(new FraListChapter(),"Chương");
        adapter.addFrag(new FraRelateChapter(),"Liên quan");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
    }
    public void setupActionBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        Preference.restorePreference(getApplicationContext());
        setSupportActionBar(toolbar);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.actionbar_title, null);
        LinearLayout ln_back = (LinearLayout) mCustomView.findViewById(R.id.ln_back);
        TextView tv_title = (TextView) mCustomView.findViewById(R.id.tv_title);
        TextView tv_title_name = (TextView) mCustomView.findViewById(R.id.tv_title_name);
        tv_title.setText("Danh sách");
        ln_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        ///Call Function///
        LoadDetailAdvertById(AdvertDto.IdAdvertRefer);
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

    ///Load Detail Advert by ID///
    public void LoadDetailAdvertById(int id)
    {
        ResClien resClient=new ResClien();
        resClient.GetService().GetAdvertById(id, new Callback<List<clsAllAdvertDto>>() {
            @Override
            public void success(List<clsAllAdvertDto> advertDtos, Response response) {
                txtNameAdvert.setText(advertDtos.get(0).tblAdvertManga.NameAdvertManga);
                if(advertDtos.get(0).tblAdvertManga.ImgAdvertManga!="")
                {
                    Picasso.with(getApplication()).load(advertDtos.get(0).tblAdvertManga.ImgAdvertManga).resize(180, 180).into(imgAdvert);
                }
                else
                {
                    Picasso.with(getApplication()).load(R.drawable.img_error).into(imgAdvert);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    ///End Load Detail Advert by ID///



}
