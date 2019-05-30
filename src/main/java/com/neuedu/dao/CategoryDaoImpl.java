package com.neuedu.dao;

import com.neuedu.pojo.Category;
import com.neuedu.pojo.Userinfo;
import com.neuedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> serchArtCatagory() {
        List<Category> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBUtils.getInstance().getConnection();
            ps = connection.prepareStatement("select typeid,typename from category");
            rs = ps.executeQuery();
            while(rs.next()){
                Category category = new Category();
//                category.setTypeid(rs.getInt("typeid"));
//                category.setTypename(rs.getString("typename"));
                list.add(category);
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
