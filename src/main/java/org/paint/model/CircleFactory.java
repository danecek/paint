/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.model;

import java.awt.Point;
import org.paint.App;

/**
 *
 * @author danecek
 */
public class CircleFactory extends AbstractElementFactory {

    @Override
    public Element createElement(Point p, Point point) {
        return new Circle(ref(p, point),
                App.INST.getColor(), App.INST.isFill(), App.INST.getActualStroke(),
                width(p, point), height(p, point));
    }

//    @Override
//    public Element createElement(Point p) {
//        return new Circle(ref(p, point), 
//                App.INST.getColor(), App.INST.getActualStroke(), 
//                0, 0);
//    }
}
