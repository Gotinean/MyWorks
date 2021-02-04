package dao;

import com.sun.istack.NotNull;
import model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TeacherDAO implements DAO <Teacher, Integer> {
    private final SessionFactory factory;
    public TeacherDAO(@NotNull final SessionFactory factory){this.factory=factory;}
    @Override
    public void create(Teacher teacher) {

    }

    @Override
    public Teacher read(Integer i) {
        try(final Session session = factory.openSession()) {
            final Teacher result = session.get(Teacher.class, i);
            return result !=null ? result : new Teacher();
        }
    }

    @Override
    public void update(Teacher teacher) {

    }

    @Override
    public void delete(Teacher teacher) {

    }
}
