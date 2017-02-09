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
    TextView tv_count_advert,tv_count_advert2,txtReadmore,txtReadmore1;

    TwoWayView lv_advert_feature;
    TwoWayView lv_advert_read;
    TwoWayView lv_advert_popular;

    private Dao<AdvertMangas, Integer> AdvertMangasDao;
    private List<AdvertMangas> AdvertMangasList;
    private DatabaseHelper databaseHelper = null;
   int countDow=0;
    SwipeRefreshLayout swipeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        lv_advert_read = (TwoWayView) view.findViewById(R.id.lv_advert_read);
        lv_advert_feature = (TwoWayView) view.findViewById(R.id.lv_advert_feature);
        lv_advert_popular=(TwoWayView) view.findViewById(R.id.lv_advert_popular);
        tv_count_advert=(TextView) view.findViewById(R.id.tv_count_advert);
        tv_count_advert2=(TextView) view.findViewById(R.id.tv_count_advert2);
        txtReadmore=(TextView) view.findViewById(R.id.txtReadmore);
        txtReadmore1=(TextView) view.findViewById(R.id.txtReadmore1);

        swipeLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_container);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(true);
                ControlDatabase.AddAllAdvert(getContext());
                LoadAdvertPopular();
                LoadAdvertRead();
                LoadAdvertFeature();
                if (countDow>=2)
                {
                    swipeLayout.setRefreshing(false);
                }
            }
        });
        txtReadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = new AlphaAnimation(0.3f, 1.0f);
                animation.setDuration(1000);
                view.startAnimation(animation);
                Preference.idActionbar=1;
                Preference.savePreference(getActivity());
                Intent intent_login=new Intent(getActivity(),AllAdvertBy.class);
                startActivity(intent_login);

            }
        });
        txtReadmore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = new AlphaAnimation(0.3f, 1.0f);
                animation.setDuration(1000);
                view.startAnimation(animation);
                Preference.idActionbar=2;
                Preference.savePreference(getActivity());
                Intent intent_login=new Intent(getActivity(),AllAdvertBy.class);
                startActivity(intent_login);

            }
        });
        try {
            AdvertMangasDao =  getHelper().getAdvertMangasDao();
            AdvertMangasList = AdvertMangasDao.queryForAll();
            if (AdvertMangasList.size()<=0)
            {
                ControlDatabase.AddAllAdvert(getContext());
            }
            LoadAdvertPopular();
            LoadAdvertRead();
            LoadAdvertFeature();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return view;

    }

    public void LoadAdvertPopular()
    {
        try {
            // This is how, a reference of DAO object can be done
            AdvertMangasDao =  getHelper().getAdvertMangasDao();
            QueryBuilder<AdvertMangas, Integer> queryBuilder = AdvertMangasDao.queryBuilder();
            queryBuilder.where().eq("TypeStatusAdvertManga",3);
            queryBuilder.orderBy("CountView",false);
            AdvertMangasList = queryBuilder.query();

            AdvertPopularAdapter adapter = new AdvertPopularAdapter(getActivity(), AdvertMangasList, "Advert Popular");
            lv_advert_popular.setAdapter(adapter);
            countDow=  countDow+1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void LoadAdvertRead()
    {
        try {
            // This is how, a reference of DAO object can be done
            AdvertMangasDao =  getHelper().getAdvertMangasDao();
            QueryBuilder<AdvertMangas, Integer> queryBuilder = AdvertMangasDao.queryBuilder();
            queryBuilder.where().eq("TypeStatusAdvertManga",2);
            queryBuilder.orderBy("CountView",false);
            AdvertMangasList = queryBuilder.query();

            AdvertReadAdapter adapter = new AdvertReadAdapter(getActivity(), AdvertMangasList, "Advert Popular");
            lv_advert_read.setAdapter(adapter);
            countDow=  countDow+1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void LoadAdvertFeature()
    {
        try {
            // This is how, a reference of DAO object can be done
            AdvertMangasDao =  getHelper().getAdvertMangasDao();
            QueryBuilder<AdvertMangas, Integer> queryBuilder = AdvertMangasDao.queryBuilder();
            queryBuilder.where().eq("TypeStatusAdvertManga",1);
            queryBuilder.orderBy("CountView",false);
            queryBuilder.orderBy("CountView",false);
            AdvertMangasList = queryBuilder.query();

            AdvertFeaturedAdapter adapter = new AdvertFeaturedAdapter(getActivity(), AdvertMangasList, "Advert Popular");
            lv_advert_feature.setAdapter(adapter);
            countDow=  countDow+1;
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

}
