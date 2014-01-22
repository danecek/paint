/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.model;

import java.awt.Point;
import org.paint.model.Element;

/**
 *
 * @author danecek
 */
public abstract class AbstractElementFactory {

    public static AbstractElementFactory INST = new CircleFactory();

    public abstract Element createElement(Point p, Point point);

    //public abstract Element createElement(Point p);

    static int width(Point ref, Point point) {
        return Math.abs(ref.x - point.x);
    }

    static int height(Point p1, Point p2) {
        return Math.abs(p1.y - p2.y);
    }

    static Pos ref(Point p1, Point p2) {
        return new Pos(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y));
    }

}
