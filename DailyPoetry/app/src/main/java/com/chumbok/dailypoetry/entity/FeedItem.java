package com.chumbok.dailypoetry.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "FeedItem")
public class FeedItem extends Model {

    @Column(name = "poemId")
    public long poemId;

    @Column(name = "authorId")
    public long authorId;

    @Column(name = "authorName")
    public String authorName;

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

    @Column(name = "ts")
    public String timeStamp;

    @Column(name = "lastUpdatedDate")
    public String lastUpdatedDate;


    public FeedItem() {
    }


    public long getPoemId() {
        return poemId;
    }

    public void setPoemId(long poemId) {
        this.poemId = poemId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDescBodyUrl() {
        return descBodyUrl;
    }

    public void setDescBodyUrl(String descBodyUrl) {
        this.descBodyUrl = descBodyUrl;
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

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public void copy(FeedItem item) {
        this.poemId = item.getPoemId();
        this.authorId = item.getAuthorId();
        this.authorName = item.getAuthorName();
        this.title = item.getTitle();
        this.desc = item.getDesc();
        this.descBodyUrl = item.getDescBodyUrl();
        this.imageUrl = item.getImageUrl();
        this.profilePicUrl = item.getProfilePicUrl();
        this.timeStamp = item.getTimeStamp();
        this.lastUpdatedDate = item.getLastUpdatedDate();
    }

    @Override
    public String toString() {
        return "FeedItem{" +
                "poemId=" + poemId +
                ", authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", descBodyUrl='" + descBodyUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", profilePicUrl='" + profilePicUrl + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", lastUpdatedDate='" + lastUpdatedDate + '\'' +
                '}';
    }


}
