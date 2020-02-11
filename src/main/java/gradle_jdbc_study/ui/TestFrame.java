package gradle_jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gradle_jdbc_study.dto.Title;
import gradle_jdbc_study.ui.content.TitlePanel;

@SuppressWarnings("serial")
public class TestFrame extends JFrame {

	public static void main(String[] args) {
//		contentPanelTest();
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 400);
		DepartmentUIPanel tp = new DepartmentUIPanel();
		frame.add(tp);
		frame.setVisible(true);
	}

	private static void contentPanelTest() {
		JFrame frame = new JFrame();
		TitlePanel tp = new TitlePanel();
		frame.add(tp);
		
		JButton btn1 = new JButton("확인");
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Title title = tp.getItem();
				JOptionPane.showMessageDialog(null, title);
			}
		});
		
		JButton btn2 = new JButton("취소");
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tp.clearTf();
			}
		});
		
		JButton btn3 = new JButton("추가");
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Title t = new Title(2, "팀장");
				tp.setItem(t);
			}
		});
		JPanel btns = new JPanel();
		btns.add(btn1);
		btns.add(btn2);
		btns.add(btn3);
		
		frame.add(btns, BorderLayout.SOUTH);
		frame.setBounds(100, 100, 450, 300);
		frame.setVisible(true);
	}


}
