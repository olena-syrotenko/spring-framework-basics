package asd.syrotenko.mapper;

import asd.syrotenko.entity.Employee;
import org.springframework.jdbc.core.RowMapper;

public class MapperFactory {
	public static RowMapper<Employee> getEmployeeMapper() {
		return new EmployeeRowMapper();
	}
}
