package com.codegym.blog.model;

import javax.persistence.*;

@Entity
@Table(name = "bloglist")
public class Blog {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String imei;
    private String title;
    private String content;
    private float rate = 0;
    public Blog() {
    }

    public Blog(String imei, String title, String content) {
        this.imei = imei;
        this.title = title;
        this.content = content;
    }
    @Override
    public String toString() {
        return String.format("Blog[id=%d, imei='%s', title='%s', content='%s', rate=%d]", id, imei, title,content,rate);
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
