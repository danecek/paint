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
public class PolylineFactory extends AbstractElementFactory {

    @Override
    public Element createElement(Point p1, Point p2) {
        return new Polyline(ref(p1, p2),
                App.INST.getColor(), App.INST.isFill(), App.INST.getActualStroke());
    }
}
