package com.neuedu.service;

import com.neuedu.dao.UserinfoDao;
import com.neuedu.dao.UserinfoDaoImpl;
import com.neuedu.mapper.UserinfoMapper;
import com.neuedu.pojo.Userinfo;
import com.neuedu.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserinfoServiceImpl implements UserinfoService {
//    private UserinfoDao ud = new UserinfoDaoImpl();
//    private SqlSession sqlSession = SqlSessionUtils.getInstance().getSqlSession();
    @Autowired
    UserinfoMapper userinfoMapper;
    @Override
    public int userEmailCheck(String email) {
        // 调用Dao
        return userinfoMapper.userEmailCheck(email);
    }
    @Override
    public int userReg(Userinfo userinfo) {

        return userinfoMapper.userReg(userinfo);
    }

    @Override
    public Userinfo userLogin(Userinfo userinfo) {
        return userinfoMapper.userLogin(userinfo);
    }

    @Override
    public int uploadHeadImg(Userinfo userinfo) {
        return userinfoMapper.uploadHeadImg(userinfo);
    }
}
