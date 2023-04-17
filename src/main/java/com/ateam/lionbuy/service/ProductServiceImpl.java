package com.ateam.lionbuy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ateam.lionbuy.dto.ProductDTO;
import com.ateam.lionbuy.dto.ProductLowpriceDTO;
import com.ateam.lionbuy.dto.ProductMallDTO;
import com.ateam.lionbuy.entity.Product;
import com.ateam.lionbuy.entity.Product_lowprice;
import com.ateam.lionbuy.entity.Product_mall;
import com.ateam.lionbuy.repository.ProductLowpriceRepository;
import com.ateam.lionbuy.repository.ProductMallRepository;
import com.ateam.lionbuy.repository.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductRepository pRepository;
    @Autowired
    private ProductMallRepository pMallRepository;
    @Autowired
    private ProductLowpriceRepository pLowpriceRepository;
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

    @Override
    public Map<String, Object> getUserProduct(String pd_name) {
        Map<String, Object> getUserProductInfo = new HashMap<String, Object>();
        Product product = pRepository.getProduct(pd_name).get();
        ProductDTO productDTO = product_build_dto(product);
        getUserProductInfo.put("product", productDTO);
        List<Product_lowprice> productLowEntityList = pLowpriceRepository.getProductLowprice(product.getPd_name()).get();
        List<ProductLowpriceDTO> productLowDtoList = new ArrayList<ProductLowpriceDTO>();
        for (int i = 0; i < productLowEntityList.size(); i++) {
            productLowDtoList.add(lowprice_build_dto(productLowEntityList.get(i)));
        }
        getUserProductInfo.put("lowprice", productLowDtoList);
        Product_mall product_mall = pMallRepository.getLowMall(pd_name).get();
        ProductMallDTO productMallDTO = mall_build_dto(product_mall);
        getUserProductInfo.put("lowmall", productMallDTO);

        List<Product> relatedList = pRepository.getRelatedList(pd_name);
        List<ProductDTO> related_DTO = new ArrayList<>();
        if (relatedList.size()>0) {
            for (Product relatedProduct : relatedList) {
                ProductDTO relatedproductDTO = product_build_dto(relatedProduct);
                related_DTO.add(relatedproductDTO);
            }
            getUserProductInfo.put("related", related_DTO);
        }
        return getUserProductInfo;
    }

    @Override
    public ProductMallDTO getLowProduct_mall(String pd_name) {
        Product_mall product_mall = pMallRepository.getLowMall(pd_name).get();
        ProductMallDTO productMallDTO = mall_build_dto(product_mall);
        return productMallDTO;
    }
    
}
