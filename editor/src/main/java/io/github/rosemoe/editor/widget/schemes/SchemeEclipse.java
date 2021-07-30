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
 * picked from Eclipse IDE for Java Developers Version 2019-12 (4.14.0)
 * Thanks to liyujiang-gzu (GitHub @liyujiang-gzu)
 */
public final class SchemeEclipse extends EditorColorScheme {

    @Override
    public void applyDefault() {
        super.applyDefault();
        setColor(ANNOTATION, 0xff646464);
        setColor(FUNCTION_NAME, 0xff000000);
        setColor(IDENTIFIER_NAME, 0xff000000);
        setColor(IDENTIFIER_VAR, 0xffb8633e);
        setColor(LITERAL, 0xff2a00ff);
        setColor(OPERATOR, 0xff3a0000);
        setColor(COMMENT, 0xff3f7f5f);
        setColor(KEYWORD, 0xff7f0074);
        setColor(WHOLE_BACKGROUND, 0xffffffff);
        setColor(TEXT_NORMAL, 0xff000000);
        setColor(LINE_NUMBER_BACKGROUND, 0xffffffff);
        setColor(LINE_NUMBER, 0xff787878);
        setColor(SELECTED_TEXT_BACKGROUND, 0xff3399ff);
        setColor(MATCHED_TEXT_BACKGROUND, 0xffd4d4d4);
        setColor(CURRENT_LINE, 0xffe8f2fe);
        setColor(SELECTION_INSERT, 0xff03ebeb);
        setColor(SELECTION_HANDLE, 0xff03ebeb);
        setColor(BLOCK_LINE, 0xffd8d8d8);
        setColor(BLOCK_LINE_CURRENT, 0);
    }

}
