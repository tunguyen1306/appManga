package com.example.tunguyen.manga.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.activity.AllAdvertBy;
import com.example.tunguyen.manga.view.activity.ResClien;
import com.example.tunguyen.manga.view.adapter.AdvertFeaturedAdapter;
import com.example.tunguyen.manga.view.adapter.AdvertPopularAdapter;
import com.example.tunguyen.manga.view.adapter.AdvertReadAdapter;
import com.example.tunguyen.manga.view.adapter.CustomAdapter;
import com.example.tunguyen.manga.view.database.AdvertMangas;
import com.example.tunguyen.manga.view.database.DatabaseHelper;
import com.example.tunguyen.manga.view.model.AdvertDto;
import com.example.tunguyen.manga.view.model.ControlDatabase;
import com.example.tunguyen.manga.view.model.Preference;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import org.lucasr.twowayview.widget.TwoWayView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FraHome extends Fragment {


    //ViewPager pager_banner;
    //InkPageIndicator indicator_banel;
    View view;
    TextView tv_count_advert, tv_count_advert2, txtReadmore, txtReadmore1;

    ////Slide///////
    TwoWayView lv_advert_feature;
    List<AdvertMangas> ItemAdvert;
    List<String> ListIdAdvert = new ArrayList<>();
    List<String> ListNameAdvert = new ArrayList<>();
    List<String> ListNameAuthorAdvert = new ArrayList<>();
    List<String> ListStatusAdvert = new ArrayList<>();
    List<String> ListStatusChapAdvert = new ArrayList<>();
    List<String> ListCountChapAdvert = new ArrayList<>();
    List<String> ListTypeAdvert = new ArrayList<>();
    List<String> ListCodeAdvert = new ArrayList<>();
    List<String> ListImgAdvert = new ArrayList<>();
    ////End Slide///

    ////Advert Read///////
    TwoWayView lv_advert_read;
    List<AdvertMangas> ItemAdvertRead;
    List<String> ListIdAdvertRead = new ArrayList<>();
    List<String> ListNameAdvertRead = new ArrayList<>();
    List<String> ListNameAuthorAdvertRead = new ArrayList<>();
    List<String> ListStatusAdvertRead = new ArrayList<>();
    List<String> ListStatusChapAdvertRead = new ArrayList<>();
    List<String> ListCountChapAdvertRead = new ArrayList<>();
    List<String> ListImgAdvertRead = new ArrayList<>();
    List<String> ListTypeAdvertRead = new ArrayList<>();
    List<String> ListCodeAdvertRead = new ArrayList<>();
    ////End Advert Read///////


    ////Advert Read///////
    TwoWayView lv_advert_popular;
    List<AdvertMangas> ItemAdvertPopular;
    List<String> ListIdAdvertPopular = new ArrayList<>();
    List<String> ListNameAdvertPopular = new ArrayList<>();
    List<String> ListNameAuthorAdvertPopular = new ArrayList<>();
    List<String> ListStatusAdvertPopular = new ArrayList<>();
    List<String> ListStatusChapAdvertPopular = new ArrayList<>();
    List<String> ListCountChapAdvertPopular = new ArrayList<>();
    List<String> ListImgAdvertPopular = new ArrayList<>();
    List<String> ListTypeAdvertPopular = new ArrayList<>();
    List<String> ListCodePopular = new ArrayList<>();

    ////End Advert Read///////
    private Dao<AdvertMangas, Integer> AdvertMangasDao;
    private List<AdvertMangas> AdvertMangasList;
    private DatabaseHelper databaseHelper = null;
    int countDow = 0;
    SwipeRefreshLayout swipeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        lv_advert_read = (TwoWayView) view.findViewById(R.id.lv_advert_read);
        lv_advert_feature = (TwoWayView) view.findViewById(R.id.lv_advert_feature);
        lv_advert_popular = (TwoWayView) view.findViewById(R.id.lv_advert_popular);
        tv_count_advert = (TextView) view.findViewById(R.id.tv_count_advert);
        tv_count_advert2 = (TextView) view.findViewById(R.id.tv_count_advert2);
        txtReadmore = (TextView) view.findViewById(R.id.txtReadmore);
        txtReadmore1 = (TextView) view.findViewById(R.id.txtReadmore1);

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(true);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (countDow >= 2) {
                            try {
                                AdvertMangasDao = getHelper().getAdvertMangasDao();
                                AdvertMangasList = AdvertMangasDao.queryForAll();
                                if (AdvertMangasList.size() >0) {
                                    LoadAdvertPopularBySqlite();
                                    LoadAdvertReadBySqlite();
                                    LoadAdvertFeatureBySqlite();
                                }else {
                                    callServiceSlide(1);
                                    callServiceAdvertRead(2);
                                    callServiceAdvertPopular(3);
                                }


                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            swipeLayout.setRefreshing(false);
                        }
                    }
                }, 5000);

            }
        });
        txtReadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = new AlphaAnimation(0.3f, 1.0f);
                animation.setDuration(1000);
                view.startAnimation(animation);
                Preference.idActionbar = 1;
                Preference.savePreference(getActivity());
                Intent intent_login = new Intent(getActivity(), AllAdvertBy.class);
                startActivity(intent_login);

            }
        });
        txtReadmore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = new AlphaAnimation(0.3f, 1.0f);
                animation.setDuration(1000);
                view.startAnimation(animation);
                Preference.idActionbar = 2;
                Preference.savePreference(getActivity());
                Intent intent_login = new Intent(getActivity(), AllAdvertBy.class);
                startActivity(intent_login);

            }
        });
        try {
            AdvertMangasDao = getHelper().getAdvertMangasDao();
            AdvertMangasList = AdvertMangasDao.queryForAll();
            if (AdvertMangasList.size() <= 0) {
                callServiceSlide(1);
                callServiceAdvertRead(2);
                callServiceAdvertPopular(3);
            } else {

                LoadAdvertPopularBySqlite();
                LoadAdvertReadBySqlite();
                LoadAdvertFeatureBySqlite();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return view;

    }

    public void LoadAdvertPopularBySqlite() {
        try {
            // This is how, a reference of DAO object can be done
            AdvertMangasDao = getHelper().getAdvertMangasDao();
            QueryBuilder<AdvertMangas, Integer> queryBuilder = AdvertMangasDao.queryBuilder();
            queryBuilder.orderBy("CountView", false);
            queryBuilder.orderBy("IdAdvertManga", false);
            AdvertMangasList = queryBuilder.query();
            tv_count_advert2.setText(AdvertMangasList.size() + " truyện");
            queryBuilder.limit(10);
            AdvertMangasList = queryBuilder.query();
            AdvertPopularAdapter adapter = new AdvertPopularAdapter(getActivity(), AdvertMangasList, "Advert Popular");
            lv_advert_popular.setAdapter(adapter);
            countDow = countDow + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void LoadAdvertReadBySqlite() {
        try {
            // This is how, a reference of DAO object can be done
            AdvertMangasDao = getHelper().getAdvertMangasDao();
            QueryBuilder<AdvertMangas, Integer> queryBuilder = AdvertMangasDao.queryBuilder();
            queryBuilder.orderBy("CountView", false);
            AdvertMangasList = queryBuilder.query();
            tv_count_advert2.setText(AdvertMangasList.size() + " truyện");
            queryBuilder.limit(10);
            AdvertMangasList = queryBuilder.query();
            AdvertReadAdapter adapter = new AdvertReadAdapter(getActivity(), AdvertMangasList, "Advert Popular");
            lv_advert_read.setAdapter(adapter);
            countDow = countDow + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void LoadAdvertFeatureBySqlite() {
        try {
            // This is how, a reference of DAO object can be done
            AdvertMangasDao = getHelper().getAdvertMangasDao();
            QueryBuilder<AdvertMangas, Integer> queryBuilder = AdvertMangasDao.queryBuilder();
            queryBuilder.orderBy("IdAdvertManga", false);
            queryBuilder.limit(10);
            AdvertMangasList = queryBuilder.query();

            AdvertFeaturedAdapter adapter = new AdvertFeaturedAdapter(getActivity(), AdvertMangasList, "Advert Popular");
            lv_advert_feature.setAdapter(adapter);

            countDow = countDow + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return databaseHelper;
    }

    ///LoadSlide///////
    private void loadSilde() {
        ItemAdvert = getDataSlide();
        try {
            for (int i = 0; i < ItemAdvert.size(); i++) {

                // pager_banner.setAdapter(new SlideAdapter(getActivity(), ItemAdvert));//pager_banner.setAdapter(slideAdapter);
                //indicator_banel.setViewPager(pager_banner);
                try {
                    AdvertFeaturedAdapter adapter = new AdvertFeaturedAdapter(getActivity(), ItemAdvert, "advert feature");
                    lv_advert_feature.setAdapter(adapter);
                } catch (Exception ex) {

                }
            }
        } catch (Exception ex) {

        }
    }

    private List<AdvertMangas> getDataSlide() {
        List<AdvertMangas> items = new ArrayList<>();
        for (int i = 0; i < ListIdAdvert.size(); i++) {
            items.add(new AdvertMangas(ListIdAdvert.get(i), ListNameAdvert.get(i), ListImgAdvert.get(i), ListNameAuthorAdvert.get(i), ListTypeAdvert.get(i), ListStatusChapAdvert.get(i),ListCodeAdvert.get(i)));
        }
        return items;
    }

    public void callServiceSlide(int id) {
        ResClien restClient = new ResClien();
        restClient.GetService().GetAdvertByTypeId(id,
                new Callback<List<AdvertDto>>() {
                    @Override
                    public void success(List<AdvertDto> AdvertDto, Response response) {

                        for (int i = 0; i < AdvertDto.size(); i++) {

                            String tmpStr10 = Integer.toString(AdvertDto.get(i).IdAdvertManga);
                            ListIdAdvert.add(tmpStr10);
                            ListNameAdvert.add(AdvertDto.get(i).NameAdvertManga);
                            ListImgAdvert.add(AdvertDto.get(i).ImgAdvertManga);
                            ListNameAuthorAdvert.add(AdvertDto.get(i).NameAuthorAdvertManga);
                            ListTypeAdvert.add(AdvertDto.get(i).TypeAdvertManga);
                            String tmpStatus = Integer.toString(AdvertDto.get(i).StatusChapAdvertManga);
                            ListStatusChapAdvert.add(tmpStatus);
                            ListCodeAdvert.add(AdvertDto.get(i).CodeAdvertManga);


                        }
                        loadSilde();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }
    ///End LoadSlide///

    ///LoadAdvertRead///
    private void loadDataAdvertRead() {

        ItemAdvertRead = getAllItemsAdvertRead();

        try {

            AdvertReadAdapter adapter = new AdvertReadAdapter(getActivity(), ItemAdvertRead, "project");
            lv_advert_read.setAdapter(adapter);

        } catch (Exception ex) {

        }
    }

    private List<AdvertMangas> getAllItemsAdvertRead() {
        List<AdvertMangas> items = new ArrayList<>();
        for (int i = 0; i < ListIdAdvertRead.size(); i++) {
            items.add(
                    new AdvertMangas(
                            ListIdAdvertRead.get(i),
                            ListNameAdvertRead.get(i),
                            ListImgAdvertRead.get(i),
                            ListNameAuthorAdvertRead.get(i),
                            ListTypeAdvertRead.get(i),
                            ListStatusChapAdvertRead.get(i),
                            ListCodeAdvertRead.get(i)
                    )
            );
        }
        return items;
    }

    public void callServiceAdvertRead(int id) {
        ResClien restClient = new ResClien();
        restClient.GetService().GetAdvertByTypeId(id, new Callback<List<AdvertDto>>() {
            @Override
            public void success(List<AdvertDto> AdvertDto, Response response) {

                for (int i = 0; i < AdvertDto.size(); i++) {

                    String tmpStr10 = Integer.toString(AdvertDto.get(i).IdAdvertManga);
                    String tmpStatus = Integer.toString(AdvertDto.get(i).StatusChapAdvertManga);
                    ListIdAdvertRead.add(tmpStr10);
                    ListNameAdvertRead.add(AdvertDto.get(i).NameAdvertManga);
                    ListImgAdvertRead.add(AdvertDto.get(i).ImgAdvertManga);
                    ListNameAuthorAdvertRead.add(AdvertDto.get(i).NameAuthorAdvertManga);
                    ListTypeAdvertRead.add(AdvertDto.get(i).TypeAdvertManga);
                    ListStatusChapAdvertRead.add(tmpStatus);
                    ListCodeAdvertRead.add(AdvertDto.get(i).CodeAdvertManga);
                }
                int count = AdvertDto.size();
                tv_count_advert.setText(count + " truyện");
                loadDataAdvertRead();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("myLogs", "-------ERROR-------Slide");
                Log.d("myLogs", Log.getStackTraceString(error));
            }
        });
    }
    ///End LoadAdvertRead///

    ///LoadAdvertPopular///
    private void loadDataAdvertPopular() {

        ItemAdvertPopular = getAllItemsAdvertPopular();

        try {

            AdvertPopularAdapter adapter = new AdvertPopularAdapter(getActivity(), ItemAdvertPopular, "Advert Popular");
            lv_advert_popular.setAdapter(adapter);
        } catch (Exception ex) {

        }
    }

    private List<AdvertMangas> getAllItemsAdvertPopular() {
        List<AdvertMangas> items = new ArrayList<>();
        for (int i = 0; i < ListIdAdvertPopular.size(); i++) {
            items.add(
                    new AdvertMangas(
                            ListIdAdvertPopular.get(i),
                            ListNameAdvertPopular.get(i),
                            ListImgAdvertPopular.get(i),
                            ListNameAuthorAdvertPopular.get(i),
                            ListTypeAdvertPopular.get(i),
                            ListStatusChapAdvertPopular.get(i),
                            ListCodePopular.get(i)
                    )
            );
        }
        return items;
    }

    public void callServiceAdvertPopular(int id) {
        ResClien restClient = new ResClien();
        restClient.GetService().GetAdvertByTypeId(id, new Callback<List<AdvertDto>>() {
            @Override
            public void success(List<AdvertDto> AdvertDto, Response response) {

                for (int i = 0; i < AdvertDto.size(); i++) {

                    String tmpStr10 = Integer.toString(AdvertDto.get(i).IdAdvertManga);
                    ListIdAdvertPopular.add(tmpStr10);
                    ListNameAdvertPopular.add(AdvertDto.get(i).NameAdvertManga);
                    ListImgAdvertPopular.add(AdvertDto.get(i).ImgAdvertManga);
                    ListNameAuthorAdvertPopular.add(AdvertDto.get(i).NameAuthorAdvertManga);
                    ListTypeAdvertPopular.add(AdvertDto.get(i).TypeAdvertManga);
                    String tmpStatus = Integer.toString(AdvertDto.get(i).StatusChapAdvertManga);
                    ListStatusChapAdvertPopular.add(tmpStatus);
                    ListCodePopular.add(AdvertDto.get(i).CodeAdvertManga);
                }
                int count = AdvertDto.size();
                tv_count_advert2.setText(count + " truyện");
                loadDataAdvertPopular();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("myLogs", "-------ERROR-------Slide");
                Log.d("myLogs", Log.getStackTraceString(error));
            }
        });
    }
    ///End LoadAdvertRead///
}
