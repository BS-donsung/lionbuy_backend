package com.ateam.lionbuy.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ateam.lionbuy.dto.ProductDTO;
import com.ateam.lionbuy.dto.ProductLowpriceDTO;
import com.ateam.lionbuy.dto.ProductMallDTO;
import com.ateam.lionbuy.entity.Product;
import com.ateam.lionbuy.entity.ProductLowprice;
import com.ateam.lionbuy.entity.ProductMall;
import com.ateam.lionbuy.repository.ProductLowpriceRepository;
import com.ateam.lionbuy.repository.ProductMallRepository;
import com.ateam.lionbuy.repository.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductRepository pRepository;
    @Autowired
    private ProductMallRepository pMallRepository;
    @Autowired
    private ProductLowpriceRepository pLowpriceRepository;
    @Autowired
    private final CategoryService categoryService;
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
    public Map<String, Object> getProduct(String pdName) {
        Map<String, Object> getUserProductInfo = new HashMap<String, Object>();
        Optional<Product> product1 = pRepository.findByPdname(pdName);
        Product productData;
        if (product1.isPresent()) {
            productData = product1.get();
            ProductDTO productDTO = product_build_dto(product1.get());
            getUserProductInfo.put("product", productDTO);

            Set<String> tags = categoryService.relation_categories(productData.getPno());
            getUserProductInfo.put("tags", tags);

            Optional<List<ProductLowprice>> productLowEntityList = pLowpriceRepository.getProductLowprice(productData.getPno());
            List<ProductLowpriceDTO> productLowDtoList = new ArrayList<ProductLowpriceDTO>();
            List<ProductLowprice> productLowList;
            if (productLowEntityList.isPresent()) {
                productLowList = productLowEntityList.get();
                for (int i = 0; i < productLowList.size(); i++) {
                    productLowDtoList.add(lowprice_build_dto(productLowList.get(i)));
                }
                getUserProductInfo.put("lowprice", productLowDtoList);
            }

            Optional<ProductMall> productMall = pMallRepository.getLowMall(productData.getPno());
            if (productMall.isPresent()) {
                ProductMallDTO productMallDTO = mall_build_dto(productMall.get());
                getUserProductInfo.put("lowmall", productMallDTO);
            }

            Optional<List<Product>> relatedList = pRepository.getRelatedList(pdName);
            List<ProductDTO> related_DTO = new ArrayList<>();
            List<Product> relationList;
            if (relatedList.isPresent()) {
                relationList = relatedList.get();
                if (relationList.size() > 0) {
                    for (Product relatedProduct : relationList) {
                        ProductDTO relatedproductDTO = product_build_dto(relatedProduct);
                        related_DTO.add(relatedproductDTO);
                    }
                    getUserProductInfo.put("related", related_DTO);
                }
            }
            return getUserProductInfo;
        } else {
            return null;
        }
    }

    @Override
    public ProductMallDTO getLowProduct_mall(String pdName) {
        Product product1 = pRepository.findByPdname(pdName).get();
        ProductMall productMall = pMallRepository.getLowMall(product1.getPno()).get();
        ProductMallDTO productMallDTO = mall_build_dto(productMall);
        return productMallDTO;
    }
    
}
