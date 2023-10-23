package asd.syrotenko.dao;

import asd.syrotenko.entity.Employee;

import java.util.List;

public interface EmployeeDao {
	Integer create(Employee employee);
	Integer update(Employee employee);
	Integer delete(Integer employeeId);
	Employee readById(Integer id);
	List<Employee> readByFullName(String firstName, String lastName);
	Integer getLastInsertId();
}
