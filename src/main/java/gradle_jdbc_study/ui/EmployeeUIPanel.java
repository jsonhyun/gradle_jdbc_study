package gradle_jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import gradle_jdbc_study.dto.Employee;
import gradle_jdbc_study.ui.content.EmployeePanel;
import gradle_jdbc_study.ui.exception.InvalidCheckException;
import gradle_jdbc_study.ui.list.EmployeeTblPanel;
import gradle_jdbc_study.ui.service.EmployeeUIService;

@SuppressWarnings("serial")
public class EmployeeUIPanel extends JPanel implements ActionListener {
	private EmployeeUIService service;
	private EmployeeTblPanel pEmployeeList;
	private EmployeePanel pEmployee;
	
	private JButton btnAdd;
	private JButton btnCancel;
	
	public EmployeeUIPanel() {
		service = new EmployeeUIService();
		initialize();
	}
	
	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pContent = new JPanel();
		add(pContent);
		pContent.setLayout(new BorderLayout(0, 0));
		
		pEmployee = new EmployeePanel();
		pContent.add(pEmployee, BorderLayout.CENTER);
		
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
		
		pEmployeeList = new EmployeeTblPanel();
		pEmployeeList.loadData(service.showEmployeeList());
		pEmployeeList.setPopupMenu(createPopupMenu());
		pList.add(pEmployeeList, BorderLayout.CENTER);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(myPopMenuListener);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(myPopMenuListener);
		popMenu.add(deleteItem);
		
		return popMenu;
	}
	
	ActionListener myPopMenuListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("수정")) {
				Employee upEmp = pEmployeeList.getSelectedItem();
				pEmployee.setItem(upEmp);
				btnAdd.setText("수정");
			}
			if (e.getActionCommand().equals("삭제")) {
				Employee delEmp = pEmployeeList.getSelectedItem();
				service.removeEmployee(delEmp);
				pEmployeeList.removeRow();
				JOptionPane.showMessageDialog(null, "삭제되었습니다.");
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
		Employee upEmp = pEmployee.getItem();
		service.modifyEmployee(upEmp);
		pEmployeeList.updateRow(upEmp, pEmployeeList.getSelectedRowIdx());
		btnAdd.setText("추가");
		pEmployee.clearTf();
		JOptionPane.showMessageDialog(null, "부서가 수정되었습니다.");		
	}

	protected void btnCancelActionPerformed(ActionEvent e) {
		pEmployee.clearTf();
	}
	
	protected void btnAddActionPerformed(ActionEvent e) {
		try {
			Employee newEmp = pEmployee.getItem();
			service.addEmployee(newEmp);
			pEmployeeList.addItem(newEmp);
			pEmployee.clearTf();
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
