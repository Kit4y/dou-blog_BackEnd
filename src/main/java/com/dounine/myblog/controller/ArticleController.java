package com.dounine.myblog.controller;

import com.alibaba.fastjson.JSONObject;
import com.dounine.myblog.bean.Article;
import com.dounine.myblog.dao.ArticleDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    ArticleDao articleDao;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public String listAll(@RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "10") int size) {
        List<Article> articleList = articleDao.listAllArticles();
        PageHelper.startPage(page, size);
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        // System.out.println(JSONObject.toJSONString(pageInfo));
        return JSONObject.toJSONString(pageInfo);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public JSONObject findById(@RequestParam int id) {
        Article article = articleDao.findById(id);
        JSONObject retMsg = new JSONObject();
        if (article == null) {
            retMsg.put("code", 0);
            retMsg.put("msg", "find this article fail");
        }
        else {
            retMsg.put("code", 1);
            retMsg.put("msg", "success");
            retMsg.put("data", JSONObject.toJSONString(article));
        }
        return retMsg;
    }

    @RequestMapping(value = "/findByArticleTitle", method = RequestMethod.GET)
    public JSONObject findByArticleTitle(@RequestParam String articleTitle) {
        Article article = articleDao.findByArticleTitle(articleTitle);
        JSONObject retMsg = new JSONObject();
        if (article == null) {
            retMsg.put("code", 0);
            retMsg.put("msg", "find this article fail");
        }
        else {
            retMsg.put("code", 1);
            retMsg.put("msg", "success");
            retMsg.put("data", JSONObject.toJSONString(article));
        }
        return retMsg;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public JSONObject submit(@RequestBody Article article) {
        JSONObject retMsg = new JSONObject();
        Article oldArtical = articleDao.findByArticleTitle(article.getArticleTitle());
        if (oldArtical == null) {
            article.setPublishDate(dateFormat.format(new Date()));  // new Date()为获取当前系统时间
            if (articleDao.insert(article) > 0) {
                retMsg.put("code", 1);
                retMsg.put("msg", "insert success");
            }
            else {
                retMsg.put("code", 0);
                retMsg.put("msg", "insert error");
            }
        }
        else {
            article.setUpdateDate(dateFormat.format(new Date()));
            article.setId(oldArtical.getId());
            if (articleDao.update(article) > 0) {
                retMsg.put("code", 1);
                retMsg.put("msg", "update success");
            }
            else {
                retMsg.put("code", 0);
                retMsg.put("msg", "update error");
            }
        }
        return retMsg;
    }

//    @RequestMapping(value = "/insert", method = RequestMethod.POST)
//    public JSONObject insert(@RequestBody Article article) {
//        JSONObject retMsg = new JSONObject();
//        if (articleDao.insert(article) > 0) {
//            retMsg.put("code", 1);
//            retMsg.put("msg", "success");
//            return retMsg;
//        }
//        else {
//            retMsg.put("code", 0);
//            retMsg.put("msg", "insert error");
//            return retMsg;
//        }
//    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public JSONObject delete(@RequestParam int id) {
        JSONObject retMsg = new JSONObject();
        if (articleDao.delete(id) > 0) {
            retMsg.put("code", 1);
            retMsg.put("msg", "success");
            return retMsg;
        }
        else {
            retMsg.put("code", 0);
            retMsg.put("msg", "delete error");
            return retMsg;
        }
    }

//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public JSONObject update(@RequestBody Article article) {
//        JSONObject retMsg = new JSONObject();
//        if (articleDao.update(article) > 0) {
//            retMsg.put("code", 1);
//            retMsg.put("msg", "success");
//            return retMsg;
//        }
//        else {
//            retMsg.put("code", 0);
//            retMsg.put("msg", "update error");
//            return retMsg;
//        }
//    }
}
