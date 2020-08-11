package andry.hais.appointment_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class Profile {
    @Id
    @NaturalId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Student student;

    @OneToOne(cascade = CascadeType.ALL)
    private Teacher teacher;

    public Profile(Student student) {
        this.student = student;
    }

    public Profile(Teacher teacher) {
        this.teacher = teacher;
    }
}
