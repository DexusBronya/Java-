package eems.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import eems.impl.OpServiceImpl;
import eems.pojo.Ee;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class UpdateEeIV extends JInternalFrame {

    private OpServiceImpl opService;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField enameField;
    private JTextField enumberField;
    private JTextField esexField;
    private JTextField ebornField;
    private JTextField eentryField;
    private JTextField esalaryField;
    private JTextField eauthorityField;
    private JTextField searchField;

    /**
     * Create the frame.
     */
    public UpdateEeIV() {
        setTitle("修改员工信息");
        setBounds(100, 100, 1000, 600);
        getContentPane().setLayout(null);

        opService = new OpServiceImpl();

        JLabel lblNewLabel = new JLabel("员工列表：");
        lblNewLabel.setBounds(32, 26, 95, 16);
        getContentPane().add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(32, 54, 437, 449);
        getContentPane().add(scrollPane);

        // 创建表格模型
        tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "员工姓名", "员工编号" });
        table = new JTable(tableModel);
        table.setRowHeight(30); // 设置行高为30像素
        scrollPane.setViewportView(table);

        JLabel lblEmployeeInformation = new JLabel("员工信息：");
        lblEmployeeInformation.setFont(new Font("Lucida Grande", Font.BOLD, 16));
        lblEmployeeInformation.setBounds(550, 26, 120, 16);
        getContentPane().add(lblEmployeeInformation);

        JLabel lblEname = new JLabel("姓名：");
        lblEname.setBounds(550, 80, 61, 16);
        getContentPane().add(lblEname);

        enameField = new JTextField();
        enameField.setBounds(650, 75, 163, 26);
        getContentPane().add(enameField);
        enameField.setColumns(10);

        JLabel lblEnumber = new JLabel("编号：");
        lblEnumber.setBounds(550, 130, 61, 16);
        getContentPane().add(lblEnumber);

        enumberField = new JTextField();
        enumberField.setBounds(650, 125, 163, 26);
        getContentPane().add(enumberField);
        enumberField.setColumns(10);

        JLabel lblEsex = new JLabel("性别：");
        lblEsex.setBounds(550, 180, 61, 16);
        getContentPane().add(lblEsex);

        esexField = new JTextField();
        esexField.setBounds(650, 175, 163, 26);
        getContentPane().add(esexField);
        esexField.setColumns(10);

        JLabel lblEborn = new JLabel("出生日期：");
        lblEborn.setBounds(550, 230, 83, 16);
        getContentPane().add(lblEborn);

        ebornField = new JTextField();
        ebornField.setBounds(650, 225, 163, 26);
        getContentPane().add(ebornField);
        ebornField.setColumns(10);

        JLabel lblEentry = new JLabel("入职日期：");
        lblEentry.setBounds(550, 280, 83, 16);
        getContentPane().add(lblEentry);

        eentryField = new JTextField();
        eentryField.setBounds(650, 275, 163, 26);
        getContentPane().add(eentryField);
        eentryField.setColumns(10);

        JLabel lblEsalary = new JLabel("薪水：");
        lblEsalary.setBounds(550, 330, 61, 16);
        getContentPane().add(lblEsalary);

        esalaryField = new JTextField();
        esalaryField.setBounds(650, 325, 163, 26);
        getContentPane().add(esalaryField);
        esalaryField.setColumns(10);

        JLabel lblEauthority = new JLabel("权限：");
        lblEauthority.setBounds(550, 380, 61, 16);
        getContentPane().add(lblEauthority);

        eauthorityField = new JTextField();
        eauthorityField.setBounds(650, 375, 163, 26);
        getContentPane().add(eauthorityField);
        eauthorityField.setColumns(10);

        JButton btnSave = new JButton("保存修改");
        
        
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "请选择要修改的员工", "提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // 获取文本框中的员工信息
                String name = enameField.getText();
                String number = enumberField.getText();
                String sex = esexField.getText();
                String born = ebornField.getText();
                String entry = eentryField.getText();
                int salary = Integer.parseInt(esalaryField.getText());
                String authority = eauthorityField.getText();

                // 创建员工对象
                Ee employee = new Ee();

                try {
                    // 设置员工信息
                    employee.setEname(name);
                    employee.setEnumber(number);
                    employee.setEsex(sex);
                    employee.setEborn(born);
                    employee.setEentry(entry);
                    employee.setEsalary(salary);
                    // 设置权限时捕获异常
                    employee.setEauthority(authority);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "参数错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int option = JOptionPane.showConfirmDialog(null, "是否保存修改？", "确认", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    try {
                        opService.updateEmployee(employee);
                        JOptionPane.showMessageDialog(null, "员工信息更新成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "员工信息更新失败", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        btnSave.setBounds(650, 425, 117, 29);
        getContentPane().add(btnSave);

     // 添加表格的选中事件监听器
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // 获取选中行的员工工号
                        String enumber = (String) tableModel.getValueAt(selectedRow, 1);
                        try {
                            Ee employee = opService.findEmployeeByNumber(enumber);
                            if (employee != null) {
                                // 填充右侧文本框
                                enameField.setText(employee.getEname());
                                enumberField.setText(employee.getEnumber());
                                esexField.setText(employee.getEsex());
                                ebornField.setText(employee.getEborn());
                                eentryField.setText(employee.getEentry());
                                esalaryField.setText(String.valueOf(employee.getEsalary()));
                                eauthorityField.setText(employee.getEauthority());
                            } else {
                                JOptionPane.showMessageDialog(null, "未找到该员工", "提示", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "参数错误", JOptionPane.ERROR_MESSAGE);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "查找员工失败", "错误", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });


        
        JLabel lblSearch = new JLabel("搜索工号：");
        lblSearch.setFont(new Font("宋体", Font.PLAIN, 24));
        lblSearch.setBounds(32, 515, 151, 29);
        getContentPane().add(lblSearch);
        
        searchField = new JTextField();
        searchField.setBounds(173, 517, 150, 29);
        getContentPane().add(searchField);
        searchField.setColumns(10);
        
        JButton btnSearch = new JButton("搜索");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enumberToSearch = searchField.getText();
                if (!enumberToSearch.isEmpty()) {
                    for (int i = 0; i < table.getRowCount(); i++) {
                        if (table.getValueAt(i, 1).equals(enumberToSearch)) {
                            table.setRowSelectionInterval(i, i);
                            table.scrollRectToVisible(table.getCellRect(i, 0, true));
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "未找到工号为 " + enumberToSearch + " 的员工", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "请输入要搜索的工号", "提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnSearch.setBounds(352, 518, 117, 29);
        getContentPane().add(btnSearch);
        
        // 加载员工列表
        loadEmployeeList();
    }

    private void loadEmployeeList() {
        try {
            // 获取所有员工信息
            List<Ee> employees = opService.findAllEmployees();
            // 将员工信息添加到表格模型中
            for (Ee employee : employees) {
                tableModel.addRow(new Object[] { employee.getEname(), employee.getEnumber() });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "加载员工列表失败", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}

