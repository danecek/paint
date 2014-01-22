/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.model;

import java.awt.BasicStroke;
import java.awt.Stroke;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author danecek
 */
@Embeddable
public class MyStroke implements Serializable {

    private float strokeWidth = 5;
    private int cap = BasicStroke.CAP_ROUND;
    private int strokeJoin = BasicStroke.JOIN_ROUND;

    public MyStroke() {
    }

    public MyStroke(float width, int cap, int join) {
        this.strokeWidth = width;
        this.cap = cap;
        this.strokeJoin = join;
    }

    /**
     * @return the strokeWidth
     */
    public float getStrokeWidth() {
        return strokeWidth;
    }

    /**
     * @param width the strokeWidth to set
     */
    public void setStrokeWidth(float width) {
        this.strokeWidth = width;
    }

    /**
     * @return the cap
     */
    public int getCap() {
        return cap;
    }

    /**
     * @param cap the cap to set
     */
    public void setCap(int cap) {
        this.cap = cap;
    }

    /**
     * @return the strokeJoin
     */
    public int getStrokeJoin() {
        return strokeJoin;
    }

    /**
     * @param join the strokeJoin to set
     */
    public void setStrokeJoin(int join) {
        this.strokeJoin = join;
    }

    Stroke getStroke() {
        return new BasicStroke(strokeWidth, cap, strokeJoin);
    }

}
