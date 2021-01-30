package dao;
import model.Course;
import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CourseDAO implements DAO <Course, Integer> {
    private final SessionFactory factory;
    public CourseDAO(@NotNull final  SessionFactory factory){this.factory = factory;}
    @Override
    public void create(Course course) {
    }

    @Override
    public Course read(Integer integer) {
        try(final Session session = factory.openSession()) {
            final Course result = session.get(Course.class, integer);
            return result !=null ? result : new Course();
        }
    }

    @Override
    public void update(Course course) {

    }

    @Override
    public void delete(Course course) {

    }
}
