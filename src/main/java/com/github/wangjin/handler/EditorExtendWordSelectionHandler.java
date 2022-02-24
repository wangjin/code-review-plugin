package com.github.wangjin.handler;

import com.intellij.codeInsight.editorActions.ExtendWordSelectionHandler;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EditorExtendWordSelectionHandler implements ExtendWordSelectionHandler {
    @Override
    public boolean canSelect(@NotNull PsiElement e) {
        return true;
    }

    @Override
    public @Nullable List<TextRange> select(@NotNull PsiElement e, @NotNull CharSequence editorText, int cursorOffset, @NotNull Editor editor) {
        List<TextRange> ranges = new ArrayList<>();
        PsiElement element = e;
        do {
            TextRange range = element.getTextRange();
            // add element as is
            ranges.add(range);

            PsiElement startFormatting = element;
            PsiElement endFormatting = element;

            element = element.getParent();
        } while (element != null && !(element instanceof PsiFile));
        return ranges;
    }
}
