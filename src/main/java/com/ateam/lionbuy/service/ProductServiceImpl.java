package com.ateam.lionbuy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ateam.lionbuy.dto.ProductDTO;
import com.ateam.lionbuy.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductDTO> findByTags(String[] tags) {
        List<ProductDTO> product_dto_list = new ArrayList<ProductDTO>();
        List<String> tag_list = new ArrayList<String>();
        for (int i = 0; i < tags.length; i++) {
            tag_list.add(tags[i]);
        }
        String jpql = "SELECT p FROM Product p JOIN Category c WHERE ";
        for (int i = 0; i < tag_list.size(); i++) {
            if(i>0) jpql += " OR ";
            jpql += "c.categories LIKE :tag"+i;
        }

        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        for (int i = 0; i < tag_list.size(); i++) {
            query.setParameter("tag"+i, "%"+tag_list.get(i)+"%");
        }
        List<Product> result = query.getResultList();

        for (int i = 0; i < result.size(); i++) {
            product_dto_list.add(product_build_dto(result.get(i)));
        }
        return product_dto_list;
    }
    
}
