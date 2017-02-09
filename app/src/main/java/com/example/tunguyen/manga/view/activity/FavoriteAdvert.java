package com.example.tunguyen.manga.view.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.adapter.AdvertFavoriteAdapter;
import com.example.tunguyen.manga.view.database.AdvertMangas;
import com.example.tunguyen.manga.view.database.DatabaseHelper;
import com.example.tunguyen.manga.view.model.Preference;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by TuNguyen on 02/06/2017.
 */

public class FavoriteAdvert extends ActionBarActivity {
    private Toolbar toolbar;
    DrawerLayout drawer;
    ListView list;
    private Dao<AdvertMangas, Integer> AdvertMangasDao;
    private List<AdvertMangas> AdvertMangasList;
    private DatabaseHelper databaseHelper = null;

    private static String PREF_NAME = "pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_advert);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        list=(ListView)findViewById(R.id.lvAdvertFavorite);
        LoadAdvertView();
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
        tv_title.setText("Danh sách yêu thích");
        ln_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }
    public void LoadAdvertView()
    {
        try {
            // This is how, a reference of DAO object can be done
            AdvertMangasDao =  getHelper().getAdvertMangasDao();
            QueryBuilder<AdvertMangas, Integer> queryBuilder = AdvertMangasDao.queryBuilder();
            queryBuilder.where().eq("IdFavorite",2);
            queryBuilder.orderBy("Id",false);
            AdvertMangasList = queryBuilder.query();
            list.setAdapter(new AdvertFavoriteAdapter(this,AdvertMangasList));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

		/*
		 * You'll need this in your class to release the helper when done.
		 */
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

}
