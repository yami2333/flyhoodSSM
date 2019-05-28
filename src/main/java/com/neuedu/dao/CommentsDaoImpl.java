package com.neuedu.dao;

import com.neuedu.pojo.Comments;
import com.neuedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CommentsDaoImpl implements CommentsDao {
    @Override
    public int addComment(Comments comments) {
        int count = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBUtils.getInstance().getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("insert into comments(uid,aid,commcontent,commtime) values (?,?,?,?)");
            ps.setLong(1,comments.getUid());
            ps.setLong(2,comments.getAid());
            ps.setString(3,comments.getCommcontent());
            ps.setTimestamp(4,new Timestamp(comments.getCommtime().getTime()));
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
    public void updateCommNum(Comments comments) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBUtils.getInstance().getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("update article set commnum = commnum + 1 where aid = ?");
            ps.setLong(1,comments.getAid());
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
}
