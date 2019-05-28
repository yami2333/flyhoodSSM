package com.neuedu.dao;

import com.neuedu.pojo.Userinfo;
import com.neuedu.utils.DBUtils;

import java.sql.*;

public class UserinfoDaoImpl implements UserinfoDao {
    @Override
    public int userEmailCheck(String email) {
        int count = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBUtils.getInstance().getConnection();
            ps = connection.prepareStatement("select count(*) counts from userinfo where email = ?");
            ps.setString(1,email);
            rs = ps.executeQuery();
            if(rs.next()){
                count = rs.getInt("counts");
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
    public int userReg(Userinfo userinfo) {
        int count = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBUtils.getInstance().getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("insert into userinfo(nickname,password,email,regtime) values (?,?,?,?)");
            ps.setString(1,userinfo.getNickname());
            ps.setString(2,userinfo.getPassword());
            ps.setString(3,userinfo.getEmail());
            ps.setTimestamp(4,new Timestamp(userinfo.getRegtime().getTime()));
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
    public Userinfo userLogin(Userinfo userinfo) {
        Userinfo userinfo1 = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBUtils.getInstance().getConnection();
            ps = connection.prepareStatement("select uid,nickname,password,email," +
                    "sex,city,sign,headurl,authority,regtime,kissnum,isexsits " +
                    "from userinfo where email = ? and password = ?");
            ps.setString(1,userinfo.getEmail());
            ps.setString(2,userinfo.getPassword());
            rs = ps.executeQuery();
            if(rs.next()){
                userinfo1 = new Userinfo();
                userinfo1.setPassword(rs.getString("password"));
                userinfo1.setEmail(rs.getString("email"));
                userinfo1.setRegtime(rs.getDate("regtime"));
                userinfo1.setNickname(rs.getString("nickname"));
                userinfo1.setSex(rs.getString("sex"));
                userinfo1.setAuthority(rs.getInt("authority"));
                userinfo1.setCity(rs.getString("city"));
                userinfo1.setHeadurl(rs.getString("headurl"));
                userinfo1.setIsexsits(rs.getInt("isexsits"));
                userinfo1.setKissnum(rs.getInt("kissnum"));
                userinfo1.setSign(rs.getString("sign"));
                userinfo1.setUid(rs.getLong("uid"));
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
        return userinfo1;
    }

    @Override
    public int uploadHeadImg(Userinfo userinfo) {
        int count = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBUtils.getInstance().getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("update userinfo set headurl = ? where uid = ?");
            ps.setString(1,userinfo.getHeadurl());
            ps.setLong(2,userinfo.getUid());
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
}
