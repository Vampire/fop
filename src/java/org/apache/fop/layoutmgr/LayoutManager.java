/*
 * Copyright 1999-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* $Id$ */
 
package org.apache.fop.layoutmgr;

import java.util.Map;

import org.apache.fop.fo.flow.Marker;

import org.apache.fop.area.Area;
import org.apache.fop.area.Resolveable;
import org.apache.fop.area.PageViewport;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.fo.FObj;

/**
 * The interface for all LayoutManagers.
 */
public interface LayoutManager {

    /**
     * Set the FO object for this layout manager.
     * For layout managers that are created without an FO
     * this may not be called.
     *
     * @param obj the FO object for this layout manager
     */
    void setFObj(FObj obj);

    /**
     * Set the user agent. For resolving user agent values.
     *
     * @param ua the user agent
     */
    void setUserAgent(FOUserAgent ua);

    /**
     * Get the user agent.
     *
     * @return the user agent
     */
    FOUserAgent getUserAgent();
    
    /**
     * Set the parent layout manager.
     * The parent layout manager is required for adding areas.
     *
     * @param lm the parent layout manager
     */
    void setParent(LayoutManager lm);

    /**
     * Get the parent layout manager.
     * @return the parent layout manager.
     */
    LayoutManager getParent();

    /**
     * Get the LayoutManagerLS object that is at the top of the LM Tree
     * @return the LayoutManagerLS object that is at the top of the LM Tree
     */
    LayoutManagerLS getLayoutManagerLS();

    /**
     * Initialize this layout manager.
     */
    void initialize();

    /**
     * Generates inline areas.
     * This is used to check if the layout manager generates inline
     * areas.
     *
     * @return true if the layout manager generates inline areas
     */
    boolean generatesInlineAreas();

    /**
     * Return true if the next area which would be generated by this
     * LayoutManager could start a new line (or flow for block-level FO).
     *
     * @param lc the layout context
     * @return true if can break before
     */
    boolean canBreakBefore(LayoutContext lc);

    /**
     * Generate and return the next break possibility.
     *
     * @param context The layout context contains information about pending
     * space specifiers from ancestor areas or previous areas, reference
     * area inline-progression-dimension and various other layout-related
     * information.
     * @return the next break position
     */
    BreakPoss getNextBreakPoss(LayoutContext context);

    /**
     * Reset to the position.
     *
     * @param position
     */
    void resetPosition(Position position);

    /**
     * Get the word chars between two positions and
     * append to the string buffer. The positions could
     * span multiple layout managers.
     *
     * @param sbChars the string buffer to append the word chars
     * @param bp1 the start position
     * @param bp2 the end position
     */
    void getWordChars(StringBuffer sbChars, Position bp1,
                             Position bp2);

    /**
     * Return a value indicating whether this LayoutManager has laid out
     * all its content (or generated BreakPossibilities for all content.)
     *
     * @return true if this layout manager is finished
     */
    boolean isFinished();

    /**
     * Set a flag indicating whether the LayoutManager has laid out all
     * its content. This is generally called by the LM itself, but can
     * be called by a parentLM when backtracking.
     *
     * @param isFinished the value to set the finished flag to
     */
    void setFinished(boolean isFinished);

    /**
     * Get the parent area for an area.
     * This should get the parent depending on the class of the
     * area passed in.
     *
     * @param childArea the child area to get the parent for
     * @return the parent Area
     */
    Area getParentArea(Area childArea);

    /**
     * Add the area as a child of the current area.
     * This is called by child layout managers to add their
     * areas as children of the current area.
     *
     * @param childArea the child area to add
     */
    void addChild(Area childArea);

    /**
     * Tell the layout manager to add all the child areas implied
     * by Position objects which will be returned by the
     * Iterator.
     *
     * @param posIter the position iterator
     * @param context the context
     */
    void addAreas(PositionIterator posIter, LayoutContext context);

    /**
     * Get the string of the current page number.
     *
     * @return the string for the current page number
     */
    String getCurrentPageNumber();

    /**
     * Resolve the id reference.
     * This is called by an area looking for an id reference.
     * If the id reference is not found then it should add a resolveable object.
     *
     * @param ref the id reference
     * @return the page containing the id reference or null if not found
     */
    PageViewport resolveRefID(String ref);

    /**
     * Add an id to the page.
     * (todo) add the location of the area on the page
     *
     * @param id the id reference to add.
     */
    void addIDToPage(String id);

    /**
     * Add an unresolved area.
     * The is used to add a resolveable object to the page for a given id.
     *
     * @param id the id reference this object needs for resolving
     * @param res the resolveable object
     */
    void addUnresolvedArea(String id, Resolveable res);

    /**
     * Add the marker.
     * A number of formatting objects may contain markers. This
     * method is used to add those markers to the page.
     *
     * @param name the marker class name
     * @param start true if the formatting object is starting false is finishing
     * @param isfirst a flag for is first
     */
    void addMarkerMap(Map marks, boolean start, boolean isfirst);

    /**
     * Retrieve a marker.
     * This method is used when retrieve a marker.
     *
     * @param name the class name of the marker
     * @param pos the retrieve position
     * @param boundary the boundary for retrieving the marker
     * @return the layout manaager of the retrieved marker if any
     */
    Marker retrieveMarker(String name, int pos, int boundary);

}
