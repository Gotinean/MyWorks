
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubscriptionPK implements Serializable {
    @Column(name = "course_id")
    protected Integer courseId;
    @Column(name = "student_id")
    protected Integer studentId;

    public SubscriptionPK(){}
    public SubscriptionPK(Integer courseId, Integer studentId){
        this.courseId = courseId;
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriptionPK)) return false;
        SubscriptionPK that = (SubscriptionPK) o;
        return Objects.equals(courseId, that.courseId) && Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, studentId);
    }
}
