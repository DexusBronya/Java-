package eems.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import eems.impl.EeServiceImpl;
import eems.pojo.Ee;
import eems.service.EeService;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class EeSearchIV extends JInternalFrame {
    private JTextField textField;
    private static String flagMoon = null;
    private static String flagNight = null;
    private JLabel labelMoon;
    private JLabel labelNight;
	protected String time;
	private static String date = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EeSearchIV frame = new EeSearchIV();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public EeSearchIV() {
    	setTitle("个人考勤");
        setBounds(100, 100, 950, 692);
        getContentPane().setLayout(null);

        JLabel label = new JLabel("日期");
        label.setFont(new Font("宋体", Font.PLAIN, 24));
        label.setBounds(58, 89, 81, 21);
        getContentPane().add(label);

        textField = new JTextField();
        textField.setBounds(166, 85, 217, 34);
        getContentPane().add(textField);
        textField.setColumns(10);

        LocalDate now = LocalDate.now();
        Date today = Date.valueOf(now);
        textField.setText(today.toString());

        JLabel label_1 = new JLabel("例如2024-2-23");
        label_1.setBounds(440, 89, 132, 21);
        getContentPane().add(label_1);

        JLabel lblNewLabel = new JLabel("上班考勤\r\n");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 24));
        lblNewLabel.setBounds(58, 153, 132, 51);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("下班考勤");
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 24));
        lblNewLabel_1.setBounds(58, 232, 132, 51);
        getContentPane().add(lblNewLabel_1);
        date = textField.getText();

        JButton button_1 = new JButton("上班打卡");
        button_1.setBounds(419, 166, 123, 29);
        getContentPane().add(button_1);
        
        JButton button_2 = new JButton("下班打卡");
        button_2.setBounds(419, 245, 123, 29);
        getContentPane().add(button_2);
        
        button_1.setEnabled(false);
        button_2.setEnabled(false);
        
        JButton button = new JButton("查询");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ee ee = LoginView.ee;
                EeService eeService = new EeServiceImpl();
                date = textField.getText() + " 00:00:00";

                try {
                    String status = eeService.getAttendanceStatusByEnumber(ee.getEnumber(), date);

                    if ("上班已打卡，下班未打卡".equals(status)) {
                        flagMoon = "已打卡";
                        flagNight = "未打卡";
                    } else if ("上班未打卡，下班已打卡".equals(status)) {
                        flagMoon = "未打卡";
                        flagNight = "已打卡";
                    } else if ("全勤".equals(status)) {
                        flagMoon = "已打卡";
                        flagNight = "已打卡";
                    } else if ("缺勤".equals(status)) {
                        flagMoon = "未打卡";
                        flagNight = "未打卡";
                    }

                    labelMoon.setText(flagMoon);
                    labelNight.setText(flagNight);

                    LocalDate today = LocalDate.now();
                    if (date.equals(today.toString() + " 00:00:00")) {
                    	boolean flagAttendenceMoon = flagMoon.equals("未打卡");
                        button_1.setEnabled(flagAttendenceMoon);
                        
                        boolean flagAttendenceNight = flagMoon.equals("已打卡") && flagNight.equals("未打卡");
                        button_2.setEnabled(flagAttendenceNight);
                    } else {
                        button_1.setEnabled(false);
                        button_2.setEnabled(false);
                    }

                    getContentPane().add(labelMoon);
                    getContentPane().add(labelNight);
                    
                    revalidate();
                    repaint();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        button.setBounds(246, 350, 123, 29);
        getContentPane().add(button);
        
        
    	EeServiceImpl eeSI = new EeServiceImpl();
    	
    	button_1.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        Ee ee = LoginView.ee;
    	        String enumber = ee.getEnumber();
    	        String time = "上班";

    	        LocalDate today1 = LocalDate.now();
    	        String today = today1.toString();

    	        try {
    	            String status = eeSI.getAttendanceStatusByEnumber(enumber, today + " 00:00:00");
    	            if (!status.equals("上班已打卡，下班未打卡")) {
    	                eeSI.updateAttendance(enumber, time);

    	                flagMoon = "已打卡";
    	                labelMoon.setText(flagMoon);
    	                getContentPane().add(labelMoon);
    	                revalidate();
    	                repaint();
    	                button.doClick();

    	                // 打卡成功提示
    	                JOptionPane.showMessageDialog(null, "上班打卡成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
    	            } else {
    	                JOptionPane.showMessageDialog(null, "您已经打过上班卡了！", "提示", JOptionPane.INFORMATION_MESSAGE);
    	            }
    	        } catch (SQLException ex) {
    	            ex.printStackTrace();
    	            JOptionPane.showMessageDialog(null, "打卡失败！", "错误", JOptionPane.ERROR_MESSAGE);
    	        }
    	    }
    	});

    	button_2.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        Ee ee = LoginView.ee;
    	        String enumber = ee.getEnumber();
    	        String time = "下班";

    	        LocalDate today1 = LocalDate.now();
    	        String today = today1.toString();
    	        try {
    	            String status = eeSI.getAttendanceStatusByEnumber(enumber, today);
    	            if (!status.equals("上班未打卡，下班已打卡")) {
    	                eeSI.updateAttendance(enumber, time);
    	                flagNight = "已打卡";
    	                labelNight.setText(flagNight);
    	                getContentPane().add(labelNight);
    	                revalidate();
    	                repaint();
    	                button.doClick();

    	                // 打卡成功提示
    	                JOptionPane.showMessageDialog(null, "下班打卡成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
    	            } else {
    	                JOptionPane.showMessageDialog(null, "您已经打过下班卡了！", "提示", JOptionPane.INFORMATION_MESSAGE);
    	            }
    	        } catch (SQLException ex) {
    	            ex.printStackTrace();
    	            JOptionPane.showMessageDialog(null, "打卡失败！", "错误", JOptionPane.ERROR_MESSAGE);
    	        }
    	    }
    	});

        labelMoon = new JLabel("1");
        labelMoon.setFont(new Font("宋体", Font.PLAIN, 24));
        labelMoon.setBounds(245, 147, 132, 59);

        labelNight = new JLabel("2");
        labelNight.setFont(new Font("宋体", Font.PLAIN, 24));
        labelNight.setBounds(245, 226, 132, 59);
    }
}
