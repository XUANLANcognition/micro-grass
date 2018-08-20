package com.example.xuanlan.nightwatchman;

public class Answer {

    private String content;
    private String pub_date;
    private String user;
    private String praise;
    private String username;
    private String userPic;

    public Answer(String content, String pub_date, String user, String praise, String username, String userPic) {
        this.content = content;
        this.pub_date = pub_date;
        this.user = user;
        this.praise = praise;
        this.username = username;
        this.userPic = userPic;
    }

    public String getContent() {
        return content;
    }

    public String getUser() {
        return user;
    }

    public String getPraise() {
        return praise;
    }

    public String getPub_date() {
        return pub_date;
    }

    public String getUsername() {
        return username;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPraise(String praise) {
        this.praise = praise;
    }

    public void setPub_date(String pub_date) {
        this.pub_date = pub_date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }
}
