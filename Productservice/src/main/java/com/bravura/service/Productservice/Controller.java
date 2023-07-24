package com.bravura.service.Productservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Autowired
	JdbcTemplate jdbctemplate;

	@GetMapping("/products")
	public List<Product> getProducts() {

		ProductDAO pd = new ProductDAO(jdbctemplate);

		List<Product> list = pd.getAllrecord();

		System.out.println(jdbctemplate);
		
		return list;

	}
	
	@PostMapping("/products")
	public String insertProducts(@RequestBody Product p) {
		
		ProductDAO pd = new ProductDAO(jdbctemplate);
		int i = pd.insertRecord(p);
		return "Row updated: "+i;
		
	}
	
	@GetMapping("/products/{id}")
	public Product getById(@PathVariable int id) {
		
		ProductDAO pd1 = new ProductDAO(jdbctemplate);
		Product p = pd1.getById(id);
		return p;
		
	}
	
	@DeleteMapping("/products/{id}")
	public Product deleteById(@PathVariable int id) {
		
		ProductDAO pd2 = new ProductDAO(jdbctemplate);
		
		Product res = pd2.getById(id);
		int j = pd2.deleteById(id);
		return res;
		
	}
	
	@PutMapping("/products/{id}")
	public String updateRecord(@RequestBody Product p,@PathVariable int id) {
		
		ProductDAO pd3 = new ProductDAO(jdbctemplate);
		
		return "Row Updated: "+pd3.updateRecord(p,id);
	}
	
}
