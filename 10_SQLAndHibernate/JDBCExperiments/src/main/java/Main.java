import dao.CourseDAO;
import dao.DAO;
import model.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main{

    public static void main(String[] args) {
    SessionFactory factory = null;
    try {
        factory = new Configuration().configure().buildSessionFactory();
        DAO<Course, Integer> CourseDAO = new CourseDAO(factory);
        final Course result = CourseDAO.read(1);
        System.out.println("Result : " + result);
        System.out.println();

    }
    finally {
        if(factory != null){
            factory.close();
        }
    }




    }
}
