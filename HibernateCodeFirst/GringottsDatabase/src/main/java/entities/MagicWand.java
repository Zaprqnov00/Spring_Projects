package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "magic_wand")
public class MagicWand {

    @Id
    @Column
    private Integer id;

    @Column(name = "magic_wand_creator", length = 100)
    private String creator;

    @Column(name = "magic_wand_size")
    private Integer size;



}
