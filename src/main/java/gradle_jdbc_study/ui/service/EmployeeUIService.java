package gradle_jdbc_study.ui.service;

import java.util.List;

import gradle_jdbc_study.dao.DepartmentDao;
import gradle_jdbc_study.dao.EmployeeDao;
import gradle_jdbc_study.dao.impl.DepartmentDaoImpl;
import gradle_jdbc_study.dao.impl.EmployeeDaoImpl;
import gradle_jdbc_study.dto.Department;

public class EmployeeUIService {
	private EmployeeDao empDao;
	private DepartmentDao deptDao;
	

	public EmployeeUIService() {
		empDao = EmployeeDaoImpl.getInstance();
		deptDao = DepartmentDaoImpl.getInstance();
	}

	public List<Department> showDeptList() {
		return deptDao.selectDepartmentByAll();
	}
	
	
}
