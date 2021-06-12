package com.goodwiil.goodwillvoice.model;

public class NewsItem {

    private String thumbnail;
    private String newsTitle;
    private String newsDescription;
    private String url;

    public NewsItem(String thumbnail, String newsTitle, String newsDescription, String url) {
        this.thumbnail = thumbnail;
        this.newsTitle = newsTitle;
        this.newsDescription = newsDescription;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }
}
