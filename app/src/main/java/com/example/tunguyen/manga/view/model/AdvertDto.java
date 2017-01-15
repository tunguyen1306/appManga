package com.example.tunguyen.manga.view.model;

public class AdvertDto {
        public AdvertDto (String IdAdvertManga,String NameAdvertManga,String ImgAdvertManga){
                this.IdAdvertManga=Integer.parseInt(IdAdvertManga);
                this.NameAdvertManga=NameAdvertManga;
                this.ImgAdvertManga=ImgAdvertManga;
        }
        public  int IdAdvertManga;
        public  String NameAdvertManga;

        public String getNameAuthorAdvertManga() {
                return NameAuthorAdvertManga;
        }

        public void setNameAuthorAdvertManga(String nameAuthorAdvertManga) {
                NameAuthorAdvertManga = nameAuthorAdvertManga;
        }

        public int getIdAdvertManga() {
                return IdAdvertManga;
        }

        public void setIdAdvertManga(int idAdvertManga) {
                IdAdvertManga = idAdvertManga;
        }

        public String getNameAdvertManga() {
                return NameAdvertManga;
        }

        public void setNameAdvertManga(String nameAdvertManga) {
                NameAdvertManga = nameAdvertManga;
        }

        public int getStatusAdvertManga() {
                return StatusAdvertManga;
        }

        public void setStatusAdvertManga(int statusAdvertManga) {
                StatusAdvertManga = statusAdvertManga;
        }

        public int getStatusChapAdvertManga() {
                return StatusChapAdvertManga;
        }

        public void setStatusChapAdvertManga(int statusChapAdvertManga) {
                StatusChapAdvertManga = statusChapAdvertManga;
        }

        public int getCountChapAdvertManga() {
                return CountChapAdvertManga;
        }

        public void setCountChapAdvertManga(int countChapAdvertManga) {
                CountChapAdvertManga = countChapAdvertManga;
        }

        public  String NameAuthorAdvertManga;
        public  int StatusAdvertManga;
        public  int StatusChapAdvertManga;
        public  int CountChapAdvertManga;

        public String getImgAdvertManga() {
                return ImgAdvertManga;
        }

        public void setImgAdvertManga(String imgAdvertManga) {
                ImgAdvertManga = imgAdvertManga;
        }

        public String ImgAdvertManga;
}
