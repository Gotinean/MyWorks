import javax.persistence.*;
import java.util.Date;

@Entity
public class PurchaseList {
    @EmbeddedId
    private PurchaseListPK purchaseListPK;
    @JoinColumn(name = "price", insertable = true, updatable = true)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private int price;
    @Column (name = "subscription_date")
    @JoinColumn(name = "subscription_date", insertable = true, updatable = true)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Date subscriptionDate;
}
