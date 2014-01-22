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
//@XmlType
@Entity
public class Circle extends ElementWidthHeight {

    public Circle() {
    }

    public Circle(Pos ref, Color color, boolean fill, MyStroke stroke, int width, int height) {
        super(ref, color, fill, stroke, width, height);
    }

    public Circle(Long id, Pos ref, Color color, boolean fill, MyStroke stroke, int width, int height) {
        super(id, ref, color, fill, stroke, width, height);
    }

    @Override
    public double distance(Point p) {
        return p.distance(getPosition().getX() + getWidth() / 2, getPosition().getY());
    }

    @Override
    public void myPaint(Graphics2D g, boolean obrys) {
        g.setColor(new Color(getRGBcolor()));
        if (isFill()) {
            g.fillOval(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
        } else {
            if (obrys) {
                g.setStroke(App.BASIC_STROKE);
                g.drawRect(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
            }
            g.setStroke(getMyStroke().getStroke());
            g.drawOval(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
        }
    }

}
