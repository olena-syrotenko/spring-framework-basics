package asd.syrotenko;

import asd.syrotenko.setterinjection.entity.assignment.Item;
import asd.syrotenko.setterinjection.entity.assignment.ShoppingCart;
import asd.syrotenko.setterinjection.entity.example.CarDealer;
import asd.syrotenko.setterinjection.entity.example.Customer;
import asd.syrotenko.setterinjection.entity.example.Employee;
import asd.syrotenko.setterinjection.entity.example.Hospital;
import asd.syrotenko.setterinjection.entity.example.Languages;
import asd.syrotenko.setterinjection.entity.example.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SetterInjectionBeanConfigTest {
	private ClassPathXmlApplicationContext ctx;

	@BeforeEach
	public void init() {
		ctx = new ClassPathXmlApplicationContext("setterInjectionConfig.xml");
	}

	@Test
	public void testEmployeeBean() {
		Employee empBean = (Employee) ctx.getBean("emp");
		assertEquals(20, empBean.getId());
		assertEquals("Tom", empBean.getName());
		assertEquals(2000.00, empBean.getSalary());
	}

	@Test
	public void testHospitalBean() {
		Hospital hospitalBean = (Hospital) ctx.getBean("hospital");
		assertEquals("CityHospital", hospitalBean.getName());
		assertEquals(3, hospitalBean.getDepartments().size());
	}

	@Test
	public void testCarDealerBean() {
		CarDealer carDealerBean = (CarDealer) ctx.getBean("carDealer");
		assertEquals("City Dealer", carDealerBean.getName());
		assertEquals(2, carDealerBean.getModels().size());
	}

	@Test
	public void testCustomerBean() {
		Customer customerBean = (Customer) ctx.getBean("customer");
		assertEquals(5, customerBean.getId());
		assertEquals(4, customerBean.getProducts().size());
		assertEquals("Samsung", customerBean.getProducts().get(1));
	}

	@Test
	public void testLanguagesBean() {
		Languages languagesBean = (Languages) ctx.getBean("languages");
		assertEquals(3, languagesBean.getCountryAndLanguages().size());
		assertEquals("British English", languagesBean.getCountryAndLanguages().getProperty("UK"));
	}

	@Test
	public void testStudentBean() {
		Student studentBean = (Student) ctx.getBean("student");
		assertEquals(100.00, studentBean.getScores().getMaths());
		assertEquals(95.00, studentBean.getScores().getPhysics());
		assertEquals(80.00, studentBean.getScores().getChemistry());
	}

	@Test
	public void testShoppingCartBean() {
		ShoppingCart shoppingCartBean = (ShoppingCart) ctx.getBean("shoppingCart");
		List<Item> shopItems = shoppingCartBean.getItems();
		assertEquals(2, shoppingCartBean.getItems().size());

		Item dressItem = shopItems.stream().filter(item -> "Dress".equalsIgnoreCase(item.getName())).findFirst().orElse(null);
		assertNotNull(dressItem);
		assertEquals(1, dressItem.getId());
		assertEquals(100.50, dressItem.getPrice());
		assertEquals(3, dressItem.getQuantity());

		Item bagItem = shopItems.stream().filter(item -> "Handbag".equalsIgnoreCase(item.getName())).findFirst().orElse(null);
		assertNotNull(bagItem);
		assertEquals(2, bagItem.getId());
		assertEquals(20.59, bagItem.getPrice());
		assertEquals(1, bagItem.getQuantity());
	}
}
