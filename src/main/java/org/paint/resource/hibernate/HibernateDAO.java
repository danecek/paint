/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paint.resource.hibernate;

import java.util.Collection;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.paint.model.Element;
import org.paint.model.Polyline;
import org.paint.model.Pos;
import org.paint.resource.DAO;

/**
 *
 * @author danecek
 */
public class HibernateDAO extends DAO {

    @Override
    public void store() {

    }

    @Override
    public Collection<Element> all() {

        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Element> l = s.createQuery("SELECT e FROM Element e").list();
        for (Element e : l) {
            if (e instanceof Polyline) {
                Polyline p = (Polyline) e;
                for (Pos pos : p.getPoints()) {
                    Hibernate.initialize(pos);
                }
            }
        }
        s.getTransaction().commit();
        return l;
    }

    @Override
    public void create(Element e) throws Exception {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(e);
        s.getTransaction().commit();
    }

}
