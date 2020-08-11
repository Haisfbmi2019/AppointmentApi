package andry.hais.appointment_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@DynamicUpdate
public class Prices implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Teacher teacher;

    @NotEmpty
    private String subject;

    @NotNull
    private Integer fifteenMinPrice;

    @NotNull
    private Integer halfAnHourPrice;

    @NotNull
    private Integer hourPrice;

    public Prices(Teacher teacher, String subject, Integer fifteenMinPrice, Integer halfAnHourPrice, Integer hourPrice) {
        this.teacher = teacher;
        this.subject = subject;
        this.fifteenMinPrice = fifteenMinPrice;
        this.halfAnHourPrice = halfAnHourPrice;
        this.hourPrice = hourPrice;
    }
}
