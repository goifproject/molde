package com.limefriends.molde.entity.news;

import com.limefriends.molde.entity.comment.CommentEntity;

import java.util.List;

public class CardNewsEntity {

    private int newsId;
    private String postId;
    private String description;
    private String date;
    private List<CardNewsImageResponseInfoEntity> newsImg = null;

     private List<CommentEntity> comments;

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void addComments(CommentEntity comment) {
        comments.add(comment);
    }

    public CardNewsEntity(int newsId, String postId, String description, String date,
                          List<CardNewsImageResponseInfoEntity> newsImg) {
        this.newsId = newsId;
        this.postId = postId;
        this.description = description;
        this.date = date;
        this.newsImg = newsImg;
    }

    public CardNewsEntity(int newsId, String postId, String description, String date,
                          List<CardNewsImageResponseInfoEntity> newsImg, List<CommentEntity> commentList) {
        this.newsId = newsId;
        this.postId = postId;
        this.description = description;
        this.date = date;
        this.newsImg = newsImg;
        this.comments = commentList;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<CardNewsImageResponseInfoEntity> getNewsImg() {
        return newsImg;
    }

    public void setNewsImg(List<CardNewsImageResponseInfoEntity> newsImg) {
        this.newsImg = newsImg;
    }
}