package gradle_jdbc_study.ui.service;

import java.util.List;

import gradle_jdbc_study.dao.EmployeeDao;
import gradle_jdbc_study.dao.TitleDao;
import gradle_jdbc_study.dao.impl.EmployeeDaoImpl;
import gradle_jdbc_study.dao.impl.TitleDaoImpl;
import gradle_jdbc_study.dto.Employee;
import gradle_jdbc_study.dto.Title;

public class TitleUiService {
	private TitleDao titleDao = TitleDaoImpl.getInstance();
	private EmployeeDao empDao = EmployeeDaoImpl.getInstance();
	
	public List<Title> showTitleList(){
		return titleDao.selectTitleByAll();
	}
	
	public void removeTitle(Title delTitle) {
		titleDao.deleteTitle(delTitle);
	}
	
	public List<Employee> showEmployeeGroupByTno(Title title){
		return empDao.selectEmployeeGroupByTno(title);
	}
	
	public void addTitle(Title newTitle) {
		titleDao.insertTitle(newTitle);
	}
	
	public void modifyTitle(Title newTitle) {
		titleDao.updateTitle(newTitle);
	}
}
