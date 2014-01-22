/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.paint.App;

/**
 *
 * @author danecek
 */
@Entity
public abstract class Element implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private Pos position;
    private int RGBcolor;
    private boolean fill;
    @Embedded
    private MyStroke myStroke;

    public Element() {
    }

    public Element(Long id, Pos ref, Color color, boolean fill, MyStroke myStroke,
            int width, int height) {
        this.id = id;
        this.position = ref;
        this.RGBcolor = color.getRGB();
        this.fill = fill;
        this.myStroke = myStroke;
    }

    public Element(Pos ref, Color color, boolean fill, MyStroke myStroke, int width, int height) {
        this(UUID.randomUUID().getLeastSignificantBits(), ref, color, fill, myStroke, width, height);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void myPaint(Graphics2D g, boolean obrys) {
        g.setColor(new Color(getRGBcolor()));
        if (!isFill()) {
            if (obrys) {
                g.setStroke(App.BASIC_STROKE);
                g.drawRect(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
            }
            g.setStroke(myStroke.getStroke());
        }
    }

    public void paint(Graphics g) {
        myPaint((Graphics2D) g, App.INST.getSelected() == this);
    }

    /**
     * @return the RGBcolor
     */
    public int getRGBcolor() {
        return RGBcolor;
    }

    public Color getXORColor() {
        Color c = getColor();
        return new Color(255- c.getRed(), 255- c.getGreen(), 255- c.getBlue());

    }
    
        public Color getColor() {
        return new Color(RGBcolor);

    }

    /**
     * @param color the RGBcolor to set
     */
    public void setRGBcolor(int color) {
        this.RGBcolor = color;
    }

    public abstract int getHeight();

    /**
     * @return the width
     */
    public abstract int getWidth();

    /**
     * @return the myStroke
     */
    public MyStroke getMyStroke() {
        return myStroke;
    }

    /**
     * @param myStroke the myStroke to set
     */
    public void setMyStroke(MyStroke myStroke) {
        this.myStroke = myStroke;
    }

    /**
     * @return the position
     */
    public Pos getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Pos position) {
        this.position = position;
    }

    /**
     * @return the fill
     */
    public boolean isFill() {
        return fill;
    }

    /**
     * @param fill the fill to set
     */
    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public double distance(Point p) {
        return p.distance(getPosition().getX(), getPosition().getY());
    }

}
