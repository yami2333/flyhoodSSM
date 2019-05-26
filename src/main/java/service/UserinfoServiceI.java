package service;

import dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Userinfo;

@Service("userService")
public class UserinfoServiceI implements UserinfoService{
    public UserMapper getUserMapper() {
        return userMapper;
    }
@Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    private UserMapper userMapper;

    public Userinfo getUserById(int id) {
        return userMapper.getUserById(id);
    }
}
