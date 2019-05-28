package com.neuedu.service;

import com.neuedu.dao.CategoryDao;
import com.neuedu.dao.CategoryDaoImpl;
import com.neuedu.pojo.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> serchArtCatagory() {
        CategoryDao categoryDao = new CategoryDaoImpl();
        return categoryDao.serchArtCatagory();
    }
}
