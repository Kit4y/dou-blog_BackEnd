package com.dounine.blog.bean;

public class Comment {

    private int id;

    private int articleId;      // 被评论的文章ID

    private String articleTitle; // 文章标题，传输给前端

    private int parentId = 0;  // 回复的父id 若是评论则为 0，若是评论中的回复则为对应评论的id

    private int commenterId;    // 评论者ID

    private String commenterName; // 评论者用户名，传输给前端

    private String commentDate; // 评论日期

    private int likes=0;

    private String commentContent;  // 评论内容

    public Comment() {}

    public Comment(int articleId, int parentId, int commenterId,
                    String commentDate, int likes, String commentContent) {
        this.articleId = articleId;
        this.parentId = parentId;
        this.commenterId = commenterId;
        this.commentDate = commentDate;
        this.likes = likes;
        this.commentContent = commentContent;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void setCommenterId(int commenterId) {
        this.commenterId = commenterId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public int getLikes() {
        return likes;
    }

    public int getId() {
        return id;
    }

    public int getArticleId() {
        return articleId;
    }

    public int getCommenterId() {
        return commenterId;
    }

    public int getParentId() {
        return parentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public String getArticleTitle() {
        return articleTitle;
    }
}
