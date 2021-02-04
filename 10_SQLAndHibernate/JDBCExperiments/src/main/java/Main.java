import com.fasterxml.classmate.AnnotationConfiguration;
import dao.CourseDAO;
import dao.DAO;
import dao.TeacherDAO;
import model.Course;
import model.Student;
import model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main{

    public static void main(String[] args) {
    SessionFactory factory = null;
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        String hql = "From " + Course.class.getSimpleName() + " Where price > 120000";
        List<Course> courseList = session.createQuery(hql).getResultList();
        for (Course course : courseList){
            System.out.println(course.getName() + " - " + course.getPrice());
        }
        String hqlStudents = "From " + Student.class.getSimpleName() + " Where id < 5";
        List<Student> list = session.createQuery(hqlStudents).getResultList();
        for (Student student : list){
            System.out.println(student.getName() + " " + student.getId());
        }


//    try {
//        factory = new Configuration().configure().buildSessionFactory();
//        DAO<Course, Integer> CourseDAO = new CourseDAO(factory);
//        final Course result = CourseDAO.read(1);
//        System.out.println("Result : " + result.getSubscriptions().get(0).getSubscriptionPK().getStudent().getName());
//        System.out.println();
//        DAO<Teacher, Integer> teacherStringDAO = new TeacherDAO(factory);
//        final Teacher resultTeacher = teacherStringDAO.read(1);
//        System.out.println("Result : " + result.getName());
//
//    }
//    finally {
//        if(factory != null){
//            factory.close();
//        }
//    }




    }
}
