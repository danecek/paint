/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.view;

import org.paint.controller.GraphMenuBar;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import org.paint.App;

/**
 *
 * @author danecek
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        super("Graph");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setJMenuBar(new GraphMenuBar());
        JSplitPane js = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new GraphPanel(800, 600), new TablePanel());
        add(js);
        pack();
        setVisible(true);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
              App.INST.exit();
            }

        });
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

}
