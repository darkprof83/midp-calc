package midpcalc;

import javax.microedition.lcdui.Graphics;

final class GFont extends UniFont {

    private final GFontData data;
    private final boolean needLargeCache;
    
    private int overlineThickness;

    public void close() {
        data.close(needLargeCache);
        if (smallerFont != null) {
            UniFont f = smallerFont;
            smallerFont = null;
            f.close();
        }
    }

    GFont(int style, boolean needLargeCache, CanvasAccess canvas) {
        super(style);
        boolean sizeX2 = false;
        
        data = GFontData.getGFontData(style, needLargeCache, canvas);
        this.needLargeCache = needLargeCache;
        
        switch (size) {
            case SMALL: default:
                charMaxWidth = GFontBase.smallCharMaxWidth;
                charHeight = GFontBase.smallCharHeight;
                baselinePosition = GFontBase.smallBaselinePosition;
                overlineThickness = GFontBase.smallOverlineThickness;
                break;
            case MEDIUM:
                charMaxWidth = GFontBase.mediumCharMaxWidth;
                charHeight = GFontBase.mediumCharHeight;
                baselinePosition = GFontBase.mediumBaselinePosition;
                overlineThickness = GFontBase.mediumOverlineThickness;
                break;
            case LARGE: case XXLARGE:
                charMaxWidth = GFontBase.largeCharMaxWidth;
                charHeight = GFontBase.largeCharHeight;
                baselinePosition = GFontBase.largeBaselinePosition;
                overlineThickness = GFontBase.largeOverlineThickness;
                sizeX2 = size==XXLARGE;
                break;
            case XLARGE: case XXXLARGE:
                charMaxWidth = GFontBase.xlargeCharMaxWidth;
                charHeight = GFontBase.xlargeCharHeight;
                baselinePosition = GFontBase.xlargeBaselinePosition;
                overlineThickness = GFontBase.xlargeOverlineThickness;
                sizeX2 = size==XXXLARGE;
                break;
        }
        if (sizeX2) {
            charMaxWidth *= 2;
            charHeight *= 2;
            baselinePosition = baselinePosition*2;
            overlineThickness = overlineThickness*2;
        }
    }

    public int getHeight() {
        if (smallerFont != null)
            return charHeight +
                   smallerFont.charHeight - smallerFont.baselinePosition + 1;
        return charHeight;
    }

    private int drawSpecialChar(Graphics g, int x, int y, char ch) {
        
        if (processChar(ch, false))
            return 0;

        GFont gFont = (subscript || superscript) ? (GFont)smallerFont : this;

        if (!superscript && smallerFont != null) {
            y ++;
        }
        if (subscript) {
            y += charHeight-smallerFont.baselinePosition;
        }

        int fg_col = ch=='�' ? Colors.GREEN : (bold ? Colors.EMPHASIZED : fg);
        int w = gFont.data.drawGFontChar(g, x, y, ch, fg_col, bg, monospaced, bold, italic);
        if (overline) {
            g.setColor(Colors.c[fg_col]);
            g.fillRect(x-1, y, w+1, overlineThickness);
        }
        if (ch == '�')
            overline = true;
        return w;
    }

    public int charWidth(char ch) {
        return monospaced ?  charMaxWidth : data.charWidth[ch];
    }

    public int substringWidth(String string, int start, int end) {
        if (monospaced) {
            // NB! Assuming that special characters will not be drawn monospaced
            return charMaxWidth * (end - start);
        }

        GFont font = this;

        int width = 0;
        for (int i = 0; i < end && i < string.length(); i++) {
            char c = string.charAt(i);
            int charWidth = 0;
            if (c == '^' || c == '�')
                font = font == this ? (GFont)smallerFont : this;
            else if (c == '~' || c == '`')
                ; // overline... no font change
            else
                charWidth = font.charWidth(c);
            if (i >= start)
                width += charWidth;
        }
        return width;
    }

    public int drawSubstring(Graphics g, int x, int y, String string, int start, int end) {
        if (end > string.length())
            end = string.length();

        italic = subscript = superscript = overline = false;
        
        data.getClip(g);
        if (smallerFont != null && smallerFont != this) {
            ((GFont)smallerFont).data.getClip(g);
        }
        for (int i = 0; i < start; i++) {
            processChar(string.charAt(i), true);
        }
        for (int i = start; i < end; i++) {
            x += drawSpecialChar(g, x, y, string.charAt(i));
        }
        data.restoreClip(g);
        return x;
    }

    public int drawString(Graphics g, int x, int y, StringBuffer string, int start) {
        int end = string.length();
        
        subscript = superscript = overline = false;
        
        data.getClip(g);
        if (smallerFont != null && smallerFont != this) {
            ((GFont)smallerFont).data.getClip(g);
        }
        for (int i = 0; i < start; i++) {
            processChar(string.charAt(i), true);
        }
        for (int i = start; i < end; i++) {
            x += drawSpecialChar(g, x, y, string.charAt(i));
        }
        data.restoreClip(g);
        return x;
    }
}
