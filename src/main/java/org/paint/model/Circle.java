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
//@XmlType
@Entity
public class Circle extends Element {

    public Circle() {
    }

    public Circle(Pos ref, Color color, boolean fill, MyStroke stroke, int width, int height) {
        super(ref, color, fill, stroke, width, height);
    }

    public Circle(Long id, Pos ref, Color color, boolean fill, MyStroke stroke, int width, int height) {
        super(id, ref, color, fill, stroke, width, height);
    }

    @Override
    public void myPaint(Graphics2D g) {
        super.myPaint(g);
        if (isFill()) {
            g.fillOval(getPosition().getX(), getPosition().getY(), width, height);
        } else {
            g.drawOval(getPosition().getX(), getPosition().getY(), width, height);
        }
    }

}
