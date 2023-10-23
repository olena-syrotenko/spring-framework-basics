package asd.syrotenko.dao;

import asd.syrotenko.entity.Employee;
import asd.syrotenko.mapper.MapperFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {

	private static final String INSERT_EMPLOYEE = "INSERT INTO employee(first_name, last_name) VALUES(?,?)";
	private static final String UPDATE_EMPLOYEE = "UPDATE employee SET first_name = ?, last_name = ? WHERE id = ?";
	private static final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE id = ?";
	private static final String SELECT_BY_ID = "SELECT * FROM employee WHERE id = ?";
	private static final String SELECT_BY_FULL_NAME = "SELECT * FROM employee WHERE first_name = ? AND last_name = ?";
	private static final String SELECT_LAST_INSERT_ID = "SELECT id FROM employee ORDER BY id DESC LIMIT 1";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Integer create(Employee employee) {
		if (employee == null) {
			return 0;
		}
		return jdbcTemplate.update(INSERT_EMPLOYEE, employee.getFirstName(), employee.getLastName());
	}

	@Override
	public Integer update(Employee employee) {
		if (employee == null) {
			return 0;
		}
		return jdbcTemplate.update(UPDATE_EMPLOYEE, employee.getFirstName(), employee.getLastName(), employee.getId());
	}

	@Override
	public Integer delete(Integer employeeId) {
		if (employeeId == null) {
			return 0;
		}
		return jdbcTemplate.update(DELETE_EMPLOYEE, employeeId);
	}

	@Override
	public Employee readById(Integer id) {
		if (id == null) {
			return null;
		}
		try {
			return jdbcTemplate.queryForObject(SELECT_BY_ID, MapperFactory.getEmployeeMapper(), id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Employee> readByFullName(String firstName, String lastName) {
		if (StringUtils.isAnyBlank(firstName, lastName)) {
			return null;
		}
		return jdbcTemplate.query(SELECT_BY_FULL_NAME, MapperFactory.getEmployeeMapper(), firstName, lastName);
	}

	@Override
	public Integer getLastInsertId() {
		try {
			return jdbcTemplate.queryForObject(SELECT_LAST_INSERT_ID, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
