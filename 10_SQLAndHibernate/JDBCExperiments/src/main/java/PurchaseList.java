import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PurchaseList {
    @Column (name = "student_name")
    private int studentName;
    @Column (name = "course_name")
    private int courseName;
    private int price;
    @Column (name = "subscription_date")
    private Date subscriptionDate;
}
