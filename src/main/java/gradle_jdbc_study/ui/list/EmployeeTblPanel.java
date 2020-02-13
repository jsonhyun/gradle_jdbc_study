package gradle_jdbc_study.ui.list;

import javax.swing.SwingConstants;

import gradle_jdbc_study.dto.Employee;

@SuppressWarnings("serial")
public class EmployeeTblPanel extends AbstractTblPanel<Employee> {

	@Override
	protected void setTblWidthAlign() {
		//  empno, empname, title, manager, salary, dno
		tableSetWidth(100, 100, 80, 150, 100, 100, 110, 50);
		tableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 5, 6, 7);	
		tableCellAlign(SwingConstants.RIGHT, 4);	
	}

	@Override
	protected String[] getColNames() {
		return new String[] {"사원번호", "사원명", "직책", "직속상사", "급여", "부서", "입사일", "증명사진"};
	}

	@Override
	protected Object[] toArray(Employee item) {
		String manager;
		if (item.getManager().getEmpNo()==0) {
			manager = "";
		}else {
			manager = String.format("%s(%d)", item.getManager().getEmpName(), item.getManager().getEmpNo());
		}
		
		String picExist;
		if(item.getPic() == null) {
			picExist = "없음";
		}else {
			picExist = "있음";
		}
		
		return new Object[] {
			item.getEmpNo(),
			item.getEmpName(),
			String.format("%s(%d)", item.getTitle().getTitleName(), item.getTitle().getTitleNo()),
			manager, //직속상사명(사원번호)
			String.format("%,d", item.getSalary()),			//천단위구분기호
			String.format("%s(%d)", item.getDept().getDeptName(), item.getDept().getDeptNo()),		//부서명(부서번호)
			String.format("%tF", item.getHireDate()),	//입사일
			picExist
		};
	}

	@Override
	public void updateRow(Employee item, int updateIdx) {
		model.setValueAt(item.getEmpNo(), updateIdx, 0);//사원번호
		model.setValueAt(item.getEmpName(), updateIdx, 1);//사원명
		model.setValueAt(item.getTitle(), updateIdx, 2);//직책
		String manager;
		if (item.getManager().getEmpNo()==0) {
			manager = "";
		}else {
			manager = String.format("%s(%d)", item.getManager().getEmpName(), item.getManager().getEmpNo());
		}
		model.setValueAt(manager, updateIdx, 3);//직속상사번호
		model.setValueAt(String.format("%,d", item.getSalary()), updateIdx, 4);//급여
		model.setValueAt(item.getDept(), updateIdx, 5);//소속부서번호
		model.setValueAt(String.format("%tF", item.getHireDate()), updateIdx, 6);
		String picExist = item.getPic()==null?"없음":"있음";
		model.setValueAt(picExist, updateIdx, 7);
	}


}
