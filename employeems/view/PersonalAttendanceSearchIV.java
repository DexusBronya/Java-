package eems.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import eems.impl.OpServiceImpl;
import eems.util.TimeUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

public class PersonalAttendanceSearchIV extends JInternalFrame {

    private JTextField textFieldYearMonth;
    private JTable table;
    private JLabel lblAttendanceCount;

    /**
     * Create the frame.
     */
    public PersonalAttendanceSearchIV() {
        setTitle("个人考勤查询");
        setClosable(true);
        setBounds(100, 100, 918, 715);
        getContentPane().setLayout(null);

        // 输入框，默认为当前年月
        textFieldYearMonth = new JTextField();
        textFieldYearMonth.setBounds(31, 26, 154, 34);
        getContentPane().add(textFieldYearMonth);
        textFieldYearMonth.setColumns(10);
        
        // 查询按钮
        JButton btnSearch = new JButton("查询");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enumber = LoginView.ee.getEnumber(); // 这里需要获取员工工号，你可以根据你的具体逻辑获取
                String yearMonth = textFieldYearMonth.getText().trim();
                if (!yearMonth.matches("\\d{4}-\\d{2}")) {
                    JOptionPane.showMessageDialog(null, "请输入正确的年月格式，如\"2024-03\"", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    OpServiceImpl opService = new OpServiceImpl();
                    updateAttendanceCount(enumber, yearMonth);
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "查询失败！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnSearch.setBounds(197, 26, 123, 34);
        getContentPane().add(btnSearch);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(31, 85, 840, 500);
        getContentPane().add(scrollPane);

        // 表格
        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"日期", "上班状态", "下班状态"}
        ));

        // 显示打卡缺勤或迟到次数
        lblAttendanceCount = new JLabel("");
        lblAttendanceCount.setBounds(196, 620, 194, 29);
        getContentPane().add(lblAttendanceCount);
    }

    // 更新打卡缺勤或迟到次数
    private void updateAttendanceCount(String enumber, String yearMonth) throws SQLException {
        OpServiceImpl opService = new OpServiceImpl();
        List<Object[]> resultList = opService.getAttendanceByEnumber(enumber, yearMonth);
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // 清空表格
        
        int absenceCount = 0; // 计算缺勤次数
        int lateCount = 0; // 计算迟到次数
        
        LocalDate currentDate = LocalDate.now();

        // 获取当前月份的天数
        int daysInMonth = currentDate.lengthOfMonth();

        int timeCount = resultList.size();

        for (Object[] row : resultList) {
            model.addRow(row);
            
/*            // 判断是否缺勤
            if (row[1].equals("未打卡") || row[2].equals("未打卡")) {
                lateCount++;
            } else if (row[1].equals("已打卡") && row[2].equals("已打卡")) {
                // 如果当天只有一次打卡，则也算作缺勤
                lateCount++;
            }
        */
        
            String time = (String)row[0]; // 获取日期时间字符串
            String[] parts = time.split(" ");
            String timeq = parts[1].split("\\.")[0]; // 去除毫秒部分            System.out.println(timeq);

            // 判断是否在上午打卡时间区间内
            if (!TimeUtil.isMorningCheckInTime(timeq) && !TimeUtil.isAfternoonCheckInTime(timeq)) {
                lateCount++;
            }
            

    }
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 24));
        lblNewLabel.setBounds(335, 26, 383, 34);
        getContentPane().add(lblNewLabel);
        lblNewLabel.setText("缺勤次数：" + absenceCount + "，迟到次数：" + lateCount);    }
}
