/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.resource;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;
import org.paint.model.Element;

@XmlRootElement
public class Cache implements Serializable {

    private Map<Long, Element> data = new HashMap<>();

    public Cache() {

    }

    public Collection<Element> values() {
        return data.values();
    }

    public int size() {
        return data.size();
    }

    public void put(Element e) {
        data.put(e.getId(), e);
    }

    /**
     * @return the data
     */
    public Map<Long, Element> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Map<Long, Element> data) {
        this.data = data;
    }

    void clear() {
        data.clear();
    }
}
