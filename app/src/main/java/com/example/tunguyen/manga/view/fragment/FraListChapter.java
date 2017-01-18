package com.example.tunguyen.manga.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.activity.ResClien;
import com.example.tunguyen.manga.view.adapter.ListChapterAdapter;
import com.example.tunguyen.manga.view.model.AdvertDto;
import com.example.tunguyen.manga.view.model.ChapterDto;
import com.example.tunguyen.manga.view.model.Preference;
import com.example.tunguyen.manga.view.model.clsAllAdvertDto;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ducthien on 17/01/2017.
 */

public class FraListChapter extends Fragment {
    CardView cardView;
    ListView list;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_list_chapter, container, false);
        cardView=(CardView)view.findViewById(R.id.cardViewChapter);
        list=(ListView)view.findViewById(R.id.lvChapter);
        Preference.restorePreference(getContext());
        LoadDetailAdvertById(AdvertDto.IdAdvertRefer);
        return view;
    }
    ///Load Detail Advert by ID///
    public void LoadDetailAdvertById(int id)
    {
        ResClien resClient=new ResClien();
        resClient.GetService().GetChapByAdvertID(id, new Callback<List<ChapterDto>>() {
            @Override
            public void success(List<ChapterDto> advertDtos, Response response) {
                list.setAdapter(new ListChapterAdapter(getActivity(),advertDtos));

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    ///End Load Detail Advert by ID///
}
