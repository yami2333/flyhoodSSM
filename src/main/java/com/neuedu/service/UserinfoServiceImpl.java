package com.neuedu.service;

import com.neuedu.dao.UserinfoDao;
import com.neuedu.dao.UserinfoDaoImpl;
import com.neuedu.mapper.UserinfoMapper;
import com.neuedu.pojo.Userinfo;
import com.neuedu.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;

public class UserinfoServiceImpl implements UserinfoService {
    private UserinfoDao ud = new UserinfoDaoImpl();
    private SqlSession sqlSession = SqlSessionUtils.getInstance().getSqlSession();
    @Override
    public int userEmailCheck(String email) {
        // 调用Dao
        return ud.userEmailCheck(email);
    }
    @Override
    public int userReg(Userinfo userinfo) {
        UserinfoMapper um = sqlSession.getMapper(UserinfoMapper.class);
        int num = um.userReg(userinfo);
        sqlSession.commit();
        return num;
    }

    @Override
    public Userinfo userLogin(Userinfo userinfo) {
        return ud.userLogin(userinfo);
    }

    @Override
    public int uploadHeadImg(Userinfo userinfo) {
        return ud.uploadHeadImg(userinfo);
    }
}
