import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PurchaseListPK implements Serializable {
    @Column(name = "student_name")
    private Integer studentName;
    @Column (name = "course_name")
    private Integer courseName;

    public PurchaseListPK(){}
    public PurchaseListPK(Integer studentName, Integer courseName){
        this.courseName = courseName;
        this.studentName = studentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PurchaseListPK)) return false;
        PurchaseListPK that = (PurchaseListPK) o;
        return Objects.equals(studentName, that.studentName) && Objects.equals(courseName, that.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, courseName);
    }
}
