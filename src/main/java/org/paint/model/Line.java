/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.model;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import org.paint.App;

/**
 *
 * @author danecek
 */
@Entity
public class Line extends Element {

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "x", column = @Column(name = "END_X")),
        @AttributeOverride(name = "y", column = @Column(name = "END_Y"))})
    private Pos lineEnd;

    public Line() {
    }

    public Line(Long id, Pos ref, Color color, boolean fill, MyStroke stroke) {
        super(id, ref, color, fill, stroke, 0, 0);
    }

    public Line(Pos ref, Color color, boolean fill, MyStroke stroke, Pos end) {
        super(ref, color, fill, stroke, 0, 0);
        this.lineEnd = end;
    }

    @Override
    public void myPaint(Graphics2D g, boolean obrys) {
        g.setColor(new Color(getRGBcolor()));
        if (isFill()) {
            g.setStroke(App.BASIC_STROKE);
            g.fillOval(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
        } else {
            if (obrys) {
                g.setStroke(App.BASIC_STROKE);
                g.drawRect(Math.min(getPosition().getX(), lineEnd.getX()),
                        Math.min(getPosition().getY(), lineEnd.getY()),
                        getWidth(), getHeight());
            }
            g.setStroke(getMyStroke().getStroke());
            g.drawLine(getPosition().getX(), getPosition().getY(), getLineEnd().getX(), getLineEnd().getY());
        }
    }

    /**
     * @return the lineEnd
     */
    public Pos getLineEnd() {
        return lineEnd;
    }

    /**
     * @param end the lineEnd to set
     */
    public void setLineEnd(Pos end) {
        this.lineEnd = end;
    }

    @Override
    public int getWidth() {
        return Math.abs(getPosition().getX() - lineEnd.getX());
    }

    @Override
    public int getHeight() {
        return Math.abs(getPosition().getY() - lineEnd.getY());
    }

}
