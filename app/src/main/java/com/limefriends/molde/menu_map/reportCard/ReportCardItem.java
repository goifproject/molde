package com.limefriends.molde.menu_map.reportcard;


public class ReportCardItem {

    private String title;
    private String text;

    public ReportCardItem(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }
    public String getText() {
        return text;
    }
}
