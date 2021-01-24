import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "Students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Subscriptions",
            joinColumns = {@JoinColumn(name = "student_id")})
    private int id;
    private String name;
    private int age;
    @Column(name = "registration_date")
    private Date registrationDate;
}
