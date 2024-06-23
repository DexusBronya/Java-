package eems.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DesktopPaneUI;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import eems.impl.EeServiceImpl;
import eems.pojo.Ee;
import eems.service.EeService;

import java.awt.Font;
import javax.swing.JInternalFrame;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane1;
	private List<JInternalFrame> internalFrames = new ArrayList<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1254, 842);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setBounds(64, 15, 970, 86);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("欢迎你：");
		label.setFont(new Font("宋体", Font.PLAIN, 24));
		label.setBounds(0, 0, 102, 47);
		panel.add(label);

		Ee ee = LoginView.ee;
		String au = ee.getEauthority();
		if (au.equals("0")) {
			au = "超级管理员";
		} else if (au.equals("1")) {
			au = "管理员";
		} else
			au = "普通员工";
		JLabel lblNewLabel = new JLabel(ee.getEname() + "  您的权限为：" + au);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 24));
		lblNewLabel.setBounds(95, 15, 823, 21);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(15, 116, 159, 609);
		contentPane.add(panel_1);
		panel_1.setBackground(Color.YELLOW);
		panel_1.setLayout(null);

		desktopPane1 = new JDesktopPane();

		JButton btnNewButton_1 = new JButton("个人考勤");

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				closeAllInternalFrames();

				EeSearchIV ESIV = new EeSearchIV();
				ESIV.setBounds(0, 0, desktopPane1.getWidth(), desktopPane1.getHeight());
				ESIV.setVisible(true);
				addToDesktopPane(ESIV);
			}
		});
		btnNewButton_1.setBounds(17, 41, 123, 29);
		panel_1.add(btnNewButton_1);
		

		
		JButton button_4 = new JButton("退出系统");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_4.setBounds(17, 517, 123, 29);
		panel_1.add(button_4);
		
		
		
		JButton button_5 = new JButton("月份考勤情况");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 关闭之前的所有 InternalFrame
				closeAllInternalFrames();
				
				PersonalAttendanceSearchIV PASIV = new PersonalAttendanceSearchIV();
				PASIV.setBounds(0, 0, desktopPane1.getWidth(), desktopPane1.getHeight());
				PASIV.setVisible(true);
				addToDesktopPane(PASIV);				
			}
		});
		button_5.setBounds(17, 111, 123, 29);
		panel_1.add(button_5);
	
		
	
		
		
		
		if (au.equals("超级管理员") || au.equals("管理员")) {
			
			JButton button = new JButton("添加员工");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 关闭之前的所有 InternalFrame
			        closeAllInternalFrames();

			        // 创建 AddEeIV 窗口
			        AddEeIV addEeIV = new AddEeIV();
			        addEeIV.setBounds(0, 0, desktopPane1.getWidth(), desktopPane1.getHeight());
			        addEeIV.setVisible(true);
			        addToDesktopPane(addEeIV);
					
				}
			});
			button.setBounds(17, 178, 123, 29);
			panel_1.add(button);
			
			JButton button_1 = new JButton("删除员工");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 关闭之前的所有 InternalFrame
			        closeAllInternalFrames();

			        // 创建 AddEeIV 窗口
			        DeleteEeIV deleteEeIV = new DeleteEeIV();
			        deleteEeIV.setBounds(0, 0, desktopPane1.getWidth(), desktopPane1.getHeight());
			        deleteEeIV.setVisible(true);
			        addToDesktopPane(deleteEeIV);
				}
			});
			button_1.setBounds(17, 245, 123, 29);
			panel_1.add(button_1);
			
			JButton button_2 = new JButton("查询修改员工");
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					// 关闭之前的所有 InternalFrame
			        closeAllInternalFrames();

			        // 创建 AddEeIV 窗口
			        UpdateEeIV updateEeIV = new UpdateEeIV();
			        updateEeIV.setBounds(0, 0, desktopPane1.getWidth(), desktopPane1.getHeight());
			        updateEeIV.setVisible(true);
			        addToDesktopPane(updateEeIV);
				}
			});
			button_2.setBounds(17, 313, 123, 29);
			panel_1.add(button_2);
			
			JButton btnNewButton = new JButton("查询日期考勤");
			btnNewButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// 关闭之前的所有 InternalFrame
					closeAllInternalFrames();
					
					SearchInternalView SIV = new SearchInternalView();
					SIV.setBounds(0, 0, desktopPane1.getWidth(), desktopPane1.getHeight());
					SIV.setVisible(true);
					addToDesktopPane(SIV);
				}
			});
			
			btnNewButton.setBounds(17, 378, 123, 29);
			panel_1.add(btnNewButton);
			
		}



		
		desktopPane1.setBounds(204, 116, 834, 609);
		contentPane.add(desktopPane1);
		
		

	}
	

	
	// 将 InternalFrame 添加到 desktopPane 中，并保存到 internalFrames 列表中
	private void addToDesktopPane(JInternalFrame internalFrame) {
		desktopPane1.add(internalFrame);
		internalFrames.add(internalFrame);
	}

	// 关闭所有的 InternalFrame
	private void closeAllInternalFrames() {
		for (JInternalFrame internalFrame : internalFrames) {
			if (internalFrame.isVisible()) {
				internalFrame.dispose();
			}
		}
		internalFrames.clear();
	}
}
