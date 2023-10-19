package asd.syrotenko;

import asd.syrotenko.autowiring.assignment.Customer;
import asd.syrotenko.autowiring.assignment.Reservation;
import asd.syrotenko.autowiring.example.Address;
import asd.syrotenko.autowiring.example.Employee;
import asd.syrotenko.autowiring.example.Scores;
import asd.syrotenko.autowiring.example.Student;
import asd.syrotenko.autowiring.example.annotations.AutowiredByConstructor;
import asd.syrotenko.autowiring.example.annotations.AutowiredByField;
import asd.syrotenko.autowiring.example.annotations.AutowiredBySetter;
import asd.syrotenko.autowiring.example.annotations.Scholar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AutowiringBeanTest {

	private ClassPathXmlApplicationContext ctx;

	@BeforeEach
	public void init() {
		ctx = new ClassPathXmlApplicationContext("autowiringConfig.xml");
	}

	@Test
	public void testEmployeeBeanAutowiredByType() {
		Employee employee = (Employee) ctx.getBean("employeeByType");
		assertEquals(0, employee.getEquipment().size());
		Address address = employee.getAddress();
		assertEquals(10, address.getHno());
		assertEquals("Default Street", address.getStreet());
		assertEquals("Kharkiv", address.getCity());
	}

	@Test
	public void testEmployeeBeanAutowiredByConstructor() {
		Employee employee = (Employee) ctx.getBean("employeeByConstructor");
		assertEquals(1, employee.getEquipment().size());
		Address address = employee.getAddress();
		assertEquals(10, address.getHno());
		assertEquals("Default Street", address.getStreet());
		assertEquals("Kharkiv", address.getCity());
	}

	@Test
	public void testStudentBeanAutowiredByName() {
		Student student = (Student) ctx.getBean("student");
		Scores scores = student.getScores();
		assertEquals(100.00, scores.getMaths());
		assertEquals(95.00, scores.getPhysics());
		assertEquals(90.00, scores.getChemistry());
	}

	@Test
	public void testAutowiredByFieldBean() {
		AutowiredByField autowiredByField = (AutowiredByField) ctx.getBean("autowiredByField");
		assertNotNull(autowiredByField.getMessageService());
		assertEquals("", autowiredByField.getInitMessage());
	}

	@Test
	public void testAutowiredBySetterBean() {
		AutowiredBySetter autowiredBySetter = (AutowiredBySetter) ctx.getBean("autowiredBySetter");
		assertNotNull(autowiredBySetter.getMessageService());
		assertEquals("Autowired by setter", autowiredBySetter.getInitMessage());
	}

	@Test
	public void testAutowiredByConstructorBean() {
		AutowiredByConstructor autowiredByConstructor = (AutowiredByConstructor) ctx.getBean("autowiredByConstructor");
		assertNotNull(autowiredByConstructor.getMessageService());
		assertEquals("Autowired by constructor", autowiredByConstructor.getInitMessage());
	}

	@Test
	public void testScholarBean() {
		Scholar scholar = (Scholar) ctx.getBean("scholar");
		Scores scores = scholar.getScores();
		assertEquals(70.00, scores.getMaths());
		assertEquals(65.00, scores.getPhysics());
		assertEquals(60.00, scores.getChemistry());
	}

	@Test
	public void testCustomerBean() {
		Customer customer = (Customer) ctx.getBean("customer");
		assertEquals("Tom", customer.getName());

		Reservation reservation = customer.getReservation();
		assertEquals(1, reservation.getId());
		assertEquals(LocalDate.parse("2023-10-10"), reservation.getFromDate());
		assertEquals(LocalDate.parse("2023-10-15"), reservation.getToDate());
	}

}
