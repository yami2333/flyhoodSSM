package com.neuedu.utils;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PageInfo {
    private List<Map<String,Object>> list;
    private Long total;
}
