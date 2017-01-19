package com.example.tunguyen.manga.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.activity.MainActivity;
import com.example.tunguyen.manga.view.activity.ResClien;
import com.example.tunguyen.manga.view.adapter.AdvertRelateAdapter;
import com.example.tunguyen.manga.view.adapter.CustomAdapter;
import com.example.tunguyen.manga.view.adapter.ListChapterAdapter;
import com.example.tunguyen.manga.view.model.AdvertDto;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FraRelateChapter extends Fragment {
    CardView cardView;
    ListView list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_relate_chapter, container, false);
        list=(ListView)view.findViewById(R.id.lvAdvertRelate);
        LoadAdvertRalate();
        return view;
    }

    public void LoadAdvertRalate()
    {
        ResClien resClient=new ResClien();
        resClient.GetService().GetListAdvert(
                new Callback<List<AdvertDto>>() {
                    @Override
                    public void success(List<AdvertDto> AdvertDto, Response response) {
                        list.setAdapter(new AdvertRelateAdapter(getActivity(),AdvertDto));
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }


}
