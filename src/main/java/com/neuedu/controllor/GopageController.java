package com.neuedu.controllor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("go")
public class GopageController {

    @RequestMapping("reg")
    public String goReg(){
        return  "user/reg";
    }

    @RequestMapping("login")
    public String goLogin(){
        return "user/login";
    }
}
