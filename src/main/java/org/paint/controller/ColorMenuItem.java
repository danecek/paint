/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import static javax.swing.Action.NAME;
import javax.swing.JColorChooser;
import javax.swing.JMenuItem;
import org.paint.App;

/**
 *
 * @author danecek
 */
public class ColorMenuItem extends JMenuItem {

    public ColorMenuItem() {
        super(new AbstractAction("Color") {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.INST.setColor(JColorChooser.showDialog(App.INST.getMainFrame(), NAME, App.INST.getColor()));
            }
        });
    }

    @Override
    public Color getBackground() {
        return App.INST.getColor();
    }

    @Override
    public Color getForeground() {
        return new Color(~getBackground().getRGB());
    }
}
