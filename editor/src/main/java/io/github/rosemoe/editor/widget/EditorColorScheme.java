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
package io.github.rosemoe.editor.widget;

import android.util.SparseIntArray;

import io.github.rosemoe.editor.util.Objects;

/**
 * This class manages the colors of editor.
 * You can use color ids that are not in pre-defined id pool due to new languages.
 * <p>
 * This is also the default color scheme of editor.
 * Be careful to change this class, because this can cause its
 * subclasses behave differently and some subclasses did not apply
 * their default colors to some color ids. So change to this can cause
 * sub themes to change as well.
 * <p>
 * Typically, you can use this class to set color of editor directly
 * with {@link #setColor(int, int)} in a thread with looper.
 * <p>
 * However, we also accept you to extend this class to customize
 * your own ColorScheme to use different default colors.
 * Subclasses is expected to override {@link #applyDefault()}
 * to define colors, though other methods are not final.
 * After overriding this method, you will have to call super class's
 * applyDefault() and then a series of {@link #setColor(int, int)} calls
 * to apply your colors.
 * <p>
 * Note that new colors can be added in newer version of editor,
 * it is dangerous not to call super.applyDefault(), which can cause
 * newer editor works wrongly.
 * <p>
 * For more pre-defined color schemes, please turn to package io.github.rosemoe.editor.widget.schemes
 * <p>
 * Thanks to liyujiang-gzu (GitHub @liyujiang-gzu) for contribution to color schemes
 *
 * @author Rose
 */
public class EditorColorScheme {
    //-----------------Highlight colors-----------

    public static final int ATTRIBUTE_VALUE = 33;
    public static final int ATTRIBUTE_NAME = 32;
    public static final int HTML_TAG = 31;
    public static final int ANNOTATION = 28;
    public static final int FUNCTION_NAME = 27;
    public static final int IDENTIFIER_NAME = 26;
    public static final int IDENTIFIER_VAR = 25;
    public static final int LITERAL = 24;
    public static final int OPERATOR = 23;
    public static final int COMMENT = 22;
    public static final int KEYWORD = 21;

    //-------------View colors---------------------

    public static final int NON_PRINTABLE_CHAR = 31;
    public static final int TEXT_SELECTED = 30;
    public static final int MATCHED_TEXT_BACKGROUND = 29;
    public static final int AUTO_COMP_PANEL_CORNER = 20;
    public static final int AUTO_COMP_PANEL_BG = 19;

    /**
     * No longer supported
     */
    public static final int LINE_BLOCK_LABEL = 18;

    public static final int LINE_NUMBER_PANEL_TEXT = 17;
    public static final int LINE_NUMBER_PANEL = 16;
    public static final int BLOCK_LINE_CURRENT = 15;
    public static final int BLOCK_LINE = 14;
    public static final int SCROLL_BAR_TRACK = 13;
    public static final int SCROLL_BAR_THUMB_PRESSED = 12;
    public static final int SCROLL_BAR_THUMB = 11;
    public static final int UNDERLINE = 10;
    public static final int CURRENT_LINE = 9;
    public static final int SELECTION_HANDLE = 8;
    public static final int SELECTION_INSERT = 7;
    public static final int SELECTED_TEXT_BACKGROUND = 6;
    public static final int TEXT_NORMAL = 5;
    public static final int WHOLE_BACKGROUND = 4;
    public static final int LINE_NUMBER_BACKGROUND = 3;
    public static final int LINE_NUMBER = 2;
    public static final int LINE_DIVIDER = 1;

    /**
     * Min pre-defined color id
     */
    protected static final int START_COLOR_ID = 1;

    /**
     * Max pre-defined color id
     */
    protected static final int END_COLOR_ID = 31;
    /**
     * Real color saver
     */
    protected final SparseIntArray mColors;
    /**
     * Host editor object
     */
    private CodeEditor mEditor;

