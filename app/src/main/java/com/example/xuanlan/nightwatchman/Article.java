package com.example.xuanlan.nightwatchman;

public class Article {

    private String title;
    private String username;
    private String pubDate;
    private String user;
    private String hot;
    private String userPic;
    private String id;
    private String content;
    private String lables;

    public Article(String title, String content, String lables, String pubDate, String hot) {
        this.title = title;
        this.content = content;
        this.lables = lables;
        this.pubDate = pubDate;
        this.hot = hot;
    }

    public Article(String title, String username, String pubDate, String user, String hot, String userPic, String id) {
        this.title = title;
        this.username = username;
        this.pubDate = pubDate;
        this.user = user;
        this.hot = hot;
        this.userPic = userPic;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getUserPic() {
        return userPic;
    }

    public String getUser() {
        return user;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getTitle() {
        return title;
    }

    public String getHot() {
        return hot;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getLables() {
        return lables;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLables(String lables) {
        this.lables = lables;
    }
}
