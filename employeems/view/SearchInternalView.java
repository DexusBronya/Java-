package eems.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import eems.pojo.Ee;
import eems.pojo.Eecheck;
import eems.service.EeService;
import eems.service.OpService;
import eems.impl.EeServiceImpl;
import eems.impl.OpServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchInternalView extends JInternalFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private OpService opService;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SearchInternalView frame = new SearchInternalView();
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
    public SearchInternalView() {
        setTitle("测试internal");
        setBounds(100, 100, 800, 600); // 修改窗口大小
        getContentPane().setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 50, 700, 450); // 修改 JScrollPane 的大小
        getContentPane().add(scrollPane);
        
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {"工号","时间", "上班考勤", "下班考勤"}
        );
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);
        
        textField = new JTextField();
        textField.setBounds(268, 18, 171, 27);
        getContentPane().add(textField);
        textField.setColumns(10);
        
        LocalDate now = LocalDate.now();
        // 将当前日期转换为 java.sql.Date 对象
        Date today = Date.valueOf(now);
        textField.setText(today.toString());
        
        JButton button = new JButton("查询");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 清空表格数据
                clearTable();
                
                // 获取数据并添加到表格中
                
                try {
                    List<Eecheck> attendanceStatusList = opService.getAttendanceStatusByDate(textField.getText());
                    for (Eecheck eecheck : attendanceStatusList) {
                        String goonworkStatus = eecheck.getGoonwork().equals("1") ? "已打卡" : "未打卡";
                        String gooffworkStatus = eecheck.getGooffwork().equals("1") ? "已打卡" : "未打卡";
                        addRowToTable(eecheck.getEnumber(), eecheck.getDate(), goonworkStatus, gooffworkStatus);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button.setBounds(474, 17, 123, 29);
        getContentPane().add(button);
        
        JLabel label = new JLabel("输入格式2024-3-24");
        label.setBounds(65, 21, 188, 21);
        getContentPane().add(label);

        // 初始化 OpService
        opService = new OpServiceImpl();
    }

    // 添加行到表格中
    public void addRowToTable(String enumber, String time, String goonwork, String gooffwork) {
        Object[] row = {enumber, time, goonwork, gooffwork};
        tableModel.addRow(row);
    }
    
    // 清空表格数据
    public void clearTable() {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
    }
}
