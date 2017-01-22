package com.example.tunguyen.manga.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.adapter.AllAdvertAdapter;
import com.example.tunguyen.manga.view.adapter.SlideChapAdapter;
import com.example.tunguyen.manga.view.model.AdvertDto;
import com.example.tunguyen.manga.view.model.ChapterDto;
import com.example.tunguyen.manga.view.model.Preference;
import com.pixelcan.inkpageindicator.InkPageIndicator;

import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AllAdvertBy extends ActionBarActivity  {

    private Toolbar toolbar;
    DrawerLayout drawer;
    ListView list;


    boolean doubleBackToExitPressedOnce = false;

    //string
    private static String PREF_NAME = "pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_advert);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        list=(ListView)findViewById(R.id.lvAllAdvert);
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

        switch(Preference.idActionbar) {
            case 1:
                tv_title.setText("Danh sách nhiều người đang đọc");
                LoadAllAdvertRead();
                break;
            case 2:
                tv_title.setText("Danh sách truyện phổ biến trong tuần");
                LoadAllAdvertPopular();
                break;
            default:
                tv_title.setText("Danh sách nhiều người đang đọc");
                LoadAllAdvertRead();
        }
        ln_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
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
    public void LoadAllAdvertRead()
    {
        ResClien resClient=new ResClien();
        resClient.GetService().GetAdvertByTypeId(2,
                new Callback<List<AdvertDto>>() {
                    @Override
                    public void success(List<AdvertDto> AdvertDto, Response response) {
                        list.setAdapter(new AllAdvertAdapter(getApplication(),AdvertDto));
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }
    public void LoadAllAdvertPopular()
    {
        ResClien resClient=new ResClien();
        resClient.GetService().GetAdvertByTypeId(3,
                new Callback<List<AdvertDto>>() {
                    @Override
                    public void success(List<AdvertDto> AdvertDto, Response response) {
                        list.setAdapter(new AllAdvertAdapter(getApplication(),AdvertDto));
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }
}
