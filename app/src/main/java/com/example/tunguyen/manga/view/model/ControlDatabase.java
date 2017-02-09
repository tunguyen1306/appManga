package com.example.tunguyen.manga.view.model;


import android.content.Context;
import android.util.Log;

import com.example.tunguyen.manga.view.activity.ResClien;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ControlDatabase {

    public static void AddAllAdvert(final Context context) {
        ResClien restClient = new ResClien();
        restClient.GetService().GetListAdvert(new Callback<List<AdvertDto>>() {
            @Override
            public void success(List<AdvertDto> AdvertDto, Response response) {
                for (int i = 0; i < AdvertDto.size(); i++) {

                    Preference.AddAllAdvertMangaSqlite(context,AdvertDto.get(i).IdAdvertManga,AdvertDto.get(i).NameAdvertManga,AdvertDto.get(i).ImgAdvertManga,AdvertDto.get(i).NameAuthorAdvertManga,AdvertDto.get(i).DesAdvertManga,AdvertDto.get(i).TypeAdvertManga,AdvertDto.get(i).CountView,AdvertDto.get(i).TypeStatusAdvertManga);

                }

            }
            @Override
            public void failure(RetrofitError error) {
                Log.d("myLogs", "-------ERROR-------Slide");
                Log.d("myLogs", Log.getStackTraceString(error));
            }
        });
    }
}
