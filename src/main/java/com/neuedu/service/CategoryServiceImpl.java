package com.neuedu.service;

import com.neuedu.dao.CategoryDao;
import com.neuedu.dao.CategoryDaoImpl;
import com.neuedu.mapper.CategoryMapper;
import com.neuedu.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> serchArtCatagory() {
//        CategoryDao categoryDao = new CategoryDaoImpl();

        return categoryMapper.serchArtCatagory();
    }
}
