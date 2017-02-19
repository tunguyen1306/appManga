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
import com.example.tunguyen.manga.view.adapter.CustomAdapter;
import com.example.tunguyen.manga.view.database.AdvertMangas;
import com.example.tunguyen.manga.view.database.DatabaseHelper;
import com.example.tunguyen.manga.view.model.AdvertDto;
import com.example.tunguyen.manga.view.model.ControlDatabase;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by TuNguyen on 02/19/2017.
 */

public class FraSearchAdvert extends Fragment {
    GridView grid;
    SwipeRefreshLayout swipeLayout;
    private Dao<AdvertMangas, Integer> AdvertMangasDao;
    private List<AdvertMangas> AdvertMangasList;
    private DatabaseHelper databaseHelper = null;

    ////Advert Read///////
    List<AdvertMangas> ItemAllAdvert;
    List<String> ListIdAllAdvert = new ArrayList<>();
    List<String> ListNameAllAdvert = new ArrayList<>();
    List<String> ListNameAuthorAllAdvert = new ArrayList<>();
    List<String> ListStatusAllAdvert = new ArrayList<>();
    List<String> ListStatusChapAllAdvert = new ArrayList<>();
    List<String> ListCountChapAllAdvert = new ArrayList<>();
    List<String> ListImgAllAdvert = new ArrayList<>();
    List<String> ListCountAllAdvert = new ArrayList<>();
    List<String> ListTypeAllAdvert = new ArrayList<>();

    ////End Advert Read///////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_all_advert, container, false);
        grid=(GridView)view.findViewById(R.id.gridView1);
        LoadAllAdvertBySqlite();
        swipeLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_container);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(true);
                ControlDatabase.AddAllAdvert(getContext());

                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        try {
                            AdvertMangasDao = getHelper().getAdvertMangasDao();
                            AdvertMangasList = AdvertMangasDao.queryForAll();
                            if (AdvertMangasList.size() >0) {
                                // ControlDatabase.AddAllAdvert(getContext());
                                LoadAllAdvertBySqlite();

                            }else {
                                callServiceAllAdvert();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        swipeLayout.setRefreshing(false);

                    }
                }, 5000);

            }
        });
        try {
            AdvertMangasDao = getHelper().getAdvertMangasDao();
            AdvertMangasList = AdvertMangasDao.queryForAll();
            if (AdvertMangasList.size() <= 0) {
                callServiceAllAdvert();
            } else {


                LoadAllAdvertBySqlite();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return view;
    }


    ///End LoadAdvertRead///
    public void LoadAllAdvertBySqlite()
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
    ///LoadAllAdvert///
    private void loadDataAllAdvert() {

        ItemAllAdvert = getAllItemsAllAdvert();

        try {

            CustomAdapter adapter = new CustomAdapter(getActivity(), ItemAllAdvert);
            grid.setAdapter(adapter);
        } catch (Exception ex) {

        }
    }
    private List<AdvertMangas> getAllItemsAllAdvert() {
        List<AdvertMangas> items = new ArrayList<>();
        for (int i = 0; i < ListIdAllAdvert.size(); i++) {
            items.add(
                    new AdvertMangas(
                            ListIdAllAdvert.get(i),
                            ListNameAllAdvert.get(i),
                            ListImgAllAdvert.get(i) ,
                            ListNameAuthorAllAdvert.get(i),
                            ListTypeAllAdvert.get(i),
                            ListStatusAllAdvert.get(i)
                    )
            );
        }
        return items;
    }
    public void callServiceAllAdvert() {
        ResClien restClient = new ResClien();
        restClient.GetService().GetListAdvert(new Callback<List<AdvertDto>>() {
            @Override
            public void success(List<AdvertDto> AdvertDto, Response response) {
                for (int i = 0; i < AdvertDto.size(); i++) {

                    String tmpStr10 = Integer.toString(AdvertDto.get(i).IdAdvertManga);
                    String tmpStatus = Integer.toString(AdvertDto.get(i).StatusChapAdvertManga);
                    ListIdAllAdvert.add(tmpStr10);
                    ListNameAllAdvert.add(AdvertDto.get(i).NameAdvertManga);
                    ListImgAllAdvert.add(AdvertDto.get(i).ImgAdvertManga);
                    ListNameAuthorAllAdvert.add(AdvertDto.get(i).NameAuthorAdvertManga);
                    ListTypeAllAdvert.add(AdvertDto.get(i).TypeAdvertManga);
                    ListStatusAllAdvert.add(tmpStatus);


                }
                loadDataAllAdvert();
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
