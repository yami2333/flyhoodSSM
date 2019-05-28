package com.neuedu.dao;

import com.neuedu.pojo.Article;
import com.neuedu.pojo.Userinfo;
import com.neuedu.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface ArticleDao {
    // 新增文章
    int releaseArticle(Article article);
    // 扣对应用户的飞吻数
    void costKiss(Article article);
    // 查询首页文章信息
    List<Map<String,Object>> getIndexArticleInfo();
    // 查询首页置顶文章信息
    List<Map<String,Object>> getTopArticleInfo();
    // 分页查询
    List<Map<String,Object>> getPage(PageUtils pageUtils);
    // 文章总数
    Long getTotal();
    // 根据id查询对应文章信息
    Map<String,Object> getArticleDetail(Long aid);
    // 更新浏览量
    void updateViewNum(Long aid);
    // 获取文章评论列表
    List<Map<String,Object>> getCommList(Long aid);

}
