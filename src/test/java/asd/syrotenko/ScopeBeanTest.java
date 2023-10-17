package asd.syrotenko;

import asd.syrotenko.scope.assignment.University;
import asd.syrotenko.scope.example.EmployeePrototype;
import asd.syrotenko.scope.example.EmployeeSingleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ScopeBeanTest {
	private ClassPathXmlApplicationContext ctx;

	@BeforeEach
	public void init() {
		ctx = new ClassPathXmlApplicationContext("scopeBeanConfig.xml");
	}

	@Test
	public void testSingletonBeanScope() {
		EmployeeSingleton employee = (EmployeeSingleton) ctx.getBean("employeeSingleton");
		EmployeeSingleton anotherEmployee = (EmployeeSingleton) ctx.getBean("employeeSingleton");
		assertEquals(employee.hashCode(), anotherEmployee.hashCode());
	}

	@Test
	public void testPrototypeBeanScope() {
		EmployeePrototype employee = (EmployeePrototype) ctx.getBean("employeePrototype");
		EmployeePrototype anotherEmployee = (EmployeePrototype) ctx.getBean("employeePrototype");
		assertNotEquals(employee.hashCode(), anotherEmployee.hashCode());
	}

	@Test
	public void testUniversityBean() {
		University kyivUniversity = (University) ctx.getBean("university");
		kyivUniversity.setLocation("Kyiv");
		assertNull(kyivUniversity.getId());
		assertEquals("Kyiv", kyivUniversity.getLocation());
		assertEquals("Default University", kyivUniversity.getName());

		University defaultUniversity = (University) ctx.getBean("university");
		assertNull(defaultUniversity.getId());
		assertNull(defaultUniversity.getLocation());
		assertEquals("Default University", defaultUniversity.getName());
	}
}
