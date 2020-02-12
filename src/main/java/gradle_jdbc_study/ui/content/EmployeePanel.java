package gradle_jdbc_study.ui.content;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gradle_jdbc_study.dto.Department;
import gradle_jdbc_study.dto.Employee;

@SuppressWarnings("serial")
public class EmployeePanel extends AbsItemPanel<Employee> {
	private JTextField tfNo;
	private JTextField tfName;
	private JComboBox<Department> cmbDept;

	public EmployeePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pWest = new JPanel();
		add(pWest, BorderLayout.WEST);
		
		JPanel pCenter = new JPanel();
		add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblNo = new JLabel("사원번호");
		pCenter.add(lblNo);
		
		tfNo = new JTextField();
		pCenter.add(tfNo);
		tfNo.setColumns(10);
		
		JLabel lblName = new JLabel("사원명");
		pCenter.add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		pCenter.add(tfName);
		
		JLabel lblDept = new JLabel("부서");
		pCenter.add(lblDept);
		
		cmbDept = new JComboBox<>();
		pCenter.add(cmbDept);

	}

	public void setCmbDeptList(List<Department> deptList) {
		DefaultComboBoxModel<Department> model = new DefaultComboBoxModel<Department>(new Vector<>(deptList));
		cmbDept.setModel(model);
		cmbDept.setSelectedIndex(-1);
	}
	
	@Override
	public Employee getItem() {
		validCheck();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setItem(Employee item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearTf() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validCheck() {
		// TODO Auto-generated method stub
		
	}

}
