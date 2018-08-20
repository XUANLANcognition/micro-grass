package com.example.xuanlan.nightwatchman;

public class KnowLedge {

    private String head;
    private String body;
    private String hot;
    private String id;
    private String user;
    private String pubDate;

    public KnowLedge(String head, String body, String hot, String id){
        this.head = head;
        this.body = body;
        this.hot = hot;
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public String getBody() {
        return body;
    }

    public String getHot() {
        return hot;
    }

    public String getId() {
        return id;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getUser() {
        return user;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
