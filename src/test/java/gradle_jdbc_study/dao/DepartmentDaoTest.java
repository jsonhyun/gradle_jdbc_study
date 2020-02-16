package gradle_jdbc_study.dao;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import gradle_jdbc_study.dao.impl.DepartmentDaoImpl;
import gradle_jdbc_study.dto.Department;
import gradle_jdbc_study.util.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	static DepartmentDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		dao = DepartmentDaoImpl.getInstance();

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		dao = null;

	}

	@Before
	public void setUp() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");

	}

	@After
	public void tearDown() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");

	}

	@Test
	public void test01SelectDepartmentByNo() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Department department = dao.selectDepartmentByNo(new Department(1));
		Assert.assertNotNull(department);
		LogUtil.prnLog(department);
	}

	@Test
	public void test02SelectDepartmentByAll() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		List<Department> lists = dao.selectDepartmentByAll();
		Assert.assertNotNull(lists);
		
		for(Department d:lists) {
			LogUtil.prnLog(d);
		}
	}

	@Test
	public void test03InsertDepartment() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Department department = new Department(5, "마케팅", 12);
		int res = dao.insertDepartment(department);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test04UpdateDepartment() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Department department = new Department(5,"모바일개발",60);
		int res = dao.updateDepartment(department);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test05DeleteDepartment() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Department department = new Department(5);
		int res = dao.deleteDepartment(department);
		Assert.assertEquals(1, res);
	}

}
