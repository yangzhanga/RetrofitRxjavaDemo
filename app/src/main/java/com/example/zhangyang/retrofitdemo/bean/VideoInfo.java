package com.example.zhangyang.retrofitdemo.bean;

/**
 * Created by zhangyang on 2017/11/9.
 */

public class VideoInfo {
    private String title;
    private String url;
    private String name;
    private String playcount;

    public VideoInfo(String title, String url, String name, String playcount) {
        this.title = title;
        this.url = url;
        this.name = name;
        this.playcount = playcount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }
}
