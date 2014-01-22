/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.resource.derby;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.paint.model.Circle;
import org.paint.model.Element;
import org.paint.model.Rectangle;
import org.paint.resource.DAO;

/**
 *
 * @author danecek
 */
public class DerbyDAO extends DAO {

    File f = new File(System.getProperty("user.home"), "graph.bin");
    private Connection connection;
    private PreparedStatement createCirclePS;
    private PreparedStatement deletePS;
    private PreparedStatement findAllPS;
    private PreparedStatement deleteAllPS;
    private PreparedStatement createReactanglePS;

    public DerbyDAO() {
        try {
            new org.apache.derby.jdbc.EmbeddedDriver();
            String url = "jdbc:derby:" + System.getProperty("user.home") + "/graphDB; create=true";
            connection = DriverManager.getConnection(url);
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, "ELEMENTS", null);
            if (!rs.next()) {
                Logger.getLogger(getClass().getName()).
                        log(Level.INFO, "Table ELEMENTS generated");
                Statement s = connection.createStatement();
                s.executeUpdate("CREATE TABLE ELEMENTS"
                        + "(ID VARCHAR(36) NOT NULL,"
                        + "X   INT NOT NULL,"
                        + "Y   INT NOT NULL,"
                        + "DISCR VARCHAR(20) NOT NULL,"
                        + "RADIUS   INT,"
                        + "WIDTH   INT,"
                        + "HEIGHT   INT,"
                        + "PRIMARY KEY (ID))");

            }
            createCirclePS = connection.prepareStatement("INSERT INTO ELEMENTS VALUES(?, ?, ?, ?, ?   , NULL, NULL)");
            createReactanglePS = connection.prepareStatement("INSERT INTO ELEMENTS VALUES(?, ?, ?, ?, NULL, ?   , ?)");
            deletePS = connection.prepareStatement("DELETE FROM ELEMENTS WHERE ID = ?");
            deleteAllPS = connection.prepareStatement("DELETE FROM ELEMENTS");
            findAllPS = connection.prepareStatement("SELECT * FROM ELEMENTS");
            x();

        } catch (SQLException ex) {
            Logger.getLogger(DerbyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void create(Element e) {
        try {
            super.create(e);
            int i = 0;
            if (e instanceof Circle) {
                Circle c = (Circle) e;
                createCirclePS.setLong(++i, c.getId());
                createCirclePS.setInt(++i, c.getPosition().getX());
                createCirclePS.setInt(++i, c.getPosition().getY());
                createCirclePS.setString(++i, c.getClass().getSimpleName());
                createReactanglePS.setInt(++i, c.getWidth());
                createReactanglePS.setInt(++i, c.getHeight());
                createCirclePS.execute();
            } else if (e instanceof Rectangle) {
                Rectangle c = (Rectangle) e;
                createReactanglePS.setLong(++i, c.getId());
                createReactanglePS.setInt(++i, c.getPosition().getX());
                createReactanglePS.setInt(++i, c.getPosition().getY());
                createReactanglePS.setString(++i, c.getClass().getSimpleName());
                createReactanglePS.setInt(++i, c.getWidth());
                createReactanglePS.setInt(++i, c.getHeight());
                createReactanglePS.execute();
            }
            //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            Logger.getLogger(DerbyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void store() {

    }

    @Override
    public void clear() {
        super.clear();
        try {
            deleteAllPS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void x() throws SQLException {
        ResultSet rs = findAllPS.executeQuery();
        try {
            while (rs.next()) {
//                String discr = rs.getString("DISCR");
//                Color color = Color.decode(rs.getString("COLOR"));
//                if (discr.equals(Circle.class.getSimpleName())) {
//                    cache.put(new Circle(rs.getString("ID"),
//                            new Pos(rs.getInt("X"), rs.getInt("Y")),
//                            color,
//                            rs.getBoolean("FILLED"),
//                            rs.getInt("WIDTH"),
//                            rs.getInt("HEIGHT")));
//                } else if (discr.equals(Rectangle.class.getSimpleName())) {
//                    cache.put(new Rectangle(rs.getString("ID"),
//                            new Pos(rs.getInt("X"), rs.getInt("Y")),
//                            color,
//                            rs.getBoolean("FILLED"),
//                            rs.getInt("WIDTH"),
//                            rs.getInt("HEIGHT")));
//                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
