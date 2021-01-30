package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
public class PurchaseList {
    @EmbeddedId
    private PurchaseListPK purchaseListPK;
    private int price;
    @Column (name = "subscription_date")
    private Date subscriptionDate;

}
