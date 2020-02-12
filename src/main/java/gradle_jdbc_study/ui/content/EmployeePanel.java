package gradle_jdbc_study.ui.content;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JDateChooser;

import gradle_jdbc_study.dto.Department;
import gradle_jdbc_study.dto.Employee;
import gradle_jdbc_study.dto.Title;
import gradle_jdbc_study.ui.listener.MyDocumentListener;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class EmployeePanel extends AbsItemPanel<Employee> {
	private JTextField tfNo;
	private JTextField tfName;
	private JComboBox<Department> cmbDept;
	private JComboBox<Employee> cmbManager;
	private JComboBox<Title> cmbTitle;
	private JPasswordField pfPasswd1;
	private JPasswordField pfPasswd2;
	private JLabel lblPasswdEqual;
	private Dimension picDimension = new Dimension(100, 150);
	private JLabel lblPic;

	public EmployeePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pWest = new JPanel();
		add(pWest, BorderLayout.WEST);
		pWest.setLayout(new BoxLayout(pWest, BoxLayout.Y_AXIS));
		
		lblPic = new JLabel();
		lblPic.setHorizontalAlignment(SwingConstants.CENTER);
		lblPic.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblPic.setPreferredSize(picDimension);
		lblPic.setSize(picDimension);
		setPic(getClass().getClassLoader().getResource("no-image.png").getPath());
		pWest.add(lblPic);
		
		JButton btnPic = new JButton("증명사진");
		pWest.add(btnPic);
		
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
		
		JLabel lblHireDate = new JLabel("입사일");
		lblHireDate.setHorizontalAlignment(SwingConstants.RIGHT);
		pCenter.add(lblHireDate);
		
		JDateChooser tfHireDate = new JDateChooser(new Date(), "yyyy-MM-dd hh:mm");
		pCenter.add(tfHireDate);
		
		JLabel lblSalary = new JLabel("급여");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		pCenter.add(lblSalary);
		
		JSpinner spSalary = new JSpinner();
		spSalary.setModel(new SpinnerNumberModel(1500000, 1000000, 5000000, 100000));
		pCenter.add(spSalary);
		
		JLabel lblPasswd1 = new JLabel("비밀번호");
		lblPasswd1.setHorizontalAlignment(SwingConstants.RIGHT);
		pCenter.add(lblPasswd1);
		
		lblPasswdEqual = new JLabel();
		lblPasswdEqual.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswdEqual.setFont(new Font("굴림", Font.BOLD, 12));
		lblPasswdEqual.setForeground(Color.RED);
		
		pfPasswd1 = new JPasswordField();
		pfPasswd1.getDocument().addDocumentListener(docListener);
		pCenter.add(pfPasswd1);
		
		JLabel lblPasswd2 = new JLabel("비밀번호 확인");
		lblPasswd2.setHorizontalAlignment(SwingConstants.RIGHT);
		pCenter.add(lblPasswd2);
		
		pfPasswd2 = new JPasswordField();
		pfPasswd2.getDocument().addDocumentListener(docListener);
		pCenter.add(pfPasswd2);
		
		JPanel panel = new JPanel();
		pCenter.add(panel);
		
		pCenter.add(lblPasswdEqual);
	}
	
	private void setPic(String imgPath) {
		lblPic.setIcon(new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance((int)picDimension.getWidth(), (int)picDimension.getHeight(), Image.SCALE_DEFAULT)));
	}
	
	DocumentListener docListener = new MyDocumentListener() {
		
		@Override
		public void msg() {
			String pw1 = new String(pfPasswd1.getPassword());
			String pw2 = new String(pfPasswd2.getPassword());
			if(pw1.length() == 0 | pw2.length() == 0) {
				lblPasswdEqual.setText("");
			}else if(pw1.contentEquals(pw2)) {
				lblPasswdEqual.setText("비밀번호 일치");
			}else {
				lblPasswdEqual.setText("비밀번호 불일치");
			}
		}
	};
	
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
