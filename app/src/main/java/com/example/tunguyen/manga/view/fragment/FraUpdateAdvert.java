package com.example.tunguyen.manga.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.activity.ResClien;
import com.example.tunguyen.manga.view.adapter.AdvertRelateAdapter;
import com.example.tunguyen.manga.view.adapter.CustomAdapter;
import com.example.tunguyen.manga.view.adapter.UpdateAdvertAdapter;
import com.example.tunguyen.manga.view.model.AdvertDto;

import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FraUpdateAdvert extends Fragment {
    GridView grid;

    ////Advert Read///////
    List<AdvertDto> ItemUpdateAdvert;
    List<String> ListIdUpdateAdvert = new ArrayList<>();
    List<String> ListNameUpdateAdvert = new ArrayList<>();
    List<String> ListNameAuthorUpdateAdvert = new ArrayList<>();
    List<String> ListStatusUpdateAdvert = new ArrayList<>();
    List<String> ListStatusChapUpdateAdvert = new ArrayList<>();
    List<String> ListCountChapUpdateAdvert = new ArrayList<>();
    List<String> ListImgUpdateAdvert = new ArrayList<>();
    List<String> ListCountUpdateAdvert = new ArrayList<>();
    ////End Advert Read///////
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_update_advert, container, false);
        grid=(GridView)view.findViewById(R.id.gridViewUpdate);
        callServiceUpdateAdvert();
        return view;
    }


    ///LoadUpdateAdvert///
    private void loadDataUpdateAdvert() {

        ItemUpdateAdvert = getAllItemsUpdateAdvert();

        try {

            UpdateAdvertAdapter adapter = new UpdateAdvertAdapter(getActivity(), ItemUpdateAdvert);
            grid.setAdapter(adapter);
        } catch (Exception ex) {

        }
    }
    private List<AdvertDto> getAllItemsUpdateAdvert() {
        List<AdvertDto> items = new ArrayList<>();
        for (int i = 0; i < ListIdUpdateAdvert.size(); i++) {
            items.add(
                    new AdvertDto(
                            ListIdUpdateAdvert.get(i),
                            ListNameUpdateAdvert.get(i),
                            ListImgUpdateAdvert.get(i) ,
                            ListNameAuthorUpdateAdvert.get(i),
                            ListCountUpdateAdvert.get(i)
                    )
            );
        }
        return items;
    }
    public void callServiceUpdateAdvert() {
        ResClien restClient = new ResClien();
        restClient.GetService().CountUpdate(new Callback<List<AdvertDto>>() {
            @Override
            public void success(List<AdvertDto> AdvertDto, Response response) {
                for (int i = 0; i < AdvertDto.size(); i++) {

                    String tmpStr10 = Integer.toString(AdvertDto.get(i).IdAdvertManga);
                    String strCount = Integer.toString(AdvertDto.get(i).num_update);
                    ListIdUpdateAdvert.add(tmpStr10);
                    ListNameUpdateAdvert.add(AdvertDto.get(i).NameAdvertManga);
                    ListImgUpdateAdvert.add(AdvertDto.get(i).ImgAdvertManga);
                    ListNameAuthorUpdateAdvert.add(AdvertDto.get(i).NameAuthorAdvertManga);
                    ListCountUpdateAdvert.add(strCount);
                }
                loadDataUpdateAdvert();
            }
            @Override
            public void failure(RetrofitError error) {
                Log.d("myLogs", "-------ERROR-------Slide");
                Log.d("myLogs", Log.getStackTraceString(error));
            }
        });
    }
    ///End LoadAdvertRead///
}
