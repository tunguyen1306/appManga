package com.example.tunguyen.manga.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.activity.ResClien;
import com.example.tunguyen.manga.view.adapter.AdvertFeaturedAdapter;
import com.example.tunguyen.manga.view.adapter.AdvertPopularAdapter;
import com.example.tunguyen.manga.view.adapter.AdvertReadAdapter;
import com.example.tunguyen.manga.view.model.AdvertDto;

import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FraHome extends Fragment {


    //ViewPager pager_banner;
    //InkPageIndicator indicator_banel;
    View view;
TextView tv_count_advert,tv_count_advert2;
    ////Slide///////
    TwoWayView lv_advert_feature;
    List<AdvertDto> ItemAdvert;
    List<String> ListIdAdvert = new ArrayList<>();
    List<String> ListNameAdvert = new ArrayList<>();
    List<String> ListNameAuthorAdvert = new ArrayList<>();
    List<String> ListStatusAdvert = new ArrayList<>();
    List<String> ListStatusChapAdvert = new ArrayList<>();
    List<String> ListCountChapAdvert = new ArrayList<>();
    List<String> ListImgAdvert = new ArrayList<>();
    ////End Slide///

    ////Advert Read///////
    TwoWayView lv_advert_read;
    List<AdvertDto> ItemAdvertRead;
    List<String> ListIdAdvertRead = new ArrayList<>();
    List<String> ListNameAdvertRead = new ArrayList<>();
    List<String> ListNameAuthorAdvertRead = new ArrayList<>();
    List<String> ListStatusAdvertRead = new ArrayList<>();
    List<String> ListStatusChapAdvertRead = new ArrayList<>();
    List<String> ListCountChapAdvertRead = new ArrayList<>();
    List<String> ListImgAdvertRead = new ArrayList<>();
    ////End Advert Read///////


    ////Advert Read///////
    TwoWayView lv_advert_popular;
    List<AdvertDto> ItemAdvertPopular;
    List<String> ListIdAdvertPopular = new ArrayList<>();
    List<String> ListNameAdvertPopular = new ArrayList<>();
    List<String> ListNameAuthorAdvertPopular = new ArrayList<>();
    List<String> ListStatusAdvertPopular = new ArrayList<>();
    List<String> ListStatusChapAdvertPopular = new ArrayList<>();
    List<String> ListCountChapAdvertPopular = new ArrayList<>();
    List<String> ListImgAdvertPopular = new ArrayList<>();
    ////End Advert Read///////
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        lv_advert_read = (TwoWayView) view.findViewById(R.id.lv_advert_read);
        lv_advert_feature = (TwoWayView) view.findViewById(R.id.lv_advert_feature);
        lv_advert_popular=(TwoWayView) view.findViewById(R.id.lv_advert_popular);
        tv_count_advert=(TextView) view.findViewById(R.id.tv_count_advert);
        tv_count_advert2=(TextView) view.findViewById(R.id.tv_count_advert2);
        callServiceSlide(1);
        callServiceAdvertRead(2);
        callServiceAdvertPopular(3);

        return view;

    }

    ///LoadSlide///////
    private void loadSilde(){
        ItemAdvert = getDataSlide();
        try {
            for (int i = 0; i < ItemAdvert.size(); i++) {

               // pager_banner.setAdapter(new SlideAdapter(getActivity(), ItemAdvert));//pager_banner.setAdapter(slideAdapter);
                //indicator_banel.setViewPager(pager_banner);
                try {
                    AdvertFeaturedAdapter adapter = new AdvertFeaturedAdapter(getActivity(), ItemAdvert, "advert feature");
                    lv_advert_feature.setAdapter(adapter);
                } catch (Exception ex) {

                }
            }
        }
        catch (Exception ex) {

        }
    }
    private List<AdvertDto>getDataSlide(){
        List<AdvertDto> items = new ArrayList<>();
        for (int i=0;i<ListIdAdvert.size();i++) {
            items.add(new AdvertDto(ListIdAdvert.get(i),ListNameAdvert.get(i),ListImgAdvert.get(i),ListNameAuthorAdvert.get(i)));
        }
        return items;
    }
    public void callServiceSlide(int id) {
        ResClien restClient = new ResClien();
        restClient.GetService().GetAdvertByTypeId(id,
                new Callback<List<AdvertDto>>() {
                    @Override
                    public void success(List<AdvertDto> AdvertDto, Response response) {

                        for (int i = 0; i < AdvertDto.size(); i++) {

                            String tmpStr10 = Integer.toString(AdvertDto.get(i).IdAdvertManga);
                            ListIdAdvert.add(tmpStr10);
                            ListNameAdvert.add(AdvertDto.get(i).NameAdvertManga);
                            ListImgAdvert.add(AdvertDto.get(i).ImgAdvertManga);
                            ListNameAuthorAdvert.add(AdvertDto.get(i).NameAuthorAdvertManga);
                        }
                        loadSilde();
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }
    ///End LoadSlide///


    ///LoadAdvertRead///
    private void loadDataAdvertRead() {

        ItemAdvertRead = getAllItemsAdvertRead();

        try {

            AdvertReadAdapter adapter = new AdvertReadAdapter(getActivity(), ItemAdvertRead, "project");
            lv_advert_read.setAdapter(adapter);

        } catch (Exception ex) {

        }
    }
    private List<AdvertDto> getAllItemsAdvertRead() {
        List<AdvertDto> items = new ArrayList<>();
        for (int i = 0; i < ListIdAdvertRead.size(); i++) {
            items.add(
                    new AdvertDto(
                            ListIdAdvertRead.get(i),
                            ListNameAdvertRead.get(i),
                            ListImgAdvertRead.get(i) ,
                            ListNameAuthorAdvertRead.get(i)
                    )
            );
        }
        return items;
    }
    public void callServiceAdvertRead(int id) {
        ResClien restClient = new ResClien();
        restClient.GetService().GetAdvertByTypeId(id,new Callback<List<AdvertDto>>() {
            @Override
            public void success(List<AdvertDto> AdvertDto, Response response) {
                for (int i = 0; i < AdvertDto.size(); i++) {

                    String tmpStr10 = Integer.toString(AdvertDto.get(i).IdAdvertManga);
                    ListIdAdvertRead.add(tmpStr10);
                    ListNameAdvertRead.add(AdvertDto.get(i).NameAdvertManga);
                    ListImgAdvertRead.add(AdvertDto.get(i).ImgAdvertManga);
                    ListNameAuthorAdvertRead.add(AdvertDto.get(i).NameAuthorAdvertManga);
                }
                int count=AdvertDto.size();
                tv_count_advert.setText(count+" truyện");
                loadDataAdvertRead();
            }
            @Override
            public void failure(RetrofitError error) {
                Log.d("myLogs", "-------ERROR-------Slide");
                Log.d("myLogs", Log.getStackTraceString(error));
            }
        });
    }
    ///End LoadAdvertRead///

    ///LoadAdvertPopular///
    private void loadDataAdvertPopular() {

        ItemAdvertPopular = getAllItemsAdvertPopular();

        try {

            AdvertPopularAdapter adapter = new AdvertPopularAdapter(getActivity(), ItemAdvertPopular, "Advert Popular");
            lv_advert_popular.setAdapter(adapter);
        } catch (Exception ex) {

        }
    }
    private List<AdvertDto> getAllItemsAdvertPopular() {
        List<AdvertDto> items = new ArrayList<>();
        for (int i = 0; i < ListIdAdvertPopular.size(); i++) {
            items.add(
                    new AdvertDto(
                            ListIdAdvertPopular.get(i),
                            ListNameAdvertPopular.get(i),
                            ListImgAdvertPopular.get(i) ,
                            ListNameAuthorAdvertPopular.get(i)
                    )
            );
        }
        return items;
    }
    public void callServiceAdvertPopular(int id) {
        ResClien restClient = new ResClien();
        restClient.GetService().GetAdvertByTypeId(id,new Callback<List<AdvertDto>>() {
            @Override
            public void success(List<AdvertDto> AdvertDto, Response response) {
                for (int i = 0; i < AdvertDto.size(); i++) {

                    String tmpStr10 = Integer.toString(AdvertDto.get(i).IdAdvertManga);
                    ListIdAdvertPopular.add(tmpStr10);
                    ListNameAdvertPopular.add(AdvertDto.get(i).NameAdvertManga);
                    ListImgAdvertPopular.add(AdvertDto.get(i).ImgAdvertManga);
                    ListNameAuthorAdvertPopular.add(AdvertDto.get(i).NameAuthorAdvertManga);
                }
                int count=AdvertDto.size();
                tv_count_advert2.setText(count+" truyện");
                loadDataAdvertPopular();
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
