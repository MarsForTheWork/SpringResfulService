package com.mars.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.entity.ProductDetail;

@Service
public class ProductDetailService {

	@Autowired
	private ProductDetailRepository productDetailRepository;

	public List<ProductDetail> findAll() {
		return productDetailRepository.findAll();
	}
}
