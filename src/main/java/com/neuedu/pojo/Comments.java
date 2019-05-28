package com.neuedu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class Comments {
    private Long cid;
    private Long uid;
    private Long aid;
    private Date commtime;
    private String commcontent;
    private int goodnum;
    private String artorcomm;
}
