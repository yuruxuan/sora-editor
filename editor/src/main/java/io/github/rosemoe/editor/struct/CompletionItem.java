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
package io.github.rosemoe.editor.struct;

import android.graphics.drawable.Drawable;

import java.util.Comparator;

/**
 * The class used to save auto complete result items
 *
 * @author Rose
 */
@SuppressWarnings("CanBeFinal")
public class CompletionItem {

    public final static Comparator<CompletionItem> COMPARATOR_BY_NAME = (p1, p2) -> p1.label.compareTo(p2.label);

    /**
     * Icon for displaying in adapter
     */
    public Drawable icon;

    /**
     * Text to commit when selected
     */
    public String commit;

    /**
     * Text to display as title in adapter
     */
    public String label;

    /**
     * Text to display as description in adapter
     */
    public String desc;

    /**
     * Cursor offset in {@link CompletionItem#commit}
     */
    public int cursorOffset;

    public CompletionItem(String str, String desc) {
        this(str, desc, (Drawable) null);
    }

    public CompletionItem(String label, String commit, String desc) {
        this(label, commit, desc, null);
    }

    public CompletionItem(String label, String desc, Drawable icon) {
        this(label, label, desc, icon);
    }

    public CompletionItem(String label, String commit, String desc, Drawable icon) {
        this.label = label;
        this.commit = commit;
        this.desc = desc;
        this.icon = icon;
        cursorOffset = commit.length();
    }

    public CompletionItem shiftCount(int shiftCount) {
        return cursorOffset(commit.length() - shiftCount);
    }

    public CompletionItem cursorOffset(int offset) {
        if (offset < 0 || offset > commit.length()) {
            throw new IllegalArgumentException();
        }
        cursorOffset = offset;
        return this;
    }

}

