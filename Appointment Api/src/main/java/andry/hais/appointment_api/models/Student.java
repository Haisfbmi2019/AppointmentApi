package andry.hais.appointment_api.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Student implements Serializable {
    @Id
    @NaturalId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String universityName;

    @NotEmpty
    private String gradeBookNum;

    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Lesson> lessons = new HashSet<>();

    public Student(String universityName, String gradeBookNum) {
        this.universityName = universityName;
        this.gradeBookNum = gradeBookNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(universityName, student.universityName) &&
                Objects.equals(gradeBookNum, student.gradeBookNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, universityName, gradeBookNum);
    }
}