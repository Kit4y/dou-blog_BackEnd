package com.dounine.myblog.dao.impl;

import com.dounine.myblog.bean.User;
import com.dounine.myblog.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> listAllUsers() {
        return jdbcTemplate.query("select * from user", new BeanPropertyRowMapper(User.class));
    }

    @Override
    public User findById(int id) {
        String sql = "select * from user where id = ?";
        return jdbcTemplate.queryForObject(sql, User.class, id);
    }

    @Override
    public User findByName(String userName) {
        String sql = "select * from user where name = ?";
        return jdbcTemplate.queryForObject(sql, User.class, userName);
    }

    @Override
    public int insert(User user) {
        String sql = "insert into user(name, phone, password, gender, personalBrief, email) " +
                "values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                user.getName(), user.getPhone(), user.getPassword(),
                user.getGender(), user.getPersonalBrief(), user.getEmail());
    }

    @Override
    public int delete(int userId) {
        String sql = "delete from user where id = ?";
        return jdbcTemplate.update(sql, userId);
    }

    @Override
    public int update(User newUser) {
        String sql = "update user set name=?, phone=?, password=?, gender=?, personalBrief=?, email=? where id=?";
        return jdbcTemplate.update(sql, newUser.getName(), newUser.getPhone(), newUser.getPassword(),
                newUser.getGender(), newUser.getPersonalBrief(), newUser.getEmail(), newUser.getId());
    }
}