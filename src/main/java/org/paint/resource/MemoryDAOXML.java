/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.resource;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.paint.model.Circle;
import org.paint.model.Element;
import org.paint.model.Line;
import org.paint.model.Polyline;
import org.paint.model.Pos;
import org.paint.model.Rectangle;

/**
 *
 * @author danecek
 */
public class MemoryDAOXML extends DAO {

    JAXBContext context;
    File f = new File(System.getProperty("user.home"), "graph.xml");

    public MemoryDAOXML() {
        try {
            context = JAXBContext.newInstance(Cache.class, Circle.class, 
                    Element.class, Pos.class, Rectangle.class, Line.class, Polyline.class);

            if (f.exists()) {
                cache = (Cache) context.createUnmarshaller().unmarshal(f);
            }
        } catch (JAXBException ex) {
            Logger.getLogger(MemoryDAOXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void store() {
        try {
            context.createMarshaller().marshal(cache, f);
        } catch (JAXBException ex) {
            Logger.getLogger(MemoryDAOXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
