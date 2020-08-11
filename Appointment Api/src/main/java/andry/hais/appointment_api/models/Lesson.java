package andry.hais.appointment_api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Teacher teacher;

    @ManyToOne
    @JsonIgnore
    private Student student;

    @NotNull
    private boolean approval;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateFrom;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTo;

    public Lesson(Teacher teacher, Student student, LocalDateTime dateFrom, LocalDateTime dateTo) {
        this.teacher = teacher;
        this.student = student;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return approval == lesson.approval &&
                Objects.equals(id, lesson.id) &&
                Objects.equals(dateFrom, lesson.dateFrom) &&
                Objects.equals(dateTo, lesson.dateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, approval, dateFrom, dateTo);
    }
}
