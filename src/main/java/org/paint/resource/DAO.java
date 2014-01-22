/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.resource;

import java.awt.Point;
import java.util.Collection;
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
        for (Element e : cache.values()) {
            if (p.distance(e.getPosition().getX(), e.getPosition().getY()) < 10) {
                return e;
            }
        }
        return null;
    }

    public int size() {
        return cache.size();
    }

}
