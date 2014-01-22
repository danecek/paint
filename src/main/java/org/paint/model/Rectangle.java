/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.persistence.Entity;
import org.paint.App;

/**
 *
 * @author danecek
 */
@Entity
public class Rectangle extends ElementWidthHeight {

    public Rectangle() {
    }

    public Rectangle(Pos ref, MyStroke stroke, Color color, boolean fill, int width, int height) {
        super(ref, color, fill, stroke, width, height);
    }

    public Rectangle(Long id, Pos ref, Color color, boolean fill, MyStroke stroke, int width, int height) {
        super(id, ref, color, fill, stroke, width, height);
    }

    @Override
    public void myPaint(Graphics2D g, boolean obrys) {
        g.setColor(new Color(getRGBcolor()));
        if (isFill()) {
            g.fillRect(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
        } else {
            if (obrys) {
                g.setStroke(App.BASIC_STROKE);
                g.drawLine(getPosition().getX(), getPosition().getY(),
                        getPosition().getX() + getWidth(), getPosition().getY() + getHeight());
            }
            g.setStroke(getMyStroke().getStroke());
            g.drawRect(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
        }
    }

}
