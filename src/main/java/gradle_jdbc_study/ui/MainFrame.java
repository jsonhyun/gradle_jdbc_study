package gradle_jdbc_study.ui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private LoginFrame loginFrame;
	private JButton btnLogOut;
	private JLabel lblLoginName;
	private JPanel panel;
	private JButton btnTitle;
	private JButton btnDepartment;
	private JButton btnEmployee;
	
	public MainFrame() {
		initialize();
	}
	
	
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 192);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblLoginName = new JLabel("New label");
		lblLoginName.setFont(new Font("굴림", Font.BOLD, 40));
		lblLoginName.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblLoginName);
		
		btnLogOut = new JButton("로그아웃");
		btnLogOut.addActionListener(this);
		contentPane.add(btnLogOut);
		
		
		loginNameRefresh();
		panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		btnTitle = new JButton("직책 정보");
		btnTitle.addActionListener(this);
		panel.add(btnTitle);
		
		btnDepartment = new JButton("부서 정보");
		btnDepartment.addActionListener(this);
		panel.add(btnDepartment);
		
		btnEmployee = new JButton("사원 정보");
		btnEmployee.addActionListener(this);
		panel.add(btnEmployee);
	}

	public void loginNameRefresh() {
		lblLoginName.setText(LoginFrame.loginEmp.getEmpName());
	}
	public void setLoginFrame(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEmployee) {
			btnEmployeeActionPerformed(e);
		}
		if (e.getSource() == btnDepartment) {
			btnDepartmentActionPerformed(e);
		}
		if (e.getSource() == btnTitle) {
			btnTitleActionPerformed(e);
		}
		if (e.getSource() == btnLogOut) {
			btnLogOutActionPerformed(e);
		}
	}
	protected void btnLogOutActionPerformed(ActionEvent e) {
		LoginFrame.loginEmp = null;
		dispose();
		loginFrame.setVisible(true);
		loginFrame.clearTf();
	}
	protected void btnTitleActionPerformed(ActionEvent e) {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 400);
		TitleUiPanel tup = new TitleUiPanel();
		frame.getContentPane().add(tup);
		frame.setVisible(true);
	}
	protected void btnDepartmentActionPerformed(ActionEvent e) {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 400);
		DepartmentUIPanel tp = new DepartmentUIPanel();
		frame.getContentPane().add(tp);
		frame.setVisible(true);
	}
	protected void btnEmployeeActionPerformed(ActionEvent e) {
	}
}
