package com.example.tunguyen.manga.view.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.adapter.AdvertPopularAdapter;
import com.example.tunguyen.manga.view.adapter.AdvertReadAdapter;
import com.example.tunguyen.manga.view.adapter.AdvertViewedAdapter;
import com.example.tunguyen.manga.view.database.AdvertMangas;
import com.example.tunguyen.manga.view.database.AdvertViewedMangas;
import com.example.tunguyen.manga.view.database.DatabaseHelper;
import com.example.tunguyen.manga.view.fragment.FraInfoChapter;
import com.example.tunguyen.manga.view.fragment.FraListChapter;
import com.example.tunguyen.manga.view.fragment.FraRelateChapter;
import com.example.tunguyen.manga.view.fragment.NoSwipeableViewpager;
import com.example.tunguyen.manga.view.model.AdvertDto;
import com.example.tunguyen.manga.view.model.ControlDatabase;
import com.example.tunguyen.manga.view.model.Preference;
import com.example.tunguyen.manga.view.model.clsAllAdvertDto;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.squareup.picasso.Picasso;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.tunguyen.manga.view.model.AdvertDto.IdAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.ImgAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.NameAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.PositionItemChapterRefer;
import static com.example.tunguyen.manga.view.model.ChapterDto.IdChapterRefer;
import static com.example.tunguyen.manga.view.model.ChapterDto.NameChapterRefer;
import static com.example.tunguyen.manga.view.model.DeviceDto.SerialDeviceRefer;

public class DetailAdvert extends ActionBarActivity {

    private Toolbar toolbar;

    DrawerLayout drawer;


    TextView txtNameAdvert, txtAuthor, txtCountChapter, txtStatusChap, txtInfo, txtChap, txtRelate, txtCountView, txtRead;
    ImageView imgAdvert, imgAdvertOffFavorite, imgAdvertOnFavorite;
    ;

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

