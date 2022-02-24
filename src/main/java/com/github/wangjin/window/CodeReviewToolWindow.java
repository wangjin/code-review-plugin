package com.github.wangjin.window;

import com.github.wangjin.window.dialog.CodeReviewDialogWrapper;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.util.Vector;

/**
 * @author Jin Wang
 * @version 1.0
 * @date 2021-07-02 7:20 下午
 */
public class CodeReviewToolWindow {
    private JButton exportBtn;
    private JPanel mainPanel;
    private JTable dataTable;
    private JButton clearBtn;
    private JButton addBtn;

    private Vector<String> vName = new Vector<>();
    private Vector<Vector> vData = new Vector<>();


    public CodeReviewToolWindow(Project project, ToolWindow toolWindow) {

        TableColumnModel columnModel = dataTable.getTableHeader().getColumnModel();

        // 添加列
        TableColumn tableColumn = new TableColumn();
        tableColumn.setHeaderValue("类名");
        tableColumn.setPreferredWidth(100);
        tableColumn.setMinWidth(100);
        columnModel.addColumn(tableColumn);

        tableColumn = new TableColumn();
        tableColumn.setHeaderValue("行数");
        tableColumn.setPreferredWidth(50);
        columnModel.addColumn(tableColumn);

        tableColumn = new TableColumn();
        tableColumn.setHeaderValue("问题描述");
        tableColumn.setPreferredWidth(300);
        columnModel.addColumn(tableColumn);


        vName.add("类名");
        vName.add("行数");
        vName.add("问题描述");

        addBtn.addActionListener(e -> {

            if (new CodeReviewDialogWrapper(project, dataTable, vName, vData).showAndGet()) {

            }
        });

        exportBtn.addActionListener(e -> {
        });

        clearBtn.addActionListener(e -> {
            vData.clear();
            dataTable.setModel(new DefaultTableModel(vData, vName));
        });
    }

    public JPanel getContent() {
        return mainPanel;
    }

    public JTable getDataTable() {
        return dataTable;
    }

    ;
}
