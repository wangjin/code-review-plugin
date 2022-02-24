package com.github.wangjin.window;

import javax.swing.*;

public class ReviewFormWindow {
    private JPanel formPanel;
    private JPanel contentPanel;
    private JTextField classNameTextField;
    private JTextField lineNumberTextField;
    private JTextArea issueTextArea;

    public JPanel getContent() {
        return formPanel;
    }

    public void setClassName(String className) {
        classNameTextField.setText(className);
    }

    public void setLineNumber(int lineNumber) {
        lineNumberTextField.setText(String.valueOf(lineNumber));
    }

    public String getIssueContent() {
        return issueTextArea.getText();
    }

    public String getClassName() {
        return classNameTextField.getText();
    }

    public String getLineNumber() {
        return lineNumberTextField.getText();
    }

    public String getIssue() {
        return issueTextArea.getText();
    }
}
