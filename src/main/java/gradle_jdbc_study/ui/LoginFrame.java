package gradle_jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import gradle_jdbc_study.dto.Employee;
import gradle_jdbc_study.ui.service.LoginService;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfNo;
	private JPasswordField pfPasswd;
	private JButton btnLogin;
	private JButton btnCancel;
	private LoginService service;
	private MainFrame main;
	
	public static Employee loginEmp;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//select Look and Feel
//					UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
//					UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
//					UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
//					UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
//					UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
//					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFrame() {
		service = new LoginService();
		initialize();
	}

	private void initialize() {
		setTitle("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 349, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pContent = new JPanel();
		pContent.setBorder(new TitledBorder(null, "\uB85C\uADF8\uC778", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pContent);
		pContent.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblNo = new JLabel("사원번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblNo);
		
		tfNo = new JTextField();
		tfNo.setText("1003");
		pContent.add(tfNo);
		tfNo.setColumns(10);
		
		JLabel lblPasswd = new JLabel("비밀번호");
		lblPasswd.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblPasswd);
		
		pfPasswd = new JPasswordField("1234567");
		pContent.add(pfPasswd);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn, BorderLayout.SOUTH);
		
		btnLogin = new JButton("로그인");
		btnLogin.addActionListener(this);
		pBtn.add(btnLogin);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			btnCancelActionPerformed(e);
		}
		if (e.getSource() == btnLogin) {
			btnLoginActionPerformed(e);
		}
	}
	protected void btnLoginActionPerformed(ActionEvent e) {
		int empNo= Integer.parseInt(tfNo.getText().trim());
		String passwd = new String(pfPasswd.getPassword());
		System.out.println(empNo+":"+passwd);
		
		loginEmp = service.login(new Employee(empNo, passwd));
		if(loginEmp == null) {
			JOptionPane.showMessageDialog(null, "사원번호 혹은 비밀번호가 틀림");
			return;
		}
		JOptionPane.showMessageDialog(null, loginEmp.getEmpName()+"님 반갑습니다.");
		if(main == null) {
			main = new MainFrame();
			main.setLoginFrame(this);
		}
		dispose();
		main.loginNameRefresh();
		main.setVisible(true);
	}
	protected void btnCancelActionPerformed(ActionEvent e) {
		clearTf();
	}

	public void clearTf() {
		tfNo.setText("");
		pfPasswd.setText("");
	}
}
