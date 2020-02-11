package gradle_jdbc_study.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import gradle_jdbc_study.dao.impl.DepartmentDaoImpl;
import gradle_jdbc_study.dto.Department;
import gradle_jdbc_study.ui.list.DepartmentTblPanel;

public class TestTableFrame {
	private static DepartmentTblPanel tp;
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		tp = new DepartmentTblPanel();
		frame.add(tp);
		List<Department> list = DepartmentDaoImpl.getInstance().selectDepartmentByAll();
		tp.loadData(list);
		tp.setPopupMenu(createPopupMenu());
		frame.setVisible(true);
	}
	
	static ActionListener myPopMenuListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().contentEquals("수정")) {
				Department updateDept = new Department(5, "마케팅", 10);
				tp.updateRow(updateDept, tp.getSelectedRowIdx());
			}
			if(e.getActionCommand().contentEquals("삭제")) {
				Department selectedDept = tp.getSelectedItem();
				JOptionPane.showMessageDialog(null, selectedDept);
				
				tp.removeRow();
				Department insDept = new Department(5, "마케팅", 10);
				tp.addItem(insDept);
			}
			if(e.getActionCommand().contentEquals("소속 사원")) {
				JOptionPane.showMessageDialog(null, e.getActionCommand());
			}
			
		}
	};

	
	private static JPopupMenu createPopupMenu() {
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
}
