package com.example.tunguyen.manga.view.model;

public class ChapterDto {

    public static int IdChapterRefer;
    public static String NameChapterRefer;
    public ChapterDto(String Link)
    {
        this.Link=Link;
    }

    public  int IdChapterManga;

    public String getNameChapterManga() {
        return NameChapterManga;
    }

    public void setNameChapterManga(String nameChapterManga) {
        NameChapterManga = nameChapterManga;
    }

    public int getIdChapterManga() {
        return IdChapterManga;
    }

    public void setIdChapterManga(int idChapterManga) {
        IdChapterManga = idChapterManga;
    }

    public int getStatusChapterManga() {
        return StatusChapterManga;
    }

    public void setStatusChapterManga(int statusChapterManga) {
        StatusChapterManga = statusChapterManga;
    }

    public int getIdAdvertManga() {
        return IdAdvertManga;
    }

    public void setIdAdvertManga(int idAdvertManga) {
        IdAdvertManga = idAdvertManga;
    }

    public  String NameChapterManga;
    public  int StatusChapterManga;
    public  int IdAdvertManga;

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }
    public  String Link;


}
