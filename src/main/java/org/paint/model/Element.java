/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    protected int height;
    protected int width;
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
        this.width = width;
        this.height = height;
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


    public void myPaint(Graphics2D g) {
        if (App.INST.getSelected() == this) {
            g.drawRect(getPosition().getX(), getPosition().getY(), width, height);
        }
        g.setColor(new Color(getRGBcolor()));
        if (!isFill()) {
            g.setStroke(myStroke.getStroke());
        }
    }

    public void paint(Graphics g) {
        myPaint((Graphics2D) g);
    }

    /**
     * @return the RGBcolor
     */
    public int getRGBcolor() {
        return RGBcolor;
    }

    /**
     * @param color the RGBcolor to set
     */
    public void setRGBcolor(int color) {
        this.RGBcolor = color;
    }

    public int getHeight() {
        return height;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

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

}
