package com.example.regionapi;

public class RegionTour {
    private String contentID;
    private String title;
    private String image;
    private String address;

    public RegionTour() {
    }

    public RegionTour(String contentID, String title, String image, String address) {
        this.contentID = contentID;
        this.title = title;
        this.image = image;
        this.address = address;
    }

    public String getContentID() {
        return contentID;
    }

    public void setContentID(String contentID) {
        this.contentID = contentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAdrress(String address) {
        this.address = address;
    }
}
