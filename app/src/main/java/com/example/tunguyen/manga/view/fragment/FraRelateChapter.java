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
//    GridView grid;
//    private ArrayList<String> NameAdvert=new ArrayList<String>();
//    private ArrayList<String> ImgAdvert=new ArrayList<String>();

    CardView cardView;
    ListView list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_relate_chapter, container, false);
       // grid=(GridView)view.findViewById(R.id.grid);
        cardView=(CardView)view.findViewById(R.id.cardViewAdvertRelate);
        list=(ListView)view.findViewById(R.id.lvAdvertRelate);
        LoadDetailAdvertById();
        return view;
    }

    public void LoadDetailAdvertById()
    {
        ResClien resClient=new ResClien();
        resClient.GetService().GetListAdvert(
                new Callback<List<AdvertDto>>() {
                    @Override
                    public void success(List<AdvertDto> AdvertDto, Response response) {
                        list.setAdapter(new AdvertRelateAdapter(getActivity(),AdvertDto));
                        for (int i = 0; i < AdvertDto.size(); i++) {

//                            String tmpStr10 = Integer.toString(AdvertDto.get(i).IdAdvertManga);
//                            NameAdvert.add(AdvertDto.get(i).NameAdvertManga);
//                            ImgAdvert.add(AdvertDto.get(i).ImgAdvertManga);
//                            CustomAdapter adapter = new CustomAdapter(getActivity(), NameAdvert, ImgAdvert);
//                            grid.setAdapter(adapter);
                        }
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }


}
