package asd.syrotenko.controllers;

import asd.syrotenko.dao.ProductDao;
import asd.syrotenko.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductDao productDao;

	@GetMapping
	public List<Product> getProducts() {
		return productDao.findAll();
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Integer id) {
		return productDao.findById(id).orElse(null);
	}

	@PostMapping
	public Product create(@RequestBody Product product) {
		productDao.save(product);
		return product;
	}

	@PutMapping
	public Product update(@RequestBody Product product) {
		productDao.save(product);
		return product;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		productDao.deleteById(id);
	}
}
