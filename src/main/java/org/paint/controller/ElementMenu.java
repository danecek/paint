/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.controller;

import org.paint.controller.ColorMenuItem;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import org.paint.model.AbstractElementFactory;
import org.paint.model.CircleFactory;
import org.paint.model.RectangleFactory;
import org.paint.model.Circle;
import org.paint.model.Line;
import org.paint.model.LineFactory;
import org.paint.model.Polyline;
import org.paint.model.PolylineFactory;
import org.paint.model.Rectangle;
import org.paint.App;

/**
 *
 * @author danecek
 */
public class ElementMenu extends JMenu {

    ButtonGroup bg = new ButtonGroup();

    public ElementMenu() {
        super("Shape");
        add(new JCheckBoxMenuItem(new AbstractAction("Fill") {

            @Override
            public void actionPerformed(ActionEvent e) {
                App.INST.setFill(
                        ((JCheckBoxMenuItem) e.getSource()).getModel().isSelected());
            }
        }));
        JRadioButtonMenuItem rb;
        add(rb = new JRadioButtonMenuItem(new AbstractAction(Circle.class.getSimpleName()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractElementFactory.INST = new CircleFactory();
            }
        }));
        bg.add(rb);
        rb.setSelected(true);
        add(rb = new JRadioButtonMenuItem(new AbstractAction(Rectangle.class.getSimpleName()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractElementFactory.INST = new RectangleFactory();
            }
        }));
        bg.add(rb);
        add(rb = new JRadioButtonMenuItem(new AbstractAction(Polyline.class.getSimpleName()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractElementFactory.INST = new PolylineFactory();
            }
        }));
        bg.add(rb);
        add(rb = new JRadioButtonMenuItem(new AbstractAction(Line.class.getSimpleName()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractElementFactory.INST = new LineFactory();
            }
        }));
        bg.add(rb);
        add(rb = new JRadioButtonMenuItem(new AbstractAction("Select") {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractElementFactory.INST = null;
            }
        }));
        bg.add(rb);
        add(new ColorMenuItem());
    }

}
