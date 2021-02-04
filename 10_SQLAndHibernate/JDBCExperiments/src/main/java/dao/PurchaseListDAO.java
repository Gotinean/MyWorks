package dao;

import com.sun.istack.NotNull;
import model.Course;
import model.PurchaseList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PurchaseListDAO implements DAO<PurchaseList, Integer> {
    private final SessionFactory factory;

    public PurchaseListDAO(@NotNull final SessionFactory factory){this.factory = factory;}
    @Override
    public void create(PurchaseList purchaseList) {

    }

    @Override
    public PurchaseList read(Integer i) {
        try(final Session session = factory.openSession()) {
            final PurchaseList result = session.get(PurchaseList.class, i);
            return result !=null ? result : new PurchaseList();
        }
    }

    @Override
    public void update(PurchaseList purchaseList) {

    }

    @Override
    public void delete(PurchaseList purchaseList) {

    }
}
