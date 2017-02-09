package com.example.tunguyen.manga.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.activity.ResClien;
import com.example.tunguyen.manga.view.adapter.AdvertRelateAdapter;
import com.example.tunguyen.manga.view.adapter.AdvertViewedAdapter;
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

public class FraAllAdvert extends Fragment {
        GridView grid;
    SwipeRefreshLayout swipeLayout;
    private Dao<AdvertMangas, Integer> AdvertMangasDao;
    private List<AdvertMangas> AdvertMangasList;
    private DatabaseHelper databaseHelper = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_all_advert, container, false);
        grid=(GridView)view.findViewById(R.id.gridView1);
        LoadAllAdvert();
        swipeLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_container);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(true);
                ControlDatabase.AddAllAdvert(getContext());

                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {

                            swipeLayout.setRefreshing(false);

                    }
                }, 5000);

            }
        });
        return view;
    }


    ///End LoadAdvertRead///
    public void LoadAllAdvert()
    {
        try {
            AdvertMangasDao =  getHelper().getAdvertMangasDao();
            AdvertMangasList = AdvertMangasDao.queryForAll();
            if (AdvertMangasList.size()<=0)
            {
               ControlDatabase.AddAllAdvert(getContext());
            }
            CustomAdapter adapter = new CustomAdapter(getActivity(), AdvertMangasList);
            grid.setAdapter(adapter);
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
