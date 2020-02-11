package gradle_jdbc_study.ui.service;

import java.util.List;

import gradle_jdbc_study.dao.DepartmentDao;
import gradle_jdbc_study.dao.EmployeeDao;
import gradle_jdbc_study.dao.impl.DepartmentDaoImpl;
import gradle_jdbc_study.dao.impl.EmployeeDaoImpl;
import gradle_jdbc_study.dto.Department;
import gradle_jdbc_study.dto.Employee;

public class DepartmentUiService {
	private DepartmentDao deptDao = DepartmentDaoImpl.getInstance();
	private EmployeeDao empDao = EmployeeDaoImpl.getInstance();
	
	public List<Department> showDepartmentList(){
		return deptDao.selectDepartmentByAll();
	}
	
	public void removeDepartment(Department delDept) {
		deptDao.deleteDepartment(delDept);
	}
	
	public List<Employee> showEmployeeGroupByDno(Department dept){
		return empDao.selectEmployeeGroupByDno(dept);
	}
	
	public void addDepartment(Department newDept) {
		deptDao.insertDepartment(newDept);
	}
	
	public void modifyDepartment(Department newDept) {
		deptDao.updateDepartment(newDept);
	}
}
