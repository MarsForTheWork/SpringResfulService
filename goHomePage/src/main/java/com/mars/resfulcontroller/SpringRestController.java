package com.mars.resfulcontroller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mars.model.ProductDetailService;
import com.mars.entity.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/hello2")
public class SpringRestController {
	
	@Autowired
	private ProductDetailService ProductDetailService;
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<Object> hello(@PathVariable String name) {
		
		for (ProductDetail ProductDetail: ProductDetailService.findAll()){
			System.out.println(ProductDetail.getName());
			System.out.println(ProductDetail.getUuid());
			System.out.println(ProductDetail.getCreateDate());
			System.out.println(ProductDetail.getPrize());

		}
		
		ProductDetail productDetail = ProductDetailService.findOne(name);
		System.out.println("this is gohomepage");
		System.out.println();
		String result = "Hello " + productDetail.getName();
		System.out.println(result);
		return new ResponseEntity<Object>(productDetail, HttpStatus.OK);
	}
}