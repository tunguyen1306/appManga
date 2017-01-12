package com.example.tunguyen.manga.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.adapter.SlideAdapter;
import com.example.tunguyen.manga.view.model.AdvertDto;
import com.pixelcan.inkpageindicator.InkPageIndicator;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Home extends Fragment {


    ViewPager pager_banner;
    InkPageIndicator indicator_banel;
    View view;

    ////Slide///////
    List<AdvertDto> ItemAdvert;
    List<String> ListIdAdvert = new ArrayList<>();
    List<String> ListNameAdvert = new ArrayList<>();
    List<String> ListNameAuthorAdvert = new ArrayList<>();
    List<String> ListStatusAdvert = new ArrayList<>();
    List<String> ListStatusChapAdvert = new ArrayList<>();
    List<String> ListCountChapAdvert = new ArrayList<>();
    ////End Slide///


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

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
            items.add(new AdvertDto(ListIdAdvert.get(i),ListNameAdvert.get(i)));
        }
        return items;
    }
    public void callServiceSlide() {
        ResClient restClient = new ResClient();
        restClient.GetService().GetSlide(
                new Callback<List<SlideDto>>() {
                    @Override
                    public void success(List<SlideDto> slideDtos, Response response) {
                        for (int i = 0; i < slideDtos.size(); i++) {

                            list_id_slide.add(slideDtos.get(i).id_slide);
                            list_img_slide.add(slideDtos.get(i).img_slide);
                            list_des_slide.add(slideDtos.get(i).des_slide);
                            list_title_slide.add(slideDtos.get(i).title_slide);
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
}
