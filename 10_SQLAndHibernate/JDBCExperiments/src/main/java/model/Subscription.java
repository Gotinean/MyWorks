package model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "Subscriptions")
@Data
public class Subscription implements Serializable {
    @EmbeddedId
    private SubscriptionPK subscriptionPK;
    @Column(name = "subscription_date")
    private Date subscriptionDate;


}
