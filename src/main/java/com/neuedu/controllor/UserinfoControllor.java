package com.neuedu.controllor;

import com.neuedu.mapper.UserinfoMapper;
import com.neuedu.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserinfoControllor {
    @Autowired
    UserinfoService userinfoService;

    @GetMapping("reg")
    public int emailCheck(@RequestParam("email") String email){

        return userinfoService.userEmailCheck(email);
    }
}
