package com.example.tunguyen.manga.view.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.activity.ResClien;
import com.example.tunguyen.manga.view.database.AdvertMangas;
import com.example.tunguyen.manga.view.database.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.squareup.picasso.Picasso;

import java.sql.SQLException;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Preference {
    private static String PREF_NAME = "pref";

    private static SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);}

    public  static int  idActionbar;
    public static void savePreference(Context context) {


        SharedPreferences.Editor edit = getPref(context).edit();
        edit.putInt("IdAdvertRefer", AdvertDto.IdAdvertRefer);
        edit.putString("NameAdvertRefer", AdvertDto.NameAdvertRefer);
        edit.putInt("CountViewRefer", AdvertDto.CountViewRefer);
        edit.putString("TypeAdvertRefer", AdvertDto.TypeAdvertRefer);
        ///Chapter///
        edit.putInt("IdChapterRefer", ChapterDto.IdChapterRefer);
        edit.putString("NameChapterRefer",ChapterDto.NameChapterRefer);

        ///Device///
        edit.putString("SerialDeviceRefer", DeviceDto.SerialDeviceRefer);

        ///Action bar///
        edit.putInt("idActionbar",idActionbar);

        edit.clear();
        edit.commit();
    }
    public static void restorePreference(Context context) {
        AdvertDto.IdAdvertRefer=getPref(context).getInt("IdAdvertRefer",0);
        AdvertDto.NameAdvertRefer=getPref(context).getString("NameAdvertRefer", "");
        AdvertDto.CountViewRefer=getPref(context).getInt("CountViewRefer",0);
        AdvertDto.TypeAdvertRefer=getPref(context).getString("TypeAdvertRefer", "");
        ///Chapter///
        ChapterDto.IdChapterRefer=getPref(context).getInt("IdChapterRefer",0);
        ChapterDto.NameChapterRefer=getPref(context).getString("NameChapterRefer", "");

        ///Device///
        DeviceDto.SerialDeviceRefer=getPref(context).getString("SerialDeviceRefer", "");

        ///Action bar///
        idActionbar=getPref(context).getInt("idActionbar",0);
    }
    public static void CountView(int id,int idCount)
    {
        ResClien resClient=new ResClien();
        resClient.GetService().CountView(id,idCount, new Callback<List<AdvertDto>>() {
            @Override
            public void success(List<AdvertDto> advertDtos, Response response) {
                AdvertDto.CountViewRefer=advertDtos.get(0).CountView;
            }
            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    public static void AddDevice(String serial, String model, String product, String imei, String osversion, int osapiLevel, String os)
    {
        ResClien resClient=new ResClien();
        resClient.GetService().AddDevice(serial,  model,  product,  imei,  osversion,  osapiLevel,  os, new Callback<List<DeviceDto>>() {
            @Override
            public void success(List<DeviceDto> advertDtos, Response response) {

            }
            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
