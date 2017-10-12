package com.mars.resfulcontroller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mars.model.ProductDetailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mars.entity.ProductDetail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/hello2")
public class SpringRestController {
	
	@Autowired
	private ProductDetailService ProductDetailService;
	
	@RequestMapping(value = "/findAll/{name}", method = RequestMethod.GET)
	public ResponseEntity<Object> hello(@PathVariable String name) {
		
		// 印出所有內容
		for (ProductDetail ProductDetail: ProductDetailService.findAll()){
			System.out.println(ProductDetail.getName());
			System.out.println(ProductDetail.getUuid());
			System.out.println(ProductDetail.getCreateDate());
			System.out.println(ProductDetail.getPrize());
		}
		
		// 找出單一內容
		ProductDetail productDetail = ProductDetailService.findOne(name);
		System.out.println("this is gohomepage");
		System.out.println();
		String result = "Hello " + productDetail.getName();
		System.out.println(result);
		return new ResponseEntity<Object>(productDetail, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/findBySelectDate", 
			consumes = MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8")
	public ResponseEntity<Object> findBySelectDate(@RequestBody String receivingData) throws JsonProcessingException, IOException {
		
		// 請求格式轉換
		JsonNode receivingDataNode = new ObjectMapper().readTree(receivingData);
		JsonNode start = receivingDataNode.get("start");
		JsonNode end = receivingDataNode.get("end");

		List list = new ArrayList();
		for (ProductDetail ProductDetail: ProductDetailService.custFindBySelectDate(start.asText(),end.asText())){
			System.out.println(ProductDetail.getName());
			System.out.println(ProductDetail.getUuid());
			System.out.println(ProductDetail.getCreateDate());
			System.out.println(ProductDetail.getPrize());
			list.add(ProductDetail);
		}
		
		System.out.println("this is findBySelectDate");
		System.out.println();
		String result = "start " + start + " end:" + end;
		System.out.println(result);
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}
}