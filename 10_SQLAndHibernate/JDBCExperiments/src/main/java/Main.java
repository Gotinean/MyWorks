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
        String hql3 = "From " + LinkedPurchaseList.class.getSimpleName();
        LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
        LinkedPurchaseListPK linkedPurchaseListPK = new LinkedPurchaseListPK();
        session.beginTransaction();

        for(Subscription subscription : subscriptionList) {
            linkedPurchaseListPK = new LinkedPurchaseListPK(subscription.getSubscriptionPK().getStudent().getId(), subscription.getSubscriptionPK().getCourse().getId());
            linkedPurchaseList = new LinkedPurchaseList(linkedPurchaseListPK,null,null,subscription.getSubscriptionDate(),null);
            session.saveOrUpdate(linkedPurchaseList);
        }
        List<LinkedPurchaseList> list = session.createQuery(hql3).getResultList();
        for(Course course : courseList){
            for(LinkedPurchaseList linkedList : list) {
                if (course.getId() == linkedList.getLinkedPurchaseListPK().getCourseId()) {
                    linkedList.setCourseName(course.getName());
                    linkedList.setCoursePrice(course.getPrice());
                    session.saveOrUpdate(linkedList);
                }
            }
        }
        for(Student student : studentList){
            for(LinkedPurchaseList linkedList : list) {
                if (student.getId() == linkedList.getLinkedPurchaseListPK().getStudentId()) {
                    linkedList.setStudentName(student.getName());
                    session.saveOrUpdate(linkedList);
                }
            }
        }
        session.getTransaction().commit();
        session.close();

    }

    private void createObject(List<Subscription> list, LinkedPurchaseList obj,LinkedPurchaseListPK o){

    }
    private void addCourseItems(List<Course> courseList, List<LinkedPurchaseList> list){

    }

}
