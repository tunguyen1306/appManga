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
import com.example.tunguyen.manga.view.model.AdvertDto;

import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FraAllAdvert extends Fragment {
        GridView grid;

    ////Advert Read///////
    List<AdvertDto> ItemAllAdvert;
    List<String> ListIdAllAdvert = new ArrayList<>();
    List<String> ListNameAllAdvert = new ArrayList<>();
    List<String> ListNameAuthorAllAdvert = new ArrayList<>();
    List<String> ListStatusAllAdvert = new ArrayList<>();
    List<String> ListStatusChapAllAdvert = new ArrayList<>();
    List<String> ListCountChapAllAdvert = new ArrayList<>();
    List<String> ListImgAllAdvert = new ArrayList<>();
    List<String> ListCountAllAdvert = new ArrayList<>();
    List<String> ListTypeAllAdvert = new ArrayList<>();

    ////End Advert Read///////
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_all_advert, container, false);
       grid=(GridView)view.findViewById(R.id.gridView1);
        callServiceAllAdvert();
        return view;
    }


    ///LoadAllAdvert///
    private void loadDataAllAdvert() {

        ItemAllAdvert = getAllItemsAllAdvert();

        try {

            CustomAdapter adapter = new CustomAdapter(getActivity(), ItemAllAdvert);
            grid.setAdapter(adapter);
        } catch (Exception ex) {

        }
    }
    private List<AdvertDto> getAllItemsAllAdvert() {
        List<AdvertDto> items = new ArrayList<>();
        for (int i = 0; i < ListIdAllAdvert.size(); i++) {
            items.add(
                    new AdvertDto(
                            ListIdAllAdvert.get(i),
                            ListNameAllAdvert.get(i),
                            ListImgAllAdvert.get(i) ,
                            ListNameAuthorAllAdvert.get(i),
                            ListTypeAllAdvert.get(i),
                            ListStatusAllAdvert.get(i)
                    )
            );
        }
        return items;
    }
    public void callServiceAllAdvert() {
        ResClien restClient = new ResClien();
        restClient.GetService().GetListAdvert(new Callback<List<AdvertDto>>() {
            @Override
            public void success(List<AdvertDto> AdvertDto, Response response) {
                for (int i = 0; i < AdvertDto.size(); i++) {

                    String tmpStr10 = Integer.toString(AdvertDto.get(i).IdAdvertManga);
                    String tmpStatus = Integer.toString(AdvertDto.get(i).StatusChapAdvertManga);
                    ListIdAllAdvert.add(tmpStr10);
                    ListNameAllAdvert.add(AdvertDto.get(i).NameAdvertManga);
                    ListImgAllAdvert.add(AdvertDto.get(i).ImgAdvertManga);
                    ListNameAuthorAllAdvert.add(AdvertDto.get(i).NameAuthorAdvertManga);
                    ListTypeAllAdvert.add(AdvertDto.get(i).TypeAdvertManga);
                    ListStatusAllAdvert.add(tmpStatus);


                }
                loadDataAllAdvert();
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
