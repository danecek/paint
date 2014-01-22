/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

/**
 *
 * @author danecek
 */
@Entity
public class Polyline extends Element {

    @ElementCollection
    private List<Pos> points = new ArrayList<>();

    public Polyline() {
    }

    public Polyline(Long id, Pos ref, Color color, boolean fill, MyStroke stroke) {
        super(id, ref, color, fill, stroke, 0, 0);
    }

    public Polyline(Pos ref, Color color, boolean fill, MyStroke stroke) {
        super(ref, color, fill, stroke, 0, 0);
    }

    @Override
    public void myPaint(Graphics2D g) {
        super.myPaint(g);
        int[] xPoints = new int[getPoints().size()];
        int[] yPoints = new int[getPoints().size()];
        int i = 0;
        for (Pos pos : getPoints()) {
            xPoints[i] = pos.getX();
            yPoints[i] = pos.getY();
            i++;
        }
        g.drawPolyline(xPoints, yPoints, getPoints().size());
    }

    public void addPos(int x, int y) {
        getPoints().add(new Pos(x, y));
    }

    /**
     * @return the points
     */
    public List<Pos> getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(List<Pos> points) {
        this.points = points;
    }

}
