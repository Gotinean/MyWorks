package model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseList {
    @EmbeddedId
    private PurchaseListPK purchaseListPK;
    private Integer price;
    @Column (name = "subscription_date")
    private Date subscriptionDate;

}
