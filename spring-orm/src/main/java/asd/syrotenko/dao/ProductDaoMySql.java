package asd.syrotenko.dao;

import asd.syrotenko.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("productDao")
public class ProductDaoMySql implements ProductDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public Integer create(Product product) {
		if (product == null) {
			return 0;
		}
		return (Integer) hibernateTemplate.save(product);
	}

	@Override
	@Transactional
	public void update(Product product) {
		if (product == null) {
			return;
		}
		hibernateTemplate.update(product);
	}

	@Override
	@Transactional
	public void delete(Product product) {
		if (product == null) {
			return;
		}
		hibernateTemplate.delete(product);
	}

	@Override
	@Transactional
	public Product find(Integer id) {
		if (id == null) {
			return null;
		}
		return hibernateTemplate.get(Product.class, id);
	}

	@Override
	@Transactional
	public List<Product> findAll() {
		return hibernateTemplate.loadAll(Product.class);
	}

}
