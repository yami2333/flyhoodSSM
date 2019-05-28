package com.neuedu.dao;

import com.neuedu.pojo.Article;
import com.neuedu.pojo.Userinfo;
import com.neuedu.utils.DBUtils;
import com.neuedu.utils.PageUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleDaoImpl implements ArticleDao {
    @Override
    public int releaseArticle(Article article) {
        int count = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBUtils.getInstance().getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("insert into article(title,releasetime,paykiss,uid,typeid,mdcontent,hmcontent)\n" +
                    "values (?,?,?,?,?,?,?)");
            ps.setString(1,article.getTitle());
            ps.setTimestamp(2,new Timestamp(article.getReleasetime().getTime()));
            ps.setInt(3,article.getPaykiss());
            ps.setLong(4,article.getUid());
            ps.setInt(5,article.getTypeid());
            ps.setString(6,article.getMdcontent());
            ps.setString(7,article.getHmcontent());
            count = ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(connection);
            DBUtils.getInstance().close(ps);
        }
        return count;
    }

    @Override
    public void costKiss(Article article) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBUtils.getInstance().getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("update userinfo set kissnum = kissnum - ? where uid = ?");
            ps.setInt(1,article.getPaykiss());
            ps.setLong(2,article.getUid());
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(connection);
            DBUtils.getInstance().close(ps);
        }
    }

    @Override
    /**
     * 查询首页非置顶文章
     */
    public List<Map<String, Object>> getIndexArticleInfo() {
        List<Map<String, Object>> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBUtils.getInstance().getConnection();
            ps = connection.prepareStatement("select iscream,typename,title,headurl,nickname,DATE_FORMAT(releasetime,'%Y-%m-%d') releasetime,paykiss,isend,commnum,c.typeid,aid,u.uid\n" +
                    "from userinfo u\n" +
                    "join article a\n" +
                    "on a.uid = u.uid\n" +
                    "join category c\n" +
                    "on c.typeid = a.typeid\n" +
                    "where istop = 0\n" +
                    "order by releasetime desc,aid desc limit 0,10");
            rs = ps.executeQuery();
            while(rs.next()){
                Map<String, Object> map = new HashMap<>();
                map.put("typename",rs.getString("typename"));
                map.put("title",rs.getString("title"));
                map.put("headurl",rs.getString("headurl"));
                map.put("nickname",rs.getString("nickname"));
                map.put("releasetime",rs.getString("releasetime"));
                map.put("paykiss",rs.getInt("paykiss"));
                map.put("isend",rs.getInt("isend"));
                map.put("iscream",rs.getInt("iscream"));
                map.put("commnum",rs.getInt("commnum"));
                map.put("typeid",rs.getInt("typeid"));
                map.put("aid",rs.getLong("aid"));
                map.put("uid",rs.getLong("uid"));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(connection);
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getTopArticleInfo() {
        List<Map<String, Object>> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBUtils.getInstance().getConnection();
            ps = connection.prepareStatement("select iscream,istop,typename,title,headurl,nickname,DATE_FORMAT(releasetime,'%Y-%m-%d') releasetime,paykiss,isend,commnum,c.typeid,aid,u.uid\n" +
                    "from userinfo u\n" +
                    "join article a\n" +
                    "on a.uid = u.uid\n" +
                    "join category c\n" +
                    "on c.typeid = a.typeid\n" +
                    "where istop = 1\n" +
                    "order by releasetime desc,aid desc limit 0,4");
            rs = ps.executeQuery();
            while(rs.next()){
                Map<String, Object> map = new HashMap<>();
                map.put("typename",rs.getString("typename"));
                map.put("title",rs.getString("title"));
                map.put("headurl",rs.getString("headurl"));
                map.put("nickname",rs.getString("nickname"));
                map.put("releasetime",rs.getString("releasetime"));
                map.put("paykiss",rs.getInt("paykiss"));
                map.put("isend",rs.getInt("isend"));
                map.put("iscream",rs.getInt("iscream"));
                map.put("istop",rs.getInt("istop"));
                map.put("commnum",rs.getInt("commnum"));
                map.put("typeid",rs.getInt("typeid"));
                map.put("aid",rs.getLong("aid"));
                map.put("uid",rs.getLong("uid"));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(connection);
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getPage(PageUtils pageUtils) {
        List<Map<String, Object>> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBUtils.getInstance().getConnection();
            ps = connection.prepareStatement("select iscream,istop,typename,title,headurl,nickname,DATE_FORMAT(releasetime,'%Y-%m-%d') releasetime,paykiss,isend,commnum,c.typeid,aid,u.uid\n" +
                    "from userinfo u\n" +
                    "join article a\n" +
                    "on a.uid = u.uid\n" +
                    "join category c\n" +
                    "on c.typeid = a.typeid\n" +
                    "order by releasetime desc,aid desc limit ?,?");
            ps.setInt(1,pageUtils.getPageIndex());
            ps.setInt(2,pageUtils.getPageSize());
            rs = ps.executeQuery();
            while(rs.next()){
                Map<String, Object> map = new HashMap<>();
                map.put("typename",rs.getString("typename"));
                map.put("title",rs.getString("title"));
                map.put("headurl",rs.getString("headurl"));
                map.put("nickname",rs.getString("nickname"));
                map.put("releasetime",rs.getString("releasetime"));
                map.put("paykiss",rs.getInt("paykiss"));
                map.put("isend",rs.getInt("isend"));
                map.put("iscream",rs.getInt("iscream"));
                map.put("istop",rs.getInt("istop"));
                map.put("commnum",rs.getInt("commnum"));
                map.put("typeid",rs.getInt("typeid"));
                map.put("aid",rs.getLong("aid"));
                map.put("uid",rs.getLong("uid"));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(connection);
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
        }
        return list;
    }

    @Override
    public Long getTotal() {
        Long count = 0L;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBUtils.getInstance().getConnection();
            ps = connection.prepareStatement("select count(*) counts from article");
            rs = ps.executeQuery();
            if(rs.next()){
                count = rs.getLong("counts");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(connection);
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
        }
        return count;
    }

    @Override
    public Map<String, Object> getArticleDetail(Long aid) {
        Map<String, Object> map = new HashMap<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBUtils.getInstance().getConnection();
            ps = connection.prepareStatement("select aid,u.uid,title,typename,isend,istop,iscream,commnum,viewnum,headurl,releasetime,paykiss,nickname,hmcontent\n" +
                    "from userinfo u\n" +
                    "join article a\n" +
                    "on u.uid = a.uid\n" +
                    "join category c\n" +
                    "on c.typeid = a.typeid\n" +
                    "where aid = ?");
            ps.setLong(1,aid);
            rs = ps.executeQuery();
            if(rs.next()){
                map.put("typename",rs.getString("typename"));
                map.put("title",rs.getString("title"));
                map.put("headurl",rs.getString("headurl"));
                map.put("nickname",rs.getString("nickname"));
                map.put("releasetime",rs.getString("releasetime"));
                map.put("paykiss",rs.getInt("paykiss"));
                map.put("isend",rs.getInt("isend"));
                map.put("iscream",rs.getInt("iscream"));
                map.put("istop",rs.getInt("istop"));
                map.put("commnum",rs.getInt("commnum"));
                map.put("viewnum",rs.getInt("viewnum"));
                map.put("aid",rs.getLong("aid"));
                map.put("uid",rs.getLong("uid"));
                map.put("hmcontent",rs.getString("hmcontent"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(connection);
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
        }
        return map;
    }

    /**
     * 别忘了更新浏览量
     * @param aid
     */
    @Override
    public void updateViewNum(Long aid) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBUtils.getInstance().getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("update article set viewnum = viewnum + 1 where aid = ?");
            ps.setLong(1,aid);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(connection);
            DBUtils.getInstance().close(ps);
        }
    }

    @Override
    public List<Map<String, Object>> getCommList(Long aid) {
        List<Map<String, Object>> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBUtils.getInstance().getConnection();
            ps = connection.prepareStatement("select cid,isaccept,authority,u.uid,headurl,nickname,DATE_FORMAT(commtime,'%Y-%m-%d') commtime,commcontent,goodnum\n" +
                    "from userinfo u\n" +
                    "join comments c\n" +
                    "on u.uid = c.uid\n" +
                    "join article a\n" +
                    "on c.aid = a.aid\n" +
                    "where a.aid = ?");
            ps.setLong(1,aid);
            rs = ps.executeQuery();
            while(rs.next()){
                Map<String, Object> map = new HashMap<>();
                map.put("uid",rs.getLong("uid"));
                map.put("cid",rs.getLong("cid"));
                map.put("headurl",rs.getString("headurl"));
                map.put("nickname",rs.getString("nickname"));
                map.put("commtime",rs.getString("commtime"));
                map.put("commcontent",rs.getString("commcontent"));
                map.put("goodnum",rs.getInt("goodnum"));
                map.put("authority",rs.getInt("authority"));
                map.put("isaccept",rs.getInt("isaccept"));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(connection);
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
        }
        return list;
    }
}
