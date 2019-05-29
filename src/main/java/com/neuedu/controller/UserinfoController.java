package com.neuedu.controller;

import com.neuedu.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("user")
public class UserinfoController {

    @Autowired
    UserinfoService userinfoService;

    @RequestMapping("emailCheck")
    public void checkUserEmail(String email, HttpServletResponse response) throws IOException {
        int checknum = userinfoService.userEmailCheck(email);
        response.getWriter().println(checknum);
    }

    @PostMapping("reg")
    public  String userReg()
}
