package com.neuedu.pojo;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Userinfo {
    private Long uid;
    private String nickname;
    private String password;
    private String email;
    private String sex;
    private String city;
    private String sign;
    private String headurl;
    private int authority;
    private Date regtime;
    private int kissnum;
    private int isexsits;
}
