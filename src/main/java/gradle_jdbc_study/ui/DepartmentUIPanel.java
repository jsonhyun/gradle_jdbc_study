package gradle_jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import gradle_jdbc_study.dto.Department;
import gradle_jdbc_study.dto.Employee;
import gradle_jdbc_study.ui.content.DepartmentPanel;
import gradle_jdbc_study.ui.exception.InvalidCheckException;
import gradle_jdbc_study.ui.list.DepartmentTblPanel;
import gradle_jdbc_study.ui.service.DepartmentUiService;

@SuppressWarnings("serial")
public class DepartmentUIPanel extends JPanel implements ActionListener {
	private DepartmentUiService service;
	private DepartmentTblPanel pDeptList;
	private DepartmentPanel pDepartment;
	private DlgEmployee dialog;
	
	private JButton btnAdd;
	private JButton btnCancel;
	
	public DepartmentUIPanel() {
		service = new DepartmentUiService();
		dialog = new DlgEmployee();
		initialize();
	}
	
	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pContent = new JPanel();
		add(pContent);
		pContent.setLayout(new BorderLayout(0, 0));
		
		pDepartment = new DepartmentPanel();
		pContent.add(pDepartment, BorderLayout.CENTER);
		
		JPanel pBtns = new JPanel();
		add(pBtns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);
		
		JPanel pList = new JPanel();
		add(pList);
		pList.setLayout(new BorderLayout(0, 0));
		
		pDeptList = new DepartmentTblPanel();
		pDeptList.loadData(service.showDepartmentList());
		pDeptList.setPopupMenu(createPopupMenu());
		pList.add(pDeptList, BorderLayout.CENTER);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(myPopMenuListener);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(myPopMenuListener);
		popMenu.add(deleteItem);
		
		JMenuItem showEmployee = new JMenuItem("소속 사원");
		showEmployee.addActionListener(myPopMenuListener);
		popMenu.add(showEmployee);
		
		return popMenu;
	}
	
	ActionListener myPopMenuListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("수정")) {
				Department upDept = pDeptList.getSelectedItem();
				pDepartment.setItem(upDept);
				btnAdd.setText("수정");
			}
			if (e.getActionCommand().equals("삭제")) {
				Department delDept = pDeptList.getSelectedItem();
				service.removeDepartment(delDept);
				pDeptList.removeRow();
				JOptionPane.showMessageDialog(null, "삭제되었습니다.");
			}
			if (e.getActionCommand().contentEquals("소속 사원")) {
				Department selectedDept = pDeptList.getSelectedItem(); //선택한 부서정보
				List<Employee> list = service.showEmployeeGroupByDno(selectedDept);
				dialog.setEmpList(list);
				dialog.setTitle(selectedDept.getDeptName() + " 부서");
	
				dialog.setVisible(true);				
			}
		}
	};


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			if (e.getActionCommand().contentEquals("추가")) {
				btnAddActionPerformed(e);
			}else {
				btnUpdateActionPerformed(e);
			}
		}
		if (e.getSource() == btnCancel) {
			btnCancelActionPerformed(e);
		}
	}
	private void btnUpdateActionPerformed(ActionEvent e) {
		Department newDept = pDepartment.getItem();
		service.modifyDepartment(newDept);
		pDeptList.updateRow(newDept, pDeptList.getSelectedRowIdx());
		btnAdd.setText("추가");
		pDepartment.clearTf();
		JOptionPane.showMessageDialog(null, "부서가 수정되었습니다.");		
	}

	protected void btnCancelActionPerformed(ActionEvent e) {
		pDepartment.clearTf();
	}
	
	protected void btnAddActionPerformed(ActionEvent e) {
		try {
			Department newDept = pDepartment.getItem();
			service.addDepartment(newDept);
			pDeptList.addItem(newDept);
			pDepartment.clearTf();
			JOptionPane.showMessageDialog(null, "부서가 추가되었습니다.");
		}catch(InvalidCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}catch(Exception e1) {
			SQLException e2 = (SQLException) e1;
			if (e2.getErrorCode() == 1062) {
				JOptionPane.showMessageDialog(null, "부서번호가 중복");
				System.err.println(e2.getMessage());
				return;
			}
			e1.printStackTrace();
		}
	}

}
