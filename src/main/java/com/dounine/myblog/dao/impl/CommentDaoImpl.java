package com.dounine.myblog.dao.impl;

import com.dounine.myblog.bean.Comment;
import com.dounine.myblog.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public CommentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Comment> listAllComments() {
        return jdbcTemplate.query("select * from comment", new BeanPropertyRowMapper(Comment.class));
    }

    @Override
    public Comment findById(int id) {
        String sql = "select * from comment where id = ?";
        return (Comment) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(Comment.class), id);
    }

    @Override
    public int insert(Comment comment) {
        String sql = "insert into comment(parent_id, articleId, commenterId, commentDate, commentContent, likes) " +
                "values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                comment.getParent_id(), comment.getArticleId(), comment.getCommenterId(),
                comment.getCommentDate(), comment.getCommentContent(), comment.getLikes());
    }

    @Override
    public int delete(int id) {
        String sql = "delete from comment where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int update(Comment newComment) {
        String sql = "update comment set (parent_id, articleId, commenterId, commentDate, commentContent, likes) " +
                "values(?, ?, ?, ?, ?, ?) where id = ?";
        return jdbcTemplate.update(sql,
                newComment.getParent_id(), newComment.getArticleId(), newComment.getCommenterId(),
                newComment.getCommentDate(), newComment.getCommentContent(), newComment.getLikes(),
                newComment.getId());
    }
}
