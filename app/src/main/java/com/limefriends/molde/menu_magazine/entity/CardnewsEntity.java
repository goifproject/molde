package com.limefriends.molde.menu_magazine.entity;

public class CardnewsEntity {
    private int thumbnail;
    private String title;

    public CardnewsEntity(int thumbnail, String title) {
        this.thumbnail = thumbnail;
        this.title = title;
    }


    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
