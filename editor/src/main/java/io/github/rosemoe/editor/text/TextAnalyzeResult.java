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
package io.github.rosemoe.editor.text;

import java.util.ArrayList;
import java.util.List;

import io.github.rosemoe.editor.struct.BlockLine;
import io.github.rosemoe.editor.struct.NavigationItem;
import io.github.rosemoe.editor.struct.Span;
import io.github.rosemoe.editor.widget.EditorColorScheme;

/**
 * The result of analysis
 */
public class TextAnalyzeResult {

    protected final List<BlockLine> mBlocks;
    protected final List<List<Span>> mSpanMap;
    public Object mExtra;
    protected List<NavigationItem> mLabels;
    protected Span mLast;
    protected int mSuppressSwitch = Integer.MAX_VALUE;

    /**
     * Create a new result
     */
    public TextAnalyzeResult() {
        mLast = null;
        mSpanMap = new ArrayList<>(2048);
        mBlocks = new ArrayList<>(1024);
    }

    /**
     * Add a new span if required (colorId is different from last)
     *
     * @param spanLine Line
     * @param column   Column
     * @param colorId  Type
     */
    public void addIfNeeded(int spanLine, int column, int colorId) {
        if (mLast != null && mLast.colorId == colorId) {
            return;
        }
        add(spanLine, Span.obtain(column, colorId));
    }

    /**
     * Add a span directly
     * Note: the line should always >= the line of span last committed
     * if two spans are on the same line, you must add them in order by their column
     *
     * @param spanLine The line position of span
     * @param span     The span
     */
    public void add(int spanLine, Span span) {
        int mapLine = mSpanMap.size() - 1;
        if (spanLine == mapLine) {
            mSpanMap.get(spanLine).add(span);
        } else if (spanLine > mapLine) {
            Span extendedSpan = mLast;
            if (extendedSpan == null) {
                extendedSpan = Span.obtain(0, EditorColorScheme.TEXT_NORMAL);
            }
            while (mapLine < spanLine) {
                List<Span> lineSpans = new ArrayList<>();
                lineSpans.add(extendedSpan.copy().setColumn(0));
                mSpanMap.add(lineSpans);
                mapLine++;
            }
            List<Span> lineSpans = mSpanMap.get(spanLine);
            if (span.column == 0) {
                lineSpans.clear();
            }
            lineSpans.add(span);
        } else {
            throw new IllegalStateException("Invalid position");
        }
        mLast = span;
    }

    /**
     * This method must be called when whole text is analyzed
     *
     * @param line The line is the line last of text
     */
    public void determine(int line) {
        int mapLine = mSpanMap.size() - 1;
        Span extendedSpan = mLast;
        if (mLast == null) {
            extendedSpan = Span.obtain(0, EditorColorScheme.TEXT_NORMAL);
        }
        while (mapLine < line) {
            List<Span> lineSpans = new ArrayList<>();
            lineSpans.add(extendedSpan.copy().setColumn(0));
            mSpanMap.add(lineSpans);
            mapLine++;
        }
    }

    /**
     * Get a new BlockLine object
     * <strong>It fields maybe not initialized with zero</strong>
     *
     * @return An idle BlockLine
     */
    public BlockLine obtainNewBlock() {
        return ObjectAllocator.obtainBlockLine();
    }

    /**
     * Add a new code block info
     *
     * @param block Info of code block
     */
    public void addBlockLine(BlockLine block) {
        mBlocks.add(block);
    }

    /**
     * Get list of code blocks
     *
     * @return code blocks
     */
    public List<BlockLine> getBlocks() {
        return mBlocks;
    }

    /**
     * Ensure the list not empty
     */
    public void addNormalIfNull() {
        if (mSpanMap.isEmpty()) {
            List<Span> spanList = new ArrayList<>();
            spanList.add(Span.obtain(0, EditorColorScheme.TEXT_NORMAL));
            mSpanMap.add(spanList);
        }
    }

    /**
     * Get code navigation list
     *
     * @return Current navigation list
     */
    public List<NavigationItem> getNavigation() {
        return mLabels;
    }

    /**
     * Set code navigation list
     *
     * @param navigation New navigation list
     */
    public void setNavigation(List<NavigationItem> navigation) {
        mLabels = navigation;
    }

    /**
     * Returns suppress switch
     *
     * @return suppress switch
     * @see TextAnalyzeResult#setSuppressSwitch(int)
     */
    public int getSuppressSwitch() {
        return mSuppressSwitch;
    }

    /**
     * Set suppress switch for editor
     * What is 'suppress switch' ?:
     * Suppress switch is a switch size for code block line drawing
     * and for the process to find out which code block the cursor is in.
     * Because the code blocks are not saved by the order of both start line and
     * end line,we are unable to know exactly when we should stop the process.
     * So without a suppress switch,it will cost a large of time to search code
     * blocks.So I added this switch.
     * A suppress switch is the code block count in the first layer code block
     * (as well as its sub code blocks).
     * If you are unsure,do not set it.
     * The default value if Integer.MAX_VALUE
     *
     * @param suppressSwitch Suppress switch
     */
    public void setSuppressSwitch(int suppressSwitch) {
        mSuppressSwitch = suppressSwitch;
    }

    /**
     * Get span map
     */
    public List<List<Span>> getSpanMap() {
        return mSpanMap;
    }
}
