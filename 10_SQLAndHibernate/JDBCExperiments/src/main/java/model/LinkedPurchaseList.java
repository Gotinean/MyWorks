package model;
import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.security.Key;
import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "Linked_purchase_list")
public class LinkedPurchaseList implements Serializable {
    @EmbeddedId
    private LinkedPurchaseListPK linkedPurchaseListPK;
    @Column(name = "student_name")
    @JoinTable(name = "Students", joinColumns ={@JoinColumn(name = "name")})
    private String studentName;
    @Column(name = "course_name")
    @JoinTable(name = "Courses", joinColumns ={@JoinColumn(name = "name")})
    private String courseName;
    @Column(name = "subscription_date")
    @JoinTable(name = "Subscriptions", joinColumns ={@JoinColumn(name = "subscription_date")})
    private Date subscriptionDate;
    @Column(name = "course_price")
    @JoinTable(name = "Course", joinColumns ={@JoinColumn(name = "price")})
    private Integer coursePrice;
}
