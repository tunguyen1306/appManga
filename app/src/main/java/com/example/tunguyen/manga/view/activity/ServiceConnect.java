package com.example.tunguyen.manga.view.activity;

import com.example.tunguyen.manga.view.model.AdvertDto;
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
    @GET("/Advert/GetAdvertById/")
    void GetAdvertById(@Query("id")int id,
                       Callback<List<clsAllAdvertDto>> items);
}
