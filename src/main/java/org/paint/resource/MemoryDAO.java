/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danecek
 */
public class MemoryDAO extends DAO {

    File f = new File(System.getProperty("user.home"), "graph.bin");

    public MemoryDAO() {
        try (ObjectInputStream oos
                = new ObjectInputStream(
                        new FileInputStream(f))) {
                    cache = (Cache) oos.readObject();
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(MemoryDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

    }

    @Override
    public void store() {
        try (ObjectOutputStream oos
                = new ObjectOutputStream(
                        new FileOutputStream(f))) {
                    oos.writeObject(cache);
                } catch (IOException ex) {
                    Logger.getLogger(MemoryDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
}
