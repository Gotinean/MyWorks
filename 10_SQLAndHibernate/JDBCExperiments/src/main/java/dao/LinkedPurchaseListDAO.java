package dao;

import com.sun.istack.NotNull;
import model.LinkedPurchaseList;
import model.PurchaseList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class LinkedPurchaseListDAO implements DAO<LinkedPurchaseList, Integer>{
    private final SessionFactory factory;

    public LinkedPurchaseListDAO(@NotNull final SessionFactory factory){this.factory = factory;}


    @Override
    public void create(LinkedPurchaseList linkedPurchaseList) {
        try(final Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(linkedPurchaseList);
            session.getTransaction().commit();
        }
    }

    @Override
    public LinkedPurchaseList read(Integer i) {
        try(final Session session = factory.openSession()) {
            final LinkedPurchaseList result = session.get(LinkedPurchaseList.class, i);
            return result !=null ? result : new LinkedPurchaseList();
        }
    }

    @Override
    public void update(LinkedPurchaseList linkedPurchaseList) {
        try(final Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(linkedPurchaseList);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(LinkedPurchaseList linkedPurchaseList) {
        try(final Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(linkedPurchaseList);
            session.getTransaction().commit();
        }
    }


}
