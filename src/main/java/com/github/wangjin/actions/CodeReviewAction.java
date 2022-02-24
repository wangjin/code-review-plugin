package com.github.wangjin.actions;

import com.github.wangjin.window.dialog.CodeReviewDialogWrapper;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CodeReviewAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        ToolWindow toolWindow = ToolWindowManager.getInstance(Objects.requireNonNull(e.getProject())).getToolWindow("代码走查");
        assert toolWindow != null;
        Content content = toolWindow.getContentManager().getContent(0);

       /* if (new CodeReviewDialogWrapper(e.getProject(), null).showAndGet()) {

        }*/
    }
}
