package dao;

import com.sun.istack.NotNull;
import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;

public class StudentDAO implements DAO<Student, Integer> {
    private final SessionFactory factory;
    public StudentDAO(@NotNull final SessionFactory factory){this.factory = factory;}
    @Override
    public void create(Student student) {

    }

    @Override
    public Student read(Integer i) {
        try(final Session session = factory.openSession()){
            final Student result = session.get(Student.class, i);
            return result !=null ? result : new Student();
        }
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(Student student) {

    }
}
