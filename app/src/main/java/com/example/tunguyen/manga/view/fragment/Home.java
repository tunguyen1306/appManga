package com.example.tunguyen.manga.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.activity.ResClien;
import com.example.tunguyen.manga.view.adapter.ProjectFeaturedAdapter;
import com.example.tunguyen.manga.view.adapter.SlideAdapter;
import com.example.tunguyen.manga.view.model.AdvertDto;
import com.pixelcan.inkpageindicator.InkPageIndicator;

import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Home extends Fragment {


    ViewPager pager_banner;
    InkPageIndicator indicator_banel;
    View view;
TextView tv_count_advert;
    ////Slide///////
    List<AdvertDto> ItemAdvert;
    List<String> ListIdAdvert = new ArrayList<>();
    List<String> ListNameAdvert = new ArrayList<>();
    List<String> ListNameAuthorAdvert = new ArrayList<>();
    List<String> ListStatusAdvert = new ArrayList<>();
    List<String> ListStatusChapAdvert = new ArrayList<>();
    List<String> ListCountChapAdvert = new ArrayList<>();
    List<String> ListImgAdvert = new ArrayList<>();
    ////End Slide///


    //////////
    TwoWayView lv_advert_feauture;
    ////Slide///////
    List<AdvertDto> ItemAdvertFeauture;
    List<String> ListIdAdvertFeauture = new ArrayList<>();
    List<String> ListNameAdvertFeauture = new ArrayList<>();
    List<String> ListNameAuthorAdvertFeauture = new ArrayList<>();
    List<String> ListStatusAdvertFeauture = new ArrayList<>();
    List<String> ListStatusChapAdvertFeauture = new ArrayList<>();
    List<String> ListCountChapAdvertFeauture = new ArrayList<>();
    List<String> ListImgAdvertFeauture = new ArrayList<>();
    ////End Slide///
    //////////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        lv_advert_feauture = (TwoWayView) view.findViewById(R.id.lv_advert_feauture);
        tv_count_advert=(TextView) view.findViewById(R.id.tv_count_advert);
        indicator_banel=(InkPageIndicator)view.findViewById(R.id.indicator_slide);
        pager_banner=(ViewPager)  view.findViewById(R.id.pager_banner);
        callServiceSlide();
        callServiceAdvertFeauture();
        return view;

    }

    ///LoadSlide///////
    private void loadSilde(){

        ItemAdvert = getDataSlide();

        try {
            for (int i = 0; i < ItemAdvert.size(); i++) {

                pager_banner.setAdapter(new SlideAdapter(getActivity(), ItemAdvert));

                SlideAdapter slideAdapter =new SlideAdapter(getActivity(), ItemAdvert);
                pager_banner.setAdapter(slideAdapter);
                indicator_banel.setViewPager(pager_banner);
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
    public void callServiceSlide() {
        ResClien restClient = new ResClien();
        restClient.GetService().GetListAdvert(
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


    ///LoadAdvertFeauture///
    private void loadDataAdvertFeauture() {

        ItemAdvertFeauture = getAllItemsAdvertFeauture();

        try {

            ProjectFeaturedAdapter adapter = new ProjectFeaturedAdapter(getActivity(), ItemAdvertFeauture, "project");
            lv_advert_feauture.setAdapter(adapter);
        } catch (Exception ex) {

        }
    }
    private List<AdvertDto> getAllItemsAdvertFeauture() {
        List<AdvertDto> items = new ArrayList<>();
        for (int i = 0; i < ListIdAdvertFeauture.size(); i++) {
            items.add(
                    new AdvertDto(
                            ListIdAdvertFeauture.get(i),
                            ListNameAdvertFeauture.get(i),
                            ListImgAdvertFeauture.get(i) ,
                            ListNameAuthorAdvertFeauture.get(i)
                    )
            );
        }
        return items;
    }
    public void callServiceAdvertFeauture() {
        ResClien restClient = new ResClien();
        restClient.GetService().GetListAdvert(new Callback<List<AdvertDto>>() {
            @Override
            public void success(List<AdvertDto> AdvertDto, Response response) {
                for (int i = 0; i < AdvertDto.size(); i++) {

                    String tmpStr10 = Integer.toString(AdvertDto.get(i).IdAdvertManga);
                    ListIdAdvertFeauture.add(tmpStr10);
                    ListNameAdvertFeauture.add(AdvertDto.get(i).NameAdvertManga);
                    ListImgAdvertFeauture.add(AdvertDto.get(i).ImgAdvertManga);
                    ListNameAuthorAdvertFeauture.add(AdvertDto.get(i).NameAuthorAdvertManga);
                }
                loadDataAdvertFeauture();
            }
            @Override
            public void failure(RetrofitError error) {
                Log.d("myLogs", "-------ERROR-------Slide");
                Log.d("myLogs", Log.getStackTraceString(error));
            }
        });
    }
    ///End LoadAdvertFeauture///
}
