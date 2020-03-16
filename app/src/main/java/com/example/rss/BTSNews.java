package com.example.rss;

import androidx.annotation.NonNull;

public class BTSNews {
    private String title;
    private String link;
    private String description;
    private String pubDate;
    private String newsSource;
    private String newsImage;

    public BTSNews(){};

    public BTSNews(String title, String link, String description,String pubDate, String newsSource, String image) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
        this.newsSource = newsSource;
        this.newsImage = image;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    @NonNull
    @Override
    public String toString() {
        return getTitle();
    }
}
