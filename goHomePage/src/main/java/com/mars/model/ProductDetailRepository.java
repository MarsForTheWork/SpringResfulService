package com.mars.model;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mars.core.EntityRepository;
import com.mars.entity.ProductDetail;


@Repository
public interface ProductDetailRepository extends EntityRepository<ProductDetail, String> {

	 @Query(value = "SELECT * FROM PRODUCT_DETAIL "
	 		+ "WHERE CREATE_DATE > TO_DATE(?1, 'YYYY-MM-DD')"
	 		+ "AND CREATE_DATE < TO_DATE(?2, 'YYYY-MM-DD')"
	            , nativeQuery = true)
	  public List<ProductDetail> findBySelectDate(String start, String end);
	 
	  public ProductDetail findByName(String name);
}
 