package com.ateam.lionbuy.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ateam.lionbuy.dto.CategoryDTO;
import com.ateam.lionbuy.entity.Category;
import com.ateam.lionbuy.repository.CategoryRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository cRepository;

    @Override
    public Set<String> relation_categories(String pd_name) {
        List<Category> cateGory = cRepository.get_categories(pd_name);
        Set<String> categories_list = new HashSet<String>();
        for (int i = 0; i < cateGory.size(); i++) {
            String[] categories = cateGory.get(i).getCategories().split("\\|");
            Set<String> categories_set = new HashSet<String>(Arrays.asList(categories));
            for (String element : categories_set) {
                categories_list.add(element);
            }
        }
        return categories_list;
    }
    
}
