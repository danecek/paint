/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.view;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.AbstractTableModel;
import org.paint.model.Element;
import org.paint.App;
import org.paint.resource.DAO;

/**
 *
 * @author danecek
 */
public class GraphTableModel extends AbstractTableModel implements Observer {

    List<Element> elems = new ArrayList<>();

    public GraphTableModel() {
        App.INST.addObserver(this);
    }

    @Override
    public int getRowCount() {
        return elems.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id";
            case 1:
                return "X";
            case 2:
                return "Y";
            case 3:
                return "Discriminant";
            case 4:
                return "Radius";
            case 5:
                return "Width";
            case 6:
                return "Height";

        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Element e = elems.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return e.getId();
            case 1:
                return e.getClass().getSimpleName();
            case 2:
                return e.getPosition().getX();
            case 3:
                return e.getPosition().getY();

        }
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        elems = new ArrayList<>(DAO.INST.all());

        Collections.sort(elems, new Comparator<Element>() {

            @Override
            public int compare(Element o1, Element o2) {
                return o1.getId().compareTo(o2.getId());

            }
        });
        fireTableDataChanged();
    }

}
