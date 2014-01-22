/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.controller;

import org.paint.controller.FileMenu;
import org.paint.controller.ElementMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author danecek
 */
public class GraphMenuBar extends JMenuBar {

    public GraphMenuBar() {
        add(new FileMenu());
        add(new ElementMenu());
    }
}
