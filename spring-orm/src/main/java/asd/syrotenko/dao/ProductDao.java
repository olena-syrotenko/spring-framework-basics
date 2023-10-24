package asd.syrotenko.dao;

import asd.syrotenko.entity.Product;

import java.util.List;

public interface ProductDao {
	Integer create(Product product);

	void update(Product product);
	void delete(Product product);

	Product find(Integer id);
	List<Product> findAll();
}
