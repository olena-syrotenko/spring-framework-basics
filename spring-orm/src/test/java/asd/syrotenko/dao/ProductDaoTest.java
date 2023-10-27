package asd.syrotenko.dao;

import asd.syrotenko.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
		product.setName("productCreate");
		product.setDescription("test");
		product.setPrice(220.);

		Integer id = productDao.create(product);
		assertNotNull(productDao.find(id));
	}

	@Test
	public void testUpdate() {
		Product product = new Product();
		product.setName("product2");
		product.setDescription("test");
		product.setPrice(220.);
		Integer id = productDao.create(product);

		product.setId(id);
		product.setName("updated product" + id);
		productDao.update(product);

		Product updatedProduct = productDao.find(id);
		assertEquals("updated product" + id, updatedProduct.getName());
	}

	@Test
	public void testDelete() {
		Product product = new Product();
		product.setName("product");
		product.setDescription("test");
		product.setPrice(220.);

		Integer id = productDao.create(product);
		product.setId(id);
		productDao.delete(product);
		assertNull(productDao.find(id));
	}

	@Test
	public void testFindAll() {
		Product product1 = new Product();
		product1.setName("product");
		product1.setDescription("test");
		product1.setPrice(220.);
		productDao.create(product1);

		Product product2 = new Product();
		product2.setName("product");
		product2.setDescription("test");
		product2.setPrice(220.);
		productDao.create(product2);

		assertTrue(productDao.findAll().size() >= 2);
	}

}