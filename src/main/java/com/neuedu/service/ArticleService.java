package com.neuedu.service;

import com.neuedu.pojo.Article;
import com.neuedu.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    // 新增文章
    int releaseArticle(Article article);
    // 查询首页非置顶文章信息
    List<Map<String,Object>> getIndexArticleInfo();
    // 查询首页置顶文章信息
    List<Map<String,Object>> getTopArticleInfo();
    // 分页查询
    List<Map<String,Object>> getPage(PageUtils pageUtils);
    // 文章总数
    Long getTotal();
    // 根据id查询对应文章信息
    Map<String,Object> getArticleDetail(Long aid);
    // 获取评论列表
    List<Map<String,Object>> getCommList(Long aid);
}
