package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Course;
import model.Student;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionPK implements Serializable {
    @JoinColumn(name = "course_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    @JoinColumn(name = "student_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;
}


