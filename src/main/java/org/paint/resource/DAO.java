/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.resource;

import java.awt.Point;
import java.util.Collection;
import org.paint.App;
import org.paint.model.Element;

/**
 *
 * @author danecek
 */
public abstract class DAO {

    public static DAO INST;

    Cache cache = new Cache();

    public void create(Element e) throws Exception {
        cache.put(e);
    }

    public Collection<Element> all() {
        return cache.values();
    }

    public abstract void store();

    public void clear() {
        cache.clear();
    }

    public Element find(Point p) {
        double minDist = Double.MAX_VALUE;
        Element sel = null;
        for (Element e : cache.values()) {
            double d = e.distance(p);
            if (d < minDist) {
                minDist = d;
                sel = e;
            }
        }
        return sel;
    }

    public int size() {
        return cache.size();
    }

    public void delete() {
        Element e = App.INST.getSelected();
        if (e != null) {
            cache.delete(e);
        }
    }

}
