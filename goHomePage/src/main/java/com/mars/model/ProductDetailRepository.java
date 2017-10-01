package com.mars.model;


import org.springframework.stereotype.Repository;

import com.mars.entity.ProductDetail;


@Repository
public interface ProductDetailRepository extends EntityRepository<ProductDetail, String> {

}
