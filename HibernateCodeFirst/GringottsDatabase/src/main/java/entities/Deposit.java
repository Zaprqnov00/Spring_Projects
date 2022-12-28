package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "deposit")
public class Deposit {

    @Id
    @Column(name = "deposit_id")
    private Integer id;

    @Column(name = "deposit_group", length = 20)
    private String group;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "deposit_amount")
    private Double amount;

    @Column(name = "deposit_interest")
    private Double interest;

    @Column(name = "deposit_charge")
    private Double charge;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "is_expired")
    private Boolean isExpired;

}
