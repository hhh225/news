package com.example.knowledgeaggregation.pojo;

import java.io.Serializable;

public class Article implements Serializable {
    private String id;
    private String article_title;
    private String article_writer;
    private String article_time;
    private String article_source;
    private String article_content;
    private String article_url;

    public Article() {
    }

    public Article(String id, String article_title, String article_writer, String article_time, String article_source, String article_content, String article_url) {
        this.id = id;
        this.article_title = article_title;
        this.article_writer = article_writer;
        this.article_time = article_time;
        this.article_source = article_source;
        this.article_content = article_content;
        this.article_url = article_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;

    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_writer() {
        return article_writer;
    }

    public void setArticle_writer(String article_writer) {
        this.article_writer = article_writer;
    }

    public String getArticle_time() {
        return article_time;
    }

    public void setArticle_time(String article_time) {
        this.article_time = article_time;
    }

    public String getArticle_source() {
        return article_source;
    }

    public void setArticle_source(String article_source) {
        this.article_source = article_source;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }
}

