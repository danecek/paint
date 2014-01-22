/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import org.paint.App;

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
        points.add(ref);
    }

    public Polyline(Pos ref, Color color, boolean fill, MyStroke stroke) {
        super(ref, color, fill, stroke, 0, 0);
        points.add(ref);
    }
    

    @Override
    public void myPaint(Graphics2D g, boolean obrys) {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        g.setColor(new Color(getRGBcolor()));
        int[] xPoints = new int[getPoints().size()];
        int[] yPoints = new int[getPoints().size()];
        int i = 0;
        for (Pos pos : getPoints()) {
            int x = pos.getX();
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            xPoints[i] = x;
            int y = pos.getY();
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);

            yPoints[i] = y;
            i++;
        }
        if (obrys) {
            g.setStroke(App.BASIC_STROKE);
            g.drawRect(minX, minY, maxX - minX, maxY - minY);
        }
        g.setStroke(getMyStroke().getStroke());
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

    @Override
    public int getWidth() {
        int minX = -Integer.MAX_VALUE;
        int maxX = -Integer.MIN_VALUE;

        for (Pos p : points) {
            minX = Math.min(minX, p.getX());
            minX = Math.max(minX, p.getX());
        }
        return maxX - minX;
    }

    @Override
    public int getHeight() {
        int minY = -Integer.MAX_VALUE;
        int maxY = -Integer.MIN_VALUE;
        for (Pos p : points) {
            minY = Math.min(minY, p.getX());
            maxY = Math.max(maxY, p.getX());
        }
        return maxY - minY;
    }

}
