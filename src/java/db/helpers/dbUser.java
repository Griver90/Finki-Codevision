/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db.helpers;

import classes.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;

/**
 *
 * @author Muhamed
 */
public class dbUser {

    public static User getUser(String username, String password) {
        Session session = null;
        try {
            session = dbHelper.getSessionFact().openSession();
            Criteria ct = session.createCriteria(User.class);
            ct.add(Expression.eq("UserName", username));
            ct.add(Expression.eq("Password", password));
            List<User> users = ct.list();
            if (users != null && users.size() > 0) {
                return users.get(0);
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
    }

    public static Boolean SaveOrUpdate(User user) {
        Session session = null;
        Transaction tx = null;
        try {
            session = dbHelper.getSessionFact().openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            tx.commit();
            return true;
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
    }
}
