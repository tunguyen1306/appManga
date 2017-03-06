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
                    if (AdvertDto.get(i).StatusAdvertManga != 0) {
                        Preference.AddAllAdvertMangaSqlite(context, AdvertDto.get(i).IdAdvertManga, AdvertDto.get(i).NameAdvertManga, AdvertDto.get(i).ImgAdvertManga, AdvertDto.get(i).NameAuthorAdvertManga, AdvertDto.get(i).DesAdvertManga, AdvertDto.get(i).TypeAdvertManga, AdvertDto.get(i).CountView, AdvertDto.get(i).TypeStatusAdvertManga, AdvertDto.get(i).CodeAdvertManga, AdvertDto.get(i).StatusAdvertManga);
                        LoadDetailAdvertById(AdvertDto.get(i).IdAdvertManga, context);
                    }
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("myLogs", "-------ERROR-------Slide");
                Log.d("myLogs", Log.getStackTraceString(error));
            }
        });
    }

    public static void LoadDetailAdvertById(int id, final Context context) {
        ResClien resClient = new ResClien();
        resClient.GetService().GetChapByAdvertID(id, new Callback<List<ChapterDto>>() {
            @Override
            public void success(List<ChapterDto> ChapterDto, Response response) {

                for (int i = 0; i < ChapterDto.size(); i++) {

                    Preference.AddChapterSqlite(context, ChapterDto.get(i).IdChapterManga, ChapterDto.get(i).NameChapterManga, ChapterDto.get(i).Link, ChapterDto.get(i).IdAdvertManga);
                }

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
