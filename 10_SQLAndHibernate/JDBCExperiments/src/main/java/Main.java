import com.fasterxml.classmate.AnnotationConfiguration;
import dao.CourseDAO;
import dao.DAO;
import dao.TeacherDAO;
import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.xml.crypto.Data;
import java.util.List;

public class Main{

    public static void main(String[] args) {
    SessionFactory factory = null;
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        String hql = "From " + Course.class.getSimpleName();
        List<Course> courseList = session.createQuery(hql).getResultList();
        String hql1 = "From " + Student.class.getSimpleName();
        List<Student> studentList = session.createQuery(hql1).getResultList();
        String hql2 = "From " + Subscription.class.getSimpleName();
        List<Subscription> subscriptionList = session.createQuery(hql2).getResultList();
        LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
        LinkedPurchaseListPK l = new LinkedPurchaseListPK();
        session.beginTransaction();

        for(Subscription subscription : subscriptionList) {
            l = new LinkedPurchaseListPK(subscription.getSubscriptionPK().getStudent().getId(), subscription.getSubscriptionPK().getCourse().getId());
            linkedPurchaseList = new LinkedPurchaseList(l,null,null,null,null);
            session.save(linkedPurchaseList);
        }
//        LinkedPurchaseList l = new LinkedPurchaseList(linkedPurchaseListPK, "Фуриков Эрнст", "Веб-разработчик с 0 до PRO", null, 189600);
        session.getTransaction().commit();


//        String hqlStudents = "From " + Student.class.getSimpleName() + " Where id < 5";
//        List<Student> list = session.createQuery(hqlStudents).getResultList();
//        for (Student student : list){
//            System.out.println(student.getName() + " " + student.getId());
//        }
        session.close();


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
