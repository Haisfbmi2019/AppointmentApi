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
public class Teacher implements Serializable {
    @Id
    @NaturalId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String degree;

    @NotEmpty
    private String department;

    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Prices> prices= new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Schedule> schedules = new HashSet<>();

    public Teacher(String degree, String department) {
        this.degree = degree;
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id) &&
                Objects.equals(degree, teacher.degree) &&
                Objects.equals(department, teacher.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, degree, department);
    }
}
