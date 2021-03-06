package com.example.tunguyen.manga.view.activity;

import com.example.tunguyen.manga.view.model.AdvertDto;
import com.example.tunguyen.manga.view.model.ChapterDto;
import com.example.tunguyen.manga.view.model.DeviceDto;
import com.example.tunguyen.manga.view.model.clsAllAdvertDto;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedFile;

/**
 * Created by TuNguyen on 01/12/2017.
 */

public interface ServiceConnect {
    @GET("/Advert/")
    void GetListAdvert(Callback<List<AdvertDto>> items);

    @GET("/Chapter/CountUpdate/")
    void CountUpdate(Callback<List<AdvertDto>> items);

    @GET("/Advert/GetAdvertById/")
    void GetAdvertById(@Query("id")int id,
                       Callback<List<clsAllAdvertDto>> items);
    @GET("/Chapter/GetChapByAdvertID/")
    void GetChapByAdvertID(@Query("id")int id,
                       Callback<List<ChapterDto>> items);
    @GET("/Advert/GetAdvertByTypeId/")
    void GetAdvertByTypeId(@Query("id")int id,
                           Callback<List<AdvertDto>> items);

    @GET("/Advert/GetListAdvertByType/")
    void GetListAdvertByType(@Query("type")String type,
                           Callback<List<AdvertDto>> items);

    @GET("/Chapter/GetDetailChapByID/")
    void GetDetailChapByID(@Query("idChap")int idChap,
                           Callback<List<ChapterDto>> items);

    @GET("/Advert/GetAdvertWithChap/")
    void GetAdvertWithChap(Callback<List<clsAllAdvertDto>> items);

    @GET("/Advert/CountView/")
    void CountView(@Query("id")int id,@Query("idCount")int idCount,Callback<List<AdvertDto>> items);

    @GET("/Device/AddDevice/")
    void AddDevice(@Query("serial")String serial,
                   @Query("model")String model,
                   @Query("product")String product,
                   @Query("imei")String imei,
                   @Query("osversion")String osversion,
                   @Query("osapiLevel")int osapiLevel,
                   @Query("os")String os,
                           Callback<List<DeviceDto>> device);
}
