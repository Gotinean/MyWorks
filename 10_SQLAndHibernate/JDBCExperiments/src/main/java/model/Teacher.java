package model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Teachers")
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer salary;
    private Integer age;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private List<Course> courseList;
}
