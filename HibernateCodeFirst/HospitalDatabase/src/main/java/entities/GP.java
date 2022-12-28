package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "gp")
public class GP {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "diagnose", nullable = false)
    private String diagnose;

    @Column(name = "medicaments")
    private String medicament;

    @ManyToOne(optional = false)
    private Visitation visitation;


}
