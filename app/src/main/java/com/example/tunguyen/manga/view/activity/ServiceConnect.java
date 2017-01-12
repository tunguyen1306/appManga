package com.example.tunguyen.manga.view.activity;

import com.example.tunguyen.manga.view.model.AdvertDto;

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
    @GET("/Slide/")
    void GetSlide(Callback<List<AdvertDto>> items);

}