package gradle_jdbc_study.dao.impl;

import java.util.List;

import gradle_jdbc_study.dao.EmployeeDao;
import gradle_jdbc_study.dto.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	private static final EmployeeDaoImpl Instance = new EmployeeDaoImpl();
	
	private EmployeeDaoImpl() {}

	public static EmployeeDaoImpl getInstance() {
		return Instance;
	}

	@Override
	public Employee selectEmployeeByNo(Employee emp) {
		String sql = "select emp_no, emp_name, title, manager, salary, dept, hire_date, pic from employee where emp_no = ? and passwd = password('?')";
		return null;
	}

	@Override
	public List<Employee> selectEmployeeByAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Employee loginEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

}
