package eems.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import eems.impl.EeServiceImpl;
import eems.pojo.Ee;
import eems.service.EeService;

import java.awt.Font;

public class LoginView extends JFrame {

	public static Ee ee;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setTitle("登录界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1005, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//点击登录按钮的事件处理
				//1.验证用户名和密码是否有效
				String username = textField.getText();
				String password = passwordField.getText();
				//if(uname.trim().equals("admin") && pwd.trim().equals("123")){
				EeService service = new EeServiceImpl();
				
				try {
					ee = service.login(username, password);
					if(ee != null){
						dispose();
						MainView mainView = new MainView();
						 mainView.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "用户名或者密码错误！请重新输入");//消息弹框！！！！！！！！！！！！！！！！！！！
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//1.1 有效 -> 跳转到主界面
				//先关闭当前页面

				//viewMain.setVisible(true);
				//1.2 无效 -> 给出错误提示
				
				
			}
		});
		btnNewButton.setBounds(259, 460, 171, 63);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("退出");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(659, 460, 171, 63);
		contentPane.add(button);
		
		textField = new JTextField();
		textField.setBounds(356, 246, 410, 52);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(356, 361, 410, 52);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("用户名");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel.setBounds(193, 259, 81, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密  码");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(193, 374, 81, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("员工考勤管理系统");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 50));
		lblNewLabel_2.setBounds(291, 79, 539, 82);
		contentPane.add(lblNewLabel_2);
	}
}


