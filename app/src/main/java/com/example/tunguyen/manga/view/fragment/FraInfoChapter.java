package com.example.tunguyen.manga.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.activity.ResClien;
import com.example.tunguyen.manga.view.model.AdvertDto;
import com.example.tunguyen.manga.view.model.Preference;
import com.example.tunguyen.manga.view.model.clsAllAdvertDto;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ducthien on 17/01/2017.
 */

public class FraInfoChapter extends Fragment {
    TextView txtAdvertDes1,txtAdvertType1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_info_chapter, container, false);
        txtAdvertDes1 =(TextView)view.findViewById(R.id.txtAdvertDes1);
        txtAdvertType1 =(TextView)view.findViewById(R.id.txtAdvertType1);
        Preference.restorePreference(getContext());
        LoadDetailAdvertById(AdvertDto.IdAdvertRefer);
        return view;
    }
    ///Load Detail Advert by ID///
    public void LoadDetailAdvertById(int id)
    {
        ResClien resClient=new ResClien();
        resClient.GetService().GetAdvertById(id, new Callback<List<clsAllAdvertDto>>() {
            @Override
            public void success(List<clsAllAdvertDto> advertDtos, Response response) {
                txtAdvertDes1.setText(advertDtos.get(0).tblAdvertManga.DesAdvertManga);
                txtAdvertType1.setText(advertDtos.get(0).tblAdvertManga.TypeAdvertManga);

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    ///End Load Detail Advert by ID///


}
