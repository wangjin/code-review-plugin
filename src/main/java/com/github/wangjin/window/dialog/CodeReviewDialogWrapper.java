package com.github.wangjin.window.dialog;

import com.github.wangjin.window.ReviewFormWindow;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.Vector;

/**
 * @author Jin Wang
 * @version 1.0
 * @date 2021-07-02 10:06 下午
 */
public class CodeReviewDialogWrapper extends DialogWrapper {

    private Project project;
    private JTable dataTable;
    private ReviewFormWindow reviewFormWindow;

    private Vector<String> vName;

    private Vector<Vector> vData;

    public CodeReviewDialogWrapper(@Nullable Project project, JTable dataTable, Vector<String> vName, Vector<Vector> vData) {
        super(project);
        this.project = project;
        this.dataTable = dataTable;
        this.vName = vName;
        this.vData = vData;
        this.setTitle("编辑走查问题");
        this.init();
    }


    @Override
    protected @Nullable JComponent createCenterPanel() {
        if (reviewFormWindow == null) {
            reviewFormWindow = new ReviewFormWindow();
            reviewFormWindow.setClassName(getCurrentClassName());
            reviewFormWindow.setLineNumber(getCurrentLineNumber());
        }
        return reviewFormWindow.getContent();
    }

    @Override
    protected Action @NotNull [] createActions() {
        return new Action[]{
                new DialogWrapperAction("确定") {
                    @Override
                    protected void doAction(ActionEvent e) {
                        try {
//                            DefaultTableModel model = (DefaultTableModel) dataTable.getModel();

                            Vector<String> vRow = new Vector<>();
                            vRow.add(reviewFormWindow.getClassName());
                            vRow.add(reviewFormWindow.getLineNumber());
                            vRow.add(reviewFormWindow.getIssue());
                            vData.add(vRow);
                            DefaultTableModel model = new DefaultTableModel(vData, vName);
                            dataTable.setModel(model);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        doCancelAction();
                    }
                },
                new DialogWrapperAction("关闭") {
                    @Override
                    protected void doAction(ActionEvent e) {
                        doCancelAction();
                    }
                }};
    }

    @Override
    protected @Nullable ValidationInfo doValidate() {
        return super.doValidate();
    }

    private int getCurrentLineNumber() {
        FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        // 获取editor
        Editor editor = fileEditorManager.getSelectedTextEditor();
        if (editor != null) {
            // 获取逻辑位置
            LogicalPosition logicalPosition = editor.getCaretModel().getLogicalPosition();
            // 设置行数
            return logicalPosition.line + 1;
        } else {
            return 0;
        }
    }

    public String getCurrentClassName() {
        FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        // 获取fileEditor
        FileEditor fileEditor = fileEditorManager.getSelectedEditor();
        if (fileEditor != null) {
            // 设置名称
            VirtualFile virtualFile = fileEditor.getFile();
            if (virtualFile != null) {
                return virtualFile.getName();
            }
        }
        return "";
    }
}
