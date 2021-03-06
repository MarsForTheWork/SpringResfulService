package com.mars.resfulcontroller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mars.model.ProductDetailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mars.entity.ProductDetail;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;


/**
 * @author Martin
 *
 */
@RestController
@RequestMapping("/hello2")
public class SpringRestController {
	
	@Autowired
	private ProductDetailService ProductDetailService;
	
	/**
	 * 找出所有內容
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public ResponseEntity<Object> findAll() {
		
		List<ProductDetail> productDetailList = new ArrayList<ProductDetail>();
		try {
			productDetailList = ProductDetailService.findAll();
		} catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(productDetailList, HttpStatus.OK);
	}
	
	/**
	 * 找出單一內容
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findOne", method = RequestMethod.GET)
	public ResponseEntity<Object> findOne(@RequestParam("id") String id) {
		
		// 找出單一內容
		ProductDetail productDetail = null;
		try {
			productDetail = ProductDetailService.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(productDetail, HttpStatus.OK);
	}
	
	/**
	 * 接受參數Form 
	 * @param formData
	 * @return 
	 */
	@RequestMapping(method=RequestMethod.POST, value="/insert")
	public ProductDetail createRole(@RequestBody MultiValueMap<String,String> formData){
		
		ProductDetail productDetail = new ProductDetail();
		productDetail.setUuid(formData.getFirst("formId"));
		productDetail.setName(formData.getFirst("formName"));
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-mm-dd").parse(formData.getFirst("formDateTime"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		productDetail.setCreateDate(date);
		BigDecimal d = new BigDecimal(formData.getFirst("formPrize"));
		productDetail.setPrize(d);
		
		return ProductDetailService.insert(productDetail);

	}	
	@RequestMapping(method=RequestMethod.GET, value="/delete")
	public void delete(@RequestParam("id") String id) {
		// 找出單一內容
		ProductDetailService.delete(id);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/findBySelectDate", 
			consumes = MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8")
	public ResponseEntity<Object> findBySelectDate(@RequestBody String receivingData) throws JsonProcessingException, IOException {
		
		// 請求格式轉換
		JsonNode receivingDataNode = new ObjectMapper().readTree(receivingData);
		JsonNode start = receivingDataNode.get("start");
		JsonNode end = receivingDataNode.get("end");

		List<ProductDetail> list = new ArrayList<ProductDetail>();
		for (ProductDetail ProductDetail: ProductDetailService.custFindBySelectDate(start.asText(),end.asText())){
			list.add(ProductDetail);
		}
		
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}
}