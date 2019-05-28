package com.neuedu.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Article {
  private Long aid;
  private String title;
  private Date releasetime;
  private int paykiss;
  private Long uid;
  private int typeid;
  private String iscream;
  private String istop;
  private String isend;
  private int commnum;
  private int viewnum;
  private String mdcontent;
  private String hmcontent;
}
