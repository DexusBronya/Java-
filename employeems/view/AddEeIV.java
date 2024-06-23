package eems.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import eems.impl.OpServiceImpl;
import eems.pojo.Ee;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.Font;

public class AddEeIV extends JInternalFrame {
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddEeIV frame = new AddEeIV();
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
    public AddEeIV() {
        setBounds(0, 0, 1027, 787);
        getContentPane().setLayout(null);

        OpServiceImpl opService = new OpServiceImpl(); // 初始化 OpService

        textField = new JTextField();
        textField.setBounds(200, 50, 259, 35);
        getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(200, 100, 259, 35);
        getContentPane().add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(200, 150, 259, 35);
        getContentPane().add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(200, 200, 259, 35);
        getContentPane().add(textField_3);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(200, 250, 259, 35);
        getContentPane().add(textField_4);

        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(200, 300, 259, 35);
        getContentPane().add(textField_5);

        textField_6 = new JTextField();
        textField_6.setColumns(10);
        textField_6.setBounds(200, 350, 259, 35);
        getContentPane().add(textField_6);

        JLabel lblNewLabel = new JLabel("姓名");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 22));
        lblNewLabel.setBounds(100, 50, 100, 35);
        getContentPane().add(lblNewLabel);

        JLabel label = new JLabel("工号");
        label.setFont(new Font("宋体", Font.PLAIN, 22));
        label.setBounds(100, 100, 100, 35);
        getContentPane().add(label);

        JLabel label_1 = new JLabel("性别");
        label_1.setFont(new Font("宋体", Font.PLAIN, 22));
        label_1.setBounds(100, 150, 100, 35);
        getContentPane().add(label_1);

        JLabel label_2 = new JLabel("出生日期");
        label_2.setFont(new Font("宋体", Font.PLAIN, 22));
        label_2.setBounds(100, 200, 100, 35);
        getContentPane().add(label_2);

        JLabel label_3 = new JLabel("入职日期");
        label_3.setFont(new Font("宋体", Font.PLAIN, 22));
        label_3.setBounds(100, 249, 100, 35);
        getContentPane().add(label_3);

        JLabel label_4 = new JLabel("薪水");
        label_4.setFont(new Font("宋体", Font.PLAIN, 22));
        label_4.setBounds(100, 300, 100, 35);
        getContentPane().add(label_4);

        JLabel label_5 = new JLabel("权限");
        label_5.setFont(new Font("宋体", Font.PLAIN, 22));
        label_5.setBounds(100, 346, 100, 43);
        getContentPane().add(label_5);

        JButton btnNewButton = new JButton("添加");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 从文本框中获取员工信息
                String ename = textField.getText();
                String enumber = textField_1.getText();
                String esex = textField_2.getText();
                String eborn = textField_3.getText();
                String eentry = textField_4.getText();
                String esalaryText = textField_5.getText();
                String eauthority = textField_6.getText();
                String username = textField_7.getText();
                String password = textField_8.getText();

                // 检查薪水是否为空或小于等于零
                if (esalaryText.isEmpty() || Integer.parseInt(esalaryText) <= 0) {
                    JOptionPane.showMessageDialog(null, "薪水不能为空或小于等于零，必须为自然数！", "错误", JOptionPane.ERROR_MESSAGE);
                    return; // 如果薪水为空或小于等于零，直接返回，不执行后续的操作
                }

                // 将薪水转换为整数
                int esalary = Integer.parseInt(esalaryText);

                // 创建员工对象
                Ee ee = new Ee();

                // 调用 OpService 的 addEmployee 方法添加员工
                try {
                    ee.setEname(ename);
                    ee.setEnumber(enumber);
                    ee.setEsex(esex);
                    ee.setEborn(eborn);
                    ee.setEentry(eentry);
                    ee.setEsalary(esalary);
                    ee.setEauthority(eauthority);
                    ee.setUsername(username);
                    ee.setPassword(password);
                    // 调用 OpService 的 addEmployee 方法添加员工
                    opService.addEmployee(ee);
                    JOptionPane.showMessageDialog(null, "员工添加成功！");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "增加失败！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnNewButton.setBounds(200, 533, 123, 35);
        getContentPane().add(btnNewButton);
        
        JLabel label_6 = new JLabel("日期格式为\r\n");
        label_6.setFont(new Font("宋体", Font.PLAIN, 20));
        label_6.setBounds(544, 159, 123, 105);
        getContentPane().add(label_6);
        
        JLabel label_7 = new JLabel("2024-3-24");
        label_7.setFont(new Font("宋体", Font.PLAIN, 20));
        label_7.setBounds(650, 197, 123, 28);
        getContentPane().add(label_7);
        
        JLabel label_8 = new JLabel("0为超级管理员 1为管理员 2为普通员工");
        label_8.setBounds(200, 385, 332, 24);
        getContentPane().add(label_8);
        
        JLabel lblNewLabel_1 = new JLabel("账号");
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 22));
        lblNewLabel_1.setBounds(100, 411, 100, 43);
        getContentPane().add(lblNewLabel_1);
        
        textField_7 = new JTextField();
        textField_7.setColumns(10);
        textField_7.setBounds(200, 416, 259, 35);
        getContentPane().add(textField_7);
        
        textField_8 = new JTextField();
        textField_8.setColumns(10);
        textField_8.setBounds(200, 466, 259, 34);
        getContentPane().add(textField_8);
        
        JLabel label_9 = new JLabel("密码");
        label_9.setFont(new Font("宋体", Font.PLAIN, 22));
        label_9.setBounds(100, 460, 96, 43);
        getContentPane().add(label_9);
    }
}
