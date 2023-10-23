package asd.syrotenko.dao;

import asd.syrotenko.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeDaoTest {

	private ClassPathXmlApplicationContext ctx;
	private EmployeeDao employeeDao;

	@BeforeEach
	public void init() {
		ctx = new ClassPathXmlApplicationContext("beanConfig.xml");
		employeeDao = (EmployeeDao) ctx.getBean("employeeDao");
	}

	@Test
	public void testCreate() {
		Employee employee = new Employee();
		employee.setFirstName("Tom");
		employee.setLastName("CreateTest");
		assertEquals(1, employeeDao.create(employee));
	}

	@Test
	public void testUpdate() {
		Employee employee = new Employee();
		employee.setFirstName("TomUpdate");
		employee.setLastName("UpdateTest");
		employeeDao.create(employee);

		employee.setId(employeeDao.getLastInsertId());
		employee.setLastName("UpdatedName");
		assertEquals(1, employeeDao.update(employee));
	}

	@Test
	public void testDelete() {
		Employee employee = new Employee();
		employee.setFirstName("Tom");
		employee.setLastName("DeleteTest");
		employeeDao.create(employee);

		Integer id = employeeDao.getLastInsertId();
		assertEquals(1, employeeDao.delete(id));
	}

	@Test
	public void testReadById() {
		String uuid = UUID.randomUUID().toString();
		Employee employee = new Employee();
		employee.setFirstName("Tom" + uuid);
		employee.setLastName("SingleReadTest");
		employeeDao.create(employee);

		Integer id = employeeDao.getLastInsertId();
		Employee createdEmployee = employeeDao.readById(id);
		assertEquals("Tom" + uuid, createdEmployee.getFirstName());
		assertEquals("SingleReadTest", createdEmployee.getLastName());
	}

	@Test
	public void testReadByFullName() {
		Employee employee = new Employee();
		employee.setFirstName("Tom");
		employee.setLastName("MultipleReadTest");
		employeeDao.create(employee);
		employeeDao.create(employee);

		List<Employee> employees = employeeDao.readByFullName("Tom", "MultipleReadTest");
		assertEquals(2, employees.size());

		employeeDao.delete(employeeDao.getLastInsertId());
		employeeDao.delete(employeeDao.getLastInsertId());
	}

}
