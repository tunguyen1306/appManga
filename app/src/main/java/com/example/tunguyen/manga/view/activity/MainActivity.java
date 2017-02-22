package com.example.tunguyen.manga.view.activity;
import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.MatrixCursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.adapter.AdvertFeaturedAdapter;
import com.example.tunguyen.manga.view.adapter.AllAdvertAdapter;
import com.example.tunguyen.manga.view.adapter.CustomAdapter;
import com.example.tunguyen.manga.view.adapter.ExampleAdapter;
import com.example.tunguyen.manga.view.adapter.SearchAdvertAdapter;
import com.example.tunguyen.manga.view.database.AdvertMangas;
import com.example.tunguyen.manga.view.database.DatabaseHelper;
import com.example.tunguyen.manga.view.fragment.FraAllAdvert;
import com.example.tunguyen.manga.view.fragment.FraSearchAdvert;
import com.example.tunguyen.manga.view.fragment.FraUpdateAdvert;
import com.example.tunguyen.manga.view.fragment.FraHome;
import com.example.tunguyen.manga.view.model.AdvertDto;
import com.example.tunguyen.manga.view.model.MyService;
import com.example.tunguyen.manga.view.model.Preference;
import com.google.android.gms.common.api.GoogleApiClient;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.squareup.picasso.Picasso;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.tunguyen.manga.view.model.AdvertDto.IdAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.ImgAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.NameAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.TypeAdvertRefer;

public class MainActivity extends ActionBarActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    TextView txtUpdateAdvert, txtAllAdvert, txtFeauture;

    boolean doubleBackToExitPressedOnce = false;

    private GoogleApiClient client2;
    GridView grid;

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
    List<String> ListCodeAdvertManga = new ArrayList<>();

    ////End Advert Read///////
    SearchAdvertAdapter myAppAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.tunguyen.manga",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }

        setContentView(R.layout.activity_main);
