package gradle_jdbc_study.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gradle_jdbc_study.dao.EmployeeDao;
import gradle_jdbc_study.ds.MySqlDataSource;
import gradle_jdbc_study.dto.Department;
import gradle_jdbc_study.dto.Employee;
import gradle_jdbc_study.dto.Title;
import gradle_jdbc_study.util.LogUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	private static final EmployeeDaoImpl Instance = new EmployeeDaoImpl();
	
	private EmployeeDaoImpl() {}

	public static EmployeeDaoImpl getInstance() {
		return Instance;
	}

	@Override
	public Employee selectEmployeeByNo(Employee emp) {
		String sql = "select emp_no, emp_name, title, manager, salary, dept, hire_date, pic from employee where emp_no = ?";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, emp.getEmpNo());
			LogUtil.prnLog(pstmt);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getEmployee(rs, true);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	private Employee getEmployee(ResultSet rs, boolean isPic) throws SQLException {
		int empNo = rs.getInt("emp_no");
		String empName = rs.getString("emp_name");
		Title title = new Title(rs.getInt("title"));
		Employee manager = new Employee(rs.getInt("manager"));
		int salary = rs.getInt("salary");
		Department dept = new Department(rs.getInt("dept"));
		Date hireDate = rs.getTimestamp("hire_date");
		Employee emp = new Employee(empNo, empName, title, manager, salary, dept, hireDate);
		if(isPic) {
			byte[] pic = rs.getBytes("pic");
			emp.setPic(pic);
		}
		LogUtil.prnLog(emp);
		return emp;
	}

	@Override
	public List<Employee> selectEmployeeByAll() {
		String sql = "select emp_no, emp_name, title, manager, salary, dept, hire_date from employee";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			List<Employee> list = new ArrayList<Employee>();
			while(rs.next()) {
				list.add(getEmployee(rs, false));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertEmployee(Employee emp) {
		String sql = "insert into employee(emp_no, emp_name, title, manager, salary, dept, passwd, hire_date, pic) values(?, ?, ?, ?, ?, ?, password(?), ?,  ?)";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setInt(3, emp.getTitle().getTitleNo());
			pstmt.setInt(4, emp.getManager().getEmpNo());
			pstmt.setInt(5, emp.getSalary());
			pstmt.setInt(6, emp.getDept().getDeptNo());
			pstmt.setString(7, emp.getPasswd());
//			util.Date->sql.Date로 변환
			pstmt.setTimestamp(8, new Timestamp(emp.getHireDate().getTime()));
			LogUtil.prnLog(pstmt);
			if (emp.getPic()!=null) {
				pstmt.setBytes(9, emp.getPic());
			}
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateEmployee(Employee emp) {
		StringBuilder sql = new StringBuilder("update employee set ");
		if (emp.getEmpName()!=null) sql.append("emp_name=?, ");
		if (emp.getTitle()!=null) sql.append("title=?, ");
		if (emp.getManager()!=null)sql.append("manager=?, ");
		if (emp.getSalary()!=0) sql.append("salary=?, ");
		if (emp.getDept()!=null) sql.append("dept=?, ");
		if (emp.getPasswd()!=null) sql.append("passwd=password(?), ");
		if (emp.getHireDate()!=null) sql.append("hire_date=?, ");
		if (emp.getPic()!=null) sql.append("pic=?, ");
		sql.replace(sql.lastIndexOf(","), sql.length(), " ");
		sql.append("where emp_no=?");
		
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql.toString())){
			int argCnt = 1;
			if (emp.getEmpName()!=null) pstmt.setString(argCnt++, emp.getEmpName());
			if (emp.getTitle()!=null) pstmt.setInt(argCnt++, emp.getTitle().getTitleNo());
			if (emp.getManager()!=null)pstmt.setInt(argCnt++, emp.getManager().getEmpNo());
			if (emp.getSalary()!=0) pstmt.setInt(argCnt++, emp.getSalary());
			if (emp.getDept()!=null)pstmt.setInt(argCnt++, emp.getDept().getDeptNo());
			if (emp.getPasswd()!=null)pstmt.setString(argCnt++, emp.getPasswd());
			if (emp.getHireDate()!=null) pstmt.setTimestamp(argCnt++, new Timestamp(emp.getHireDate().getTime()));
			if (emp.getPic()!=null) pstmt.setBytes(argCnt++, emp.getPic());
			pstmt.setInt(argCnt++, emp.getEmpNo());
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteEmployee(Employee emp) {
		String sql = "delete from employee where emp_no=?";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, emp.getEmpNo());
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Employee loginEmployee(Employee emp) {
		String sql = "select emp_no, emp_name, title, manager, salary, dept, hire_date from employee where emp_no=? and passwd = password(?)";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, emp.getEmpNo());

			pstmt.setString(2, emp.getPasswd());
			LogUtil.prnLog(pstmt);
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					return getEmployee(rs, false);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> selectEmployeeGroupByDno(Department dept){
		String sql = "select e.emp_no, e.emp_name , e.title , t.title_name, m.emp_name as manager_name , m.emp_no as manager_no , e.salary , e.dept , d.dept_name " + 
			     "  from employee e left join employee m on e.manager = m.emp_no join department d on e.dept = d.dept_no join title t on e.title = t.title_no " + 
			     " where e.dept =?";
		List<Employee> list = new ArrayList<>();
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, dept.getDeptNo());
			LogUtil.prnLog(pstmt);
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					list.add(getEmployeeFull(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private Employee getEmployeeFull(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("emp_no");
		String empName = rs.getString("emp_name");
		Title title = new Title(rs.getInt("title"), rs.getString("title_name"));
		Employee manager = new Employee(rs.getInt("manager_no"));
		manager.setEmpName(rs.getString("manager_name"));
		int salary = rs.getInt("salary");
		Department dept = new Department();
		dept.setDeptNo(rs.getInt("dept"));
		dept.setDeptName(rs.getString("dept_name"));
		return new Employee(empNo, empName, title, manager, salary, dept);
	}

}