        /////replace fragment/////
        Fragment fragment = null;
        fragment = new FraInfoChapter();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_frame, fragment);
        transaction.commit();

        ///Advert///
        txtNameAdvert = (TextView) findViewById(R.id.txtNameAdvert);
        txtAuthor = (TextView) findViewById(R.id.txtAuthorName);
        txtCountChapter = (TextView) findViewById(R.id.txtCountChapter);
        txtStatusChap = (TextView) findViewById(R.id.txtStatusChap);
        imgAdvert = (ImageView) findViewById(R.id.imgAdvert);
        txtCountView = (TextView) findViewById(R.id.txtCountView);
        txtInfo = (TextView) findViewById(R.id.txtInfo);
        txtChap = (TextView) findViewById(R.id.txtChap);
        txtRelate = (TextView) findViewById(R.id.txtRelate);
        txtRead = (TextView) findViewById(R.id.txtRead);
        ///End Advert///

        ///Event Button///
        txtInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = new AlphaAnimation(0.3f, 1.0f);
                animation.setDuration(1000);
                v.startAnimation(animation);
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
                Animation animation = new AlphaAnimation(0.3f, 1.0f);
                animation.setDuration(1000);
                v.startAnimation(animation);
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
                Animation animation = new AlphaAnimation(0.3f, 1.0f);
                animation.setDuration(1000);
                v.startAnimation(animation);
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
        setupActionBar();
        ///End Event Button///
        LoadAdvertByIdAdvert(AdvertDto.IdAdvertRefer);
        LoadDetailAdvertById(AdvertDto.IdAdvertRefer);


    }

    @Override
    public void onPause() {
        super.onPause();


    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setupActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        Preference.restorePreference(getApplicationContext());
        setSupportActionBar(toolbar);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.actionbar_detail, null);
        LinearLayout ln_back = (LinearLayout) mCustomView.findViewById(R.id.ln_back);
        TextView tv_title = (TextView) mCustomView.findViewById(R.id.tv_title);
        tv_title.setText("Danh sách");
        ln_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imgAdvertOffFavorite = (ImageView) mCustomView.findViewById(R.id.imgAdvertOffFavorite);

        imgAdvertOffFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(imgAdvertOffFavorite.getTag().toString()) == 1) {
                    imgAdvertOffFavorite.setBackgroundResource(R.drawable.ic_favorite_on_manga);
                    imgAdvertOffFavorite.setTag(2);
                    Preference.UpdateAdvertFavoriteMangasSqlite(getApplicationContext(),IdAdvertRefer,NameAdvertRefer,ImgAdvertRefer,2);
                }
                else
                {
                    imgAdvertOffFavorite.setBackgroundResource(R.drawable.ic_favorite_off_manga);
                    imgAdvertOffFavorite.setTag(1);
                    Preference.UpdateAdvertFavoriteMangasSqlite(getApplicationContext(),IdAdvertRefer,NameAdvertRefer,ImgAdvertRefer,1);

                }


            }
        });


        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
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
    public void LoadDetailAdvertById(int id) {
        ResClien resClient = new ResClien();
        resClient.GetService().GetAdvertById(id, new Callback<List<clsAllAdvertDto>>() {
            @Override
            public void success(List<clsAllAdvertDto> advertDtos, Response response) {


                int countChap = advertDtos.get(0).ListChapterManga.size();
                txtCountChapter.setText(countChap + " Chap");
                Preference.restorePreference(getApplicationContext());

                IdChapterRefer = advertDtos.get(0).ListChapterManga.get(0).IdChapterManga;
                NameChapterRefer = advertDtos.get(0).ListChapterManga.get(0).NameChapterManga;
                NameAdvertRefer = advertDtos.get(0).tblAdvertManga.NameAdvertManga;
                ImgAdvertRefer = advertDtos.get(0).tblAdvertManga.ImgAdvertManga;
                Preference.savePreference(getApplicationContext());
                LoadAdvertView(AdvertDto.IdAdvertRefer);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private Dao<AdvertViewedMangas, Integer> AdvertViewedMangasDao;
    private List<AdvertViewedMangas> AdvertViewedMangasList;
    private DatabaseHelper databaseHelper = null;

    public void LoadAdvertView(int IdAdvertManga) {
        try {
            // This is how, a reference of DAO object can be done
            AdvertViewedMangasDao = getHelper().getAdvertViewedMangasDao();
            QueryBuilder<AdvertViewedMangas, Integer> queryBuilder = AdvertViewedMangasDao.queryBuilder();
            queryBuilder.where().eq("IdAdvertManga", IdAdvertManga);
            AdvertViewedMangasList = queryBuilder.query();
            if (AdvertViewedMangasList.size() > 0) {
                if (AdvertViewedMangasList.get(0).PositionItemChapterManga > 0) {
                    txtRead.setText("Đọc tiếp");
                    txtRead.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Animation animation = new AlphaAnimation(0.3f, 1.0f);
                            animation.setDuration(1000);
                            v.startAnimation(animation);
                            IdAdvertRefer = AdvertViewedMangasList.get(0).IdAdvertManga;
                            NameAdvertRefer = AdvertViewedMangasList.get(0).NameAdvertManga;
                            PositionItemChapterRefer = AdvertViewedMangasList.get(0).PositionItemChapterManga;
                            IdChapterRefer = AdvertViewedMangasList.get(0).IdChapterManga;
                            Preference.savePreference(getApplicationContext());
                            Intent intent_detailchapter = new Intent(getApplicationContext(), DetailChapter.class);
                            intent_detailchapter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent_detailchapter);

                        }
                    });
                } else {
                    txtRead.setText("Đọc");
                    txtRead.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Animation animation = new AlphaAnimation(0.3f, 1.0f);
                            animation.setDuration(1000);
                            v.startAnimation(animation);
                            Intent intent_login = new Intent(getApplicationContext(), DetailChapter.class);
                            intent_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            int tt = AdvertDto.IdAdvertRefer;
//                   IdChapterRefer=ChapterDto.get(position).getIdChapterManga();
//                   NameChapterRefer =ChapterDto.get(position).getNameChapterManga();
//                   Preference.savePreference(_Context.getApplicationContext());
//                   Preference.AddAdvertViewedSqlite(_Context,ChapterDto.get(position).getIdAdvertManga(), AdvertDto.NameAdvertRefer,AdvertDto.ImgAdvertRefer,NameChapterRefer,IdChapterRefer);
//                   Preference.UpdateChapterSqlite(_Context,IdChapterRefer);
                            startActivity(intent_login);
                        }
                    });
                }
            } else {
                txtRead.setText("Đọc");
                txtRead.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Animation animation = new AlphaAnimation(0.3f, 1.0f);
                        animation.setDuration(1000);
                        v.startAnimation(animation);
                        Intent intent_login = new Intent(getApplicationContext(), DetailChapter.class);
                        intent_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        int tt = AdvertDto.IdAdvertRefer;


                        Preference.AddAdvertViewedSqlite(getApplicationContext(), AdvertDto.IdAdvertRefer, AdvertDto.NameAdvertRefer, AdvertDto.ImgAdvertRefer, NameChapterRefer, IdChapterRefer);
                        Preference.UpdateChapterSqlite(getApplicationContext(), IdChapterRefer);
                        startActivity(intent_login);
                    }
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Dao<AdvertMangas, Integer> AdvertMangasDao;
    private List<AdvertMangas> AdvertMangasList;


    public void LoadAdvertByIdAdvert(int IdAdvertManga) {
        try {
            // This is how, a reference of DAO object can be done
            AdvertMangasDao = getHelper().getAdvertMangasDao();
            QueryBuilder<AdvertMangas, Integer> queryBuilder = AdvertMangasDao.queryBuilder();
            queryBuilder.where().eq("IdAdvertManga", IdAdvertManga);
            queryBuilder.orderBy("CountView", false);
            AdvertMangasList = queryBuilder.query();
            if (AdvertMangasList.size() > 0) {
                txtNameAdvert.setText(AdvertMangasList.get(0).NameAdvertManga);
                txtAuthor.setText(AdvertMangasList.get(0).NameAuthorAdvertManga);
                txtCountView.setText(AdvertMangasList.get(0).CountView + " lượt xem");
                if (AdvertMangasList.get(0).ImgAdvertManga != "") {
                    Picasso.with(getApplication()).load(AdvertMangasList.get(0).ImgAdvertManga).into(imgAdvert);
                } else {
                    Picasso.with(getApplication()).load(R.drawable.img_error).into(imgAdvert);
                }
                if (AdvertMangasList.get(0).IdFavorite==1){
                    imgAdvertOffFavorite.setBackgroundResource(R.drawable.ic_favorite_off_manga);
                }else {
                    imgAdvertOffFavorite.setBackgroundResource(R.drawable.ic_favorite_on_manga);
                }
            } else {
                ControlDatabase.AddAllAdvert(getApplicationContext());
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(getApplicationContext(), DatabaseHelper.class);
        }
        return databaseHelper;
    }

    ///End Load Detail Advert by ID///


}
