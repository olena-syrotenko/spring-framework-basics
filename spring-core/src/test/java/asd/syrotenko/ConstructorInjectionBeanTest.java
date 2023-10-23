package asd.syrotenko;

import asd.syrotenko.constructorinjection.Address;
import asd.syrotenko.constructorinjection.AmbiguityType;
import asd.syrotenko.constructorinjection.AmbiguityWithString;
import asd.syrotenko.constructorinjection.Employee;
import asd.syrotenko.constructorinjection.NumDifference;
import asd.syrotenko.constructorinjection.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ConstructorInjectionBeanTest {

	private ClassPathXmlApplicationContext ctx;

	@BeforeEach
	public void init() {
		ctx = new ClassPathXmlApplicationContext("constructorInjectionConfig.xml");
	}

	@Test
	public void testEmployeeBean() {
		Employee employee = (Employee) ctx.getBean("employee");
		assertEquals(1, employee.getId());
		assertEquals(2, employee.getEquipment().size());

		Address address = employee.getAddress();
		assertEquals(10, address.getHno());
		assertEquals("Default Street", address.getStreet());
		assertEquals("Kharkiv", address.getCity());
	}

	@Test
	public void testUserServiceBean() {
		UserService userService = (UserService) ctx.getBean("userService");
		assertEquals("username-10", userService.getUserById(10));
	}

	@Test
	public void testAmbiguityTypeDefaultBean() {
		AmbiguityType ambiguityType = (AmbiguityType) ctx.getBean("typeDefault");
		assertEquals(10, ambiguityType.getIntField());
		assertEquals(0, ambiguityType.getDoubleField());
	}

	@Test
	public void testAmbiguityTypeWithDoubleBean() {
		AmbiguityType ambiguityType = (AmbiguityType) ctx.getBean("doubleType");
		assertEquals(0, ambiguityType.getIntField());
		assertEquals(10, ambiguityType.getDoubleField());
	}

	@Test
	public void testAmbiguityWithStringDefaultBean() {
		AmbiguityWithString ambiguityWithString = (AmbiguityWithString) ctx.getBean("withStringDefault");
		assertNull(ambiguityWithString.getIntField());
		assertNull(ambiguityWithString.getDoubleField());
		assertEquals("10", ambiguityWithString.getStringField());
	}

	@Test
	public void testAmbiguityWithStringWithArgumentNameBean() {
		AmbiguityWithString ambiguityWithString = (AmbiguityWithString) ctx.getBean("withStringSpecifyName");
		assertEquals(10, ambiguityWithString.getIntField());
		assertNull(ambiguityWithString.getDoubleField());
		assertNull(ambiguityWithString.getStringField());
	}

	@Test
	public void testNumDifferenceDefaultOrderBean() {
		NumDifference difference = (NumDifference) ctx.getBean("numDifferenceDefaultOrder");
		assertEquals(5, difference.getDifference());
	}

	@Test
	public void testNumDifferenceCustomOrderBean() {
		NumDifference difference = (NumDifference) ctx.getBean("numDifferenceReverseOrder");
		assertEquals(-5, difference.getDifference());
	}
}
