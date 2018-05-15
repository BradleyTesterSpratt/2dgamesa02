package com.allsopg.game.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

/**
 * Created by bst19 on 18/02/2018.
 */

public class BitmapText
{
    private BitmapFont font;
    private String text;
    private static GlyphLayout glyphLayout = new GlyphLayout();

    /**
     * Constructor for Bitmap Text
     *
     * @param text text to be displayed
     * @param color color of the text
     * @param tWidth width of the text
     * @param hAlign int to set horizontal alignment of the text
     * @param singleLine boolean to set the text to truncate if it is a single line
     */
    public BitmapText(String text, Color color, float tWidth, int hAlign, boolean singleLine)
    {
        font = new BitmapFont(Gdx.files.internal("font/infinite.fnt"));
        this.text=text;
        glyphLayout.setText(font,text, color,tWidth,hAlign,singleLine);
    }

    /**
     * accessor for font
     * @return font assigned to the BitmapFont class
     */
    public BitmapFont getFont() { return font; }

    /**
     * accessor for the bitmap fonts glyph layout
     * @return returns glyphLayout belonging to BitmapFont class
     */
    public GlyphLayout getGlyphLayout() { return glyphLayout; }
}
