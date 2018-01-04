package com.mars.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mars.entity.ProductDetail;

@Service
public class ProductDetailService {

	@Autowired
	private ProductDetailRepository productDetailRepository;

	public List<ProductDetail> findAll() {
		return productDetailRepository.findAll();
	}
	
	public ProductDetail findById(String id) {
		return productDetailRepository.findOne(id);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public ProductDetail insert(ProductDetail productDetail) {
		return productDetailRepository.save(productDetail);
	}
	
	public List<ProductDetail> custFindBySelectDate(String start, String end) {
		return productDetailRepository.findBySelectDate(start, end);
	}
	
	public void delete(String id) {
		productDetailRepository.delete(id);
	}
}
