package com.example.tunguyen.manga.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tunguyen.manga.R;
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

public class DetailChapter extends ActionBarActivity  {

    private Toolbar toolbar;
    DrawerLayout drawer;
    ViewPager paper_chap;
    InkPageIndicator indicator_chap;
    ////Advert Read///////
    TwoWayView lv_advert_read;
    List<ChapterDto> ItemAdvertRead;
    List<String> ListImgAdvertRead = new ArrayList<>();
    ////End Advert Read///////

    ////Slide///////
    List<ChapterDto> ItemChap;
    List<String> list_img_chap = new ArrayList<>();

    ////End Slide///

    boolean doubleBackToExitPressedOnce = false;

    //string
    private static String PREF_NAME = "pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_chapter);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //lv_advert_read = (TwoWayView)findViewById(R.id.lv_detail_chap);
        setupActionBar();
        indicator_chap=(InkPageIndicator)findViewById(R.id.indicator_chap);
        paper_chap=(ViewPager)findViewById(R.id.pager_chap);
        Preference.restorePreference(getApplicationContext());
        callServiceChap(ChapterDto.IdChapterRefer);
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
        tv_title.setText(AdvertDto.NameAdvertRefer+"-"+ChapterDto.NameChapterRefer);
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
//    ///LoadAdvertRead///
//    private void loadDataAdvertRead() {
//
//        ItemAdvertRead = getAllItemsAdvertRead();
//
//        try {
//
//            DetailChapterAdapter adapter = new DetailChapterAdapter(this, ItemAdvertRead, "project");
//            lv_advert_read.setAdapter(adapter);
//
//
//        }
//        catch (Exception ex) {
//
//        }
//    }
//    private List<ChapterDto> getAllItemsAdvertRead() {
//        List<ChapterDto> items = new ArrayList<>();
//        for (int i = 0; i < ListImgAdvertRead.size(); i++) {
//            items.add(
//                    new ChapterDto(
//                       ListImgAdvertRead.get(i)
//
//                    )
//            );
//        }
//        return items;
//    }
//    public void callServiceAdvertRead(int id) {
//        ResClien restClient = new ResClien();
//        restClient.GetService().GetDetailChapByID(id,new Callback<List<ChapterDto>>() {
//            @Override
//            public void success(List<ChapterDto> AdvertDto, Response response) {
//                for (int i = 0; i < AdvertDto.size(); i++) {
//
//                    ListImgAdvertRead.add(AdvertDto.get(i).Link);
//
//                }
//                loadDataAdvertRead();
//            }
//            @Override
//            public void failure(RetrofitError error) {
//                Log.d("myLogs", "-------ERROR-------Img");
//                Log.d("myLogs", Log.getStackTraceString(error));
//            }
//        });
//    }
//    ///End LoadAdvertRead///

    ///LoadSlide///////
    private void loadChap(){

        ItemChap = getDataChap();
        try {
            for (int i = 0; i < ItemChap.size(); i++) {

                SlideChapAdapter slideAdapter1 =new SlideChapAdapter(this, ItemChap);
                paper_chap.setAdapter(slideAdapter1);
                //indicator_chap.setViewPager(paper_chap);
            }
        }
        catch (Exception ex) {

        }
    }
    private List<ChapterDto>getDataChap(){
        List<ChapterDto> items = new ArrayList<>();
        for (int i=0;i<list_img_chap.size();i++) {
            items.add(new ChapterDto(list_img_chap.get(i)));
        }
        return items;
    }
    public void callServiceChap(int id) {
        ResClien restClient = new ResClien();
        restClient.GetService().GetDetailChapByID(id,
                new Callback<List<ChapterDto>>() {
                    @Override
                    public void success(List<ChapterDto> slideDtos, Response response) {
                        for (int i = 0; i < slideDtos.size(); i++) {
                            list_img_chap.add(slideDtos.get(i).Link);

                        }
                        loadChap();
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }
    ///End LoadSlide///
}