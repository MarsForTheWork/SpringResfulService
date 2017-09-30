package com.mars.resfulcontroller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello2")
public class SpringRestController {
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String hello(@PathVariable String name) {
		System.out.println("this is gohomepage");
		String result = "Hello " + name;
		return result;
	}
}