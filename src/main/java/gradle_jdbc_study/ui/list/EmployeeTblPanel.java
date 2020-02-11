package gradle_jdbc_study.ui.list;

import javax.swing.SwingConstants;

import gradle_jdbc_study.dto.Department;
import gradle_jdbc_study.dto.Employee;
import gradle_jdbc_study.dto.Title;

@SuppressWarnings("serial")
public class EmployeeTblPanel extends AbstractTblPanel<Employee> {

	@Override
	protected void setTblWidthAlign() {
		//  empno, empname, title, manager, salary, dno
		tableSetWidth(100, 100, 80, 150, 100, 100);
		tableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 5);	
		tableCellAlign(SwingConstants.RIGHT, 4);	
	}

	@Override
	protected String[] getColNames() {
		return new String[] {"사원번호", "사원명", "직책", "직속상사", "급여", "부서"};
	}

	@Override
	protected Object[] toArray(Employee item) {
		String manager;
		if (item.getManager().getEmpName()==null) {
			manager = "";
		}else {
			manager = String.format("%s(%d)", item.getManager().getEmpName(), item.getManager().getEmpNo());
		}
		return new Object[] {
			item.getEmpNo(),
			item.getEmpName(),
			String.format("%s(%d)", item.getTitle().getTitleName(), item.getTitle().getTitleNo()),
			manager, //직속상사명(사원번호)
			String.format("%,d", item.getSalary()),			//천단위구분기호
			String.format("%s(%d)", item.getDept().getDeptName(), item.getDept().getDeptNo())		//부서명(부서번호)
		};
	}

	@Override
	public void updateRow(Employee item, int updateIdx) {
		model.setValueAt(item.getEmpNo(), updateIdx, 0);//사원번호
		model.setValueAt(item.getEmpName(), updateIdx, 1);//사원명
		model.setValueAt(item.getTitle(), updateIdx, 2);//직책
		model.setValueAt(item.getManager().getEmpNo(), updateIdx, 3);//직속상사번호
		model.setValueAt(item.getSalary(), updateIdx, 4);//급여
		model.setValueAt(item.getDept().getDeptNo(), updateIdx, 5);//소속부서번호		
	}

	@Override
	public Employee getSelectedItem() {
		int selectedIdx = getSelectedRowIdx();
		int empNo = (int) model.getValueAt(selectedIdx, 0);
		String empName = (String) model.getValueAt(selectedIdx, 1);
		Title title = new Title((int) model.getValueAt(selectedIdx, 2));
		Employee manager = new Employee((int)model.getValueAt(selectedIdx, 3));
		int salary = (int) model.getValueAt(selectedIdx, 4);
		Department dept = new Department();
		dept.setDeptNo((int) model.getValueAt(selectedIdx, 5));
		return new Employee(empNo, empName, title, manager, salary, dept);
	}

}
