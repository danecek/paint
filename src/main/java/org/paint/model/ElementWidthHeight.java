/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.paint.model;

import java.awt.Color;

/**
 *
 * @author danecek
 */
public abstract class ElementWidthHeight extends Element {
    
    private int width;
    private int height;

    public ElementWidthHeight() {
    }

    public ElementWidthHeight(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public ElementWidthHeight(Long id, Pos ref, Color color, boolean fill, MyStroke myStroke, int width, int height) {
        super(id, ref, color, fill, myStroke, width, height);
        this.width = width;
        this.height = height;
    }

    public ElementWidthHeight(Pos ref, Color color, boolean fill, MyStroke myStroke, int width, int height) {
        super(ref, color, fill, myStroke, width, height);
        this.width = width;
        this.height = height;
    }

    /**
     * @return the width
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
}
