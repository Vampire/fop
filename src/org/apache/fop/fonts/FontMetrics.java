/*
 * $Id$
 * Copyright (C) 2001-2003 The Apache Software Foundation. All rights reserved.
 * For details on use and redistribution please refer to the
 * LICENSE file included with these sources.
 */

package org.apache.fop.fonts;

import java.util.Map;


/**
 * Main interface for access to font metrics.
 */
public interface FontMetrics {

    /**
     * Returns the font name.
     * @return the font name
     */
    String getFontName();
    
    
    /**
     * Returns the type of the font.
     * @return the font type
     */
    FontType getFontType();
    

    /**
     * Returns the ascent of the font described by this
     * FontMetrics object.
     * @param size font size
     * @return ascent in milliponts
     */
    int getAscender(int size);
    
    /**
     * Returns the size of a capital letter measured from the font's baseline.
     * @param size font size
     * @return height of capital characters
     */
    int getCapHeight(int size);
    
    
    /**
     * Returns the descent of the font described by this
     * FontMetrics object.
     * @param size font size
     * @return descent in milliponts
     */
    int getDescender(int size);
    
    
    /**
     * Determines the typical font height of this
     * FontMetrics object
     * @param size font size
     * @return font height in millipoints
     */
    int getXHeight(int size);

    /**
     * Return the width (in 1/1000ths of point size) of the character at
     * code point i.
     * @param i code point index
     * @param size font size
     * @return the width of the character
     */
    int getWidth(int i, int size);

    /**
     * Return the array of widths.
     * <p>
     * This is used to get an array for inserting in an output format.
     * It should not be used for lookup.
     * @return an array of widths
     */
    int[] getWidths();
    
    /**
     * Indicates if the font has kering information.
     * @return True, if kerning is available.
     */
    boolean hasKerningInfo();
        
    /**
     * Returns the kerning map for the font.
     * @return the kerning map
     */
    Map getKerningInfo();
    
}
