package entities;

import com.mysql.cj.jdbc.Blob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false, unique = true)
    private String lastName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "picture")
    private String picture;

    @Column(name = "medical_insurance", nullable = false)
    private Boolean medicalInsurance;

    @ManyToMany
    @JoinTable(name = "patient_gp", joinColumns = @JoinColumn(name = "gp_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "patients_id", referencedColumnName = "id"))
    private List<GP> gpList;

}
