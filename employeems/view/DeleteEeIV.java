package eems.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import eems.impl.OpServiceImpl;
import eems.pojo.Ee;
import java.awt.Font;

public class DeleteEeIV extends JInternalFrame {

    private OpServiceImpl opService;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField searchField;

    /**
     * Create the frame.
     */
    public DeleteEeIV() {
        setTitle("删除员工");
        setBounds(100, 100, 806, 706);
        getContentPane().setLayout(null);

        opService = new OpServiceImpl();

        JLabel lblNewLabel = new JLabel("员工列表：");
        lblNewLabel.setBounds(32, 26, 95, 16);
        getContentPane().add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(32, 54, 437, 349);
        getContentPane().add(scrollPane);

        // 创建表格模型
        tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "员工姓名", "员工编号" });
        table = new JTable(tableModel);
        table.setRowHeight(30); // 设置行高为30像素
        scrollPane.setViewportView(table);

        JButton btnDelete = new JButton("删除");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "请选择要删除的员工", "提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String ename = (String) tableModel.getValueAt(selectedRow, 0);
                String enumber = (String) tableModel.getValueAt(selectedRow, 1);
                int dialogResult = JOptionPane.showConfirmDialog(null, "确定要删除员工 " + ename + " 吗？", "确认删除", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    try {
                        int result = opService.deleteEmployee(enumber);
                        if (result > 0) {
                            tableModel.removeRow(selectedRow);
                            JOptionPane.showMessageDialog(null, "员工删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "员工删除失败", "错误", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "员工删除失败", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        btnDelete.setBounds(527, 198, 166, 55);
        getContentPane().add(btnDelete);

        JLabel lblSearch = new JLabel("搜索工号：");
        lblSearch.setFont(new Font("宋体", Font.PLAIN, 24));
        lblSearch.setBounds(112, 426, 148, 55);
        getContentPane().add(lblSearch);

        searchField = new JTextField();
        searchField.setBounds(245, 428, 224, 55);
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
        btnSearch.setBounds(527, 428, 166, 55);
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
