/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.controller;

import java.awt.event.ActionEvent;
import org.paint.App;
import org.paint.resource.DAO;

/**
 *
 * @author danecek
 */
public class DeleteSelectedAction extends GraphAction {

    public static DeleteSelectedAction INST = new DeleteSelectedAction();

    public DeleteSelectedAction() {
        super("Delete Selected");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DAO.INST.delete();
        App.INST.notifyObservers();
    }

}
