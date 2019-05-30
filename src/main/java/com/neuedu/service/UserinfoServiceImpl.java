package com.neuedu.service;

import com.neuedu.dao.UserinfoDao;
import com.neuedu.dao.UserinfoDaoImpl;
import com.neuedu.mapper.UserinfoMapper;
import com.neuedu.pojo.Userinfo;
import com.neuedu.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

@Service
public class UserinfoServiceImpl implements UserinfoService {


    //    private UserinfoDao ud = new UserinfoDaoImpl();
//    private SqlSession sqlSession = SqlSessionUtils.getInstance().getSqlSession();

    @Autowired
    private UserinfoMapper userinfoMapper;

    public int userEmailCheck(String email) {
//        // 调用Dao
//        return ud.userEmailCheck(email);
        return userinfoMapper.userEmailCheck(email);
    }
    @Override
    @Transactional
    public int userReg(Userinfo userinfo) {
//        UserinfoMapper um = sqlSession.getMapper(UserinfoMapper.class);
//        int num = um.userReg(userinfo);
//        sqlSession.commit();
//        return num;
        return userinfoMapper.userReg(userinfo);
    }

    @Override
    public Userinfo userLogin(Userinfo userinfo) {
        return userinfoMapper.userLogin(userinfo);
    }

    @Override
    @Transactional
    public int uploadHeadImg(Userinfo userinfo) {
        return userinfoMapper.uploadHeadImg(userinfo);
    }

}
