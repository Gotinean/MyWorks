package dao;

import com.sun.istack.NotNull;
import model.Student;
import model.Subscription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;

public class SubscriptionDAO implements DAO<Subscription, Integer>{
    private final SessionFactory factory;
    public SubscriptionDAO(@NotNull final SessionFactory factory){this.factory=factory;}
    @Override
    public void create(Subscription subscription) {

    }

    @Override
    public Subscription read(Integer i) {
        try(final Session session = factory.openSession()) {
            final Subscription result = session.get(Subscription.class, i);
            return result !=null ?  result : new Subscription();
        }
    }

    @Override
    public void update(Subscription subscription) {

    }

    @Override
    public void delete(Subscription subscription) {

    }
}
