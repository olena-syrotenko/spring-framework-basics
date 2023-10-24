package asd.syrotenko.dao;

import asd.syrotenko.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProductDaoTest {
	private ClassPathXmlApplicationContext ctx;
	private ProductDao productDao;

	@BeforeEach
	public void init() {
		ctx = new ClassPathXmlApplicationContext("beanConfig.xml");
		productDao = (ProductDao) ctx.getBean("productDao");
	}

	@Test
	public void testCreate() {
		Product product = new Product();
		product.setId(100);
		product.setName("product1");
		product.setDescription("test");
		product.setPrice(220.);
		assertEquals(100, productDao.create(product));
		assertNotNull(productDao.find(100));
		productDao.delete(product);
	}

	@Test
	public void testUpdate() {
		Product product = new Product();
		product.setId(200);
		product.setName("product2");
		product.setDescription("test");
		product.setPrice(220.);
		productDao.create(product);

		product.setName("updated product");
		productDao.update(product);

		Product updatedProduct = productDao.find(200);
		assertEquals("updated product", updatedProduct.getName());

		productDao.delete(updatedProduct);
	}

	@Test
	public void testDelete() {
		Integer id = 300;
		Product product = new Product();
		product.setId(id);
		product.setName("product");
		product.setDescription("test");
		product.setPrice(220.);

		productDao.create(product);
		productDao.delete(product);
		assertNull(productDao.find(id));
	}

	@Test
	public void testFindAll() {
		Product product1 = new Product();
		product1.setId(400);
		product1.setName("product");
		product1.setDescription("test");
		product1.setPrice(220.);
		productDao.create(product1);

		Product product2 = new Product();
		product2.setId(500);
		product2.setName("product");
		product2.setDescription("test");
		product2.setPrice(220.);
		productDao.create(product2);

		assertEquals(2, productDao.findAll().size());

		productDao.delete(product1);
		productDao.delete(product2);
	}

}