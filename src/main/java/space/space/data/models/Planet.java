package space.space.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import space.space.data.models.base.BaseEntity;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "planet")
public class Planet extends BaseEntity {
    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column
    private PlanetSize size;

    @Enumerated(EnumType.STRING)
    @Column
    private PlanetUpgrades upgrades;

    @Column
    private long incomePerTrip;

    @Column
    private long rating=0;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
