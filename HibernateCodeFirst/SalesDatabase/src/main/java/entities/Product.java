package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "product_quantity", nullable = false)
    private Double quantity;

    @Column(name = "product_price", nullable = false)
    private BigDecimal price;

    @OneToMany()
    private Set<Sale> sales;
}
