package com.example.tunguyen.manga.view.model;


import java.util.List;

public class clsAllAdvertDto {
    public List<AdvertDto> ListAdvertManga;

    public List<ChapterDto> getListChapterManga() {
        return ListChapterManga;
    }

    public void setListChapterManga(List<ChapterDto> listChapterManga) {
        ListChapterManga = listChapterManga;
    }

    public List<AdvertDto> getListAdvertManga() {
        return ListAdvertManga;
    }

    public void setListAdvertManga(List<AdvertDto> listAdvertManga) {
        ListAdvertManga = listAdvertManga;
    }

    public AdvertDto getTblAdvertManga() {
        return tblAdvertManga;
    }

    public void setTblAdvertManga(AdvertDto tblAdvertManga) {
        this.tblAdvertManga = tblAdvertManga;
    }

    public ChapterDto getTblChapterManga() {
        return tblChapterManga;
    }

    public void setTblChapterManga(ChapterDto tblChapterManga) {
        this.tblChapterManga = tblChapterManga;
    }

    public List<ChapterDto> ListChapterManga;

    public AdvertDto tblAdvertManga;
    public ChapterDto tblChapterManga;
}