    /**
     * Create a new ColorScheme for the given editor
     *
     * @param editor Host editor
     */
    EditorColorScheme(CodeEditor editor) {
        mEditor = editor;
        mColors = new SparseIntArray();
        applyDefault();
    }

    /**
     * For sub classes
     */
    public EditorColorScheme() {
        mColors = new SparseIntArray();
        applyDefault();
    }

    /**
     * Called by editor
     */
    void attachEditor(CodeEditor editor) {
        if (mEditor != null) {
            throw new IllegalStateException("A editor is already attached to this ColorScheme object");
        }
        mEditor = Objects.requireNonNull(editor);
    }

    /**
     * Apply default colors
     */
    public void applyDefault() {
        for (int i = START_COLOR_ID; i <= END_COLOR_ID; i++) {
            applyDefault(i);
        }
    }

    /**
     * Apply default color for the given type
     *
     * @param type The type
     */
    private void applyDefault(int type) {
        int color;
        switch (type) {
            case LINE_DIVIDER:
                color = 0xFFdddddd;
                break;
            case LINE_NUMBER:
                color = 0xFF808080;
                break;
            case LINE_NUMBER_BACKGROUND:
                color = 0xfff0f0f0;
                break;
            case WHOLE_BACKGROUND:
            case LINE_NUMBER_PANEL_TEXT:
            case AUTO_COMP_PANEL_BG:
            case AUTO_COMP_PANEL_CORNER:
            case TEXT_SELECTED:
                color = 0xffffffff;
                break;
            case OPERATOR:
                color = 0xFF0066D6;
                break;
            case TEXT_NORMAL:
                color = 0xFF333333;
                break;
            case SELECTION_INSERT:
                color = 0xFF03EBEB;
                break;
            case UNDERLINE:
                color = 0xff000000;
                break;
            case SELECTION_HANDLE:
                color = 0xff03ebff;
                break;
            case ANNOTATION:
                color = 0xFF03A9F4;
                break;
            case CURRENT_LINE:
                color = 0x10000000;
                break;
            case SELECTED_TEXT_BACKGROUND:
                color = 0xFF9E9E9E;
                break;
            case KEYWORD:
                color = 0xFF2196F3;
                break;
            case COMMENT:
                color = 0xffa8a8a8;
                break;
            case LITERAL:
                color = 0xFF008080;
                break;
            case SCROLL_BAR_THUMB:
                color = 0xffd8d8d8;
                break;
            case SCROLL_BAR_THUMB_PRESSED:
                color = 0xFF27292A;
                break;
            case BLOCK_LINE:
                color = 0xffdddddd;
                break;
            case LINE_BLOCK_LABEL:
            case SCROLL_BAR_TRACK:
                color = 0;
                break;
            case LINE_NUMBER_PANEL:
                color = 0xdd000000;
                break;
            case BLOCK_LINE_CURRENT:
                color = 0xff999999;
                break;
            case IDENTIFIER_VAR:
            case IDENTIFIER_NAME:
            case FUNCTION_NAME:
                color = 0xff333333;
                break;
            case MATCHED_TEXT_BACKGROUND:
                color = 0xffffff00;
                break;
            case NON_PRINTABLE_CHAR:
                color = 0xff505050;
                break;
            default:
                throw new IllegalArgumentException("Unexpected type:" + type);
        }
        setColor(type, color);
    }

    /**
     * Apply a new color for the given type
     *
     * @param type  The type
     * @param color New color
     */
    public void setColor(int type, int color) {
        //Do not change if the old value is the same as new value
        //due to avoid unnecessary invalidate() calls
        int old = getColor(type);
        if (old == color) {
            return;
        }

        mColors.put(type, color);

        //Notify the editor
        if (mEditor != null) {
            mEditor.onColorUpdated(type);
        }
    }

    /**
     * Get color by type
     *
     * @param type The type
     * @return The color for type
     */
    public int getColor(int type) {
        return mColors.get(type);
    }

}
