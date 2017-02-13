package com.chumbok.news.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "FeedItem")
public class FeedItem extends Model {

    @Column(name = "articleId")
    public long articleId;

    @Column(name = "sourceName")
    public String sourceName;

    @Column(name = "title")
    public String title;

    @Column(name = "description")
    public String desc;

    @Column(name = "descBodyUrl")
    public String descBodyUrl;

    @Column(name = "imageUrl")
    public String imageUrl;

    @Column(name = "profilePicUrl")
    public String profilePicUrl;

    @Column(name = "pubDate")
    public String pubDate;


    public FeedItem() {
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public String getDescBodyUrl() {
        return descBodyUrl;
    }

    public void setDescBodyUrl(String descBodyUrl) {
        this.descBodyUrl = descBodyUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public void copy(FeedItem feedItem) {
        this.articleId = feedItem.getArticleId();
        this.sourceName = feedItem.getSourceName();
        this.title = feedItem.getTitle();
        this.desc = feedItem.getDesc();
        this.descBodyUrl = feedItem.getDescBodyUrl();
        this.imageUrl = feedItem.getImageUrl();
        this.profilePicUrl = feedItem.getProfilePicUrl();
        this.pubDate = feedItem.getPubDate();
    }

    @Override
    public String toString() {
        return "FeedItem{" +
                "articleId=" + articleId +
                ", sourceName='" + sourceName + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", descBodyUrl='" + descBodyUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", profilePicUrl='" + profilePicUrl + '\'' +
                ", pubDate='" + pubDate + '\'' +
                '}';
    }
}
