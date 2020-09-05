import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "Subscriptions")
public class Subscription implements Serializable {
    @EmbeddedId
    private SubscriptionPK subscriptionPK;
    @JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Course course;

    @JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Student student;
    @Column(name = "subscription_date")
    private Date subscriptionDate;


}
