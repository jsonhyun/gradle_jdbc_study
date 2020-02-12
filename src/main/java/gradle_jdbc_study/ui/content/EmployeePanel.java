package gradle_jdbc_study.ui.content;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gradle_jdbc_study.dto.Department;
import gradle_jdbc_study.dto.Employee;
import gradle_jdbc_study.dto.Title;

@SuppressWarnings("serial")
public class EmployeePanel extends AbsItemPanel<Employee> {
	private JTextField tfNo;
	private JTextField tfName;
	private JComboBox<Department> cmbDept;
	private JComboBox<Employee> cmbManager;
	private JComboBox<Title> cmbTitle;

	public EmployeePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pWest = new JPanel();
		add(pWest, BorderLayout.WEST);
		
		JPanel pCenter = new JPanel();
		add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblNo = new JLabel("사원번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pCenter.add(lblNo);
		
		tfNo = new JTextField();
		pCenter.add(tfNo);
		tfNo.setColumns(10);
		
		JLabel lblName = new JLabel("사원명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		pCenter.add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		pCenter.add(tfName);
		
		JLabel lblDept = new JLabel("부서");
		lblDept.setHorizontalAlignment(SwingConstants.RIGHT);
		pCenter.add(lblDept);
		
		cmbDept = new JComboBox<>();
		pCenter.add(cmbDept);
		
		JLabel lblManager = new JLabel("직속상사");
		lblManager.setHorizontalAlignment(SwingConstants.RIGHT);
		pCenter.add(lblManager);
		
		cmbManager = new JComboBox<>();
		pCenter.add(cmbManager);
		
		JLabel lblTitle = new JLabel("직책");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		pCenter.add(lblTitle);
		
		cmbTitle = new JComboBox<Title>();
		pCenter.add(cmbTitle);
		

	}

	public void setCmbDeptList(List<Department> deptList) {
		DefaultComboBoxModel<Department> model = new DefaultComboBoxModel<Department>(new Vector<>(deptList));
		cmbDept.setModel(model);
		cmbDept.setSelectedIndex(-1);
	}
	public void setCmbManagerList(List<Employee> mgnList) {
		DefaultComboBoxModel<Employee> model = new DefaultComboBoxModel<Employee>(new Vector<>(mgnList));
		cmbManager.setModel(model);
		cmbManager.setSelectedIndex(-1);
	}
	
	public void setCmbTitleList(List<Title> titleList) {
		DefaultComboBoxModel<Title> model = new DefaultComboBoxModel<Title>(new Vector<>(titleList));
		cmbTitle.setModel(model);
		cmbTitle.setSelectedIndex(-1);
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

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cmbDept) {
			cmbDeptItemStateChanged(e);
		}
	}
	protected void cmbDeptItemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			JOptionPane.showMessageDialog(null, cmbDept.getSelectedItem());
		}
	}

	public JComboBox<Department> getCmbDept() {
		return cmbDept;
	}

	public JComboBox<Employee> getCmbManager() {
		return cmbManager;
	}

	public JComboBox<Title> getCmbTitle() {
		return cmbTitle;
	}
	
}
