import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "Subscriptions")
public class Subscription implements Serializable {
    @EmbeddedId
    private SubscriptionPK subscriptionPK;

    @Column(name = "subscription_date")
    private Date subscriptionDate;


}
