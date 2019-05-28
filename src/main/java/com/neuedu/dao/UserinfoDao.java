package com.neuedu.dao;

import com.neuedu.pojo.Userinfo;

public interface UserinfoDao {
    // 检查注册邮箱是否合法
    int userEmailCheck(String email);
    // 用户注册
    int userReg(Userinfo userinfo);
    // 用户登录
    Userinfo userLogin(Userinfo userinfo);
    // 上传头像
    int uploadHeadImg(Userinfo userinfo);
}
