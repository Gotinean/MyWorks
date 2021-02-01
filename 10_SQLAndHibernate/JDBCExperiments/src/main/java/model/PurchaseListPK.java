package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class PurchaseListPK implements Serializable {
    @Column(name = "student_name")
    private String studentName;
    @Column (name = "course_name")
    private String courseName;

}
