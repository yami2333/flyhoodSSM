package com.neuedu.controllor;

import com.neuedu.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("category")
public class CheckCategoryController {
    @Autowired
    CategoryMapper categoryMapper;

    @RequestMapping("check")
    public ModelAndView catCheck(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("categoryList",categoryMapper.serchArtCatagory());
        return mv;
    }
}
