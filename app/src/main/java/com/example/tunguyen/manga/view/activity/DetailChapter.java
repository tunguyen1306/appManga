package com.example.tunguyen.manga.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
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
TextView txtCount,txtItemCount,txtItemChapterName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_chapter);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //lv_advert_read = (TwoWayView)findViewById(R.id.lv_detail_chap);
        setupActionBar();
        paper_chap=(ViewPager)findViewById(R.id.pager_chap);
        Preference.restorePreference(getApplicationContext());
        callServiceChap(ChapterDto.IdChapterRefer);
        txtCount=(TextView)findViewById(R.id.txtCountDetailChap);
        txtItemChapterName=(TextView)findViewById(R.id.txtItemChapterName);
        txtItemChapterName.setText(ChapterDto.NameChapterRefer);
        txtItemCount=(TextView)findViewById(R.id.txtItemCount);
        paper_chap.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                txtItemCount.setText(String.valueOf(position+1));
                Preference.UpdatePositionViewedSqlite(getApplicationContext(),position+1,AdvertDto.IdAdvertRefer,ChapterDto.IdChapterRefer);
            }

            @Override
            public void onPageSelected(int position) {
                txtItemCount.setText(String.valueOf(position+1));
                Preference.UpdatePositionViewedSqlite(getApplicationContext(),position+1,AdvertDto.IdAdvertRefer,ChapterDto.IdChapterRefer);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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
        tv_title.setText(AdvertDto.NameAdvertRefer);
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

    ///LoadSlide///////
    private void loadChap(){
           ItemChap = getDataChap();
                try {

                    String t1=String.valueOf("/"+ ItemChap.size());
                    txtCount.setText(t1);



                    if (AdvertDto.PositionItemChapterRefer>0){
                        SlideChapAdapter slideAdapter1 =new SlideChapAdapter(this, ItemChap);
                        paper_chap.setAdapter(slideAdapter1);
                        paper_chap.setCurrentItem(AdvertDto.PositionItemChapterRefer-1);

                    }else {
                        SlideChapAdapter slideAdapter1 =new SlideChapAdapter(this, ItemChap);
                        paper_chap.setAdapter(slideAdapter1);
                        paper_chap.setCurrentItem(0);
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
