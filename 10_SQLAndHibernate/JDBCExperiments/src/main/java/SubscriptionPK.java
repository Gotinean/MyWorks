
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
@Data
@AllArgsConstructor
public class SubscriptionPK implements Serializable {
    @Column(name = "course_id")
    protected Integer courseId;
    @Column(name = "student_id")
    protected Integer studentId;
    @JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Course course;

    @JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Student student;
}


