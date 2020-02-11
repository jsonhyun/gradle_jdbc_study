package gradle_jdbc_study.ui;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private LoginFrame loginFrame;
	private JButton btnLogout;
	private JLabel lblLoginName;
	public MainFrame() {
		initialize();
	}
	
	
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblLoginName = new JLabel("New label");
		lblLoginName.setFont(new Font("굴림", Font.BOLD, 40));
		lblLoginName.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblLoginName);
		
		btnLogout = new JButton("로그아웃");
		btnLogout.addActionListener(this);
		contentPane.add(btnLogout);
		
		
		loginNameRefresh();
	}

	public void loginNameRefresh() {
		lblLoginName.setText(LoginFrame.loginEmp.getEmpName());
	}
	public void setLoginFrame(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogout) {
			btnLogoutActionPerformed(e);
		}
	}
	protected void btnLogoutActionPerformed(ActionEvent e) {
		LoginFrame.loginEmp = null;
		dispose();
		loginFrame.setVisible(true);
		loginFrame.clearTf();
	}
}
