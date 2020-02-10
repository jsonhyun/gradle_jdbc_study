package gradle_jdbc_study.dao;

import java.util.List;

import gradle_jdbc_study.dto.Department;

public interface DepartmentDao {
	Department selectDepartmentByNo(Department department);
	List<Department> selectDepartmentByAll();
	
	int insertDepartment(Department department);
	int updateDepartment(Department department);
	int deleteDepartment(Department department);
}
