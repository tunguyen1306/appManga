package com.example.tunguyen.manga.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.activity.ResClien;
import com.example.tunguyen.manga.view.adapter.ListChapterAdapter;
import com.example.tunguyen.manga.view.database.ChapterMangas;
import com.example.tunguyen.manga.view.database.DatabaseHelper;
import com.example.tunguyen.manga.view.model.AdvertDto;
import com.example.tunguyen.manga.view.model.ChapterDto;
import com.example.tunguyen.manga.view.model.Preference;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ducthien on 17/01/2017.
 */

public class FraListChapter extends Fragment {
    CardView cardView;
    ListView list;
    View view;
    TextView txtChapName;
    private DatabaseHelper databaseHelper = null;
    private Dao<ChapterMangas, Integer> ChapterMangasDao;
    private  List<ChapterMangas> chapterMangasList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_list_chapter, container, false);
        cardView=(CardView)view.findViewById(R.id.cardViewChapter);
        list=(ListView)view.findViewById(R.id.lvChapter);
        //txtChapName=(TextView)view.findViewById(R.id.txtNameChapter);
        Preference.restorePreference(getContext());
        LoadDetailAdvertById(AdvertDto.IdAdvertRefer);
        LoadChapter(AdvertDto.IdAdvertRefer);
        return view;
    }
    ///Load Detail Advert by ID///
    public void LoadDetailAdvertById(int id)
    {
        ResClien resClient=new ResClien();
        resClient.GetService().GetChapByAdvertID(id, new Callback<List<ChapterDto>>() {
            @Override
            public void success(List<ChapterDto> ChapterDto, Response response) {

                for (int i = 0; i < ChapterDto.size(); i++) {
                Preference.AddChapterSqlite(getContext(),ChapterDto.get(i).IdChapterManga,ChapterDto.get(i).NameChapterManga,ChapterDto.get(i).Link,ChapterDto.get(i).IdAdvertManga);
                }
                LoadChapter(AdvertDto.IdAdvertRefer);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    ///End Load Detail Advert by ID///

    private DatabaseHelper getHelper() {

        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(getContext(),DatabaseHelper.class);
        }
        return databaseHelper;
    }
    public void LoadChapter(int IdAdvertManga)
    {
        try {
            // This is how, a reference of DAO object can be done
            ChapterMangasDao =  getHelper().getChapterMangasDao();
            QueryBuilder<ChapterMangas, Integer> queryBuilder = ChapterMangasDao.queryBuilder();
            queryBuilder.where().eq("IdAdvertManga",IdAdvertManga);
            chapterMangasList = queryBuilder.query();
            list.setAdapter(new ListChapterAdapter(getActivity(), chapterMangasList));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
