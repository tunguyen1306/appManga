package com.example.tunguyen.manga.view.model;

public class AdvertDto {
        public AdvertDto (String IdAdvert,String NameAdvert){
                this.IdAdvert=IdAdvert;
                this.NameAdvert=NameAdvert;
        }
        public  String IdAdvert;
        public  String NameAdvert;

        public String getNameAuthorAdvert() {
                return NameAuthorAdvert;
        }

        public void setNameAuthorAdvert(String nameAuthorAdvert) {
                NameAuthorAdvert = nameAuthorAdvert;
        }

        public String getIdAdvert() {
                return IdAdvert;
        }

        public void setIdAdvert(String idAdvert) {
                IdAdvert = idAdvert;
        }

        public String getNameAdvert() {
                return NameAdvert;
        }

        public void setNameAdvert(String nameAdvert) {
                NameAdvert = nameAdvert;
        }

        public int getStatusAdvert() {
                return StatusAdvert;
        }

        public void setStatusAdvert(int statusAdvert) {
                StatusAdvert = statusAdvert;
        }

        public int getStatusChapAdvert() {
                return StatusChapAdvert;
        }

        public void setStatusChapAdvert(int statusChapAdvert) {
                StatusChapAdvert = statusChapAdvert;
        }

        public int getCountChapAdvert() {
                return CountChapAdvert;
        }

        public void setCountChapAdvert(int countChapAdvert) {
                CountChapAdvert = countChapAdvert;
        }

        public  String NameAuthorAdvert;
        public  int StatusAdvert;
        public  int StatusChapAdvert;
        public  int CountChapAdvert;

        public String getImgAdvert() {
                return ImgAdvert;
        }

        public void setImgAdvert(String imgAdvert) {
                ImgAdvert = imgAdvert;
        }

        public String ImgAdvert;
}
