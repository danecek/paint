/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.paint.view;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author danecek
 */
public class TablePanel extends JScrollPane {

    public TablePanel() {
        super(new JTable(new GraphTableModel()));
    }
    
    
    
}
