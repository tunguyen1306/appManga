package com.example.tunguyen.manga.view.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.tunguyen.manga.view.activity.ResClien;
import com.example.tunguyen.manga.view.database.AdvertMangas;
import com.example.tunguyen.manga.view.database.AdvertViewedMangas;
import com.example.tunguyen.manga.view.database.ChapterMangas;
import com.example.tunguyen.manga.view.database.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Preference {
    private static String PREF_NAME = "pref";

    private static SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static int idActionbar;

    public static void savePreference(Context context) {


        SharedPreferences.Editor edit = getPref(context).edit();
        edit.putInt("IdAdvertRefer", AdvertDto.IdAdvertRefer);
        edit.putString("NameAdvertRefer", AdvertDto.NameAdvertRefer);
        edit.putInt("CountViewRefer", AdvertDto.CountViewRefer);
        edit.putString("TypeAdvertRefer", AdvertDto.TypeAdvertRefer);
        ///Chapter///
        edit.putInt("IdChapterRefer", ChapterDto.IdChapterRefer);
        edit.putString("NameChapterRefer", ChapterDto.NameChapterRefer);

        ///Device///
        edit.putString("SerialDeviceRefer", DeviceDto.SerialDeviceRefer);

        ///Action bar///
        edit.putInt("idActionbar", idActionbar);

        edit.clear();
        edit.commit();
    }

    public static void restorePreference(Context context) {
        AdvertDto.IdAdvertRefer = getPref(context).getInt("IdAdvertRefer", 0);
        AdvertDto.NameAdvertRefer = getPref(context).getString("NameAdvertRefer", "");
        AdvertDto.CountViewRefer = getPref(context).getInt("CountViewRefer", 0);
        AdvertDto.TypeAdvertRefer = getPref(context).getString("TypeAdvertRefer", "");
        ///Chapter///
        ChapterDto.IdChapterRefer = getPref(context).getInt("IdChapterRefer", 0);
        ChapterDto.NameChapterRefer = getPref(context).getString("NameChapterRefer", "");

        ///Device///
        DeviceDto.SerialDeviceRefer = getPref(context).getString("SerialDeviceRefer", "");

        ///Action bar///
        idActionbar = getPref(context).getInt("idActionbar", 0);
    }

    public static void CountView(int id, int idCount) {
        ResClien resClient = new ResClien();
        resClient.GetService().CountView(id, idCount, new Callback<List<AdvertDto>>() {
            @Override
            public void success(List<AdvertDto> advertDtos, Response response) {
                AdvertDto.CountViewRefer = advertDtos.get(0).CountView;
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public static void AddDevice(String serial, String model, String product, String imei, String osversion, int osapiLevel, String os) {
        ResClien resClient = new ResClien();
        resClient.GetService().AddDevice(serial, model, product, imei, osversion, osapiLevel, os, new Callback<List<DeviceDto>>() {
            @Override
            public void success(List<DeviceDto> advertDtos, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private static DatabaseHelper databaseHelper = null;
    private static Dao<ChapterMangas, Integer> ChapterMangasDao;
    private static List<ChapterMangas> chapterMangasList;

    public static void AddChapterSqlite(Context context, int IdChapterManga, String NameChapterManga, String Link, int IdAdvertManga) {
        try {
            ChapterMangasDao = getHelper(context).getChapterMangasDao();
            QueryBuilder<ChapterMangas, Integer> queryBuilder = ChapterMangasDao.queryBuilder();
            queryBuilder.where().eq("IdAdvertManga", IdAdvertManga).and().eq("IdChapterManga", IdChapterManga);
            chapterMangasList = queryBuilder.query();
            if (chapterMangasList.size() <= 0) {
                final ChapterMangas chapterMangas = new ChapterMangas();
                chapterMangas.IdAdvertManga = IdAdvertManga;
                chapterMangas.NameChapterManga = NameChapterManga;
                chapterMangas.Link = Link;
                chapterMangas.IdChapterManga = IdChapterManga;
                chapterMangas.CheckChapterManga = 1;
                try {
                    final Dao<ChapterMangas, Integer> ChapterMangas = getHelper(context).getChapterMangasDao();
                    ChapterMangas.create(chapterMangas);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static DatabaseHelper getHelper(Context context) {

        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    private static Dao<AdvertMangas, Integer> AdvertMangasDao;
    private static List<AdvertMangas> AdvertMangasList;

    public static void AddAllAdvertMangaSqlite(Context context, int IdAdvertManga, String NameAdvertManga, String ImgAdvertManga,
                                               String NameAuthorAdvertManga, String DesAdvertManga, String TypeAdvertManga, int CountView, int TypeStatusAdvertManga,String CodeAdvertManga,int StatusAdvertManga) {
        try {

            AdvertMangasDao = getHelper(context).getAdvertMangasDao();
            QueryBuilder<AdvertMangas, Integer> queryBuilder = AdvertMangasDao.queryBuilder();
            queryBuilder.where().eq("IdAdvertManga", IdAdvertManga);
            AdvertMangasList = queryBuilder.query();
            if (AdvertMangasList.size() <= 0) {
                final AdvertMangas AdvertMangas = new AdvertMangas();
                AdvertMangas.IdAdvertManga = IdAdvertManga;
                AdvertMangas.NameAdvertManga = NameAdvertManga;
                AdvertMangas.ImgAdvertManga = ImgAdvertManga;
                AdvertMangas.NameAuthorAdvertManga = NameAuthorAdvertManga;
                AdvertMangas.DesAdvertManga = DesAdvertManga;
                AdvertMangas.TypeAdvertManga = TypeAdvertManga;
                AdvertMangas.StatusAdvertManga = StatusAdvertManga;
                AdvertMangas.CountView = CountView;
                AdvertMangas.TypeStatusAdvertManga = TypeStatusAdvertManga;
                AdvertMangas.IdFavorite = 1;
                AdvertMangas.CodeAdvertManga=CodeAdvertManga;
                try {
                    final Dao<AdvertMangas, Integer> advertMangas = getHelper(context).getAdvertMangasDao();
                    advertMangas.create(AdvertMangas);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                UpdateBuilder<AdvertMangas, Integer> updateBuilder = AdvertMangasDao.updateBuilder();
                updateBuilder.updateColumnValue("IdAdvertManga", IdAdvertManga);
                updateBuilder.updateColumnValue("NameAdvertManga", NameAdvertManga);
                updateBuilder.updateColumnValue("ImgAdvertManga", ImgAdvertManga);
                updateBuilder.updateColumnValue("NameAuthorAdvertManga", NameAuthorAdvertManga);
                updateBuilder.updateColumnValue("DesAdvertManga", DesAdvertManga);
                updateBuilder.updateColumnValue("TypeAdvertManga", TypeAdvertManga);
                updateBuilder.updateColumnValue("CountView", CountView);
                updateBuilder.updateColumnValue("StatusAdvertManga", StatusAdvertManga);
                updateBuilder.updateColumnValue("TypeStatusAdvertManga", TypeStatusAdvertManga);
                updateBuilder.updateColumnValue("CodeAdvertManga", CodeAdvertManga);
                updateBuilder.where().eq("IdAdvertManga", IdAdvertManga);
                updateBuilder.update();
            }
            DeleteBuilder<AdvertMangas, Integer>deleteBuilder = AdvertMangasDao.deleteBuilder();
            deleteBuilder.where().eq("StatusAdvertManga", 0);
            AdvertMangasDao.delete(deleteBuilder.prepare());


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Dao<AdvertViewedMangas, Integer> AdvertViewedMangasDao;
    private static List<AdvertViewedMangas> AdvertViewedMangasList;

    public static void AddAdvertViewedSqlite(Context context, int IdAdvertManga, String NameAdvertManga, String ImgAdvertManga, String NameChapManga, int IdChapterManga) {
        try {
            AdvertViewedMangasDao = getHelper(context).getAdvertViewedMangasDao();
            QueryBuilder<AdvertViewedMangas, Integer> queryBuilder = AdvertViewedMangasDao.queryBuilder();
            queryBuilder.where().eq("IdAdvertManga", IdAdvertManga);
            AdvertViewedMangasList = queryBuilder.query();
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String formattedDate = df.format(c.getTime());

            if (AdvertViewedMangasList.size() <= 0) {
                final AdvertViewedMangas AdvertViewedMangas = new AdvertViewedMangas();
                AdvertViewedMangas.IdAdvertManga = IdAdvertManga;
                AdvertViewedMangas.NameAdvertManga = NameAdvertManga;
                AdvertViewedMangas.ImgAdvertManga = ImgAdvertManga;
                AdvertViewedMangas.NameChapManga = NameChapManga;
                AdvertViewedMangas.IdChapterManga = IdChapterManga;
                AdvertViewedMangas.PositionItemChapterManga = 0;
                AdvertViewedMangas.TimeUpdatedChapterManga = formattedDate;

                try {
                    final Dao<AdvertViewedMangas, Integer> advertViewedMangas = getHelper(context).getAdvertViewedMangasDao();
                    advertViewedMangas.create(AdvertViewedMangas);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    final Dao<AdvertViewedMangas, Integer> advertViewedMangas = getHelper(context).getAdvertViewedMangasDao();

                    UpdateBuilder<AdvertViewedMangas, Integer> updateBuilder = advertViewedMangas.updateBuilder();
                    updateBuilder.updateColumnValue("NameChapterManga", NameChapManga);
                    updateBuilder.updateColumnValue("IdChapterManga", IdChapterManga);
                    updateBuilder.updateColumnValue("NameAdvertManga", NameAdvertManga);
                    updateBuilder.updateColumnValue("TimeUpdatedChapterManga", formattedDate);
                    updateBuilder.where().eq("id", AdvertViewedMangasList.get(0).Id);
                    updateBuilder.update();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void UpdateChapterSqlite(Context context, int IdChapterManga) {
        try {
            final Dao<ChapterMangas, Integer> ChapterMangas = getHelper(context).getChapterMangasDao();
            UpdateBuilder<ChapterMangas, Integer> updateBuilder = ChapterMangas.updateBuilder();
            updateBuilder.updateColumnValue("CheckChapterManga", 0);
            updateBuilder.where().eq("IdChapterManga", IdChapterManga);
            updateBuilder.update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void UpdatePositionViewedSqlite(Context context, int PositionItemChapterManga, int IdAdvertManga, int IdChapterManga) {
        try {
            final Dao<AdvertViewedMangas, Integer> advertViewedMangas = getHelper(context).getAdvertViewedMangasDao();

            UpdateBuilder<AdvertViewedMangas, Integer> updateBuilder = advertViewedMangas.updateBuilder();
            updateBuilder.updateColumnValue("PositionItemChapterManga", PositionItemChapterManga);
            updateBuilder.where().eq("idadvertmanga", IdAdvertManga).and().eq("idchaptermanga", IdChapterManga);
            updateBuilder.update();
            AdvertViewedMangasDao = getHelper(context).getAdvertViewedMangasDao();
            QueryBuilder<AdvertViewedMangas, Integer> queryBuilder = AdvertViewedMangasDao.queryBuilder();
            AdvertViewedMangasList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void UpdateAdvertFavoriteMangasSqlite(Context context, int IdAdvertManga, String NameAdvertManga, String ImgAdvertManga,int IdFavorite) {
        try {
            final Dao<AdvertMangas, Integer> AdvertMangasDao = getHelper(context).getAdvertMangasDao();
            UpdateBuilder<AdvertMangas, Integer> updateBuilder = AdvertMangasDao.updateBuilder();
            updateBuilder.updateColumnValue("IdAdvertManga", IdAdvertManga);
            updateBuilder.updateColumnValue("NameAdvertManga", NameAdvertManga);
            updateBuilder.updateColumnValue("ImgAdvertManga", ImgAdvertManga);
            updateBuilder.updateColumnValue("IdFavorite", IdFavorite);
            updateBuilder.where().eq("IdAdvertManga", IdAdvertManga);
            updateBuilder.update();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
