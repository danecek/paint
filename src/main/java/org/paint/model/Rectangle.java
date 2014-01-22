/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.model;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.persistence.Entity;

/**
 *
 * @author danecek
 */
@Entity
public class Rectangle extends Element {

    public Rectangle() {
    }

    public Rectangle(Pos ref, MyStroke stroke, Color color, boolean fill, int width, int height) {
        super(ref, color, fill, stroke, width, height);
        this.width = width;
        this.height = height;
    }

    public Rectangle(Long id, Pos ref, Color color, boolean fill, MyStroke stroke, int width, int height) {
        super(id, ref, color, fill, stroke, width, height);
        this.width = width;
        this.height = height;
    }

    @Override
    public void myPaint(Graphics2D g) {
        super.myPaint(g);
        if (isFill()) {
            g.fillRect(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
        } else {
            g.drawRect(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
        }
    }

}
