package com.neuedu.service;

import com.neuedu.dao.ArticleDao;
import com.neuedu.dao.ArticleDaoImpl;
import com.neuedu.mapper.ArticleMapper;
import com.neuedu.pojo.Article;
import com.neuedu.utils.PageUtils;
import com.neuedu.utils.SqlSessionUtils;

import java.util.List;
import java.util.Map;

public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao = new ArticleDaoImpl();
    private ArticleMapper articleMapper = SqlSessionUtils.getInstance().getSqlSession().getMapper(ArticleMapper.class);
    @Override
    public int releaseArticle(Article article) {
        // 发布文章
        int num = articleDao.releaseArticle(article);
        // 扣飞吻
        articleDao.costKiss(article);
        return num;
    }

    @Override
    public List<Map<String, Object>> getIndexArticleInfo() {
        return articleDao.getIndexArticleInfo();
    }

    @Override
    public List<Map<String, Object>> getTopArticleInfo() {
        return articleDao.getTopArticleInfo();
    }

    @Override
    public List<Map<String, Object>> getPage(PageUtils pageUtils) {
        return articleDao.getPage(pageUtils);
    }

    @Override
    public Long getTotal() {
        return articleDao.getTotal();
    }

    @Override
    public Map<String, Object> getArticleDetail(Long aid) {
        articleDao.updateViewNum(aid);
        return articleDao.getArticleDetail(aid);
    }

    @Override
    public List<Map<String, Object>> getCommList(Long aid) {
        return articleDao.getCommList(aid);
    }
}
