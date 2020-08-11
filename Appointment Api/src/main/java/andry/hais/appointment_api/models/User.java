package andry.hais.appointment_api.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@DynamicUpdate
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    @NotEmpty
    private String email;
    @NotEmpty
    @Size(min = 3, message = "Length must be more than 3")
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty
    private String phone;
    @NotNull
    private boolean active;
    @NotEmpty
    private String role = "ROLE_STUDENT";

    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;

    public User(String email, String password, String name, String phone, boolean active, String role, Profile profile) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.active = active;
        this.role = role;
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", active=" + active +
                ", role='" + role + '\'' +
                '}';
    }
}
