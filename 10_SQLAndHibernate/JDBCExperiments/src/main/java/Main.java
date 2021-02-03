import com.fasterxml.classmate.AnnotationConfiguration;
import dao.CourseDAO;
import dao.DAO;
import model.Course;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main{

    public static void main(String[] args) {
    SessionFactory factory = null;
    try {
        factory = new Configuration().configure().buildSessionFactory();
        DAO<Course, Integer> CourseDAO = new CourseDAO(factory);
        final Course result = CourseDAO.read(1);
        System.out.println("Result : " + result.getSubscriptions().get(0).getSubscriptionPK().getStudent().getName());
        System.out.println();

    }
    finally {
        if(factory != null){
            factory.close();
        }
    }




    }
}
