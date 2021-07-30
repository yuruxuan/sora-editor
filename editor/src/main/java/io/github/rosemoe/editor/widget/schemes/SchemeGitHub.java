/*
 *    CodeEditor - the awesome code editor for Android
 *    Copyright (C) 2020-2021  Rosemoe
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *     Please contact Rosemoe by email roses2020@qq.com if you need
 *     additional information or have any questions
 */
package io.github.rosemoe.editor.widget.schemes;

import io.github.rosemoe.editor.widget.EditorColorScheme;

/**
 * ColorScheme for editor
 * picked from GitHub site
 * Thanks to liyujiang-gzu (GitHub @liyujiang-gzu)
 */
public final class SchemeGitHub extends EditorColorScheme {

    @Override
    public void applyDefault() {
        super.applyDefault();
        setColor(ANNOTATION, 0xff6f42c1);
        setColor(FUNCTION_NAME, 0xff24292e);
        setColor(IDENTIFIER_NAME, 0xff24292e);
        setColor(IDENTIFIER_VAR, 0xff24292e);
        setColor(LITERAL, 0xff032f62);
        setColor(OPERATOR, 0xff005cc5);
        setColor(COMMENT, 0xff6a737d);
        setColor(KEYWORD, 0xffde3a49);
        setColor(WHOLE_BACKGROUND, 0xffffffff);
        setColor(TEXT_NORMAL, 0xff24292e);
        setColor(LINE_NUMBER_BACKGROUND, 0xffffffff);
        setColor(LINE_NUMBER, 0xffbec0c1);
        setColor(SELECTION_INSERT, 0xffc7edcc);
        setColor(SELECTION_HANDLE, 0xffc7edcc);
    }

}
