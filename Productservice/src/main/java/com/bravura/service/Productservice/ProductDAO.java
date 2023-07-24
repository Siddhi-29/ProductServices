package com.bravura.service.Productservice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ProductDAO {

	JdbcTemplate jdbctemplate;


	public ProductDAO(JdbcTemplate jdbctemplate) {

		this.jdbctemplate = jdbctemplate;
	}


	public List<Product> getAllrecord(){

		return jdbctemplate.query(" Select * from product ",new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product(rs.getInt("Id"),rs.getString("name"));

				return product;
			}
		});
	}
	
	public int insertRecord(Product p) {
		
		return jdbctemplate.update("insert into Product values (?,?)",p.getId(),p.getName());
		
	}


	public Product getById(int id) {
		
		return jdbctemplate.queryForObject(" select * from product where id ="+id,new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			
				Product product = new Product(rs.getInt("Id"),rs.getString("name"));

				return product;
			}
			
		});
	}
	
	public int deleteById(int id) {
		
		return jdbctemplate.update("delete from Product where id=?",id);
		
	}
	
	public int updateRecord(Product product, int id) {
		
		return jdbctemplate.update("update product set name = ? where id = ?",product.getName(),id);
		
	}
}