//        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
//        telephonyManager.getDeviceId();// Product
        //String SerialDevice = telephonyManager.getLine1Number();
        String OsVersion = System.getProperty("os.version");
        String OsInCremental = Build.VERSION.INCREMENTAL;
        int OSAPILevel = Build.VERSION.SDK_INT;
        String OsDevice = Build.DEVICE;
        String ModelDevice = Build.MODEL;
        String ProductDevice = Build.PRODUCT;
        // SerialDeviceRefer=SerialDevice;
        Preference.savePreference(getApplicationContext());
        //Preference.AddDevice(SerialDevice,OsVersion,OsInCremental,OsDevice,ModelDevice,OSAPILevel,ProductDevice);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        setupActionBar();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();


        final Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                startService();
            }
        }, 20, 72000000);
        /////replace fragment/////
        Fragment fragment = null;
        fragment = new FraHome();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_main, fragment);
        transaction.commit();


        txtFeauture = (TextView) findViewById(R.id.txtFeauture);
        txtAllAdvert = (TextView) findViewById(R.id.txtAllAdvert);
        txtUpdateAdvert = (TextView) findViewById(R.id.txtUpdateAdvert);

        ///End Advert///

        ///Event Button///
        txtFeauture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtUpdateAdvert.setBackgroundColor(getResources().getColor(R.color.black));
                txtUpdateAdvert.setTextColor(getResources().getColor(R.color.colorTextOnButton));
                txtAllAdvert.setBackgroundColor(getResources().getColor(R.color.black));
                txtAllAdvert.setTextColor(getResources().getColor(R.color.colorTextOnButton));
                txtFeauture.setBackgroundColor(getResources().getColor(R.color.backgroundIcon));
                txtFeauture.setTextColor(getResources().getColor(R.color.TextColor));

                Fragment fragment = null;
                fragment = new FraHome();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_main, fragment);
                transaction.commit();


            }
        });

        txtAllAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtUpdateAdvert.setBackgroundColor(getResources().getColor(R.color.black));
                txtUpdateAdvert.setTextColor(getResources().getColor(R.color.colorTextOnButton));
                txtFeauture.setBackgroundColor(getResources().getColor(R.color.black));
                txtFeauture.setTextColor(getResources().getColor(R.color.colorTextOnButton));
                txtAllAdvert.setBackgroundColor(getResources().getColor(R.color.backgroundIcon));
                txtAllAdvert.setTextColor(getResources().getColor(R.color.TextColor));

                Fragment fragment = null;
                fragment = new FraAllAdvert();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_main, fragment);
                transaction.commit();

            }
        });

        txtUpdateAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtAllAdvert.setBackgroundColor(getResources().getColor(R.color.black));
                txtAllAdvert.setTextColor(getResources().getColor(R.color.colorTextOnButton));
                txtFeauture.setBackgroundColor(getResources().getColor(R.color.black));
                txtFeauture.setTextColor(getResources().getColor(R.color.colorTextOnButton));
                txtUpdateAdvert.setBackgroundColor(getResources().getColor(R.color.backgroundIcon));
                txtUpdateAdvert.setTextColor(getResources().getColor(R.color.TextColor));

                Fragment fragment = null;
                fragment = new FraUpdateAdvert();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_main, fragment);
                transaction.commit();


            }
        });


        grid=(GridView)findViewById(R.id.gridView1);
        callServiceAllAdvert();
    }//end Oncreate



    public void startService() {
        startService(new Intent(getBaseContext(), MyService.class));
    }

    public void stopService() {
        stopService(new Intent(getBaseContext(), MyService.class));
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
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.actionbar, null);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
                break;
            case R.id.nav_advert_save:
                Intent viewd = new Intent(getApplicationContext(), ViewedAdvert.class);
                startActivity(viewd);
                break;
            case R.id.nav_advert_favorite:
                Intent favorite = new Intent(getApplicationContext(), FavoriteAdvert.class);
                startActivity(favorite);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }



    @Override
    public void onStart() {
        super.onStart();



    }

    @Override
    public void onStop() {
        super.onStop();
       
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

    private void changeSearchViewTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {



        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_search, menu);
        // Associate searchable configuration with the SearchView
        this.menu = menu;
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                LoadAdverBySqlite(newText);
                return false;
            }
        });
        return true;
    }

    private Menu menu;

    ///LoadAllAdvert///
    private void loadDataAllAdvert() {

        ItemAllAdvert = getAllItemsAllAdvert();

        try {

            CustomAdapter adapter = new CustomAdapter(getBaseContext(), ItemAllAdvert);
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
                            ListStatusAllAdvert.get(i),
                            ListCodeAdvertManga.get(i)

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
                    ListCodeAdvertManga.add(AdvertDto.get(i).CodeAdvertManga);

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

    private Dao<AdvertMangas, Integer> AdvertMangasDao;
    private List<AdvertMangas> AdvertMangasList;
    private DatabaseHelper databaseHelper = null;

    public void LoadAdverBySqlite(String query) {
        try {

            AdvertMangasDao = getHelper().getAdvertMangasDao();
            QueryBuilder<AdvertMangas, Integer> queryBuilder = AdvertMangasDao.queryBuilder();
            queryBuilder.where().like("CodeAdvertManga","%"+ query+"%");
            AdvertMangasList = queryBuilder.query();
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

                // Cursor
                String[] columns = new String[] { "_id", "text" };
                Object[] temp = new Object[] { 0, "default" };

                MatrixCursor cursor = new MatrixCursor(columns);

                for(int i = 0; i < AdvertMangasList.size(); i++) {

                    temp[0] = i;
                    temp[1] = AdvertMangasList.get(i);

                    cursor.addRow(temp);

                }

                // SearchView
                SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

                final SearchView search = (SearchView) menu.findItem(R.id.menu_search).getActionView();

                search.setSuggestionsAdapter(new ExampleAdapter(this, cursor, AdvertMangasList));

            }

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
}
