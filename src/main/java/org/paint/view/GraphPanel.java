/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import org.paint.model.AbstractElementFactory;
import org.paint.model.Element;
import org.paint.model.Polyline;
import org.paint.App;
import org.paint.resource.DAO;

/**
 *
 * @author danecek
 */
public class GraphPanel extends JPanel implements Observer {

    Element elm;
    Point p;
    Point ref;
    boolean ctrl;

    Point fix(Point p) {
        if (!ctrl) {
            return p;
        }
        return new Point(p.x, ref.y + p.x - ref.x);

    }

    public GraphPanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        requestFocus();
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                ctrl = e.isControlDown();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                ctrl = e.isControlDown();
            }
        });
        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if (!App.INST.isSelectionMode()) {
                    Graphics2D g = (Graphics2D) GraphPanel.this.getGraphics();
                    if (elm != null) {
                        g.setXORMode(elm.getXORColor());
                        elm.myPaint(g, true);
                    }
                    if (elm instanceof Polyline) {
                        Polyline pl = (Polyline) elm;
                        pl.addPos(e.getX(), e.getY());
                    } else if (e.getPoint().distance(ref.getX(), ref.getY()) > 5) {
                        elm = AbstractElementFactory.INST.createElement(ref, fix(e.getPoint()));
                    }
                    if (elm != null) {
                        g.setXORMode(elm.getXORColor());
                        elm.myPaint(g, true);
                    }
                }
            }
        });
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                ref = e.getPoint();
                requestFocusInWindow();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    if (App.INST.isSelectionMode()) {
                        App.INST.setSelected(DAO.INST.find(e.getPoint()));
                    } else if (elm instanceof Polyline) {
                        Polyline pl = (Polyline) elm;
                        pl.addPos(e.getX(), e.getY());
                        DAO.INST.create(elm);
                    } else {
                        if (!ref.equals(e.getPoint())) {
                            DAO.INST.create(AbstractElementFactory.INST.createElement(ref, fix(e.getPoint())));

                        }
                    }
                    elm = null;
                    App.INST.notifyObservers();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        App.INST.addObserver(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Element e : DAO.INST.all()) {
            e.paint(g.create());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

}
